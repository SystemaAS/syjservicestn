package no.systema.jservices.tvinn.sad.expressfortolling2.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexhfDao;
import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexmfDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 */
public class SADEXHF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdateLrnMrn(SadexhfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEhavd()>=0 && dao.getEhpro()>=0 && dao.getEhtdn()>0 ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdateLrn(SadexhfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEhavd()>=0 && dao.getEhpro()>=0 && dao.getEhtdn()>0 && StringUtils.isNotEmpty(dao.getEhuuid()) && StringUtils.isNotEmpty(dao.getEhmid())  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForUpdate(SadexhfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEhavd()>=0 && dao.getEhpro()>=0 && dao.getEhtdn()>0 ){ 
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
	public boolean isValidInputForDelete(SadexhfDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for ehmid and status=D
			if( StringUtils.isNotEmpty(dao.getEhmid()) ) { 
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
