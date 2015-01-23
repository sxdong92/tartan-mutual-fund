package edu.cmu.cs.webapp.tartan.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CRegisterForm extends FormBean {

	private String button;
	private String firstName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;
	private String password;
	private String confirm;
	private String userName;
	
	public String getFirstName()  { return firstName; }
	public String getLastName()   { return lastName;  }
	public String getUserName()   { return userName;  }
	public String getAddrLine1()  { return addrLine1; }
	public String getAddrLine2()  { return addrLine2; }
	public String getCity()       { return city;      }
	public String getState()      { return state;     }
	public String getZip()        { return zip;       }
	public String getPassword()   { return password;  }
	public String getConfirm()    { return confirm;   }
	public String getButton()    { return button;   }
	
	public void setFirstName(String s) { firstName = trimAndConvert(s,"<>\"");  }
	public void setLastName(String s)  { lastName  = trimAndConvert(s,"<>\"");  }
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setAddrLine1(String s) { addrLine1 = trimAndConvert(s,"<>\"");  }
	public void setAddrLine2(String s) { addrLine2 = trimAndConvert(s,"<>\"");  }
	public void setCity(String s)      { city = trimAndConvert(s,"<>\"");       }
	public void setState(String s)     { state = trimAndConvert(s,"<>\"");      }
	public void setZip(String s)       { zip = trimAndConvert(s,"<>\"");        }
	public void setPassword(String s)  { password  = s.trim();                  }
	public void setConfirm(String s)   { confirm   = s.trim();                  }
	public void setButton(String s)       { button = trimAndConvert(s,"<>\"");        }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}
		
		if (userName == null || userName.length() == 0) {
			errors.add("Username is required");
		}

		if (addrLine1  == null || addrLine1.length() == 0) {
			errors.add("Address1 is required");
		}
		
		if (addrLine2  == null || addrLine2.length() == 0) {
			errors.add("Address2 is required");
		}
		
		if (city   == null || city.length() == 0) {
			errors.add("City is required");
		}
		
		if (state   == null || state.length() == 0) {
			errors.add("State is required");
		}
		if (zip   == null || zip.length() == 0) {
			errors.add("Zip is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		
		
		return errors;
	}
}
