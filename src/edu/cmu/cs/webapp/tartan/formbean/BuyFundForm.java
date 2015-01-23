package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean{
	
	String buyAmount;
	private String fundId;

	public String getFundId() {
		return fundId;
	}

	public long getIdAsLong() {
		try {
			return Long.parseLong(fundId);
		}catch(NumberFormatException e){
			return (long)-1;
		}
		
	}

	public void setFundId(String id) {
		this.fundId = id;
	}
	public String getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(String buyAmount) {
		this.buyAmount = trimAndConvert (buyAmount,"<>\"");
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (buyAmount == null || buyAmount.length() == 0) {
			errors.add("Amount is required");
		}
		if (fundId == null || fundId.length() == 0) {
			errors.add("Id is required");
			return errors;
		}
		
		

		try {
			Long.parseLong(fundId);
		} catch (NumberFormatException e) {
			errors.add("Id is not an Long integer");
		}
		try{
			Long.parseLong(buyAmount);
		}catch(NumberFormatException e){
			errors.add("You can only buy integer amount.");
		}
		return errors;
	}
	
}
