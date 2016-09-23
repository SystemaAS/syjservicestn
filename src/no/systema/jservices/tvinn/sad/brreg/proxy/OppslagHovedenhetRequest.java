package no.systema.jservices.tvinn.sad.brreg.proxy;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;
import no.systema.jservices.tvinn.sad.brreg.services.BrregRegisterServicesImpl;

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
 * format: json, xml
 * 
 * @author Fredrik Möller
 * @date Sep 21, 2016
 *
 */
public class OppslagHovedenhetRequest {
	private static Logger logger = Logger.getLogger(OppslagHovedenhetRequest.class.getName());

	private static final String serviceUrl = "http://data.brreg.no/enhetsregisteret/enhet/";
	private static final String JSON_FORMAT = ".json";

	public Hovedenhet getHovedenhetRecord(String orgNr) {
		Hovedenhet hovedenhet = null;
		if (orgNr.length() > 9){  //Sanity check on lenght
			logger.info("Sanity check on lenght; "+orgNr+" length="+orgNr.length());
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
			if (ex.getStatusCode().value() != 404) {
				logger.info("Error in data.brreg.no response on : "+ urlString.toString());
				throw ex;
			}
		}

		return hovedenhet;

		// TODO Add timeout check
		// TODO Add own exception handling on invalid/change JSON structure

	}

}
