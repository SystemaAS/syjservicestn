package no.systema.jservices.tvinn.sad.expressfortolling.controller.rules;

import org.apache.commons.lang3.StringUtils;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefcmfDao;

/**
 * 
 * @author oscardelatorre
 * @date Apr 2022
 */
public class SADEFCMF_U {
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadefcmfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			if(dao.getCmavd()>0 && dao.getCmtdn()>0){ 
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
	public boolean isValidInputForDelete(SadefcmfDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if(dao.getCmli()>0){
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
