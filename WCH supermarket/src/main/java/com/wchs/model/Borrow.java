package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Borrow")
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	private Integer bid;

	@Column
	@Expose
	private double amount;

	@Column
	@Expose
	private String signature;

	@Column
	@Expose
	private Date date;

	@ManyToOne
	@JoinColumn (name="customer_cid")
	@Expose
	private Customer customer;

	public Borrow(){

	}
	public Borrow(double amount, String signature, Date date, Customer customer) {
		this.amount = amount;
		this.signature = signature;
		this.date = date;
		this.customer = customer;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
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
	
	
	
	
}
