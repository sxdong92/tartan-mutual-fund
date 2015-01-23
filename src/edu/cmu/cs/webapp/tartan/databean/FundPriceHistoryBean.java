package edu.cmu.cs.webapp.tartan.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,priceDate")
public class FundPriceHistoryBean {
	private long      fundId;
	private Date      priceDate;
	private long      price;
	
	
	public long      getFundId()             { return fundId;          }
	public Date      getPriceDate()          { return priceDate;       }
	public long      getPrice()              { return price;           }
	
	
	public void      setFundId(long l)             { fundId = l;          }
	public void      setPriceDate(Date d)          { priceDate = d;       }
	public void      setPrice(long l)              { price = l;           }
}
