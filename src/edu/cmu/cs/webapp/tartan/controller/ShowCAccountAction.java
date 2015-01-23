package edu.cmu.cs.webapp.tartan.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs.webapp.tartan.model.Model;

public class ShowCAccountAction extends Action{
	public ShowCAccountAction(Model model){
		
	}
	public String getName() {
		return "showcaccount.do";
	}
	public String perform(HttpServletRequest request) {
		return "cviewaccount.jsp";
	}
}
