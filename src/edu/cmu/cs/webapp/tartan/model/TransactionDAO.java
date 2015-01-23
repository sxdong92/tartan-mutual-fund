package edu.cmu.cs.webapp.tartan.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.tartan.databean.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean>{
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}
	
	public TransactionBean[] getAllTransactions() throws RollbackException {
		TransactionBean[] allTransactions = match();
		return allTransactions;
	}

	public TransactionBean[] getMyTransactions(long customerId) throws RollbackException{
		TransactionBean[] myTransactions=match(MatchArg.equals("customerId", customerId));
		return myTransactions;
	}
}
