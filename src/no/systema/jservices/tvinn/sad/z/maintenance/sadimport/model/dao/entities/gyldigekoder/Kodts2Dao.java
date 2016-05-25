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
public class Kodts2Dao implements Serializable, IDao {

	private String ks2sta = ""; 
	public String getKs2staPropertyName (){ return "ks2sta"; }
	public void setKs2sta (String value){ this.ks2sta = value;   }   
	public String getKs2sta (){ return this.ks2sta;   }              

	private String ks2uni = "S2"; 
	public String getKs2uniPropertyName (){ return "ks2uni"; }
	public void setKs2uni (String value){ this.ks2uni = value;   }   
	public String getKs2uni (){ return this.ks2uni;   }              

	private String ks2lk = ""; 
	public String getKs2lkPropertyName (){ return "ks2lk"; }
	public void setKs2lk (String value){ this.ks2lk = value;   }   
	public String getKs2lk(){ return this.ks2lk;   }              

	private String ks2ftx = "";
	public String getKs2ftxPropertyName (){ return "ks2ftx"; }
	public void setKs2ftx (String value){ this.ks2ftx = value;   }   
	public String getKs2ftx (){ return this.ks2ftx;   }              

	private String ks2pre = "";
	public String getKs2prePropertyName (){ return "ks2pre"; }
	public void setKs2pre (String value){ this.ks2pre = value;   }   
	public String getKs2pre (){ return this.ks2pre;   }              

	private String ks2mo = "";
	public String getKs2moPropertyName (){ return "ks2mo"; }
	public void setKs2mo (String value){ this.ks2mo = value;   }   
	public String getKs2mo (){ return this.ks2mo;   }              

	private String ks2nas = "";
	public String getKs2nasPropertyName (){ return "ks2nas"; }
	public void setKs2nas (String value){ this.ks2nas = value;   }   
	public String getKs2nas (){ return this.ks2nas;   }              

	
}
