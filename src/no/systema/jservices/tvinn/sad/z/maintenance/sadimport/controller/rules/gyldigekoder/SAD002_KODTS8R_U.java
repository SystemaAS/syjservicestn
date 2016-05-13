package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts8Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts8DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts8DaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 */
public class SAD002_KODTS8R_U {
	private Kodts8DaoServices kodts8DaoServices = new Kodts8DaoServicesImpl();
	private static Logger logger = Logger.getLogger(SAD002_KODTS8R_U.class.getName());
	
	public SAD002_KODTS8R_U(){ }
	
	public SAD002_KODTS8R_U(Kodts8DaoServices kodts8DaoServices){
		this.kodts8DaoServices = kodts8DaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Kodts8Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs8avg()!=null && !"".equals(dao.getKs8avg())) &&
				(dao.getKs8skv()!=null && !"".equals(dao.getKs8skv()))
				){
				
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(Kodts8Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs8avg()!=null && !"".equals(dao.getKs8avg())) &&
					(dao.getKs8skv()!=null && !"".equals(dao.getKs8skv())) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
	
		return retval;
	}
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidAvgSekvensId(String avgId, String sekvId, String user, String mode){
		boolean  retval = false;
		StringBuffer dbErrorStackTrace = new StringBuffer();
		List list = this.kodts8DaoServices.findById(avgId, sekvId, dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
