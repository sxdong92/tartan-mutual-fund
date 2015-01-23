package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.Model;

public class TestAction extends Action{
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	public TestAction(Model model){
		customerDAO=model.getCustomerDAO();
		fundDAO=model.getFundDAO();
	}
	public String getName(){
		return "test.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try{
			CustomerBean customer=new CustomerBean();
			FundBean fund=new FundBean();
			String userName="Wancy";
			long cash=2000;
			long availableCash=2000;
			String ticker="MUTUAL";
			String name="google";
			fund.setFundName(name);
			fund.setSymbol(ticker);
			customer.setCash(cash);
			customer.setAvailableCash(availableCash);
			customer.setUserName(userName);
			customerDAO.create(customer);
			fundDAO.create(fund);
			CustomerBean[] customers=customerDAO.getCustomer(userName);
			FundBean funds=fundDAO.read(1);
			customer=customers[0];
			request.getSession().setAttribute("customer", customer);
			request.getSession().setAttribute("fund", funds);
			return "cbuyfunds.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
	}
}
