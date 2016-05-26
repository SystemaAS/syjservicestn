package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date May 26, 2016
 * 
 */
public interface SoktariDaoServices extends IDaoServices { 
	public List findForUpdate(String id, String alfa, StringBuffer errorStackTrace);
}
