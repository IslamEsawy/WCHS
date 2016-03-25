package com.wchs.ui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
@SuppressWarnings("serial")
public class ManagementPanel extends JPanel {

	private CategoryPanel categoryPanel;
	private ProductsPanel productsPanel;
	private BorrowPanel borrowPanel;
	private MiscPanel miscPanel;
	private InventoryPanel inventoryPanel;
	/**
	 * Create the panel.
	 */
	public ManagementPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("إدارة الكانتين");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel tablePanel = new JPanel();
		add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tablePanel.add(tabbedPane);
		productsPanel = new ProductsPanel();
		borrowPanel = new BorrowPanel();
		miscPanel = new MiscPanel();
		categoryPanel = new CategoryPanel();
		inventoryPanel = new InventoryPanel();
		tabbedPane.addTab("الأصناف", null, categoryPanel, null);
		tabbedPane.addTab("الممنتجات", null, productsPanel, null);
		tabbedPane.addTab("سلفة", null, borrowPanel, null);
		tabbedPane.addTab("نثريات", null, miscPanel, null);
		tabbedPane.addTab("جميع الجرود", null, inventoryPanel, null);

	}
	public ProductsPanel getProductsPanel() {
		return productsPanel;
	}
	public void setProductsPanel(ProductsPanel productsPanel) {
		this.productsPanel = productsPanel;
	}
	public BorrowPanel getBorrowPanel() {
		return borrowPanel;
	}
	public void setBorrowPanel(BorrowPanel borrowPanel) {
		this.borrowPanel = borrowPanel;
	}
	public MiscPanel getMiscPanel() {
		return miscPanel;
	}
	public void setMiscPanel(MiscPanel miscPanel) {
		this.miscPanel = miscPanel;
	}
	public CategoryPanel getCategoryPanel() {
		return categoryPanel;
	}
	public void setCategoryPanel(CategoryPanel categoryPanel) {
		this.categoryPanel = categoryPanel;
	}
	public InventoryPanel getInventoryPanel() {
		return inventoryPanel;
	}
	public void setInventoryPanel(InventoryPanel inventoryPanel) {
		this.inventoryPanel = inventoryPanel;
	}

	
}
