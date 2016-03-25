package com.wchs.model;

import java.util.Date;

public class Borrow {
	private Integer bid;
	private Double amount;
	private String signature;
	private Date date;
	private String isPaid;
	private Customer customer;

	public Borrow(){

	}
	public Borrow(Double amount, String signature, Date date, String isPaid, Customer customer) {
		this.amount = amount;
		this.signature = signature;
		this.date = date;
		this.customer = customer;
		this.isPaid = isPaid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	
	
}
