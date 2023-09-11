package no.systema.jservices.tvinn.sad.digitoll.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmohfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmoifDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 */
public class SADMOIF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdate(SadmoifDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEilnrt()>0 && dao.getEilnrm()>0 && dao.getEilnrh()>0  ){ 
				//OK
			} else if( dao.getEili() > 0){ 
				//OK
			} else{
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
	public boolean isValidInputForDelete(SadmoifDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if( dao.getEilnrt()>0 && dao.getEilnrm()>0 && dao.getEilnrh()>0 && dao.getEili() >0  ){ 
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
