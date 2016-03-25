package com.wchs.ui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.wchs.model.Category;
import com.wchs.ui.MainWindow;
import com.wchs.util.ResultStatus;

@SuppressWarnings("serial")
public class CategoryPanel extends JPanel {

	private List<Category> categories;
	private JList<Category> categoriesList;

	/**
	 * Create the panel.
	 */
	public CategoryPanel() {
		setLayout(new BorderLayout(0, 0));

		updateData();
		intializeUIComponents();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void intializeUIComponents() {

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout(0, 0));
		add(listPanel, BorderLayout.CENTER);

		JPanel optionsPanel = new JPanel();
		listPanel.add(optionsPanel, BorderLayout.EAST);
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

		JButton addCategoryButton = new JButton("إضافة");
		addCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField catName = new JTextField();
				catName.requestFocusInWindow();
				JPanel myPanel = new JPanel();
				myPanel.setBounds(100, 100, 100, 100);
				myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

				myPanel.add(new JLabel("الإسم: "));
				myPanel.add(catName);

				int result = JOptionPane.showConfirmDialog(CategoryPanel.this, myPanel,
						"إضافة صنف", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					Category newC = MainWindow.logic.addCategory(catName
							.getText().toString());
					if (newC != null) {
						categories.add(newC);
						categoriesList.setListData(categories
								.toArray(new Category[0]));
						MainWindow.logic.setCategories(categories);
						MainWindow.observer.update();
					}
				}
			}
		});
		optionsPanel.add(addCategoryButton);

		JButton removeCategoryButton = new JButton("حذف ");
		removeCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (categoriesList.getSelectedIndex() != -1) {
					int respone = JOptionPane
							.showConfirmDialog(
									CategoryPanel.this,
									"سيتم مسح كل المنتجات المتعلقة بهذه الأصناف المختارة, هل أنت متأكد؟",
									"تأكيد", JOptionPane.YES_NO_OPTION);
					switch (respone) {
					case 0: {
						List<Category> selectedCat = categoriesList
								.getSelectedValuesList();
						System.out.println(selectedCat);
						ResultStatus reS = MainWindow.logic
								.deleteCategories(selectedCat);
						if (ResultStatus.SUCCESS.equals(reS)) {
							categories.removeAll(selectedCat);
							categoriesList.setListData(categories
									.toArray(new Category[0]));
							MainWindow.logic.setCategories(categories);
							MainWindow.logic
									.deleteCategoryProducts(selectedCat);
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
		optionsPanel.add(removeCategoryButton);

		categoriesList = new JList(categories.toArray());
		JScrollPane scrollPane = new JScrollPane(categoriesList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listPanel.add(scrollPane, BorderLayout.CENTER);
	}

	public void updateData() {
		categories = MainWindow.logic.getCategories();
		System.out.println("categories size: " + categories.size());
		if (categoriesList != null) {
			categoriesList.setListData(categories.toArray(new Category[0]));
		}
	}
}
