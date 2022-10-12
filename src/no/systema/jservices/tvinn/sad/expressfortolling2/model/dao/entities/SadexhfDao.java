package no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadexhfDao implements IDao {

	private String ehst   = ""; // tegn            1       1         1        begge    status    
	private String ehst2  = ""; // tegn            1       1         2        begge    status 2  
	private String ehst3  = ""; // tegn            1       1         3        begge    status 3  
	private String ehuuid = ""; // tegn           36      36         4        begge    lrn       
	private String ehmid  = ""; // tegn           18      18        40        begge    mrn       
	private Integer ehpro  = 0; // sonet        8  0       8        58        begge    turnummer 
	private Integer ehavd  = 0; // sonet        4  0       4        66        begge    avdeling  
	private Integer ehtdn  = 0; // sonet        7  0       7        70        begge    oppdragsnr      
	private Integer ehknd  = 0; // sonet        8  0       8        77        begge    deklarant       
	private String ehrgd  = ""; // tegn           17      17        85        begge    org.nr deklarant
	private String ehnad  = ""; // tegn           30      30       102        begge    navn deklarant      
	private String ehna2d = ""; // tegn           30      30       132        begge    subdivvision dekla. 
	private String ehad1d = ""; // tegn           30      30       162        begge    gateadr. dekla.     
	private String ehnrd  = ""; // tegn           15      15       192        begge    husnr dekla.        
	private String ehpnd  = ""; // tegn            9       9       207        begge    postnr deklarant    
	private String ehpsd  = ""; // tegn           24      24       216        begge    p.sted deklarant    
	private String ehlkd  = ""; // tegn            2       2       240        begge    l.kode deklarant    
	private String ehpbd  = ""; // tegn           15      15       242        begge    postbox dekla.      
	private String ehemd  = ""; // tegn           50      50       257        begge    epostadr dekla.     
	private String ehemdt = ""; // tegn            2       2       307        begge    kodetype decla.     
	private Integer ehknr  = 0; // sonet        8  0       8       309        begge    representative      
	private String ehrgr  = ""; // tegn           17      17       317        begge    org.nr representativ
	private String ehstr  = ""; // tegn            1       1       334        begge    status representativ
	private String ehnar  = ""; // tegn           30      30       335        begge    navn representative 
	private String ehna2r = ""; // tegn           30      30       365        begge    subdivvision repre. 
	private String ehad1r = ""; // tegn           30      30       395        begge    gateadr. repre.     
	private String ehnrr  = ""; // tegn           15      15       425        begge    husnr repre.        
	private String ehpnr  = ""; // tegn            9       9       440        begge    postnr representativ
	private String ehpsr  = ""; // tegn           24      24       449        begge    p.sted representativ
	private String ehlkr  = ""; // tegn            2       2       473        begge    l.kode representativ
	private String ehpbr  = ""; // tegn           15      15       475        begge    postbox repre.     
	private String ehemr  = ""; // tegn           50      50       490        begge    epostadr/tlf repre.
	private String ehemrt = ""; // tegn            2       2       540        begge    kodetype repre.    
	private Integer ehcnin = 0; // sonet        2  0       2       542        begge    container indikator
	private String ehvkb = ""; // sonet       13  3      13       544        begge    total vekt         
	private String ehucr  = ""; // tegn           50      50       557        begge    ref.nr ucr         
	private Integer ehntk  = 0; // sonet        7  0       7       607        begge    total kolli        
	private String ehvt   = ""; // tegn           30      30       614        begge    varebeskrivelse
	private String ehetyp = ""; // tegn            2       2       644        begge    type           
	private String ehetypt= ""; // tegn           30      30       646        begge    type tekst     
	private String eheid  = ""; // tegn           18      18       676        begge    eksport id     
	private String ehpr   = ""; // tegn            2       2       694        begge    prosedyre      
	private String ehprt  = ""; // tegn           30      30       696        begge    prosedyre tekst
	private String ehupr   = ""; // tegn            2       2       694        begge    prosedyre outgoing     
	private String ehuprt  = ""; // tegn           30      30       696        begge    prosedyre tekst outgoing
	private String ehrg   = ""; // tegn           11      11       726        begge    org.nr             
	private Integer eh0068a= 0; // sonet        8  0       8       737        begge    sendingsdato       
	private Integer eh0068b= 0; // sonet        6  0       6       745        begge    sendingssekv       
	private String ehtrnr = ""; // tegn           18      18       751        begge    mrn-nr             
	private String ehtrty = ""; // tegn            4       4       769        begge    ref.type cude      
	private String ehrga  = ""; // tegn           17      17       773        begge    mva.nr             
	private String ehrgro = ""; // tegn            3       3       790        begge    mva-aktørens rolle 
	private String ehlkf  = ""; // tegn            2       2       793        begge    land of loading   
	private String ehsdf  = ""; // tegn            5       5       795        begge    place of loading  
	private String ehsdft = ""; // tegn           30      30       800        begge    place of loading  
	private String ehlkt  = ""; // tegn            2       2       830        begge    land of unloading 
	private String ehsdt  = ""; // tegn            5       5       832        begge    place of unloading
	private String ehsdtt = ""; // tegn           30      30       837        begge    place of unloading
	private String ehrge  = ""; // tegn           17      17       867        begge    autorisert økonomi
	private String ehroe  = ""; // tegn            2       2       884        begge    rollen operatør       
	private Integer ehknm  = 0; // sonet        8  0       8       886        begge    mottaker              
	private String ehrgm  = ""; // tegn           17      17       894        begge    org.nr mottaker       
	private String ehnam  = ""; // tegn           30      30       911        begge    navn mottaker         
	private String ehna2m = ""; // tegn           30      30       941        begge    subdivvision mottak.  
	private String ehad1m = ""; // tegn           30      30       971        begge    gateadr. mottaker     
	private String ehnrm  = ""; // tegn           15      15      1001        begge    husnr mottaker     
	private String ehpnm  = ""; // tegn            9       9      1016        begge    postnr mottaker    
	private String ehpsm  = ""; // tegn           24      24      1025        begge    p.sted mottaker    
	private String ehlkm  = ""; // tegn            2       2      1049        begge    l.kode mottaker    
	private String ehpbm  = ""; // tegn           15      15      1051        begge    postbox mottaker   
	private String ehemm  = ""; // tegn           50      50      1066        begge    ep.adr/tlf mottaker
	private String ehemmt = ""; // tegn            2       2      1116        begge    kodetype mottaker  
	private Integer ehkns  = 0; // sonet        8  0       8      1118        begge    avsender            
	private String ehrgs  = ""; // tegn           17      17      1126        begge    org.nr avsender     
	private String ehnas  = ""; // tegn           30      30      1143        begge    navn avsender       
	private String ehna2s = ""; // tegn           30      30      1173        begge    subdivvision avsend.
	private String ehad1s = ""; // tegn           30      30      1203        begge    gateadr. avsender   
	private String ehnrs  = ""; // tegn           15      15      1233        begge    husnr avsender      
	private String ehpns  = ""; // tegn            9       9      1248        begge    postnr avsender  
	private String ehpss  = ""; // tegn           24      24      1257        begge    p.sted avsender  
	private String ehlks  = ""; // tegn            2       2      1281        begge    l.kode avsender  
	private String ehpbs  = ""; // tegn           15      15      1283        begge    postbox avsender 
	private String ehems  = ""; // tegn           50      50      1298        begge    epostadr avsender
	private String ehemst = ""; // tegn            2       2      1348        begge    kodetype mottaker
	private String ehdkh  = ""; // tegn           50      50      1350        begge    house dokumentnr 
	private String ehdkht = ""; // tegn            4       4      1400        begge    house dokumenttype  
	private String ehtcmp = ""; // tegn            1       1      1404        begge    betalingsmåte       
	private String ehtcva = ""; // tegn            3       3      1405        begge    valutakode          
	private String ehtcbl = ""; //sonet       11  2      11      1408        begge    beløp               
	private String ehlkr1 = ""; // tegn            2       2      1419        begge    landkode1 reiserute 
	private String ehlkr2 = ""; // tegn            2       2      1421        begge    landkode2 reiserute 
	private String ehlkr3 = ""; // tegn            2       2      1423        begge    landkode3 reiserute 
	private String ehlkr4 = ""; // tegn            2       2      1425        begge    landkode4 reiserute 
	private String ehlkr5 = ""; // tegn            2       2      1427        begge    landkode5 reiserute 
	private String ehlkr6 = ""; // tegn            2       2      1429        begge    landkode6 reiserute 
	private String ehlkr7 = ""; // tegn            2       2      1431        begge    landkode7 reiserute 
	private String ehlkr8 = ""; // tegn            2       2      1433        begge    landkode8 reiserute 
	private String ehcnr  = ""; // tegn           11      11      1435        begge    containernr         
	private String ehpmrk = ""; // tegn           30      30      1446        begge    kjennemerke         
	private String ehptyp = ""; // tegn            2       2      1476        begge    kjøretøy type       
	private String ehptm  = ""; // tegn            4       4      1478        begge    transportmåte       
	private String ehptmt = ""; // tegn           50      50      1482        begge    transportmåte tekst 
	private String ehplk  = ""; // tegn            2       2      1532        begge    kjøretøynasjonalitet
	private String ehtrid = ""; // tegn           17      17      1534        begge    bilnr               
	private Integer eh3039e= 0; // sonet        6  0       6      1551        begge    ekspedisjonsenhet

	
}
