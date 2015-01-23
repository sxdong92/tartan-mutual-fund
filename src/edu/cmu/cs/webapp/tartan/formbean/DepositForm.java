package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;
public class DepositForm extends FormBean{
	String amount;
	String customerId;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = trimAndConvert (amount,"<>\"");
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = trimAndConvert (customerId,"<>\"");
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		}

		if (customerId == null || customerId.length() == 0) {
			errors.add("Last Name is required");
		}
		try{
			Long.parseLong(amount);
		}catch(NumberFormatException e){
			errors.add("Your deposit must be an integer number.");
		}
		return errors;
	}

	
}
