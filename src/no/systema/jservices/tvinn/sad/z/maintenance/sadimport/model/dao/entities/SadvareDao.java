package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities;
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
public class SadvareDao implements Serializable, IDao {

	private String varenr = "";                                
	public String getVarenrPropertyName (){ return "varenr"; }
	public void setVarenr (String value){ this.varenr = value;   }   
	public String getVarenr (){ return this.varenr;   }  
	
	private String varebe = ""; 
	public String getVarebePropertyName (){ return "varebe"; }
	public void setVarebe (String value){ this.varebe = value;   }   
	public String getVarebe (){ return this.varebe;   }              

	private String levenr = "0";
	public String getLevenrPropertyName (){ return "levenr"; }
	public void setLevenr (String value){ this.levenr = value;   }   
	public String getLevenr (){ return this.levenr;   }              

	private String w2vf = "";
	public String getW2vfPropertyName (){ return "w2vf"; }
	public void setW2vf (String value){ this.w2vf = value;   }   
	public String getW2vf (){ return this.w2vf;   }              

	private String w2lk = ""; 
	public String getW2lkPropertyName (){ return "w2lk"; }
	public void setW2lk (String value){ this.w2lk = value;   }   
	public String getW2lk (){ return this.w2lk;   }              

	private String w2vnti = ""; 
	public String getW2vntiPropertyName (){ return "w2vnti"; }
	public void setW2vnti (String value){ this.w2vnti = value;   }   
	public String getW2vnti (){ return this.w2vnti;   }              

	private String w2tn = ""; 
	public String getW2tnPropertyName (){ return "w2tn"; }
	public void setW2tn (String value){ this.w2tn = value;   }   
	public String getW2tn (){ return this.w2tn;   }              

	private String w2pre = ""; 
	public String getW2prePropertyName (){ return "w2pre"; }
	public void setW2pre (String value){ this.w2pre = value;   }   
	public String getW2pre (){ return this.w2pre;   }              

	private String w2belt = "0"; 
	public String getW2beltPropertyName (){ return "w2belt"; }
	public void setW2belt (String value){ this.w2belt = value;   }   
	public String getW2belt (){ return this.w2belt;   }              
	
	private String w2vktb = "0"; 
	public String getW2vktbPropertyName (){ return "w2vktb"; }
	public void setW2vktb (String value){ this.w2vktb = value;   }   
	public String getW2vktb (){ return this.w2vktb;   }              

	private String w2vktn = "0"; 
	public String getW2vktnPropertyName (){ return "w2vktn"; }
	public void setW2vktn (String value){ this.w2vktn = value;   }   
	public String getW2vktn (){ return this.w2vktn;   }              
	
	private String w2ntm = "0"; 
	public String getW2ntmPropertyName (){ return "w2ntm"; }
	public void setW2ntm (String value){ this.w2ntm = value;   }   
	public String getW2ntm (){ return this.w2ntm;   }              

	private String w2pva = ""; 
	public String getW2pvaPropertyName (){ return "w2pva"; }
	public void setW2pva (String value){ this.w2pva = value;   }   
	public String getW2pva (){ return this.w2pva;   }              
	
	private String w2as = "0"; 
	public String getW2asPropertyName (){ return "w2as"; }
	public void setW2as (String value){ this.w2as = value;   }   
	public String getW2as (){ return this.w2as;   }              
	
	private String w2mfr = ""; 
	public String getW2mfrPropertyName (){ return "w2mfr"; }
	public void setW2mfr (String value){ this.w2mfr = value;   }   
	public String getW2mfr (){ return this.w2mfr;   }              
	
	private String w2akd1 = ""; 
	public String getW2akd1PropertyName (){ return "w2akd1"; }
	public void setW2akd1 (String value){ this.w2akd1 = value;   }   
	public String getW2akd1 (){ return this.w2akd1;   }              
	
	private String w2asv1 = ""; 
	public String getW2asv1PropertyName (){ return "w2asv1"; }
	public void setW2asv1 (String value){ this.w2asv1 = value;   }   
	public String getW2asv1 (){ return this.w2asv1;   }              
	
	private String w2asa1 = "0"; 
	public String getW2asa1PropertyName (){ return "w2asa1"; }
	public void setW2asa1 (String value){ this.w2asa1 = value;   }   
	public String getW2asa1 (){ return this.w2asa1;   }              
	
