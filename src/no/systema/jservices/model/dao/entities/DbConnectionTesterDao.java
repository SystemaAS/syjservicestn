package no.systema.jservices.model.dao.entities;
import java.io.Serializable;

public class DbConnectionTesterDao implements Serializable {

	private String text = null;                                
	public void setText (String value){ this.text = value;   }   
	public String getText (){ return this.text;   }              


}
