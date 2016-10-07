package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities;
import java.io.Serializable;

import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * This Dao service also exists i bcore
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 * 
 */
public class TrkodfDao implements Serializable, IDao {
	
	private String tkunik = "";                             
	public String getTkunikPropertyName (){ return "tkunik"; }
	public void setTkunik (String value){ this.tkunik = value;   }   
	public String getTkunik (){ return this.tkunik;   }  
	
	private String tkkode = "";                                
	public String getTkkodePropertyName (){ return "tkkode"; }
	public void setTkkode (String value){ this.tkkode = value;   }   
	public String getTkkode (){ return this.tkkode;   }  
	
	private String tktxtn = "";                                
	public String getTktxtnPropertyName (){ return "tktxtn"; }
	public void setTktxtn (String value){ this.tktxtn = value;   }   
	public String getTktxtn (){ return this.tktxtn;   }  
	
	private String tktxte = "";                                
	public String getTktxtePropertyName (){ return "tktxte"; }
	public void setTktxte (String value){ this.tktxte = value;   }   
	public String getTktxte (){ return this.tktxte;   }  
	
	private String tkavg = "";                                
	public String getTkavgPropertyName (){ return "tkavg"; }
	public void setTkavg (String value){ this.tkavg = value;   }   
	public String getTkavg (){ return this.tkavg;   }  
	
	
	private String tkank = "";                                
	public String getTkankPropertyName (){ return "tkank"; }
	public void setTkank (String value){ this.tkank = value;   }   
	public String getTkank (){ return this.tkank;   }  
	
	private String tktrs = "";                                
	public String getTktrsPropertyName (){ return "tktrs"; }
	public void setTktrs (String value){ this.tktrs = value;   }   
	public String getTktrs (){ return this.tktrs;   }  
	
}
