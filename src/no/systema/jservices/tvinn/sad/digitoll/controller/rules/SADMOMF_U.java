package no.systema.jservices.tvinn.sad.digitoll.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmotfDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 */
public class SADMOMF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdateLrnMrn(SadmomfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEmlnrt()>0 && dao.getEmlnrm()>0  ){ 
				//OK
			} else if( StringUtils.isNotEmpty(dao.getEmuuid())){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdateLrn(SadmomfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEmlnrt()>0 && dao.getEmlnrm()>0 && StringUtils.isNotEmpty(dao.getEmuuid()) && StringUtils.isNotEmpty(dao.getEmmid())  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdate(SadmomfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEmlnrt()>0 && dao.getEmlnrm()>=0 ){ 
				//OK
			} else if(StringUtils.isNotEmpty(dao.getEmuuid())){ 
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
	public boolean isValidInputForDelete(SadmomfDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if( (dao.getEmlnrt()>0 && dao.getEmlnrm()>0) || StringUtils.isNotEmpty(dao.getEmmid()) ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		
		return retval;
	}
	
	public boolean isValidInputForChangeTransport(SadmomfDao dao, String user, String mode, String toEmlnrt){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEmlnrt()>0 && dao.getEmlnrm()>=0 && StringUtils.isNotEmpty(toEmlnrt) ){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdateExernalMaster(SadmomfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEmlnrt()>0){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdateMrnBup(SadmomfDao dao, String user){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user)){
			//check dao
			if(StringUtils.isNotEmpty(dao.getEmmid())  ){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdateRequestIdBup(SadmomfDao dao, String user){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user)){
			//check dao
			if(StringUtils.isNotEmpty(dao.getEmuuid())  ){ 
				//OK
			} else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
}
