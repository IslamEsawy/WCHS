package com.wchs.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table (name="Product")
public class Product {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Expose
	private Integer pid;

	
	@Column(unique = true)
	@Expose
	private String barcode;
	
	@Column(unique = true)
	@Expose
	private String name;

	@Column
	@Expose
	private Date date;

	@Column
	@Expose
	private Integer numberofBoxes;

	@Column
	@Expose
	private Integer isSnack;

	@Column
	@Expose
	private Double boxPrice;



	@Column
	@Expose
	private Integer itemsPerBox;

	@Column
	@Expose
	private Integer totalItems;

	@Column
	@Expose
	private Integer totalAvailableItems;

	@Column
	@Expose
	private Double buyingPricePerItem;

	@Column
	@Expose
	private Double sellingPricePerItem;

	@Column
	@Expose
	private Double totalBuyingPrice;

	@Column
	@Expose
	private Double totalSellingPrice;

	@Column
	@Expose
	private Double netProfit;

	@ManyToOne
	@JoinColumn (name="category_id")
	@Expose
	private Category category;


	@OneToMany(mappedBy="cpid.product", cascade=CascadeType.ALL)
	private Collection<Transaction> cProducts = new ArrayList<Transaction>();


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
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public Collection<Transaction> getcProducts() {
		return cProducts;
	}

	public void setcProducts(Collection<Transaction> cProducts) {
		this.cProducts = cProducts;
	}
}
