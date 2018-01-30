package no.systema.jservices.tvinn.sad.altinn.proxy;

/**
 * Enum for possible servicecode in Altinn <br><br>
 * To use in combination with {@link ServiceEdition}
 * 
 * For full list @see {@linkplain ActionsServiceManager} getMetadata
 * 
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
public enum ServiceCode {
	/**
	 * 5012
	 */
	Dagsobjor("5012");
	
	private final String code;

	ServiceCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
