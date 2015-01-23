package edu.cmu.cs.webapp.tartan.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class FundBean {
	private long      fundId;
	private String    fundName;
	private String    symbol;
	
	
	public long      getFundId()             { return fundId;          }
	public String    getFundName()           { return fundName;        }
	public String    getSymbol()             { return symbol;          }
	
	
	public void      setFundId(long l)             { fundId = l;          }
	public void      setFundName(String s)         { fundName = s;        }
	public void      setSymbol(String s)           { symbol = s;          }
}
