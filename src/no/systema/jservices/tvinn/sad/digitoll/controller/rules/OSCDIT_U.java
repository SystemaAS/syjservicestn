package no.systema.jservices.tvinn.sad.digitoll.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.OscditDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 */
public class OSCDIT_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdateLrnMrn(OscditDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEtavd()>0 && dao.getEtpro()>0  ){ 
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
	public boolean isValidInputForUpdateLrn(OscditDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEtavd()>0 && dao.getEtpro()>0 && StringUtils.isNotEmpty(dao.getEtuuid()) && StringUtils.isNotEmpty(dao.getEtmid())  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdate(OscditDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEtavd()>0 && dao.getEtpro()>0 ){ 
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
	public boolean isValidInputForDelete(OscditDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if( dao.getEtavd()>0 && dao.getEtpro()>0 && StringUtils.isNotEmpty(dao.getEtmid()) ){ 
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
