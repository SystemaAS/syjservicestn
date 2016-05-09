package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
import java.io.Serializable;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 6, 2016
 * 
 */
public class TariDao implements Serializable {
	
	private String tatanr = "";                                
	public String getTatanrPropertyName (){ return "tatanr"; }
	public void setTatanr (String value){ this.tatanr = value;   }   
	public String getTatanr (){ return this.tatanr;   }  
	
	private String tatar = ""; 
	public String getTatarPropertyName (){ return "tatar"; }
	public void setTatar (String value){ this.tatar = value;   }   
	public String getTatar (){ return this.tatar;   }              

	private String tadato = "0";
	public String getTadatoPropertyName (){ return "tadato"; }
	public void setTadato (String value){ this.tadato = value;   }   
	public String getTadato (){ return this.tadato;   }              

	private String tadts = "0";
	public String getTadtsPropertyName (){ return "tadts"; }
	public void setTadts (String value){ this.tadts = value;   }   
	public String getTadts (){ return this.tadts;   }              

	private String tadtr = "0"; 
	public String getTadtrPropertyName (){ return "tadtr"; }
	public void setTadtr (String value){ this.tadtr = value;   }   
	public String getTadtr (){ return this.tadtr;   }              

	private String taordk = ""; 
	public String getTaordkPropertyName (){ return "taordk"; }
	public void setTaordk (String value){ this.taordk = value;   }   
	public String getTaordk (){ return this.taordk;   }              

	private String taordb = "0"; 
	public String getTaordbPropertyName (){ return "taordb"; }
	public void setTaordb (String value){ this.taordb = value;   }   
	public String getTaordb (){ return this.taordb;   }              

	
	private String taeftk = ""; 
	public String getTaeftkPropertyName (){ return "taeftk"; }
	public void setTaeftk (String value){ this.taeftk = value;   }   
	public String getTaeftk (){ return this.taeftk;   }              

	private String taeftb = "0"; 
	public String getTaeftbPropertyName (){ return "taeftb"; }
	public void setTaeftb (String value){ this.taeftb = value;   }   
	public String getTaeftb (){ return this.taeftb;   }              
	
	private String taefk = ""; 
	public String getTaefkPropertyName (){ return "taefk"; }
	public void setTaefk (String value){ this.taefk = value;   }   
	public String getTaefk (){ return this.taefk;   }              

	private String taefb = "0"; 
	public String getTaefbPropertyName (){ return "taefb"; }
	public void setTaefb (String value){ this.taefb = value;   }   
	public String getTaefb (){ return this.taefb;   }              
	
	private String tatxt = ""; 
	public String getTatxtPropertyName (){ return "tatxt"; }
	public void setTatxt (String value){ this.tatxt = value;   }   
	public String getTatxt (){ return this.tatxt;   }              

	private String takapn = ""; 
	public String getTakapnPropertyName (){ return "takapn"; }
	public void setTakapn (String value){ this.takapn = value;   }   
	public String getTakapn (){ return this.takapn;   }              
	
	private String taalfa = ""; 
	public String getTaalfaPropertyName (){ return "taalfa"; }
	public void setTaalfa (String value){ this.taalfa = value;   }   
	public String getTaalfa (){ return this.taalfa;   }              
	
	private String taalfaOrig = ""; 
	public String getTaalfaOrigPropertyName (){ return "taalfaOrig"; }
	public void setTaalfaOrig (String value){ this.taalfaOrig = value;   }   
	public String getTaalfaOrig (){ return this.taalfaOrig;   }              
	
	private String tastk = ""; 
	public String getTastkPropertyName (){ return "tastk"; }
	public void setTastk (String value){ this.tastk = value;   }   
	public String getTastk (){ return this.tastk;   }              
	
	private String takap = ""; 
	public String getTakapPropertyName (){ return "takap"; }
	public void setTakap (String value){ this.takap = value;   }   
	public String getTakap (){ return this.takap;   }              
	
