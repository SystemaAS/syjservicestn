package no.systema.jservices.model.dao.entities;

import java.io.Serializable;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 19, 2016
 * 
 */
public class EdisssDao implements Serializable, IDao {
	
	private String ssssn = "";                                
	public void setSsssn (String value){ this.ssssn = value;   }   
	public String getSsssn (){ return this.ssssn;   }              

	private String sssdt = "";                                
	public void setSssdt (String value){ this.sssdt = value;   }   
	public String getSssdt (){ return this.sssdt;   }              

	private String ssstm = "";                                
	public void setSsstm (String value){ this.ssstm = value;   }   
	public String getSsstm (){ return this.ssstm;   }              

	private String sssdata = "";                                
	public void setSssdata (String value){ this.sssdata = value;   }   
	public String getSssdata (){ return this.sssdata;   }              

	
}
