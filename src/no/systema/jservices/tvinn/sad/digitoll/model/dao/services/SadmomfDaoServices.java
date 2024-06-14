package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public interface SadmomfDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	public List findByLrn (String id, StringBuffer errorStackTrace );
	public int updateLrnMrn(Object daoObj, StringBuffer errorStackTrace);
	public int updateLrn(Object daoObj, StringBuffer errorStackTrace);
	public int deleteLight(Object daoObj, StringBuffer errorStackTrace);
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace);
	public int updateStatus2(Object daoObj, StringBuffer errorStackTrace);
	public int updateStatus3(Object daoObj, StringBuffer errorStackTrace);
	public int updateTransport(Object daoObj, String toEmlnrt, StringBuffer errorStackTrace);
	public int setMrnBup(Object daoObj, StringBuffer errorStackTrace);
	public int setRequestIdBup(Object daoObj, StringBuffer errorStackTrace);
	
	public int insertExternalMaster(Object daoObj, StringBuffer errorStackTrace);
	//public int updateManifestStatus(Object daoObj, StringBuffer errorStackTrace);
}
