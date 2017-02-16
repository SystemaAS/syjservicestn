package no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.entities;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import no.systema.jservices.common.dao.IDao;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 * 
 */
public class FirmDao implements Serializable, IDao {
	
	private String fifirm = "";                             
	public String getFifirmPropertyName (){ return "fifirm"; }
	public void setFifirm (String value){ this.fifirm = value;   }   
	public String getFifirm (){ return this.fifirm;   }  
	
	private String fift = "";                                
	public String getFiftPropertyName (){ return "fift"; }
	public void setFift (String value){ this.fift = value;   }   
	public String getFift (){ return this.fift;   }  
	
	private String fikdul = "";                                
	public String getFikdulPropertyName (){ return "fikdul"; }
	public void setFikdul (String value){ this.fikdul = value;   }   
	public String getFikdul (){ return this.fikdul;   }  
	
	private String filtb = "";                                
	public String getFiltbPropertyName (){ return "filtb"; }
	public void setFiltb (String value){ this.filtb = value;   }   
	public String getFiltb (){ return this.filtb;   }  
	
	private String filfb = "";                                
	public String getFilfbPropertyName (){ return "filfb"; }
	public void setFilfb (String value){ this.filfb = value;   }   
	public String getFilfb (){ return this.filfb;   }  
	
	private String fiups = "";                                
	public String getFiupsPropertyName (){ return "fiups"; }
	public void setFiups (String value){ this.fiups = value;   }   
	public String getFiups (){ return this.fiups;   }  
	
	private String fiupm = "";                                
	public String getFiupmPropertyName (){ return "fiupm"; }
	public void setFiupm (String value){ this.fiupm = value;   }   
	public String getFiupm (){ return this.fiupm;   }  
	
	private String fikdt = "";                                
	public String getFikdtPropertyName (){ return "fikdt"; }
	public void setFikdt (String value){ this.fikdt = value;   }   
	public String getFikdt (){ return this.fikdt;   }  
	
	private String fiatk = "";                                
	public String getFiatkPropertyName (){ return "fiatk"; }
	public void setFiatk (String value){ this.fiatk = value;   }   
	public String getFiatk (){ return this.fiatk;   }  
	
	private String fiurli = "";                                
	public String getFiurliPropertyName (){ return "fiurli"; }
	public void setFiurli (String value){ this.fiurli = value;   }   
	public String getFiurli (){ return this.fiurli;   }  
	
	private String fiurle = "";                                
	public String getFiurlePropertyName (){ return "fiurle"; }
	public void setFiurle (String value){ this.fiurle = value;   }   
	public String getFiurle (){ return this.fiurle;   }  
	
	private String fiurfi = "";                                
	public String getFiurfiPropertyName (){ return "fiurfi"; }
	public void setFiurfi (String value){ this.fiurfi = value;   }   
	public String getFiurfi (){ return this.fiurfi;   }  
	
	private String fiurfe = "";                                
	public String getFiurfePropertyName (){ return "fiurfe"; }
	public void setFiurfe (String value){ this.fiurfe = value;   }   
	public String getFiurfe (){ return this.fiurfe;   }  
	
	private String fiurfl = "";                                
	public String getFiurflPropertyName (){ return "fiurfl"; }
	public void setFiurfl (String value){ this.fiurfl = value;   }   
	public String getFiurfl (){ return this.fiurfl;   }  
	
	private String fiurbi = "";                                
	public String getFiurbiPropertyName (){ return "fiurbi"; }
	public void setFiurbi (String value){ this.fiurbi = value;   }   
	public String getFiurbi (){ return this.fiurbi;   }  
	
	private String fiurbe = "";                                
	public String getFiurbePropertyName (){ return "fiurbe"; }
	public void setFiurbe (String value){ this.fiurbe = value;   }   
	public String getFiurbe (){ return this.fiurbe;   }  
	
	private String fiurbl = "";                                
	public String getFiurblPropertyName (){ return "fiurbl"; }
	public void setFiurbl (String value){ this.fiurbl = value;   }   
	public String getFiurbl (){ return this.fiurbl;   }  
	
	private String fiursi = "";                                
	public String getFiursiPropertyName (){ return "fiursi"; }
	public void setFiursi (String value){ this.fiursi = value;   }   
	public String getFiursi (){ return this.fiursi;   }  
	
