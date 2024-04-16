package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

//log data to FTP for främmande houses: 
@Data
public class SadmolffDao implements IDao {

	private String status = ""; //TEGN 20 status (send, confirmed, dialog, receipt, ok) 
	private String statustxt = ""; //TEGN 70 status txt (usually when getting a receipt)
	private String uuid = ""; //TEGN 36 messageId
	private String emdkm = ""; //TEGN 50 master doc id
	private String emlnrt  = "" ;  //TEGN   7 
	private String avsid = ""; //TEGN 20 sender Orgnr
	private String motid = ""; //TEGN 20 receiver Orgnr		
	
	private String date = ""; //TEGN 8 
	private String time = ""; //TEGN 6 
	
}
