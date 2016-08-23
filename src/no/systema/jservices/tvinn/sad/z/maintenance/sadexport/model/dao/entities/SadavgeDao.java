package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the
 * db-table
 * 
 * <li> AGTANR SONET 8 : TARIFFNR. </li>
 * <li> TAALF		   : BESK (Note: from table TARI) </li>
 * <li> AGAKD  TEGN  2 : AVGIFTKODE </li> 
 * <li> AGSKV  TEGN  3 : SEKVENS </li>
 * <li> AGDTF  SONET 8 : F.O.M. DATO </li> 
 * <li> AGDTT  SONET 8 : T.O.M. DATO </li>  
 * <li> AGKD   TEGN  1 : B=EU-LAND </li>   
 * <li> AGPP   TEGN  1 : PROSENT/PROMILLE </li>  
 * <li> AGSATS SONET 5 : SATS </li>  
 * <li> AGAKTK TEGN  1 : AKTIVKODE </li> 
 * 
 * @author Fredrik Möller
 * @date June 28, 2016
 * 
 */

@SuppressWarnings("serial")
public class SadavgeDao implements Serializable, IDao {			
	
	private String agtanr = "";                            
	public String getAgtanrPropertyName (){ return "agtanr"; }
	public void setAgtanr (String value){ this.agtanr = value;   }   
	public String getAgtanr (){ return this.agtanr;   }  

	private String taalfa = "";                            
	public String getTaalfaPropertyName (){ return "taalfa"; }
	public void setTaalfa (String value){ this.taalfa = value;   }   
	public String getTaalfa (){ return this.taalfa;   }  
		
	private String agakd = "";                                
	public String getAgakdPropertyName (){ return "agakd"; }
	public void setAgakd (String value){ this.agakd = value;   }   
	public String getAgakd (){ return this.agakd;   }  	
	
	private String agskv = "";                                
	public String getAgskvPropertyName (){ return "agskv"; }
	public void setAgskv (String value){ this.agskv = value;   }   
	public String getAgskv (){ return this.agskv;   }  
	
	private String agdtf = "";                                
	public String getAgdtfPropertyName (){ return "agdtf"; }
	public void setAgdtf (String value){ this.agdtf = value;   }   
	public String getAgdtf (){ return this.agdtf;   }  
	
	private String agdtt = "";                                
	public String getAgdttPropertyName (){ return "agdtt"; }
	public void setAgdtt (String value){ this.agdtt = value;   }   
	public String getAgdtt (){ return this.agdtt;   }  
	
	private String agkd = "";                                
	public String getAgkdPropertyName (){ return "agkd"; }
	public void setAgkd (String value){ this.agkd = value;   }   
	public String getAgkd (){ return this.agkd;   }  
	 
	private String agpp = "";                                
	public String getAgppPropertyName (){ return "agpp"; }
	public void setAgpp (String value){ this.agpp = value;   }   
	public String getAgpp (){ return this.agpp;   }  
	 	
	private String agsats = "";                                
	public String getAgsatsPropertyName (){ return "agsats"; }
	public void setAgsats (String value){ this.agsats = value;   }   
	public String getAgsats (){ return this.agsats;   }  

	private String agaktk = "";                                
	public String getAgaktkPropertyName (){ return "agaktk"; }
	public void setAgaktk (String value){ this.agaktk = value;   }   
	public String getAgaktk (){ return this.agaktk;   }  	
	
	
   public String toString() {
	   StringBuffer ret = new StringBuffer(this.getClass().getName());
	   ret.append(":[ agtanr="+getAgtanr());
	   ret.append(", agakd="+getAgakd());
	   ret.append(", agskv="+getAgskv());
	   ret.append(", agdtf="+getAgdtf());
	   ret.append(", agdtt="+getAgdtt());
	   ret.append(", agkd="+getAgkd());
	   ret.append(", agpp="+getAgpp());
	   ret.append(", agsats="+getAgsats());
	   ret.append(", agaktk="+getAgaktk()+"]");
	   return ret.toString();
	
   }
	
}
