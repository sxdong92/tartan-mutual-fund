package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	
	private long      fundId;
	private String    fundName;
	private String    symbol;
	
	public long      getFundId()             { return fundId;          }
	public String    getFundName()           { return fundName;        }
	public String    getSymbol()             { return symbol;          }
	
	
	public void      setFundId(long l)             { fundId = l;          }
	public void      setFundName(String s)         { fundName = s;        }
	public void      setSymbol(String s)           { symbol = s;          }

	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (symbol == null || symbol.length() == 0) {
			errors.add("Ticker is required");
		}

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund Name is required");
		}

		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}