	private String fiurse = "";                                
	public String getFiursePropertyName (){ return "fiurse"; }
	public void setFiurse (String value){ this.fiurse = value;   }   
	public String getFiurse (){ return this.fiurse;   }  
	
	private String fimvas = "";                                
	public String getFimvasPropertyName (){ return "fimvas"; }
	public void setFimvas (String value){ this.fimvas = value;   }   
	public String getFimvas (){ return this.fimvas;   }  
	
	private String fivalk = "";                                
	public String getFivalkPropertyName (){ return "fivalk"; }
	public void setFivalk (String value){ this.fivalk = value;   }   
	public String getFivalk (){ return this.fivalk;   }  
	
	private String ficurr = "";                                
	public String getFicurrPropertyName (){ return "ficurr"; }
	public void setFicurr (String value){ this.ficurr = value;   }   
	public String getFicurr (){ return this.ficurr;   }  
	
	private String fitax = "";                                
	public String getFitaxPropertyName (){ return "fitax"; }
	public void setFitax (String value){ this.fitax = value;   }   
	public String getFitax (){ return this.fitax;   }  
	
	private String fidtfm = "";                                
	public String getFidtfmPropertyName (){ return "fidtfm"; }
	public void setFidtfm (String value){ this.fidtfm = value;   }   
	public String getFidtfm (){ return this.fidtfm;   }  
	
	private String fideci = "";                                
	public String getFideciPropertyName (){ return "fideci"; }
	public void setFideci (String value){ this.fideci = value;   }   
	public String getFideci (){ return this.fideci;   }  
	
	private String fiecon = "";                                
	public String getFieconPropertyName (){ return "fiecon"; }
	public void setFiecon (String value){ this.fiecon = value;   }   
	public String getFiecon (){ return this.fiecon;   }  
	
	private String fiavte = "";                                
	public String getFiavtePropertyName (){ return "fiavte"; }
	public void setFiavte (String value){ this.fiavte = value;   }   
	public String getFiavte (){ return this.fiavte;   }  
	
	private String fikont = "";                                
	public String getFikontPropertyName (){ return "fikont"; }
	public void setFikont (String value){ this.fikont = value;   }   
	public String getFikont (){ return this.fikont;   }  
	
	private String filand = "";                                
	public String getFilandPropertyName (){ return "filand"; }
	public void setFiland (String value){ this.filand = value;   }   
	public String getFiland (){ return this.filand;   }  
	
	private String fitdvi = "";                                
	public String getFitdviPropertyName (){ return "fitdvi"; }
	public void setFitdvi (String value){ this.fitdvi = value;   }   
	public String getFitdvi (){ return this.fitdvi;   }  
	
	private String fistfn = "";                                
	public String getFistfnPropertyName (){ return "fistfn"; }
	public void setFistfn (String value){ this.fistfn = value;   }   
	public String getFistfn (){ return this.fistfn;   }  
	
	private String fistfe = "";                                
	public String getFistfePropertyName (){ return "fistfe"; }
	public void setFistfe (String value){ this.fistfe = value;   }   
	public String getFistfe (){ return this.fistfe;   }  
	
	private String fistft = "";                                
	public String getFistftPropertyName (){ return "fistft"; }
	public void setFistft (String value){ this.fistft = value;   }   
	public String getFistft (){ return this.fistft;   }  
	
	private String file12 = "";                                
	public String getFile12PropertyName (){ return "file12"; }
	public void setFile12 (String value){ this.file12 = value;   }   
	public String getFile12 (){ return this.file12;   }  
	
	private String file22 = "";                                
	public String getFile22PropertyName (){ return "file22"; }
	public void setFile22 (String value){ this.file22 = value;   }   
	public String getFile22 (){ return this.file22;   }  
	
	private String file11 = "";                                
	public String getFile11PropertyName (){ return "file11"; }
	public void setFile11 (String value){ this.file11 = value;   }   
	public String getFile11 (){ return this.file11;   }  
	
	private String file21 = "";                                
	public String getFile21PropertyName (){ return "file21"; }
	public void setFile21 (String value){ this.file21 = value;   }   
	public String getFile21 (){ return this.file21;   }  
	
	private String file31 = "";                                
	public String getFile31PropertyName (){ return "file31"; }
	public void setFile31 (String value){ this.file31 = value;   }   
	public String getFile31 (){ return this.file31;   }  
	
