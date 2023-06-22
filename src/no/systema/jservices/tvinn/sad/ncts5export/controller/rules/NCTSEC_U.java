package no.systema.jservices.tvinn.sad.ncts5export.controller.rules;

import org.apache.commons.lang3.StringUtils;
import no.systema.jservices.tvinn.sad.ncts5export.model.dao.entities.NctsecDao;

/**
 * 
 * @author oscardelatorre
 * @date Jun 2023
 */
public class NCTSEC_U {
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForInsert(NctsecDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			if(dao.getTcavd()>0 && dao.getTctdn()>0){ 
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdate(NctsecDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			if(dao.getTcavd()>0 && dao.getTctdn()>0 && dao.getTcli()>0){ 
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	
	
}
