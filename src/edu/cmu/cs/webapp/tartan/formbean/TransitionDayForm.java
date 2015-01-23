package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransitionDayForm extends FormBean{
	private String    date;
	private String[]  closingPrice;
	private String[]  allFundId;
	private String    button;
	
	public String    getDate()               { return date;              }
	public String    getButton()             { return button;            }
	public String[]  getClosingPrice()       { return closingPrice;      }
	public String[]  getAllFundId()          { return allFundId;         }
	
	public boolean   isPresent()     { return button != null; }
	
	public void      setDate(String s)               { date = s;              }
	public void      setButton(String s)             { button = s;            }
	public void      setClosingPrice(String[] s)     { closingPrice = s;      }
	public void      setAllFundId(String[] s)        { allFundId = s;         }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (date == null ) errors.add("Date is required");
		if (button == null) errors.add("Button is required");
		
		if(closingPrice!=null) {
			boolean omitInput = false;
			for(int i=0; i<closingPrice.length; i++){
				if(closingPrice[i]==null || closingPrice[i].length()==0) omitInput = true;
			}
			if(omitInput) errors.add("Please input all fund prices");
		}
		
		if (errors.size() > 0) return errors;
		
		if (!button.equals("Submit") && !button.equals("Clear")) errors.add("Invalid button");
		
		return errors;
	}
	
}