	private String file41 = "";                                
	public String getFile41PropertyName (){ return "file41"; }
	public void setFile41 (String value){ this.file41 = value;   }   
	public String getFile41 (){ return this.file41;   }  
	
	private String fitran = "";                                
	public String getFitranPropertyName (){ return "fitran"; }
	public void setFitran (String value){ this.fitran = value;   }   
	public String getFitran (){ return this.fitran;   }  
	
	private String fikrtn = "";                                
	public String getFikrtnPropertyName (){ return "fikrtn"; }
	public void setFikrtn (String value){ this.fikrtn = value;   }   
	public String getFikrtn (){ return this.fikrtn;   }  
	
	private String fitax2 = "";                                
	public String getFitax2PropertyName (){ return "fitax2"; }
	public void setFitax2 (String value){ this.fitax2 = value;   }   
	public String getFitax2 (){ return this.fitax2;   }  
	

	private String fitaxd = "";                                
	public String getFitaxdPropertyName (){ return "fitaxd"; }
	public void setFitaxd (String value){ this.fitaxd = value;   }   
	public String getFitaxd (){ return this.fitaxd;   }  
	
	private String fisadk = "";                                
	public String getFisadkPropertyName (){ return "fisadk"; }
	public void setFisadk (String value){ this.fisadk = value;   }   
	public String getFisadk (){ return this.fisadk;   }  
	
	private String filibf = "";                                
	public String getFilibfPropertyName (){ return "filibf"; }
	public void setFilibf (String value){ this.filibf = value;   }   
	public String getFilibf (){ return this.filibf;   }  
	
	private String filibo = "";                                
	public String getFiliboPropertyName (){ return "filibo"; }
	public void setFilibo (String value){ this.filibo = value;   }   
	public String getFilibo (){ return this.filibo;   }  
	
	private String innutl = "";                                
	public String getInnutlPropertyName (){ return "innutl"; }
	public void setInnutl (String value){ this.innutl = value;   }   
	public String getInnutl (){ return this.innutl;   }  
	
	private String zipcod = "";                                
	public String getZipcodPropertyName (){ return "zipcod"; }
	public void setZipcod (String value){ this.zipcod = value;   }   
	public String getZipcod (){ return this.zipcod;   }  
	
	//--------------
	//FIRFB -table
	//--------------
	private String fifbnr = "";                                
	public String getFifbnrPropertyName (){ return "fifbnr"; }
	public void setFifbnr (String value){ this.fifbnr = value;   }   
	public String getFifbnr (){ return this.fifbnr;   }  
	
	private String fitpnr = "";                                
	public String getFitpnrPropertyName (){ return "fitpnr"; }
	public void setFitpnr (String value){ this.fitpnr = value;   }   
	public String getFitpnr (){ return this.fitpnr;   }  
	
	private String firecn = "";                                
	public String getFirecnPropertyName (){ return "firecn"; }
	public void setFirecn (String value){ this.firecn = value;   }   
	public String getFirecn (){ return this.firecn;   }  
	
	private String firecm = "";                                
	public String getFirecmPropertyName (){ return "firecm"; }
	public void setFirecm (String value){ this.firecm = value;   }   
	public String getFirecm (){ return this.firecm;   }  
	
	private String fisnla = "";                                
	public String getFisnlaPropertyName (){ return "fisnla"; }
	public void setFisnla (String value){ this.fisnla = value;   }   
	public String getFisnla (){ return this.fisnla;   }  
	
	private String fisnle = "";                                
	public String getFisnlePropertyName (){ return "fisnle"; }
	public void setFisnle (String value){ this.fisnle = value;   }   
	public String getFisnle (){ return this.fisnle;   }  
	
	private String fiidla = "";                                
	public String getFiidlaPropertyName (){ return "fiidla"; }
	public void setFiidla (String value){ this.fiidla = value;   }   
	public String getFiidla (){ return this.fiidla;   }  
	
	private String fiidle = "";                                
	public String getFiidlePropertyName (){ return "fiidle"; }
	public void setFiidle (String value){ this.fiidle = value;   }   
	public String getFiidle (){ return this.fiidle;   }  
	
	private String fiidnr = "";                                
	public String getFiidnrPropertyName (){ return "fiidnr"; }
	public void setFiidnr (String value){ this.fiidnr = value;   }   
	public String getFiidnr (){ return this.fiidnr;   }  
	
