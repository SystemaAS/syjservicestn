package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadsdDao;
/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 */
public class SAD999R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadsdDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSdtnrf()!=null && !"".equals(dao.getSdtnrf())) &&
				(dao.getSdkdae()!=null && !"".equals(dao.getSdkdae())) &&
				(dao.getSddtf()!=null && !"".equals(dao.getSddtf())) &&
				(dao.getSddtt()!=null && !"".equals(dao.getSddtt())) && 
				(dao.getSdblse()!=null && !"".equals(dao.getSdblse()))
				){
				
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
	public boolean isValidInputForDelete(SadsdDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSdtnrf()!=null && !"".equals(dao.getSdtnrf())) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
	
		return retval;
	}
}
