package com.example.productcatalog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}
	
	@GetMapping("/{productId}")
	public Product getProductById(	
				@PathVariable Long productId
			)
	{
		return productService.getProductById(productId);
	}
	
	@PostMapping
	public Product saveProduct(@RequestBody Product product)
	{
		return productService.saveProduct(product);
	}
	
	@PutMapping("/{productId}")
	public Product updateProduct(
			@PathVariable Long productId,
			@RequestBody Product product
			)
	{
		return productService.updateProduct(productId, product);
	}
	
	@DeleteMapping("/{productId}")
	public String deleteProduct(
			@PathVariable Long productId
			)
	{
		productService.deleteProduct(productId);
		
		return "Product Deleted Successfully";
	}
	
}
