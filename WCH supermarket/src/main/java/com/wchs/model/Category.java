package com.wchs.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Islam on 3/21/2016.
 */

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column(unique = true)
    @Expose
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Collection<Product> products = new ArrayList<>();

    @Column
    @Expose
    private Double capital;

    @Column
    @Expose
    private Double profit;


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Collection<Product> getProducts() {
        return products;
    }


    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

}
