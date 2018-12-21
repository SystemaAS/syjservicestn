package no.systema.jservices.tvinn.sad.brreg.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.client.RestClientException;

import no.systema.jservices.common.brreg.proxy.entities.IEnhet;
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
	public List getInvalidaKunderEnhetsRegisteret() throws RestClientException, IOException;
	
	
	/**
	 * Retrieve a Enhet, checking in Hoved- and Underenhetsregister.
	 * Using brreg.no synchronous API. 
	 * 
	 * @param orgnr
	 * @return a Enhet, if exists, else null.
	 */
	public IEnhet get(String orgnr) throws RestClientException, IOException ;

}
