package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class IdForm extends FormBean {
	private String id;

	public String getId() {
		return id;
	}

	public long getIdAsLong() {
		try {
			return Long.parseLong(id);
		}catch(NumberFormatException e){
			return (long)-1;
		}
		
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (id == null || id.length() == 0) {
			errors.add("Id is required");
			return errors;
		}
		
		

		try {
			Long.parseLong(id);
		} catch (NumberFormatException e) {
			errors.add("Id is not an Long integer");
		}

		return errors;
	}
}
