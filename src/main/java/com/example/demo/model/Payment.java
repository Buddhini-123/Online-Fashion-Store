package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "payment")
public class Payment {
	private Long pid;
	private String bank;
	private float amount;
	private String name;
	private Long cvv;
	private String date;
	private int month;
	private Long year;
	private Long account;
	
	public Payment() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pid")
	public Long getPid() {
		return pid;
	}

	public String getBank() {
		return bank;
	}

	public float getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}

	public Long getCvv() {
		return cvv;
	}

	public String getDate() {
		return date;
	}

	public int getMonth() {
		return month;
	}

	public Long getYear() {
		return year;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCvv(Long cvv) {
		this.cvv = cvv;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}
	
}
