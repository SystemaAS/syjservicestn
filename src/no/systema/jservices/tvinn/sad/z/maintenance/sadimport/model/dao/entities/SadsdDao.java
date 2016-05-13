package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */
public class SadsdDao implements Serializable, IDao {
	
	private String sdtnrf = "";                                
	public String getSdtnrfPropertyName (){ return "sdtnrf"; }
	public void setSdtnrf (String value){ this.sdtnrf = value;   }   
	public String getSdtnrf (){ return this.sdtnrf;   }  
	
	private String taalfa = ""; 
	public String getTaalfaPropertyName (){ return "taalfa"; }
	public void setTaalfa (String value){ this.taalfa = value;   }   
	public String getTaalfa (){ return this.taalfa;   }              

	private String taalfaOrig = ""; 
	public void setTaalfaOrig (String value){ this.taalfaOrig = value;   }   
	public String getTaalfaOrig (){ return this.taalfaOrig;   }              

	private String sdkdae = "";
	public String getSdkdaePropertyName (){ return "sdkdae"; }
	public void setSdkdae (String value){ this.sdkdae = value;   }   
	public String getSdkdae (){ return this.sdkdae;   }              

	private String sdkdse = ""; 
	public String getSdkdsePropertyName (){ return "sdkdse"; }
	public void setSdkdse (String value){ this.sdkdse = value;   }   
	public String getSdkdse (){ return this.sdkdse;   }              

	private String sddtf = ""; 
	public String getSddtfPropertyName (){ return "sddtf"; }
	public void setSddtf (String value){ this.sddtf = value;   }   
	public String getSddtf (){ return this.sddtf;   }              

	private String sddtfOrig = ""; 
	public void setSddtfOrig (String value){ this.sddtfOrig = value;   }   
	public String getSddtfOrig (){ return this.sddtfOrig;   }              
	
	private String sddtt = ""; 
	public String getSddttPropertyName (){ return "sddtt"; }
	public void setSddtt (String value){ this.sddtt = value;   }   
	public String getSddtt (){ return this.sddtt;   }              
	
	private String sddttOrig = ""; 
	public void setSddttOrig (String value){ this.sddttOrig = value;   }   
	public String getSddttOrig (){ return this.sddttOrig;   }              
	
	private String sdblse = ""; 
	public String getSdblsePropertyName (){ return "sdblse"; }
	public void setSdblse (String value){ this.sdblse = value;   }   
	public String getSdblse (){ return this.sdblse;   }              
	
	private String sdaktk = ""; 
	public String getSdaktkPropertyName (){ return "sdaktk"; }
	public void setSdaktk (String value){ this.sdaktk = value;   }   
	public String getSdaktk (){ return this.sdaktk;   }              
	
	
}
