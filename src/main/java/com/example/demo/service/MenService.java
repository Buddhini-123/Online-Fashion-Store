package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Men;
import com.example.demo.repository.MenRepository;

@Service
public class MenService {

	@Autowired
	private MenRepository menRepository;
	
	
	public void saveImage(Men men) {
		menRepository.save(men);	
	}

	public List<Men> getAllActiveImages() {
		return menRepository.findAll();
	}
	
	public List<Men> getAllActiveDisplay() {
		return menRepository.findAll();
	}

	public Optional<Men> getImageById(Long id) {
		return menRepository.findById(id);
	}
	
	public Men getProductById(long id) {
		return null;
	}
	public void deleteProduct(long id) {
		menRepository.deleteById(id);
    }
	public void saveMen(Men men) {
		menRepository.save(men);	
	}
	
}
