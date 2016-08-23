package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SadavgeDao;
/**
 * 
 * @author Fredrik Möller
 * @date Aug 16, 2016
 */
public class SAD015_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public static boolean isValid(SadavgeDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getAgtanr()!=null && !"".equals(dao.getAgtanr())) && (dao.getAgakd()!=null && !"".equals(dao.getAgakd())) && (dao.getAgskv()!=null && !"".equals(dao.getAgskv())) ){
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
	 * Validate before Add/update
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public static boolean isValidInput(SadavgeDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getAgtanr()!=null && !"".equals(dao.getAgtanr())) && (dao.getAgskv()!=null && !"".equals(dao.getAgskv())) ){
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
