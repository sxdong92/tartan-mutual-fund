//Name: Xudong Song
//Date: DEC/04/2014
//Course#: 08-600
package edu.cmu.cs.webapp.tartan.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
//	private LastDayDAO lastDayDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			customerDAO          = new CustomerDAO(pool, "tartan_customer");
			employeeDAO          = new EmployeeDAO(pool, "tartan_employee");
			fundDAO              = new FundDAO(pool, "tartan_fund");
			fundPriceHistoryDAO  = new FundPriceHistoryDAO(pool, "tartan_fund_price_history");
			positionDAO          = new PositionDAO(pool, "tartan_position");
			transactionDAO       = new TransactionDAO(pool, "tartan_transaction");
//			lastDayDAO           = new LastDayDAO(pool, "tartan_last_day");
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public CustomerDAO           getCustomerDAO()          { return customerDAO;         }
	public EmployeeDAO           getEmployeeDAO()          { return employeeDAO;         }
	public FundDAO               getFundDAO()              { return fundDAO;             }
	public FundPriceHistoryDAO   getFundPriceHistoryDAO()  { return fundPriceHistoryDAO; }
	public PositionDAO           getPositionDAO()          { return positionDAO;         }
	public TransactionDAO        getTransactionDAO()       { return transactionDAO;      }
//	public LastDayDAO            getLastDayDAO()           { return lastDayDAO;          }
}
