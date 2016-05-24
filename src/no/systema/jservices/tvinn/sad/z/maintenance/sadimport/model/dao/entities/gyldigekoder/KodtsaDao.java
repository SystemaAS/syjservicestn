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
public class KodtsaDao implements Serializable, IDao {

	private String ksakd = ""; 
	public String getKsakdPropertyName (){ return "ksakd"; }
	public void setKsakd (String value){ this.ksakd = value;   }   
	public String getKsakd (){ return this.ksakd;   }              

	private String ksaft = ""; 
	public String getKsaftPropertyName (){ return "ksaft"; }
	public void setKsaft (String value){ this.ksaft = value;   }   
	public String getKsaft (){ return this.ksaft;   }              

	
}
