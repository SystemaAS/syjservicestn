package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
public interface SadefcfDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	public List pick(Object dao,StringBuffer errorStackTrace);
	public List findById (String pro, String tdn, StringBuffer errorStackTrace );
	public int release(Object daoObj, StringBuffer errorStackTrace);
	public int bindTur(Object daoObj, StringBuffer errorStackTrace);
}
