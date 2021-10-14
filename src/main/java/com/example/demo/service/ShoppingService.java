package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Shopping;
import com.example.demo.repository.ShoppingRepository;



@Service
public class ShoppingService {

	@Autowired
    private ShoppingRepository repo;
	
	public List<Shopping> listAll() {
        return repo.findAll();
    }
     
    public void save(Shopping std) {
        repo.save(std);
    }
     
    public Shopping get(String id) {
        return repo.findById(id).get();
    }
     
    public void delete(String id) {
        repo.deleteById(id);
    }
	
}
