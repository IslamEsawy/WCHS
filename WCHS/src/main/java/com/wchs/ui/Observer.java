package com.wchs.ui;

import com.wchs.ui.panel.CustomersPanel;
import com.wchs.ui.panel.ManagementPanel;
import com.wchs.ui.panel.TransactionPanel;

public class Observer {
	private CustomersPanel customersPanel;
	private ManagementPanel managementPanel;
	private TransactionPanel transactionPanel;
	
	public CustomersPanel getCustomersPanel() {
		return customersPanel;
	}
	public void setCustomersPanel(CustomersPanel customersPanel) {
		this.customersPanel = customersPanel;
	}
	public ManagementPanel getManagementPanel() {
		return managementPanel;
	}
	public void setManagementPanel(ManagementPanel managementPanel) {
		this.managementPanel = managementPanel;
	}
	
	public TransactionPanel getTransactionPanel() {
		return transactionPanel;
	}
	public void setTransactionPanel(TransactionPanel transactionPanel) {
		this.transactionPanel = transactionPanel;
	}
	public void update() {
		//MainWindow.logic.loadData();
		managementPanel.getProductsPanel().updateData();
		managementPanel.getBorrowPanel().updateData();
		managementPanel.getMiscPanel().updateData();
		managementPanel.getInventoryPanel().updateData();
		customersPanel.updateData();
		transactionPanel.updateData();
		
	}
	
}
