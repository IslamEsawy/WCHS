package com.wchs.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

import com.wchs.util.TableType;

public class MyCellRenderer extends javax.swing.table.DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private TableType tableType;

	public MyCellRenderer(TableType tableType) {
		this.tableType = tableType;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component cell = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		table.setSelectionBackground(Color.blue);

		if (tableType == TableType.CUSTOMER) {
			Double mTR = 0.0;
			try {
				mTR = Double.parseDouble(table.getValueAt(row, 3).toString());
				if (mTR >= 346) {
					setForeground(Color.black);
					setBackground(Color.red);
				} else {
					setBackground(Color.green);
					setForeground(Color.black);
				}
			} catch (NumberFormatException e) {
				setBackground(Color.white);
				setForeground(Color.black);
			}
		} else if (tableType == TableType.BORROW) {
			String mTR = "";
			try {
				mTR = table.getValueAt(row, 4).toString();
				if (mTR.equals("YES")) {
					setForeground(Color.black);
					setBackground(Color.green);
				} else {
					setBackground(Color.white);
					setForeground(Color.black);
				}
			} catch (NumberFormatException e) {
				setBackground(Color.white);
				setForeground(Color.black);
			}
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}

		if (isSelected) {
			setBackground(Color.cyan);
			setForeground(Color.black);
		}

		return cell;
	}

}