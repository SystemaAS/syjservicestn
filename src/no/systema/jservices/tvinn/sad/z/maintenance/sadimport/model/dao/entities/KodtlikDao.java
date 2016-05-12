package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Mar 30, 2016
 * 
 */
public class KodtlikDao implements Serializable, IDao {
	
	private String klista = "";                                
	public String getKlistaPropertyName (){ return "klista"; }
	public void setKlista (String value){ this.klista = value;   }   
	public String getKlista (){ return this.klista;   }  
	
	private String kliuni = "LIKO"; //always 
	public String getKliuniPropertyName (){ return "kliuni"; }
	public void setKliuni (String value){ this.kliuni = value;   }   
	public String getKliuni (){ return this.kliuni;   }              

	private String klikod = "";
	public String getKlikodPropertyName (){ return "klikod"; }
	public void setKlikod (String value){ this.klikod = value;   }   
	public String getKlikod (){ return this.klikod;   }              

	private String klinav = ""; 
	public String getKlinavPropertyName (){ return "klinav"; }
	public void setKlinav (String value){ this.klinav = value;   }   
	public String getKlinav (){ return this.klinav;   }              

	private String klisto = ""; 
	public String getKlistoPropertyName (){ return "klisto"; }
	public void setKlisto (String value){ this.klisto = value;   }   
	public String getKlisto (){ return this.klisto;   }              

	private String klixxx = ""; 
	public String getKlixxxPropertyName (){ return "klixxx"; }
	public void setKlixxx (String value){ this.klixxx = value;   }   
	public String getKlixxx (){ return this.klixxx;   }              
	
	
}
