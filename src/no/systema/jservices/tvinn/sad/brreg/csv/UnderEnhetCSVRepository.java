package no.systema.jservices.tvinn.sad.brreg.csv;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.UnderEnhet;

public interface UnderEnhetCSVRepository {

	/**
	 * Retrieve a UnderEnhet by orgnr
	 * 
	 * @param orgNr, Integer
	 * @return HovedEnhet
	 */
	public UnderEnhet get(Integer orgNr); 
	
	/**
	 * Download the underenheter.csv from data.brreg.no
	 * 
	 */
	public void downloadCSVFile();
	
	/**
	 * Check if underenheter.csv exist in path and create InputStream
	 * 
	 */
	public void loadCSVFileFromPath();
	
	/**
	 * Setting RestTemplate, primary used for injection in tests.
	 * 
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate);	
}
