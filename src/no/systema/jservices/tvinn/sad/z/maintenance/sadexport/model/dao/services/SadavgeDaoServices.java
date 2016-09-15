package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services;

import java.util.List;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;

/**
 * 
 * @author Fredrik Möller
 * @date 2016-08-08
 * 
 */
public interface SadavgeDaoServices extends IDaoServices { 
	/**
	 * Find by Sadavge ids, @see {@link SadavgeDao}
	 * @param id , AGTANR
	 * @param kode, AGAKD
	 * @param sekv, AGSKV
	 * @param errorStackTrace
	 * @return
	 */
	public List findByIds (String id, String kode, String sekv, StringBuffer errorStackTrace );


}
