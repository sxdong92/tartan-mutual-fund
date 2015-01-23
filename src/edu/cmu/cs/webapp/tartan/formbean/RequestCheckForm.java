package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String requestAmount;

	public String getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(String a) {
		requestAmount = a;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (requestAmount == null || requestAmount.length() == 0)
			errors.add("Please enter the amount you want to withdraw");

		if (requestAmount.equals("0"))
			errors.add("The amount you withdraw should not be zero.");

		/*
		 * double requestAmount2 = Double.parseDouble(requestAmount); long
		 * requestAmount3 = (long) requestAmount2; if (requestAmount2 -
		 * requestAmount3 < 0.001)
		 * errors.add("The decimal digit number cannot excess 3. ");
		 */

		if (errors.size() > 0)
			return errors;

		if (requestAmount.matches(".*[<>\"].*"))
			errors.add("User Name may not contain angle brackets or quotes");

		return errors;
	}
}
