package no.systema.jservices.tvinn.sad.z.maintenance.main.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Jun 1, 2016
 * 
 */
public interface KodtvaDaoServices extends IDaoServices { 
	public List findForUpdate(String id, String alfa, StringBuffer errorStackTrace);
}
