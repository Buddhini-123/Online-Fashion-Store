package com.example.demo.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Feedback;



@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	//query sql search function get id & display id & feedback
	@Query("SELECT u FROM Feedback u WHERE u.id LIKE ?1")
	public List<Feedback> findAll(Long keyword);
	
	
	//JRE file take a JPA REPOSITORY , its take to user entity  & primary key. Automatically generate

	
}
