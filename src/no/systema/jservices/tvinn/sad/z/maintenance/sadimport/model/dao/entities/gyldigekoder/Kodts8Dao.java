package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */
public class Kodts8Dao implements Serializable, IDao {
	
	private String ks8avg = "";                                
	public String getKs8avgPropertyName (){ return "ks8avg"; }
	public void setKs8avg (String value){ this.ks8avg = value;   }   
	public String getKs8avg (){ return this.ks8avg;   }  
	
	private String ks8skv = ""; 
	public String getKs8skvPropertyName (){ return "ks8skv"; }
	public void setKs8skv (String value){ this.ks8skv = value;   }   
	public String getKs8skv (){ return this.ks8skv;   }              

	private String ks8ftx = ""; 
	public String getKs8ftxPropertyName (){ return "ks8ftx"; }
	public void setKs8ftx (String value){ this.ks8ftx = value;   }   
	public String getKs8ftx(){ return this.ks8ftx;   }              

	private String ks8sat = "";
	public String getKs8satPropertyName (){ return "ks8sat"; }
	public void setKs8sat (String value){ this.ks8sat = value;   }   
	public String getKs8sat (){ return this.ks8sat;   }              

	private String ks8sty = ""; 
	public String getKs8styPropertyName (){ return "ks8sty"; }
	public void setKs8sty (String value){ this.ks8sty = value;   }   
	public String getKs8sty (){ return this.ks8sty;   }              

	
	
}
