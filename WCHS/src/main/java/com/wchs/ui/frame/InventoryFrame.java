package com.wchs.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import com.wchs.model.Category;
import com.wchs.model.Inventory;
import com.wchs.ui.MainWindow;
import com.wchs.ui.MyCellRenderer;
import com.wchs.ui.MyTable;
import com.wchs.util.TableType;

@SuppressWarnings("serial")
public class InventoryFrame extends JFrame {

	private JPanel contentPane;
	private Inventory inventory;
	private JTable table;
	private MyTable<Category> myTable;
	private List<Category> categories;
	private JTextField capitalSumTextField;
	private JTextField ProfitSumTextField;
	private JTextField restOfGoodsWithOutProfitTextField;
	private JTextField restOfGoodsWithProfitTextField;
	private JTextField restGoodsWithSumTextField;
	private JLabel lblNewLabel;
	private JTextField totalBorrowTextField;
	private JTextField totalMiscTextField;

	public MyTable<Category> getMyTable() {
		return myTable;
	}

	public JTable getTable() {
		return table;
	}

	public void setMyTable(MyTable<Category> myTable) {
		this.myTable = myTable;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryFrame frame = new InventoryFrame();
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
	public InventoryFrame() {
		setTitle("جرد");
		inventory = MainWindow.logic.doInventory();
		categories = MainWindow.logic.loadCategories();
		MainWindow.logic.setCategories(categories);
		makeTable();
		intializeUIComponents();
		setUIDate();
	}

	private void setUIDate() {
		lblNewLabel.setText(inventory.getMonth());
		capitalSumTextField.setText(inventory.getTotalCategoriesCapital()
				.toString());
		ProfitSumTextField.setText(inventory.getTotalCategoriesProfit()
				.toString());
		restOfGoodsWithOutProfitTextField.setText(inventory
				.getRestOfGoodsCapital().toString());
		restOfGoodsWithProfitTextField.setText(inventory.getRestOfGoodsProfit()
				.toString());
		restGoodsWithSumTextField.setText(String.valueOf(limitPrecision(
				(inventory.getRestOfGoodsProfit() + inventory
						.getRestOfGoodsCapital()), 3)));

		totalBorrowTextField.setText(MainWindow.logic.getTotalBorrows()
				.toString());
		totalMiscTextField.setText(MainWindow.logic.getTotalMisc().toString());
	}

	public Double limitPrecision(Double dblAsString, int maxDigitsAfterDecimal) {
		int multiplier = (int) Math.pow(10, maxDigitsAfterDecimal);
		Double truncated = (double) ((long) (dblAsString * multiplier))
				/ multiplier;
		System.out.println(dblAsString + " ==> " + truncated);
		return truncated;
	}

	private void intializeUIComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 534);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("ملف");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("طباعة");

