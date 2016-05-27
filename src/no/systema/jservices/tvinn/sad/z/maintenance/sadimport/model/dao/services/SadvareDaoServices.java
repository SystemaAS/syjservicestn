package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date May 26, 2016
 * 
 */
public interface SadvareDaoServices extends IDaoServices { 
	public List getList(String leveid, StringBuffer errorStackTrace);
	public List findById (String id, String leveid, StringBuffer errorStackTrace );
}
