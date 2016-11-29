package no.systema.jservices.tvinn.sad.brreg.proxy;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.mail.GMail;
import no.systema.jservices.common.mail.GMailProperties;
import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;
import no.systema.main.util.ApplicationPropertiesUtil;
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
	// For test
	//private static final String READ_TIMEOUT = "6000";
	//private static final String CONNECT_TIMEOUT = "6000";
	private static final String READ_TIMEOUT = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url.read.timeout");
	private static final String CONNECT_TIMEOUT = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url.connect.timeout");
	private HovedEnhetCSVRepository hovedEnhetCSVRepository = null;

	/**
	 * Constructor injection for enabling easier testing.
	 * 
	 * @param serviceUrl
	 * @param kalleanka2 
	 */
	public OppslagHovedenhetRequest(String serviceUrl, HovedEnhetCSVRepository hovedEnhetCSVRepository) {
		this.serviceUrl = serviceUrl;
		this.hovedEnhetCSVRepository = hovedEnhetCSVRepository;
	}
	
	
	/**
	 * Get Hovedenhet created from JSON from data.brreg.no.
	 * 
	 * @param orgNr
	 * @param useApi boolean, true if accessing brreg.no api, false if using CSV-file
	 * @return {@link Hovedenhet}
	 * @throws RestClientException
	 */
	public Hovedenhet getHovedenhetRecord(String orgNr, boolean useApi) throws RestClientException{
		Hovedenhet hovedenhet = null;
		if (!passSanityCheck(orgNr)) {
			logger.info("Organisasjonnummer: "+orgNr+" har feilaktig lengde, kan være maksimalt 9 sifre.");
			return hovedenhet;
		}
		if (useApi) {
			hovedenhet = getHovedEnhetFromAPI(orgNr);			
		} else {
			hovedenhet = getHovedEnhetFromCVS(orgNr);	
		}
		
		return hovedenhet;
	}


	private Hovedenhet getHovedEnhetFromCVS(String orgNr) {
		Hovedenhet hovedenhet = null;
		hovedenhet =  hovedEnhetCSVRepository.get(new Integer(orgNr));
		
		return hovedenhet;
	}


	private Hovedenhet getHovedEnhetFromAPI(String orgNr) {
		Hovedenhet hovedenhet = null;
		StringBuffer urlString = new StringBuffer();
		RestTemplate restTemplate = getRestTemplate(); // TODO: REview this one,
														// cache it some how
		urlString.append(serviceUrl);
		urlString.append(orgNr);
		urlString.append(JSON_FORMAT);

		try {

			hovedenhet = restTemplate.getForObject(urlString.toString(), Hovedenhet.class);

		} catch (RestClientException ex) {
			logger.info("RestClientErrorException in data.brreg.no response on:" + urlString.toString() + " :Exception=" + ex);
			if (ex instanceof ResourceAccessException) {
				if (GMailProperties.SEND_MAIL_TO_SUPPORT_BOX) {
					sendMail(urlString, ex);
				}
				throw ex;
			} 
			if (ex instanceof HttpStatusCodeException) {
				HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
				if (httpException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					// continue;
				}
			}
		}
		return hovedenhet;

	}

	private void sendMail(StringBuffer urlString, RestClientException ex) {
		logger.info(ex.getCause()+ ":: Sending mail to support from:"+GMailProperties.MAIL_USERNAME+ " to:"+GMailProperties.MAIL_BOX_SUPPORT);

		GMail mail = new GMail();
		StringBuilder subject = new StringBuilder("Brønnøysundregisteret og Enhetsregisteret sere ut til å ha problemer.");
		StringBuilder message = new StringBuilder("eSpedsg kan ikke få data på denne url:"+urlString);
		message.append("\n\n\n\n");
		message.append("::Detta mail har skickats av eSpedsg.::");
		message.append("\n");
		message.append("::fra:"+GMailProperties.MAIL_USERNAME);
		message.append(" til:"+GMailProperties.MAIL_BOX_SUPPORT+"::");
		mail.sendMail(GMailProperties.MAIL_BOX_SUPPORT,subject.toString(), message.toString());

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

    private RestTemplate getRestTemplate() {    	
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(new Integer(READ_TIMEOUT));
        factory.setConnectTimeout(new Integer(CONNECT_TIMEOUT));
        return factory;
    }

}