		menu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel infoPanel = new JPanel();
		contentPane.add(infoPanel, BorderLayout.EAST);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0,
				0.0, 0.0, 0.0 };
		gbl_infoPanel.columnWidths = new int[] { 20, 0 };
		infoPanel.setLayout(gbl_infoPanel);

		capitalSumTextField = new JTextField();
		capitalSumTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		capitalSumTextField.setEditable(false);
		GridBagConstraints gbc_capitalSumTextField = new GridBagConstraints();
		gbc_capitalSumTextField.gridwidth = 3;
		gbc_capitalSumTextField.insets = new Insets(0, 0, 5, 5);
		gbc_capitalSumTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_capitalSumTextField.gridx = 1;
		gbc_capitalSumTextField.gridy = 0;
		infoPanel.add(capitalSumTextField, gbc_capitalSumTextField);
		capitalSumTextField.setColumns(10);

		JLabel capitalSumLabel = new JLabel("أجمالي رأس المال");
		capitalSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_capitalSumLabel = new GridBagConstraints();
		gbc_capitalSumLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_capitalSumLabel.insets = new Insets(0, 0, 5, 0);
		gbc_capitalSumLabel.gridwidth = 4;
		gbc_capitalSumLabel.weightx = 1;
		gbc_capitalSumLabel.gridx = 4;
		gbc_capitalSumLabel.gridy = 0;
		infoPanel.add(capitalSumLabel, gbc_capitalSumLabel);

		ProfitSumTextField = new JTextField();
		ProfitSumTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		ProfitSumTextField.setEditable(false);
		GridBagConstraints gbc_ProfitSumTextField = new GridBagConstraints();
		gbc_ProfitSumTextField.gridwidth = 3;
		gbc_ProfitSumTextField.insets = new Insets(0, 0, 5, 5);
		gbc_ProfitSumTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ProfitSumTextField.gridx = 1;
		gbc_ProfitSumTextField.gridy = 1;
		infoPanel.add(ProfitSumTextField, gbc_ProfitSumTextField);
		ProfitSumTextField.setColumns(10);
		JLabel profitSumLabel = new JLabel("أجمالي الربح");
		profitSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_profitSumLabel = new GridBagConstraints();
		gbc_profitSumLabel.insets = new Insets(0, 0, 5, 0);
		gbc_profitSumLabel.gridwidth = 4;
		gbc_profitSumLabel.weightx = 1;
		gbc_profitSumLabel.fill = GridBagConstraints.BOTH;
		gbc_profitSumLabel.gridx = 4;
		gbc_profitSumLabel.gridy = 1;
		infoPanel.add(profitSumLabel, gbc_profitSumLabel);

		restOfGoodsWithOutProfitTextField = new JTextField();
		restOfGoodsWithOutProfitTextField
				.setHorizontalAlignment(SwingConstants.RIGHT);
		restOfGoodsWithOutProfitTextField.setEditable(false);
		GridBagConstraints gbc_restOfGoodsWithOutProfitTextField = new GridBagConstraints();
		gbc_restOfGoodsWithOutProfitTextField.gridwidth = 3;
		gbc_restOfGoodsWithOutProfitTextField.insets = new Insets(0, 0, 5, 5);
		gbc_restOfGoodsWithOutProfitTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_restOfGoodsWithOutProfitTextField.gridx = 1;
		gbc_restOfGoodsWithOutProfitTextField.gridy = 2;
		infoPanel.add(restOfGoodsWithOutProfitTextField,
				gbc_restOfGoodsWithOutProfitTextField);
		restOfGoodsWithOutProfitTextField.setColumns(10);
		JLabel restGoodsWithOutProfitLabel = new JLabel("بضاعة متبقية");
		restGoodsWithOutProfitLabel
				.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_restGoodsWithOutProfitLabel = new GridBagConstraints();
		gbc_restGoodsWithOutProfitLabel.insets = new Insets(0, 0, 5, 0);
		gbc_restGoodsWithOutProfitLabel.gridwidth = 4;
		gbc_restGoodsWithOutProfitLabel.weightx = 1;
		gbc_restGoodsWithOutProfitLabel.fill = GridBagConstraints.BOTH;
		gbc_restGoodsWithOutProfitLabel.gridx = 4;
		gbc_restGoodsWithOutProfitLabel.gridy = 2;
		infoPanel.add(restGoodsWithOutProfitLabel,
				gbc_restGoodsWithOutProfitLabel);

		restOfGoodsWithProfitTextField = new JTextField();
		restOfGoodsWithProfitTextField
				.setHorizontalAlignment(SwingConstants.RIGHT);
		restOfGoodsWithProfitTextField.setEditable(false);
		GridBagConstraints gbc_restOfGoodsWithProfitTextField = new GridBagConstraints();
		gbc_restOfGoodsWithProfitTextField.gridwidth = 3;
		gbc_restOfGoodsWithProfitTextField.insets = new Insets(0, 0, 5, 5);
		gbc_restOfGoodsWithProfitTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_restOfGoodsWithProfitTextField.gridx = 1;
		gbc_restOfGoodsWithProfitTextField.gridy = 3;
		infoPanel.add(restOfGoodsWithProfitTextField,
				gbc_restOfGoodsWithProfitTextField);
		restOfGoodsWithProfitTextField.setColumns(10);
		JLabel restGoodsWithProfitLabel = new JLabel("ربح البضائع المتبقية");
		restGoodsWithProfitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_restGoodsWithProfitLabel = new GridBagConstraints();
		gbc_restGoodsWithProfitLabel.insets = new Insets(0, 0, 5, 0);
		gbc_restGoodsWithProfitLabel.gridwidth = 4;
		gbc_restGoodsWithProfitLabel.weightx = 1;
		gbc_restGoodsWithProfitLabel.fill = GridBagConstraints.BOTH;
		gbc_restGoodsWithProfitLabel.gridx = 4;
		gbc_restGoodsWithProfitLabel.gridy = 3;
		infoPanel.add(restGoodsWithProfitLabel, gbc_restGoodsWithProfitLabel);

		restGoodsWithSumTextField = new JTextField();
		restGoodsWithSumTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		restGoodsWithSumTextField.setEditable(false);
		GridBagConstraints gbc_restGoodsWithSumTextField = new GridBagConstraints();
		gbc_restGoodsWithSumTextField.gridwidth = 3;
		gbc_restGoodsWithSumTextField.insets = new Insets(0, 0, 5, 5);
		gbc_restGoodsWithSumTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_restGoodsWithSumTextField.gridx = 1;
		gbc_restGoodsWithSumTextField.gridy = 4;
		infoPanel.add(restGoodsWithSumTextField, gbc_restGoodsWithSumTextField);
		restGoodsWithSumTextField.setColumns(10);
		JLabel restGoodsWithSumLabel = new JLabel("أجمالي البضائع المتبقية");
		restGoodsWithSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_restGoodsWithSumLabel = new GridBagConstraints();
		gbc_restGoodsWithSumLabel.insets = new Insets(0, 0, 5, 0);
		gbc_restGoodsWithSumLabel.gridwidth = 4;
		gbc_restGoodsWithSumLabel.weightx = 1;
		gbc_restGoodsWithSumLabel.fill = GridBagConstraints.BOTH;
		gbc_restGoodsWithSumLabel.gridx = 4;
		gbc_restGoodsWithSumLabel.gridy = 4;
		infoPanel.add(restGoodsWithSumLabel, gbc_restGoodsWithSumLabel);

		totalBorrowTextField = new JTextField();
		totalBorrowTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalBorrowTextField.setEditable(false);
		GridBagConstraints gbc_totalBorrowTextField = new GridBagConstraints();
		gbc_totalBorrowTextField.gridwidth = 2;
		gbc_totalBorrowTextField.insets = new Insets(0, 0, 5, 5);
		gbc_totalBorrowTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalBorrowTextField.gridx = 1;
		gbc_totalBorrowTextField.gridy = 5;
		infoPanel.add(totalBorrowTextField, gbc_totalBorrowTextField);
		totalBorrowTextField.setColumns(10);

		JLabel totalBorrowLabel = new JLabel("أجمالي السلفة");
		totalBorrowLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_totalBorrowLabel = new GridBagConstraints();
		gbc_totalBorrowLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalBorrowLabel.gridwidth = 4;
		gbc_totalBorrowLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalBorrowLabel.gridx = 4;
		gbc_totalBorrowLabel.gridy = 5;
		infoPanel.add(totalBorrowLabel, gbc_totalBorrowLabel);

		totalMiscTextField = new JTextField();
		totalMiscTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalMiscTextField.setEditable(false);
		GridBagConstraints gbc_totalMiscTextField = new GridBagConstraints();
		gbc_totalMiscTextField.gridwidth = 2;
		gbc_totalMiscTextField.insets = new Insets(0, 0, 0, 5);
		gbc_totalMiscTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalMiscTextField.gridx = 1;
		gbc_totalMiscTextField.gridy = 6;
		infoPanel.add(totalMiscTextField, gbc_totalMiscTextField);
		totalMiscTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("أجمالي النثريات");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 6;
		infoPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JPanel actionPanel = new JPanel();
		contentPane.add(actionPanel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("حفظ الجرد وإعادة الضبط");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.logic.addInventory(inventory);
				MainWindow.logic.addInventoryToList(inventory);
				MainWindow.observer.update();
				dispose();
			}
		});
		actionPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("إلغاء");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		actionPanel.add(btnNewButton_1);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));
		JScrollPane jScrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(jScrollPane);
		contentPane.add(tablePanel, BorderLayout.CENTER);

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					printComponentToFile(contentPane, true);
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void makeTable() {
		myTable = new MyTable<Category>(null);

		String[] columns = { "الأسم", "رأس المال", "الربح" };

		myTable.setColumnNames(columns);
		myTable.setData(categories);
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
		MyCellRenderer myCellRenderer = new MyCellRenderer(TableType.CATEGORY);
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

	public void printComponentToFile(Component comp, boolean fill)
			throws PrinterException {
		Paper paper = new Paper();
		paper.setSize(8.3 * 72, 11.7 * 72);
		paper.setImageableArea(18, 18, 559, 783);

		PageFormat pf = new PageFormat();
		pf.setPaper(paper);
		pf.setOrientation(PageFormat.LANDSCAPE);

		BufferedImage img = new BufferedImage((int) Math.round(pf.getWidth()),
				(int) Math.round(pf.getHeight()), BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle(0, 0, img.getWidth(), img.getHeight()));
		ComponentPrinter cp = new ComponentPrinter(comp, fill);
		try {
			cp.print(g2d, pf, 0);
		} finally {
			g2d.dispose();
		}

		try {
			ImageIO.write(img, "png", new File("Page-" + (fill ? "Filled" : "")
					+ ".png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static class ComponentPrinter implements Printable {

		private Component comp;
		private boolean fill;

		public ComponentPrinter(Component comp, boolean fill) {
			this.comp = comp;
			this.fill = fill;
		}

		@Override
		public int print(Graphics g, PageFormat format, int page_index)
				throws PrinterException {

			if (page_index > 0) {
				return Printable.NO_SUCH_PAGE;
			}

			Graphics2D g2 = (Graphics2D) g;
			g2.translate(format.getImageableX(), format.getImageableY());

			double width = (int) Math.floor(format.getImageableWidth());
			double height = (int) Math.floor(format.getImageableHeight());

			if (!fill) {

				width = Math.min(width, comp.getPreferredSize().width);
				height = Math.min(height, comp.getPreferredSize().height);

			}

			/*
			 * comp.setBounds(0, 0, (int) Math.floor(width), (int)
			 * Math.floor(height)); if (comp.getParent() == null) {
			 * comp.addNotify(); } comp.validate(); comp.doLayout();
			 */
			comp.printAll(g2);
			// if (comp.getParent() != null) {
			//comp.removeNotify();
			// }

			return Printable.PAGE_EXISTS;
		}
	}
}
