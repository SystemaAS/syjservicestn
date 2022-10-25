package no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadexlogDao implements IDao {

	private Integer elavd  = 0; // SONET        4  0       4         9        Begge    AVDELING     
	private Integer elpro  = 0; // SONET        8  0       8         1        Begge    TURNUMMER   
	private Integer eltdn  = 0; // SONET        7  0       7        13        Begge    OPPDRAGSNR 
	
	private Integer eldate  = 0; // SONET        8  0       8        20        Begge    DATO   
	private Integer eltime  = 0; // SONET        6  0       6        28        Begge    TID 
	private String eltyp  = "" ; // TEGN            5       5        34        Begge    LOGGTYPE     
	private String elltxt  = "" ; // TEGN         1024    1024        39        Begge    LOGTEXT     
	private String elifsf  = "" ; // TEGN          100     100      1063        Begge    IFS FILENAME	 
	
	
}
