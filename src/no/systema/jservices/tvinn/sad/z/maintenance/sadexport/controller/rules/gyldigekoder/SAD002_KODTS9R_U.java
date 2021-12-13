package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules.gyldigekoder;

import org.apache.logging.log4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.Kodts9Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.Kodts9DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.Kodts9DaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 */
public class SAD002_KODTS9R_U {
	private Kodts9DaoServices kodts9DaoServices = new Kodts9DaoServicesImpl();
	private static Logger logger = LogManager.getLogger(SAD002_KODTS9R_U.class.getName());
	
	public SAD002_KODTS9R_U(){ }
	
	public SAD002_KODTS9R_U(Kodts9DaoServices kodts8DaoServices){
		this.kodts9DaoServices = kodts9DaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Kodts9Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs9typ()!=null && !"".equals(dao.getKs9typ())) &&
				(dao.getKs9ftx()!=null && !"".equals(dao.getKs9ftx()))	){
				
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
	public boolean isValidInputForDelete(Kodts9Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs9typ()!=null && !"".equals(dao.getKs9typ()))  ){
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
	public boolean isValidId(String id, String user, String mode){
		boolean  retval = false;
		StringBuffer dbErrorStackTrace = new StringBuffer();
		List list = this.kodts9DaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