	private String w2agr1 = "0"; 
	public String getW2agr1PropertyName (){ return "w2agr1"; }
	public void setW2agr1 (String value){ this.w2agr1 = value;   }   
	public String getW2agr1 (){ return this.w2agr1;   }              
	
	private String w2abl1 = "0"; 
	public String getW2abl1PropertyName (){ return "w2abl1"; }
	public void setW2abl1 (String value){ this.w2abl1 = value;   }   
	public String getW2abl1 (){ return this.w2abl1;   }              
	
	private String w2bel = "0"; 
	public String getW2belPropertyName (){ return "w2bel"; }
	public void setW2bel (String value){ this.w2bel = value;   }   
	public String getW2bel (){ return this.w2bel;   }              
	
	
	
	private String w2akd2 = ""; 
	public String getW2akd2PropertyName (){ return "w2akd2"; }
	public void setW2akd2 (String value){ this.w2akd2 = value;   }   
	public String getW2akd2 (){ return this.w2akd2;   }              
	
	private String w2asv2 = ""; 
	public String getW2asv2PropertyName (){ return "w2asv2"; }
	public void setW2asv2 (String value){ this.w2asv2 = value;   }   
	public String getW2asv2 (){ return this.w2asv2;   }              
	
	private String w2asa2 = "0"; 
	public String getW2asa2PropertyName (){ return "w2asa2"; }
	public void setW2asa2 (String value){ this.w2asa2 = value;   }   
	public String getW2asa2 (){ return this.w2asa2;   }              
	
	private String w2agr2 = "0"; 
	public String getW2agr2PropertyName (){ return "w2agr2"; }
	public void setW2agr2 (String value){ this.w2agr2 = value;   }   
	public String getW2agr2 (){ return this.w2agr2;   }              
	
	private String w2abl2 = "0"; 
	public String getW2abl2PropertyName (){ return "w2abl2"; }
	public void setW2abl2 (String value){ this.w2abl2 = value;   }   
	public String getW2abl2 (){ return this.w2abl2;   }              
	
	private String w2pros = "0"; 
	public String getW2prosPropertyName (){ return "w2pros"; }
	public void setW2pros (String value){ this.w2pros = value;   }   
	public String getW2pros (){ return this.w2pros;   }              
	
	
	private String w2akd3 = ""; 
	public String getW2akd3PropertyName (){ return "w2akd3"; }
	public void setW2akd3 (String value){ this.w2akd3 = value;   }   
	public String getW2akd3 (){ return this.w2akd3;   }              
	
	private String w2asv3 = ""; 
	public String getW2asv3PropertyName (){ return "w2asv3"; }
	public void setW2asv3 (String value){ this.w2asv3 = value;   }   
	public String getW2asv3 (){ return this.w2asv3;   }              
	
	private String w2asa3 = "0"; 
	public String getW2asa3PropertyName (){ return "w2asa3"; }
	public void setW2asa3 (String value){ this.w2asa3 = value;   }   
	public String getW2asa3 (){ return this.w2asa3;   }              
	
	private String w2agr3 = "0"; 
	public String getW2agr3PropertyName (){ return "w2agr3"; }
	public void setW2agr3 (String value){ this.w2agr3 = value;   }   
	public String getW2agr3 (){ return this.w2agr3;   }              
	
	private String w2abl3 = "0"; 
	public String getW2abl3PropertyName (){ return "w2abl3"; }
	public void setW2abl3 (String value){ this.w2abl3 = value;   }   
	public String getW2abl3 (){ return this.w2abl3;   }              
	
	private String w2val = ""; 
	public String getW2valPropertyName (){ return "w2val"; }
	public void setW2val (String value){ this.w2val = value;   }   
	public String getW2val (){ return this.w2val;   }              
	
	
	private String w2akd4 = ""; 
	public String getW2akd4PropertyName (){ return "w2akd4"; }
	public void setW2akd4 (String value){ this.w2akd4 = value;   }   
	public String getW2akd4 (){ return this.w2akd4;   }              
	
	private String w2asv4 = ""; 
	public String getW2asv4PropertyName (){ return "w2asv4"; }
	public void setW2asv4 (String value){ this.w2asv4 = value;   }   
	public String getW2asv4 (){ return this.w2asv4;   }              
	
	private String w2asa4 = "0"; 
	public String getW2asa4PropertyName (){ return "w2asa4"; }
	public void setW2asa4 (String value){ this.w2asa4 = value;   }   
	public String getW2asa4 (){ return this.w2asa4;   }              
	
