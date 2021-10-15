package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ordering")
public class Ordering {
	private Long oid;
	private String zip;
	private String tel;
	private String address;
	private String special;
	
	public Ordering() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getOid() {
		return oid;
	}

	public String getZip() {
		return zip;
	}

	public String getTel() {
		return tel;
	}

	public String getAddress() {
		return address;
	}

	public String getSpecial() {
		return special;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setSpecial(String special) {
		this.special = special;
	}
	
	
}
