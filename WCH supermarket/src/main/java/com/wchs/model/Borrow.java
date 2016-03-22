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
	private Double amount;

	@Column
	@Expose
	private String signature;

	@Column
	@Expose
	private Date date;

	@Column
	@Expose
	private String isPaid;

	@ManyToOne
	@JoinColumn (name="customer_cid")
	@Expose
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
