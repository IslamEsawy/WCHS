package com.wchs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wchs.model.Borrow;
import com.wchs.model.Category;
import com.wchs.model.Customer;
import com.wchs.model.Inventory;
import com.wchs.model.Miscellaneous;
import com.wchs.model.Product;
import com.wchs.model.Transaction;
import com.wchs.util.BackEndResponse;
import com.wchs.util.ResultStatus;
import com.wchs.util.URLs;

public class Logic {

	private List<Customer> customers;
	private List<Product> products;
	private List<Miscellaneous> miscellaneous;
	private List<Borrow> borrows;
	private List<Category> categories;
	private List<Inventory> inventories;

	public void loadData() {
		customers = loadCustomers();
		products = loadProducts();
		borrows = loadBorrows();
		miscellaneous = loadMiscellaneouss();
		categories = loadCategories();
		inventories = loadInventories();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Miscellaneous> getMiscellaneous() {
		return miscellaneous;
	}

	public void setMiscellaneous(List<Miscellaneous> miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public List<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<Borrow> borrows) {
		this.borrows = borrows;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Category getCategory(String name) {
		Category selectedCategory = null;
		for (Category category : categories) {
			if (category.getName().equals(name)) {
				selectedCategory = category;
				break;
			}
		}
		return selectedCategory;
	}

	public List<Product> getCategoryProducts(Category category) {
		List<Product> categoryProducts = new ArrayList<Product>();
		for (Product product : products) {
			if (product.getCategory().getId() == category.getId()) {
				categoryProducts.add(product);
			}
		}
		return categoryProducts;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public List<Customer> loadCustomers() {

		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listCustomer);

		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			System.out.println(backEndResponse.getObject());
			Gson gsonResponse = new Gson();
			return gsonResponse.fromJson(
					backEndResponse.getObject().toString(),
					new TypeToken<List<Customer>>() {
					}.getType());
		} else
			return null;
	}

	public Customer addCustomer(String name, Double totalCash,
			Double moneyToReturn) {
		Customer cus = new Customer(name, totalCash, moneyToReturn);

		Gson gson = new Gson();
		String message = gson.toJson(cus);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.saveCustomer);
		return gson.fromJson(backEndResponse.getObject().toString(),
				Customer.class);
	}

