package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;

public interface UnderEnhetCSVRepository {

	/**
	 * Retrieve a UnderEnhet by orgnr
	 * 
	 * @param orgNr, Integer
	 * @return HovedEnhet
	 */
	public UnderEnhet get(Integer orgNr) throws IOException; 
	
	/**
	 * Download the underenheter.csv from data.brreg.no
	 * 
	 */
	public void downloadCSVFile() throws IOException;
	
	/**
	 * Check if underenheter.csv exist in path and create InputStream
	 * 
	 */
	public void loadCSVFileFromPath() throws IOException;
	
	/**
	 * Setting RestTemplate, primary used for injection in tests.
	 * 
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate);	
	
	/**
	 * Set Map to null.
	 * The map that is holding Enheter.
	 * 
	 */
	public void clearMap();
}
