package com.wchs.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import com.wchs.model.Category;
import com.wchs.model.Customer;
import com.wchs.model.Product;
import com.wchs.model.Transaction;
import com.wchs.model.TransactionId;
import com.wchs.ui.AutocompleteJComboBox;
import com.wchs.ui.CustomerSearchable;
import com.wchs.ui.EventHandling;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class TransactionPanel extends JPanel {

	private JPanel productsPanel;
	private JPanel categoriesPanel;
	private JTable table;
	private List<Category> categories;
	private List<Customer> customers;
	// private JComboBox<Customer> comboBox;
	private MyTable<Transaction> myTable;
	private JTextField cashTextField;
	private JTextField mTRTextField;
	private EventHandling handler = new EventHandling(TransactionPanel.this);

	private CustomerSearchable searchable;
	private AutocompleteJComboBox combo;

	public MyTable<Transaction> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Transaction> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Create the panel.
	 */
	public TransactionPanel() {
		categories = MainWindow.logic.getCategories();
		customers = MainWindow.logic.getCustomers();
		setLayout(new BorderLayout(0, 0));
		updateData();
		intializeUIComponents();

	}

	private void intializeUIComponents() {
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);

		JButton btnNewButton = new JButton("تنفيذ");
		panel_5.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.logic.makeTransaction(myTable.getData(),
						Double.parseDouble(mTRTextField.getText().toString()),
						Double.parseDouble(cashTextField.getText().toString()));
				createNewSheet();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnNewButton_1 = new JButton("عملية جديدة");
		panel_5.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewSheet();
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JPanel tablePanel = new JPanel();
		tablePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tablePanel.setLayout(new BorderLayout(2, 2));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		panel_3.add(tablePanel);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		tablePanel.add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton_3 = new JButton("حذف المنتج من القائمة");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					int respone = JOptionPane.showConfirmDialog(
							TransactionPanel.this, "هل أنت متأكد؟", "تأكيد",
							JOptionPane.YES_NO_OPTION);
					switch (respone) {
					case 0: {
						List<Integer> delList = new ArrayList<Integer>();
						for (int value : table.getSelectedRows()) {
							delList.add(Integer.valueOf(value));
						}
						Collections.sort(delList);
						for (int i = delList.size() - 1; i >= 0; i--)
							myTable.deleteRowIndex(table
									.convertRowIndexToModel(delList.get(i)));
						myTable.fireTableDataChanged();
						updateFields();
						break;
					}
					case 1: {
					}
					}

				}
			}
		});
		panel_1.add(btnNewButton_3);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JLabel cashLabel = new JLabel("أجمالي النقدي");
		cashLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cashLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(cashLabel);

		cashTextField = new JTextField();
		cashTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cashTextField.setEditable(false);
		panel_2.add(cashTextField);
		cashTextField.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JLabel mTRLabel = new JLabel("أجمالي التقريشة");
		mTRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mTRLabel.setAlignmentX(0.5f);
		panel_4.add(mTRLabel);

		mTRTextField = new JTextField();
		mTRTextField.setHorizontalAlignment(SwingConstants.CENTER);
		mTRTextField.setEditable(false);
		mTRTextField.setColumns(10);
		panel_4.add(mTRTextField);

		JPanel panel_8 = new JPanel();
		tablePanel.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		searchable = new CustomerSearchable(customers);
		combo = new AutocompleteJComboBox(searchable);

		/*
		 * comboBox = new JComboBox(customers.toArray());
		 * AutoCompleteDecorator.decorate(this.comboBox);
		 * comboBox.setEditable(true); comboBox.setSelectedIndex(-1);
		 * panel_8.add(comboBox);
		 */
		panel_8.add(combo);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(new BorderLayout(2, 2));
		addCategoriesToPanel();
		JScrollPane categoriesPaneljScrollPanea = new JScrollPane(
				categoriesPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(new BorderLayout(2, 2));
		panel_7.add(categoriesPaneljScrollPanea, BorderLayout.CENTER);
		panel_6.add(panel_7, BorderLayout.NORTH);
		panel_3.add(panel_6);
		productsPanel = new JPanel();

		productsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		JScrollPane jScrollPanea = new JScrollPane(productsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_6.add(jScrollPanea);
		productsPanel.setLayout(new GridLayout(0, 2, 8, 8));
	}

	protected void createNewSheet() {
		combo.setSelectedIndex(-1);
		myTable.setData(new ArrayList<Transaction>());
		addEmptyTransaction();
		myTable.fireTableDataChanged();
		updateFields();
	}

	private void addCategoriesToPanel() {
		if (categoriesPanel == null)
			categoriesPanel = new JPanel();
		else
			categoriesPanel.removeAll();
		categoriesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		for (Category category : categories) {
			JButton newButton = new JButton(category.getName());
			newButton.setPreferredSize(new Dimension(120, 50));
			categoriesPanel.add(newButton);
			newButton.addActionListener(handler);
		}
	}

	public void createTransaction(Product product) {
		Customer customer = (Customer) combo.getSelectedItem();
		System.out.println(customer.getCid());
		if (customer.getCid() != null) {
			if (customer.getMoneyToReturn() >= 346)
				showErrorMessage(" العميل  " + customer.getName()
						+ " متجاوز حد التقريشة ");
			else {
				Date date = new Date();
				TransactionId cPid = new TransactionId(customer, product);
				Transaction s = new Transaction(cPid, 1, date, 0.0, 0.0);
				s.setMoneyToReturn(s.getQuantity()
						* product.getSellingPricePerItem());
				System.out.println("New T: " + s);
				myTable.setTransaction(s);
				addEmptyTransaction();
				updateFields();
			}
		} else {
			showErrorMessage("من فضلك قم بإختيار العميل");
		}
	}

	public static void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Oops", JOptionPane.OK_OPTION);
	}

	public void addEmptyTransaction() {
		myTable.addRow(new Transaction());
		myTable.fireTableDataChanged();
	}

	private void makeTable() {
		myTable = new MyTable<Transaction>(TransactionPanel.this);
		String[] columns = { "باركود", "أسم المنتج", "التاريخ", "الكمية",
				"نقدي", "تقريشة" };
		myTable.setColumnNames(columns);
		myTable.setData(new ArrayList<Transaction>());
		table = new JTable(myTable);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		adjustJTableRowSizes(table);
		alignTable();
		addEmptyTransaction();
	}

	public void alignTable() {
		MyCellRenderer myCellRenderer = new MyCellRenderer(
				TableType.TRANSACTION);
		myCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(myCellRenderer);
		}
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
		categories = MainWindow.logic.getCategories();

		System.out.println("categories size :" + categories.size());
		customers = MainWindow.logic.getCustomers();
		if (combo != null) {
			searchable.setTerms(customers);
			combo.setSearchable(searchable);
		}
		/*
		 * if (comboBox != null) { DefaultComboBoxModel model = new
		 * DefaultComboBoxModel( customers.toArray()); comboBox.setModel(model);
		 * comboBox.setSelectedIndex(-1); }
		 */
		addCategoriesToPanel();
		makeTable();
	}

	public void updateFields() {
		List<Transaction> transactions = myTable.getData();
		Double sumCash = 0.0, sumMTR = 0.0;
		for (int i = 0; i < transactions.size() - 1; i++) {
			sumCash += transactions.get(i).getCash();
			sumMTR += transactions.get(i).getMoneyToReturn();
		}
		cashTextField.setText(sumCash.toString());
		mTRTextField.setText(sumMTR.toString());
	}

	public void fillProducts(List<Product> products) {
		productsPanel.removeAll();
		for (Product product : products) {
			String buttonName = product.getBarcode() + "\n - \n"
					+ product.getName();
			JButton productButton = new JButton("<html>"
					+ buttonName.replaceAll("\\n", "<br>") + "</html>");
			productButton.setPreferredSize(new Dimension(80, 80));
			productButton.addActionListener(handler);
			if (product.getTotalAvailableItems() <= 0) {
				productButton.setEnabled(false);
			}
			productsPanel.add(productButton);
			productsPanel.revalidate();
			productsPanel.repaint();

		}
	}
}
