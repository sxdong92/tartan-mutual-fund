package edu.cmu.cs.webapp.tartan.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.controller.Action;
import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;
import edu.cmu.cs.webapp.tartan.databean.LastDayBean;
import edu.cmu.cs.webapp.tartan.databean.PositionBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.formbean.TransitionDayForm;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.LastDayDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.EmployeeDAO;
import edu.cmu.cs.webapp.tartan.model.PositionDAO;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class TransitionDayAction extends Action {
	private FormBeanFactory<TransitionDayForm> formBeanFactory = FormBeanFactory.getInstance(TransitionDayForm.class);
	
	private EmployeeDAO employeeDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private CustomerDAO customerDAO;
//	private LastDayDAO lastDayDAO;
	
	
	public TransitionDayAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		positionDAO = model.getPositionDAO();	
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		customerDAO = model.getCustomerDAO();
//		lastDayDAO = model.getLastDayDAO();
	}

	
	public String getName() { return "transition-day.do"; }
	
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        
        //if employee is not logged in, back to login page.
        
        
        
        //print transition day page with all the funds
        try {
        	TransitionDayForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			//add all funds data to request
			FundBean[] allFunds = fundDAO.getAllFunds(); //not sure
			request.setAttribute("allFunds", allFunds);
			
			//add last day data to request
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
			
//			FundPriceHistoryBean[] ph = fundPriceHistoryDAO.getAllFundPriceHistory(); 
//			Date lastDay;
//			if (ph!=null && ph.length!=0) {
//				Date[] dateArray = new Date[ph.length];
//				for(int i=0; i<ph.length; i++){
//					dateArray[i] = ph[i].getPriceDate();
//				}
//				Arrays.sort(dateArray);
//				lastDay = dateArray[dateArray.length-1];
//			}
//			else {
//				lastDay = null;
//			}
//			request.setAttribute("lastDay", lastDay);
			
			TransactionBean[] tbOrigin = transactionDAO.getAllTransactions();
			Date lastDay = null;
			if (tbOrigin!=null && tbOrigin.length!=0) {
				ArrayList<TransactionBean> l = new ArrayList<TransactionBean>();
				for (int i=0; i<tbOrigin.length; i++) {
					if (tbOrigin[i].getExecuteDate()!=null) {
						l.add(tbOrigin[i]);
					}
				}
				
				if(l!=null && l.size()!=0) {
					TransactionBean[] tb = new TransactionBean[l.size()];
					for (int i=0; i<l.size(); i++) {
						tb[i] = l.get(i);
					}
					if (tb!=null && tb.length!=0) {
						Date[] dateArray = new Date[tb.length];
						for(int i=0; i<tb.length; i++){
							dateArray[i] = tb[i].getExecuteDate();
						}
						Arrays.sort(dateArray);
						lastDay = dateArray[dateArray.length-1];
					}
				}
			}
			request.setAttribute("lastDay", lastDay);
			
			//if no parameters were passed, return with no errors so that the form will be
	        //presented (we assume for the first time).
			if (!form.isPresent()) {
				//not sure
	            return "transition-day.jsp";
	        }
			
			
			//if validation errors exists
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "transition-day.jsp";
	        }
	        
	        
			Date today = dateFormat.parse(form.getDate());
			if (lastDay != null) {
				if(today.compareTo(lastDay) != 1) {
					errors.add("Closing date you entered must later than last one");
					return "transition-day.jsp";
				}
			}
			
			
	        //get closing prices of all funds matched with their fund id, save in two arrays.
	        String[] closingPrices = form.getClosingPrice();    
			String[] allFundId = form.getAllFundId();
			HashMap<Long, Long> hm = new HashMap<Long, Long>();
			
			
			if (closingPrices!=null && closingPrices.length!=0) {
				//save new fund prices
				for(int i=0; i<closingPrices.length; i++)
				{	
					FundPriceHistoryBean newHistory = new FundPriceHistoryBean();
					newHistory.setFundId(Long.parseLong(allFundId[i]));
					newHistory.setPrice(Long.parseLong(closingPrices[i]));
					newHistory.setPriceDate(today);
					fundPriceHistoryDAO.create(newHistory);
					hm.put(Long.parseLong(allFundId[i]), Long.parseLong(closingPrices[i]));
				}
			}
			
			
			//execute transactions
			
			/* Rounding:
			 * double d = 1.4568222;
			 * d *= 1000;
			 * System.out.println(new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP));
			 * 
			 * 1457
			 * */
			
			
			TransactionBean[] allTransactions = transactionDAO.match(MatchArg.equals("executeDate", null));
			
			for(int i=0; i<allTransactions.length; i++) {
				String type = allTransactions[i].getTransactionType();
				
				if(type.equals("buy")) {
					long fundId = allTransactions[i].getFundId();
					long price = hm.get(fundId);
					long amount = allTransactions[i].getAmount();
					long canBuy = amount / price;
					//we don't need to check whether cash >= amount
					//this has been checked when require "buy" and "check"
					
					
					//need to check whether canBuy < 0.001 here !!!
					//if so, delete the transaction !!!
					//maybe we need to change data type !!!
					
					
					//update(add) positions
					long customerId = allTransactions[i].getCustomerId();
					PositionBean pb = positionDAO.read(customerId,fundId);
					if(pb == null) {
						PositionBean newPb = new PositionBean();
						newPb.setCustomerId(customerId);
						newPb.setFundId(fundId);
						newPb.setShares(canBuy);
						positionDAO.create(newPb);
					}
					else {
						long originShares = pb.getShares();
						pb.setShares(originShares+canBuy);
						positionDAO.update(pb);
					}
					
					//update transactions
					allTransactions[i].setExecuteDate(today);
					allTransactions[i].setShares(canBuy);
					transactionDAO.update(allTransactions[i]);
					
					//update customers
					CustomerBean cb = customerDAO.read(customerId);
					if(cb != null) {
						long originCash = cb.getCash();
						cb.setCash(originCash - amount);
						customerDAO.update(cb);
					}
				}
				else if(type.equals("sell")) {
					long fundId = allTransactions[i].getFundId();
					long price = hm.get(fundId);
					long shares = allTransactions[i].getShares();
					
					long customerId = allTransactions[i].getCustomerId();
					PositionBean pb = positionDAO.read(customerId,fundId);
					
					if(pb != null){
						long originShares = pb.getShares();
						if(originShares >= shares) {
							//need to check whether amount < 0.001 here !!!
							//if so, delete the transaction !!!
							//maybe we need to change data type !!!
							long amount = price * shares;
							
							
							//update positions
							pb.setShares(originShares - shares);
							positionDAO.update(pb);
							
							//update transactions
							allTransactions[i].setExecuteDate(today);
							allTransactions[i].setAmount(amount);
							transactionDAO.update(allTransactions[i]);
							
							//update customers
							CustomerBean cb = customerDAO.read(customerId);
							if(cb != null) {
								long originCash = cb.getCash();
								cb.setCash(originCash + amount);
								customerDAO.update(cb);
							}
						}
					}
				}
				else if(type.equals("check")) {
					long customerId = allTransactions[i].getCustomerId();
					long amount = allTransactions[i].getAmount();
					//we don't need to check whether cash >= amount
					//this has been checked when require "buy" and "check"
					
					//update transactions
					allTransactions[i].setExecuteDate(today);
					transactionDAO.update(allTransactions[i]);
					
					//update customers
					CustomerBean cb = customerDAO.read(customerId);
					if(cb != null) {
						long originCash = cb.getCash();
						cb.setCash(originCash - amount);
						customerDAO.update(cb);
					}
				}
				else if(type.equals("deposit")) {
					long customerId = allTransactions[i].getCustomerId();
					long amount = allTransactions[i].getAmount();
					
					//update transactions
					allTransactions[i].setExecuteDate(today);
					transactionDAO.update(allTransactions[i]);
					
					//update customers
					CustomerBean cb = customerDAO.read(customerId);
					if(cb != null) {
						long originCash = cb.getCash();
						cb.setCash(originCash + amount);
						customerDAO.update(cb);
					}
				}
			}
			
			
			//update all customers' availableCash here !!!
			CustomerBean[] allCustomers = customerDAO.getAllCustomers();
			for(int i=0; i<allCustomers.length; i++) {
				allCustomers[i].setAvailableCash(allCustomers[i].getCash());
				customerDAO.update(allCustomers[i]);
			}
			
			
			//update all positions' availableShares here (if zero, then delete) !!!
			PositionBean[] allPositions = positionDAO.getAllPositions();
			int len = allPositions.length;
			for(int i=0; i<len; i++) {
				if(allPositions[i].getShares() == 0) {
					positionDAO.delete(allPositions[i].getCustomerId(),allPositions[i].getFundId());
				}
				else {
					allPositions[i].setAvailableShares(allPositions[i].getShares());
					positionDAO.update(allPositions[i]);
				}
			}
			
		} catch (RollbackException e) {
			
			errors.add(e.getMessage());
			//not sure
			return "transition-day.jsp";
			
		} catch (FormBeanException e) {
			
        	errors.add(e.getMessage());
        	//not sure
        	return "transition-day.jsp";
        	
        } catch (ParseException e) {
        	
        	errors.add(e.getMessage());
			//not sure
        	return "transition-day.jsp";
		}
        
        
        //everything was done
        errors.add("Success!");
        
        return "transition-day.jsp";
	}
}
