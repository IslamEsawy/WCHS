package com.wchs.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import com.wchs.model.Product;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.ui.frame.ProductProcedure;
import com.wchs.util.ResultStatus;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class ProductsPanel extends JPanel {
	private JTextField totalBuying;
	private JTextField totalSelling;
	private JTextField netProfit;
	private List<Product> products;
	private JTable table;
	private MyTable<Product> myTable;

	public MyTable<Product> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Product> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Create the panel.
	 */
	public ProductsPanel() {
		setLayout(new BorderLayout(0, 0));

		updateData();
		intializeUIComponents();
		setTextFields();
	}

	private void setTextFields() {
		Double totalBuyingD = getSumOfTotalBuyingPrice();
		Double totalSellingD = getSumOfTotalSellingPrice();
		totalBuying.setText(totalBuyingD + "");
		totalSelling.setText(totalSellingD + "");
		netProfit.setText((Double) (totalSellingD - totalBuyingD) + "");
	}

	private Double getSumOfTotalBuyingPrice() {
		Double sum = 0.0;
		for (Product pro : products) {
			sum += pro.getTotalBuyingPrice();
		}
		return sum;
	}

	private Double getSumOfTotalSellingPrice() {
		Double sum = 0.0;
		for (Product pro : products) {
			sum += pro.getTotalSellingPrice();
		}
		return sum;
	}

	private void intializeUIComponents() {

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		add(tablePanel, BorderLayout.CENTER);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		add(optionsPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_optionsPanel = new GridBagLayout();
		gbl_optionsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };
		optionsPanel.setLayout(gbl_optionsPanel);

		JButton addCustomerButton = new JButton("تعديل");
		addCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 1)
					ProductProcedure.main(
							null,
							myTable.getObject(table.getValueAt(
									table.getSelectedRow(), 0).toString()),
							ProductsPanel.this);
			}
		});

		JButton btnNewButton = new JButton("إضافة");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductProcedure.main(null, null, ProductsPanel.this);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		optionsPanel.add(btnNewButton, gbc_btnNewButton);

		totalBuying = new JTextField();
		totalBuying.setHorizontalAlignment(SwingConstants.CENTER);
		totalBuying.setEditable(false);
		GridBagConstraints gbc_moneyToReturnText = new GridBagConstraints();
		gbc_moneyToReturnText.insets = new Insets(0, 0, 5, 5);
		gbc_moneyToReturnText.fill = GridBagConstraints.VERTICAL;
		gbc_moneyToReturnText.gridx = 11;
		gbc_moneyToReturnText.gridy = 1;
		optionsPanel.add(totalBuying, gbc_moneyToReturnText);
		totalBuying.setColumns(10);

		JLabel totalBuyingLabel = new JLabel("أجمالي الشراء");
		GridBagConstraints gbc_totalBuyingLabel = new GridBagConstraints();
		gbc_totalBuyingLabel.insets = new Insets(0, 0, 5, 0);
		gbc_totalBuyingLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalBuyingLabel.weighty = 1;
		gbc_totalBuyingLabel.gridx = 12;
		gbc_totalBuyingLabel.gridy = 1;
		optionsPanel.add(totalBuyingLabel, gbc_totalBuyingLabel);
		GridBagConstraints gbc_addCustomerButton = new GridBagConstraints();
		gbc_addCustomerButton.insets = new Insets(0, 0, 5, 5);
		gbc_addCustomerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addCustomerButton.gridx = 1;
		gbc_addCustomerButton.gridy = 2;
		optionsPanel.add(addCustomerButton, gbc_addCustomerButton);

		totalSelling = new JTextField();
		totalSelling.setHorizontalAlignment(SwingConstants.CENTER);
		totalSelling.setEditable(false);
		totalSelling.setColumns(10);
		GridBagConstraints gbc_cashText = new GridBagConstraints();
		gbc_cashText.fill = GridBagConstraints.VERTICAL;
		gbc_cashText.insets = new Insets(0, 0, 5, 5);
		gbc_cashText.gridx = 11;
		gbc_cashText.gridy = 2;
		optionsPanel.add(totalSelling, gbc_cashText);

		JButton customerHistoryDetails = new JButton("حذف");
		customerHistoryDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					int respone = JOptionPane.showConfirmDialog(
							ProductsPanel.this, "هل أنت متأكد؟", "تأكيد",
							JOptionPane.YES_NO_OPTION);
					switch (respone) {
					case 0: {
						String[] barcode = new String[table.getSelectedRows().length];
						int g = 0;
						for (int i : table.getSelectedRows()) {
							barcode[g] = table.getValueAt(i, 0).toString();
							g++;
						}
						ResultStatus rs = MainWindow.logic
								.deleteProduct(barcode);
						if (ResultStatus.SUCCESS.equals(rs)) {
							for (int i = 0; i < barcode.length; i++)
								myTable.deleteRow(barcode[i]);
							MainWindow.logic.setProducts(myTable.getData());
							MainWindow.observer.update();
						}
						break;
					}
					case 1: {
					}
					}

				}
			}
		});

		JLabel totalSellingLabel = new JLabel("أجمالي البيع");
		GridBagConstraints gbc_totalSellingLabel = new GridBagConstraints();
		gbc_totalSellingLabel.insets = new Insets(0, 0, 5, 0);
		gbc_totalSellingLabel.fill = GridBagConstraints.VERTICAL;
		gbc_totalSellingLabel.weighty = 1;
		gbc_totalSellingLabel.gridx = 12;
		gbc_totalSellingLabel.gridy = 2;
		optionsPanel.add(totalSellingLabel, gbc_totalSellingLabel);
		GridBagConstraints gbc_customerHistoryDetails = new GridBagConstraints();
		gbc_customerHistoryDetails.insets = new Insets(0, 0, 0, 5);
		gbc_customerHistoryDetails.fill = GridBagConstraints.BOTH;
		gbc_customerHistoryDetails.gridx = 1;
		gbc_customerHistoryDetails.gridy = 3;
		optionsPanel.add(customerHistoryDetails, gbc_customerHistoryDetails);

		netProfit = new JTextField();
		netProfit.setHorizontalAlignment(SwingConstants.CENTER);
		netProfit.setEditable(false);
		netProfit.setColumns(10);
		GridBagConstraints gbc_totalSumText = new GridBagConstraints();
		gbc_totalSumText.fill = GridBagConstraints.VERTICAL;
		gbc_totalSumText.insets = new Insets(0, 0, 0, 5);
		gbc_totalSumText.gridx = 11;
		gbc_totalSumText.gridy = 3;
		optionsPanel.add(netProfit, gbc_totalSumText);

		JLabel totalNetProfitLabel = new JLabel("صافي الربح");
		GridBagConstraints gbc_totalNetProfitLabel = new GridBagConstraints();
		gbc_totalNetProfitLabel.fill = GridBagConstraints.VERTICAL;
		gbc_totalNetProfitLabel.weighty = 1;
		gbc_totalNetProfitLabel.gridx = 12;
		gbc_totalNetProfitLabel.gridy = 3;
		optionsPanel.add(totalNetProfitLabel, gbc_totalNetProfitLabel);

	}

	private void makeTable() {
		myTable = new MyTable<Product>(null);

		String[] columns = { "الباركود", "الأسم", "التاريخ", "عدد الكراتين",
				"سعر الكرتونة", "أجمالي سعر الكراتين",
				"عدد الوحدات في الكرتونة", "أجمالي عدد الوحدات",
				"عدد الوحدات المتاحة", "سعر شراء الوجدة", "سعر بيع الوحدة",
				"أجمالي سعر الشراء", "أجمالي سعر البيع", "صافي الربح" };

		myTable.setColumnNames(columns);
		myTable.setData(products);
		table = new JTable(myTable);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		adjustJTableRowSizes(table);
		alignTable();
	}

	public void alignTable() {
		MyCellRenderer myCellRenderer = new MyCellRenderer(TableType.PRODUCT);
		myCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) 
			table.getColumnModel().getColumn(i).setCellRenderer(myCellRenderer);
		table.setDefaultRenderer(Object.class, myCellRenderer);
	}

	public void adjustJTableRowSizes(JTable jTable) {
		for (int row = 0; row < jTable.getRowCount(); row++) {
			int maxHeight = 0;
			for (int column = 0; column < jTable.getColumnCount(); column++) {
				TableCellRenderer cellRenderer = jTable.getCellRenderer(row,
						column);
				Object valueAt = jTable.getValueAt(row, column);
				Component tableCellRendererComponent = cellRenderer
						.getTableCellRendererComponent(jTable, valueAt, false,
								false, row, column);
				int heightPreferable = tableCellRendererComponent
						.getPreferredSize().height;
				maxHeight = Math.max(heightPreferable, maxHeight);
			}
			jTable.setRowHeight(row, maxHeight);
		}
	}

	public void updateData() {
		products = MainWindow.logic.getProducts();
		if (totalBuying != null) {
			setTextFields();
			myTable.setData(products);
			myTable.fireTableStructureChanged();
			myTable.fireTableDataChanged();
			adjustJTableRowSizes(table);
			alignTable();
		} else
			makeTable();

	}
}
