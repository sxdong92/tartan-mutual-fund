package edu.cmu.cs.webapp.tartan.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.tartan.databean.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean>{
	
	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}

	public CustomerBean[] getAllCustomers() throws RollbackException {
		CustomerBean[] customerBeans = match();
		return customerBeans;
	}

	public CustomerBean[] getCustomer(String userName) throws RollbackException{
		CustomerBean[] customer=match(MatchArg.equals("userName", userName));
		return customer;
	}


	public CustomerBean[] searchCustomer(String s) throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("userName",s));
		return customer;
	}
	
	public void setPassword(int id, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	CustomerBean dbCustomer = read(id);
			
			if (dbCustomer == null) {
				throw new RollbackException("User "+ id +" no longer exists");
			}
			
			dbCustomer.setPassword(password);
			
			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void setPassword(String userName, String password) throws RollbackException {
        try {
        	Transaction.begin();
    		CustomerBean[] customers = match(MatchArg.equals("userName", userName));
    		 if (customers == null || customers.length == 0) {
    			 throw new RollbackException(userName + " no longer exists.");
    		 }
			
    		 customers[0].setPassword(password);
			
			update(customers[0]);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
