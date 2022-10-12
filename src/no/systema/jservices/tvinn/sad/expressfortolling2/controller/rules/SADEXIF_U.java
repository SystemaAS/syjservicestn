package no.systema.jservices.tvinn.sad.expressfortolling2.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexifDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 */
public class SADEXIF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdate(SadexifDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEiavd()>=0 && dao.getEipro()>0 && dao.getEitdn()>0 && dao.getEili()>0){ 
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
	public boolean isValidInputForDelete(SadexifDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for ehmid and status=D
			if( dao.getEiavd()>=0 && dao.getEipro()>0 && dao.getEitdn()>0 && dao.getEili()>0){ 
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
