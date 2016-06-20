package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 */
public class KodttsDao implements Serializable, IDao {
	
	private String ktsuni = "TST";                                
	public String getKtsuniPropertyName (){ return "ktsuni"; }
	public void setKtsuni (String value){ this.ktsuni = value;   }   
	public String getKtsuni (){ return this.ktsuni;   }  
	
	private String ktskod = "";
	public String getKtskodPropertyName (){ return "ktskod"; }
	public void setKtskod (String value){ this.ktskod = value;   }   
	public String getKtskod (){ return this.ktskod;   }              

	private String ktsnav = "";
	public String getKtsnavPropertyName (){ return "ktsnav"; }
	public void setKtsnav (String value){ this.ktsnav = value;   }   
	public String getKtsnav (){ return this.ktsnav;   }              

	private String ktspnr = "";
	public String getKtspnrPropertyName (){ return "ktspnr"; }
	public void setKtspnr (String value){ this.ktspnr = value;   }   
	public String getKtspnr (){ return this.ktspnr;   }              

	private String ktstrt = ""; 
	public String getKtstrtPropertyName (){ return "ktstrt"; }
	public void setKtstrt (String value){ this.ktstrt = value;   }   
	public String getKtstrt (){ return this.ktstrt;   }              

	private String ktssat = ""; 
	public String getKtssatPropertyName (){ return "ktssat"; }
	public void setKtssat (String value){ this.ktssat = value;   }   
	public String getKtssat (){ return this.ktssat;   }              

	private String ktsxxx = ""; 
	public String getKtsxxxPropertyName (){ return "ktsxxx"; }
	public void setKtsxxx (String value){ this.ktsxxx = value;   }   
	public String getKtsxxx (){ return this.ktsxxx;   }              

	//Postnr table KODTTSX
	private String ktxpnr = ""; 
	public String getKtxpnrPropertyName (){ return "ktxpnr"; }
	public void setKtxpnr (String value){ this.ktxpnr = value;   }   
	public String getKtxpnr (){ return this.ktxpnr;   }              

	private String ktxkod = ""; 
	public String getKtxkodPropertyName (){ return "ktxkod"; }
	public void setKtxkod (String value){ this.ktxkod = value;   }   
	public String getKtxkod (){ return this.ktxkod;   }              

	
}
