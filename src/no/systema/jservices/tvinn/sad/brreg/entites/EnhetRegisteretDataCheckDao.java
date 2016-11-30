package no.systema.jservices.tvinn.sad.brreg.entites;

import java.io.Serializable;

import no.systema.jservices.model.dao.entities.IDao;

/**
 * Data Transfer Object for validated kunder for firma i NO
 * 
 * Exposed as JSON to client.
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 23, 2016
 *
 */
public class EnhetRegisteretDataCheckDao implements Serializable, IDao {

	private String kundeNr = "";
	private String kundeNavn = "";
	private String orgNr = "";
	private String konkurs = "";
	private String registrertIMvaregisteret = "";
	private String underAvvikling = "";
	private String underTvangsavviklingEllerTvangsopplosning = "";
	private String existsAsHovedEnhet = "";
	private String existsAsUnderEnhet = "";
	private String overordnetEnhetOrgnr = "";
	
	public String getKundeNr() {
		return kundeNr;
	}
	public void setKundeNr(String kundeNr) {
		this.kundeNr = kundeNr;
	}
	public String getKundeNavn() {
		return kundeNavn;
	}
	public void setKundeNavn(String kundeNavn) {
		this.kundeNavn = kundeNavn;
	}
	public String getOrgNr() {
		return orgNr;
	}
	public void setOrgNr(String orgNr) {
		this.orgNr = orgNr;
	}
	public String getKonkurs() {
		return konkurs;
	}
	public void setKonkurs(String konkurs) {
		this.konkurs = konkurs;
	}
	public String getRegistrertIMvaregisteret() {
		return registrertIMvaregisteret;
	}
	public void setRegistrertIMvaregisteret(String registrertIMvaregisteret) {
		this.registrertIMvaregisteret = registrertIMvaregisteret;
	}
	public String getUnderAvvikling() {
		return underAvvikling;
	}
	public void setUnderAvvikling(String underAvvikling) {
		this.underAvvikling = underAvvikling;
	}
	public String getUnderTvangsavviklingEllerTvangsopplosning() {
		return underTvangsavviklingEllerTvangsopplosning;
	}
	public void setUnderTvangsavviklingEllerTvangsopplosning(String underTvangsavviklingEllerTvangsopplosning) {
		this.underTvangsavviklingEllerTvangsopplosning = underTvangsavviklingEllerTvangsopplosning;
	}
	public String getExistsAsHovedEnhet() {
		return existsAsHovedEnhet;
	}
	public void setExistsAsHovedEnhet(String existsAsHovedEnhet) {
		this.existsAsHovedEnhet = existsAsHovedEnhet;
	}
	public String getExistsAsUnderEnhet() {
		return existsAsUnderEnhet;
	}
	public void setExistsAsUnderEnhet(String existsAsUnderEnhet) {
		this.existsAsUnderEnhet = existsAsUnderEnhet;
	}
	public String getOverordnetEnhetOrgnr() {
		return overordnetEnhetOrgnr;
	}
	public void setOverordnetEnhetOrgnr(String overordnetEnhetOrgnr) {
		this.overordnetEnhetOrgnr = overordnetEnhetOrgnr;
	}
	
}
