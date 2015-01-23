package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.Model;

public class ViewFundsAction extends Action {
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ViewFundsAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "viewfunds.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ArrayList<FundPriceHistoryBean[]> priceHistoryList = new ArrayList<FundPriceHistoryBean[]>();
			FundBean[] fundlist = fundDAO.getAllFunds();
			for (int i = 0; i < fundlist.length; i++)
				priceHistoryList.add(fundPriceHistoryDAO
						.getFundPrices(fundlist[i].getFundId()));
			long[] priceList = new long[fundlist.length];
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
				for (j = 0; j < fundPriceHistory.length; j++)
					if (fundPriceHistory[j].getPriceDate() == lastDay)
						break;
				long fundNewPrice = fundPriceHistory[j].getPrice();
				priceList[temp] = fundNewPrice;
				temp++;
			}

			request.setAttribute("fundList", fundlist);
			request.setAttribute("priceList", priceList);
			if (errors.size() != 0) {
				return "error.jsp";
			}

			return "csearchfunds.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
