package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import no.systema.jservices.model.dao.entities.IDao;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author Fredrik Möller
 * @date Sep 16, 2016
 * 
 */
@SuppressWarnings("serial")
public class TrughDao implements Serializable, IDao {

	private String tgst = "";                                
	public String getTgstPropertyName(){ return "tgst"; }
	public void setTgst(String value){ this.tgst = value;   }   
	public String getTgst(){ return this.tgst;   }  
	
	private String tggnr = "";                                
	public String getTggnrPropertyName(){ return "tggnr"; }
	public void setTggnr(String value){ this.tggnr = value;   }   
	public String getTggnr(){ return this.tggnr;   }  
	
	private String tggty = "";                                
	public String getTggtyPropertyName(){ return "tggty"; }
	public void setTggty(String value){ this.tggty = value;   }   
	public String getTggty(){ return this.tggty;   }  
	
	private String tggvk = "";                                
	public String getTggvkPropertyName(){ return "tggvk"; }
	public void setTggvk(String value){ this.tggvk = value;   }   
	public String getTggvk(){ return this.tggvk;   }  

	private String tggbl = "";                                
	public String getTggblPropertyName(){ return "tggbl"; }
	public void setTggbl(String value){ this.tggbl = value;   }   
	public String getTggbl(){ return this.tggbl;   }  
	
	private String tggblb = "";                                
	public String getTggblbPropertyName(){ return "tggblb"; }
	public void setTggblb(String value){ this.tggblb = value;   }   
	public String getTggblb(){ return this.tggblb;   }  
	
	private String tgkna = "";                                
	public String getTgknaPropertyName(){ return "tgkna"; }
	public void setTgkna(String value){ this.tgkna = value;   }   
	public String getTgkna(){ return this.tgkna;   }  

	private String tgtina = "";                                
	public String getTgtinaPropertyName(){ return "tgtina"; }
	public void setTgtina(String value){ this.tgtina = value;   }   
	public String getTgtina(){ return this.tgtina;   }  
	
	private String tgnaa = "";                                
	public String getTgnaaPropertyName(){ return "tgnaa"; }
	public void setTgnaa(String value){ this.tgnaa = value;   }   
	public String getTgnaa(){ return this.tgnaa;   }  
		
	private String tgada1 = "";                                
	public String getTgada1PropertyName(){ return "tgada1"; }
	public void setTgada1(String value){ this.tgada1 = value;   }   
	public String getTgada1(){ return this.tgada1;   }  
	
	private String tgpna = "";                                
	public String getTgpnaPropertyName(){ return "tgpna"; }
	public void setTgpna(String value){ this.tgpna = value;   }   
	public String getTgpna(){ return this.tgpna;   }  

	private String tgpsa = "";                                
	public String getTgpsaPropertyName(){ return "tgpsa"; }
	public void setTgpsa(String value){ this.tgpsa = value;   }   
	public String getTgpsa(){ return this.tgpsa;   }  

	private String tglka = "";                                
	public String getTglkaPropertyName(){ return "tglka"; }
	public void setTglka(String value){ this.tglka = value;   }   
	public String getTglka(){ return this.tglka;   }  

	private String tgtsd = "";                                
	public String getTgtsdPropertyName(){ return "tgtsd"; }
	public void setTgtsd(String value){ this.tgtsd = value;   }   
	public String getTgtsd(){ return this.tgtsd;   }  

	private String tgakny = "";                                
	public String getTgaknyPropertyName(){ return "tgakny"; }
	public void setTgakny(String value){ this.tgakny = value;   }   
	public String getTgakny(){ return this.tgakny;   }  

	private String tgakgm = "";                                
	public String getTgakgmPropertyName(){ return "tgakgm"; }
	public void setTgakgm(String value){ this.tgakgm = value;   }   
	public String getTgakgm(){ return this.tgakgm;   }  

	private String tgusr = "";                                
	public String getTgusrPropertyName(){ return "tgusr"; }
	public void setTgusr(String value){ this.tgusr = value;   }   
	public String getTgusr(){ return this.tgusr;   }  

	private String tgdt = "";                                
	public String getTgdtPropertyName(){ return "tgdt"; }
	public void setTgdt(String value){ this.tgdt = value;   }   
	public String getTgdt(){ return this.tgdt;   }  
	
	private String tgdtr = "";                                
	public String getTgdtrPropertyName(){ return "tgdtr"; }
	public void setTgdtr(String value){ this.tgdtr = value;   }   
	public String getTgdtr(){ return this.tgdtr;   }  

	private String tgprm = "";                                
	public String getTgprmPropertyName(){ return "tgprm"; }
	public void setTgprm(String value){ this.tgprm = value;   }   
	public String getTgprm(){ return this.tgprm;   }  

	private String tgrn = "";                                
	public String getTgrnPropertyName(){ return "tgrn"; }
	public void setTgrn(String value){ this.tgrn = value;   }   
	public String getTgrn(){ return this.tgrn;   }  

	private String tggfv = "";                                
	public String getTggfvPropertyName(){ return "tggfv"; }
	public void setTggfv(String value){ this.tggfv = value;   }   
	public String getTggfv(){ return this.tggfv;   }  


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


}
