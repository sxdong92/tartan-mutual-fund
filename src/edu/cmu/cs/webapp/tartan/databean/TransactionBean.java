package edu.cmu.cs.webapp.tartan.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean {
	private long      transactionId;
	private long      customerId;
	private long      fundId;
	private Date      executeDate;
	private long      shares;
	private String    transactionType;
	private long      amount;
	
	
	public long      getTransactionId()      { return transactionId;   }
	public long      getCustomerId()         { return customerId;      }
	public long      getFundId()             { return fundId;          }
	public Date      getExecuteDate()        { return executeDate;     }
	public long      getShares()             { return shares;          }
	public String    getTransactionType()    { return transactionType; }
	public long      getAmount()             { return amount;          }
	
	
	public void      setTransactionId(long l)      { transactionId = l;   }
	public void      setCustomerId(long l)         { customerId = l;      }
	public void      setFundId(long l)             { fundId = l;          }
	public void      setExecuteDate(Date d)        { executeDate = d;     }
	public void      setShares(long l)             { shares = l;          }
	public void      setTransactionType(String s)  { transactionType = s; }
	public void      setAmount(long l)             { amount = l;          }
}
