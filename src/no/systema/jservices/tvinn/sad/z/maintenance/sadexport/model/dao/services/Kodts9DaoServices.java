package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services;

import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Oct 6, 2016
 * 
 */
public interface Kodts9DaoServices extends IDaoServices { 
	//override
	public List findById(String id, StringBuffer errorStackTrace);
	
}
