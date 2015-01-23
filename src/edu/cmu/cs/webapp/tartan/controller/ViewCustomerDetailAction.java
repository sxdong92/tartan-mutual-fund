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

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.tartan.databean.PositionBean;
import edu.cmu.cs.webapp.tartan.formbean.IdForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.PositionDAO;

public class ViewCustomerDetailAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory
			.getInstance(IdForm.class);

	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewCustomerDetailAction(Model model) {
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "viewdetail.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the request attributes (the errors list and the form bean so
		// we can just return to the jsp with the form if the request isn't
		// correct)
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {

			IdForm form = formBeanFactory.create(request);

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "error.jsp";
			}

			long id = form.getIdAsLong();
			CustomerBean customer = customerDAO.read(id);

			if (customer == null) {
				errors.add("No customer with id=" + id);
				return "error.jsp";
			}

			PositionBean[] positionList = positionDAO.getPositions(id);
			FundBean[] fundList = new FundBean[positionList.length];
			ArrayList<FundPriceHistoryBean[]> priceHistoryList = new ArrayList<FundPriceHistoryBean[]>();

			for (int i = 0; i < positionList.length; i++)
				fundList[i] = fundDAO.read(positionList[i].getFundId());

			for (int j = 0; j < fundList.length; j++)
				priceHistoryList.add(fundPriceHistoryDAO
						.getFundPrices(positionList[j].getFundId()));

			long[] priceList = new long[fundList.length];

			int temp = 0;
			for (int k = 0; k < priceHistoryList.size(); k++) {
				Date lastDay = null;
				FundPriceHistoryBean[] fundPriceHistory = priceHistoryList
						.get(k);
				Date[] dateArray = new Date[fundPriceHistory.length];
				for (int i = 0; i < fundPriceHistory.length; i++) {
					dateArray[i] = fundPriceHistory[i].getPriceDate();
				}
				Arrays.sort(dateArray);
				lastDay = dateArray[dateArray.length - 1];
				int j;
				for(j = 0; j < fundPriceHistory.length;j++)
					 if(fundPriceHistory[j].getPriceDate() == lastDay)
						 break;
				long fundNewPrice = fundPriceHistory[j].getPrice();
				priceList[temp] = fundNewPrice;
				temp++;
			}

			request.setAttribute("customer", customer);
			request.setAttribute("fundList", fundList);
			request.setAttribute("positionList", positionList);
			request.setAttribute("priceList", priceList);
           
			request.getSession().setAttribute("customer", customer);
			return "emagc.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
