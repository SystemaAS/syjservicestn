package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadvareDao;
/**
 * 
 * @author oscardelatorre
 * @date May 27, 2016
 */
public class SAD001AR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadvareDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getVarenr()!=null && !"".equals(dao.getVarenr())) &&  (dao.getLevenr()!=null && !"".equals(dao.getLevenr())) ){
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
	public boolean isValidInputForDelete(SadvareDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getVarenr()!=null && !"".equals(dao.getVarenr())) &&  (dao.getLevenr()!=null && !"".equals(dao.getLevenr())) ){
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
