package no.systema.main.util;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

import no.systema.main.context.JServicesAppContext;

/**
 * Help class accessing messages in message.properties
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 *
 */
public class MessageSourceHelper {

	private ApplicationContext context = null;

	public MessageSourceHelper() {
		context = JServicesAppContext.getApplicationContext();
	}
	
	
	/**
	 * Decode key/id into messages from message.properties.
	 * Using Locale default
	 * 
	 * @param id, key in message.properties
	 * @param params
	 * @return decoded message
	 */
	public String getMessage(String id, Object[] params) {
		return context.getMessage(id, params, Locale.getDefault());
	}
	
}