	private String w2agr4 = "0"; 
	public String getW2agr4PropertyName (){ return "w2agr4"; }
	public void setW2agr4 (String value){ this.w2agr4 = value;   }   
	public String getW2agr4 (){ return this.w2agr4;   }              
	
	private String w2abl4 = "0"; 
	public String getW2abl4PropertyName (){ return "w2abl4"; }
	public void setW2abl4 (String value){ this.w2abl4 = value;   }   
	public String getW2abl4 (){ return this.w2abl4;   }              
	
	private String w2beln = "0"; 
	public String getW2belnPropertyName (){ return "w2beln"; }
	public void setW2beln (String value){ this.w2beln = value;   }   
	public String getW2beln (){ return this.w2beln;   }              
	
	
	private String w2akd5 = ""; 
	public String getW2akd5PropertyName (){ return "w2akd5"; }
	public void setW2akd5 (String value){ this.w2akd5 = value;   }   
	public String getW2akd5 (){ return this.w2akd5;   }              
	
	private String w2asv5 = ""; 
	public String getW2asv5PropertyName (){ return "w2asv5"; }
	public void setW2asv5 (String value){ this.w2asv5 = value;   }   
	public String getW2asv5 (){ return this.w2asv5;   }              
	
	private String w2asa5 = "0"; 
	public String getW2asa5PropertyName (){ return "w2asa5"; }
	public void setW2asa5 (String value){ this.w2asa5 = value;   }   
	public String getW2asa5 (){ return this.w2asa5;   }              
	
	private String w2agr5 = "0"; 
	public String getW2agr5PropertyName (){ return "w2agr5"; }
	public void setW2agr5 (String value){ this.w2agr5 = value;   }   
	public String getW2agr5 (){ return this.w2agr5;   }              
	
	private String w2abl5 = "0"; 
	public String getW2abl5PropertyName (){ return "w2abl5"; }
	public void setW2abl5 (String value){ this.w2abl5 = value;   }   
	public String getW2abl5 (){ return this.w2abl5;   }              
	
	
	
	private String w2akd6 = ""; 
	public String getW2akd6PropertyName (){ return "w2akd6"; }
	public void setW2akd6 (String value){ this.w2akd6 = value;   }   
	public String getW2akd6 (){ return this.w2akd6;   }              
	
	private String w2asv6 = ""; 
	public String getW2asv6PropertyName (){ return "w2asv6"; }
	public void setW2asv6 (String value){ this.w2asv6 = value;   }   
	public String getW2asv6 (){ return this.w2asv6;   }              
	
	private String w2asa6 = "0"; 
	public String getW2asa6PropertyName (){ return "w2asa6"; }
	public void setW2asa6 (String value){ this.w2asa6 = value;   }   
	public String getW2asa6 (){ return this.w2asa6;   }              
	
	private String w2agr6 = "0"; 
	public String getW2agr6PropertyName (){ return "w2agr6"; }
	public void setW2agr6 (String value){ this.w2agr6 = value;   }   
	public String getW2agr6 (){ return this.w2agr6;   }              
	
	private String w2abl6 = "0"; 
	public String getW2abl6PropertyName (){ return "w2abl6"; }
	public void setW2abl6 (String value){ this.w2abl6 = value;   }   
	public String getW2abl6 (){ return this.w2abl6;   }              
	
	
	private String w2akd7 = ""; 
	public String getW2akd7PropertyName (){ return "w2akd7"; }
	public void setW2akd7 (String value){ this.w2akd7 = value;   }   
	public String getW2akd7 (){ return this.w2akd7;   }              
	
	private String w2asv7 = ""; 
	public String getW2asv7PropertyName (){ return "w2asv7"; }
	public void setW2asv7 (String value){ this.w2asv7 = value;   }   
	public String getW2asv7 (){ return this.w2asv7;   }              
	
	private String w2asa7 = "0"; 
	public String getW2asa7PropertyName (){ return "w2asa7"; }
	public void setW2asa7 (String value){ this.w2asa7 = value;   }   
	public String getW2asa7 (){ return this.w2asa7;   }              
	
	private String w2agr7 = "0"; 
	public String getW2agr7PropertyName (){ return "w2agr7"; }
	public void setW2agr7 (String value){ this.w2agr7 = value;   }   
	public String getW2agr7 (){ return this.w2agr7;   }              
	
