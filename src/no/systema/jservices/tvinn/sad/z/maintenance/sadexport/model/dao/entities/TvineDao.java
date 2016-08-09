package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author Fredrik Möller
 * @date June 28, 2016
 * 
 */

@SuppressWarnings("serial")
public class TvineDao implements Serializable, IDao {
	
	private String e9705 = "";                                
	public String getE9705PropertyName (){ return "e9705"; }
	public void setE9705 (String value){ this.e9705 = value;   }   
	public String getE9705 (){ return this.e9705;   }  
	
	private String e4440 = "";
	public String getE4440PropertyName (){ return "e4440"; }
	public void setE4440 (String value){ this.e4440 = value;   }   
	public String getE4440 (){ return this.e4440;   }              

   public String toString() {
	   StringBuffer ret = new StringBuffer(this.getClass().getName());
	   ret.append(": e9705="+getE9705());
	   ret.append(", e4440="+getE4440());
	   return ret.toString();
	
   }
	
}
