package no.systema.jservices.tvinn.sad.ncts5export.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 */
public interface NctsecDaoServices extends IDaoServices { 
	public List findById (Integer avd, Integer tdn, StringBuffer errorStackTrace );
	public List findById (Integer avd, Integer tdn, Integer lineNr, StringBuffer errorStackTrace );
}
