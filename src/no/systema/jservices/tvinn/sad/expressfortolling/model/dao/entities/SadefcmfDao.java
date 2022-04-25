package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadefcmfDao implements IDao {

	private String cmst = ""; //tegn            1       1         1        begge    status    
	private Integer cmavd = 0; //sonet        4  0       4         2        begge    avdeling  
	private Integer cmtdn = 0; //sonet        7  0       7         6        begge    oppdragsnr
	private Integer cmli = 0; //sonet        2  0       2        13        begge    linjenr   
	private Integer cmavde = 0;//sonet        4  0       4        15        begge    svensk eksp.avdeling
	private Integer cmtdne = 0;//sonet        7  0       7        19        begge    svensk eksp.oppd.nr 
	private String cmetyp = ""; //tegn            2       2        26        begge    type        
	private String cmetypt = ""; //tegn           30      30        28        begge    type tekst  
	private String cmeid = ""; 	//tegn           18      18        58        begge    eksport id  
	private String cmeser = ""; //tegn            1       1        76        begge    sertifisert 
	
}
