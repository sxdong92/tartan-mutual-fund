package edu.cmu.cs.webapp.tartan.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.EmployeeBean;
import edu.cmu.cs.webapp.tartan.formbean.ChangePwdForm;
import edu.cmu.cs.webapp.tartan.model.EmployeeDAO;
import edu.cmu.cs.webapp.tartan.model.Model;



public class EChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	
	private EmployeeDAO employeeDAO;

	public EChangePwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "echangepwd.do"; }
    
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
	            return "echangepwd.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	request.setAttribute("errors", errors);
	            return "echangepwd.jsp";
	        }
	
			EmployeeBean employee = (EmployeeBean) request.getSession().getAttribute("employee");
			if(!employee.getPassword().equals(form.getOldPassword())){
				errors.add("Incorrect old password");
				request.setAttribute("errors", errors);
				return "echangepwd.jsp";
			}
			// Change the password
			employeeDAO.setPassword(employee.getUserName(),form.getNewPassword());
			request.setAttribute("message","Password changed for "+employee.getUserName());
			request.setAttribute("success", "You have successfully changed the password.");
	        return "echangepwd.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
