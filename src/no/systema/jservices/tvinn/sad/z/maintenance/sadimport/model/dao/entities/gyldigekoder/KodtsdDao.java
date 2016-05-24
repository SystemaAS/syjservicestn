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
public class KodtsdDao implements Serializable, IDao {

	private String ksdls = ""; 
	public String getKsdlsPropertyName (){ return "ksdls"; }
	public void setKsdls (String value){ this.ksdls = value;   }   
	public String getKsdls (){ return this.ksdls;   }              

	private String ksdtxt = ""; 
	public String getKsdtxtPropertyName (){ return "ksdtxt"; }
	public void setKsdtxt (String value){ this.ksdtxt = value;   }   
	public String getKsdtxt (){ return this.ksdtxt;   }              

	
}
