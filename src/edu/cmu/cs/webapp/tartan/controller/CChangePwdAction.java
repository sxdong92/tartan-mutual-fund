package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;









import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.formbean.ChangePwdForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.Model;



public class CChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	
	private CustomerDAO customerDAO;

	public CChangePwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "cchangepwd.do"; }
    
    public String perform(HttpServletRequest request) {
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            // Set up user list for nav bar
			//request.setAttribute("userList",userDAO.getUsers());
	        
	        // Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	request.setAttribute("errors", errors);
	            return "cchangepwd.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	request.setAttribute("errors", errors);
	            return "cchangepwd.jsp";
	        }
	
			CustomerBean customer = (CustomerBean) request.getSession().getAttribute("customer");
			if(!customer.getPassword().equals(form.getOldPassword())){
				errors.add("Incorrect old password");
				request.setAttribute("errors", errors);
				return "cchangepwd.jsp";
			}
			
			// Change the password
			customerDAO.setPassword(customer.getUserName(),form.getNewPassword());
			request.setAttribute("message","Password changed for "+customer.getUserName());
			request.setAttribute("success", "You have successfully changed the password.");
	        return "cchangepwd.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
