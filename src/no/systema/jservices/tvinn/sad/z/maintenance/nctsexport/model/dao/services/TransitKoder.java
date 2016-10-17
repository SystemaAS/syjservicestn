package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;

/**
 * Holding constant for select section from {@link TrkodfDaoServices}
 * 
 * 
 * 012=Språk kode 013=Dokument kode 014=Tidligere dokument 017=Pakketype
 * 031=Deklarasjons type 039=Tilleggsopplysning 047=Kontroll resultat 064=Følsom
 * vare 096=Spesielle omstendigheter 105=Adgangskode for garanti 106=Tollsted
 * referansenr 116=Transportkostnad betalingsmåte
 * 023=Feilkoder i CONTROL 041=Kontroll indikator VAREPOST 042=Kontroll
 * indikator TRANSIT 049=Feilkoder i CUSRES feilmelding 149=Feilkoder for
 * datagruppe i CUSRES 209=Feilkoder for garanti
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 7, 2016
 *
 */
public enum TransitKoder {

	SPRAK_KODE ("012"),
	DOKUMENT_KODE ("013"),
	TIDLIGERE_DOKUMENT ("014"),
	PAKKETYPE ("017"),
	DEKLARASJONS_TYPE ("031"),
	TILLEGGS_OPPLYSNING ("039"),
	KONTROLL_RESULTAT ("047"),
	FOLSOME_VARE ("064"),
	SPECIELLE_OMSTENDIGHETER ("096"),
	ADGANGSKODE_GARANTI ("105"),
	TOLLSTED_REFNR ("106"),
	TRANSPORTKOST_BETALMATE ("116"),
	FEILKODER_CONTROL ("023"),
	KONTROLL_INDIKATOR_VAREPOST ("041"),
	KONTROLL_INDIKATOR_TRANSIT ("042"),
	FEILKODER_CUSTRES_FEILMELDING ("049"),
	FEILKODER_DATAGRUPPE_CUSTRES ("149"),
	FEILKODER_GARANTI ("209");
	
	private final String transitKode;

	TransitKoder(String transitKode) {
		this.transitKode = transitKode;
	}
	
	public String getTransitKode() {
		return transitKode;
	}

	/**
	 * Return {@link TransitKoder} from String
	 * 
	 * @param kode
	 * @return
	 *@throws IllegalArgumentException iF constant not found
	 */
	public static TransitKoder fromString(String kode) {
	    if (kode != null) {
	      for (TransitKoder tKode : TransitKoder.values()) {
	        if (kode.equalsIgnoreCase(tKode.transitKode)) {
	          return tKode;
	        }
	      }
	    }
	    throw new IllegalArgumentException("No constant with kode " + kode + " found");
	  }	
	
	
}
