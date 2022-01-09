package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder;

import org.slf4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsbDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.KodtsbDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.KodtsbDaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 */
public class SAD002_KODTSBR_U {
	private KodtsbDaoServices kodtsbDaoServices = new KodtsbDaoServicesImpl();
	private static Logger logger = LoggerFactory.getLogger(SAD002_KODTSBR_U.class.getName());
	
	public SAD002_KODTSBR_U(){ }
	
	public SAD002_KODTSBR_U(KodtsbDaoServices kodts8DaoServices){
		this.kodtsbDaoServices = kodtsbDaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtsbDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsbkd()!=null && !"".equals(dao.getKsbkd())) &&
				(dao.getKsbft()!=null && !"".equals(dao.getKsbft()))	){
				
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
	public boolean isValidInputForDelete(KodtsbDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsbkd()!=null && !"".equals(dao.getKsbkd()))  ){
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
		List list = this.kodtsbDaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
