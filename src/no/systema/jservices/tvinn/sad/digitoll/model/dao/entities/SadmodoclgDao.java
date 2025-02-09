package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadmodoclgDao implements IDao  {

	private String resultapi = ""; //(200) NOT NULL,
	private String docId = "";	//char(100) NOT NULL,          
	private String deklid = ""; //char (100) NOT NULL
	private String doctyp = ""; //char(30) NOT NULL, 
	private String doclnk = ""; //char(200) NOT NULL,
	private String deklnr = ""; //char(35) NOT NULL, 
	private String dekldate = ""; //char(8) NOT NULL,
	private String deklsekv = ""; //char(8) NOT NULL,
	private String senddate = ""; //char(8) NOT NULL,
	private String sendtime = ""; //char(6) NOT NULL 
	
}
