package no.systema.jservices.tvinn.sad.z.maintenance.sad.controller.rules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadlDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts6DaoServices;
/**
 * 
 * @author oscardelatorre
 * @date May 30, 2016
 */
public class SAD004R_U {
	
	public boolean isValidInput(SadlDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSlalfa()!=null && !"".equals(dao.getSlalfa())) &&  (dao.getSlknr()!=null && !"".equals(dao.getSlknr())) ){
				//OK
				if (dao.getPref()!=null && !"".equals(dao.getPref())){
					
					
				}
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}

	public boolean isValidInputForDelete(SadlDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSlalfa()!=null && !"".equals(dao.getSlalfa())) &&  (dao.getSlknr()!=null && !"".equals(dao.getSlknr())) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
	
		return retval;
	}
	
	
}
