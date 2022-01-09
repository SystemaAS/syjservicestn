package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.slf4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts7Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts7DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts7DaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 */
public class SAD002_KODTS7R_U {
	private Kodts7DaoServices kodts7DaoServices = new Kodts7DaoServicesImpl();
	private static Logger logger = LoggerFactory.getLogger(SAD002_KODTS7R_U.class.getName());
	
	public SAD002_KODTS7R_U(){ }
	
	public SAD002_KODTS7R_U(Kodts7DaoServices kodts8DaoServices){
		this.kodts7DaoServices = kodts7DaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Kodts7Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs7vf()!=null && !"".equals(dao.getKs7vf())) &&
				(dao.getKs7ftx()!=null && !"".equals(dao.getKs7ftx()))	){
				
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
	public boolean isValidInputForDelete(Kodts7Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs7vf()!=null && !"".equals(dao.getKs7vf()))  ){
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
		List list = this.kodts7DaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
