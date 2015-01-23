package edu.cmu.cs.webapp.tartan.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.tartan.databean.FundBean;
import edu.cmu.cs.webapp.tartan.databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean>{
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}
	
	public FundPriceHistoryBean[] getAllFundPriceHistory() throws RollbackException {
		FundPriceHistoryBean[] allFundPriceHistoryBean = match();
		return allFundPriceHistoryBean;
	}

	public FundPriceHistoryBean[] getFundPrices(long id)
			throws RollbackException {
		FundPriceHistoryBean[] fundPriceList = match(MatchArg.equals("fundId",
				id));
		return fundPriceList;
	}
}
