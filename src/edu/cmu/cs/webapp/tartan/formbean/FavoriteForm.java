//Name: Xudong Song
//Date: DEC/04/2014
//Course#: 08-600
package edu.cmu.cs.webapp.tartan.formbean;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class FavoriteForm extends FormBean{
	private String uRL;
	private String comment;
	
//	public FavoriteForm(HttpServletRequest request) {
//		uRL = sanitize(request.getParameter("uRL"));
//		comment = sanitize(request.getParameter("comment"));
//	}
	
	public String getURL()        { return uRL; }
	public String getComment()    { return comment; }

	public void setURL(String s)        { uRL = s; }
	public void setComment(String s)    { comment = s; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (uRL == null || uRL.length() == 0) {
			errors.add("url is required");
		}

		return errors;
	}

//	private String sanitize(String s) {
//    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
//	}
}
