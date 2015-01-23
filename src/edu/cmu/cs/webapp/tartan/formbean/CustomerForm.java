package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerForm extends FormBean {
	private String customerId;
	private String action;
	
	public String getCustomerId() {
		return customerId;
	}
	
	public String getAction() {
		return action;
	}


	public void setCustomerId(String c) {
		customerId = c;
	}
	
	public void setAction(String a) {
		action = a;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (customerId == null || customerId.length() == 0)
			errors.add("Please enter the customerId");
		
		if (action == null || action.length() == 0) {
			errors.add("Action is required");
		}
		
		if (errors.size() > 0)
			return errors;
		
		if (customerId.matches(".*[<>\"].*"))
			errors.add("User Name may not contain angle brackets or quotes");

		return errors;
	}
}
