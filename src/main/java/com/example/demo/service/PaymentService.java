package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {
	
	@Autowired
	private PaymentRepository repo;
	
	public List<Payment> listAll(Long keyword){
		if (keyword != null) {
    		return repo.findAll(keyword);
    	}
		return repo.findAll();
	}
	
	 public void save(Payment payment) {
        repo.save(payment);
    }
	     
    public Payment get(long pid) {
        return repo.findById(pid).get();
    }
	     
    public void delete(long pid) {
        repo.deleteById(pid);
    }

}
