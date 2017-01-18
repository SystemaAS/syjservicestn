package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.HovedEnhet;

public interface HovedEnhetCSVRepository {

	/**
	 * Retrieve a HovenEnhet by orgnr
	 * 
	 * @param orgNr, Integer
	 * @return HovedEnhet
	 */
	public HovedEnhet get(Integer orgNr) throws IOException; 
	
	/**
	 * Download the hovedenheter.csv from data.brreg.no
	 * 
	 */
	public void downloadCSVFile()  throws IOException;
	
	/**
	 * Check if hovedenheter.csv exist in path and create InputStream
	 * 
	 */
	public void loadCSVFileFromPath()  throws IOException;
	
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
