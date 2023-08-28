package no.systema.jservices.tvinn.sad.digitoll.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmotfDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 */
public class SADMOTF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdateLrnMrn(SadmotfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEtlnrt()>0){ 
				//OK
			} else if( StringUtils.isNotEmpty(dao.getEtuuid())){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdateLrn(SadmotfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEtlnrt()>0 && StringUtils.isNotEmpty(dao.getEtuuid()) && StringUtils.isNotEmpty(dao.getEtmid())  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdate(SadmotfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			if( dao.getEtlnrt()>= 0  ){ //Insert = 0, Update > 0
				//OK
			} else if(StringUtils.isNotEmpty(dao.getEtuuid())){ 
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
	public boolean isValidInputForDelete(SadmotfDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for lnrt and status=D
			if( dao.getEtlnrt()>0 ){ 
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
