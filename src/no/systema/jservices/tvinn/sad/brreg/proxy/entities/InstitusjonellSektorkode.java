package no.systema.jservices.tvinn.sad.brreg.proxy.entities;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "kode", "beskrivelse" })
public class InstitusjonellSektorkode {

	@JsonProperty("kode")
	private String kode;
	@JsonProperty("beskrivelse")
	private String beskrivelse;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The kode
	 */
	@JsonProperty("kode")
	public String getKode() {
		return kode;
	}

	/**
	 * 
	 * @param kode
	 *            The kode
	 */
	@JsonProperty("kode")
	public void setKode(String kode) {
		this.kode = kode;
	}

	/**
	 * 
	 * @return The beskrivelse
	 */
	@JsonProperty("beskrivelse")
	public String getBeskrivelse() {
		return beskrivelse;
	}

	/**
	 * 
	 * @param beskrivelse
	 *            The beskrivelse
	 */
	@JsonProperty("beskrivelse")
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}