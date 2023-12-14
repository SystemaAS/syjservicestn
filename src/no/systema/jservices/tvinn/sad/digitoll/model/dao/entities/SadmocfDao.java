package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadmocfDao implements IDao  {

	private String orgnr  = "";//      tegn      30     
	private String name  = "";//      tegn      30  
	private String commtype  = ""; //  tegn      10 (ftp, email, webserv)   
	

}
