package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public interface SadmotfDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	public List findByLrn (String id, StringBuffer errorStackTrace );
	public int updateLrnMrn(Object daoObj, StringBuffer errorStackTrace);
	public int updateLrn(Object daoObj, StringBuffer errorStackTrace);
	public int deleteLight(Object daoObj, StringBuffer errorStackTrace);
	public int deleteConsolidated(Object daoObj, StringBuffer errorStackTrace);
	//
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace);
	public int updateStatus2(Object daoObj, StringBuffer errorStackTrace);
	public int updateStatus3(Object daoObj, StringBuffer errorStackTrace);
	public int setMrnBup(Object daoObj, StringBuffer errorStackTrace);
	public int setRequestIdBup(Object daoObj, StringBuffer errorStackTrace);
	public int resetMrn(Object daoObj, StringBuffer errorStackTrace);
	public int resetMrnManually(Object daoObj, StringBuffer errorStackTrace);
	public int reassignSignature(Object daoObj, StringBuffer errorStackTrace);
	//Entry
	public int updateStatus2ForEntry(Object daoObj, StringBuffer errorStackTrace);
	//AUTO-GENERATED children
	public int updateAutoGenChildren(Object daoObj, StringBuffer errorStackTrace);
	
	
	public List findHouseOpd(String opd,Object dao,StringBuffer errorStackTrace);
	public List findHouseExtRef (String extref, Object obj, StringBuffer errorStackTrace );
	public List findMasterId (String masterId, Object obj, StringBuffer errorStackTrace );
}
