package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

//log data to FTP for främmande houses: 
@Data
public class ZadmoattfDao implements IDao {
	
	
	private String id = ""; //TEGN 100 emdkm or ehdkm or other significant
	private String avsid = ""; //TEGN 50 sender Orgnr
	private String motid = ""; //TEGN 20 receiver Orgnr		
	
	private Integer date = 0; //TEGN 8 
	private Integer time = 0; //TEGN 6 
	
	private String docname = ""; //TEGN 100 file name	
	private String typref = ""; //TEGN 50 file type (business doc.type: invoice or docX)	
	private String docref = ""; //TEGN 300 file absolute name including path... 
	
	
}
