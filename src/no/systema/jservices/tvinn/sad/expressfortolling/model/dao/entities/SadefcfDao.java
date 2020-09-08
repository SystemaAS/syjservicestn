package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadefcfDao implements IDao {

	private String clst = ""; //TEGN (1) Status
	private Integer clpro = 0; //SONET (8,0) Turnr
	private Integer clavd = 0; //SONET (4,0) avd
	private Integer cltdn = 0; //SONET (7,0) Oppdnr.
	private String clrg = ""; //TEGN (11) orgnr
	private Integer cl0068a = 0; //SONET (8,0) Sendingsdato
	private Integer cl0068b = 0; //SONET (6,0) Sendingssekvens
	private Integer clntk = 0; //SONET (7) Total kolli
	private Integer clvkb = 0; //SONET (9,0) Total vekt
	private String clvt = ""; //TEGN (30) Varebeskr.
	private String cltrid = ""; //TEGN (17) Bilnr
	private Integer cl3039e = 0; //SONET (6,0) Eksped.enhet
	private String cllkf = ""; //TEGN (2) Land of loading
	
	private String clsdf = ""; //TEGN (5) Place of loading
	private String clsdft = ""; //TEGN (30) Place of loading
	private String cllkt = ""; //TEGN (2) Land of unloading
	//
	private String clsdt = ""; //TEGN (5) Place of unloading
	private String clsdtt = ""; //TEGN (30) Place of unloading
	private String clpr = ""; //TEGN (2) Procedure
	private String clprt = ""; //TEGN (30) Procedure text
	private String cletyp = ""; //TEGN (2) Type
	private String cletypt = ""; //TEGN (30) Type text
	private String cleid = ""; //TEGN (18) Eksport Id
	private String cleser = ""; //TEGN (1) Certified
		
}
