package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules.gyldigekoder;

import org.apache.logging.log4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.KodtseDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.KodtseDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.KodtseDaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 */
public class SAD002_KODTSER_U {
	private KodtseDaoServices kodtseDaoServices = new KodtseDaoServicesImpl();
	private static Logger logger = LogManager.getLogger(SAD002_KODTSER_U.class.getName());
	
	public SAD002_KODTSER_U(){ }
	
	public SAD002_KODTSER_U(KodtseDaoServices kodts8DaoServices){
		this.kodtseDaoServices = kodtseDaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtseDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsefyl()!=null && !"".equals(dao.getKsefyl())) &&
				(dao.getKsetxt()!=null && !"".equals(dao.getKsetxt()))	){
				
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
	public boolean isValidInputForDelete(KodtseDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsefyl()!=null && !"".equals(dao.getKsefyl()))  ){
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
		List list = this.kodtseDaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
