package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 */
public class KodtsiDao implements Serializable {
	
	private String ksista = "";                                
	public String getKsistaPropertyName (){ return "ksista"; }
	public void setKsista (String value){ this.ksista = value;   }   
	public String getKsista (){ return this.ksista;   }  
	
	private String ksiuni = "SI"; //always
	public String getKsiuniPropertyName (){ return "ksiuni"; }
	public void setKsiuni (String value){ this.ksiuni = value;   }   
	public String getKsiuni (){ return this.ksiuni;   }              

	private String ksisig = "";
	public String getKsisigPropertyName (){ return "ksisig"; }
	public void setKsisig (String value){ this.ksisig = value;   }   
	public String getKsisig (){ return this.ksisig;   }              

	private String ksinav = ""; 
	public String getKsinavPropertyName (){ return "ksinav"; }
	public void setKsinav (String value){ this.ksinav = value;   }   
	public String getKsinav (){ return this.ksinav;   }              

	private String ksovl = ""; 
	public String getKsovlPropertyName (){ return "ksovl"; }
	public void setKsovl (String value){ this.ksovl = value;   }   
	public String getKsovl (){ return this.ksovl;   }              

	private String ksuser = ""; 
	public String getKsuserPropertyName (){ return "ksuser"; }
	public void setKsuser (String value){ this.ksuser = value;   }   
	public String getKsuser (){ return this.ksuser;   }              

	private String ksixxx = ""; 
	public String getKsixxxPropertyName (){ return "ksixxx"; }
	public void setKsixxx (String value){ this.ksixxx = value;   }   
	public String getKsixxx (){ return this.ksixxx;   }              
	
	
}
