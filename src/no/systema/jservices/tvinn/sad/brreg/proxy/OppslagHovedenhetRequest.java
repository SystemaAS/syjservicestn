package no.systema.jservices.tvinn.sad.brreg.proxy;

import org.apache.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;
import no.systema.main.util.constants.AppConstants;

/**
 * Synchronous request against data.brreg.no and the service Oppslag på
 * Hovedenhet
 * 
 * Hovedenheter
 * 
 * Enkeltpersonforetak, foreninger, selskap, sameier og andre som er registrert
 * i Enhetsregisteret. Enhet på øverste nivå i registreringsstrukturen i
 * Enhetsregisteret. Identifiseres med organisasjonsnummer.
 * 
 * 
 * Service: http://data.brreg.no/enhetsregisteret/enhet/{orgnr}.{format}, ex:
 * http://data.brreg.no/enhetsregisteret/enhet/974760673.json
 * 
 * format: json, (optional xml)
 * 
 * @author Fredrik Möller
 * @date Sep 21, 2016
 *
 */
public class OppslagHovedenhetRequest {
	private static Logger logger = Logger.getLogger(OppslagHovedenhetRequest.class.getName());

	private String serviceUrl = null;
	private static final String JSON_FORMAT = ".json";
	private static final String ERROR_MESSAGE = "HttpClientErrorException in data.brreg.no response on: ";

	/**
	 * Constructor injection for enabling easier testing.
	 * 
	 * @param serviceUrl
	 */
	public OppslagHovedenhetRequest(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	
	public Hovedenhet getHovedenhetRecord(String orgNr) {
		Hovedenhet hovedenhet = null;
		if (!passSanityCheck(orgNr)) {
			return hovedenhet;
		}
		
		StringBuffer urlString = new StringBuffer();
		RestTemplate restTemplate = new RestTemplate();
		urlString.append(serviceUrl);
		urlString.append(orgNr);
		urlString.append(JSON_FORMAT);

		try {
			
			hovedenhet = restTemplate.getForObject(urlString.toString(), Hovedenhet.class);
			
		} catch (HttpClientErrorException ex) {
			logger.info(ERROR_MESSAGE + urlString.toString()+ ex.getStatusCode() + ex.getStatusText());
			//continue
		}

		return hovedenhet;

		// TODO Add own exception handling on invalid/change JSON structure

	}

	private boolean passSanityCheck(String orgNr) {
		if (!isNumber(orgNr)) {
			return false;
		}

		if(!hasCorrectLenght(orgNr)) {
			return false;
		}
		return true;
	}

	private boolean isNumber(String orgNr) {
		try {
			Integer.parseInt(orgNr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}		
	}

	private boolean hasCorrectLenght(String orgNr) {
		if (orgNr.length() > 9){  
			return false;
		} 
		return true;
	}

}
