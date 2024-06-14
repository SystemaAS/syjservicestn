package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Apr 2024
 * 
 */
public interface ZadmomlfDaoServices extends IDaoServices { 
	public List find(Object dao,StringBuffer errorStackTrace);
	public int insertLight(Object daoObj, StringBuffer errorStackTrace);
}
