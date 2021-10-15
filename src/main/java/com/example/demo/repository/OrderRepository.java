package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ordering;

public interface OrderRepository extends JpaRepository<Ordering, Long> {

	@Query("SELECT p FROM Ordering p WHERE p.tel LIKE %?1%")
	public List<Ordering> findall(String keyword);
}
