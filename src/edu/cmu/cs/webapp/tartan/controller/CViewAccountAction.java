package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.tartan.databean.PositionBean;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.PositionDAO;

public class CViewAccountAction extends Action{
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	public CViewAccountAction(Model model){
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	public String getName(){
		return "cviewaccount.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		CustomerBean customer=(CustomerBean)request.getSession().getAttribute("customer");
		long customerId=customer.getCustomerId();
		try{
			PositionBean[] positionList = positionDAO.getPositions(customerId);
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
			return "detail.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
