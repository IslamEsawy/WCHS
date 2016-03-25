package com.wchs.model;

import java.util.Date;

public class Product {
	private Integer pid;
	private String barcode;
	private String name;
	private Date date;
	private Integer numberofBoxes;
	private Integer isSnack;
	private Double boxPrice;
	private Integer itemsPerBox;
	private Integer totalItems;
	private Integer totalAvailableItems;
	private Double buyingPricePerItem;
	private Double sellingPricePerItem;
	private Double totalBuyingPrice;
	private Double totalSellingPrice;
	private Double netProfit;
	private Category category;



	public Product(){}

	public Product(String barcode, String name, Date date, Integer isSnack, Integer numberofBoxes,
				   Double boxPrice, Integer itemsPerBox,
				   Integer totalItems, Integer totalAvailableItems, Double buyingPricePerItem,
				   Double sellingPricePerItem, Double totalBuyingPrice,
				   Double totalSellingPrice, Double netProfit, Category category) {
		this.barcode = barcode;
		this.name = name;
		this.date = date;
		this.isSnack = isSnack;
		this.numberofBoxes = numberofBoxes;
		this.boxPrice = boxPrice;
		this.itemsPerBox = itemsPerBox;
		this.totalItems = totalItems;
		this.totalAvailableItems = totalAvailableItems;
		this.buyingPricePerItem = buyingPricePerItem;
		this.sellingPricePerItem = sellingPricePerItem;
		this.totalBuyingPrice = totalBuyingPrice;
		this.totalSellingPrice = totalSellingPrice;
		this.netProfit = netProfit;
		this.category = category;
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

	public Double getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(Double boxPrice) {
		this.boxPrice = boxPrice;
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


	public Integer getNumberofBoxes() {
		return numberofBoxes;
	}

	public void setNumberofBoxes(Integer numberofBoxes) {
		this.numberofBoxes = numberofBoxes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
    public void subtractItems(Integer items){
    	this.totalAvailableItems -= items;
    }

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
