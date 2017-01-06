package no.systema.jservices.tvinn.sad.brreg.proxy.entities.TOREMOVE;

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
@JsonPropertyOrder({ "rel", "href" })
public class Link {

	@JsonProperty("rel")
	private String rel;
	@JsonProperty("href")
	private String href;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The rel
	 */
	@JsonProperty("rel")
	public String getRel() {
		return rel;
	}

	/**
	 * 
	 * @param rel
	 *            The rel
	 */
	@JsonProperty("rel")
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * 
	 * @return The href
	 */
	@JsonProperty("href")
	public String getHref() {
		return href;
	}

	/**
	 * 
	 * @param href
	 *            The href
	 */
	@JsonProperty("href")
	public void setHref(String href) {
		this.href = href;
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
