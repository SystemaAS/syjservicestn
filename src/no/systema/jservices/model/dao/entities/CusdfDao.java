package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

public class CusdfDao implements Serializable {

	private String knavn = null;                                
	public void setKnavn (String value){ this.knavn = value;   }   
	public String getKnavn (){ return this.knavn;   }              

	private String adr1 = null;                                
	public void setAdr1 (String value){ this.adr1 = value;   }   
	public String getAdr1 (){ return this.adr1;   }              

	private String adr2 = null;                                
	public void setAdr2 (String value){ this.adr2 = value;   }   
	public String getAdr2 (){ return this.adr2;   }              

	private String adr3 = null;                                
	public void setAdr3 (String value){ this.adr3 = value;   }   
	public String getAdr3 (){ return this.adr3;   }              

	private String postnr = null;                                
	public void setPostnr (String value){ this.postnr = value;   }   
	public String getPostnr (){ return this.postnr;   }              

}
