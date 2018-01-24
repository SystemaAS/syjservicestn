package no.systema.jservices.tvinn.sad.altinn.proxy;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service("actionsservicemanager")
public class ActionsServiceManager {
	private static Logger logger = Logger.getLogger(ActionsServiceManager.class.getName());
	
	@Autowired
	private Authorization authorization;

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}	
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Get all messages for orgnr
	 * 
	 * @see {@link ActionsUriBuilder}
	 * @param orgnr
	 * @return ?
	 */
	public String getMessages(int orgnr) {
		URI uri = ActionsUriBuilder.messages(authorization.getHost(), orgnr);
		return getBody(uri, orgnr);
	}
	
	/**
	 * Get user profile for orgnr
	 * 
	 * @see {@link ActionsUriBuilder}
	 * @param orgnr
	 * @return ?
	 */
	public String getProfile(int orgnr) {
		URI uri = ActionsUriBuilder.profile(authorization.getHost(), orgnr);
		return getBody(uri, orgnr);

	}

	private String getBody(URI uri, int orgnr) {
		HttpEntity<ApiKeyDto> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> response = null;

		try {
			logger.info("getBody for " + uri);

			response = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); // TODO

			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			}

			logger.error("Error in getBody for " + uri);
			throw new RuntimeException(response.getStatusCode().toString());

		} catch (HttpClientErrorException e) {
			String message = String.format("Authorization request failed: %s", e.getLocalizedMessage());
			logger.warn(message, e);
			throw new RuntimeException(message);
		}
	}
	
	
}