	private String w2abl7 = "0"; 
	public String getW2abl7PropertyName (){ return "w2abl7"; }
	public void setW2abl7 (String value){ this.w2abl7 = value;   }   
	public String getW2abl7 (){ return this.w2abl7;   }              
	
	
	private String w2akd8 = ""; 
	public String getW2akd8PropertyName (){ return "w2akd8"; }
	public void setW2akd8 (String value){ this.w2akd8 = value;   }   
	public String getW2akd8 (){ return this.w2akd8;   }              
	
	private String w2asv8 = ""; 
	public String getW2asv8PropertyName (){ return "w2asv8"; }
	public void setW2asv8 (String value){ this.w2asv8 = value;   }   
	public String getW2asv8 (){ return this.w2asv8;   }              
	
	private String w2asa8 = "0"; 
	public String getW2asa8PropertyName (){ return "w2asa8"; }
	public void setW2asa8 (String value){ this.w2asa8 = value;   }   
	public String getW2asa8 (){ return this.w2asa8;   }              
	
	private String w2agr8 = "0"; 
	public String getW2agr8PropertyName (){ return "w2agr8"; }
	public void setW2agr8 (String value){ this.w2agr8 = value;   }   
	public String getW2agr8 (){ return this.w2agr8;   }              
	
	private String w2abl8 = "0"; 
	public String getW2abl8PropertyName (){ return "w2abl8"; }
	public void setW2abl8 (String value){ this.w2abl8 = value;   }   
	public String getW2abl8 (){ return this.w2abl8;   }              
	
	
	private String w2ft01 = ""; 
	public String getW2ft01PropertyName (){ return "w2ft01"; }
	public void setW2ft01 (String value){ this.w2ft01 = value;   }   
	public String getW2ft01 (){ return this.w2ft01;   }              
	
	private String w2nt01 = "0"; 
	public String getW2nt01PropertyName (){ return "w2nt01"; }
	public void setW2nt01 (String value){ this.w2nt01 = value;   }   
	public String getW2nt01 (){ return this.w2nt01;   }              
	
	private String w2eh01 = ""; 
	public String getW2eh01PropertyName (){ return "w2eh01"; }
	public void setW2eh01 (String value){ this.w2eh01 = value;   }   
	public String getW2eh01 (){ return this.w2eh01;   }              
	
	private String w2vt01 = ""; 
	public String getW2vt01PropertyName (){ return "w2vt01"; }
	public void setW2vt01 (String value){ this.w2vt01 = value;   }   
	public String getW2vt01 (){ return this.w2vt01;   }              
	
	
	private String w2ft02 = ""; 
	public String getW2ft02PropertyName (){ return "w2ft02"; }
	public void setW2ft02 (String value){ this.w2ft02 = value;   }   
	public String getW2ft02 (){ return this.w2ft02;   }              
	
	private String w2nt02 = "0"; 
	public String getW2nt02PropertyName (){ return "w2nt02"; }
	public void setW2nt02 (String value){ this.w2nt02 = value;   }   
	public String getW2nt02 (){ return this.w2nt02;   }              
	
	private String w2eh02 = ""; 
	public String getW2eh02PropertyName (){ return "w2eh02"; }
	public void setW2eh02 (String value){ this.w2eh02 = value;   }   
	public String getW2eh02 (){ return this.w2eh02;   }              
	
	private String w2vt02 = ""; 
	public String getW2vt02PropertyName (){ return "w2vt02"; }
	public void setW2vt02 (String value){ this.w2vt02 = value;   }   
	public String getW2vt02 (){ return this.w2vt02;   }              
	
	
	private String w2ft03 = ""; 
	public String getW2ft03PropertyName (){ return "w2ft03"; }
	public void setW2ft03 (String value){ this.w2ft03 = value;   }   
	public String getW2ft03 (){ return this.w2ft03;   }              
	
	private String w2nt03 = "0"; 
	public String getW2nt03PropertyName (){ return "w2nt03"; }
	public void setW2nt03 (String value){ this.w2nt03 = value;   }   
	public String getW2nt03 (){ return this.w2nt03;   }              
	
	private String w2eh03 = ""; 
	public String getW2eh03PropertyName (){ return "w2eh03"; }
	public void setW2eh03 (String value){ this.w2eh03 = value;   }   
	public String getW2eh03 (){ return this.w2eh03;   }              
	
	private String w2vt03 = ""; 
	public String getW2vt03PropertyName (){ return "w2vt03"; }
	public void setW2vt03 (String value){ this.w2vt03 = value;   }   
	public String getW2vt03 (){ return this.w2vt03;   }              
	
	
	private String w2ft04 = ""; 
	public String getW2ft04PropertyName (){ return "w2ft04"; }
	public void setW2ft04 (String value){ this.w2ft04 = value;   }   
	public String getW2ft04 (){ return this.w2ft04;   }              
	
