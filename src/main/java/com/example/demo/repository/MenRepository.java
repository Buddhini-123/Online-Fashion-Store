package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Men;

@Repository
public interface MenRepository extends JpaRepository<Men, Long>{

}
