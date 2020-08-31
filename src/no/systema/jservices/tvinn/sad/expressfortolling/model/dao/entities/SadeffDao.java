package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadeffDao implements IDao {

	private String efst; //TEGN (1) Status
	private String efuuid; //TEGN (36) ManifestID (uuid)
 	private String efavd; //SONET (4,0) Avdeling
	private String efpro; //SONET (8,0) Turnr
	private String efdtr; //SONET (8,0) Reg.dato
	private String efsg; //TEGN (3) Signatur
	private String efst2; //TEGN (1) Status om manifest
	private String eftsd; //SONET (4,0) Passeringstollsted
	private String efst3; //TEGN (1) Status om inpassering
	private String efdtin; //SONET (8,0) Innsendingsdato
	private String efeta; //SONET (8,0) ETA-dato
	private String efetm; //SONET (6,0) ETA-tid
	private String efata; //SONET (8,0) ATA-dato
	private String efatm; //SONET (6,0) ATA-tid
	//
	private String ef3039E; //SONET (6,0) ATA-tid
	private String efeid; //TEGN (18) Ekportid
	//
	private String efknd; //SONET (8,0) Deklarant/Tr.sportør
	private String efrgd; //TEGN (9) Org.nr Deklarant
	private String eftm; //TEGN (3) Transportmåte
	private String eftmt; //TEGN (50) Transportmåte tekst
	private String efktyp; //TEGN (2) Kjøretøy type kode
	private String efktypt; //TEGN (50) Kjøretøy type tekst
	private String efklk; //TEGN (2) Kjøretøynasjonalitet
	private String efkmrk; //TEGN (30) Kjennemerke
	private String efplk; //TEGN (2) Kjøretøynasjonalitet
	private String efpmrk; //TEGN (30) Kjennemerke
	private String efsjaf; //TEGN (30) Sjåfør fornavn
	private String efsjae; //TEGN (30) Sjåfør ettenavn
	private String efsjalk; //TEGN (30) Sjåfør nasjonalitet
	private String efsjadt; //SONET (8,0) Sjåfør Sjåfør fødselsdato
	private String efbekr; //TEGN (1) Sjåfør bekreftelse
	private String eferr; //TEGN (50) Feilmelding ved SND
	
}
