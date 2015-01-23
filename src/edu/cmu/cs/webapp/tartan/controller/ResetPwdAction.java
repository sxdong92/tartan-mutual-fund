package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.formbean.ResetPwdForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.Model;

public class ResetPwdAction extends Action{
	private FormBeanFactory<ResetPwdForm> formBeanFactory = FormBeanFactory.getInstance(ResetPwdForm.class);	
	private CustomerDAO customerDAO;

	public ResetPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "resetpwd.do"; }
    
    public String perform(HttpServletRequest request) {
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        long customerId=(long)request.getAttribute("customerId");
        try {
            // Set up user list for nav bar
			//request.setAttribute("userList",userDAO.getUsers());
	        
	        // Load the form parameters into a form bean
	        ResetPwdForm form = formBeanFactory.create(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	request.setAttribute("errors", errors);
	            return "resetpwd.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	request.setAttribute("errors", errors);
	            return "resetpwd.jsp";
	        }
	
			CustomerBean customer = customerDAO.read(customerId);
			customer.setPassword(form.getNewPassword());
			
			// Change the password
			customerDAO.update(customer);
			request.getSession().setAttribute("customer", customer);
			request.setAttribute("success", "You have successfully reset the password.");
	        return "resetpwd.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
