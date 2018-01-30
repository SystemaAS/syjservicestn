package no.systema.jservices.tvinn.sad.altinn.proxy;

import java.net.URI;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Creates URI on https://www.altinn.no/api/Help
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
	 * Gets the list of available API-services in Altinn.
	 * 
	 * @param host
	 * @return URI, ex. GET metadata
	 */
	public static URI metadata(String host) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(host)
				.path("/api/metadata")
				.build()
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

	/**
	 * Gets all messages for the given 'who', here orgnr. These can optionally be retrieved in the language specified.
	 * 
	 * Filtered on Serviceowner, e.g. SKD (=Skatteetaten)
	 * 
	 * @param host
	 * @param orgnr
	 * @param serviceOwneer
	 * @return URI, ex. GET {who}/Messages?language={language}
	 */
	public static URI messages(String host, int orgnr, ServiceOwner serviceOwner) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(host)
				.path("/api/{who}/messages")
			    .query("$filter={value}")
			    .buildAndExpand(orgnr, "ServiceOwner eq \'"+serviceOwner+"\'")
				.encode();

		return uriComponents.toUri();

	}
	
	/**
	 * Gets all messages for the given 'who', here orgnr. These can optionally be retrieved in the language specified.
	 * 
	 * Filtered on Serviceowner, e.g. SKD (=Skatteetaten)
	 * 
	 * @param host
	 * @param orgnr
	 * @param serviceOwneer
	 * @return URI, ex. GET {who}/Messages?language={language}
	 */
	public static URI messages(String host, int orgnr, ServiceOwner serviceOwner, ServiceCode serviceCode, ServiceEdition serviceEdition ) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(host)
				.path("/api/{who}/messages")
			    .query("$filter={expand1}")
//			    .query("$ServiceCode={expand2}")
//			    .query("$ServiceEdition={expand3}")
			    .buildAndExpand(orgnr, "ServiceOwner eq \'"+serviceOwner+"\' and ServiceCode eq \'"+serviceCode.getCode()+"\' and ServiceEdition eq \'"+serviceEdition.getCode()+"\'" )
				.encode();

		return uriComponents.toUri();

//		filter=Country_Region_Code eq 'ES' and Payment_Terms_Code eq '14 DAYS'
		
		
		
	}

	/**
	 * Contains all actions related to the authorization roles
	 * 
	 * @param host
	 * @param orgnr
	 * @return URI, ex. GET {who}/authorization/roles?language={language}
	 */
	public static URI roles(String host, int orgnr) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(host)
				.path("/api/{who}/authorization/roles")
				.build()
				.expand(orgnr)
				.encode();

		return uriComponents.toUri();

	}	
	
}
