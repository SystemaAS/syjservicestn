package no.systema.jservices.tvinn.sad.brreg.proxy.entities.TOREMOVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "organisasjonsnummer", "navn", "registreringsdatoEnhetsregisteret", "organisasjonsform",
		"hjemmeside", "registrertIFrivillighetsregisteret", "registrertIMvaregisteret", "registrertIForetaksregisteret",
		"registrertIStiftelsesregisteret", "antallAnsatte", "institusjonellSektorkode", "naeringskode1", "postadresse",
		"forretningsadresse", "konkurs", "underAvvikling", "underTvangsavviklingEllerTvangsopplosning",
		"overordnetEnhet", "links" })

/**
 * 
 * GENERATED on http://data.brreg.no/enhetsregisteret/enhet/974760673.json using
 * http://www.jsonschema2pojo.org/
 * 
 * Sourcetype : JSON Annotation style: Jackson 1.x
 * 
 * Preferred to be generated from json-schema, but delivered schema seems corrupt.
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 21, 2016
 *
 */
public class Enhet {

	@JsonProperty("organisasjonsnummer")
	private Integer organisasjonsnummer;
	@JsonProperty("navn")
	private String navn;
	@JsonProperty("registreringsdatoEnhetsregisteret")
	private String registreringsdatoEnhetsregisteret;
	@JsonProperty("organisasjonsform")
	private String organisasjonsform;
	@JsonProperty("hjemmeside")
	private String hjemmeside;
	@JsonProperty("registrertIFrivillighetsregisteret")
	private String registrertIFrivillighetsregisteret;
	@JsonProperty("registrertIMvaregisteret")
	private String registrertIMvaregisteret;
	@JsonProperty("registrertIForetaksregisteret")
	private String registrertIForetaksregisteret;
	@JsonProperty("registrertIStiftelsesregisteret")
	private String registrertIStiftelsesregisteret;
	@JsonProperty("antallAnsatte")
	private Integer antallAnsatte;
	@JsonProperty("institusjonellSektorkode")
	private InstitusjonellSektorkode institusjonellSektorkode;
	@JsonProperty("naeringskode1")
	private Naeringskode1 naeringskode1;
	@JsonProperty("postadresse")
	private Postadresse postadresse;
	@JsonProperty("forretningsadresse")
	private Forretningsadresse forretningsadresse;
	@JsonProperty("konkurs")
	private String konkurs;
	@JsonProperty("underAvvikling")
	private String underAvvikling;
	@JsonProperty("underTvangsavviklingEllerTvangsopplosning")
	private String underTvangsavviklingEllerTvangsopplosning;
	@JsonProperty("overordnetEnhet")
	private Integer overordnetEnhet;
	@JsonProperty("links")
	private List<Link> links = new ArrayList<Link>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The organisasjonsnummer
	 */
	@JsonProperty("organisasjonsnummer")
	public Integer getOrganisasjonsnummer() {
		return organisasjonsnummer;
	}

	/**
	 * 
	 * @param organisasjonsnummer
	 *            The organisasjonsnummer
	 */
	@JsonProperty("organisasjonsnummer")
	public void setOrganisasjonsnummer(Integer organisasjonsnummer) {
		this.organisasjonsnummer = organisasjonsnummer;
	}

	/**
	 * 
	 * @return The navn
	 */
	@JsonProperty("navn")
	public String getNavn() {
		return navn;
	}

	/**
	 * 
	 * @param navn
	 *            The navn
	 */
	@JsonProperty("navn")
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * 
	 * @return The registreringsdatoEnhetsregisteret
	 */
	@JsonProperty("registreringsdatoEnhetsregisteret")
	public String getRegistreringsdatoEnhetsregisteret() {
		return registreringsdatoEnhetsregisteret;
	}

	/**
	 * 
	 * @param registreringsdatoEnhetsregisteret
	 *            The registreringsdatoEnhetsregisteret
	 */
	@JsonProperty("registreringsdatoEnhetsregisteret")
	public void setRegistreringsdatoEnhetsregisteret(String registreringsdatoEnhetsregisteret) {
		this.registreringsdatoEnhetsregisteret = registreringsdatoEnhetsregisteret;
	}

