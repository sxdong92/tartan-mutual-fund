package edu.cmu.cs.webapp.tartan.controller;

//AndrewID: shiw
//Class: 08-600
//Date: 12/1/2014
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import edu.cmu.cs.webapp.tartan.model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
	

	public LogoutAction(Model model) {
		
	}

	public String getName() {
		return "logout.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		session.setAttribute("customer", null);
		session.setAttribute("fund", null);
		session.setAttribute("employee", null);
		return "login.jsp";
	}
}