	public Product addProduct(String barcode, String name, long date,
			Integer numberofBoxes, Double boxPrice, Integer itemsPerBox,
			Integer totalItems, Integer totalAvailableItems,
			Double buyingPricePerItem, Double sellingPricePerItem,
			Double totalBuyingPrice, Double totalSellingPrice,
			Double netProfit, Category category) {
		Product pro = new Product(barcode, name, new Date(date), 0,
				numberofBoxes, boxPrice, itemsPerBox, totalItems,
				totalAvailableItems, buyingPricePerItem, sellingPricePerItem,
				totalBuyingPrice, totalSellingPrice, netProfit, category);

		Gson gson = new Gson();
		String message = gson.toJson(pro);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.saveProduct);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);
		return gson.fromJson(json, Product.class);
	}

	public Product updateProduct(Integer id, String barcode, String name,
			long date, Integer numberofBoxes, Double boxPrice,
			Integer itemsPerBox, Integer totalItems,
			Integer totalAvailableItems, Double buyingPricePerItem,
			Double sellingPricePerItem, Double totalBuyingPrice,
			Double totalSellingPrice, Double netProfit, Category category) {
		Product pro = new Product(barcode, name, new Date(date), 0,
				numberofBoxes, boxPrice, itemsPerBox, totalItems,
				totalAvailableItems, buyingPricePerItem, sellingPricePerItem,
				totalBuyingPrice, totalSellingPrice, netProfit, category);
		pro.setPid(id);
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(pro);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.updateProduct);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);
		return gson.fromJson(json, Product.class);
	}

	public List<Transaction> loadTransactions(Customer customer) {

		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listTransactionCustomer + "/" + customer.getCid());

		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, new TypeToken<List<Transaction>>() {
			}.getType());
		} else
			return null;
	}

	public List<Product> loadProducts() {
		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listProducts);
		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, new TypeToken<List<Product>>() {
			}.getType());
		} else
			return null;
	}

	public ResultStatus deleteProduct(String[] id) {
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(id);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.deleteProduct);
		return backEndResponse.getResultStatus();
	}

	public List<Borrow> loadBorrows() {
		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listBorrows);
		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, new TypeToken<List<Borrow>>() {
			}.getType());
		} else
			return null;
	}

	public Borrow addBorrow(Double amount, String signature, Date date,
			Customer customer) {
		Borrow pro = new Borrow(amount, signature, date, "NO", customer);

		Gson gson = new Gson();
		String message = gson.toJson(pro);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.saveBorrow);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);
		return gson.fromJson(json, Borrow.class);
	}

	public ResultStatus makeBorrowPaid(String[] id) {
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(id);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.makePaidBorrow);
		return backEndResponse.getResultStatus();
	}

	public List<Miscellaneous> loadMiscellaneouss() {
		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listMiscs);
		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, new TypeToken<List<Miscellaneous>>() {
			}.getType());
		} else
			return null;
	}

	public ResultStatus deleteMiscs(String[] id) {
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(id);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.deleteMisc);
		return backEndResponse.getResultStatus();
	}

	public Miscellaneous addMisc(String name, double parseDouble,
			String signature) {
		Miscellaneous pro = new Miscellaneous(parseDouble, signature,
				new Date(), name);

		Gson gson = new Gson();
		String message = gson.toJson(pro);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.saveMisc);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);
		return gson.fromJson(json, Miscellaneous.class);
	}

	public List<Category> loadCategories() {
		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listCategories);
		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, new TypeToken<List<Category>>() {
			}.getType());
		} else
			return null;
	}

	public ResultStatus deleteCategories(List<Category> categories) {
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(categories);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.deleteCategories);
		System.out.println(backEndResponse.getResultStatus());
		return backEndResponse.getResultStatus();
	}

	public Category addCategory(String name) {
		Category pro = new Category(name);

		Gson gson = new Gson();
		String message = gson.toJson(pro);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.saveCategory);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);
		return gson.fromJson(json, Category.class);
	}

	public Inventory addInventory(Inventory pro) {
		Gson gson = new Gson();
		String message = gson.toJson(pro);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.saveInventory);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);
		return gson.fromJson(json, Inventory.class);
	}

	public Inventory doInventory() {
		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.doInventory);
		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, Inventory.class);
		} else
			return null;
	}

	public List<Inventory> loadInventories() {
		BackEndResponse backEndResponse = BackEndService.getInstance().sendGet(
				URLs.listInventories);
		if (ResultStatus.SUCCESS.equals(backEndResponse.getResultStatus())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(backEndResponse.getObject());
			System.out.println(json);
			return gson.fromJson(json, new TypeToken<List<Inventory>>() {
			}.getType());
		} else
			return null;
	}

	public void makeTransaction(List<Transaction> transactions, Double mTR,
			Double cash) {
		transactions.remove(transactions.size() - 1);
		transactions.get(0).getCpid().getCustomer().addToMTR(mTR);
		transactions.get(0).getCpid().getCustomer().addToCash(cash);
		for (int i = 0; i < transactions.size(); i++) {
			transactions.get(i).getCpid().getProduct()
					.subtractItems(transactions.get(i).getQuantity());
		}
		Gson gson = new Gson();
		String message = gson.toJson(transactions);
		System.out.println(message);
		BackEndResponse backEndResponse = BackEndService.getInstance()
				.sendPost(message, URLs.makeTransaction);
		String json = gson.toJson(backEndResponse.getObject());
		System.out.println(json);

	}

	public void addBorrowToList(Borrow b) {
		borrows.add(b);
	}

	public void addInventoryToList(Inventory inventory) {
		inventories.add(inventory);
	}

	public void addProductToList(Product newPro) {
		products.add(newPro);
	}

	public void updateProductToList(Product newPro) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getPid() == newPro.getPid()) {
				products.set(i, newPro);
				return;
			}
		}
	}

	public void deleteCategoryProducts(List<Category> selectedCat) {
		for (int i = 0; i < selectedCat.size(); i++) {
			for (int j = 0; j < products.size(); j++) {
				if (products.get(j).getCategory().getId() == selectedCat.get(i)
						.getId()) {
					products.remove(j);
					j--;
				}
			}
		}
	}

	public Product getProductWithBarcode(String barcode) {
		for (Product product : products) {
			if (product.getBarcode().equals(barcode))
				return product;
		}
		return null;
	}

	public Double getTotalBorrows() {
		Double sum = 0.0;
		for (Borrow borrow : borrows) {
			if (borrow.getIsPaid().equals("NO"))
				sum += borrow.getAmount();
		}
		return sum;
	}

	public Double getTotalMisc() {
		Double sum = 0.0;
		for (Miscellaneous miscellaneous : miscellaneous) {
			sum += miscellaneous.getPrice();
		}
		return sum;
	}

}
