package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodttsDao;
/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 */
public class SYFT04R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodttsDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKtspnr()!=null && !"".equals(dao.getKtspnr())) ){
				//At least one of 3 values must exist
				if(  (dao.getKtsnav()!=null && !"".equals(dao.getKtsnav()))	 ){
	  					 //OK = do nothing
	  				}else{
	  					retval = false;
	  				}
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	
	public boolean isValidInputForDelete(KodttsDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKtspnr()!=null && !"".equals(dao.getKtspnr()) && dao.getKtsnav()!=null && !"".equals(dao.getKtsnav()) ){
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