	/**
	 * 
	 * @return The organisasjonsform
	 */
	@JsonProperty("organisasjonsform")
	public String getOrganisasjonsform() {
		return organisasjonsform;
	}

	/**
	 * 
	 * @param organisasjonsform
	 *            The organisasjonsform
	 */
	@JsonProperty("organisasjonsform")
	public void setOrganisasjonsform(String organisasjonsform) {
		this.organisasjonsform = organisasjonsform;
	}

	/**
	 * 
	 * @return The hjemmeside
	 */
	@JsonProperty("hjemmeside")
	public String getHjemmeside() {
		return hjemmeside;
	}

	/**
	 * 
	 * @param hjemmeside
	 *            The hjemmeside
	 */
	@JsonProperty("hjemmeside")
	public void setHjemmeside(String hjemmeside) {
		this.hjemmeside = hjemmeside;
	}

	/**
	 * 
	 * @return The registrertIFrivillighetsregisteret
	 */
	@JsonProperty("registrertIFrivillighetsregisteret")
	public String getRegistrertIFrivillighetsregisteret() {
		return registrertIFrivillighetsregisteret;
	}

	/**
	 * 
	 * @param registrertIFrivillighetsregisteret
	 *            The registrertIFrivillighetsregisteret
	 */
	@JsonProperty("registrertIFrivillighetsregisteret")
	public void setRegistrertIFrivillighetsregisteret(String registrertIFrivillighetsregisteret) {
		this.registrertIFrivillighetsregisteret = registrertIFrivillighetsregisteret;
	}

	/**
	 * 
	 * @return The registrertIMvaregisteret
	 */
	@JsonProperty("registrertIMvaregisteret")
	public String getRegistrertIMvaregisteret() {
		return registrertIMvaregisteret;
	}

	/**
	 * 
	 * @param registrertIMvaregisteret
	 *            The registrertIMvaregisteret
	 */
	@JsonProperty("registrertIMvaregisteret")
	public void setRegistrertIMvaregisteret(String registrertIMvaregisteret) {
		this.registrertIMvaregisteret = registrertIMvaregisteret;
	}

	/**
	 * 
	 * @return The registrertIForetaksregisteret
	 */
	@JsonProperty("registrertIForetaksregisteret")
	public String getRegistrertIForetaksregisteret() {
		return registrertIForetaksregisteret;
	}

	/**
	 * 
	 * @param registrertIForetaksregisteret
	 *            The registrertIForetaksregisteret
	 */
	@JsonProperty("registrertIForetaksregisteret")
	public void setRegistrertIForetaksregisteret(String registrertIForetaksregisteret) {
		this.registrertIForetaksregisteret = registrertIForetaksregisteret;
	}

	/**
	 * 
	 * @return The registrertIStiftelsesregisteret
	 */
	@JsonProperty("registrertIStiftelsesregisteret")
	public String getRegistrertIStiftelsesregisteret() {
		return registrertIStiftelsesregisteret;
	}

	/**
	 * 
	 * @param registrertIStiftelsesregisteret
	 *            The registrertIStiftelsesregisteret
	 */
	@JsonProperty("registrertIStiftelsesregisteret")
	public void setRegistrertIStiftelsesregisteret(String registrertIStiftelsesregisteret) {
		this.registrertIStiftelsesregisteret = registrertIStiftelsesregisteret;
	}

	/**
	 * 
	 * @return The antallAnsatte
	 */
	@JsonProperty("antallAnsatte")
	public Integer getAntallAnsatte() {
		return antallAnsatte;
	}

	/**
	 * 
	 * @param antallAnsatte
	 *            The antallAnsatte
	 */
	@JsonProperty("antallAnsatte")
	public void setAntallAnsatte(Integer antallAnsatte) {
		this.antallAnsatte = antallAnsatte;
	}

	/**
	 * 
	 * @return The institusjonellSektorkode
	 */
	@JsonProperty("institusjonellSektorkode")
	public InstitusjonellSektorkode getInstitusjonellSektorkode() {
		return institusjonellSektorkode;
	}

