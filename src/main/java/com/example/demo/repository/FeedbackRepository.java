package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Feedback;

/* feedback repository*/
@Repository
public interface UserRepository extends JpaRepository<Feedback, Long> {
	@Query("SELECT u FROM Feedback u WHERE u.id LIKE ?1")
	public List<Feedback> findAll(Long keyword);
//data	
	
	//JRE file take a JPAREPOSITORY , its take to user entity  & primary key. Automatically generate

	
}



