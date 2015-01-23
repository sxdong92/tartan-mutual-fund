package edu.cmu.cs.webapp.tartan.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.tartan.databean.FundBean;

public class FundDAO extends GenericDAO<FundBean>{
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}

	public FundBean[] getAllFunds() throws RollbackException {
		FundBean[] allFunds = match();
		return allFunds;
	}

	public FundBean[] searchFund(String s) throws RollbackException {
		FundBean[] fund = match(MatchArg.equals("symbol",s));
		return fund;
	}
}
