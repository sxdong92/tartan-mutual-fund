package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.tartan.databean.PositionBean;
import edu.cmu.cs.webapp.tartan.formbean.FundIdForm;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.PositionDAO;

public class CMagFundsAction extends Action{
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	public CMagFundsAction(Model model){
		fundDAO=model.getFundDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO=model.getFundPriceHistoryDAO();
	}
	public String getName(){
		return "cmagfunds.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		CustomerBean customer=(CustomerBean)request.getSession().getAttribute("customer");
		try{
			
			PositionBean[] positionList = positionDAO.getPositions(customer.getCustomerId());
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
			request.setAttribute("fundList", fundList);
			request.setAttribute("positionList", positionList);
			request.setAttribute("priceList", priceList);
			return "cmagfunds.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
	}
}
