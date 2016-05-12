package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Maj 2, 2016
 * 
 */
public class CundfLikvKodeDao implements Serializable, IDao {
	
	private String firma = "";                                
	public String getFirmaPropertyName (){ return "firma"; }
	public void setFirma (String value){ this.firma = value;   }   
	public String getFirma (){ return this.firma;   }  
	
	private String kundnr = "";                                
	public String getKundnrPropertyName (){ return "kundnr"; }
	public void setKundnr (String value){ this.kundnr = value;   }   
	public String getKundnr (){ return this.kundnr;   }  
	
	private String knavn = ""; 
	public String getKnavnPropertyName (){ return "knavn"; }
	public void setKnavn (String value){ this.knavn = value;   }   
	public String getKnavn (){ return this.knavn;   }              

	private String adr1 = "";
	public String getAdr1PropertyName (){ return "adr1"; }
	public void setAdr1 (String value){ this.adr1 = value;   }   
	public String getAdr1 (){ return this.adr1;   }              

	private String sylikv = "";
	public String getSylikvPropertyName (){ return "sylikv"; }
	public void setSylikv (String value){ this.sylikv = value;   }   
	public String getSylikv (){ return this.sylikv;   }              
	
	
}
