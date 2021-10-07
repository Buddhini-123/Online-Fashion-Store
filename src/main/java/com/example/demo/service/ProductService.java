package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public void saveImage(Product product) {
		productRepository.save(product);	
	}

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
	
	public Product getProductById(long id) {
		return null;
	}
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
    }
    
	
	
}

	
	
