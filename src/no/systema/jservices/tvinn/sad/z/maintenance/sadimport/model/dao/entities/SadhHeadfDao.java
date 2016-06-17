package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 17, 2016
 * 
 */
public class SadhHeadfDao implements Serializable, IDao {
	
	private String siavd = "";                                
	public String getSiavdPropertyName (){ return "siavd"; }
	public void setSiavd (String value){ this.siavd = value;   }   
	public String getSiavd (){ return this.siavd;   }  
	
	private String sitdn = "";
	public String getSitdnPropertyName (){ return "sitdn"; }
	public void setSitdn (String value){ this.sitdn = value;   }   
	public String getSitdn (){ return this.sitdn;   }              

	private String sitll = "";
	public String getSitllPropertyName (){ return "sitll"; }
	public void setSitll (String value){ this.sitll = value;   }   
	public String getSitll (){ return this.sitll;   }              

	private String sitle = ""; 
	public String getSitlePropertyName (){ return "sitle"; }
	public void setSitle (String value){ this.sitle = value;   }   
	public String getSitle (){ return this.sitle;   }              

	private String sidtg = ""; 
	public String getSidtgPropertyName (){ return "sidtg"; }
	public void setSidtg (String value){ this.sidtg = value;   }   
	public String getSidtg (){ return this.sidtg;   }              

	private String heavd = ""; 
	public String getHeavdPropertyName (){ return "heavd"; }
	public void setHeavd (String value){ this.heavd = value;   }   
	public String getHeavd (){ return this.heavd;   }              

	private String heopd = ""; 
	public String getHeopdPropertyName (){ return "heopd"; }
	public void setHeopd (String value){ this.heopd = value;   }   
	public String getHeopd (){ return this.heopd;   }              
	
	private String hetll = ""; 
	public String getHetllPropertyName (){ return "hetll"; }
	public void setHetll (String value){ this.hetll = value;   }   
	public String getHetll (){ return this.hetll;   }              
	
	private String hetle = ""; 
	public String getHetlePropertyName (){ return "hetle"; }
	public void setHetle (String value){ this.hetle = value;   }   
	public String getHetle (){ return this.hetle;   }              
	
	
}
