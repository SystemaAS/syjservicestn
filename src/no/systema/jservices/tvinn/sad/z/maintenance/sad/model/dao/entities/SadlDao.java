package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 27, 2016
 * 
 */
public class SadlDao implements Serializable, IDao {

	private String slstat = "";                                
	public String getSlstatPropertyName (){ return "slstat"; }
	public void setSlstat (String value){ this.slstat = value;   }   
	public String getSlstat (){ return this.slstat;   }  
	
	private String slknr = ""; 
	public String getSlknrPropertyName (){ return "slknr"; }
	public void setSlknr (String value){ this.slknr = value;   }   
	public String getSlknr (){ return this.slknr;   }              

	private String slalfa = "";
	public String getSlalfaPropertyName (){ return "slalfa"; }
	public void setSlalfa (String value){ this.slalfa = value;   }   
	public String getSlalfa (){ return this.slalfa;   }              

	private String sltxt = "";
	public String getSltxtPropertyName (){ return "sltxt"; }
	public void setSltxt (String value){ this.sltxt = value;   }   
	public String getSltxt (){ return this.sltxt;   }              

	private String sloppl = ""; 
	public String getSlopplPropertyName (){ return "sloppl"; }
	public void setSloppl (String value){ this.sloppl = value;   }   
	public String getSloppl (){ return this.sloppl;   }              

	private String slvekt = "0"; 
	public String getSlvektPropertyName (){ return "slvekt"; }
	public void setSlvekt (String value){ this.slvekt = value;   }   
	public String getSlvekt (){ return this.slvekt;   }              

	private String sltanr = "0"; 
	public String getSltanrPropertyName (){ return "sltanr"; }
	public void setSltanr (String value){ this.sltanr = value;   }   
	public String getSltanr (){ return this.sltanr;   }              

	private String sltar = ""; 
	public String getSltarPropertyName (){ return "sltar"; }
	public void setSltar (String value){ this.sltar = value;   }   
	public String getSltar (){ return this.sltar;   }              

	private String slpva = "0"; 
	public String getSlpvaPropertyName (){ return "slpva"; }
	public void setSlpva (String value){ this.slpva = value;   }   
	public String getSlpva (){ return this.slpva;   }              
	
	private String slsats = "0"; 
	public String getSlsatsPropertyName (){ return "slsats"; }
	public void setSlsats (String value){ this.slsats = value;   }   
	public String getSlsats (){ return this.slsats;   }              

	private String sltn = ""; 
	public String getSltnPropertyName (){ return "sltn"; }
	public void setSltn (String value){ this.sltn = value;   }   
	public String getSltn (){ return this.sltn;   }              
	
	private String slkdae = "0"; 
	public String getSlkdaePropertyName (){ return "slkdae"; }
	public void setSlkdae (String value){ this.slkdae = value;   }   
	public String getSlkdae (){ return this.slkdae;   }              

	private String slkdse = ""; 
	public String getSlkdsePropertyName (){ return "slkdse"; }
	public void setSlkdse (String value){ this.slkdse = value;   }   
	public String getSlkdse (){ return this.slkdse;   }              
	
	private String slto = ""; 
	public String getSltoPropertyName (){ return "slto"; }
	public void setSlto (String value){ this.slto = value;   }   
	public String getSlto (){ return this.slto;   }              
	
	private String slcref = ""; 
	public String getSlcrefPropertyName (){ return "slcref"; }
	public void setSlcref (String value){ this.slcref = value;   }   
	public String getSlcref (){ return this.slcref;   }              
	
	private String r31 = ""; 
	public String getR31PropertyName (){ return "r31"; }
	public void setR31 (String value){ this.r31 = value;   }   
	public String getR31 (){ return this.r31;   }              

	private String pref = ""; 
	public String getPrefPropertyName (){ return "pref"; }
	public void setPref (String value){ this.pref = value;   }   
	public String getPref (){ return this.pref;   }              

	private String mf = ""; 
	public String getMfPropertyName (){ return "mf"; }
	public void setMf (String value){ this.mf = value;   }   
	public String getMf (){ return this.mf;   }            
	
}
