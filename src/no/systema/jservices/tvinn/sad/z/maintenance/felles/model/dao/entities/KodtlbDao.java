package no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 */
public class KodtlbDao implements Serializable, IDao {
	
	private String klbsta = "";                                
	public String getKlbstaPropertyName (){ return "klbsta"; }
	public void setKlbsta (String value){ this.klbsta = value;   }   
	public String getKlbsta (){ return this.klbsta;   }  
	
	private String klbuni = "LB"; //always
	public String getKlbuniPropertyName (){ return "klbuni"; }
	public void setKlbuni (String value){ this.klbuni = value;   }   
	public String getKlbuni (){ return this.klbuni;   }              

	private String klbkod = "";
	public String getKlbkodPropertyName (){ return "klbkod"; }
	public void setKlbkod (String value){ this.klbkod = value;   }   
	public String getKlbkod (){ return this.klbkod;   }              

	private String klbkt = "";
	public String getKlbktPropertyName (){ return "klbkt"; }
	public void setKlbkt (String value){ this.klbkt = value;   }   
	public String getKlbkt (){ return this.klbkt;   }              

	private String klbnav = ""; 
	public String getKlbnavPropertyName (){ return "klbnav"; }
	public void setKlbnav (String value){ this.klbnav = value;   }   
	public String getKlbnav (){ return this.klbnav;   }              

	private String klbfok = ""; 
	public String getKlbfokPropertyName (){ return "klbfok"; }
	public void setKlbfok (String value){ this.klbfok = value;   }   
	public String getKlbfok (){ return this.klbfok;   }              

	private String klbprm = ""; 
	public String getKlbprmPropertyName (){ return "klbprm"; }
	public void setKlbprm (String value){ this.klbprm = value;   }   
	public String getKlbprm (){ return this.klbprm;   }              

	private Double klbprmNum = 0.0D; 
	public void setKlbprmNum (Double value){ this.klbprmNum = value;   }   
	public Double getKlbprmNum (){
		if(klbprm!=null && !"".equals(klbprm)){
			String tmp = this.klbprm.replace(",", ".");
			this.klbprmNum = Double.parseDouble(tmp);
		}
		return this.klbprmNum;  
	}              

	private String klbfrk = ""; 
	public String getKlbfrkPropertyName (){ return "klbfrk"; }
	public void setKlbfrk (String value){ this.klbfrk = value;   }   
	public String getKlbfrk (){ return this.klbfrk;   }              

	private String klbxxx = ""; 
	public String getKlbxxxPropertyName (){ return "klbxxx"; }
	public void setKlbxxx (String value){ this.klbxxx = value;   }   
	public String getKlbxxx (){ return this.klbxxx;   }              
	
	
}
