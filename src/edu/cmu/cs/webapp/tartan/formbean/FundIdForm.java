package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FundIdForm extends FormBean{
	private String fundId;

	public String getId() {
		return fundId;
	}

	public long getIdAsLong() {
		try {
			return Long.parseLong(fundId);
		}catch(NumberFormatException e){
			return (long)-1;
		}
		
	}

	public void setId(String id) {
		this.fundId = id;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundId == null || fundId.length() == 0) {
			errors.add("Id is required");
			return errors;
		}
		
		

		try {
			Long.parseLong(fundId);
		} catch (NumberFormatException e) {
			errors.add("Id is not an Long integer");
		}

		return errors;
	}
}
