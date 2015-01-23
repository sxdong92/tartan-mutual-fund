package edu.cmu.cs.webapp.tartan.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.tartan.databean.LastDayBean;

public class LastDayDAO extends GenericDAO<LastDayBean>{
	public LastDayDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(LastDayBean.class, tableName, cp);
	}

	public LastDayBean[] getAllLastDays() throws RollbackException {
		LastDayBean[] allLastDays = match();
		return allLastDays;
	}
}
