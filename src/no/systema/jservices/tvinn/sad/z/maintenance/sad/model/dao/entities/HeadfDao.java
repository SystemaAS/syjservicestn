package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author Fredrik Möller
 * @date Aug 29, 2016
 * 
 */
public class HeadfDao implements Serializable, IDao {
	
	private String heavd = ""; 
	public void setHeavd (String value){ this.heavd = value;   }
	public String getHeavd (){ return this.heavd;   }              

	private String heopd = ""; 
	public void setHeopd (String value){ this.heopd = value;   }
	public String getHeopd (){ return this.heopd;   }              
	
	private String hetll = ""; 
	public void setHetll (String value){ this.hetll = value;   }   
	public String getHetll (){ return this.hetll;   }              
	
	private String hetle = ""; 
	public void setHetle (String value){ this.hetle = value;   }   
	public String getHetle (){ return this.hetle;   }              
	
	public String toString() {
		StringBuffer ret = new StringBuffer(this.getClass().getName());
		ret.append(":[heavd=" + getHeavd());
		ret.append(", heopd=" + getHeopd());
		ret.append(", hetll=" + getHetll());
		ret.append(", hetle=" + getHetle() + "]");
		return ret.toString();

	}
}
