package com.wchs.model;

import java.util.ArrayList;
import java.util.Collection;


public class Customer {
    private Integer cid;

    private String name;

    private double totalCash;

    private double moneyToReturn;


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



}
