package com.wchs.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import com.wchs.model.Category;
import com.wchs.model.Product;
import com.wchs.ui.DateLabelFormatter;
import com.wchs.ui.MainWindow;
import com.wchs.ui.panel.ProductsPanel;

@SuppressWarnings("serial")
public class ProductProcedure extends JFrame {

	private JPanel contentPane;
	private Product product;
	private JTextField nameTextField;
	private JTextField boxesTextField;
	private JTextField itemsTextField;
	private JTextField boxPriceTextField;
	private JTextField sellingPricePerItemTextField;
	private JTextField buyingPricePerItemTextField;
	private JTextField totalItemsTextField;
	private JTextField totalBuyingPriceTextField;
	private JTextField totalSellingPriceTextField;
	private JTextField netProfitTextField;
	private SqlDateModel model;
	private ProductsPanel productsPanel;
	private List<Category> categories;
	private JComboBox<Category> comboBox;
	private JTextField barcodeTextField;

	/**
	 * Launch the application.
	 * 
	 * @param productsPanel
	 */
	public static void main(String[] args, final Product product,
			final ProductsPanel productsPanel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductProcedure frame = new ProductProcedure(product,
							productsPanel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductProcedure(Product product, ProductsPanel productsPanel) {
		this.product = product;
		this.productsPanel = productsPanel;
		categories = MainWindow.logic.getCategories();
		intializeUIComponents();
		if (product != null)
			setTextFields();

	}

	private void setTextFields() {
		barcodeTextField.setText(product.getBarcode());
		nameTextField.setText(product.getName());
		boxesTextField.setText(product.getNumberofBoxes() + "");
		itemsTextField.setText(product.getItemsPerBox() + "");
		boxPriceTextField.setText(product.getBoxPrice() + "");
		sellingPricePerItemTextField.setText(product.getSellingPricePerItem()
				+ "");
		buyingPricePerItemTextField.setText(product.getBuyingPricePerItem()
				+ "");
		totalItemsTextField.setText(product.getTotalItems() + "");
		totalBuyingPriceTextField.setText(product.getTotalBuyingPrice() + "");
		totalSellingPriceTextField.setText(product.getTotalSellingPrice() + "");
		netProfitTextField.setText(product.getNetProfit() + "");
		model.setValue(new java.sql.Date(product.getDate().getTime()));
		System.out.println(product.getCategory().getName());
		comboBox.setSelectedItem(product.getCategory());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void intializeUIComponents() {
		model = new SqlDateModel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel optionsPanel = new JPanel();
		contentPane.add(optionsPanel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("New button");
		if (product == null) {
			btnNewButton.setText("إضافة ");
			setTitle("إضافة منتج");
		} else {
			btnNewButton.setText(" تعديل ");
			setTitle(product.getName() + " تعديل ");
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String barcode = barcodeTextField.getText().toString();
				String name = nameTextField.getText().toString();
				long date = ((Date) model.getValue()).getTime();
				Integer numberofBoxes = Integer.parseInt(boxesTextField
						.getText().toString());
				Double boxPrice = Double.parseDouble(boxPriceTextField
						.getText().toString());
				Integer itemsPerBox = Integer.parseInt(itemsTextField.getText()
						.toString());
				Integer totalItems = Integer.parseInt(totalItemsTextField
						.getText().toString());
				Integer totalAvailableItems = totalItems;
				Double buyingPricePerItem = Double
						.parseDouble(buyingPricePerItemTextField.getText()
								.toString());
				Double sellingPricePerItem = Double
						.parseDouble(sellingPricePerItemTextField.getText()
								.toString());
				Double totalBuyingPrice = Double
						.parseDouble(totalBuyingPriceTextField.getText()
								.toString());
				Double totalSellingPrice = Double
						.parseDouble(totalSellingPriceTextField.getText()
								.toString());
				Double netProfit = Double.parseDouble(netProfitTextField
						.getText().toString());
				Product newPro = null;
				Category category = (Category) comboBox.getSelectedItem();
				if (product == null) {
					newPro = MainWindow.logic.addProduct(barcode, name, date,
							numberofBoxes, boxPrice, itemsPerBox, totalItems,
							totalAvailableItems, buyingPricePerItem,
							sellingPricePerItem, totalBuyingPrice,
							totalSellingPrice, netProfit, category);
					productsPanel.getMyTable().addRow(newPro);
					MainWindow.logic.addProductToList(newPro);
				} else {
					newPro = MainWindow.logic.updateProduct(product.getPid(),
							barcode, name, date, numberofBoxes, boxPrice,
							itemsPerBox, totalItems, totalAvailableItems,
							buyingPricePerItem, sellingPricePerItem,
							totalBuyingPrice, totalSellingPrice, netProfit,
							category);
					productsPanel.getMyTable().updateRow(product.getBarcode(),
							newPro);
					MainWindow.logic.updateProductToList(newPro);
				}
				MainWindow.observer.update();
				dispose();
			}
		});
		optionsPanel.add(btnNewButton);

		JPanel entriesPanel = new JPanel();
		entriesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		contentPane.add(entriesPanel, BorderLayout.CENTER);

		GridBagLayout gbl_entriessPanel = new GridBagLayout();
		gbl_entriessPanel.columnWidths = new int[] { 0, 42, 148, 124, 0, 150,
				0, 28 };
		gbl_entriessPanel.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0,
				0.0, 1.0, 0.0, 0.0 };
		entriesPanel.setLayout(gbl_entriessPanel);

		barcodeTextField = new JTextField();
		GridBagConstraints gbc_barcodeTextField = new GridBagConstraints();
		gbc_barcodeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_barcodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_barcodeTextField.gridx = 5;
		gbc_barcodeTextField.gridy = 0;
		entriesPanel.add(barcodeTextField, gbc_barcodeTextField);
		barcodeTextField.setColumns(10);

		JLabel barcodeLabel = new JLabel("الباركود");
		GridBagConstraints gbc_barcodeLabel = new GridBagConstraints();
		gbc_barcodeLabel.fill = GridBagConstraints.VERTICAL;
		gbc_barcodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_barcodeLabel.gridx = 6;
		gbc_barcodeLabel.gridy = 0;
		entriesPanel.add(barcodeLabel, gbc_barcodeLabel);

		buyingPricePerItemTextField = new JTextField();
		GridBagConstraints gbc_buyingPricePerItemTextField = new GridBagConstraints();
		gbc_buyingPricePerItemTextField.insets = new Insets(0, 0, 5, 5);
		gbc_buyingPricePerItemTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buyingPricePerItemTextField.gridx = 2;
		gbc_buyingPricePerItemTextField.gridy = 1;
		entriesPanel.add(buyingPricePerItemTextField,
				gbc_buyingPricePerItemTextField);
		buyingPricePerItemTextField.setColumns(10);

		JLabel buyingItemLabel = new JLabel("سعر شراء السلعة");
		buyingItemLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_buyingItemLabel = new GridBagConstraints();
		gbc_buyingItemLabel.insets = new Insets(0, 0, 5, 5);
		gbc_buyingItemLabel.gridx = 3;
		gbc_buyingItemLabel.gridy = 1;
		entriesPanel.add(buyingItemLabel, gbc_buyingItemLabel);

		JButton btnNewButton_1 = new JButton("تحديث");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyingPricePerItemTextField.setText((Double
						.parseDouble(boxPriceTextField.getText().toString()) / Double
						.parseDouble(itemsTextField.getText().toString()))
						+ "");
				totalItemsTextField.setText((Integer.parseInt(itemsTextField
						.getText().toString()) * Integer
						.parseInt(boxesTextField.getText().toString()))
						+ "");
				totalBuyingPriceTextField.setText((Double
						.parseDouble(boxPriceTextField.getText().toString()) * Double
						.parseDouble(boxesTextField.getText().toString()))
						+ "");
				totalSellingPriceTextField.setText((Double
						.parseDouble(sellingPricePerItemTextField.getText()
								.toString())
						* Double.parseDouble(itemsTextField.getText()
								.toString()) * Double
							.parseDouble(boxesTextField.getText().toString()))
						+ "");
				netProfitTextField.setText((Double
						.parseDouble(totalSellingPriceTextField.getText()
								.toString()) - Double
						.parseDouble(totalBuyingPriceTextField.getText()
								.toString()))
						+ "");
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 1;
		entriesPanel.add(btnNewButton_1, gbc_btnNewButton_1);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 5;
		gbc_nameTextField.gridy = 1;
		entriesPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel productNameLabel = new JLabel("أسم المنتج");
		productNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_productNameLabel = new GridBagConstraints();
		gbc_productNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productNameLabel.fill = GridBagConstraints.VERTICAL;
		gbc_productNameLabel.weighty = 1;
		gbc_productNameLabel.gridx = 6;
		gbc_productNameLabel.gridy = 1;
		entriesPanel.add(productNameLabel, gbc_productNameLabel);

		totalItemsTextField = new JTextField();
		GridBagConstraints gbc_totalItemsTextField = new GridBagConstraints();
		gbc_totalItemsTextField.insets = new Insets(0, 0, 5, 5);
		gbc_totalItemsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalItemsTextField.gridx = 2;
		gbc_totalItemsTextField.gridy = 2;
		entriesPanel.add(totalItemsTextField, gbc_totalItemsTextField);
		totalItemsTextField.setColumns(10);

		JLabel totalItemsLabel = new JLabel("أجمالي عدد السلع");
		totalItemsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_totalItemsLabel = new GridBagConstraints();
		gbc_totalItemsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalItemsLabel.gridx = 3;
		gbc_totalItemsLabel.gridy = 2;
		entriesPanel.add(totalItemsLabel, gbc_totalItemsLabel);

		boxesTextField = new JTextField();
		GridBagConstraints gbc_boxesTextField = new GridBagConstraints();
		gbc_boxesTextField.insets = new Insets(0, 0, 5, 5);
		gbc_boxesTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxesTextField.gridx = 5;
		gbc_boxesTextField.gridy = 2;
		entriesPanel.add(boxesTextField, gbc_boxesTextField);
		boxesTextField.setColumns(10);

		JLabel numberOfBoxesLabel = new JLabel("عدد الكراتين");
		numberOfBoxesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_numberOfBoxesLabel = new GridBagConstraints();
		gbc_numberOfBoxesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfBoxesLabel.fill = GridBagConstraints.VERTICAL;
		gbc_numberOfBoxesLabel.weighty = 1;
		gbc_numberOfBoxesLabel.gridx = 6;
		gbc_numberOfBoxesLabel.gridy = 2;
		entriesPanel.add(numberOfBoxesLabel, gbc_numberOfBoxesLabel);

		totalBuyingPriceTextField = new JTextField();
		GridBagConstraints gbc_totalBuyingPriceTextField = new GridBagConstraints();
		gbc_totalBuyingPriceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_totalBuyingPriceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalBuyingPriceTextField.gridx = 2;
		gbc_totalBuyingPriceTextField.gridy = 3;
		entriesPanel.add(totalBuyingPriceTextField,
				gbc_totalBuyingPriceTextField);
		totalBuyingPriceTextField.setColumns(10);

		JLabel totalBuyingPriceLabel = new JLabel("أجمالي سعر الشراء");
		totalBuyingPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_totalBuyingPriceLabel = new GridBagConstraints();
		gbc_totalBuyingPriceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalBuyingPriceLabel.gridx = 3;
		gbc_totalBuyingPriceLabel.gridy = 3;
		entriesPanel.add(totalBuyingPriceLabel, gbc_totalBuyingPriceLabel);

		itemsTextField = new JTextField();
		GridBagConstraints gbc_itemsTextField = new GridBagConstraints();
		gbc_itemsTextField.insets = new Insets(0, 0, 5, 5);
		gbc_itemsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemsTextField.gridx = 5;
		gbc_itemsTextField.gridy = 3;
		entriesPanel.add(itemsTextField, gbc_itemsTextField);
		itemsTextField.setColumns(10);

		JLabel numerOfItemsLabel = new JLabel("عدد الوحدات في الكرتونة");
		numerOfItemsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_numerOfItemsLabel = new GridBagConstraints();
		gbc_numerOfItemsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numerOfItemsLabel.fill = GridBagConstraints.VERTICAL;
		gbc_numerOfItemsLabel.weighty = 1;
		gbc_numerOfItemsLabel.gridx = 6;
		gbc_numerOfItemsLabel.gridy = 3;
		entriesPanel.add(numerOfItemsLabel, gbc_numerOfItemsLabel);

		totalSellingPriceTextField = new JTextField();
		GridBagConstraints gbc_totalSellingPriceTextField = new GridBagConstraints();
		gbc_totalSellingPriceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_totalSellingPriceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalSellingPriceTextField.gridx = 2;
		gbc_totalSellingPriceTextField.gridy = 4;
		entriesPanel.add(totalSellingPriceTextField,
				gbc_totalSellingPriceTextField);
		totalSellingPriceTextField.setColumns(10);

		JLabel totalSellingPriceLabel = new JLabel("أجمالي سعر البيع");
		totalSellingPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_totalSellingPriceLabel = new GridBagConstraints();
		gbc_totalSellingPriceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalSellingPriceLabel.gridx = 3;
		gbc_totalSellingPriceLabel.gridy = 4;
		entriesPanel.add(totalSellingPriceLabel, gbc_totalSellingPriceLabel);

		boxPriceTextField = new JTextField();
		GridBagConstraints gbc_boxPriceTextField = new GridBagConstraints();
		gbc_boxPriceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_boxPriceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxPriceTextField.gridx = 5;
		gbc_boxPriceTextField.gridy = 4;
		entriesPanel.add(boxPriceTextField, gbc_boxPriceTextField);
		boxPriceTextField.setColumns(10);

		JLabel boxPriceLabel = new JLabel("سعر الكرتونة");
		boxPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_boxPriceLabel = new GridBagConstraints();
		gbc_boxPriceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_boxPriceLabel.fill = GridBagConstraints.VERTICAL;
		gbc_boxPriceLabel.weighty = 1;
		gbc_boxPriceLabel.gridx = 6;
		gbc_boxPriceLabel.gridy = 4;
		entriesPanel.add(boxPriceLabel, gbc_boxPriceLabel);

		netProfitTextField = new JTextField();
		GridBagConstraints gbc_netProfitTextField = new GridBagConstraints();
		gbc_netProfitTextField.insets = new Insets(0, 0, 5, 5);
		gbc_netProfitTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_netProfitTextField.gridx = 2;
		gbc_netProfitTextField.gridy = 5;
		entriesPanel.add(netProfitTextField, gbc_netProfitTextField);
		netProfitTextField.setColumns(10);

		JLabel netProfitLabel = new JLabel("صافي الربح");
		netProfitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_netProfitLabel = new GridBagConstraints();
		gbc_netProfitLabel.insets = new Insets(0, 0, 5, 5);
		gbc_netProfitLabel.gridx = 3;
		gbc_netProfitLabel.gridy = 5;
		entriesPanel.add(netProfitLabel, gbc_netProfitLabel);

		sellingPricePerItemTextField = new JTextField();
		GridBagConstraints gbc_sellingPricePerItemTextField = new GridBagConstraints();
		gbc_sellingPricePerItemTextField.insets = new Insets(0, 0, 5, 5);
		gbc_sellingPricePerItemTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sellingPricePerItemTextField.gridx = 5;
		gbc_sellingPricePerItemTextField.gridy = 5;
		entriesPanel.add(sellingPricePerItemTextField,
				gbc_sellingPricePerItemTextField);
		sellingPricePerItemTextField.setColumns(10);

		JLabel sellingPricePerItemLabel = new JLabel("سعر بيع السلعة");
		sellingPricePerItemLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_sellingPricePerItemLabel = new GridBagConstraints();
		gbc_sellingPricePerItemLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sellingPricePerItemLabel.fill = GridBagConstraints.VERTICAL;
		gbc_sellingPricePerItemLabel.weighty = 1;
		gbc_sellingPricePerItemLabel.gridx = 6;
		gbc_sellingPricePerItemLabel.gridy = 5;
		entriesPanel
				.add(sellingPricePerItemLabel, gbc_sellingPricePerItemLabel);

		comboBox = new JComboBox(categories.toArray());
		if (!categories.isEmpty())
			comboBox.setSelectedIndex(0);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 6;
		entriesPanel.add(comboBox, gbc_comboBox);

		JLabel dateLabel = new JLabel("تاريخ الورود");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.insets = new Insets(0, 0, 0, 5);
		gbc_dateLabel.fill = GridBagConstraints.VERTICAL;
		gbc_dateLabel.weighty = 1;
		gbc_dateLabel.gridx = 6;
		gbc_dateLabel.gridy = 6;
		entriesPanel.add(dateLabel, gbc_dateLabel);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.gridwidth = 2;
		gbc_datePicker.insets = new Insets(0, 0, 0, 5);
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.weighty = 1;
		gbc_datePicker.gridx = 4;
		gbc_datePicker.gridy = 6;
		entriesPanel.add(datePicker, gbc_datePicker);

	}

}
