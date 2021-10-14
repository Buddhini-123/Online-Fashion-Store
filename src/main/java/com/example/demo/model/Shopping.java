package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shopping {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    private String details;
    private String price;
    private String itemname;
	public Shopping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shopping(String id , String details, String price, String itemname) {
		super();
		this.id = id;
		this.details = details;
		this.price = price;
		this.itemname = itemname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
 

}
