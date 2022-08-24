package no.systema.jservices.tvinn.sad.expressfortolling2.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexmfDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 */
public class SADEXMF_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForUpdateLrnMrn(SadexmfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEmavd()>0 && dao.getEmpro()>0  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdateLrn(SadexmfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( dao.getEmavd()>0 && dao.getEmpro()>0 && StringUtils.isNotEmpty(dao.getEmuuid()) && StringUtils.isNotEmpty(dao.getEmmid())  ){ 
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	public boolean isValidInputForUpdate(SadexmfDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode)){
			//check dao
			
			if( dao.getEmavd()>0 && dao.getEmpro()>0 ){ 
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
	public boolean isValidInputForDelete(SadexmfDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if( dao.getEmavd()>0 && dao.getEmpro()>0 && StringUtils.isNotEmpty(dao.getEmmid()) ){ 
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
