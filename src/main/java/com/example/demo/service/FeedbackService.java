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
	
	public List<Feedback>listAll(Long keyword){
		if (keyword != null) {
			return Repo.findAll(keyword);
    	
		}
		return Repo.findAll();
	}
	
	public void save(Feedback us) {
		Repo.save(us);
	}
	
	public Feedback get(int id) {
		return Repo.findById((long) id).get();
	}
	public void delete(int id) {
		Repo.deleteById((long) id);
	}
	
	
	         
	       
	    }



