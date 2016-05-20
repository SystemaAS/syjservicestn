package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.apache.log4j.Logger;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts1Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts1DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts1DaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 */
public class SAD002_KODTS1R_U {
	private Kodts1DaoServices kodts1DaoServices = new Kodts1DaoServicesImpl();
	private static Logger logger = Logger.getLogger(SAD002_KODTS1R_U.class.getName());
	
	public SAD002_KODTS1R_U(){ }
	
	public SAD002_KODTS1R_U(Kodts1DaoServices kodts8DaoServices){
		this.kodts1DaoServices = kodts1DaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(Kodts1Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs1typ()!=null && !"".equals(dao.getKs1typ())) &&
				(dao.getKs1ftx()!=null && !"".equals(dao.getKs1ftx()))	){
				
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
	public boolean isValidInputForDelete(Kodts1Dao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKs1typ()!=null && !"".equals(dao.getKs1typ()))  ){
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
		List list = this.kodts1DaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
