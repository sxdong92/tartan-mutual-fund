package edu.cmu.cs.webapp.tartan.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("lId")
public class LastDayBean {
	private long      lId;
	private Date      lastDay;
	
	
	public long      getId()                 { return lId;             }
	public Date      getLastDay()            { return lastDay;        }
	
	
	public void      setId(long l)                { lId = l;               }
	public void      setLastDay(Date d)           { lastDay = d;          }
}
