package com.wchs.model;



/**
 * Created by Islam on 3/21/2016.
 */

public class Category  {
    private Integer id;
    private String name;
    private Double capital;
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		Category secondOne = (Category)obj;
		return (this.id == secondOne.getId());
	}



}
