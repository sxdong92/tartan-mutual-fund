package edu.cmu.cs.webapp.tartan.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId,fundId")
public class PositionBean {
	private long      customerId;
	private long      fundId;
	private long      shares;
	private long      availableShares;
	
	
	public long      getCustomerId()         { return customerId;      }
	public long      getFundId()             { return fundId;          }
	public long      getShares()             { return shares;          }
	public long      getAvailableShares()             { return availableShares;          }
	
	
	public void      setCustomerId(long l)         { customerId = l;      }
	public void      setFundId(long l)             { fundId = l;          }
	public void      setShares(long l)             { shares = l;          }
	public void      setAvailableShares(long l)             { availableShares = l;          }
}
