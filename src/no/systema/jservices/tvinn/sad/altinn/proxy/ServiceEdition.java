package no.systema.jservices.tvinn.sad.altinn.proxy;

/**
 * Enum for possible serviceedition in Altinn <br><br>
 * To use in combination with {@linkplain ServiceCode}
 * 
 * For full list @see {@linkplain ActionsServiceManager} getMetadata
 * 
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
public enum ServiceEdition {
	/**
	 * 171208
	 */
	Dagsobjor("171208");
	
	private final String code;

	ServiceEdition(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
