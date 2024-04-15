package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

//log data to FTP for främmande houses: 
@Data
public class SadmolffDao implements IDao {

	private String status = ""; //TEGN 20 status (send, confirmed, dialog, receipt, ok) 
	private String uuid = ""; //TEGN 36 messageId
	private String emdkm = ""; //TEGN 50 master doc id
	private String emlnrt  = "" ;  //TEGN   7 
	
	private Integer date = 0; //numeric 8 (SONET 8,0)
	private Integer time = 0; //numeric 6 (SONET 6,0)
	
}
