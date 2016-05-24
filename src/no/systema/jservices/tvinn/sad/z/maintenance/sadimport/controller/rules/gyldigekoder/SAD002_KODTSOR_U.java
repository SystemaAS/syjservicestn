package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.apache.log4j.Logger;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsoDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.KodtsoDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.KodtsoDaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 */
public class SAD002_KODTSOR_U {
	private KodtsoDaoServices kodtsoDaoServices = new KodtsoDaoServicesImpl();
	private static Logger logger = Logger.getLogger(SAD002_KODTSOR_U.class.getName());
	
	public SAD002_KODTSOR_U(){ }
	
	public SAD002_KODTSOR_U(KodtsoDaoServices kodts8DaoServices){
		this.kodtsoDaoServices = kodtsoDaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtsoDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsokd()!=null && !"".equals(dao.getKsokd())) &&
				(dao.getKsoft()!=null && !"".equals(dao.getKsoft()))	){
				
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
	public boolean isValidInputForDelete(KodtsoDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsokd()!=null && !"".equals(dao.getKsokd()))  ){
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
		List list = this.kodtsoDaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
