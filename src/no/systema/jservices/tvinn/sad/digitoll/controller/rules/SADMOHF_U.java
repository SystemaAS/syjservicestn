package no.systema.jservices.tvinn.sad.digitoll.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmohfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 */
public class SADMOHF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdateLrnMrn(SadmohfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEhlnrt()>0 && dao.getEhlnrm()>0 && dao.getEhlnrh()>0  ){ 
				//OK
			} else if( StringUtils.isNotEmpty(dao.getEhuuid())){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdateLrn(SadmohfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEhlnrt()>0 && dao.getEhlnrm()>0 && dao.getEhlnrh()>0  && StringUtils.isNotEmpty(dao.getEhuuid()) && StringUtils.isNotEmpty(dao.getEhmid())  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdate(SadmohfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEhlnrt()>0 && dao.getEhlnrm()>0 && dao.getEhlnrh()>=0){ 
				//OK
			} else if(StringUtils.isNotEmpty(dao.getEhuuid())){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdateMrnBup(SadmohfDao dao, String user){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user)){
			//check dao
			if( StringUtils.isNotEmpty(dao.getEhmid())  ){ 
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
	public boolean isValidInputForDelete(SadmohfDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if( (dao.getEhlnrt()>0 && dao.getEhlnrm()>0 && dao.getEhlnrh()>0) || StringUtils.isNotEmpty(dao.getEhmid()) ){ 
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
