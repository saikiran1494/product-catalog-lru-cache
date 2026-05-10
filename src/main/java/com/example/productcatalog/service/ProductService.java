package com.example.productcatalog.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.productcatalog.cache.ProductLRUCache;
import com.example.productcatalog.entity.Product;
import com.example.productcatalog.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductLRUCache productLRUCache;
	private final ProductRepository productRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public ProductService(ProductLRUCache productLRUCache,
			ProductRepository productRepository)
	{
		this.productLRUCache = productLRUCache;
		this.productRepository = productRepository;
	}
	
	public Product getProductById(Long productId)
	{
		//Check Cache
		Product cacheProduct = productLRUCache.getProduct(productId);
		
		
		if (cacheProduct != null)
		{
			logger.info("Product Returned from LRU Cache");
			return cacheProduct;
		}
		
		//Check DB
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if (optionalProduct.isEmpty())
		{
			throw new RuntimeException("Product Not Found");
		}
		
		Product dbProduct = optionalProduct.get();
		
		logger.info("Product Returned from DB");
		
		//Store into Cache
		
		productLRUCache.putProduct(productId, dbProduct);
		
		return dbProduct;
	}
	
	public Product saveProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public Product updateProduct(
			Long productId,
			Product updatedProduct
			)
	{
		Product existingProduct = productRepository
				.findById(productId)
				.orElseThrow(()->new RuntimeException("Product Not Found"));
		
	    existingProduct.setName(
	            updatedProduct.getName());

	    existingProduct.setPrice(
	            updatedProduct.getPrice());

	    existingProduct.setQuantity(
	            updatedProduct.getQuantity());

	    existingProduct.setCategory(
	            updatedProduct.getCategory());
	    
	    Product savedProduct = productRepository.save(existingProduct);
	    
	    //Update Cache
	    
	    productLRUCache.putProduct(
	    		productId,
	    		savedProduct
	    		);
	    
	    return savedProduct;
	}
	
	public void deleteProduct(Long productId)
	{
		Product existingProduct = productRepository
				.findById(productId)
				.orElseThrow(()-> new RuntimeException("Product Not Found"));
		
		productRepository.deleteById(productId);
		
		productLRUCache.removeProduct(productId);
		
		logger.info("Product Removed from DB and cache");
	}
	
	
}
