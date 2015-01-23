package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean {
	private String shares;

	public String getShares() {
		return shares;
	}

	public void setShares(String s) {
		shares = s;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (shares == null || shares.length() == 0)
			errors.add("The shares you'd like to sell should not be zero.");


		if (errors.size() > 0)
			return errors;

		if (shares.matches(".*[<>\"].*"))
			errors.add("ticker may not contain angle brackets or quotes");
		
		return errors;
	}
}
