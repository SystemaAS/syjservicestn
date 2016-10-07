package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;
import java.util.List;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;
/**
 * This Dao service also exists i bcode
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 * 
 */
public interface TrkodfDaoServices extends IDaoServices { 
	/**
	 * Retrieve unique id from selection using TransitKoder
	 * 
	 * 
	 * @param unikKode
	 * @param kode
	 * @param errorStackTrace
	 * @return
	 */
	public List findById(TransitKoder unikKode, String kode, StringBuffer errorStackTrace);
}
