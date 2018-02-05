package no.systema.jservices.tvinn.sad.altinn.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.otto.edison.hal.HalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.proxy.ActionsUriBuilder;

/**
 * The value object for Messages 
 * 
 * @see {@linkplain ActionsUriBuilder} getMessages
 * @author Fredrik Möller
 * @date 2018-01
 *
 */
public class MessagesHalRepresentation extends HalRepresentation{

    @JsonProperty("MessageId")
	private String messageId;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("LastChangedBy")
    private String lastChangedBy;
    @JsonProperty("ServiceOwner")
    private String serviceOwner;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("MessageSender")
    private String messageSender;
    @JsonProperty("ServiceCode")
    private String serviceCode;
    @JsonProperty("ServiceEdition")
    private int serviceEdition;
    @JsonProperty("MessageLink")
    private String messageLink; 
    @JsonProperty("LastChangedDateTime")
    private String lastChangedDateTime;    
    @JsonProperty("CreatedDate")
    private String createdDate;   
    @JsonProperty("DueDate")
    private String dueDate;    

    
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastChangedBy() {
		return lastChangedBy;
	}

	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(String messageSender) {
		this.messageSender = messageSender;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public int getServiceEdition() {
		return serviceEdition;
	}

	public void setServiceEdition(int serviceEdition) {
		this.serviceEdition = serviceEdition;
	}

	public String getServiceOwner() {
		return serviceOwner;
	}

	public void setServiceOwner(String serviceOwner) {
		this.serviceOwner = serviceOwner;
	}

	public String getMessageLink() {
		return messageLink;
	}

	public void setMessageLink(String messageLink) {
		this.messageLink = messageLink;
	}

	public String getLastChangedDateTime() {
		return lastChangedDateTime;
	}

	public void setLastChangedDateTime(String lastChangedDateTime) {
		this.lastChangedDateTime = lastChangedDateTime;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
