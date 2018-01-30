package no.systema.jservices.tvinn.sad.altinn.proxy;

import static de.otto.edison.hal.HalParser.parse;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import de.otto.edison.hal.Link;
import no.systema.jservices.tvinn.sad.altinn.entities.ApiKey;
import no.systema.jservices.tvinn.sad.altinn.entities.MessagesHalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.entities.MetadataHalRepresentation;

/**
 * The responsible service manager for accessing resources inside www.altinn.no
 * 
 * Implementing part of actions found here: https://www.altinn.no/api/Help
 * 
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
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
	 * @return List<MessagesHalRepresentation>
	 */
	public List<MessagesHalRepresentation> getMessages(int orgnr) {
		URI uri = ActionsUriBuilder.messages(authorization.getHost(), orgnr);
		return getMessages(uri, orgnr);
	}
	
	/**
	 * Get all message for orgnr and specific {@link ServiceOwner}
	 * 
	 * @see {@link ActionsUriBuilder}
	 * @param orgnr
	 * @param serviceOwner
	 * @return List<MessagesHalRepresentation>
	 */
	public List<MessagesHalRepresentation> getMessages(int orgnr, ServiceOwner serviceOwner) {
		URI uri = ActionsUriBuilder.messages(authorization.getHost(), orgnr,serviceOwner);
		return getMessages(uri, orgnr);
	}	
	
	/**
	 * Get all message for orgnr and specific {@link ServiceOwner}, {@link ServiceOwner}, {@link ServiceEdition}
	 * 
	 * @see {@link ActionsUriBuilder}
	 * @param orgnr
	 * @param serviceOwner
	 * @param serviceCode
	 * @param serviceEdition
	 * @return List<MessagesHalRepresentation>
	 */
	public List<MessagesHalRepresentation> getMessages(int orgnr, ServiceOwner serviceOwner, ServiceCode serviceCode, ServiceEdition serviceEdition) {
		URI uri = ActionsUriBuilder.messages(authorization.getHost(), orgnr,serviceOwner, serviceCode, serviceEdition);
		return getMessages(uri, orgnr);
	}	
	
	
	
	
	/**
	 * Gets the list of available API-services in Altinn.
	 * 
	 * @see {@link ActionsUriBuilder}
	 * @return List<MetadataHalRepresentation>
	 */
	public List<MetadataHalRepresentation> getMetadata() {
		URI uri = ActionsUriBuilder.metadata(authorization.getHost());
		return getMetadata(uri);
	}
	
	
//	private MessagesHalRepresentation getMessages(URI uri, int orgnr){
//		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
//		ResponseEntity<String> responseEntity = null;
//		
//		try {
//			logger.info("getMessage for " + orgnr);
//			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); 
//
//			if (responseEntity.getStatusCode() != HttpStatus.OK) {
//				logger.error("Error in getMessage for " + uri);
//				throw new RuntimeException(responseEntity.getStatusCode().toString());
//			}
//
//			logger.info("responseEntity.getBody"+responseEntity.getBody());
//	
//	        final HalRepresentation result = parse(responseEntity.getBody())
//	                .as(HalRepresentation.class, withEmbedded("messages", MessagesHalRepresentation.class));
//	        final List<MessagesHalRepresentation> embeddedItems = result.getEmbedded().getItemsBy("messages", MessagesHalRepresentation.class);
//	        
//	        embeddedItems.forEach((message) -> System.out.println("xx:"+ReflectionToStringBuilder.toString(message)));		
//
//	        
//			return embeddedItems.get(0);  //TODO
//
//		} catch (Exception e) {
//			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
//			logger.warn(errMessage, e);
//			throw new RuntimeException(errMessage);
//		}
//		
//	}

	private List<MessagesHalRepresentation> getMessages(URI uri, int orgnr){
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> responseEntity = null;
		
		try {

			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); 

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				logger.error("Error in getMessage for " + uri);
				throw new RuntimeException(responseEntity.getStatusCode().toString());
			}
			logger.info("responseEntity.getBody"+responseEntity.getBody());
	
	        return HalHelper.getMessages(responseEntity.getBody());
	        
		} catch (Exception e) {
			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
			logger.warn(errMessage, e);
			throw new RuntimeException(errMessage);
		}
		
	}	
	
	private List<MetadataHalRepresentation> getMetadata(URI uri){
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> responseEntity = null;
		
		try {

			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); 

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				logger.error("Error in getMessage for " + uri);
				throw new RuntimeException(responseEntity.getStatusCode().toString());
			}
			logger.info("responseEntity.getBody"+responseEntity.getBody());
	
	        return HalHelper.getMetadata(responseEntity.getBody());

		} catch (Exception e) {
			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
			logger.warn(errMessage, e);
			throw new RuntimeException(errMessage);
		}
		
	}
	
	/*
	 * Metadata for specific message, e.i. MessagesHalRepresentation
	 */
	private MetadataHalRepresentation getMetadata(MessagesHalRepresentation message){
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> responseEntity = null;
		
		try {

			Optional<Link> link = message.getLinks().getLinkBy("metadata");
			responseEntity = restTemplate.exchange(link.get().getHref(), HttpMethod.GET, entityHeadersOnly, String.class); 

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				logger.error("Error in getMessage for " + link.get().getHref());
				throw new RuntimeException(responseEntity.getStatusCode().toString());
			}
			logger.info("responseEntity.getBody"+responseEntity.getBody());
	
	        final MetadataHalRepresentation result = parse(responseEntity.getBody())
	                .as(MetadataHalRepresentation.class);
	        
			return result;

		} catch (Exception e) {
			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
			logger.warn(errMessage, e);
			throw new RuntimeException(errMessage);
		}
		
	}
	
}
