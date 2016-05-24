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
public class KodtseDao implements Serializable, IDao {

	private String ksefyl = ""; 
	public String getKsefylPropertyName (){ return "ksefyl"; }
	public void setKsefyl (String value){ this.ksefyl = value;   }   
	public String getKsefyl (){ return this.ksefyl;   }              

	private String ksetxt = ""; 
	public String getKsetxtPropertyName (){ return "ksetxt"; }
	public void setKsetxt (String value){ this.ksetxt = value;   }   
	public String getKsetxt (){ return this.ksetxt;   }              

	
}
