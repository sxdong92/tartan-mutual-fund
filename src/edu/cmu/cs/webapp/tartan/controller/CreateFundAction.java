package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.formbean.CreateFundForm;

public class CreateFundAction extends Action {


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

	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);

	private FundDAO FundDAO;
	
	public CreateFundAction(Model model) {
		FundDAO = model.getFundDAO();
		
	}

	public String getName() { return "ecreatefunds.do"; }

	public String perform(HttpServletRequest request) {
      List<String> errors = new ArrayList<String>();
      request.setAttribute("errors",errors);

      try {
      		//request.setAttribute("userList",userDAO.getUsers());
	        CreateFundForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "ecreatefund.jsp";
	        }
	        if (FundDAO.searchFund(form.getSymbol()).length != 0) {
	        	errors.add("Fund already exists");
	        	return "ecreatefund.jsp";
	        }
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "ecreatefund.jsp";
	        }
	
	        // Create the customer bean
	        FundBean fund = new FundBean();
	        fund.setSymbol(form.getSymbol());
	        fund.setFundName(form.getFundName());
	        FundDAO.createAutoIncrement(fund);
      
			// Attach (this copy of) the user bean to the session
	        //HttpSession session = request.getSession(false);
	        //session.setAttribute("user",user);
	        request.setAttribute("success", "You have successfully created the fund.");
			return "ecreatefunds.do";
			
      } catch (RollbackException e) {
      	errors.add(e.getMessage());
      	return "ecreatefund.jsp";
      } catch (FormBeanException e) {
      	errors.add(e.getMessage());
      	return "ecreatefund.jsp";
      }
  }
}
