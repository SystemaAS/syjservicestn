package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules.gyldigekoder;

import org.apache.logging.log4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.KodtscDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.KodtscDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.KodtscDaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 */
public class SAD002_KODTSCR_U {
	private KodtscDaoServices kodtscDaoServices = new KodtscDaoServicesImpl();
	private static Logger logger = LogManager.getLogger(SAD002_KODTSCR_U.class.getName());
	
	public SAD002_KODTSCR_U(){ }
	
	public SAD002_KODTSCR_U(KodtscDaoServices kodts8DaoServices){
		this.kodtscDaoServices = kodtscDaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtscDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsckd()!=null && !"".equals(dao.getKsckd())) &&
				(dao.getKscft()!=null && !"".equals(dao.getKscft()))	){
				
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
	public boolean isValidInputForDelete(KodtscDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsckd()!=null && !"".equals(dao.getKsckd()))  ){
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
		List list = this.kodtscDaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
