package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;
import java.util.List;

import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TransitKodeTypeDao;
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
	 * Retrieve unique kode from selection using TransitKoder
	 * 
	 * 
	 * @param unikKode
	 * @param kode
	 * @param errorStackTrace
	 * @return
	 */
	public List findById(TransitKoder unikKode, String kode, StringBuffer errorStackTrace);
	
	/**
	 * Search koder using kode and/or text
	 * 
	 * @param unikKode, implicit delivered
	 * @param kode, like search, can be null
	 * @param text, like search, can be null
	 * @param errorStackTrace
	 * @return a List bases on search criteria
	 */
	public List findBySearch(TransitKoder unikKode, String kode, String text, StringBuffer errorStackTrace);

	/**
	 * Retrieve all Transit kodetyper.
	 * 
	 * Description is located in message.properities
	 * 
	 * @return list of {@link TransitKodeTypeDao}
	 */
	public List getTransitKodeTyper();

}
