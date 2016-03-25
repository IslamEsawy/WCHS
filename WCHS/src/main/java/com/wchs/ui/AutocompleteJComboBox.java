package com.wchs.ui;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import com.wchs.model.Customer;
import com.wchs.util.FlexibleCustomerComparator;

/**
 * 
 * JComboBox with an autocomplete drop down menu. This class is hard-coded for
 * String objects, but can be
 * 
 * altered into a generic form to allow for any searchable item.
 * 
 * @author G. Cope
 * 
 *
 */

@SuppressWarnings("rawtypes")
public class AutocompleteJComboBox extends JComboBox {

	static final long serialVersionUID = 4321421L;

	private Searchable<Customer, String> searchable;

	public Searchable<Customer, String> getSearchable() {
		return searchable;
	}

	public void setSearchable(Searchable<Customer, String> searchable) {
		this.searchable = searchable;
	}

	/**
	 * 
	 * Constructs a new object based upon the parameter searchable
	 * 
	 * @param s
	 */

	public AutocompleteJComboBox(Searchable<Customer, String> s) {

		super();

		this.searchable = s;

		setEditable(true);

		Component c = getEditor().getEditorComponent();

		if (c instanceof JTextComponent) {

			final JTextComponent tc = (JTextComponent) c;

			tc.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent arg0) {
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {

					update();

				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {

					update();

				}

				public void update() {

					// perform separately, as listener conflicts between the
					// editing component

					// and JComboBox will result in an IllegalStateException due
					// to editing

					// the component when it is locked.

					SwingUtilities.invokeLater(new Runnable() {

						@SuppressWarnings({ "unchecked" })
						@Override
						public void run() {

							List<Customer> founds = searchable.search(tc
									.getText());

							Set<Customer> foundSet = new HashSet<Customer>();

							for (Customer s : founds) {
								foundSet.add(s);
							}
							FlexibleCustomerComparator comparator = new FlexibleCustomerComparator();
							Collections.sort(founds, comparator);// sort
																	// alphabetically

							setEditable(false);

							removeAllItems();

							// if founds contains the search text, then only add
							// once.
							Customer c = new Customer();
							c.setName(tc.getText());
							if (!foundSet.contains(c)) {
								addItem(c);
							}

							for (Customer s : founds) {
								addItem(s);
							}
							setEditable(true);
							setPopupVisible(true);
							requestFocusInWindow();
						}

					});

				}

			});

			// When the text component changes, focus is gained

			// and the menu disappears. To account for this, whenever the focus

			// is gained by the JTextComponent and it has searchable values, we
			// show the popup.

			tc.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {

					if (tc.getText().length() > 0) {

						setPopupVisible(true);

					}

				}

				@Override
				public void focusLost(FocusEvent arg0) {

				}

			});

		} else {

			throw new IllegalStateException(
					"Editing component is not a JTextComponent!");

		}

	}

}
