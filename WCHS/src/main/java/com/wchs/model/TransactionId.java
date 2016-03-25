package com.wchs.model;



@SuppressWarnings("serial")
public class TransactionId implements java.io.Serializable{
	private Customer customer;
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
