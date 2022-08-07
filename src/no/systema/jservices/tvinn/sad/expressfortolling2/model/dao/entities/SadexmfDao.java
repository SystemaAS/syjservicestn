package no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadexmfDao implements IDao {

	private String emst = ""; //       tegn            1       1         1        begge    status    
	private String emuuid = ""; //     tegn           36      36         2        begge    lrn       
	private String emmid = ""; //      tegn           18      18        38        begge    mrn       
	private Integer emavd  = 0; // sonet        4  0       4        56        begge    avdeling  
	private Integer empro  = 0; // sonet        8  0       8        60        begge    turnummer 
	private Integer emdtr  = 0; // sonet        8  0       8        68        begge    registreringsdato
	private String emsg = ""; //       tegn            3       3        76        begge    signatur         
	private String emst2 = ""; //      tegn            1       1        79        begge    status om manifest 
	private String emtsd = ""; //     tegn            8       8        80        begge    passeringstollsted 
	private String emst3 = ""; //     tegn            1       1        88        begge    status om innpass. 
	private Integer emdtin = 0; // sonet        8  0       8        89        begge    innsendingsdato 
	private Integer ematdd = 0; // sonet        8  0       8        97        begge    akt. avgang atd
	private Integer ematdt = 0; // sonet        6  0       6       105        begge    atd tid        
	private Integer emetdd = 0; // sonet        8  0       8       111        begge    estimert avgang etd  
	private Integer emetdt = 0; // sonet        6  0       6       119        begge    etd tid              
	private Integer emetad = 0; // sonet        8  0       8       125        begge    estimert ank. eta
	private Integer emetat = 0; // sonet        6  0       6       133        begge    eta tid          
	private Integer ematad = 0; // sonet        8  0       8       139        begge    akt. ankomst ata
	private Integer ematat = 0; // sonet        6  0       6       147        begge    ata tid         
	private Integer emknd  = 0; // sonet        8  0       8       153        begge    deklarant          
	private String emrgd = ""; //     tegn           17      17       161        begge    org.nr deklarant   
	private String emnad = ""; //     tegn           30      30       178        begge    navn deklarant     
	private String emna2d = ""; //    tegn           30      30       208        begge    subdivvision dekla.
	private String emad1d = ""; //    tegn           30      30       238        begge    gateadr. dekla.    
	private String emnrd = ""; //     tegn           15      15       268        begge    husnr dekla.       
	private String empnd = ""; //     tegn            9       9       283        begge    postnr deklarant 
	private String empsd = ""; //     tegn           24      24       292        begge    p.sted deklarant 
	private String emlkd = ""; //     tegn            2       2       316        begge    l.kode deklarant 
	private String empbd = ""; //     tegn           15      15       318        begge    postbox dekla.   
	private String ememd = ""; //     tegn           50      50       333        begge    epostadr dekla.  
	private String ememdt = ""; //    tegn            2       2       383        begge    kodetype decla.  
	private Integer emknr  = 0; // sonet        8  0       8       385        begge    representative        
	private String emrgr = ""; //     tegn           17      17       393        begge    org.nr representativ  
	private String emstr = ""; //      tegn            1       1       410        begge    status representativ  
	private String emnar = ""; //     tegn           30      30       411        begge    navn representative   
	private String emna2r = ""; //    tegn           30      30       441        begge    subdivvision repre.   
	private String emad1r = ""; //    tegn           30      30       471        begge    gateadr. repre.       
	private String emnrr = ""; //     tegn           15      15       501        begge    husnr repre.          
	private String empnr = ""; //     tegn            9       9       516        begge    postnr representativ 
	private String empsr = ""; //     tegn           24      24       525        begge    p.sted representativ 
	private String emlkr = ""; //     tegn            2       2       549        begge    l.kode representativ 
	private String empbr = ""; //     tegn           15      15       551        begge    postbox repre.       
	private String ememr = ""; //     tegn           50      50       566        begge    epostadr/tlf repre.  
	private String ememrt = ""; //    tegn            2       2       616        begge    kodetype repre.      
	private String emkmrk = ""; //    tegn           30      30       618        begge    kjennemerke         
	private String emktyp = ""; //    tegn            2       2       648        begge    kjøretøy type       
	private String emktypt = ""; //   tegn           50      50       650        begge    kjøretøy type tekst 
	private String emktm = ""; //     tegn            4       4       700        begge    transportmåte       
	private String emktmt = ""; //    tegn           50      50       704        begge    transportmåte tekst 
	private String emklk = ""; //     tegn            2       2       754        begge    kjøretøynasjonalitet
	private String emktkd = ""; //    tegn            1       1       756        begge    mode av transportkd 
	private String emlkr1 = ""; //    tegn            2       2       757        begge    landkode1 reiserute 
	private String emlkr2 = ""; //    tegn            2       2       759        begge    landkode2 reiserute 
	private String emlkr3 = ""; //    tegn            2       2       761        begge    landkode3 reiserute 
	private String emlkr4 = ""; //    tegn            2       2       763        begge    landkode4 reiserute 
	private String emlkr5 = ""; //    tegn            2       2       765        begge    landkode5 reiserute 
	private String emlkr6 = ""; //    tegn            2       2       767        begge    landkode6 reiserute 
	private String emlkr7 = ""; //    tegn            2       2       769        begge    landkode7 reiserute  
	private String emlkr8 = ""; //    tegn            2       2       771        begge    landkode8 reiserute  
	private String empmrk = ""; //    tegn           30      30       773        begge    kjennemerke          
	private String emptyp = ""; //    tegn            2       2       803        begge    kjøretøy type        
	private String emptm = ""; //     tegn            4       4       805        begge    transportmåte        
	private String emptmt = ""; //    tegn           50      50       809        begge    transportmåte tekst  
	private String emplk = ""; //     tegn            2       2       859        begge    kjøretøynasjonalitet  
	private String emsjaf = ""; //    tegn           50      50       861        begge    sjåfør navn           
	private String emsjalk = ""; //   tegn            2       2       911        begge    sjåfør nasjonalitet   
	private Integer emsjadt= 0; // sonet        8  0       8       913        begge    sjåfør fødselsdato    
	private String emsj2f = ""; //    tegn           50      50       921        begge    sjåfør-2 navn         
	private String emsj2lk = ""; //   tegn            2       2       971        begge    sjåfør-2 nasjonalit.  
	private Integer emsj2dt= 0; // sonet        8  0       8       973        begge    sjåfør-2 fødselsdato  
	private String emdkh = ""; //     tegn           50      50       981        begge    house dokumentnr   
	private String emdkht = ""; //    tegn            4       4      1031        begge    house dokumenttype 
	private Integer emcn   = 0; // sonet        1  0       1      1035        begge    container 1/0      
	private String emcnr = ""; //     tegn           11      11      1036        begge    containernr        
	private Integer emvkb  = 0; // sonet        9  0       9      1047        begge    bruttovekt         
	private Integer emknt  = 0; // sonet        8  0       8      1056        begge    transportør        
	private String emrgt = ""; //     tegn           17      17      1064        begge    org.nr transportør 
	private String emnat = ""; //     tegn           30      30      1081        begge    navn transportør   
	private String emna2t = ""; // tegn           30      30      1111        begge    subdivvision trans.
	private String emad1t = ""; // tegn           30      30      1141        begge    gateadr. trans.    
	private String emnrt  = ""; // tegn           15      15      1171        begge    husnr trans.       
	private String empnt  = ""; // tegn            9       9      1186        begge    postnr transportør 
	private String empst  = ""; // tegn           24      24      1195        begge    p.sted transortør  
	private String emlkt  = ""; // tegn            2       2      1219        begge    l.kode transportør 
	private String empbt  = ""; // tegn           15      15      1221        begge    postbox trans.     
	private String ememt  = ""; // tegn           50      50      1236        begge    epostadr/tlf trans.
	private String ememtt = ""; // tegn            2       2      1286        begge    kodetype trans.    
	private String emdkm  = ""; // tegn           50      50      1288        begge    master dokumentnr  
	private String emdkmt = ""; // tegn            4       4      1338        begge    master dokumenttype
	private String emucr  = ""; // tegn           50      50      1342        begge    ref.nr ucr         
	private Integer emknm  = 0; // sonet        8  0       8      1392        begge    mottaker            
	private String emrgm  = ""; // tegn           17      17      1400        begge    org.nr mottaker     
	private String emnam  = ""; // tegn           30      30      1417        begge    navn mottaker       
	private String emna2m = ""; // tegn           30      30      1447        begge    subdivvision mottak.
	private String emad1m = ""; // tegn           30      30      1477        begge    gateadr. mottaker   
	private String emnrm  = ""; // tegn           15      15      1507        begge    husnr mottaker      
	private String empnm  = ""; // tegn            9       9      1522        begge    postnr mottaker     
	private String empsm  = ""; // tegn           24      24      1531        begge    p.sted mottaker     
	private String emlkm  = ""; // tegn            2       2      1555        begge    l.kode mottaker     
	private String empbm  = ""; // tegn           15      15      1557        begge    postbox mottaker    
	private String ememm  = ""; // tegn           50      50      1572        begge    ep.adr/tlf mottaker 
	private String ememmt = ""; // tegn            2       2      1622        begge    kodetype mottaker   
	private Integer emkns  = 0; // sonet        8  0       8      1624        begge    avsender            
	private String emrgs  = ""; // tegn           17      17      1632        begge    org.nr avsender     
	private String emnas  = ""; // tegn           30      30      1649        begge    navn avsender       
	private String emna2s = ""; // tegn           30      30      1679        begge    subdivvision avsend.
	private String emad1s = ""; // tegn           30      30      1709        begge    gateadr. avsender   
	private String emnrs  = ""; // tegn           15      15      1739        begge    husnr avsender      
	private String empns  = ""; // tegn            9       9      1754        begge    postnr avsender     
	private String empss  = ""; // tegn           24      24      1763        begge    p.sted avsender     
	private String emlks  = ""; // tegn            2       2      1787        begge    l.kode avsender  
	private String empbs  = ""; // tegn           15      15      1789        begge    postbox avsender 
	private String emems  = ""; // tegn           50      50      1804        begge    epostadr avsender
	private String ememst = ""; // tegn            2       2      1854        begge    kodetype mottaker
	private String emlkl  = ""; // tegn            2       2      1856        begge    land of loading  
	private String emsdl  = ""; // tegn            5       5      1858        begge    place of loading 
	private String emsdlt = ""; // tegn           30      30      1863        begge    place of loading    
	private String emlku  = ""; // tegn            2       2      1893        begge    land of unloading   
	private String emsdu  = ""; // tegn            5       5      1895        begge    place of unloading  
	private String emsdut = ""; // tegn           30      30      1900        begge    place of unloading  
	private String emsca  = ""; // tegn           17      17      1930        begge    org.nr supplychainac
	private String emscar = ""; // tegn            2       2      1947        begge    role supplychainacto
	private String emrcem = ""; // tegn           50      50      1949        begge    releasedconf. epost.
	private String emerr  = ""; // tegn           50      50      1999        begge    feilmelding ved snd 
	
	
	
	
}
