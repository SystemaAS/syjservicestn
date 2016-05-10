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
	
	//Albania
	private String tan06b = ""; 
	public String getTan06bPropertyName (){ return "tan06b"; }
	public void setTan06b (String value){ this.tan06b = value;   }   
	public String getTan06b (){ return this.tan06b;   }              
	
	private String tan06k = ""; 
	public String getTan06kPropertyName (){ return "tan06k"; }
	public void setTan06k (String value){ this.tan06k = value;   }   
	public String getTan06k (){ return this.tan06k;   }              
	
	//Ukraina
	private String tan07b = ""; 
	public String getTan07bPropertyName (){ return "tan07b"; }
	public void setTan07b (String value){ this.tan07b = value;   }   
	public String getTan07b (){ return this.tan07b;   }              
	
	private String tan07k = ""; 
	public String getTan07kPropertyName (){ return "tan07k"; }
	public void setTan07k (String value){ this.tan07k = value;   }   
	public String getTan07k (){ return this.tan07k;   }              
	
	//Jordan
	private String taungb = ""; 
	public String getTaungbPropertyName (){ return "taungb"; }
	public void setTaungb (String value){ this.taungb = value;   }   
	public String getTaungb (){ return this.taungb;   }              
	
	private String taungk = ""; 
	public String getTaungkPropertyName (){ return "taungk"; }
	public void setTaungk (String value){ this.taungk = value;   }   
	public String getTaungk (){ return this.taungk;   }              
	
	//Tunisia
	private String taslob = ""; 
	public String getTaslobPropertyName (){ return "taslob"; }
	public void setTaslob (String value){ this.taslob = value;   }   
	public String getTaslob (){ return this.taslob;   }              
	
	private String taslok = ""; 
	public String getTaslokPropertyName (){ return "taslok"; }
	public void setTaslok (String value){ this.taslok = value;   }   
	public String getTaslok (){ return this.taslok;   }              
	
	//Min uland
	private String tamulb = ""; 
	public String getTamulbPropertyName (){ return "tamulb"; }
	public void setTamulb (String value){ this.tamulb = value;   }   
	public String getTamulb (){ return this.tamulb;   }  
	
	private String tamulk = ""; 
	public String getTamulkPropertyName (){ return "tamulk"; }
	public void setTamulk (String value){ this.tamulk = value;   }   
	public String getTamulk (){ return this.tamulk;   }              
	
	//Ord.uland
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
	
	private String taferb = ""; 
	public String getTaferbPropertyName (){ return "taferb"; }
	public void setTaferb (String value){ this.taferb = value;   }   
	public String getTaferb (){ return this.taferb;   }              
	
	private String taferk = ""; 
	public String getTaferkPropertyName (){ return "taferk"; }
	public void setTaferk (String value){ this.taferk = value;   }   
	public String getTaferk (){ return this.taferk;   }              
	
	private String taistb = ""; 
	public String getTaistbPropertyName (){ return "taistb"; }
	public void setTaistb (String value){ this.taistb = value;   }   
	public String getTaistb (){ return this.taistb;   }              
	
	private String taistk = ""; 
	public String getTaistkPropertyName (){ return "taistk"; }
	public void setTaistk (String value){ this.taistk = value;   }   
	public String getTaistk (){ return this.taistk;   }              
	                       
	
	private String tamarb = ""; 
	public String getTamarbPropertyName (){ return "tamarb"; }
	public void setTamarb (String value){ this.tamarb = value;   }   
	public String getTamarb (){ return this.tamarb;   }              
	
	private String tamark = ""; 
	public String getTamarkPropertyName (){ return "tamark"; }
	public void setTamark (String value){ this.tamark = value;   }   
	public String getTamark (){ return this.tamark;   }              
	
	//Peru
	private String tan08b = ""; 
	public String getTan08bPropertyName (){ return "tan08b"; }
	public void setTan08b (String value){ this.tan08b = value;   }   
	public String getTan08b (){ return this.tan08b;   }              
	
	private String tan08k = ""; 
	public String getTan08kPropertyName (){ return "tan08k"; }
	public void setTan08k (String value){ this.tan08k = value;   }   
	public String getTan08k (){ return this.tan08k;   }              
	
	//Montenegro
	private String tan09b = ""; 
	public String getTan09bPropertyName (){ return "tan09b"; }
	public void setTan09b (String value){ this.tan09b = value;   }   
	public String getTan09b (){ return this.tan09b;   }              
	
	private String tan09k = ""; 
	public String getTan09kPropertyName (){ return "tan09k"; }
	public void setTan09k (String value){ this.tan09k = value;   }   
	public String getTan09k (){ return this.tan09k;   }              
	
	//Hong Kong
	private String tan10b = ""; 
	public String getTan10bPropertyName (){ return "tan10b"; }
	public void setTan10b (String value){ this.tan10b = value;   }   
	public String getTan10b (){ return this.tan10b;   }              
	
	private String tan10k = ""; 
	public String getTan10kPropertyName (){ return "tan10k"; }
	public void setTan10k (String value){ this.tan10k = value;   }   
	public String getTan10k (){ return this.tan10k;   }              
	
	//Mexico
	private String tamexb = ""; 
	public String getTamexbPropertyName (){ return "tamexb"; }
	public void setTamexb (String value){ this.tamexb = value;   }   
	public String getTamexb (){ return this.tamexb;   }              
	
	private String tamexk = ""; 
	public String getTamexkPropertyName (){ return "tamexk"; }
	public void setTamexk (String value){ this.tamexk = value;   }   
	public String getTamexk (){ return this.tamexk;   }              
	
	//VB & GAZA
	private String tavgab = ""; 
	public String getTavgabPropertyName (){ return "tavgab"; }
	public void setTavgab (String value){ this.tavgab = value;   }   
	public String getTavgab (){ return this.tavgab;   }              
	
	private String tavgak = ""; 
	public String getTavgakPropertyName (){ return "tavgak"; }
	public void setTavgak (String value){ this.tavgak = value;   }   
	public String getTavgak (){ return this.tavgak;   }              
	
	
	//Libanon
	private String tan01b = ""; 
	public String getTan01bPropertyName (){ return "tan01b"; }
	public void setTan01b (String value){ this.tan01b = value;   }   
	public String getTan01b (){ return this.tan01b;   }              
	
	private String tan01k = ""; 
	public String getTan01kPropertyName (){ return "tan01k"; }
	public void setTan01k (String value){ this.tan01k = value;   }   
	public String getTan01k (){ return this.tan01k;   }              
	
	//SACU
	private String tan02b = ""; 
	public String getTan02bPropertyName (){ return "tan02b"; }
	public void setTan02b (String value){ this.tan02b = value;   }   
	public String getTan02b (){ return this.tan02b;   }              
	
	private String tan02k = ""; 
	public String getTan02kPropertyName (){ return "tan02k"; }
	public void setTan02k (String value){ this.tan02k = value;   }   
	public String getTan02k (){ return this.tan02k;   }              
	
	//Egypt
	private String tan03b = ""; 
	public String getTan03bPropertyName (){ return "tan03b"; }
	public void setTan03b (String value){ this.tan03b = value;   }   
	public String getTan03b (){ return this.tan03b;   }              
	
	private String tan03k = ""; 
	public String getTan03kPropertyName (){ return "tan03k"; }
	public void setTan03k (String value){ this.tan03k = value;   }   
	public String getTan03k (){ return this.tan03k;   }              
	
	//Canada
	private String tan04b = ""; 
	public String getTan04bPropertyName (){ return "tan04b"; }
	public void setTan04b (String value){ this.tan04b = value;   }   
	public String getTan04b (){ return this.tan04b;   }              
	
	private String tan04k = ""; 
	public String getTan04kPropertyName (){ return "tan04k"; }
	public void setTan04k (String value){ this.tan04k = value;   }   
	public String getTan04k (){ return this.tan04k;   }              
	
	//BW, NA, SZ
	private String tan11b = ""; 
	public String getTan11bPropertyName (){ return "tan11b"; }
	public void setTan11b (String value){ this.tan11b = value;   }   
	public String getTan11b (){ return this.tan11b;   }              
	
	private String tan11k = ""; 
	public String getTan11kPropertyName (){ return "tan11k"; }
	public void setTan11k (String value){ this.tan11k = value;   }   
	public String getTan11k (){ return this.tan11k;   }              
	
	//Lavere MIL
	private String tan12b = ""; 
	public String getTan12bPropertyName (){ return "tan12b"; }
	public void setTan12b (String value){ this.tan12b = value;   }   
	public String getTan12b (){ return this.tan12b;   }              
	
	private String tan12k = ""; 
	public String getTan12kPropertyName (){ return "tan12k"; }
	public void setTan12k (String value){ this.tan12k = value;   }   
	public String getTan12k (){ return this.tan12k;   }              
	
	private String tan13b = ""; 
	public String getTan13bPropertyName (){ return "tan13b"; }
	public void setTan13b (String value){ this.tan13b = value;   }   
	public String getTan13b (){ return this.tan13b;   }              
	
	private String tan13k = ""; 
	public String getTan13kPropertyName (){ return "tan13k"; }
	public void setTan13k (String value){ this.tan13k = value;   }   
	public String getTan13k (){ return this.tan13k;   }              
	
	private String tan14b = ""; 
	public String getTan14bPropertyName (){ return "tan14b"; }
	public void setTan14b (String value){ this.tan14b = value;   }   
	public String getTan14b (){ return this.tan14b;   }              
	
	private String tan14k = ""; 
	public String getTan14kPropertyName (){ return "tan14k"; }
	public void setTan14k (String value){ this.tan14k = value;   }   
	public String getTan14k (){ return this.tan14k;   }              
	
	private String tan15b = ""; 
	public String getTan15bPropertyName (){ return "tan15b"; }
	public void setTan15b (String value){ this.tan15b = value;   }   
	public String getTan15b (){ return this.tan15b;   }              
	
	private String tan15k = ""; 
	public String getTan15kPropertyName (){ return "tan15k"; }
	public void setTan15k (String value){ this.tan15k = value;   }   
	public String getTan15k (){ return this.tan15k;   }              
	
}
