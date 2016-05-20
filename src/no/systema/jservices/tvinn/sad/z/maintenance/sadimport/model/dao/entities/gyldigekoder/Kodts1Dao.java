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
public class Kodts1Dao implements Serializable, IDao {

	private String ks1sta = ""; 
	public String getKs1staPropertyName (){ return "ks1sta"; }
	public void setKs1sta (String value){ this.ks1sta = value;   }   
	public String getKs1sta (){ return this.ks1sta;   }              

	private String ks1uni = "S1"; 
	public String getKs1uniPropertyName (){ return "ks1uni"; }
	public void setKs1uni (String value){ this.ks1uni = value;   }   
	public String getKs1uni (){ return this.ks1uni;   }              

	private String ks1typ = ""; 
	public String getKs1typPropertyName (){ return "ks1typ"; }
	public void setKs1typ (String value){ this.ks1typ = value;   }   
	public String getKs1typ(){ return this.ks1typ;   }              

	private String ks1ftx = "";
	public String getKs1ftxPropertyName (){ return "ks1ftx"; }
	public void setKs1ftx (String value){ this.ks1ftx = value;   }   
	public String getKs1ftx (){ return this.ks1ftx;   }              

	
}
