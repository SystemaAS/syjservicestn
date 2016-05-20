package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 */
public class Kodts4Dao implements Serializable, IDao {

	private String ks4sta = ""; 
	public String getKs4staPropertyName (){ return "ks4sta"; }
	public void setKs4sta (String value){ this.ks4sta = value;   }   
	public String getKs4sta (){ return this.ks4sta;   }              

	private String ks4uni = "S4"; 
	public String getKs4uniPropertyName (){ return "ks4uni"; }
	public void setKs4uni (String value){ this.ks4uni = value;   }   
	public String getKs4uni (){ return this.ks4uni;   }              

	private String ks4trm = ""; 
	public String getKs4trmPropertyName (){ return "ks4trm"; }
	public void setKs4trm (String value){ this.ks4trm = value;   }   
	public String getKs4trm(){ return this.ks4trm;   }              

	private String ks4ftx = "";
	public String getKs4ftxPropertyName (){ return "ks4ftx"; }
	public void setKs4ftx (String value){ this.ks4ftx = value;   }   
	public String getKs4ftx (){ return this.ks4ftx;   }              

}
