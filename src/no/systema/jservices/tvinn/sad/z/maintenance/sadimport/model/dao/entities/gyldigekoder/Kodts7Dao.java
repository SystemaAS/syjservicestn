package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 */
public class Kodts7Dao implements Serializable, IDao {

	private String ks7sta = ""; 
	public String getKs7staPropertyName (){ return "ks7sta"; }
	public void setKs7sta (String value){ this.ks7sta = value;   }   
	public String getKs7sta (){ return this.ks7sta;   }              

	private String ks7uni = "S7"; 
	public String getKs7uniPropertyName (){ return "ks7uni"; }
	public void setKs7uni (String value){ this.ks7uni = value;   }   
	public String getKs7uni (){ return this.ks7uni;   }              

	private String ks7vf = ""; 
	public String getKs7vfPropertyName (){ return "ks7vf"; }
	public void setKs7vf (String value){ this.ks7vf = value;   }   
	public String getKs7vf(){ return this.ks7vf;   }              

	private String ks7ftx = "";
	public String getKs7ftxPropertyName (){ return "ks7ftx"; }
	public void setKs7ftx (String value){ this.ks7ftx = value;   }   
	public String getKs7ftx (){ return this.ks7ftx;   }              

	          

}
