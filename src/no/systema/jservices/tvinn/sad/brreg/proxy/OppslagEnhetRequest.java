package no.systema.jservices.tvinn.sad.brreg.proxy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.HovedEnhet;
import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;
import no.systema.jservices.common.mail.GMail;
import no.systema.jservices.common.mail.GMailProperties;
import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.csv.UnderEnhetCSVRepository;

/**
 * Synchronous request against data.brreg.no and the service Oppslag på
 * Enhet
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
public class OppslagEnhetRequest {
	private static Logger logger = Logger.getLogger(OppslagEnhetRequest.class.getName());
	private String serviceUrl = null;
	private static final String JSON_FORMAT = ".json";
	// For test
	//private static final String READ_TIMEOUT = "6000";
	//private static final String CONNECT_TIMEOUT = "6000";
	//private static final String READ_TIMEOUT = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url.read.timeout");
	//private static final String CONNECT_TIMEOUT = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url.connect.timeout");
	private HovedEnhetCSVRepository hovedEnhetCSVRepository = null;
	private UnderEnhetCSVRepository underEnhetCSVRepository = null;
	private static final Integer MAX_ORGNR_LENGHT = new Integer(999999999);

	/**
	 * Constructor injection for enabling easier testing.
	 * 
	 * @param String serviceUrl
	 * @param HovedEnhetCSVRepository hovedEnhetCSVRepository
	 * @param UnderEnhetCSVRepository underEnhetCSVRepository
	 */
	public OppslagEnhetRequest(String serviceUrl, HovedEnhetCSVRepository hovedEnhetCSVRepository, UnderEnhetCSVRepository underEnhetCSVRepository) {
		this.serviceUrl = serviceUrl;
		this.hovedEnhetCSVRepository = hovedEnhetCSVRepository;
		this.underEnhetCSVRepository = underEnhetCSVRepository;
	}
	
	
	/**
	 * Get Enhet created from JSON from data.brreg.no or from CSV-file
	 * 
	 * @param Integer orgNr
	 * @param boolean useApi, true if accessing brreg.no api, false if using CSV-file
	 * @return {@link Enhet} instances can be {@link HovedEnhet} or {@link UnderEnhet}
	 * @throws RestClientException
	 */
	public Enhet getEnhetRecord(Integer orgNr, boolean useApi) throws RestClientException {
		Enhet enhet = null;
		if (!hasCorrectLenght(orgNr)) {
			logger.info("Organisasjonnummer: " + orgNr + " har feilaktig lengde, kan være maksimalt 9 sifre.");
			return enhet;
		}
		if (useApi) {
			enhet = getHovedEnhetFromAPI(orgNr);
			// TODO: Add underenhet from api
		} else {
			enhet = getHovedEnhetFromCVS(orgNr);
			if (enhet == null) {
				enhet = getUnderEnhetFromCVS(orgNr);
				if (enhet != null && (enhet.getOverordnetEnhet() != null)) {
					HovedEnhet he = (HovedEnhet) getHovedEnhetFromCVS(enhet.getOverordnetEnhet());
					if (he != null) {
						enhet.setKonkurs(he.getKonkurs());
						enhet.setRegistrertIMvaregisteret(he.getRegistrertIMvaregisteret());
						enhet.setUnderAvvikling(he.getUnderAvvikling());
						enhet.setUnderTvangsavviklingEllerTvangsopplosning(he.getUnderTvangsavviklingEllerTvangsopplosning());
					}
				}
			}
		}

		return enhet;
	}

	private Enhet getHovedEnhetFromCVS(Integer orgNr) {
		HovedEnhet hovedenhet = null;
		hovedenhet = hovedEnhetCSVRepository.get(orgNr);
		
		return hovedenhet;
	}

	private Enhet getUnderEnhetFromCVS(Integer orgNr) {
		UnderEnhet underEnhet = null;
		underEnhet = underEnhetCSVRepository.get(orgNr);
		return underEnhet;
	}
	
	private Enhet getHovedEnhetFromAPI(Integer orgNr) {
		HovedEnhet hovedenhet = null;
		StringBuffer urlString = new StringBuffer();
		urlString.append(serviceUrl);
		urlString.append(orgNr);
		urlString.append(JSON_FORMAT);

		try {

			hovedenhet = restTemplate.getForObject(urlString.toString(), HovedEnhet.class);

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

	private boolean hasCorrectLenght(Integer orgNr) {
		if (orgNr > MAX_ORGNR_LENGHT){  
			return false;
		} else {
			return true;
		}
	}

	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	@Autowired
	@Required
	public void setRestTemplate(RestTemplate value) {
		this.restTemplate = value;
	}
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}	
    
    
}
