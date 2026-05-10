package com.example.productcatalog.cache;

import com.example.productcatalog.entity.Product;

public class ProductNode {
	Long ProductId;
	Product product;
	ProductNode prev;
	ProductNode next;
	
	public ProductNode(Long productId,
			Product product)
	{
		this.ProductId = productId;
		this.product = product;
	}
}