	private String w2nt04 = "0"; 
	public String getW2nt04PropertyName (){ return "w2nt04"; }
	public void setW2nt04 (String value){ this.w2nt04 = value;   }   
	public String getW2nt04 (){ return this.w2nt04;   }              
	
	private String w2eh04 = ""; 
	public String getW2eh04PropertyName (){ return "w2eh04"; }
	public void setW2eh04 (String value){ this.w2eh04 = value;   }   
	public String getW2eh04 (){ return this.w2eh04;   }              
	
	private String w2vt04 = ""; 
	public String getW2vt04PropertyName (){ return "w2vt04"; }
	public void setW2vt04 (String value){ this.w2vt04 = value;   }   
	public String getW2vt04 (){ return this.w2vt04;   }              
	
	
	private String w2ft05 = ""; 
	public String getW2ft05PropertyName (){ return "w2ft05"; }
	public void setW2ft05 (String value){ this.w2ft05 = value;   }   
	public String getW2ft05 (){ return this.w2ft05;   }              
	
	private String w2nt05 = "0"; 
	public String getW2nt05PropertyName (){ return "w2nt05"; }
	public void setW2nt05 (String value){ this.w2nt05 = value;   }   
	public String getW2nt05 (){ return this.w2nt05;   }              
	
	private String w2eh05 = ""; 
	public String getW2eh05PropertyName (){ return "w2eh05"; }
	public void setW2eh05 (String value){ this.w2eh05 = value;   }   
	public String getW2eh05 (){ return this.w2eh05;   }              
	
	private String w2vt05 = ""; 
	public String getW2vt05PropertyName (){ return "w2vt05"; }
	public void setW2vt05 (String value){ this.w2vt05 = value;   }   
	public String getW2vt05 (){ return this.w2vt05;   }              
	
	
	private String w2ft06 = ""; 
	public String getW2ft06PropertyName (){ return "w2ft06"; }
	public void setW2ft06 (String value){ this.w2ft06 = value;   }   
	public String getW2ft06 (){ return this.w2ft06;   }              
	
	private String w2nt06 = "0"; 
	public String getW2nt06PropertyName (){ return "w2nt06"; }
	public void setW2nt06 (String value){ this.w2nt06 = value;   }   
	public String getW2nt06 (){ return this.w2nt06;   }              
	
	private String w2eh06 = ""; 
	public String getW2eh06PropertyName (){ return "w2eh06"; }
	public void setW2eh06 (String value){ this.w2eh06 = value;   }   
	public String getW2eh06 (){ return this.w2eh06;   }              
	
	private String w2vt06 = ""; 
	public String getW2vt06PropertyName (){ return "w2vt06"; }
	public void setW2vt06 (String value){ this.w2vt06 = value;   }   
	public String getW2vt06 (){ return this.w2vt06;   }              
	
	
	private String w2ft07 = ""; 
	public String getW2ft07PropertyName (){ return "w2ft07"; }
	public void setW2ft07 (String value){ this.w2ft07 = value;   }   
	public String getW2ft07 (){ return this.w2ft07;   }              
	
	private String w2nt07 = "0"; 
	public String getW2nt07PropertyName (){ return "w2nt07"; }
	public void setW2nt07 (String value){ this.w2nt07 = value;   }   
	public String getW2nt07 (){ return this.w2nt07;   }              
	
	private String w2eh07 = ""; 
	public String getW2eh07PropertyName (){ return "w2eh07"; }
	public void setW2eh07 (String value){ this.w2eh07 = value;   }   
	public String getW2eh07 (){ return this.w2eh07;   }              
	
	private String w2vt07 = ""; 
	public String getW2vt07PropertyName (){ return "w2vt07"; }
	public void setW2vt07 (String value){ this.w2vt07 = value;   }   
	public String getW2vt07 (){ return this.w2vt07;   }              
	
	
	private String w2top1 = ""; 
	public String getW2top1PropertyName (){ return "w2top1"; }
	public void setW2top1 (String value){ this.w2top1 = value;   }   
	public String getW2top1 (){ return this.w2top1;   }              
	
	private String w2cre1 = ""; 
	public String getW2cre1PropertyName (){ return "w2cre1"; }
	public void setW2cre1 (String value){ this.w2cre1 = value;   }   
	public String getW2cre1 (){ return this.w2cre1;   }              
	
