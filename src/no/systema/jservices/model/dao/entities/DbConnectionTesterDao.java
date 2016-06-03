package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

public class DbConnectionTesterDao implements Serializable, IDao {

	private String text = null;                                
	public void setText (String value){ this.text = value;   }   
	public String getText (){ return this.text;   }              

	private String dbserver = null;                                
	public void setDbserver (String value){ this.dbserver = value;   }   
	public String getDbserver (){ return this.dbserver;   }              


}
