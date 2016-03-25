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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import com.wchs.model.Borrow;
import com.wchs.model.Customer;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.ui.frame.CustomerHistory;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class CustomersPanel extends JPanel {
	private JTextField moneyToReturnText;
	private JTextField cashText;
	private JTextField totalSumText;
	private List<Customer> customers;
	private JTable table;
	private MyTable<Customer> myTable;

	public MyTable<Customer> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Customer> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Create the panel.
	 */
	public CustomersPanel() {
		setLayout(new BorderLayout(0, 0));

		updateData();

		intializeUIComponents();
		setTextFields();
	}

	private void setTextFields() {
		Double sumOfMTR = getSumOfMoneyToReturn();
		Double sumOfCash = getSumCash();
		moneyToReturnText.setText(sumOfMTR + "");
		cashText.setText(sumOfCash + "");
		totalSumText.setText((Double) (sumOfMTR + sumOfCash) + "");
	}

	private Double getSumCash() {
		Double sum = 0.0;
		for (Customer cus : customers) {
			sum += cus.getTotalCash();
		}
		return sum;
	}

	private Double getSumOfMoneyToReturn() {
		Double sum = 0.0;
		for (Customer cus : customers) {
			sum += cus.getMoneyToReturn();
		}
		return sum;
	}

	private void intializeUIComponents() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		add(optionsPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_optionsPanel = new GridBagLayout();
		gbl_optionsPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_optionsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gbl_optionsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, 0.0, 1.0, 0.0 };
		optionsPanel.setLayout(gbl_optionsPanel);

		JButton addCustomerButton = new JButton("إضافة عميل");
		addCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField cusName = new JTextField();
				cusName.requestFocusInWindow();
				JPanel myPanel = new JPanel();
				myPanel.setBounds(100, 100, 100, 100);
				myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

				JLabel nameL = new JLabel("الأسم: ");
				nameL.setHorizontalAlignment(JLabel.RIGHT);
				myPanel.add(nameL);
				myPanel.add(cusName);

				int result = JOptionPane.showConfirmDialog(CustomersPanel.this, myPanel,
						"إضافة عميل", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					customers.add(MainWindow.logic.addCustomer(cusName
							.getText().toString(), 0.0, 0.0));
					MainWindow.logic.setCustomers(customers);
					MainWindow.observer.update();
				}
			}
		});
		GridBagConstraints gbc_addCustomerButton = new GridBagConstraints();
		gbc_addCustomerButton.insets = new Insets(0, 0, 5, 5);
		gbc_addCustomerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addCustomerButton.gridx = 0;
		gbc_addCustomerButton.gridy = 1;
		optionsPanel.add(addCustomerButton, gbc_addCustomerButton);

		moneyToReturnText = new JTextField();
		moneyToReturnText.setHorizontalAlignment(SwingConstants.CENTER);
		moneyToReturnText.setEditable(false);
		GridBagConstraints gbc_moneyToReturnText = new GridBagConstraints();
		gbc_moneyToReturnText.gridwidth = 2;
		gbc_moneyToReturnText.insets = new Insets(0, 0, 5, 5);
		gbc_moneyToReturnText.fill = GridBagConstraints.BOTH;
		gbc_moneyToReturnText.gridx = 6;
		gbc_moneyToReturnText.gridy = 1;
		optionsPanel.add(moneyToReturnText, gbc_moneyToReturnText);
		moneyToReturnText.setColumns(10);

		JButton customerHistoryDetails = new JButton("تاريخ عمليات العميل");
		customerHistoryDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 1)
					CustomerHistory.main(
							null,
							myTable.getObject(table.getValueAt(
									table.getSelectedRow(), 0).toString()));
			}
		});

		JLabel moneyToReturnLabel = new JLabel("أجمالي التقريشة ");
		moneyToReturnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_moneyToReturnLabel = new GridBagConstraints();
		gbc_moneyToReturnLabel.anchor = GridBagConstraints.EAST;
		gbc_moneyToReturnLabel.insets = new Insets(0, 0, 5, 0);
		gbc_moneyToReturnLabel.weighty = 1;
		gbc_moneyToReturnLabel.gridx = 8;
		gbc_moneyToReturnLabel.gridy = 1;
		optionsPanel.add(moneyToReturnLabel, gbc_moneyToReturnLabel);
		GridBagConstraints gbc_customerHistoryDetails = new GridBagConstraints();
		gbc_customerHistoryDetails.insets = new Insets(0, 0, 5, 5);
		gbc_customerHistoryDetails.fill = GridBagConstraints.VERTICAL;
		gbc_customerHistoryDetails.gridx = 0;
		gbc_customerHistoryDetails.gridy = 2;
		optionsPanel.add(customerHistoryDetails, gbc_customerHistoryDetails);

		cashText = new JTextField();
		cashText.setHorizontalAlignment(SwingConstants.CENTER);
		cashText.setEditable(false);
		cashText.setColumns(10);
		GridBagConstraints gbc_cashText = new GridBagConstraints();
		gbc_cashText.gridwidth = 2;
		gbc_cashText.fill = GridBagConstraints.BOTH;
		gbc_cashText.insets = new Insets(0, 0, 5, 5);
		gbc_cashText.gridx = 6;
		gbc_cashText.gridy = 2;
		optionsPanel.add(cashText, gbc_cashText);

		JLabel cashLabel = new JLabel("أجمالي النقدي");
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_cashLabel = new GridBagConstraints();
		gbc_cashLabel.anchor = GridBagConstraints.EAST;
		gbc_cashLabel.insets = new Insets(0, 0, 5, 0);
		gbc_cashLabel.fill = GridBagConstraints.VERTICAL;
		gbc_cashLabel.weighty = 1;
		gbc_cashLabel.gridx = 8;
		gbc_cashLabel.gridy = 2;
		optionsPanel.add(cashLabel, gbc_cashLabel);

		JButton addBorrowButton = new JButton("إنشاء سلفة");
		addBorrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 1) {
					Customer customer = (Customer) myTable.getObject(table
							.getValueAt(table.getSelectedRow(), 0).toString());
					JTextField amountText = new JTextField();
					JTextField signatureText = new JTextField();
					amountText.requestFocusInWindow();
					JPanel myPanel = new JPanel();
					myPanel.setBounds(100, 100, 100, 100);
					myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

					myPanel.add(new JLabel("المبلغ: "));
					myPanel.add(amountText);
					myPanel.add(new JLabel("الأمر بالصرف: "));
					myPanel.add(signatureText);

					int result = JOptionPane.showConfirmDialog(CustomersPanel.this, myPanel,
							customer.getName() + " إنشاء سلفة للعميل ",
							JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Borrow b = MainWindow.logic.addBorrow(Double
								.parseDouble(amountText.getText().toString()),
								signatureText.getText().toString(), new Date(),
								customer);
						System.out.println(b.getDate());
						MainWindow.logic.addBorrowToList(b);
						MainWindow.observer.update();
					}
				}
			}
		});
		GridBagConstraints gbc_addBorrowButton = new GridBagConstraints();
		gbc_addBorrowButton.fill = GridBagConstraints.BOTH;
		gbc_addBorrowButton.insets = new Insets(0, 0, 0, 5);
		gbc_addBorrowButton.gridx = 0;
		gbc_addBorrowButton.gridy = 3;
		optionsPanel.add(addBorrowButton, gbc_addBorrowButton);

		totalSumText = new JTextField();
		totalSumText.setHorizontalAlignment(SwingConstants.CENTER);
		totalSumText.setEditable(false);
		totalSumText.setColumns(10);
		GridBagConstraints gbc_totalSumText = new GridBagConstraints();
		gbc_totalSumText.gridwidth = 2;
		gbc_totalSumText.insets = new Insets(0, 0, 0, 5);
		gbc_totalSumText.fill = GridBagConstraints.BOTH;
		gbc_totalSumText.gridx = 6;
		gbc_totalSumText.gridy = 3;
		optionsPanel.add(totalSumText, gbc_totalSumText);

		JLabel totalSumLabel = new JLabel("الأجمالي");
		totalSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_totalSumLabel = new GridBagConstraints();
		gbc_totalSumLabel.anchor = GridBagConstraints.EAST;
		gbc_totalSumLabel.fill = GridBagConstraints.VERTICAL;
		gbc_totalSumLabel.weighty = 1;
		gbc_totalSumLabel.gridx = 8;
		gbc_totalSumLabel.gridy = 3;
		optionsPanel.add(totalSumLabel, gbc_totalSumLabel);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		add(tablePanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("العملاء");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
	}

	private void makeTable() {
		myTable = new MyTable<Customer>(null);

		String[] columns = { "الرقم", "الأسم", "نقدي", "تقريشة" };

		myTable.setColumnNames(columns);
		myTable.setData(customers);
		table = new JTable(myTable);
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
		MyCellRenderer myCellRenderer = new MyCellRenderer(TableType.CUSTOMER);
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
		customers = MainWindow.logic.getCustomers();
		if (moneyToReturnText != null) {
			setTextFields();
			myTable.setData(customers);
			myTable.fireTableStructureChanged();
			myTable.fireTableDataChanged();
			adjustJTableRowSizes(table);
			alignTable();
		} else
			makeTable();
	}

}