	private String w2top2 = ""; 
	public String getW2top2PropertyName (){ return "w2top2"; }
	public void setW2top2 (String value){ this.w2top2 = value;   }   
	public String getW2top2 (){ return this.w2top2;   }              
	
	private String w2cre2 = ""; 
	public String getW2cre2PropertyName (){ return "w2cre2"; }
	public void setW2cre2 (String value){ this.w2cre2 = value;   }   
	public String getW2cre2 (){ return this.w2cre2;   }              
	
	private String w2top3 = ""; 
	public String getW2top3PropertyName (){ return "w2top3"; }
	public void setW2top3 (String value){ this.w2top3 = value;   }   
	public String getW2top3 (){ return this.w2top3;   }              
	
	private String w2cre3 = ""; 
	public String getW2cre3PropertyName (){ return "w2cre3"; }
	public void setW2cre3 (String value){ this.w2cre3 = value;   }   
	public String getW2cre3 (){ return this.w2cre3;   }              
	
	private String w2top4 = ""; 
	public String getW2top4PropertyName (){ return "w2top4"; }
	public void setW2top4 (String value){ this.w2top4 = value;   }   
	public String getW2top4 (){ return this.w2top4;   }              
	
	private String w2cre4 = ""; 
	public String getW2cre4PropertyName (){ return "w2cre4"; }
	public void setW2cre4 (String value){ this.w2cre4 = value;   }   
	public String getW2cre4 (){ return this.w2cre4;   }              
	
	private String w2top5 = ""; 
	public String getW2top5PropertyName (){ return "w2top5"; }
	public void setW2top5 (String value){ this.w2top5 = value;   }   
	public String getW2top5 (){ return this.w2top5;   }              
	
	private String w2cre5 = ""; 
	public String getW2cre5PropertyName (){ return "w2cre5"; }
	public void setW2cre5 (String value){ this.w2cre5 = value;   }   
	public String getW2cre5 (){ return this.w2cre5;   }              
	
	private String w2top6 = ""; 
	public String getW2top6PropertyName (){ return "w2top6"; }
	public void setW2top6 (String value){ this.w2top6 = value;   }   
	public String getW2top6 (){ return this.w2top6;   }              
	
	private String w2cre6 = ""; 
	public String getW2cre6PropertyName (){ return "w2cre6"; }
	public void setW2cre6 (String value){ this.w2cre6 = value;   }   
	public String getW2cre6 (){ return this.w2cre6;   }              
	
	
	private String w2top7 = ""; 
	public String getW2top7PropertyName (){ return "w2top7"; }
	public void setW2top7 (String value){ this.w2top7 = value;   }   
	public String getW2top7 (){ return this.w2top7;   }              
	
	private String w2cre7 = ""; 
	public String getW2cre7PropertyName (){ return "w2cre7"; }
	public void setW2cre7 (String value){ this.w2cre7 = value;   }   
	public String getW2cre7 (){ return this.w2cre7;   }              
	
	
	private String w2top8 = ""; 
	public String getW2top8PropertyName (){ return "w2top8"; }
	public void setW2top8 (String value){ this.w2top8 = value;   }   
	public String getW2top8 (){ return this.w2top8;   }              
	
	private String w2cre8 = ""; 
	public String getW2cre8PropertyName (){ return "w2cre8"; }
	public void setW2cre8 (String value){ this.w2cre8 = value;   }   
	public String getW2cre8 (){ return this.w2cre8;   }              
	
	
	private String w2top9 = ""; 
	public String getW2top9PropertyName (){ return "w2top9"; }
	public void setW2top9 (String value){ this.w2top9 = value;   }   
	public String getW2top9 (){ return this.w2top9;   }              
	
	private String w2cre9 = ""; 
	public String getW2cre9PropertyName (){ return "w2cre9"; }
	public void setW2cre9 (String value){ this.w2cre9 = value;   }   
	public String getW2cre9 (){ return this.w2cre9;   }              
	
	private String w2top10 = ""; 
	public String getW2top10PropertyName (){ return "w2top10"; }
	public void setW2top10 (String value){ this.w2top10 = value;   }   
	public String getW2top10 (){ return this.w2top10;   }              
	
	private String w2cre10 = ""; 
	public String getW2cre10PropertyName (){ return "w2cre10"; }
	public void setW2cre10 (String value){ this.w2cre10 = value;   }   
	public String getW2cre10 (){ return this.w2cre10;   }              
	
}
