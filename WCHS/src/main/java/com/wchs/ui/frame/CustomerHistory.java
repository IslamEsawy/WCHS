package com.wchs.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import com.wchs.model.Customer;
import com.wchs.model.Transaction;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class CustomerHistory extends JFrame {

	private JPanel contentPane;
	private Customer customer;
	private JTable table;
	private MyTable<Transaction> myTable;
	private List<Transaction> transactions;

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
	 * Launch the application.
	 */
	public static void main(String[] args, final Customer customer) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerHistory frame = new CustomerHistory(customer);
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
	public CustomerHistory(Customer cus) {
		
		this.customer = cus;
		setTitle(customer.getName() + " تاريخ عمليات " );
		transactions = MainWindow.logic.loadTransactions(customer);
		System.out.println(customer.getName());
		System.out.println(transactions.size());
		makeTable();
		intializeUIComponents();
		
	}

	
	private void intializeUIComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		contentPane.add(tablePanel, BorderLayout.CENTER);
		System.out.println(customer.getName());		
	}

	private void makeTable() {
		myTable = new MyTable<Transaction>(null);
		String[] columns = { "باركود", "أسم المنتج", "التاريخ", "الكمية",
				"نقدي", "تقريشة" };
		myTable.setColumnNames(columns);
		myTable.setData(transactions);
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
		MyCellRenderer myCellRenderer = new MyCellRenderer(TableType.TRANSACTION);
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
}
