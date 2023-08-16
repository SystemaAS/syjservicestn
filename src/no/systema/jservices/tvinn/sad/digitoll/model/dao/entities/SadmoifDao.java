package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class SadmoifDao implements IDao  {

	private String eist  = "";//      tegn            1       1         1        begge    status     
	private Integer eipro  = 0;//       sonet        8  0       8         2        begge    turnummer  
	private Integer eiavd  = 0;//       sonet        4  0       4        10        begge    avdeling   
	private Integer eitdn  = 0;//       sonet        7  0       7        14        begge    oppdragsnr 
	private Integer eili  = 0;//        sonet        5  0       5        21        begge    linjenr           
	private Integer eilnrt  = 0;//      sonet        7  0       7        26        begge    løpenummer        
	private Integer eilnrm  = 0;//      sonet        4  0       4        33        begge    m-lnr innen transp
	private Integer eilnrh  = 0;//      sonet        4  0       4        37        begge    h-lnr innen master 
	private String eibl  = "";//        sonet       13  2      13        41        begge    vrd/stk,nok voec   
	private String eistk  = "";//       sonet        5  2       5        54        begge    ant stk denne voec 
	private Integer eivnt  = 0;//       sonet        6  0       6        59        begge    tariffnr 6 siffer  
	private String eirge  = "";//       tegn           17      17        65        begge    voec id of seller  
	private String eiroe  = "";//       tegn            3       3        82        begge    role (fast fr5)    

}
