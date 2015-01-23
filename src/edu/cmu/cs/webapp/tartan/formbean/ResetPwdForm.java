//Name: Xudong Song
//Date: DEC/04/2014
//Course#: 08-600
package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetPwdForm extends FormBean {
	private String customerId;
	private String confirmPassword;
	private String newPassword;

	
	public String getUserName()		   { return customerId;		 }
	public String getConfirmPassword() { return confirmPassword; }
	public String getNewPassword()     { return newPassword;     }

	
	public void setUserName(String s)		 { customerId = s.trim();		   }
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setNewPassword(String s)     { newPassword     = s.trim(); }


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

        if (customerId == null || customerId.length() == 0) errors.add("UserName is required."); 
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm password is required.");
		}
	
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match. Please enter your new password again.");
		}

		return errors;
	}
}
