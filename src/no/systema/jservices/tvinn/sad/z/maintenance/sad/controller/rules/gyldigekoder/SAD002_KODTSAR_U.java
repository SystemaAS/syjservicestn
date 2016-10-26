package no.systema.jservices.tvinn.sad.z.maintenance.sad.controller.rules.gyldigekoder;

import org.apache.log4j.Logger;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.gyldigekoder.KodtsaDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.gyldigekoder.KodtsaDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.gyldigekoder.KodtsaDaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 */
public class SAD002_KODTSAR_U {
	private KodtsaDaoServices kodtsaDaoServices = new KodtsaDaoServicesImpl();
	private static Logger logger = Logger.getLogger(SAD002_KODTSAR_U.class.getName());
	
	public SAD002_KODTSAR_U(){ }
	
	public SAD002_KODTSAR_U(KodtsaDaoServices kodts8DaoServices){
		this.kodtsaDaoServices = kodtsaDaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtsaDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsakd()!=null && !"".equals(dao.getKsakd())) &&
				(dao.getKsaft()!=null && !"".equals(dao.getKsaft()))	){
				
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
	public boolean isValidInputForDelete(KodtsaDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsakd()!=null && !"".equals(dao.getKsakd()))  ){
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
		List list = this.kodtsaDaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
