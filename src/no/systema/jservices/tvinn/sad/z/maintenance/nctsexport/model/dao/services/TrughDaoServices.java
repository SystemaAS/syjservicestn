package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;

import java.util.List;

import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrughDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 16, 2016
 * 
 */
public interface TrughDaoServices extends IDaoServices { 

	/**
	 * Search by id.
	 * 
	 * Find row in TRUGH by tggnr (garantinr)
	 * 
	 * @param id
	 * @param errorStackTrace
	 * @return a List of {@link TrughDao}
	 */
	public List findByIdSearch(String id, StringBuffer errorStackTrace);

}
