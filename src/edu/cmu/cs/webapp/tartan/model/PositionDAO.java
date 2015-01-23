package edu.cmu.cs.webapp.tartan.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.tartan.databean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean>{
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}

	public PositionBean[] getAllPositions() throws RollbackException {
		PositionBean[] allPositions = match();
		return allPositions;
	}

	public PositionBean[] getPositions(long id) throws RollbackException {
		PositionBean[] phonelist = match(MatchArg.equals("customerId", id));
		return phonelist;
	}
}
