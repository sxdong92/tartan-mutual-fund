package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.formbean.DepositForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class DepositAction extends Action{
	private FormBeanFactory<DepositForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	public DepositAction(Model model){
		customerDAO=model.getCustomerDAO();
		transactionDAO=model.getTransactionDAO();
	}
	public String getName(){
		return "edeposit.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		CustomerBean customer;
		
		
		
		try {
			
			
			DepositForm form=formBeanFactory.create(request);
			
			if(form.getAmount()==null){
				long customerId=Long.parseLong(request.getParameter("customerId"));
				customer=customerDAO.read(customerId);
				request.setAttribute("customer", customer);
				return "edeposit.jsp";
				
			}
				
			long customerId=Long.parseLong(form.getCustomerId());
			customer=customerDAO.read(customerId);
			request.setAttribute("customer",customer );
			
			if (!form.isPresent()) {
				request.setAttribute("errors", errors);
				return "edeposit.jsp";
			}
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "edeposit.jsp";
			}
			
			long availableCash=customer.getAvailableCash()+Long.parseLong(form.getAmount());
			if(10000000<availableCash){
				errors.add("Your cannot deposit more than $10,000,000 at a time.");
				request.setAttribute("errors", errors);
				
				return "edeposit.jsp";
			}
			if(1>Integer.parseInt(form.getAmount())){
				errors.add("You cannot deposit less than $1.");
				request.setAttribute("errors", errors);
				
				return "edeposit.jsp";
			}
			
			
			TransactionBean transaction=new TransactionBean();
			transaction.setAmount(Long.parseLong(form.getAmount()));
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setTransactionType("deposit");
			transactionDAO.create(transaction);
			request.getSession().setAttribute("customer",customer );
			request.setAttribute("success", "You have successfully deposit the check.");
			
			return "edeposit.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
