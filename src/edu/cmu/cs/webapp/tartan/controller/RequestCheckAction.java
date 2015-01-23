package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.controller.Action;
import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.formbean.RequestCheckForm;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public RequestCheckAction(Model model) {

		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "requestcheck.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			/*
			 * CustomerBean customer = (CustomerBean) request.getSession(false)
			 * .getAttribute("customer");
			 */
			CustomerBean customer = customerDAO.read((long) 1);
			request.setAttribute("customer", customer);

			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {

				return "crequestcheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "crequestcheck.jsp";
			}

			long amountBefore = customer.getAvailableCash();
			long amount = Integer.parseInt(form.getRequestAmount());
			long amountNew = amountBefore - amount;

			if (amountNew < (long) 0) {
				errors.add("Your available cash is not enough.");
				return "crequestcheck.jsp";
			}

			TransactionBean transaction = new TransactionBean();
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setTransactionType("check");
			transaction.setAmount(amount);
			transactionDAO.create(transaction);

			customer.setAvailableCash((long) amountNew);

			customerDAO.update(customer);

			request.setAttribute("success",
					"You have successfully requested check!");

			return "crequestcheck.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
