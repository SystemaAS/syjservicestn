package no.systema.jservices.tvinn.sad.brreg.services;

import java.util.List;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.tvinn.sad.brreg.proxy.OppslagEnhetRequest;

/**
 * 
 * Services using misc API resources in data.brreg.no
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 22, 2016
 *
 */
public interface BrregRegisterServices {

	/**
	 * Retrieve kunder from {@link CundfDaoServices} where data is incorrupt related 
	 * to service {@link OppslagEnhetRequest}
	 * 
	 * 
	 */
	public List getInvalidaKunderEnhetsRegisteret();
	
	
	/**
	 * Retrieve a Enhet, checking in Hoved- and Underenhetsregister.
	 * 
	 * @param orgnr
	 * @return a Enhet, if exists, else null.
	 */
	public Enhet get(String orgnr);

}