	/**
	 * 
	 * @param institusjonellSektorkode
	 *            The institusjonellSektorkode
	 */
	@JsonProperty("institusjonellSektorkode")
	public void setInstitusjonellSektorkode(InstitusjonellSektorkode institusjonellSektorkode) {
		this.institusjonellSektorkode = institusjonellSektorkode;
	}

	/**
	 * 
	 * @return The naeringskode1
	 */
	@JsonProperty("naeringskode1")
	public Naeringskode1 getNaeringskode1() {
		return naeringskode1;
	}

	/**
	 * 
	 * @param naeringskode1
	 *            The naeringskode1
	 */
	@JsonProperty("naeringskode1")
	public void setNaeringskode1(Naeringskode1 naeringskode1) {
		this.naeringskode1 = naeringskode1;
	}

	/**
	 * 
	 * @return The postadresse
	 */
	@JsonProperty("postadresse")
	public Postadresse getPostadresse() {
		return postadresse;
	}

	/**
	 * 
	 * @param postadresse
	 *            The postadresse
	 */
	@JsonProperty("postadresse")
	public void setPostadresse(Postadresse postadresse) {
		this.postadresse = postadresse;
	}

	/**
	 * 
	 * @return The forretningsadresse
	 */
	@JsonProperty("forretningsadresse")
	public Forretningsadresse getForretningsadresse() {
		return forretningsadresse;
	}

	/**
	 * 
	 * @param forretningsadresse
	 *            The forretningsadresse
	 */
	@JsonProperty("forretningsadresse")
	public void setForretningsadresse(Forretningsadresse forretningsadresse) {
		this.forretningsadresse = forretningsadresse;
	}

	/**
	 * 
	 * @return The konkurs
	 */
	@JsonProperty("konkurs")
	public String getKonkurs() {
		return konkurs;
	}

	/**
	 * 
	 * @param konkurs
	 *            The konkurs
	 */
	@JsonProperty("konkurs")
	public void setKonkurs(String konkurs) {
		this.konkurs = konkurs;
	}

	/**
	 * 
	 * @return The underAvvikling
	 */
	@JsonProperty("underAvvikling")
	public String getUnderAvvikling() {
		return underAvvikling;
	}

	/**
	 * 
	 * @param underAvvikling
	 *            The underAvvikling
	 */
	@JsonProperty("underAvvikling")
	public void setUnderAvvikling(String underAvvikling) {
		this.underAvvikling = underAvvikling;
	}

	/**
	 * 
	 * @return The underTvangsavviklingEllerTvangsopplosning
	 */
	@JsonProperty("underTvangsavviklingEllerTvangsopplosning")
	public String getUnderTvangsavviklingEllerTvangsopplosning() {
		return underTvangsavviklingEllerTvangsopplosning;
	}

	/**
	 * 
	 * @param underTvangsavviklingEllerTvangsopplosning
	 *            The underTvangsavviklingEllerTvangsopplosning
	 */
	@JsonProperty("underTvangsavviklingEllerTvangsopplosning")
	public void setUnderTvangsavviklingEllerTvangsopplosning(String underTvangsavviklingEllerTvangsopplosning) {
		this.underTvangsavviklingEllerTvangsopplosning = underTvangsavviklingEllerTvangsopplosning;
	}

	/**
	 * 
	 * @return The overordnetEnhet
	 */
	@JsonProperty("overordnetEnhet")
	public Integer getOverordnetEnhet() {
		return overordnetEnhet;
	}

	/**
	 * 
	 * @param overordnetEnhet
	 *            The overordnetEnhet
	 */
	@JsonProperty("overordnetEnhet")
	public void setOverordnetEnhet(Integer overordnetEnhet) {
		this.overordnetEnhet = overordnetEnhet;
	}

	/**
	 * 
	 * @return The links
	 */
	@JsonProperty("links")
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * 
	 * @param links
	 *            The links
	 */
	@JsonProperty("links")
	public void setLinks(List<Link> links) {
		this.links = links;
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