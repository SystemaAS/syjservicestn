package no.systema.jservices.tvinn.sad.brreg.csv;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.HovedEnhet;

public interface HovedEnhetCSVRepository {

	/**
	 * Retrieve a HovenEnhet by orgnr
	 * 
	 * @param orgNr, Integer
	 * @return HovedEnhet
	 */
	public HovedEnhet get(Integer orgNr); 
	
	/**
	 * Download the hovedenheter.csv from data.brreg.no
	 * 
	 */
	public void downloadCSVFile();
	
	/**
	 * Check if hovedenheter.csv exist in path and create InputStream
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
