package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.tartan.formbean.FundForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.Model;

public class SearchFundAction extends Action {
	private FormBeanFactory<FundForm> formBeanFactory = FormBeanFactory
			.getInstance(FundForm.class);

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public SearchFundAction(Model model) {

		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "searchfund.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			FundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {

				return "csearchfunds.jsp";
			}
			// if the employee clicks "show all" button
			// return the "viewCustomer" action
			if (form.getAction().equals("Show All")) {

				return "viewfunds.do";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "csearchfunds.jsp";
			}

			// Look up the fund
			String symbol = form.getTicker();

			FundBean[] fundlist = fundDAO.match(MatchArg.equals("symbol",
					symbol));
			FundBean fund = fundlist[0];
			FundBean[] funds = new FundBean[1];

			if (fund == null) {
				errors.add("fund ticker is not found");
				return "csearchfunds.jsp";
			}

			funds[0] = fund;
			FundPriceHistoryBean[] priceHistory = fundPriceHistoryDAO
					.match(MatchArg.equals("fundId", funds[0].getFundId()));

			Date lastDay = null;
			Date[] dateArray = new Date[priceHistory.length];
			for (int i = 0; i < priceHistory.length; i++) {
				dateArray[i] = priceHistory[i].getPriceDate();
			}
			Arrays.sort(dateArray);
			lastDay = dateArray[dateArray.length - 1];
			int j;
			for (j = 0; j < priceHistory.length; j++)
				if (priceHistory[j].getPriceDate() == lastDay)
					break;
			long fundNewPrice = priceHistory[j].getPrice();
			long[] fundNewPrice1 = new long[1];
			fundNewPrice1[0] = fundNewPrice;

			request.setAttribute("fundList", funds);
			request.setAttribute("priceList", fundNewPrice1);

			return "csearchfunds.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