	private String takapa = ""; 
	public String getTakapaPropertyName (){ return "takapa"; }
	public void setTakapa (String value){ this.takapa = value;   }   
	public String getTakapa (){ return this.takapa;   }              
	
	private String taenhe = ""; 
	public String getTaenhePropertyName (){ return "taenhe"; }
	public void setTaenhe (String value){ this.taenhe = value;   }   
	public String getTaenhe (){ return this.taenhe;   }              
	
	private String tarest = ""; 
	public String getTarestPropertyName (){ return "tarest"; }
	public void setTarest (String value){ this.tarest = value;   }   
	public String getTarest (){ return this.tarest;   }              
	
	private String takdae = ""; 
	public String getTakdaePropertyName (){ return "takdae"; }
	public void setTakdae (String value){ this.takdae = value;   }   
	public String getTakdae (){ return this.takdae;   }              
	
	private String takdsae = ""; 
	public String getTakdsaePropertyName (){ return "takdsae"; }
	public void setTakdsae (String value){ this.takdsae = value;   }   
	public String getTakdsae (){ return this.takdsae;   }              
	
	//countries
	private String taeosb = "0"; 
	public String getTaeosbPropertyName (){ return "taeosb"; }
	public void setTaeosb (String value){ this.taeosb = value;   }   
	public String getTaeosb (){ return this.taeosb;   }              
	
	private String taeosk = ""; 
	public String getTaeoskPropertyName (){ return "taeosk"; }
	public void setTaeosk (String value){ this.taeosk = value;   }   
	public String getTaeosk (){ return this.taeosk;   } 
	
	private String tatsjb = "0"; 
	public String getTatsjbPropertyName (){ return "tatsjb"; }
	public void setTatsjb (String value){ this.tatsjb = value;   }   
	public String getTatsjb (){ return this.tatsjb;   }              
	
	private String tatsjk = ""; 
	public String getTatsjkPropertyName (){ return "tatsjk"; }
	public void setTatsjk (String value){ this.tatsjk = value;   }   
	public String getTatsjk (){ return this.tatsjk;   }              
	
	private String tatyrb = "0"; 
	public String getTatyrbPropertyName (){ return "tatyrb"; }
	public void setTatyrb (String value){ this.tatyrb = value;   }   
	public String getTatyrb (){ return this.tatyrb;   }              
	
	private String tatyrk = ""; 
	public String getTatyrkPropertyName (){ return "tatyrk"; }
	public void setTatyrk (String value){ this.tatyrk = value;   }   
	public String getTatyrk (){ return this.tatyrk;   }              
	
	private String taisrb = "0"; 
	public String getTaisrbPropertyName (){ return "taisrb"; }
	public void setTaisrb (String value){ this.taisrb = value;   }   
	public String getTaisrb (){ return this.taisrb;   }              
	
	private String taisrk = ""; 
	public String getTaisrkPropertyName (){ return "taisrk"; }
	public void setTaisrk (String value){ this.taisrk = value;   }   
	public String getTaisrk (){ return this.taisrk;   }              
	
	private String taellb = "0"; 
	public String getTaellbPropertyName (){ return "taellb"; }
	public void setTaellb (String value){ this.taellb = value;   }   
	public String getTaellb (){ return this.taellb;   }              
	
	private String taellk = ""; 
	public String getTaellkPropertyName (){ return "taellk"; }
	public void setTaellk (String value){ this.taellk = value;   }   
	public String getTaellk (){ return this.taellk;   }              
	
	private String tabulb = ""; 
	public String getTabulbPropertyName (){ return "tabulb"; }
	public void setTabulb (String value){ this.tabulb = value;   }   
	public String getTabulb (){ return this.tabulb;   }              
	
	private String tabulk = ""; 
	public String getTabulkPropertyName (){ return "tabulk"; }
	public void setTabulk (String value){ this.tabulk = value;   }   
	public String getTabulk (){ return this.tabulk;   }              
	
