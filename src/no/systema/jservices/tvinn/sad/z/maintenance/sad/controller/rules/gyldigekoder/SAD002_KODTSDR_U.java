package no.systema.jservices.tvinn.sad.z.maintenance.sad.controller.rules.gyldigekoder;

import org.apache.logging.log4j.*;
import java.util.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.gyldigekoder.KodtsdDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.gyldigekoder.KodtsdDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.gyldigekoder.KodtsdDaoServicesImpl;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 */
public class SAD002_KODTSDR_U {
	private KodtsdDaoServices kodtsdDaoServices = new KodtsdDaoServicesImpl();
	private static Logger logger = LogManager.getLogger(SAD002_KODTSDR_U.class.getName());
	
	public SAD002_KODTSDR_U(){ }
	
	public SAD002_KODTSDR_U(KodtsdDaoServices kodts8DaoServices){
		this.kodtsdDaoServices = kodtsdDaoServices;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtsdDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsdls()!=null && !"".equals(dao.getKsdls())) &&
				(dao.getKsdtxt()!=null && !"".equals(dao.getKsdtxt()))	){
				
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
	public boolean isValidInputForDelete(KodtsdDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKsdls()!=null && !"".equals(dao.getKsdls()))  ){
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
		List list = this.kodtsdDaoServices.findById( id,  dbErrorStackTrace);
		if(list!=null && list.size()>0){
			retval = true;
		}
		
		return retval;
	}
	
}
