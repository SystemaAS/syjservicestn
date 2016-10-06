package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Oct 6, 2016
 * 
 */
public class Kodts9Dao implements Serializable, IDao {

	private String ks9sta = ""; 
	public String getKs9staPropertyName (){ return "ks9sta"; }
	public void setKs9sta (String value){ this.ks9sta = value;   }   
	public String getKs9sta (){ return this.ks9sta;   }              

	private String ks9uni = "S9"; 
	public String getKs9uniPropertyName (){ return "ks9uni"; }
	public void setKs9uni (String value){ this.ks9uni = value;   }   
	public String getKs9uni (){ return this.ks9uni;   }              

	private String ks9typ = ""; 
	public String getKs9typPropertyName (){ return "ks9typ"; }
	public void setKs9typ (String value){ this.ks9typ = value;   }   
	public String getKs9typ(){ return this.ks9typ;   }              

	private String ks9ftx = "";
	public String getKs9ftxPropertyName (){ return "ks9ftx"; }
	public void setKs9ftx (String value){ this.ks9ftx = value;   }   
	public String getKs9ftx (){ return this.ks9ftx;   }              

	
}
