package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlbDao;

/**
 * 
 * @author oscardelatorre
 * @date Jun 17, 2016
 * 
 */
public interface HeadfDaoServices extends IDaoServices { 
	public List findForUpdate(String avd, String opd, StringBuffer errorStackTrace);
}
