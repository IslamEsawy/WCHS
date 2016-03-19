package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.Date;


public class Income {
    private Integer iid;
    private Double price;
    private String signature;
    private Date date;
    private String name;

    public Income() {
    }

    public Income(Double price, String signature, Date date, String name) {
        this.price = price;
        this.signature = signature;
        this.date = date;
        this.name = name;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
