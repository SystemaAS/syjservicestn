package no.systema.jservices.tvinn.sad.altinn.proxy;

import static de.otto.edison.hal.EmbeddedTypeInfo.withEmbedded;
import static de.otto.edison.hal.HalParser.parse;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

import de.otto.edison.hal.HalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.entities.ApiKey;
import no.systema.jservices.tvinn.sad.altinn.entities.MessagesHalRepresentation;

@Service("actionsservicemanager")
public class ActionsServiceManager {
	private static Logger logger = Logger.getLogger(ActionsServiceManager.class.getName());
	
	@Autowired
	private Authorization authorization;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
	    return rt;		
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
	public MessagesHalRepresentation getMessages(int orgnr) {
		URI uri = ActionsUriBuilder.messages(authorization.getHost(), orgnr);
		
		return getMessages(uri, orgnr);
		
//		return getBody(uri, orgnr);
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

	/**
	 * Get roles for orgnr
	 * 
	 * @see {@link ActionsUriBuilder}
	 * @param orgnr
	 * @return ?
	 */
	public String getRoles(int orgnr) {
		URI uri = ActionsUriBuilder.roles(authorization.getHost(), orgnr);
		return getBody(uri, orgnr);
	}
	
	
	private String getBody(URI uri, int orgnr) {
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> response = null;

		try {
			logger.info("getBody for " + uri);

			response = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); 

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
	
//	private List<Messages> getMessages(URI uri, int orgnr) {
//		HttpEntity<ApiKeyDto> entityHeadersOnly = authorization.getHttpEntity();
//		List<Messages> messageList = null;
//		try {
//			logger.info("getMessage for " + orgnr);
//
//			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//			
//			ResponseEntity<Resource<List<Messages>>> response = restTemplate.exchange(uri, HttpMethod.GET,entityHeadersOnly, new ParameterizedTypeReference<Resource<List<Messages>>>() {});
//
//
//			if (response.getStatusCode() == HttpStatus.OK) {
//				Resource<List<Messages>> messageListResource = response.getBody();
//				messageList = messageListResource.getContent();
//			}
//
//			return messageList;
//
//		} catch (HttpClientErrorException e) {
//			String message = String.format("Authorization request failed: %s", e.getLocalizedMessage());
//			logger.warn(message, e);
//			throw new RuntimeException(message);
//		}
//	}


	private MessagesHalRepresentation getMessages(URI uri, int orgnr){
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> responseEntity = null;
		
		try {
			logger.info("getMessage for " + orgnr);

			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); 

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				logger.error("Error in getMessage for " + uri);
				throw new RuntimeException(responseEntity.getStatusCode().toString());
			}

			logger.info("responseEntity.getBody"+responseEntity.getBody());
	
	        final HalRepresentation result = parse(responseEntity.getBody())
	                .as(HalRepresentation.class, withEmbedded("messages", MessagesHalRepresentation.class));
	        final List<MessagesHalRepresentation> embeddedItems = result.getEmbedded().getItemsBy("messages", MessagesHalRepresentation.class);
	        
	        embeddedItems.forEach((message) -> System.out.println("xx:"+ReflectionToStringBuilder.toString(message)));		

	        //later get meatadata, kolla TraversonTest
//	        final HalRepresentation hal = traverson(mock)
//	                .startWith("http://example.com/example")
//	                .follow("foo", withVars("test", "bar"))
//	                .getResource()
//	                .get();	        
	        
	        
			return embeddedItems.get(0);  //TODO

		} catch (Exception e) {
			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
			logger.warn(errMessage, e);
			throw new RuntimeException(errMessage);
		}
		
	}
	
}
