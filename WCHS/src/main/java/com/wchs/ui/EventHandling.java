package com.wchs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.wchs.model.Category;
import com.wchs.model.Product;
import com.wchs.ui.panel.TransactionPanel;

public class EventHandling implements ActionListener {

	private TransactionPanel transactionPanel;

	public EventHandling(TransactionPanel transactionPanel) {
		this.transactionPanel = transactionPanel;
	}

	public void actionPerformed(ActionEvent event) {

		JButton nutton = (JButton) event.getSource();
		if (nutton.getPreferredSize().getWidth() == 120) {
			Category selectedCategory = MainWindow.logic.getCategory(nutton
					.getText().toString());
			transactionPanel.fillProducts(MainWindow.logic
					.getCategoryProducts(selectedCategory));
		} else if (nutton.getPreferredSize().getWidth() == 80) {
			String barcode = nutton.getText().toString().split("-")[0];
			int size = barcode.length();
			barcode = barcode.substring(6, size - 5);
			System.out.println("Barcode: " + barcode);
			Product product = MainWindow.logic.getProductWithBarcode(barcode);
			transactionPanel.createTransaction(product);
		}

	}

}