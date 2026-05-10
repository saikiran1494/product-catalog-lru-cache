package com.example.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productcatalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
