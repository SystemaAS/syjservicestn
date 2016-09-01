package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * <li> SEAVD : SONET  4 </li>
 * <li> SETDN : SONET  7 </li>
 * <li> SENAS : TEGN   30  </li>
 * <li> SETLL : TEGN   10  </li>
 * <li> SETLE : TEGN   6  </li>
 * <li> SEDTG : TEGN   10  </li>    
 *           
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 */
public class SaehDao implements Serializable, IDao {
	
	private String seavd = "";                                
	public String getSeavdPropertyName (){ return "seavd"; }
	public void setSeavd (String value){ this.seavd = value;   }   
	public String getSeavd (){ return this.seavd;   }  
	
	private String setdn = "";
	public String getSetdnPropertyName (){ return "setdn"; }
	public void setSetdn (String value){ this.setdn = value;   }   
	public String getSetdn (){ return this.setdn;   }              

	private String senas = "";
	public String getSenasPropertyName (){ return "senas"; }
	public void setSenas (String value){ this.senas = value;   }   
	public String getSenas (){ return this.senas;   }              

	private String setll = "";
	public String getSetllPropertyName (){ return "setll"; }
	public void setSetll (String value){ this.setll = value;   }   
	public String getSetll (){ return this.setll;   }              

	private String setle = ""; 
	public String getSetlePropertyName (){ return "setle"; }
	public void setSetle (String value){ this.setle = value;   }   
	public String getSetle (){ return this.setle;   }              

	private String sedtg = ""; 
	public String getSedtgPropertyName (){ return "sedtg"; }
	public void setSedtg (String value){ this.sedtg = value;   }   
	public String getSedtg (){ return this.sedtg;   }     
	
	
	public String toString() {
		StringBuffer ret = new StringBuffer(this.getClass().getName());
		ret.append(":[ seavd=" + getSeavd());
		ret.append(", setdn=" + getSetdn());
		ret.append(", senas=" + getSenas());
		ret.append(", setll=" + getSetll());
		ret.append(", setle=" + getSetle());
		ret.append(", sedtg=" + getSedtg() + "]");
		return ret.toString();
	}
	
}
