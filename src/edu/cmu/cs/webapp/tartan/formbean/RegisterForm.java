//Name: Xudong Song
//Date: DEC/04/2014
//Course#: 08-600
package edu.cmu.cs.webapp.tartan.formbean;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean{
	private String emailAddress;
	private String firstName;
    private String lastName;
    private String password;
    private String button;
    
//    public RegisterForm(HttpServletRequest request) {
//    	emailAddress = request.getParameter("emailAddress");
//    	firstName = request.getParameter("firstName");
//        lastName = request.getParameter("lastName");
//    	password = request.getParameter("password");
//    	button   = request.getParameter("button");
//    }
    
    public String getEmailAddress()  { return emailAddress; }
    public String getFirstName()     { return firstName;    }
    public String getLastName()      { return lastName;     }
    public String getPassword()      { return password;     }
    public String getButton()        { return button;       }
    
    public boolean isPresent()   { return button != null; }
    
    public void setEmailAddress(String s)  { emailAddress = s; }
    public void setFirstName(String s)     { firstName = s;    }
    public void setLastName(String s)      { lastName = s;     }
    public void setPassword(String s)      { password = s;     }
    public void setButton(String s)        { button = s;       }
    
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (emailAddress == null || emailAddress.length() == 0) errors.add("E-mail is required");
        if (firstName == null || firstName.length() == 0) errors.add("First Name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last Name is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (button == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Login") && !button.equals("Register")) errors.add("Invalid button");
        if (emailAddress.matches(".*[<>\"].*")) errors.add("User Name may not contain angle brackets or quotes");
		
        return errors;
    }
}
