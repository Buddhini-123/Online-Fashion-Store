package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
			+ " OR p.description LIKE %?1%"
			+ " OR p.image LIKE %?1%"
			+ " OR CONCAT(p.price, '') LIKE %?1%")
	public List<Product> search(String keywordp);
}

