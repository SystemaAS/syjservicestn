package no.systema.jservices.tvinn.sad.altinn.proxy;

/**
 * Enum for possible serviceowner in Altinn <br><br>
 * 
 * For full list @see {@linkplain ActionsServiceManager} getMetadata
 * 
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
public enum ServiceOwner {
	Skatteetaten("SKD"),
	Samlesider("ASF");
	
	private final String code;

	ServiceOwner(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
