package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 * 
 */
public class KodtsbDao implements Serializable, IDao {

	private String ksbkd = ""; 
	public String getKsbkdPropertyName (){ return "ksbkd"; }
	public void setKsbkd (String value){ this.ksbkd = value;   }   
	public String getKsbkd (){ return this.ksbkd;   }              

	private String ksbft = ""; 
	public String getKsbftPropertyName (){ return "ksbft"; }
	public void setKsbft (String value){ this.ksbft = value;   }   
	public String getKsbft (){ return this.ksbft;   }              

	
}
