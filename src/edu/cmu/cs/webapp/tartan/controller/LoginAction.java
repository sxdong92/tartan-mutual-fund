package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.EmployeeDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.EmployeeBean;
import edu.cmu.cs.webapp.tartan.formbean.LoginForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        HttpSession session = request.getSession();

    	// If user is already logged in, redirect to todolist.do
        if (session.getAttribute("employee") != null) {
        	return "transition-day.do";
        } else if (session.getAttribute("customer")!=null) {
        	return "cviewaccount.jsp";
        } 
        
        try {
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }
	        if(form.getButton().equals("Employee Login")){	        	
	        	// Look up the user
		        EmployeeBean[] employees = employeeDAO.match(MatchArg.equals("userName",form.getUserName()));
		        
		        if (employees == null || employees.length == 0) {
		            errors.add("User Name not found in employee database.");
		            request.setAttribute("errors", errors);
		            return "login.jsp";
		        }
		        
		        else {
		        	// Check the password
		        	EmployeeBean employee1 = employees[0];
			        System.out.println(employee1.getPassword());
			        System.out.println(form.getPassword());
			        if (!employee1.getPassword().equals(form.getPassword())) {
			            errors.add("Incorrect password");
			            return "login.jsp";
			        }
//			        if (!employee.checkPassword(form.getPassword())) {
//			            errors.add("Incorrect password");
//			            return "login.jsp";
//			        }
			        // Attach (this copy of) the user bean to the session
			        session.setAttribute("employee",employees[0]);
			        System.out.println("employee login sucess.");
			        return "transition-day.do";
		        } 
		        		

	        } else if(form.getButton().equals("Customer Login")){	        			               		
       		    // Look up the user
		        CustomerBean[] customers = customerDAO.match(MatchArg.equals("userName",form.getUserName()));
		        if (customers == null || customers.length == 0) {
		            errors.add("User Name not found in customer database.");
		            request.setAttribute("errors", errors);
		            return "login.jsp";
		        }
		        else{
			        CustomerBean customer1 = customers[0];		        
		        	// Check the password
			        if (!customer1.getPassword().equals(form.getPassword())) {
			            errors.add("Incorrect password");
			            return "login.jsp";
			        }
			
//			        if (!customer1.checkPassword(form.getPassword())) {
//			            errors.add("Incorrect password");
//			            request.setAttribute("errors", errors);
//			            return "login.jsp";
//			        }
			        // Attach (this copy of) the user bean to the session
			        session.setAttribute("customer",customers[0]);
			        return "cviewaccount.jsp"; 
		        } 
		       
		        
	        } else {
	        	System.out.println("get button error.");
	        	return "login.jsp";
	        }

        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}

