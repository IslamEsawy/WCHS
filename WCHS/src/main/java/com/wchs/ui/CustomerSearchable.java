package com.wchs.ui;

import java.util.ArrayList;
import java.util.List;

import com.wchs.model.Customer;

public class CustomerSearchable implements Searchable<Customer, String> {

	private List<Customer> terms = new ArrayList<Customer>();

	/**
	 * 
	 * Constructs a new object based upon the parameter terms.
	 * 
	 * @param terms
	 *            The inventory of terms to search.
	 */

	public CustomerSearchable(List<Customer> terms) {

		this.terms.addAll(terms);

	}

	@Override
	public List<Customer> search(String value) {

		List<Customer> founds = new ArrayList<Customer>();

		for (Customer s : terms) {

			if (s.getName().indexOf(value) == 0) {

				founds.add(s);

			}

		}

		return founds;

	}

	public List<Customer> getTerms() {
		return terms;
	}

	public void setTerms(List<Customer> terms) {
		this.terms = terms;
	}

}
