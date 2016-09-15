package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date May 30, 2016
 * 
 */
public interface SadlDaoServices extends IDaoServices { 
	public List getList(String leveid, StringBuffer errorStackTrace);
	public List findById (String id, String leveid, StringBuffer errorStackTrace );
}
