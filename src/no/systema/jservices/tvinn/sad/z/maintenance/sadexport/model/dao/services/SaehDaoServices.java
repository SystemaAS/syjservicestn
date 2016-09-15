package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services;
import java.util.List;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 */
public interface SaehDaoServices extends IDaoServices { 
	
	//TODO Review naming
	public List getListByAvd(String avd, StringBuffer errorStackTrace);
	public List getListByAvdOpd(String avd, String opd, StringBuffer errorStackTrace);
	public List findForUpdate(String avd, String opd, StringBuffer errorStackTrace);
}
