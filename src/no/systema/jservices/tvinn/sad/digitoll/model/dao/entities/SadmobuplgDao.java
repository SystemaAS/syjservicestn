package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadmobuplgDao implements IDao  {

	private String file  = "";// tegn  400     
	private String date  = "";// tegn   8  
	private String time  = ""; //tegn   6 
	private String msgid  = ""; //tegn  70
	private String peppolid  = ""; //tegn  70
	
	
}
