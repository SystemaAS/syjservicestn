package no.systema.jservices.tvinn.sad.expressfortolling.controller.rules;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.systema.jservices.tvinn.sad.expressfortolling.controller.JsonResponseOutputterController_SADEFCMF;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefcmfDao;

/**
 * 
 * @author oscardelatorre
 * @date Apr 2022
 */
public class SADEFCMF_U {
	private static Logger logger = LoggerFactory.getLogger(SADEFCMF_U.class.getName());
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
			logger.warn("A");
			if( dao.getCmavd()>0 && dao.getCmavd()>0 && dao.getCmtdn()>0 && StringUtils.isNotEmpty(dao.getCmeid()) ){ 
				//OK
				logger.warn("B");
			}else{
				logger.warn("C");
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
			if(dao.getCmli()>0 && dao.getCmavd()>0 && dao.getCmtdn()>0){
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
