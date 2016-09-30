package no.systema.jservices.tvinn.sad.brreg.proxy;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;
import no.systema.main.util.constants.AppConstants;
import no.systema.main.util.mail.Mail;

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
	private static final String HTTP_CLIENT_ERROR_MESSAGE = "HttpClientErrorException in data.brreg.no response on: ";
	private static final String REST_CLIENT_ERROR_MESSAGE = "RestClientErrorException in data.brreg.no response on: ";
	private static final int READ_TIMEOUT = Constants.BRREG_READ_TIMEOUT;
	private static final int CONNECT_TIMEOUT = Constants.BRREG_CONNECT_TIMEOUT;

	/**
	 * Constructor injection for enabling easier testing.
	 * 
	 * @param serviceUrl
	 */
	public OppslagHovedenhetRequest(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	
	/**
	 * Get Hovedenhet created from JSON from data.brreg.no.
	 * 
	 * @param orgNr
	 * @return {@link Hovedenhet}
	 */
	public Hovedenhet getHovedenhetRecord(String orgNr) {
		Hovedenhet hovedenhet = null;
		if (!passSanityCheck(orgNr)) {
			return hovedenhet;
		}
		
		StringBuffer urlString = new StringBuffer();
		RestTemplate restTemplate = getRestTemplate();
		urlString.append(serviceUrl);
		urlString.append(orgNr);
		urlString.append(JSON_FORMAT);

		try {
			
			hovedenhet = restTemplate.getForObject(urlString.toString(), Hovedenhet.class);
			
		} catch (HttpClientErrorException ex) {
			logger.info(HTTP_CLIENT_ERROR_MESSAGE + urlString.toString()+ ex.getStatusCode() + ex.getStatusText());
			//continue
		} catch (RestClientException ex) {  
			logger.info(REST_CLIENT_ERROR_MESSAGE + urlString.toString()+ ex.getRootCause());
			if(ex.getCause()  instanceof UnknownHostException) {
				//problems....
				if (AppConstants.SEND_MAIL_TO_SUPPORT_BOX) {
					sendMail(urlString, ex);
				}
				throw ex;  
				
			}
			//continue
		}
		
		return hovedenhet;

	}


	private void sendMail(StringBuffer urlString, RestClientException ex) {
		logger.info(ex.getCause()+ ":: Sending mail to support from:"+AppConstants.MAIL_USERNAME+ " to:"+AppConstants.MAIL_BOX_SUPPORT);

		Mail mail = new Mail();
		StringBuilder subject = new StringBuilder("Brønnøysundregisteret og Enhetsregisteret sere ut til å ha problemer.");
		StringBuilder message = new StringBuilder("eSpedsg kan ikke få data på denne url:");
		message.append("\n\n\n\n");
		message.append("::Detta mail har skickats av eSpedsg.::");
		message.append("\n");
		message.append("::fra:"+AppConstants.MAIL_USERNAME);
		message.append(" til:"+AppConstants.MAIL_BOX_SUPPORT+"::");
		mail.sendMail(AppConstants.MAIL_BOX_SUPPORT,subject.toString(), message.toString());

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
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        return factory;
    }

}
