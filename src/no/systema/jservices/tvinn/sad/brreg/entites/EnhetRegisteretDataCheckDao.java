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

	private String firmaKode = "";
	private String kundeNr = "";
	private String kundeNavn = "";
	private String orgNr = "";
	private String konkurs = "";
	private String registrertIMvaregisteret = "";
	private String underAvvikling = "";
	private String underTvangsavviklingEllerTvangsopplosning = "";
	private String existsInRegister = "";

	public String getFirmaKode() {
		return firmaKode;
	}
	public void setFirmaKode(String firmaKode) {
		this.firmaKode = firmaKode;
	}
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
	public String getExistsInRegister() {
		return existsInRegister;
	}
	public void setExistsInRegister(String existsInRegister) {
		this.existsInRegister = existsInRegister;
	}
	
	
}
