package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FundForm extends FormBean {
		private String ticker;
		private String action;
		
		public String getTicker() {
			return ticker;
		}
		
		public String getAction() {
			return action;
		}


		public void setTicker(String t) {
			ticker = t;
		}
		
		public void setAction(String a) {
			action = a;
		}


		public List<String> getValidationErrors() {
			List<String> errors = new ArrayList<String>();

			if (ticker == null || ticker.length() == 0)
				errors.add("Please enter the ticker number");
			
			if (action == null || action.length() == 0) {
				errors.add("Action is required");
			}
			
			if (errors.size() > 0)
				return errors;
			
			if (ticker.matches(".*[<>\"].*"))
				errors.add("User Name may not contain angle brackets or quotes");

			return errors;
		}
	

}
