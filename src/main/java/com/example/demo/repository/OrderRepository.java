package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Ordering;

public interface OrderRepository extends JpaRepository<Ordering, Long> {

}
