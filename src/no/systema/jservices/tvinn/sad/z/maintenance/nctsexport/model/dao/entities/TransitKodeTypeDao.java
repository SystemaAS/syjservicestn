package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities;
import java.io.Serializable;

import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author Fredrik Möller
 * @date Okt 17, 2016
 * 
 */
public class TransitKodeTypeDao implements Serializable, IDao {
	
	private String tkunik = "";                             
	public String getTkunikPropertyName (){ return "tkunik"; }
	public void setTkunik (String value){ this.tkunik = value;   }   
	public String getTkunik (){ return this.tkunik;   }  
	
	private String description = "";                                
	public String getDescriptionPropertyName (){ return "description"; }
	public void setDescription (String value){ this.description = value;   }   
	public String getDescription (){ return this.description;   }  
	
	
}
