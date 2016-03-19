package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Transaction {
	private TransactionId cpid = new TransactionId();
	private Integer quantity;
	private Date date;
	private Double cash;
	private Double moneyToReturn;

	public Transaction(){}
	public Transaction(TransactionId cpid, Integer quantity, Date date,
					   Double cash, Double moneyToReturn) {
		this.cpid = cpid;
		this.quantity = quantity;
		this.date = date;
		this.cash = cash;
		this.moneyToReturn = moneyToReturn;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TransactionId getCpid() {
		return cpid;
	}
	public void setCpid(TransactionId cpid) {
		this.cpid = cpid;
	}

	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getMoneyToReturn() {
		return moneyToReturn;
	}
	public void setMoneyToReturn(Double moneyToReturn) {
		this.moneyToReturn = moneyToReturn;
	}
	
	
}
