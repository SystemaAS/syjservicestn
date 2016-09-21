package no.systema.jservices.tvinn.sad.proxy.brreg;

import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.proxy.brreg.entities.Hovedenhet;

/**
 * Synchronous request against data.brreg.no and the service Oppslag på Hovedenhet
 * 
 * Hovedenheter
 * 
 * Enkeltpersonforetak, foreninger, selskap, sameier og andre som er registrert i Enhetsregisteret. 
 * Enhet på øverste nivå i registreringsstrukturen i Enhetsregisteret. Identifiseres med organisasjonsnummer.
 * 
 * 
 * Service:   http://data.brreg.no/enhetsregisteret/enhet/{orgnr}.{format}, ex: http://data.brreg.no/enhetsregisteret/enhet/974760673.json
 * 
 * format: json, xml
 * 
 * @author Fredrik Möller
 * @date Sep 21, 2016
 *
 */
public class OppslagHovedenhetRequest {

	private static final String serviceUrl = "http://data.brreg.no/enhetsregisteret/enhet/";
	private static final String JSON_FORMAT = ".json";

	public  Hovedenhet getHovedenhetRecord(String orgnr) {
		StringBuffer urlString = new StringBuffer();
		RestTemplate restTemplate = new RestTemplate();
		urlString.append(serviceUrl);
		urlString.append(orgnr);
		urlString.append(JSON_FORMAT);
		
		return restTemplate.getForObject(urlString.toString(), Hovedenhet.class);
		
	}
	
}
