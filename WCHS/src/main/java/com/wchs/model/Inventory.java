package com.wchs.model;


public class Inventory {
	private Integer id;
	private String month;
	private Double restOfGoodsCapital;
	private Double restOfGoodsProfit;
	private Double totalBorrow;
	private Double totalMisc;
	private Double totalCategoriesCapital;
	private Double totalCategoriesProfit;

	public Inventory(String month, Double restOfGoodsCapital, Double restOfGoodsProfit, Double totalBorrow, Double totalMisc, Double totalCategoriesCapital, Double totalCategoriesProfit) {
		this.month = month;
		this.restOfGoodsCapital = restOfGoodsCapital;
		this.restOfGoodsProfit = restOfGoodsProfit;
		this.totalBorrow = totalBorrow;
		this.totalMisc = totalMisc;
		this.totalCategoriesCapital = totalCategoriesCapital;
		this.totalCategoriesProfit = totalCategoriesProfit;
	}

	public Inventory() {
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getRestOfGoodsCapital() {
		return restOfGoodsCapital;
	}

	public void setRestOfGoodsCapital(Double restOfGoodsCapital) {
		this.restOfGoodsCapital = restOfGoodsCapital;
	}

	public Double getRestOfGoodsProfit() {
		return restOfGoodsProfit;
	}

	public void setRestOfGoodsProfit(Double restOfGoodsProfit) {
		this.restOfGoodsProfit = restOfGoodsProfit;
	}

	public Double getTotalBorrow() {
		return totalBorrow;
	}

	public void setTotalBorrow(Double totalBorrow) {
		this.totalBorrow = totalBorrow;
	}

	public Double getTotalMisc() {
		return totalMisc;
	}

	public void setTotalMisc(Double totalMisc) {
		this.totalMisc = totalMisc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalCategoriesCapital() {
		return totalCategoriesCapital;
	}

	public void setTotalCategoriesCapital(Double totalCategoriesCapital) {
		this.totalCategoriesCapital = totalCategoriesCapital;
	}

	public Double getTotalCategoriesProfit() {
		return totalCategoriesProfit;
	}

	public void setTotalCategoriesProfit(Double totalCategoriesProfit) {
		this.totalCategoriesProfit = totalCategoriesProfit;
	}

}
