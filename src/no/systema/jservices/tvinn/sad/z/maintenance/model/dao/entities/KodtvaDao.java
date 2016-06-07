package no.systema.jservices.tvinn.sad.z.maintenance.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 1, 2016
 * 
 */
public class KodtvaDao implements Serializable, IDao {

	private String kvasta = "";                                
	public String getKvastaPropertyName (){ return "kvasta"; }
	public void setKvasta (String value){ this.kvasta = value;   }   
	public String getKvasta (){ return this.kvasta;   }  
	
	private String kvauni = "VA"; 
	public String getKvauniPropertyName (){ return "kvauni"; }
	public void setKvauni (String value){ this.kvauni = value;   }   
	public String getKvauni (){ return this.kvauni;   }              

	private String kvakod = "";
	public String getKvakodPropertyName (){ return "kvakod"; }
	public void setKvakod (String value){ this.kvakod = value;   }   
	public String getKvakod (){ return this.kvakod;   }              

	private String kvakrs = "0";
	public String getKvakrsPropertyName (){ return "kvakrs"; }
	public void setKvakrs (String value){ this.kvakrs = value;   }   
	public String getKvakrs (){ return this.kvakrs;   }              

	private String kvaomr = "0"; 
	public String getKvaomrPropertyName (){ return "kvaomr"; }
	public void setKvaomr (String value){ this.kvaomr = value;   }   
	public String getKvaomr (){ return this.kvaomr;   }              

	private String kvadt = ""; 
	public String getKvadtPropertyName (){ return "kvadt"; }
	public void setKvadt (String value){ this.kvadt = value;   }   
	public String getKvadt (){ return this.kvadt;   }              

	private String kvagkr = "0"; 
	public String getKvagkrPropertyName (){ return "kvagkr"; }
	public void setKvagkr (String value){ this.kvagkr = value;   }   
	public String getKvagkr (){ return this.kvagkr;   }              

	private String kvaxxx = ""; 
	public String getKvaxxxPropertyName (){ return "kvaxxx"; }
	public void setKvaxxx (String value){ this.kvaxxx = value;   }   
	public String getKvaxxx (){ return this.kvaxxx;   }              

	private String kvagv = "0"; 
	public String getKvagvPropertyName (){ return "kvagv"; }
	public void setKvagv (String value){ this.kvagv = value;   }   
	public String getKvagv (){ return this.kvagv;   }              
	
	
}
