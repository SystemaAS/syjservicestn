package no.systema.jservices.tvinn.sad.expressfortolling.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadeffDao;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 */
public class SADEFFR_U {
	private StringUtils strMgr = new StringUtils();
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadeffDao dao, String user, String mode){
		boolean retval = true;
		
		if( strMgr.isNotEmpty(user) && strMgr.isNotEmpty(mode )){
			//check dao
			
			if( strMgr.isNotEmpty(dao.getEfuuid())){ /* && !"".equals(dao.getKvakod())) &&
				(dao.getKvakrs()!=null && !"".equals(dao.getKvakrs())) && 
				(dao.getKvaomr()!=null && !"".equals(dao.getKvaomr())) && 
				(dao.getKvadt()!=null && !"".equals(dao.getKvadt())) ){*/
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
	public boolean isValidInputForDelete(SadeffDao dao, String user, String mode){
		boolean retval = true;
		if( strMgr.isNotEmpty(user) && strMgr.isNotEmpty(mode )){
			//check dao
			if(strMgr.isNotEmpty(dao.getEfuuid())){
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
