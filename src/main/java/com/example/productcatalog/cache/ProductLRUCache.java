package com.example.productcatalog.cache;

import java.util.Map;
import java.util.HashMap;
import com.example.productcatalog.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductLRUCache {
	private int capacity;
	
	private Map<Long,ProductNode> cacheMap;
	
	private ProductNode head;
	private ProductNode tail;
	
	public ProductLRUCache()
	{
		this.capacity = 3;
		
		cacheMap = new HashMap<>();
		
		head = new ProductNode(null,null);
		tail = new ProductNode(null,null);
		
		head.next = tail;
		tail.prev = head;
	}
	
	private void insertNode(ProductNode node)
	{
		ProductNode nextNode = head.next;
		
		node.next = nextNode;
		node.prev = head;
		head.next = node;
		nextNode.prev = node;
	}
	
	private void removeNode(ProductNode node)
	{
		ProductNode prevNode = node.prev;
		ProductNode nextNode = node.next;
		
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}
	
	public Product getProduct(Long productId)
	{
		if(!cacheMap.containsKey(productId))
			return null;
		
		ProductNode node = cacheMap.get(productId);
		
		removeNode(node);
		
		insertNode(node);
		
		return node.product;
	}
	
	public void putProduct(Long productId,
			               Product product)
	{
		if (cacheMap.containsKey(productId))
		{
			ProductNode existNode = cacheMap.get(productId);
			removeNode(existNode);
		}
		
		ProductNode newNode = new ProductNode(productId,product);
		
		insertNode(newNode);
		
		cacheMap.put(productId, newNode);
		
		if (cacheMap.size() > capacity)
		{
			ProductNode lruNode = tail.prev;
			
			removeNode(lruNode);
			
			cacheMap.remove(lruNode.ProductId);
		}
	}
	
	public void removeProduct(Long productId)
	{
		if(!cacheMap.containsKey(productId))
			return;
	
		ProductNode node = cacheMap.get(productId);
		
		removeNode(node);
		
		cacheMap.remove(productId);
	}
	
	public void displayCache()
	{
		ProductNode current = head.next;
		
		System.out.println();
		
		System.out.println("Current Product Cache: ");
		
		while(current != tail)
		{
			System.out.println(current.product.getName() + " <-> ");
				
			current = current.next;
		}
		
		System.out.println("TAIL");
	}
}
