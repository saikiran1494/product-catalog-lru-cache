package com.example.productcatalog;

import com.example.productcatalog.cache.ProductLRUCache;
import com.example.productcatalog.entity.Product;

public class ProductCatalogTest {
	public static void main(String[] args)
	{
		ProductLRUCache cache = new ProductLRUCache();
		
		Product p1 = new Product(
                101L,
                "iPhone",
                80000,
                10,
                "Mobiles");

        Product p2 = new Product(
                102L,
                "Shoes",
                3000,
                20,
                "Fashion");

        Product p3 = new Product(
                103L,
                "Watch",
                5000,
                15,
                "Accessories");

        Product p4 = new Product(
                104L,
                "MacBook",
                150000,
                5,
                "Electronics");
        
        cache.putProduct(101L, p1);
        cache.putProduct(102L, p2);
        cache.putProduct(103L, p3);
        
        cache.displayCache();
        
        cache.getProduct(103L);
        
        cache.displayCache();
        
        cache.putProduct(104L, p4);
        
	}
}
