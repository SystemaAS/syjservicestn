package no.systema.jservices.tvinn.sad.sadimport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public interface SadImpDigDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	
}
