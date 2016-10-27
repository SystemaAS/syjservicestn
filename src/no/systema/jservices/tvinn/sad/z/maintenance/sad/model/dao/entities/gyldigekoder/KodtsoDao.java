package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.gyldigekoder;
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
public class KodtsoDao implements Serializable, IDao {

	private String ksokd = ""; 
	public String getKsokdPropertyName (){ return "ksokd"; }
	public void setKsokd (String value){ this.ksokd = value;   }   
	public String getKsokd (){ return this.ksokd;   }              

	private String ksoft = ""; 
	public String getKsoftPropertyName (){ return "ksoft"; }
	public void setKsoft (String value){ this.ksoft = value;   }   
	public String getKsoft (){ return this.ksoft;   }              

	
}
