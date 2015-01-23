//Name: Xudong Song
//Date: DEC/04/2014
//Course#: 08-600
package edu.cmu.cs.webapp.tartan.formbean;


import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm  extends FormBean{
    private String userName;
    private String password;
    private String button;
    
    public String getUserName()      { return userName; }
    public String getPassword()      { return password;     }
    public String getButton()        { return button;       }
    
    public boolean isPresent()       { return button != null; }
    
    public void setUserName(String s)  { userName = s; }
    public void setPassword(String s)      { password = s;     }
    public void setButton(String s)        { button = s;       }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userName == null || userName.length() == 0) errors.add("UserName is required"); 
      //  System.out.print(userName+" ");     
        if (password == null || password.length() == 0) errors.add("Password is required"); 
      //  System.out.println(password + " in loginform");   
        if (button == null) errors.add("Button is required");
    //    System.out.println(button);

        if (errors.size() > 0) return errors;

        if (!button.equals("Customer Login") && !button.equals("Employee Login")) errors.add("Invalid button");
        if (userName.matches(".*[<>\"].*")) errors.add("User Name may not contain angle brackets or quotes");
		
        return errors;
    }
}