	private String tapolb = ""; 
	public String getTapolbPropertyName (){ return "tapolb"; }
	public void setTapolb (String value){ this.tapolb = value;   }   
	public String getTapolb (){ return this.tapolb;   }              
	
	private String tapolk = ""; 
	public String getTapolkPropertyName (){ return "tapolk"; }
	public void setTapolk (String value){ this.tapolk = value;   }   
	public String getTapolk (){ return this.tapolk;   }              
	
	private String taromb = ""; 
	public String getTarombPropertyName (){ return "taromb"; }
	public void setTaromb (String value){ this.taromb = value;   }   
	public String getTaromb (){ return this.taromb;   }              
	
	private String taromk = ""; 
	public String getTaromkPropertyName (){ return "taromk"; }
	public void setTaromk (String value){ this.taromk = value;   }   
	public String getTaromk (){ return this.taromk;   }              
	
	private String tan05b = ""; 
	public String getTan05bPropertyName (){ return "tan05b"; }
	public void setTan05b (String value){ this.tan05b = value;   }   
	public String getTan05b (){ return this.tan05b;   }              
	
	private String tan05k = ""; 
	public String getTan05kPropertyName (){ return "tan05k"; }
	public void setTan05k (String value){ this.tan05k = value;   }   
	public String getTan05k (){ return this.tan05k;   }              
	
	private String tan06b = ""; 
	public String getTan06bPropertyName (){ return "tan06b"; }
	public void setTan06b (String value){ this.tan06b = value;   }   
	public String getTan06b (){ return this.tan06b;   }              
	
	private String tan06k = ""; 
	public String getTan06kPropertyName (){ return "tan06k"; }
	public void setTan06k (String value){ this.tan06k = value;   }   
	public String getTan06k (){ return this.tan06k;   }              
	
	private String taungb = ""; 
	public String getTaungbPropertyName (){ return "taungb"; }
	public void setTaungb (String value){ this.taungb = value;   }   
	public String getTaungb (){ return this.taungb;   }              
	
	private String taungk = ""; 
	public String getTaungkPropertyName (){ return "taungk"; }
	public void setTaungk (String value){ this.taungk = value;   }   
	public String getTaungk (){ return this.taungk;   }              
	
	private String taslob = ""; 
	public String getTaslobPropertyName (){ return "taslob"; }
	public void setTaslob (String value){ this.taslob = value;   }   
	public String getTaslob (){ return this.taslob;   }              
	
	private String taslok = ""; 
	public String getTaslokPropertyName (){ return "taslok"; }
	public void setTaslok (String value){ this.taslok = value;   }   
	public String getTaslok (){ return this.taslok;   }              
	
	private String tamulb = ""; 
	public String getTamulbPropertyName (){ return "tamulb"; }
	public void setTamulb (String value){ this.tamulb = value;   }   
	public String getTamulb (){ return this.tamulb;   }  
	
	private String tamulk = ""; 
	public String getTamulkPropertyName (){ return "tamulk"; }
	public void setTamulk (String value){ this.tamulk = value;   }   
	public String getTamulk (){ return this.tamulk;   }              
	
	private String taoulb = ""; 
	public String getTaoulbPropertyName (){ return "taoulb"; }
	public void setTaoulb (String value){ this.taoulb = value;   }   
	public String getTaoulb (){ return this.taoulb;   }              
	
	private String taoulk = ""; 
	public String getTaoulkPropertyName (){ return "taoulk"; }
	public void setTaoulk (String value){ this.taoulk = value;   }   
	public String getTaoulk (){ return this.taoulk;   }              
	
	private String tagrlb = ""; 
	public String getTagrlbPropertyName (){ return "tagrlb"; }
	public void setTagrlb (String value){ this.tagrlb = value;   }   
	public String getTagrlb (){ return this.tagrlb;   }              
	
	private String tagrlk = ""; 
	public String getTagrlkPropertyName (){ return "tagrlk"; }
	public void setTagrlk (String value){ this.tagrlk = value;   }   
	public String getTagrlk (){ return this.tagrlk;   }              
	
	                       

	
	
}
