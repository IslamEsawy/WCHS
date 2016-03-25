package com.wchs.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.wchs.model.Borrow;
import com.wchs.model.Category;
import com.wchs.model.Customer;
import com.wchs.model.Inventory;
import com.wchs.model.Miscellaneous;
import com.wchs.model.Product;
import com.wchs.model.Transaction;
import com.wchs.ui.panel.TransactionPanel;

@SuppressWarnings("serial")
public class MyTable<T> extends AbstractTableModel {
	private String[] columnNames;
	private List<T> data;
	private TransactionPanel tPanel;

	public MyTable(TransactionPanel tPanel) {
		data = new ArrayList<T>();
		this.tPanel = tPanel;
	}

	List<Color> rowColours = Arrays.asList(Color.RED, Color.GREEN, Color.CYAN);

	public void setRowColour(int row, Color c) {
		rowColours.set(1, c);
		fireTableRowsUpdated(row, row);
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public void setData(List<T> p) {
		data = new ArrayList<T>(p);
		System.out.println(data.size());
	}

	public void setColumnNames(String[] names) {
		columnNames = names;
	}

	public T getObject(String i) {
		if (data.size() > 0 && Customer.class.isInstance(data.get(0))) {
			for (T object : data) {
				if (String.valueOf(((Customer) object).getCid()).equals(i)) {
					return object;
				}
			}
		} else if (data.size() > 0 && Product.class.isInstance(data.get(0))) {
			for (T object : data) {
				if (String.valueOf(((Product) object).getBarcode()).equals(i)) {
					return object;
				}
			}
		} else if (data.size() > 0 && Borrow.class.isInstance(data.get(0))) {
			for (T object : data) {
				if (String.valueOf(((Borrow) object).getBid()).equals(i)) {
					return object;
				}
			}
		}
		return null;
	}

	public List<T> getData() {
		return data;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public String getValueAt(int row, int col) {
		String result = null;
		if (Customer.class.isInstance(data.get(row))) {
			if (col == 0)
				result = String.valueOf(((Customer) data.get(row)).getCid());
			if (col == 1)
				result = ((Customer) data.get(row)).getName();
			if (col == 2)
				result = String.valueOf(((Customer) data.get(row))
						.getTotalCash());
			if (col == 3)
				result = String.valueOf(((Customer) data.get(row))
						.getMoneyToReturn());
		} else if (Inventory.class.isInstance(data.get(row))) {
			if (col == 0)
				result = ((Inventory) data.get(row)).getMonth();
			if (col == 1)
				result = String.valueOf(((Inventory) data.get(row))
						.getTotalCategoriesCapital());
			if (col == 2)
				result = String.valueOf(((Inventory) data.get(row))
						.getTotalCategoriesProfit());
			if (col == 3)
				result = String.valueOf(((Inventory) data.get(row))
						.getRestOfGoodsCapital());
			if (col == 4)
				result = String.valueOf(((Inventory) data.get(row))
						.getRestOfGoodsProfit());
			if (col == 5)
				result = String.valueOf(((Inventory) data.get(row))
						.getTotalBorrow());
			if (col == 6)
				result = String.valueOf(((Inventory) data.get(row))
						.getTotalMisc());
		} else if (Transaction.class.isInstance(data.get(row))) {
			if (((Transaction) data.get(row)).getQuantity() != null) {
				if (col == 0)
					result = ((Transaction) data.get(row)).getCpid()
							.getProduct().getBarcode();
				if (col == 1)
					result = ((Transaction) data.get(row)).getCpid()
							.getProduct().getName();
				if (col == 2)
					result = String.valueOf(((Transaction) data.get(row))
							.getDate());
				if (col == 3)
					result = String.valueOf(((Transaction) data.get(row))
							.getQuantity());
				if (col == 4)
					result = String.valueOf(((Transaction) data.get(row))
							.getCash());
				if (col == 5)
					result = String.valueOf(((Transaction) data.get(row))
							.getMoneyToReturn());
			}
		} else if (Borrow.class.isInstance(data.get(row))) {
			if (col == 0)
				result = String.valueOf(((Borrow) data.get(row)).getBid());
			if (col == 1)
				result = ((Borrow) data.get(row)).getCustomer().getName();
			if (col == 2)
				result = String.valueOf(((Borrow) data.get(row)).getAmount());
			if (col == 3)
				result = String.valueOf(((Borrow) data.get(row)).getDate());
			if (col == 4)
				result = ((Borrow) data.get(row)).getIsPaid();
			if (col == 5)
				result = ((Borrow) data.get(row)).getSignature();
		} else if (Miscellaneous.class.isInstance(data.get(row))) {
			if (col == 0)
				result = String.valueOf(((Miscellaneous) data.get(row))
						.getMid());
			if (col == 1)
				result = ((Miscellaneous) data.get(row)).getName();
			if (col == 2)
				result = String.valueOf(((Miscellaneous) data.get(row))
						.getPrice());
			if (col == 3)
				result = String.valueOf(((Miscellaneous) data.get(row))
						.getDate());
			if (col == 4)
				result = ((Miscellaneous) data.get(row)).getSignature();
		} else if (Category.class.isInstance(data.get(row))) {
			if (col == 0)
				result = ((Category) data.get(row)).getName();
			if (col == 1)
				result = String
						.valueOf(((Category) data.get(row)).getCapital());
			if (col == 2)
				result = String.valueOf(((Category) data.get(row)).getProfit());
		} else if (Product.class.isInstance(data.get(row))) {
			if (col == 0)
				result = ((Product) data.get(row)).getBarcode();
			if (col == 1)
				result = ((Product) data.get(row)).getName();
			if (col == 2)
				result = String.valueOf(((Product) data.get(row)).getDate());
			if (col == 3)
				result = String.valueOf(((Product) data.get(row))
						.getNumberofBoxes());
			if (col == 4)
				result = String
						.valueOf(((Product) data.get(row)).getBoxPrice());

			if (col == 5)
				result = String.valueOf(((Product) data.get(row))
						.getItemsPerBox());
			if (col == 6)
				result = String.valueOf(((Product) data.get(row))
						.getTotalItems());
			if (col == 7)
				result = String.valueOf(((Product) data.get(row))
						.getNumberofBoxes());
			if (col == 8)
				result = String.valueOf(((Product) data.get(row))
						.getTotalAvailableItems());

			if (col == 9)
				result = String.valueOf(((Product) data.get(row))
						.getBuyingPricePerItem());
			if (col == 10)
				result = String.valueOf(((Product) data.get(row))
						.getSellingPricePerItem());
			if (col == 11)
				result = String.valueOf(((Product) data.get(row))
						.getTotalBuyingPrice());
			if (col == 12)
				result = String.valueOf(((Product) data.get(row))
						.getTotalSellingPrice());
			if (col == 13)
				result = String.valueOf(((Product) data.get(row))
						.getNetProfit());
		}
		return result;
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		return true;
	}

	public void addRow(T pro) {
		data.add(pro);
	}

	public void remove(int selectedRow) {
		data.remove(selectedRow);
	}

	public void deleteRow(String id) {
		if (data.get(0) instanceof Customer) {
			for (int i = 0; i < data.size(); i++) {
				if (String.valueOf((((Customer) data.get(i)).getCid())).equals(
						id)) {
					data.remove(i);
				}
			}
		}
		if (data.get(0) instanceof Product) {
			for (int i = 0; i < data.size(); i++) {
				if (id.equals(((Product) data.get(i)).getBarcode())) {
					data.remove(i);
				}
			}
		}
		if (data.get(0) instanceof Miscellaneous) {
			for (int i = 0; i < data.size(); i++) {
				if (String.valueOf((((Miscellaneous) data.get(i)).getMid()))
						.equals(id)) {
					data.remove(i);
					return;
				}
			}
		}
	}

	public void updateRow(String barcode, T t) {
		if (data.get(0) instanceof Product) {
			for (int i = 0; i < data.size(); i++) {
				if (barcode.equals(((Product) data.get(i)).getBarcode())) {
					data.set(i, t);
				}
			}
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (data.get(0) instanceof Transaction && tPanel != null) {
			if (columnIndex == 0) {
				String barcode = aValue.toString();
				List<Product> products = MainWindow.logic.getProducts();
				for (Product product : products) {
					if (product.getBarcode().equals(barcode)) {
						tPanel.createTransaction(product);
						break;
					}
				}
			}
			if (columnIndex == 3) {
				Double newPrice = ((Transaction) data.get(rowIndex)).getCpid()
						.getProduct().getSellingPricePerItem()
						* Double.parseDouble(aValue.toString());
				((Transaction) data.get(rowIndex)).setQuantity(Integer
						.parseInt(aValue.toString()));
				((Transaction) data.get(rowIndex)).setMoneyToReturn(newPrice);
				((Transaction) data.get(rowIndex)).setCash(0.0);
			}
			if (columnIndex == 4) {
				Double newPrice = ((Transaction) data.get(rowIndex)).getCpid()
						.getProduct().getSellingPricePerItem()
						* ((Transaction) data.get(rowIndex)).getQuantity();
				if (Double.parseDouble(aValue.toString()) <= newPrice) {
					Double mTR = newPrice
							- Double.parseDouble(aValue.toString());
					((Transaction) data.get(rowIndex)).setMoneyToReturn(mTR);
					((Transaction) data.get(rowIndex)).setCash(Double
							.parseDouble(aValue.toString()));
				}
			}
			if (columnIndex == 5) {
				Double newPrice = ((Transaction) data.get(rowIndex)).getCpid()
						.getProduct().getSellingPricePerItem()
						* ((Transaction) data.get(rowIndex)).getQuantity();
				if (Double.parseDouble(aValue.toString()) <= newPrice) {
					Double cash = newPrice
							- Double.parseDouble(aValue.toString());
					((Transaction) data.get(rowIndex)).setMoneyToReturn(Double
							.parseDouble(aValue.toString()));
					((Transaction) data.get(rowIndex)).setCash(cash);
				}
			}

			fireTableDataChanged();
			tPanel.updateFields();
		}
	}

	public void deleteRowIndex(int convertRowIndexToModel) {
		if (data.get(0) instanceof Transaction) {
			data.remove(convertRowIndexToModel);

		}
	}

	public void setTransaction(T s) {
		data.set(data.size() - 1, s);
	}

}
