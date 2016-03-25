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

import com.wchs.model.Miscellaneous;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.util.ResultStatus;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class MiscPanel extends JPanel {

	private JTextField sumTextField;
	private List<Miscellaneous> miscellaneouss;
	private JTable table;
	private MyTable<Miscellaneous> myTable;

	public MyTable<Miscellaneous> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Miscellaneous> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Create the panel.
	 */
	public MiscPanel() {
		setLayout(new BorderLayout(0, 0));

		updateData();
		intializeUIComponents();
		setTextFields();
	}

	private void setTextFields() {
		Double sumOfMiscellaneouss = getSumOfMiscellaneouss();
		sumTextField.setText(sumOfMiscellaneouss + "");
	}

	private Double getSumOfMiscellaneouss() {
		Double sum = 0.0;
		for (Miscellaneous miscellaneous : miscellaneouss) {
			sum += miscellaneous.getPrice();
		}
		return sum;
	}

	private void intializeUIComponents() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		add(optionsPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_optionsPanel = new GridBagLayout();
		gbl_optionsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		optionsPanel.setLayout(gbl_optionsPanel);

		JButton addMiscButton = new JButton("إضافة");
		addMiscButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField name = new JTextField();
				JTextField price = new JTextField();
				JTextField signature = new JTextField();
				name.requestFocusInWindow();
				JPanel myPanel = new JPanel();
				myPanel.setBounds(100, 100, 100, 100);
				myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

				myPanel.add(new JLabel("الأسم: "));
				myPanel.add(name);

				myPanel.add(new JLabel("السعر: "));
				myPanel.add(price);

				myPanel.add(new JLabel("الأمر بالصرف: "));
				myPanel.add(signature);

				int result = JOptionPane.showConfirmDialog(MiscPanel.this, myPanel,
						"إضافة", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					miscellaneouss.add(MainWindow.logic.addMisc(name.getText()
							.toString(), Double.parseDouble(price.getText()
							.toString()), signature.getText().toString()));
					MainWindow.logic.setMiscellaneous(miscellaneouss);
					MainWindow.observer.update();
				}
			}
		});
		GridBagConstraints gbc_addMiscButton = new GridBagConstraints();
		gbc_addMiscButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addMiscButton.insets = new Insets(0, 0, 5, 5);
		gbc_addMiscButton.gridx = 1;
		gbc_addMiscButton.gridy = 1;
		optionsPanel.add(addMiscButton, gbc_addMiscButton);

		JButton makePaidButton = new JButton("حذف");
		makePaidButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					int respone = JOptionPane.showConfirmDialog(MiscPanel.this,
							"هل أنت متأكد؟", "تأكيد",
							JOptionPane.YES_NO_OPTION);
					switch (respone) {
					case 0: {
						String[] id = new String[table.getSelectedRows().length];
						int g = 0;
						for (int i : table.getSelectedRows()) {
							id[g] = table.getValueAt(i, 0).toString();
							g++;
						}
						ResultStatus reS = MainWindow.logic.deleteMiscs(id);
						if (ResultStatus.SUCCESS.equals(reS)) {
							for (int i = 0; i < id.length; i++)
								myTable.deleteRow(id[i]);
							MainWindow.logic.setMiscellaneous(myTable.getData());
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
		GridBagConstraints gbc_makePaidButton = new GridBagConstraints();
		gbc_makePaidButton.insets = new Insets(0, 0, 0, 5);
		gbc_makePaidButton.fill = GridBagConstraints.BOTH;
		gbc_makePaidButton.gridx = 1;
		gbc_makePaidButton.gridy = 2;
		optionsPanel.add(makePaidButton, gbc_makePaidButton);

		sumTextField = new JTextField();
		sumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		sumTextField.setEditable(false);
		GridBagConstraints gbc_moneyToReturnText = new GridBagConstraints();
		gbc_moneyToReturnText.insets = new Insets(0, 0, 0, 5);
		gbc_moneyToReturnText.fill = GridBagConstraints.VERTICAL;
		gbc_moneyToReturnText.gridx = 8;
		gbc_moneyToReturnText.gridy = 2;
		optionsPanel.add(sumTextField, gbc_moneyToReturnText);
		sumTextField.setColumns(10);

		JLabel sumLabel = new JLabel("الأجمالي");
		GridBagConstraints gbc_sumLabel = new GridBagConstraints();
		gbc_sumLabel.insets = new Insets(0, 0, 0, 5);
		gbc_sumLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_sumLabel.weighty = 1;
		gbc_sumLabel.gridx = 9;
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
		myTable = new MyTable<Miscellaneous>(null);
		String[] columns = { "الرقم", "أسم الصنف", "المبلغ", "التاريخ",
				"الأمر بالصرف" };

		myTable.setColumnNames(columns);
		myTable.setData(miscellaneouss);
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
		MyCellRenderer myCellRenderer = new MyCellRenderer(
				TableType.MISCELLANEOUS);
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
		miscellaneouss = MainWindow.logic.getMiscellaneous();
		System.out.println("miscellaneouss size: " + miscellaneouss.size());
		if (sumTextField != null) {
			setTextFields();
			myTable.setData(miscellaneouss);
			myTable.fireTableStructureChanged();
			myTable.fireTableDataChanged();
			adjustJTableRowSizes(table);
			alignTable();
		} else
			makeTable();
	}
}
