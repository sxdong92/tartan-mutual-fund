package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.formbean.BuyFundForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class CBuyFundAction extends Action{
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyFundForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	public CBuyFundAction(Model model){
		customerDAO=model.getCustomerDAO();
		transactionDAO=model.getTransactionDAO();
		fundDAO=model.getFundDAO();
	}
	public String getName(){
		return "cbuyfund.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		CustomerBean customer=(CustomerBean)request.getSession().getAttribute("customer");
		try {
			
			BuyFundForm form = formBeanFactory.create(request);
			
			
			long Id = Long.parseLong(request.getParameter("fundId"));
			FundBean fund=fundDAO.read(Id);	
			request.getSession().setAttribute("fund", fund);
			
			
			if (!form.isPresent()) {
				request.setAttribute("errors", errors);
				return "cbuyfunds.jsp";
			}
			
			
			if(form.getBuyAmount()==null){
				
				return "cbuyfunds.jsp";
			}
			

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "cbuyfunds.jsp";
			}
			
			
			long inputAmount = Long.parseLong(form.getBuyAmount());
			if(inputAmount < 1){
				errors.add("You cannot buy less than $1.");
				request.setAttribute("errors", errors);
				return "cbuyfunds.jsp";
			}
			if(inputAmount > customer.getAvailableCash()){
				errors.add("Your avavilable cash is not enough.");
				request.setAttribute("errors", errors);
				return "cbuyfunds.jsp";
			}
		
			TransactionBean transaction=new TransactionBean();
			transaction.setAmount(Long.parseLong(form.getBuyAmount()));
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setFundId(form.getIdAsLong());
			transaction.setTransactionType("buy");
			transactionDAO.create(transaction);
			customer.setAvailableCash(customer.getAvailableCash()-Long.parseLong(form.getBuyAmount()));
			customerDAO.update(customer);
			request.setAttribute("success", "You have successfully bought this fund.");
			request.getSession().setAttribute("customer", customer);
			return "cbuyfunds.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
