package no.systema.jservices.tvinn.sad.altinn.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.otto.edison.hal.HalRepresentation;

/**
 * The value object for metadata
 * 
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
public class MetadataHalRepresentation extends HalRepresentation {

	@JsonProperty("ServiceOwnerCode")
	private String serviceOwnerCode;
	@JsonProperty("ServiceOwnerName")
	private String serviceOwnerName;
	@JsonProperty("ServiceCode")
	private String serviceCode;
	@JsonProperty("ServiceEditionCode")
	private String serviceEditionCode;
	@JsonProperty("ValidFrom")
	private String validFrom;
	@JsonProperty("ValidTo")
	private String validTo;
	@JsonProperty("ServiceType")
	private String serviceType;

	public String getServiceOwnerCode() {
		return serviceOwnerCode;
	}

	public void setServiceOwnerCode(String serviceOwnerCode) {
		this.serviceOwnerCode = serviceOwnerCode;
	}

	public String getServiceOwnerName() {
		return serviceOwnerName;
	}

	public void setServiceOwnerName(String serviceOwnerName) {
		this.serviceOwnerName = serviceOwnerName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceEditionCode() {
		return serviceEditionCode;
	}

	public void setServiceEditionCode(String serviceEditionCode) {
		this.serviceEditionCode = serviceEditionCode;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

}
