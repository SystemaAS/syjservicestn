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
public class Kodts5Dao implements Serializable, IDao {

	private String ks5sta = ""; 
	public String getKs5staPropertyName (){ return "ks5sta"; }
	public void setKs5sta (String value){ this.ks5sta = value;   }   
	public String getKs5sta (){ return this.ks5sta;   }              

	private String ks5uni = "S5"; 
	public String getKs5uniPropertyName (){ return "ks5uni"; }
	public void setKs5uni (String value){ this.ks5uni = value;   }   
	public String getKs5uni (){ return this.ks5uni;   }              

	private String ks5tln = ""; 
	public String getKs5tlnPropertyName (){ return "ks5tln"; }
	public void setKs5tln (String value){ this.ks5tln = value;   }   
	public String getKs5tln(){ return this.ks5tln;   }              

	private String ks5ftx = "";
	public String getKs5ftxPropertyName (){ return "ks5ftx"; }
	public void setKs5ftx (String value){ this.ks5ftx = value;   }   
	public String getKs5ftx (){ return this.ks5ftx;   }              

}