	private String fiidmx = "";                                
	public String getFiidmxPropertyName (){ return "fiidmx"; }
	public void setFiidmx (String value){ this.fiidmx = value;   }   
	public String getFiidmx (){ return this.fiidmx;   }  
	//--------------
	//END FIRFB-table
	//--------------

	//--------------
	//FIRMKOS -table
	//--------------
	private String tillat = "";  
	public String getTillatPropertyName (){ return "tillat"; }
	public void setTillat (String value){ this.tillat = value;   }   
	public String getTillat (){ return this.tillat;   }  
	
	private String interr = ""; 
	public String getInterrPropertyName (){ return "interr"; }
	public void setInterr (String value){ this.interr = value;   }   
	public String getInterr (){ return this.interr;   }  
	//END FIRMKOS
	
	//--------------
	//FIRKU -table
	//--------------
	private String fikufr = ""; 
	public String getFikufrPropertyName (){ return "fikufr"; }
	public void setFikufr (String value){ this.fikufr = value;   }   
	public String getFikufr (){ return this.fikufr;   }  
	
	private String fikuti = "";
	public String getFikutiPropertyName (){ return "fikuti"; }
	public void setFikuti (String value){ this.fikuti = value;   }   
	public String getFikuti (){ return this.fikuti;   } 
	
	private String fikune = "";  
	public String getFikunePropertyName (){ return "fikune"; }
	public void setFikune (String value){ this.fikune = value;   }   
	public String getFikune (){ return this.fikune;   } 
	//END FIRKU
	
	//--------------
	//KODTV -table
	//--------------
	private String kovavd = "";  
	public String getKovavdPropertyName (){ return "kovavd"; }
	public void setKovavd (String value){ this.kovavd = value;   }   
	public String getKovavd (){ return this.kovavd;   } 
	
	private String kovlkg = "";  
	public String getKovlkgPropertyName (){ return "kovlkg"; }
	public void setKovlkg (String value){ this.kovlkg = value;   }   
	public String getKovlkg (){ return this.kovlkg;   } 
	
	private String kovkkg = "";  
	public String getKovkkgPropertyName (){ return "kovkkg"; }
	public void setKovkkg (String value){ this.kovkkg = value;   }   
	public String getKovkkg (){ return this.kovkkg;   } 
	
	private String kovk1 = "";  
	public String getKovk1PropertyName (){ return "kovk1"; }
	public void setKovk1 (String value){ this.kovk1 = value;   }   
	public String getKovk1 (){ return this.kovk1;   } 
	
	private String kovk2 = "";  
	public String getKovk2PropertyName (){ return "kovk2"; }
	public void setKovk2 (String value){ this.kovk2 = value;   }   
	public String getKovk2 (){ return this.kovk2;   } 
	
	private String kovk3 = "";  
	public String getKovk3PropertyName (){ return "kovk3"; }
	public void setKovk3 (String value){ this.kovk3 = value;   }   
	public String getKovk3 (){ return this.kovk3;   } 
	
	private String kovk4 = "";  
	public String getKovk4PropertyName (){ return "kovk4"; }
	public void setKovk4 (String value){ this.kovk4 = value;   }   
	public String getKovk4 (){ return this.kovk4;   } 
	
	private String kovk5 = "";  
	public String getKovk5PropertyName (){ return "kovk5"; }
	public void setKovk5 (String value){ this.kovk5 = value;   }   
	public String getKovk5 (){ return this.kovk5;   } 
	
	private String kovk6 = "";  
	public String getKovk6PropertyName (){ return "kovk6"; }
	public void setKovk6 (String value){ this.kovk6 = value;   }   
	public String getKovk6 (){ return this.kovk6;   } 
	
	private String kovk7 = "";  
	public String getKovk7PropertyName (){ return "kovk7"; }
	public void setKovk7 (String value){ this.kovk7 = value;   }   
	public String getKovk7 (){ return this.kovk7;   } 
	
	private String kovk8 = "";  
	public String getKovk8PropertyName (){ return "kovk8"; }
	public void setKovk8 (String value){ this.kovk8 = value;   }   
	public String getKovk8 (){ return this.kovk8;   } 
	
	private String kovk9 = "";  
	public String getKovk9PropertyName (){ return "kovk9"; }
	public void setKovk9 (String value){ this.kovk9 = value;   }   
	public String getKovk9 (){ return this.kovk9;   } 
	
