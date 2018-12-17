package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;

public interface UnderEnhetCSVRepository {

	/**
	 * Retrieve a UnderEnhet by orgnr
	 * 
	 * @param orgNr
	 * @return HovedEnhet
	 */
	public UnderEnhet get(String orgNr) throws IOException; 
	
	/**
	 * Download the underenheter.csv from data.brreg.no
	 * 
	 */
	public void downloadFile() throws IOException;
	
	/**
	 * Setting RestTemplate, primary used for injection in tests.
	 * 
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate);	
	
}
