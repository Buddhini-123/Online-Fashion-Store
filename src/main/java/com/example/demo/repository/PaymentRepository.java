package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Payment;



public interface PaymentRepository extends JpaRepository<Payment, Long>{
	@Query("SELECT p FROM Payment p WHERE p.pid LIKE ?1")
	public List<Payment> findAll(Long keyword);
	
	@Query("SELECT p FROM Payment p WHERE p.date BETWEEN ?1 AND ?2")
	public List<Payment> findbydate(Date startdate,Date enddate);
	
	//@Query("SELECT SUM(amount) FROM Payment p WHERE p.date BETWEEN ?1 AND ?2")
	//public Float calculate(Date startdate,Date enddate);
}
