package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */
public interface SadsdDaoServices extends IDaoServices { 
	public List findByAlfa(String alfa, StringBuffer errorStackTrace);
	public List findByAvgift(String avgift, StringBuffer errorStackTrace);
	public List findByDates(String id, String fdate, String tdate, StringBuffer errorStackTrace);
	
}
