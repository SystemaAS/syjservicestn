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
public class Kodts3Dao implements Serializable, IDao {

	private String ks3sta = ""; 
	public String getKs3staPropertyName (){ return "ks3sta"; }
	public void setKs3sta (String value){ this.ks3sta = value;   }   
	public String getKs3sta (){ return this.ks3sta;   }              

	private String ks3uni = "S3"; 
	public String getKs3uniPropertyName (){ return "ks3uni"; }
	public void setKs3uni (String value){ this.ks3uni = value;   }   
	public String getKs3uni (){ return this.ks3uni;   }              

	private String ks3trt = ""; 
	public String getKs3trtPropertyName (){ return "ks3trt"; }
	public void setKs3trt (String value){ this.ks3trt = value;   }   
	public String getKs3trt(){ return this.ks3trt;   }              

	private String ks3ftx = "";
	public String getKs3ftxPropertyName (){ return "ks3ftx"; }
	public void setKs3ftx (String value){ this.ks3ftx = value;   }   
	public String getKs3ftx (){ return this.ks3ftx;   }              

}
