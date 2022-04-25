package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Apr 2022
 * 
 */
public interface SadefcmfDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	public List pick(Object dao,StringBuffer errorStackTrace);
	//public List findById (String pro, String tdn, StringBuffer errorStackTrace );
}
