package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table (name="transaction")
@AssociationOverrides({
    @AssociationOverride(name = "cpid.customer",
        joinColumns = @JoinColumn(name = "cid")),
    @AssociationOverride(name = "cpid.product",
        joinColumns = @JoinColumn(name = "pid")) })
public class Transaction {
	
	
	@EmbeddedId
	@Expose
	private TransactionId cpid = new TransactionId();

	@Column
	@Expose
	private Integer quantity;

	@Column
	@Expose
	private Date date;

	@Column
	@Expose
	private Double cash;

	@Column
	@Expose
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
