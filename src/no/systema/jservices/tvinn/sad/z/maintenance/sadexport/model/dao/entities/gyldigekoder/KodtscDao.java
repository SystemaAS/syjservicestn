package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 */
public class KodtscDao implements Serializable, IDao {

	private String ksckd = ""; 
	public String getKsckdPropertyName (){ return "ksckd"; }
	public void setKsckd (String value){ this.ksckd = value;   }   
	public String getKsckd (){ return this.ksckd;   }              

	private String kscft = ""; 
	public String getKscftPropertyName (){ return "kscft"; }
	public void setKscft (String value){ this.kscft = value;   }   
	public String getKscft (){ return this.kscft;   }              

	
}
