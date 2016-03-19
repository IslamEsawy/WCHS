package com.wchs.model;

import com.google.gson.annotations.Expose;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class TransactionId implements java.io.Serializable{

	@ManyToOne
	@Expose
	private Customer customer;

	@ManyToOne
	@Expose
	private Product product;
	
	public TransactionId(){}
	public TransactionId(Customer customer, Product product) {
		this.customer = customer;
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
