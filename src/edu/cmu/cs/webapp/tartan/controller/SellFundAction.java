package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.formbean.IdForm;
import edu.cmu.cs.webapp.tartan.formbean.SellFundForm;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class SellFundAction extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);
	private FormBeanFactory<IdForm> formBeanFactory1 = FormBeanFactory
			.getInstance(IdForm.class);

	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;

	public SellFundAction(Model model) {

		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "sellfund.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CustomerBean customer = (CustomerBean) request.getSession().getAttribute("customer");
			
			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			IdForm form1 = formBeanFactory1.create(request);
			request.setAttribute("form1", form1);
			
			if (!form.isPresent() && !form1.isPresent()) {
				return "csellfunds.jsp";
			}
			
			if(!form.isPresent()) {
				long fundId = form1.getIdAsLong();
				FundBean fund = fundDAO.read(fundId);
				request.getSession().setAttribute("fund", fund);
				return "csellfunds.jsp";
			}
			

//			if (!form1.isPresent()) {
//				return "csellfunds.jsp";
//			}
			
			FundBean fund =  (FundBean) request.getSession().getAttribute("fund");
			long fundId = fund.getFundId();
			// Any validation errors?
			errors.addAll(form1.getValidationErrors());
			errors.addAll(form.getValidationErrors());
			

			if (errors.size() != 0) {
				return "error.jsp";
			}

//			FundBean fund = fundDAO.read(fundId);
			long shares = Long.parseLong(form.getShares());
			
			TransactionBean transaction = new TransactionBean();
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setTransactionType("sell");
			transaction.setShares(shares);
			transaction.setFundId(fundId);
			
			transactionDAO.create(transaction);

			request.setAttribute("fund", fund);

			request.setAttribute("success",
					"You have successfully sell the fund!");

			return "csellfunds.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
