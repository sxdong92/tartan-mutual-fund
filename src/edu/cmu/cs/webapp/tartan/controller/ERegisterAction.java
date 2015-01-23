package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.EmployeeDAO;


import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.EmployeeBean;
import edu.cmu.cs.webapp.tartan.formbean.CRegisterForm;
import edu.cmu.cs.webapp.tartan.formbean.ERegisterForm;

public class ERegisterAction extends Action {


/*
* Processes the parameters from the form in register.jsp.
* If successful:
*   (1) creates a new User bean
*   (2) sets the "user" session attribute to the new User bean
*   (3) redirects to view the originally requested photo.
* If there was no photo originally requested to be viewed
* (as specified by the "redirect" hidden form value),
* just redirect to manage.do to allow the user to add some
* photos.
*/

	private FormBeanFactory<ERegisterForm> formBeanFactory = FormBeanFactory.getInstance(ERegisterForm.class);

	private EmployeeDAO EmployeeDAO;
	
	public ERegisterAction(Model model) {
		EmployeeDAO = model.getEmployeeDAO();
		
	}

	public String getName() { return "eregister.do"; }

  public String perform(HttpServletRequest request) {
      List<String> errors = new ArrayList<String>();
      request.setAttribute("errors",errors);

      try {
      		//request.setAttribute("userList",userDAO.getUsers());
	        ERegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "eregister.jsp";
	        }
	        if (EmployeeDAO.searchEmployee(form.getUserName()).length != 0) {
	        	errors.add("Employee already exists");
	        	return "eregister.jsp";
	        }
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "eregister.jsp";
	        }
	
	        // Create the employee bean
	        EmployeeBean employee = new EmployeeBean();
	        employee.setFirstName(form.getFirstName());
	        employee.setLastName(form.getLastName());
	        employee.setPassword(form.getPassword());
	        employee.setUserName(form.getUserName()); 
	        EmployeeDAO.createAutoIncrement(employee);
      
			// Attach (this copy of) the user bean to the session
	        //HttpSession session = request.getSession(false);
	        //session.setAttribute("user",user);
	        request.setAttribute("success", "You have successfully changed the password.");
			return "eregister.do";
			
      } catch (RollbackException e) {
      	errors.add(e.getMessage());
      	return "eregister.jsp";
      } catch (FormBeanException e) {
      	errors.add(e.getMessage());
      	return "eregister.jsp";
      }
  }
}
