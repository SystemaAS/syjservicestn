package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;

public interface HovedEnhetCSVRepository {

	/**
	 * Retrieve a HovenEnhet by orgnr
	 * 
	 * @param orgNr, Integer
	 * @return HovedEnhet
	 */
	public Enhet get(String orgNr) throws IOException; 
	
	/**
	 * Download the hovedenheter.csv from data.brreg.no
	 * 
	 */
	public void downloadFile()  throws IOException;
	
	/**
	 * Setting RestTemplate, primary used for injection in tests.
	 * 
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate);
	
}
