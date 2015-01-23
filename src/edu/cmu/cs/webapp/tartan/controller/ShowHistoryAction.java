package edu.cmu.cs.webapp.tartan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;
import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.TransactionBean;
import edu.cmu.cs.webapp.tartan.model.CustomerDAO;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.Model;
import edu.cmu.cs.webapp.tartan.model.TransactionDAO;

public class ShowHistoryAction extends Action{
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	public ShowHistoryAction(Model model){
		customerDAO=model.getCustomerDAO();
		transactionDAO=model.getTransactionDAO();
		fundDAO=model.getFundDAO();
	}
	public String getName(){
		return "showhistory.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		HashMap<TransactionBean,FundBean> transHistory=new HashMap<TransactionBean,FundBean>();
		CustomerBean customer=(CustomerBean)request.getSession().getAttribute("customer");
		long customerId=customer.getCustomerId();
		try{
			
			TransactionBean[] transactions=transactionDAO.getMyTransactions(customerId);
			for(int i=0;i<transactions.length;i++){
				long fundId=transactions[i].getFundId();
				FundBean fund;
				if(fundId<=0){
					fund=new FundBean();
				}else{
					fund=fundDAO.read(fundId);
				}
				transHistory.put(transactions[i], fund);
				//transHistory.
			}
			request.setAttribute("transHistory", transHistory);
			return "trans.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} 
	}
}
