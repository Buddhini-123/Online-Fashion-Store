package com.example.demo.service;

import java.awt.print.Pageable;

/*
import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;



@Service
public interface ProductService {
	List<Product> getAllProduct();
	void saveProduct(Product product);
	Product getProductById(long id);
	void deleteProductById(long id);
	Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
} */



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	//save new product
	public void saveImage(Product product) {
		productRepository.save(product);	
	}
	//return products
	public List<Product> getAllActiveImages(String keywordp) {
		if (keywordp != null) {
            return productRepository.search(keywordp);
        }
		return productRepository.findAll();
	}
	
	public List<Product> getAllActiveDisplay() {
		return productRepository.findAll();
	}

	public Optional<Product> getImageById(Long id) {
		return productRepository.findById(id);
	}
	//get by id
	public Product getProductById(long id) {
		return null;
	}
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
    }
	
	
}  

	
	
