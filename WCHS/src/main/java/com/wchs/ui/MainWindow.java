package com.wchs.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import com.wchs.controller.Logic;
import com.wchs.ui.frame.InventoryFrame;
import com.wchs.ui.panel.CustomersPanel;
import com.wchs.ui.panel.ManagementPanel;
import com.wchs.ui.panel.TransactionPanel;

public class MainWindow {
	public static Logic logic;
	private JFrame frame;
	private CustomersPanel customersPanel;
	private ManagementPanel managementPanel;
	private TransactionPanel transactionPanel;
	private CardLayout cardLayout;
	private JPanel division;
	public static Observer observer = new Observer();
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem inventoryMenuItem;
	private JMenuItem menuItem;

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public MainWindow() {
		logic = new Logic();
		final JDialog dlgProgress = new JDialog(frame, "", true);
		dlgProgress.setSize(200, 200);
		dlgProgress.setLocationRelativeTo(null);
		JProgressBar pbProgress = new JProgressBar(0, 100);
		pbProgress.setIndeterminate(true);
		JLabel lblStatus = new JLabel("جاري التحميل ...");
		dlgProgress.getContentPane().add(BorderLayout.NORTH, lblStatus);
		dlgProgress.getContentPane().add(BorderLayout.CENTER, pbProgress);
		dlgProgress.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dlgProgress.setSize(300, 90);
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				logic.loadData();
				return null;
			}

			@Override
			protected void done() {
				dlgProgress.dispose();// close the modal dialog
				initialize();
			}

		};
		sw.execute(); // this will start the processing on a separate
		dlgProgress.setVisible(true); // this will block user input as/
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("كانتين مستشفى غرب القاهرة");
		frame.setBounds(100, 100, 644, 678);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		customersPanel = new CustomersPanel();
		managementPanel = new ManagementPanel();
		transactionPanel = new TransactionPanel();

		observer.setCustomersPanel(customersPanel);
		observer.setManagementPanel(managementPanel);
		observer.setTransactionPanel(transactionPanel);

		JPanel divisions = new JPanel();
		frame.getContentPane().add(divisions, BorderLayout.NORTH);

		JButton customerDivision = new JButton("العملاء");
		customerDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(division, "Card1");
			}
		});
		divisions.add(customerDivision);

		JButton managementDivision = new JButton("الإدارة");
		managementDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(division, "Card2");
			}
		});
		divisions.add(managementDivision);

		JButton transactionDivision = new JButton("عملية بيع");
		transactionDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(division, "Card3");
			}
		});
		divisions.add(transactionDivision);

		division = new JPanel();
		frame.getContentPane().add(division, BorderLayout.CENTER);
		cardLayout = new CardLayout();
		division.setLayout(cardLayout);
		division.add(customersPanel, "Card1");
		division.add(managementPanel, "Card2");
		division.add(transactionPanel, "Card3");

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnNewMenu = new JMenu("ملف");
		menuBar.add(mnNewMenu);

		inventoryMenuItem = new JMenuItem("عمل جرد");
		inventoryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryFrame.main(null);
			}
		});
		mnNewMenu.add(inventoryMenuItem);

		menuItem = new JMenuItem("طباعة");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel card = null;

				for (Component comp : division.getComponents()) {
					if (comp.isVisible() == true) {
						card = (JPanel) comp;
						if (card instanceof CustomersPanel) {
							customersPanel.print();
						} else if (card instanceof ManagementPanel) {
							managementPanel.print();
						}
					}
				}
			}
		});
		mnNewMenu.add(menuItem);
	}

}
