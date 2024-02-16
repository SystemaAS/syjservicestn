package no.systema.jservices.tvinn.sad.sadimport.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.sadimport.model.dao.entities.SadImpDigDao;

/**
 * 
 * @author oscardelatorre
 * @date Feb 2024
 */
public class SADIMPDIG_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValid(SadImpDigDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			
		}else{
			retval = false;
		}
		
		return retval;
	}
}
