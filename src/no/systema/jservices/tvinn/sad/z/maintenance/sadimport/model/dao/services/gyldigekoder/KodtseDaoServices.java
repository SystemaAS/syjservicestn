package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder;

import java.util.*;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 * 
 */
public interface KodtseDaoServices extends IDaoServices { 
	//override
	public List findById(String id, StringBuffer errorStackTrace);
	
}
