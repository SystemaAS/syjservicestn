package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public class BridfDao implements Serializable {
	
	private String bibrid = null;                                
	public void setBibrid (String value){ this.bibrid = value;   }   
	public String getBibrid (){ return this.bibrid;   }              

	private String bipo = null;                                
	public void setBipo (String value){ this.bipo = value;   }   
	public String getBipo (){ return this.bipo;   }              

	private String bibesk = null;                                
	public void setBibesk (String value){ this.bibesk = value;   }   
	public String getBibesk (){ return this.bibesk;   }              

}
