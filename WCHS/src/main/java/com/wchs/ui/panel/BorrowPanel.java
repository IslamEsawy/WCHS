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

import com.wchs.model.Borrow;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class BorrowPanel extends JPanel {

	private JTextField sumTextField;
	private List<Borrow> borrows;
	private JTable table;
	private MyTable<Borrow> myTable;

	public MyTable<Borrow> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Borrow> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Create the panel.
	 */
	public BorrowPanel() {
		setLayout(new BorderLayout(0, 0));

		updateData();
		intializeUIComponents();
		setTextFields();
	}

	private void setTextFields() {
		Double sumOfBorrows = MainWindow.logic.getTotalBorrows();
		sumTextField.setText(sumOfBorrows + "");
	}

	private void intializeUIComponents() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		add(optionsPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_optionsPanel = new GridBagLayout();
		gbl_optionsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 0.0 };
		optionsPanel.setLayout(gbl_optionsPanel);

		JButton makePaidButton = new JButton("استرجاع للسلفة");
		makePaidButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					int respone = JOptionPane.showConfirmDialog(null,
							"هل أنت متأكد؟", "تأكيد",
							JOptionPane.YES_NO_OPTION);
					switch (respone) {
					case 0: {
						String[] id = new String[table.getSelectedRows().length];
						int g = 0;
						for (int i : table.getSelectedRows()) {
							id[g] = table.getValueAt(i, 0).toString();
							for (int j = 0; j < borrows.size(); j++) {
								if (borrows.get(j).getBid().toString()
										.equals(id[g])) {
									borrows.get(j).setIsPaid("YES");
									break;
								}
							}
							g++;
						}
						MainWindow.logic.makeBorrowPaid(id);
						MainWindow.logic.setBorrows(borrows);
						MainWindow.observer.update();
						break;
					}
					case 1: {
					}
					}

				}
			}
		});
		GridBagConstraints gbc_makePaidButton = new GridBagConstraints();
		gbc_makePaidButton.insets = new Insets(0, 0, 5, 5);
		gbc_makePaidButton.fill = GridBagConstraints.VERTICAL;
		gbc_makePaidButton.gridx = 0;
		gbc_makePaidButton.gridy = 2;
		optionsPanel.add(makePaidButton, gbc_makePaidButton);

		sumTextField = new JTextField();
		sumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		sumTextField.setEditable(false);
		GridBagConstraints gbc_moneyToReturnText = new GridBagConstraints();
		gbc_moneyToReturnText.insets = new Insets(0, 0, 5, 5);
		gbc_moneyToReturnText.fill = GridBagConstraints.VERTICAL;
		gbc_moneyToReturnText.gridx = 7;
		gbc_moneyToReturnText.gridy = 2;
		optionsPanel.add(sumTextField, gbc_moneyToReturnText);
		sumTextField.setColumns(10);

		JLabel sumLabel = new JLabel("الأجمالي");
		GridBagConstraints gbc_sumLabel = new GridBagConstraints();
		gbc_sumLabel.insets = new Insets(0, 0, 5, 0);
		gbc_sumLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_sumLabel.weighty = 1;
		gbc_sumLabel.gridx = 8;
		gbc_sumLabel.gridy = 2;
		optionsPanel.add(sumLabel, gbc_sumLabel);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		add(tablePanel, BorderLayout.CENTER);
	}

	private void makeTable() {
		myTable = new MyTable<Borrow>(null);
		String[] columns = { "الرقم", "أسم العميل", "المبلغ", "التاريخ",
				"تم الدفع", "الأمر بالصرف" };

		myTable.setColumnNames(columns);
		myTable.setData(borrows);
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
		MyCellRenderer myCellRenderer = new MyCellRenderer(TableType.BORROW);
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
		borrows = MainWindow.logic.getBorrows();
		System.out.println("borrows size: " + borrows.size());
		if (sumTextField != null) {
			setTextFields();
			myTable.setData(borrows);
			myTable.fireTableStructureChanged();
			myTable.fireTableDataChanged();
			adjustJTableRowSizes(table);
			alignTable();
		} else
			makeTable();
	}
}
