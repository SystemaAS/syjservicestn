package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadmomfDao implements Serializable, IDao {
	
	private String emst  = "";  //tegn            1    status    
	private String emuuid ="";  //tegn           36    lrn  
	private String emuuid_own ="";  //tegn           36    lrn  
	private String emmid = "";  //tegn           18    mrn     
	private String emmid_own = "";  //tegn           18    mrn back up
	private Integer emavd = 0;  //sonet        4  0    avdeling     
	private Integer empro= 0;  //sonet        8  0     turnummer    
	private Integer emlnrt= 0;  //sonet        7  0    løpenummer (KEY i transport)            
	private Integer emlnrm= 0;  //sonet        4  0    m-lnr innen transp   (KEY)  
	private Integer emdtr= 0;  //sonet        8  0     registreringsdato      
	private String emsg = "";    //tegn            3   signatur               
	private String emst2 = "";   //tegn            1   status om manifest 
	private String emst3 = "";   //tegn            1   status om innpass. 
	private Integer emdtin= 0;  //sonet        8  0    innsendingsdato      
	private Integer ematdd= 0;  //sonet        8  0    date ymd              
	private String emrcem1 = "";  //tegn           50  releasedconf. epost.  
	private String emrcem2 = "";  //tegn           50  releasedconf. epost.  
	private String emrcem3 = "";  //tegn           50  releasedconf. epost.  
	private Integer emcn= 0;  //sonet        1  0      container 1/0        
	private Integer emvkb= 0;  //sonet        9  0     bruttovekt           
	private Integer emknt= 0;  //sonet        8  0     transportør          
	private String emrgt = "";    //tegn           17  org.nr transportør   
	private Integer emknm = 0;    //sonet        8  0  mottaker             
	private String emrgm = "";    //tegn           17  org.nr mottaker  
	private Integer emtppm = 0;   //sonet           1   Type of person
	private String emnam = "";    //tegn           30  navn mottaker        
	private String emna2m = "";   //tegn           30  subdivvision mottak. 
	private String emad1m = "";   //tegn           30  gateadr. mottaker
	private String emnrm = "";    //tegn           15  husnr mottaker   
	private String empnm = "";    //tegn            9  postnr mottaker  
	private String empsm = "";    //tegn           24  p.sted mottaker  
	private String emlkm = "";    //tegn            2  l.kode mottaker  
	private String empbm = "";    //tegn           15  postbox mottaker 
	private String ememm = "";    //tegn           50  ep.adr/tlf mottaker  
	private String ememmt = "";   //tegn            2  kodetype mottaker    
	private Integer emkns = 0;   //sonet        8  0   avsender             
	private String emrgs = "";   //tegn           17   org.nr avsender
	private Integer emtpps = 0;   //sonet           1   Type of person
	private String emnas = "";   //tegn           30   navn avsender        
	private String emna2s = "";   //tegn           30  subdivvision avsend. 
	private String emad1s = "";   //tegn           30  gateadr. avsender    
	private String emnrs = "";   //tegn           15   husnr avsender       
	private String empns = "";   //tegn            9   postnr avsender      
	private String empss = "";   //tegn           24   p.sted avsender      
	private String emlks = "";   //tegn            2   l.kode avsender      
	private String empbs = "";   //tegn           15   postbox avsender     
	private String emems = "";   //tegn           50   epost/tlf avsender   
	private String ememst = "";   //tegn            2  kodetype avsender  
	private String emdkm = "";   //tegn           50   master dokumentnr  
	private String emdkmt = "";   //tegn            4  master dokumenttype
	private String emc1ty = "";   //tegn            2  cont1.sizetype     
	private String emc1ps = "";   //tegn            1  cont1.packsts      
	private String emc1ss = "";   //tegn            1  cont1.supptype     
	private String emc1id = "";   //tegn           17  cont1.idnumber 
	private String emc2ty = "";   //tegn            2  cont2.sizetype 
	private String emc2ps = "";   //tegn            1  cont2.packsts  
	private String emc2ss = "";   //tegn            1  cont2.supptype 
	private String emc2id = "";   //tegn           17  cont2.idnumber 
	private String emc3ty = "";   //tegn            2  cont3.sizetype 
	private String emc3ps = "";   //tegn            1  cont3.packsts  
	private String emc3ss = "";   //tegn            1  cont3.supptype     
	private String emc3id = "";   //tegn           17  cont3.idnumber     
	private String emlkl = "";   //tegn            2   land of loading    
	private String emsdl = "";   //tegn            5   place of load code 
	private String emsdlt = "";   //tegn           30  place of load text 
	private String emlku = "";   //tegn            2   land of unloading  
	private String emsdu = "";   //tegn            5   place of unloa code  
	private String emsdut = "";   //tegn           30  place of unloa text  
	private String emlkd = "";   //tegn            2   land of delivery     
	private String emsdd = "";   //tegn            5   place of deliv code  
	private String emsddt = "";   //tegn           30  place of deliv text  
	private String emerr = "";  //tegn           50    feilmelding ved snd  
	//for främmande master
	private String emdkm_ff = "";   //tegn           50   master dokumentnr  
	private String emdkmt_ff = "";   //tegn           4  master dokumenttype
	private String emrgt_ff = "";    //tegn           17  org.nr transportør
	private String emrgr_ff = "";    //tegn           17  org.nr ombud
	
}
	      