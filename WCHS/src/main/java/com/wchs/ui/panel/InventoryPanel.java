package com.wchs.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import com.wchs.model.Inventory;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {
	private JTable table;
	private MyTable<Inventory> myTable;
	private List<Inventory> inventories;

	public MyTable<Inventory> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Inventory> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Create the panel.
	 */
	public InventoryPanel() {

		inventories = MainWindow.logic.getInventories();
		makeTable();
		intializeUIComponents();
	}

	private void intializeUIComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		add(tablePanel);
	}

	private void makeTable() {
		myTable = new MyTable<Inventory>(null);
		String[] columns = { "التاريخ", "أجمالي رأس مال البضائع",
				"أجمالي ربح البضائع", "أجمالي البضائع المتبقية",
				"أجمالي ربح البضائع المتبقية", "سلفة", "نثريات" };

		myTable.setColumnNames(columns);
		myTable.setData(inventories);
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
		MyCellRenderer myCellRenderer = new MyCellRenderer(TableType.INVENTORY);
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
		inventories = MainWindow.logic.getInventories();
		if (table != null) {
			myTable.setData(inventories);
			myTable.fireTableStructureChanged();
			myTable.fireTableDataChanged();
			adjustJTableRowSizes(table);
			alignTable();
		} else
			makeTable();
	}
}
