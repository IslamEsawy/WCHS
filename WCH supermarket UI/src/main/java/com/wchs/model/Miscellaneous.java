package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Miscellaneous {
    private Integer mid;
    private Double price;
    private String signature;
    private Date date;
    private String name;

    public Miscellaneous() {
    }

    public Miscellaneous(Double price, String signature, Date date, String name) {
        this.price = price;
        this.signature = signature;
        this.date = date;
        this.name = name;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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
