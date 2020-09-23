package no.systema.jservices.tvinn.sad.expressfortolling.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefdefDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 */
public class SADEFDEFR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	/*
	public boolean isValidInput(SadefdefDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao
			
			if( StringUtils.isNotEmpty(dao.getEfuuid())){ /* && !"".equals(dao.getKvakod())) &&
				(dao.getKvakrs()!=null && !"".equals(dao.getKvakrs())) && 
				(dao.getKvaomr()!=null && !"".equals(dao.getKvaomr())) && 
				(dao.getKvadt()!=null && !"".equals(dao.getKvadt())) ){
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	*/
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	/*
	public boolean isValidInputForDelete(SadefdefDao dao, String user, String mode){
		boolean retval = true;
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode )){
			//check dao for uuid and status=D
			if(StringUtils.isNotEmpty(dao.getEfuuid()) && StringUtils.isNotEmpty(dao.getEfst())){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		
		return retval;
	}
	*/
}
