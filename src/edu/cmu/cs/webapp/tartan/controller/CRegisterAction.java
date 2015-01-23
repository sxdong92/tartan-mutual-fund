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
import edu.cmu.cs.webapp.tartan.formbean.CRegisterForm;

public class CRegisterAction extends Action {


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

	private FormBeanFactory<CRegisterForm> formBeanFactory = FormBeanFactory.getInstance(CRegisterForm.class);

	private CustomerDAO CustomerDAO;
	
	public CRegisterAction(Model model) {
		CustomerDAO = model.getCustomerDAO();
		
	}

	public String getName() { return "cregister.do"; }

  public String perform(HttpServletRequest request) {
      List<String> errors = new ArrayList<String>();
      request.setAttribute("errors",errors);

      try {
      		//request.setAttribute("userList",userDAO.getUsers());
	        CRegisterForm form = formBeanFactory.create(request);
	        
	        if(form.getButton()==null){
	        	 return "cregister.jsp";
	        }
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "cregister.jsp";
	        }
	        request.setAttribute("form",form);
	        if (CustomerDAO.searchCustomer(form.getUserName()).length != 0) {
	        	errors.add("Customer already exists");
	        	return "cregister.jsp";
	        }
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "cregister.jsp";
	        }
	
	        // Create the customer bean
	        CustomerBean customer = new CustomerBean();
	        customer.setAddrLine1(form.getAddrLine1());
	        customer.setAddrLine2(form.getAddrLine2());
	        customer.setCity(form.getCity());
	        customer.setState(form.getState());
	        customer.setZip(form.getZip());  
	        customer.setFirstName(form.getFirstName());
	        customer.setLastName(form.getLastName());
	        customer.setPassword(form.getPassword());
	        customer.setUserName(form.getUserName()); 
	        CustomerDAO.createAutoIncrement(customer);
      
			// Attach (this copy of) the user bean to the session
	        //HttpSession session = request.getSession(false);
	        //session.setAttribute("user",user);
	        request.setAttribute("success", "You have successfully changed the password.");
			return "cregister.do";
			
      } catch (RollbackException e) {
      	errors.add(e.getMessage());
      	return "cregister.jsp";
      } catch (FormBeanException e) {
      	errors.add(e.getMessage());
      	return "cregister.jsp";
      }
  }
}
