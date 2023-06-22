package no.systema.jservices.tvinn.sad.ncts5export.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class NctsecDao implements IDao {

	private int tcavd  = 0; // sonet        4  avdeling  
	private int tctdn  = 0; // sonet        7  Tolldeklarasjonsnr
	private Integer tcli   = 0; // sonet        5  linjenr      
	private Integer tcln  = 0; // sonet        5   utgående linjenr.
	private String tcdk  = ""; // tegn        5   deklType
	private String tcalk  = ""; // tegn        2  avs.land     
	private String tcblk   = ""; // tegn        2 best.land        
	private String tcvktb  = ""; // sonet       13 3      BruttoVekt        
	private String tcucr  = ""; // tegn         35   ref.nr.ucr
	private Integer tcavd2  = 0; // sonet        4  avdeling FOR HENTET OPD 
	private Integer tctdn2  = 0; // sonet        7  Tolldeklarasjonsnr FOR HENTET OPD     
	private String  tcxext  = ""; // tegn       35  Extern.ref.   
	//AdditionalSupplyChainActor (0-99)
	private String tcrole  = ""; // tegn        3 Lev.role           
	private String tcidr  = ""; // tegn        17 Lev.id     
	//DepartureTransportMeans (0-999)
	private Integer tctaty = 0; // tegn        2  Type identif.         
	private String tctaid = ""; // tegn       35  ident         
	private String tctalk = ""; // sonet      2   landkod - Tr.middel        
	//Previous Document (0-99)
	private String tcpdty = ""; // tegn        4  Dok.Type
	private String tcpdrf   = ""; // tegn      70 Dok.ref 
	private String tcpdin   = ""; // tegn      35 Info.           
	//Supporting Document (0-99)
	private String tcsdty = ""; // tegn        4  Dok.Type
	private String tcsdrf   = ""; // tegn      70 Dok.ref 
	private Integer tcsdln   = 0; // sonet      5 Line item nr 
	private String tcsdin   = ""; // tegn      35 Info.
	//Transport Document (0-99)
	private String tctdty = ""; // tegn        4  Dok.Type
	private String tctdrf   = ""; // tegn      70 Dok.ref
	//Additional Reference (0-99)
	private String tcadty   = ""; // tegn      4 Ref.type
	private String tcadrf   = ""; // tegn      70 Dok.ref
	//Additional information (0-99)
	private String tcaicd   = ""; // tegn      5 info.kod
	private String tcaitx  = ""; // tegn    120 Text
	//Transp.Charges (1)
	private String tctrch  = ""; // tegn    1 Betal.sätt

	
}