	private String kovk10 = "";  
	public String getKovk10PropertyName (){ return "kovk10"; }
	public void setKovk10 (String value){ this.kovk10 = value;   }   
	public String getKovk10 (){ return this.kovk10;   } 
	
	private String kovk11 = "";  
	public String getKovk11PropertyName (){ return "kovk11"; }
	public void setKovk11 (String value){ this.kovk11 = value;   }   
	public String getKovk11 (){ return this.kovk11;   } 
	
	private String kovomr = "";  
	public String getKovomrPropertyName (){ return "kovomr"; }
	public void setKovomr (String value){ this.kovomr = value;   }   
	public String getKovomr (){ return this.kovomr;   } 
	
	private String kovpro = "";  
	public String getKovproPropertyName (){ return "kovpro"; }
	public void setKovpro (String value){ this.kovpro = value;   }   
	public String getKovpro (){ return this.kovpro;   } 
	//END KODTV
	
	
	//--------------
	//FIRSTA -table
	//--------------
	private String fista = "";  
	public String getFistaPropertyName (){ return "fista"; }
	public void setFista (String value){ this.fista = value;   }   
	public String getFista (){ return this.fista;   }
	
	private String fistb = "";  
	public String getFistbPropertyName (){ return "fistb"; }
	public void setFistb (String value){ this.fistb = value;   }   
	public String getFistb (){ return this.fistb;   }
	
	private String fistc = "";  
	public String getFistcPropertyName (){ return "fistc"; }
	public void setFistc (String value){ this.fistc = value;   }   
	public String getFistc (){ return this.fistc;   }
	
	private String fistd = "";  
	public String getFistdPropertyName (){ return "fistd"; }
	public void setFistd (String value){ this.fistd = value;   }   
	public String getFistd (){ return this.fistd;   }
	
	private String fiste = "";  
	public String getFistePropertyName (){ return "fiste"; }
	public void setFiste (String value){ this.fiste = value;   }   
	public String getFiste (){ return this.fiste;   }
	
	private String fistf = "";  
	public String getFistfPropertyName (){ return "fistf"; }
	public void setFistf (String value){ this.fistf = value;   }   
	public String getFistf (){ return this.fistf;   }
	
	private String fistg = "";  
	public String getFistgPropertyName (){ return "fistg"; }
	public void setFistg (String value){ this.fistg = value;   }   
	public String getFistg (){ return this.fistg;   }
	
	private String fisth = "";  
	public String getFisthPropertyName (){ return "fisth"; }
	public void setFisth (String value){ this.fisth = value;   }   
	public String getFisth (){ return this.fisth;   }
	
	private String fisti = "";  
	public String getFistiPropertyName (){ return "fisti"; }
	public void setFisti (String value){ this.fisti = value;   }   
	public String getFisti (){ return this.fisti;   }
	
	private String fistj = "";  
	public String getFistjPropertyName (){ return "fistj"; }
	public void setFistj (String value){ this.fistj = value;   }   
	public String getFistj (){ return this.fistj;   }
	
	private String fistk = "";  
	public String getFistkPropertyName (){ return "fistk"; }
	public void setFistk (String value){ this.fistk = value;   }   
	public String getFistk (){ return this.fistk;   }
	
	private String fistl = "";  
	public String getFistlPropertyName (){ return "fistl"; }
	public void setFistl (String value){ this.fistl = value;   }   
	public String getFistl (){ return this.fistl;   }
	
	private String fistm = "";  
	public String getFistmPropertyName (){ return "fistm"; }
	public void setFistm (String value){ this.fistm = value;   }   
	public String getFistm (){ return this.fistm;   }
	
	private String fistn = "";  
	public String getFistnPropertyName (){ return "fistn"; }
	public void setFistn (String value){ this.fistn = value;   }   
	public String getFistn (){ return this.fistn;   }
	
	private String fisto = "";  
	public String getFistoPropertyName (){ return "fisto"; }
	public void setFisto (String value){ this.fisto = value;   }   
	public String getFisto (){ return this.fisto;   }
	
	private String fistp = "";  
	public String getFistpPropertyName (){ return "fistp"; }
	public void setFistp (String value){ this.fistp = value;   }   
	public String getFistp (){ return this.fistp;   }
	
	private String fistq = "";  
	public String getFistqPropertyName (){ return "fistq"; }
	public void setFistq (String value){ this.fistq = value;   }   
	public String getFistq (){ return this.fistq;   }
	
