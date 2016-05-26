package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Maj 25, 2016
 * 
 */
public class SoktariDao implements Serializable, IDao {
	
	private String tariff = "";                                
	public String getTariffPropertyName (){ return "tariff"; }
	public void setTariff (String value){ this.tariff = value;   }   
	public String getTariff (){ return this.tariff;   }  
	
	private String fill1 = ""; 
	public String getFill1PropertyName (){ return "fill1"; }
	public void setFill1 (String value){ this.fill1 = value;   }   
	public String getFill1 (){ return this.fill1;   }              

	private String beskr1 = "";
	public String getBeskr1PropertyName (){ return "beskr1"; }
	public void setBeskr1 (String value){ this.beskr1 = value;   }   
	public String getBeskr1 (){ return this.beskr1;   }              
	
	private String beskr1Orig = "";
	public String getBeskr1OrigPropertyName (){ return "beskr1Orig"; }
	public void setBeskr1Orig (String value){ this.beskr1Orig = value;   }   
	public String getBeskr1Orig (){ return this.beskr1Orig;   }              
	
	
	
}
