package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder;

import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 */
public interface KodtscDaoServices extends IDaoServices { 
	//override
	public List findById(String id, StringBuffer errorStackTrace);
	
}
