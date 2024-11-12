package no.systema.jservices.tvinn.sad.sadimport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Nov 2024
 * 
 */
public interface SadhDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
}
