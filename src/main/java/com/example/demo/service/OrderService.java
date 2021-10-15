package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ordering;
import com.example.demo.repository.OrderRepository;
 
@Service
@Transactional
public class OrderService {
	 @Autowired
	    private OrderRepository repo;
	     
	    public List<Ordering> listAll() {
	        return repo.findAll();
	    }
	     
	    public void save(Ordering ordering) {
	        repo.save(ordering);
	    }
	     
	    public Ordering get(long oid) {
	        return repo.findById(oid).get();
	    }
	     
	    public void delete(long oid) {
	        repo.deleteById(oid);
	    }
}
