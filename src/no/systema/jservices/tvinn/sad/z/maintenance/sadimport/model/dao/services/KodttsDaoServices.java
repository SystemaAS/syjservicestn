package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlbDao;

/**
 * 
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 */
public interface KodttsDaoServices extends IDaoServices { 
	public List findByAlfa(String id, StringBuffer errorStackTrace);
	
}
