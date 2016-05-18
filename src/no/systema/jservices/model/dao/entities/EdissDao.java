package no.systema.jservices.model.dao.entities;

import java.io.Serializable;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public class EdissDao implements Serializable, IDao {
	
	private String sssn = "";                                
	public void setSssn (String value){ this.sssn = value;   }   
	public String getSssn (){ return this.sssn;   }              

	private String ssdt = "";                                
	public void setSsdt (String value){ this.ssdt = value;   }   
	public String getSsdt (){ return this.ssdt;   }              

	private String sstm = "";                                
	public void setSstm (String value){ this.sstm = value;   }   
	public String getSstm (){ return this.sstm;   }              

	private String ssst = "";                                
	public void setSsst (String value){ this.ssst = value;   }   
	public String getSsst (){ return this.ssst;   }              

	private String sstext = "";                                
	public void setSstext (String value){ this.sstext = value;   }   
	public String getSstext (){ return this.sstext;   }              

	
}
