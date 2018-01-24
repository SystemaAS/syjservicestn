package no.systema.jservices.tvinn.sad.altinn.proxy;

import java.net.URI;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Implementation for https://www.altinn.no/api/Help
 * 
 * @author Fredrik Möller
 * @date 2018
 */
public class ActionsUriBuilder {

	/**
	 * Gets the user profile of the currently authenticated user.
	 * 
	 * @param host
	 * @param orgnr
	 * @return URI, ex. GET {orgno}/profile
	 */
	public static URI profile(String host, int orgnr) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(host)
				.path("/api/{who}/profile")
				.build()
				.expand(orgnr)
				.encode();

		return uriComponents.toUri();

	}
	
	/**
	 * Gets all messages for the given 'who', here orgnr. These can optionally be retrieved in the language specified.
	 * 
	 * @param host
	 * @param orgnr
	 * @return URI, ex. GET {who}/Messages?language={language}
	 */
	public static URI messages(String host, int orgnr) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(host)
				.path("/api/{who}/messages")
				.build()
				.expand(orgnr)
				.encode();

		return uriComponents.toUri();

	}	
	
}
