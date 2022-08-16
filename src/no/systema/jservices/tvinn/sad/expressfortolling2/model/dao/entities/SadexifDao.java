package no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadexifDao implements IDao {

	private String eist   = ""; // tegn            1       1         1        begge    status    
	private Integer eipro  = 0; // sonet        8  0       8         2        begge    turnummer 
	private Integer eiavd  = 0; // sonet        4  0       4        10        begge    avdeling  
	private Integer eitdn  = 0; // sonet        7  0       7        14        begge    oppdragsnr
	private Integer eili   = 0; // sonet        5  0       5        21        begge    linjenr      
	private Integer eilid  = 0; // sonet        5  0       5        26        begge    dekl. linjenr
	private Integer eilit  = 0; // sonet        5  0       5        31        begge    ncts linjenr
	private String eigty  = ""; // tegn            2       2        36        begge    godstype    
	private String eibl   = ""; // sonet       13  2      13        38        begge    linje beløp       
	private String eival  = ""; // tegn            3       3        51        begge    valutakode        
	private String eirge  = ""; // tegn           17      17        54        begge    autorisert økonomi
	private String eiroe  = ""; // tegn            2       2        71        begge    rollen operatør   
	private String eiucr  = ""; // tegn           50      50        73        begge    ref.nr ucr        
	private String eivt   = ""; // tegn           70      70       123        begge    godsbeskrivelse   
	private String eickd  = ""; // tegn            9       9       193        begge    cuscode           
	private Integer eivnt  = 0; // sonet        8  0       8       202        begge    tariffnr          
	private String eiunnr = ""; // tegn            4       4       210        begge    un-nummer         
	private String eicvkn = ""; // sonet       11  3      11       214        begge    nettovekt         
	private String eicvkb = ""; // sonet       11  3      11       225        begge    bruttovekt        
	private String eiunit = ""; // tegn           35      35       236        begge    supplementaryunits
	private String eilk   = ""; // tegn            2       2       271        begge    opprinnelses land 
	private String eimk   = ""; // tegn           17      17       273        begge    merking           
	private Integer eint   = 0; // sonet        7  0       7       290        begge    kolli               
	private String einteh = ""; // tegn            2       2       297        begge    kolli enhet         
	private String eipmrk = ""; // tegn           30      30       299        begge    kjennemerke         
	private String eiptyp = ""; // tegn            2       2       329        begge    kjøretøy type       
	private String eiptm  = ""; // tegn            4       4       331        begge    transportmåte       
	private String eiptmt = ""; // tegn           50      50       335        begge    transportmåte tekst 
	private String eiplk  = ""; // tegn            2       2       385        begge    kjøretøynasjonalitet
	private String eicnr  = ""; // tegn           11      11       387        begge    containernr

	
}
