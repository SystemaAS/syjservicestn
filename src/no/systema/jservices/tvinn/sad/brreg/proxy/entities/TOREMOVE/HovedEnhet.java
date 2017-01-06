package no.systema.jservices.tvinn.sad.brreg.proxy.entities.TOREMOVE;

import org.apache.commons.csv.CSVRecord;

/**
 * This class represent a entity found as HovedEnhet
 * 
 * @author Fredrik Möller
 * @date Nov 29, 2016
 *
 */
public class HovedEnhet extends Enhet {


	/**
	 * Constructor init values delivered in CSVRecord
	 * Values included:
	 * <li> organisasjonsnummer </li>
	 * <li> konkurs </li>
	 * <li> registrertIMvaregisteret </li>
	 * <li> underAvvikling </li>
	 * <li> underTvangsavviklingEllerTvangsopplosning </li>
	 * <li> organisasjonsform </li>
	 * <li> navn </li>
	 * <li> forretningsadresse.adresse </li>
	 * <li> forretningsadresse.postnummer </li>

	 * 
	 * @param csvRecord
	 */
	public HovedEnhet(CSVRecord csvRecord) {
		String organisasjonsnummer = csvRecord.get("organisasjonsnummer");
		String konkurs = csvRecord.get("konkurs");
		String registrertIMvaregisteret = csvRecord.get("registrertIMvaregisteret");
		String underAvvikling = csvRecord.get("underAvvikling");
		String underTvangsavviklingEllerTvangsopplosning = csvRecord.get("underTvangsavviklingEllerTvangsopplosning");
		String organisasjonsform = csvRecord.get("organisasjonsform");
		String navn = csvRecord.get("navn");
		String forretningsadresseAdresse = csvRecord.get("forretningsadresse.adresse");
		String forretningsadressePostnummer = csvRecord.get("forretningsadresse.postnummer");
		
		this.setOrganisasjonsnummer(new Integer(organisasjonsnummer));
		this.setKonkurs(konkurs);
		this.setRegistrertIMvaregisteret(registrertIMvaregisteret);
		this.setUnderAvvikling(underAvvikling);
		this.setUnderTvangsavviklingEllerTvangsopplosning(underTvangsavviklingEllerTvangsopplosning);
		this.setOrganisasjonsform(organisasjonsform);
		this.setNavn(navn);
		Forretningsadresse fa = new Forretningsadresse();
		fa.setAdresse(forretningsadresseAdresse);
		fa.setPostnummer(forretningsadressePostnummer);
		this.setForretningsadresse(fa);
		

	}

}
