package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Product {
	private Integer pid;
	private String name;
	private Date date;
	private Integer isSnack;
	private Integer numberofBoxes;
	private Double boxPrice;
	private Double totalBoxesPrice;
	private Integer itemsPerBox;
	private Integer totalItems;
	private Integer totalAvailableItems;
	private Double buyingPricePerItem;
	private Double sellingPricePerItem;
	private Double totalBuyingPrice;
	private Double totalSellingPrice;
	private Double netProfit;

	private Collection<Transaction> cProducts = new ArrayList<Transaction>();

	public Product(){}

	public Product(String name, Date date, Integer isSnack, Integer numberofBoxes,
			Double boxPrice, Double totalBoxesPrice, Integer itemsPerBox,
			Integer totalItems, Integer totalAvailableItems, Double buyingPricePerItem,
			Double sellingPricePerItem, Double totalBuyingPrice,
			Double totalSellingPrice, Double netProfit,
			Collection<Transaction> cProducts) {
		this.name = name;
		this.date = date;
		this.isSnack = isSnack;
		this.numberofBoxes = numberofBoxes;
		this.boxPrice = boxPrice;
		this.totalBoxesPrice = totalBoxesPrice;
		this.itemsPerBox = itemsPerBox;
		this.totalItems = totalItems;
		this.totalAvailableItems = totalAvailableItems;
		this.buyingPricePerItem = buyingPricePerItem;
		this.sellingPricePerItem = sellingPricePerItem;
		this.totalBuyingPrice = totalBuyingPrice;
		this.totalSellingPrice = totalSellingPrice;
		this.netProfit = netProfit;
		this.cProducts = cProducts;
	}

	public Integer getTotalAvailableItems() {
		return totalAvailableItems;
	}

	public void setTotalAvailableItems(Integer totalAvailableItems) {
		this.totalAvailableItems = totalAvailableItems;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getIsSnack() {
		return isSnack;
	}

	public void setIsSnack(Integer isSnack) {
		this.isSnack = isSnack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberofBoxes() {
		return numberofBoxes;
	}

	public void setNumberofBoxes(Integer numberofBoxes) {
		this.numberofBoxes = numberofBoxes;
	}

	public Double getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(Double boxPrice) {
		this.boxPrice = boxPrice;
	}

	public Double getTotalBoxesPrice() {
		return totalBoxesPrice;
	}

	public void setTotalBoxesPrice(Double totalBoxesPrice) {
		this.totalBoxesPrice = totalBoxesPrice;
	}

	public Integer getItemsPerBox() {
		return itemsPerBox;
	}

	public void setItemsPerBox(Integer itemsPerBox) {
		this.itemsPerBox = itemsPerBox;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public Double getBuyingPricePerItem() {
		return buyingPricePerItem;
	}

	public void setBuyingPricePerItem(Double buyingPricePerItem) {
		this.buyingPricePerItem = buyingPricePerItem;
	}

	public Double getSellingPricePerItem() {
		return sellingPricePerItem;
	}

	public void setSellingPricePerItem(Double sellingPricePerItem) {
		this.sellingPricePerItem = sellingPricePerItem;
	}

	public Double getTotalBuyingPrice() {
		return totalBuyingPrice;
	}

	public void setTotalBuyingPrice(Double totalBuyingPrice) {
		this.totalBuyingPrice = totalBuyingPrice;
	}

	public Double getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(Double totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public Double getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(Double netProfit) {
		this.netProfit = netProfit;
	}

	public Collection<Transaction> getcProducts() {
		return cProducts;
	}

	public void setcProducts(Collection<Transaction> cProducts) {
		this.cProducts = cProducts;
	}
	
	
}
