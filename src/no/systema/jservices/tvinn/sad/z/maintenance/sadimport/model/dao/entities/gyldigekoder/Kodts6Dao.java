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
public class Kodts6Dao implements Serializable, IDao {

	private String ks6sta = ""; 
	public String getKs6staPropertyName (){ return "ks6sta"; }
	public void setKs6sta (String value){ this.ks6sta = value;   }   
	public String getKs6sta (){ return this.ks6sta;   }              

	private String ks6uni = "S6"; 
	public String getKs6uniPropertyName (){ return "ks6uni"; }
	public void setKs6uni (String value){ this.ks6uni = value;   }   
	public String getKs6uni (){ return this.ks6uni;   }              

	private String ks6pre = ""; 
	public String getKs6prePropertyName (){ return "ks6pre"; }
	public void setKs6pre (String value){ this.ks6pre = value;   }   
	public String getKs6pre(){ return this.ks6pre;   }              

	private String ks6ftx = "";
	public String getKs6ftxPropertyName (){ return "ks6ftx"; }
	public void setKs6ftx (String value){ this.ks6ftx = value;   }   
	public String getKs6ftx (){ return this.ks6ftx;   }              

	private String ks6trn = "";
	public String getKs6trnPropertyName (){ return "ks6trn"; }
	public void setKs6trn (String value){ this.ks6trn = value;   }   
	public String getKs6trn (){ return this.ks6trn;   }              

}
