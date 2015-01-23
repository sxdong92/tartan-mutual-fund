package edu.cmu.cs.webapp.tartan.model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.tartan.databean.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean>{

	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}
	
	public EmployeeBean[] getEmployee() throws RollbackException {
		EmployeeBean[] employee = match();
		Arrays.sort(employee);  
		return employee;
	}

	public EmployeeBean[] getEmployee(String userName) throws RollbackException{
		EmployeeBean[] employees = match(MatchArg.equals("userName", userName));
		return employees;
	}
	
	public EmployeeBean[] searchEmployee(String s) throws RollbackException {
		EmployeeBean[] employee = match(MatchArg.equals("userName",s));
		return employee;
	}
	
	public void setPassword(int id, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	EmployeeBean dbEmployee = read(id);
			
			if (dbEmployee == null) {
				throw new RollbackException("User "+ id +" no longer exists");
			}
			
			dbEmployee.setPassword(password);
			
			update(dbEmployee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void setPassword(String userName, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	EmployeeBean[] employees = match(MatchArg.equals("userName", userName));
    		 if (employees == null || employees.length == 0) {
    			 throw new RollbackException(userName + " no longer exists.");
    		 }
			
    		 employees[0].setPassword(password);
			
			update(employees[0]);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
