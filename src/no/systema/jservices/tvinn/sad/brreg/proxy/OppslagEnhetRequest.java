package no.systema.jservices.tvinn.sad.brreg.proxy;

import java.io.IOException;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.IEnhet;
import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;
import no.systema.jservices.common.mail.GMail;
import no.systema.jservices.common.mail.GMailProperties;
import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.csv.UnderEnhetCSVRepository;
import no.systema.main.util.ApplicationPropertiesUtil;

/**
 * Synchronous request against data.brreg.no and the service Oppslag på
 * Enhet
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 21, 2016
 *
 */
public class OppslagEnhetRequest {
	private static Logger logger = LoggerFactory.getLogger(OppslagEnhetRequest.class.getName());
	private String serviceUrl = null;
	private static final String JSON_FORMAT = ".json";
	// For test
	//private static final String READ_TIMEOUT = "6000";
	//private static final String CONNECT_TIMEOUT = "6000";
	//private static final String READ_TIMEOUT = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url.read.timeout");
	//private static final String CONNECT_TIMEOUT = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url.connect.timeout");
	private HovedEnhetCSVRepository hovedEnhetCSVRepository = null;
	private UnderEnhetCSVRepository underEnhetCSVRepository = null;
	private RestTemplate restTemplate = null;
	private static final int MAX_ORGNR_LENGHT = 9;
	private final static String ENHETS_REGISTERET_UNDERENHET_URL = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenhetsregisteret.url");


	/**
	 * Constructor injection for enabling easier testing.
	 * @param restTemplate 
	 * 
	 * @param String serviceUrl
	 * @param HovedEnhetCSVRepository hovedEnhetCSVRepository
	 * @param UnderEnhetCSVRepository underEnhetCSVRepository
	 */
	public OppslagEnhetRequest(String serviceUrl, HovedEnhetCSVRepository hovedEnhetCSVRepository, UnderEnhetCSVRepository underEnhetCSVRepository, RestTemplate restTemplate) {
		this.serviceUrl = serviceUrl;
		this.hovedEnhetCSVRepository = hovedEnhetCSVRepository;
		this.underEnhetCSVRepository = underEnhetCSVRepository;
		this.restTemplate = restTemplate;
	}
	
	
	/**
	 * Get Enhet created from JSON from data.brreg.no or from file
	 * 
	 * @param String orgNr
	 * @param boolean useApi, true if accessing brreg.no api, false if using CSV-file
	 * @return {@link IEnhet} instances can be {@link Enhet} or {@link UnderEnhet}
	 * @throws RestClientException
	 */
	public IEnhet getEnhetRecord(String orgNr, boolean useApi) throws RestClientException, IOException {
		IEnhet i_enhet = null;
		if (!hasCorrectLenght(orgNr)) {
			logger.error("Organisasjonnummer: " + orgNr + " har feilaktig lengde, kan være maksimalt 9 sifre.");
			return i_enhet;
		}
		if (useApi) {
			i_enhet = getEnhetFromAPI(orgNr);
			if (i_enhet == null) {
				i_enhet = getUnderEnhetFromAPI(orgNr);
				if (i_enhet != null && (i_enhet.getOverordnetEnhet() != null)) {
					Enhet he = (Enhet) getEnhetFromAPI(i_enhet.getOverordnetEnhet());
					if (he != null) {
						i_enhet.setKonkurs(he.getKonkurs());
						i_enhet.setRegistrertIMvaregisteret(he.getRegistrertIMvaregisteret());
						i_enhet.setUnderAvvikling(he.getUnderAvvikling());
						i_enhet.setUnderTvangsavviklingEllerTvangsopplosning(he.getUnderTvangsavviklingEllerTvangsopplosning());
					}
				}
			}
		} else {
			i_enhet = getEnhet(orgNr);
			if (i_enhet == null) {
				/*Underenhet from API*/
				i_enhet = getUnderEnhetFromAPI(orgNr);
				if (i_enhet != null && (i_enhet.getOverordnetEnhet() != null)) {
					/*Enhet from API*/
					Enhet he = (Enhet) getEnhetFromAPI(i_enhet.getOverordnetEnhet());
					if (he != null) {
						i_enhet.setKonkurs(he.getKonkurs());
						i_enhet.setRegistrertIMvaregisteret(he.getRegistrertIMvaregisteret());
						i_enhet.setUnderAvvikling(he.getUnderAvvikling());
						i_enhet.setUnderTvangsavviklingEllerTvangsopplosning(he.getUnderTvangsavviklingEllerTvangsopplosning());
					}
				}
			}
		}

		return i_enhet;
	}

	private Enhet getEnhet(String orgNr) throws IOException {
		Enhet enhet = null;
		enhet = hovedEnhetCSVRepository.get(orgNr);
		
		return enhet;
	}

	private UnderEnhet getUnderEnhet(String orgNr) throws IOException  {
		UnderEnhet underEnhet = null;
		underEnhet = underEnhetCSVRepository.get(orgNr);
		return underEnhet;
	}
	
	private Enhet getEnhetFromAPI(String orgNr) {
		Enhet hovedenhet = null;
		StringBuffer urlString = new StringBuffer();
		urlString.append(serviceUrl);
		urlString.append(orgNr);

		try {

			hovedenhet = restTemplate.getForObject(urlString.toString(), Enhet.class);

		} catch (RestClientException ex) {
			if (ex instanceof HttpStatusCodeException) {
				HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
				if (!httpException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					logger.error("RestClientException in data.brreg.no response on:" + urlString.toString() + " :Exception=" + ex);
				}
			}
			// continue
		}
		return hovedenhet;

	}

	private UnderEnhet getUnderEnhetFromAPI(String orgNr) {
		UnderEnhet underEnhet = null;
		StringBuffer urlString = new StringBuffer();
		urlString.append(ENHETS_REGISTERET_UNDERENHET_URL);
		urlString.append(orgNr);

		try {

			underEnhet = restTemplate.getForObject(urlString.toString(), UnderEnhet.class);

		} catch (RestClientException ex) {
			if (ex instanceof HttpStatusCodeException) {
				HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
				if (!httpException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					logger.error("RestClientException in data.brreg.no response on:" + urlString.toString() + " :Exception=" + ex);
				}
			}
			// continue
		}
		return underEnhet;

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

	private boolean hasCorrectLenght(String orgNr) {
		if (orgNr.length() > MAX_ORGNR_LENGHT){  
			return false;
		} else {
			return true;
		}
	}

}
