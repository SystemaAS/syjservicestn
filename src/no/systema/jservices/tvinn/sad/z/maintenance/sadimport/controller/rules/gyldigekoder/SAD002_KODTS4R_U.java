package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.slf4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts4Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts4DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts4DaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 */
public class SAD002_KODTS4R_U {
	private Kodts4DaoServices kodts4DaoServices = new Kodts4DaoServicesImpl();
	private static Logger logger = LoggerFactory.getLogger(SAD002_KODTS4R_U.class.getName());
	
	public SAD002_KODTS4R_U(){ }
	
	public SAD002_KODTS4R_U(Kodts4DaoServices kodts8DaoServices){
		this.kodts4DaoServices = kodts4DaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Kodts4Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs4trm()!=null && !"".equals(dao.getKs4trm())) &&
				(dao.getKs4ftx()!=null && !"".equals(dao.getKs4ftx()))	){
				
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
	public boolean isValidInputForDelete(Kodts4Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs4trm()!=null && !"".equals(dao.getKs4trm()))  ){
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
		List list = this.kodts4DaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
