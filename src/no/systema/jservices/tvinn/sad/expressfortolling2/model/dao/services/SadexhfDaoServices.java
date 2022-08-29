package no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Aug 2022
 * 
 */
public interface SadexhfDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	public int updateLrnMrn(Object daoObj, StringBuffer errorStackTrace);
	public int updateLrn(Object daoObj, StringBuffer errorStackTrace);
	public int deleteLight(Object daoObj, StringBuffer errorStackTrace);
	
	//public int updateStatus(Object daoObj, StringBuffer errorStackTrace);
	//public int updateManifestStatus(Object daoObj, StringBuffer errorStackTrace);
}
