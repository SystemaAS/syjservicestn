package no.systema.jservices.tvinn.sad.altinn.proxy;

import static de.otto.edison.hal.EmbeddedTypeInfo.withEmbedded;
import static de.otto.edison.hal.HalParser.parse;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;

import de.otto.edison.hal.HalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.entities.MessagesHalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.entities.MetadataHalRepresentation;

/**
 * Helper class for managing Hal stuff.
 * 
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
public class HalHelper {
	private static Logger logger = Logger.getLogger(HalHelper.class.getName());

	/**
	 * Return Hal representations of Messages
	 * 
	 * @param body
	 * @return List<MessagesHalRepresentation>  messages
	 * @throws IOException
	 */
	public static List<MessagesHalRepresentation> getMessages(String body) throws IOException {
        final HalRepresentation result = parse(body)
                .as(HalRepresentation.class, withEmbedded("messages", MessagesHalRepresentation.class));
        final List<MessagesHalRepresentation> embeddedMessages = result.getEmbedded().getItemsBy("messages", MessagesHalRepresentation.class);
        
//        embeddedMessages.forEach((message) -> logger.info(ReflectionToStringBuilder.toString(message)));		
        
        return embeddedMessages;

	}
	
	/**
	 * Return Hal representations of Message
	 * 
	 * @param body
	 * @return List<MessagesHalRepresentation>  messages
	 * @throws IOException
	 */
	public static MessagesHalRepresentation getMessage(String body) throws IOException {
        final MessagesHalRepresentation result = parse(body)
                .as(MessagesHalRepresentation.class);//, withEmbedded("messages", MessagesHalRepresentation.class));
        
//        embeddedMessages.forEach((message) -> logger.info(ReflectionToStringBuilder.toString(message)));		
        
        return result;

	}	
	
	
	

	/**
	 * Return Hal representations of metadata
	 * 
	 * @param body
	 * @return List<MetadataHalRepresentation> embeddedMetadata
	 * @throws IOException
	 */
	public static List<MetadataHalRepresentation> getMetadata(String body) throws IOException {
        final HalRepresentation result = parse(body)
                .as(MetadataHalRepresentation.class, withEmbedded("metadata", MetadataHalRepresentation.class));
        final List<MetadataHalRepresentation> embeddedMetadata = result.getEmbedded().getItemsBy("metadata", MetadataHalRepresentation.class);
   
//        embeddedMetadata.forEach((metadata) -> logger.info(ReflectionToStringBuilder.toString(metadata)));		
        
        return embeddedMetadata;
	}

}
