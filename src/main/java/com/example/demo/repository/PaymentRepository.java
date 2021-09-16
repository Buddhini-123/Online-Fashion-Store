package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Payment;



public interface PaymentRepository extends JpaRepository<Payment, Long>{
	@Query("SELECT p FROM Payment p WHERE p.pid LIKE ?1")
	public List<Payment> findAll(Long keyword);
}
