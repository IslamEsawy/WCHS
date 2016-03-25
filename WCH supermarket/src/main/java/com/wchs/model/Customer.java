package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer cid;

    @Column(name = "name",unique = true)
    @Expose
    private String name;

    @Column(name = "totalCash")
    @Expose
    private double totalCash;

    @Column(name = "moneyToReturn")
    @Expose
    private double moneyToReturn;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Collection<Borrow> borrows = new ArrayList<Borrow>();

    @OneToMany(mappedBy = "cpid.customer", cascade = CascadeType.ALL)
    private Collection<Transaction> cProducts = new ArrayList<Transaction>();

    public Customer() {
    }

    public Customer(String name, double totalCash, double moneyToReturn) {
        this.name = name;
        this.totalCash = totalCash;
        this.moneyToReturn = moneyToReturn;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public double getMoneyToReturn() {
        return moneyToReturn;
    }

    public void setMoneyToReturn(double moneyToReturn) {
        this.moneyToReturn = moneyToReturn;
    }

    public Collection<Borrow> getBorrows() {
        return borrows;
    }


    public Collection<Transaction> getcProducts() {
        return cProducts;
    }

    public void setcProducts(Collection<Transaction> cProducts) {
        this.cProducts = cProducts;
    }
}
