package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.controller.Action;
import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.formbean.CustomerForm;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;

public class SearchCustomerAction extends Action {
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerForm.class);

	private CustomerDAO customerDAO;

	public SearchCustomerAction(Model model) {

		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "search.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			CustomerForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {

				return "eviewallc.jsp";
			}
			// if the employee clicks "show all" button
			// return the "viewCustomer" action
			if (form.getAction().equals("Show All")) {

				return "viewcustomer.do";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "eviewallc.jsp";
			}

			long id = Integer.parseInt(form.getCustomerId());
			// Look up the user
			CustomerBean customer = customerDAO.read(id);
			CustomerBean[] customers = new CustomerBean[1];

			if (customer == null) {
				errors.add("customer Id not found");
				return "eviewallc.jsp";
			}

			customers[0] = customer;
			request.setAttribute("customerList", customers);

			return "eviewallc.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
