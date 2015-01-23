package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.controller.Action;
import edu.cmu.cs.webapp.tartan.formbean.CustomerForm;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;

public class ViewCustomerAction extends Action {

	private CustomerDAO customerDAO;

	public ViewCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "viewcustomer.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			
			request.setAttribute("customerList", customerDAO.getAllCustomers());

			if (errors.size() != 0) {
				return "error.jsp";
			}

			return "eviewallc.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