	private String fistr = "";  
	public String getFistrPropertyName (){ return "fistr"; }
	public void setFistr (String value){ this.fistr = value;   }   
	public String getFistr (){ return this.fistr;   }
	
	private String fists = "";  
	public String getFistsPropertyName (){ return "fists"; }
	public void setFists (String value){ this.fists = value;   }   
	public String getFists (){ return this.fists;   }
	
	private String fistt = "";  
	public String getFisttPropertyName (){ return "fistt"; }
	public void setFistt (String value){ this.fistt = value;   }   
	public String getFistt (){ return this.fistt;   }
	
	private String fistu = "";  
	public String getFistuPropertyName (){ return "fistu"; }
	public void setFistu (String value){ this.fistu = value;   }   
	public String getFistu (){ return this.fistu;   }
	
	private String fistv = "";  
	public String getFistvPropertyName (){ return "fistv"; }
	public void setFistv (String value){ this.fistv = value;   }   
	public String getFistv (){ return this.fistv;   }
	
	private String fistw = "";  
	public String getFistwPropertyName (){ return "fistw"; }
	public void setFistw (String value){ this.fistw = value;   }   
	public String getFistw (){ return this.fistw;   }
	
	private String fistx = "";  
	public String getFistxPropertyName (){ return "fistx"; }
	public void setFistx (String value){ this.fistx = value;   }   
	public String getFistx (){ return this.fistx;   }
	
	private String fisty = "";  
	public String getFistyPropertyName (){ return "fisty"; }
	public void setFisty (String value){ this.fisty = value;   }   
	public String getFisty (){ return this.fisty;   }
	
	private String fistz = "";  
	public String getFistzPropertyName (){ return "fistz"; }
	public void setFistz (String value){ this.fistz = value;   }   
	public String getFistz (){ return this.fistz;   }
	
	private String fistnr = "";  
	public String getFistnrPropertyName (){ return "fistnr"; }
	public void setFistnr (String value){ this.fistnr = value;   }   
	public String getFistnr (){ return this.fistnr;   }
	//END FIRSTA
	
	
	//-------------
	//FIRTR table
	//-------------
	private String favreg = "";  
	public String getFavregPropertyName (){ return "favreg"; }
	public void setFavreg (String value){ this.favreg = value;   }   
	public String getFavreg (){ return this.favreg;   }
	
	private String fibise = "";  
	public String getFibisePropertyName (){ return "fibise"; }
	public void setFibise (String value){ this.fibise = value;   }   
	public String getFibise (){ return this.fibise;   }
	
	private String fferk = "";  
	public String geFfferkPropertyName (){ return "fferk"; }
	public void setFferk (String value){ this.fferk = value;   }   
	public String getFferk (){ return this.fferk;   }
	
	private String fibrut = "";  
	public String getFibrutPropertyName (){ return "fibrut"; }
	public void setFibrut (String value){ this.fibrut = value;   }   
	public String getFibrut (){ return this.fibrut;   }
	
	private String fikonv = "";  
	public String getFikonvPropertyName (){ return "fikonv"; }
	public void setFikonv(String value){ this.fikonv = value;   }   
	public String getFikonv (){ return this.fikonv;   }
	//END FIRTR
	
	//-----------
	//FIRML1
	//-----------
	private String l1firm = "";  
	public String getL1firmPropertyName (){ return "l1firm"; }
	public void setL1firm(String value){ this.l1firm = value;   }   
	public String getL1firm (){ return this.l1firm;   }
	
	private String l1kjor = "";  
	public String getL1kjorPropertyName (){ return "l1kjor"; }
	public void setL1kjor(String value){ this.l1kjor = value;   }   
	public String getL1kjor (){ return this.l1kjor;   }
	
	private String l1stdn = "";  
	public String getL1stdnPropertyName (){ return "l1stdn"; }
	public void setL1stdn(String value){ this.l1stdn = value;   }   
	public String getL1stdn (){ return this.l1stdn;   }
	
	private String l1datn = "";  
	public String getL1datnPropertyName (){ return "l1datn"; }
	public void setL1datn(String value){ this.l1datn = value;   }   
	public String getL1datn (){ return this.l1datn;   }
	//END FIRML1

	@Override
	public Map<String, Object> getKeys() {
		Map<String, Object>  keys = new HashMap<String, Object>();
		keys.put("fifirm", fifirm);
		return keys;
	}
	
}
