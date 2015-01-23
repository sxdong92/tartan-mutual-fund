package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class ViewHistoryAction extends Action{
	
	public ViewHistoryAction(Model model){
		
	}
	public String getName(){
		return "viewhistory.do";
	}
	public String perform(HttpServletRequest request) {
		
		CustomerBean customer=(CustomerBean)request.getSession().getAttribute("customer");
		request.getSession().setAttribute("customer", customer);
		return "eviewctrans.jsp";
	}
}
