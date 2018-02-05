package no.systema.jservices.tvinn.sad.altinn.proxy;

import static de.otto.edison.hal.HalParser.parse;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.otto.edison.hal.HalRepresentation;
import de.otto.edison.hal.Link;
import de.otto.edison.hal.Links;
import no.systema.jservices.tvinn.sad.altinn.entities.ApiKey;
import no.systema.jservices.tvinn.sad.altinn.entities.MessagesHalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.entities.MetadataHalRepresentation;
import no.systema.jservices.tvinn.sad.brreg.csv.FileHelper;
import no.systema.main.util.ApplicationPropertiesUtil;

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
		return getMessages(uri);
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
		return getMessages(uri);
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
		return getMessages(uri);
	}	
	

	//TODO
	public String getXMLRepresentation(MessagesHalRepresentation message) {
		String self = message.getLinks().getLinksBy("self").get(0).getHref();
		
		
		URI uri = URI.create(self);
		HalRepresentation hal = getMessage(uri);
		
		return hal.toString();
	}
	
	public void getAttachment(MessagesHalRepresentation message) {
		Links links = message.getLinks();
		logger.info("message:"+message.getMessageId()+ "links="+links.toString());
		
		
		String self = message.getLinks().getLinksBy("self").get(0).getHref();
		
		logger.info("self="+self);
		
		URI uri = URI.create(self);
		
		//Get message
		MessagesHalRepresentation halMessage = getMessage(uri);

		
		Optional<Link> attachmentLink =halMessage.getLinks().getLinkBy("attachment");
		logger.info("attLink="+attachmentLink);
		
		logger.info("attLink.get().getHref()="+attachmentLink.get().getHref());
		
		uri = URI.create(attachmentLink.get().getHref());
		
		//Prefix Altinn-name with created_date
		StringBuilder writeFile = new StringBuilder(halMessage.getCreatedDate()).append("-").append(attachmentLink.get().getName());
		
		getAttachment(uri, writeFile.toString());

	}	
	
	public void putDagsobjorXMLRepresentationToPath(int orgnr) {
		
		 List<MessagesHalRepresentation> dagsobjors = getMessages(orgnr, ServiceOwner.Skatteetaten, ServiceCode.Dagsobjor, ServiceEdition.Dagsobjor);
//		 dagsobjors.forEach((message) ->  logger.info("dagsobjor for orgnr"+orgnr+", XML="+getXMLRepresentation(message)));
		 
		//TODO
		
	}
	
	public void putDagsobjorPDFRepresentationToPath(int orgnr) {

		List<MessagesHalRepresentation> dagsobjors = getMessages(orgnr, ServiceOwner.Skatteetaten,
				ServiceCode.Dagsobjor, ServiceEdition.Dagsobjor);
		dagsobjors.forEach((message) -> {
			logger.info("message" + message);
			getAttachment(message);
		});



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
	
	private List<MessagesHalRepresentation> getMessages(URI uri){
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
	
	private MessagesHalRepresentation getMessage(URI uri){
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntity();
		ResponseEntity<String> responseEntity = null;
		
		try {

			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entityHeadersOnly, String.class); 

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				logger.error("Error in getMessage for " + uri);
				throw new RuntimeException(responseEntity.getStatusCode().toString());
			}
			logger.info("responseEntity.getBody"+responseEntity.getBody());
	
	        return HalHelper.getMessage(responseEntity.getBody());
	        
		} catch (Exception e) {
			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
			logger.warn(errMessage, e);
			throw new RuntimeException(errMessage);
		}
		
	}	

	
	private void getAttachment(URI uri, String writeFile) {
		String CATALINA_BASE = System.getProperty("catalina.base");
		HttpEntity<ApiKey> entityHeadersOnly = authorization.getHttpEntityFileDownload();
		ResponseEntity<byte[]> responseEntity = null;
		String filePath;

		try {
			logger.info("getAttachment, uri="+uri);
			
			responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, entityHeadersOnly, byte[].class, "1");
			
			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				logger.error("Error in getAttachment for " + uri);
				throw new RuntimeException(responseEntity.getStatusCode().toString());
			} else {

				try {
					filePath = ApplicationPropertiesUtil.getProperty("no.brreg.data.resources.filepath");
				} catch (Exception e1) {
					// TODO Afor testing
					filePath="WebContent/WEB-INF/resources/files/";
					CATALINA_BASE = "";
				}

				ByteArrayInputStream bis = new ByteArrayInputStream(responseEntity.getBody());
				FileOutputStream fos = new FileOutputStream(CATALINA_BASE + filePath + writeFile);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = bis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				bis.close();

			}
			
			logger.info("File: "+ filePath + writeFile + " saved on disk.");

		} catch (Exception e) {
			String errMessage = String.format(" request failed: %s", e.getLocalizedMessage());
			logger.warn(errMessage, e);
			throw new RuntimeException(errMessage);
		}

	}
	
	
	private void getAttachment2(URI uri) {
		
		logger.info("getAttachment2, uri="+uri);

		//For test
//		String filePath="WebContent/WEB-INF/resources/files/";	
		String filePath;
		try {
			filePath = ApplicationPropertiesUtil.getProperty("no.brreg.data.resources.filepath");
		} catch (Exception e1) {
			// TODO Afor testing
			filePath="WebContent/WEB-INF/resources/files/";
		}
		FileHelper fileHelper = new FileHelper(restTemplate);
		try {
			fileHelper.downloadFile(uri.toString(), filePath, "theFile2.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
