
package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {
	
//feedback main java file
	@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    @Column(name="u_id")
	    private Long id;
		
	    private String feedback;

	    private String helpfull;
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public User(Long id, String feedback, String helpfull) {
			super();
			this.id = id;
			this.feedback = feedback;
			this.helpfull = helpfull;
		}

}
