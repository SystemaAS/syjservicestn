package no.systema.jservices.tvinn.sad.brreg.proxy.entities;

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
		
		this.setOrganisasjonsnummer(new Integer(organisasjonsnummer));
		this.setKonkurs(konkurs);
		this.setRegistrertIMvaregisteret(registrertIMvaregisteret);
		this.setUnderAvvikling(underAvvikling);
		this.setUnderTvangsavviklingEllerTvangsopplosning(underTvangsavviklingEllerTvangsopplosning);
		this.setOrganisasjonsform(organisasjonsform);

	}

}
