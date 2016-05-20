package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.apache.log4j.Logger;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts3Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts3DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts3DaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 */
public class SAD002_KODTS3R_U {
	private Kodts3DaoServices kodts3DaoServices = new Kodts3DaoServicesImpl();
	private static Logger logger = Logger.getLogger(SAD002_KODTS3R_U.class.getName());
	
	public SAD002_KODTS3R_U(){ }
	
	public SAD002_KODTS3R_U(Kodts3DaoServices kodts8DaoServices){
		this.kodts3DaoServices = kodts3DaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Kodts3Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs3trt()!=null && !"".equals(dao.getKs3trt())) &&
				(dao.getKs3ftx()!=null && !"".equals(dao.getKs3ftx()))	){
				
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
	public boolean isValidInputForDelete(Kodts3Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs3trt()!=null && !"".equals(dao.getKs3trt()))  ){
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
		List list = this.kodts3DaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
