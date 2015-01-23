package edu.cmu.cs.webapp.tartan.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs.webapp.tartan.model.Model;

public class CViewTransAction extends Action{
	public CViewTransAction(Model models){
		
	}
	public String getName() {
		return "cviewtrans.do";
	}
	public String perform(HttpServletRequest request) {
		return "cviewtrans.jsp";
	}
}
