package com.example.demo.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;


	

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository Repo;
	
	//returns the user table as a list
	public List<Feedback>listAll(Long keyword){
		//search id null
		if (keyword != null) {
			return Repo.findAll(keyword);
    	
		}
		return Repo.findAll();
	}
	//save feeback 
	public void save(Feedback us) {
		Repo.save(us);
	}
	
	//returns the primary key of the record to the relevant id
	public Feedback get(int id) {
		return Repo.findById((long) id).get();
	}
	//delete own ids feedback
	public void delete(int id) {
		Repo.deleteById((long) id);
	}
	
	
}



