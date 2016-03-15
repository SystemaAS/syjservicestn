package no.systema.jservices.tvinn.sad.sadexport.model.dao.entities;
import java.io.Serializable;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public class SadlDao implements Serializable {
	
	private String slstat = "";                                
	public void setSlstat (String value){ this.slstat = value;   }   
	public String getSlstat (){ return this.slstat;   }              

	private String slknr = "";                                
	public void setSlknr (String value){ this.slknr = value;   }   
	public String getSlknr (){ return this.slknr;   }              

	private String slalfa = "";                                
	public void setSlalfa (String value){ this.slalfa = value;   }   
	public String getSlalfa (){ return this.slalfa;   }              

	private String sltxt = "";                                
	public void setSltxt (String value){ this.sltxt = value;   }   
	public String getSltxt (){ return this.sltxt;   }              

	private String sloppl = "";                                
	public void setSloppl (String value){ this.sloppl = value;   }   
	public String getSloppl (){ return this.sloppl;   }              

	private String slvekt = "";                                
	public void setSlvekt (String value){ this.slvekt = value;   }   
	public String getSlvekt (){ return this.slvekt;   }              
	
	private Double slvektDbl = 0.00D;                                
	public Double getSlvektDbl (){
		if(this.slvekt!=null && !"".equals(this.slvekt)){
			String tmp = this.slvekt.replace(",", ".");
			slvektDbl = Double.valueOf(tmp);
		}
		return slvektDbl;   
	} 
	
	private String sltanr = "";                                
	public void setSltanr (String value){ this.sltanr = value;   }   
	public String getSltanr (){ return this.sltanr;   }              

	private String sltar = "";                                
	public void setSltar (String value){ this.sltar = value;   }   
	public String getSltar (){ return this.sltar;   }              

	private String slpva = "";                                
	public void setSlpva (String value){ this.slpva = value;   }   
	public String getSlpva(){ return this.slpva;   }              

	private String slsats = "";                                
	public void setSlsats (String value){ this.slsats = value;   }   
	public String getSlsats (){ return this.slsats;   }              

	private Double slsatsDbl = 0.00D;                                
	public Double getSlsatsDbl (){
		if(this.slsats!=null && !"".equals(this.slsats)){
			String tmp = this.slsats.replace(",", ".");
			slsatsDbl = Double.valueOf(tmp);
		}
		return slsatsDbl;   
	}              

	private String sltn = "";                                
	public void setSltn (String value){ this.sltn = value;   }   
	public String getSltn (){ return this.sltn;   }              

	private String slto = "";                                
	public void setSlto (String value){ this.slto = value;   }   
	public String getSlto (){ return this.slto;   }              

	private String slcref = "";                                
	public void setSlcref (String value){ this.slcref = value;   }   
	public String getSlcref (){ return this.slcref;   }              

}
