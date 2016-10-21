package no.systema.jservices.tvinn.sad.z.maintenance.felles.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;
/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 */
public class SAD010R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(TariDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTatanr()!=null && !"".equals(dao.getTatanr())) &&
				(dao.getTaalfa()!=null && !"".equals(dao.getTaalfa())) && 
				(dao.getTadtr()!=null && !"".equals(dao.getTadtr())) ){
				
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
	public boolean isValidInputForDelete(TariDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTatanr()!=null && !"".equals(dao.getTatanr())) && (dao.getTaalfa()!=null && !"".equals(dao.getTaalfa())) ){
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
