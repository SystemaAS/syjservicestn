package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadhHeadfDao;
/**
 * 
 * @author oscardelatorre
 * @date Jun 17, 2016
 */
public class SAD006R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(SadhHeadfDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) &&  (dao.getSitdn()!=null && !"".equals(dao.getSitdn())) ){
				//At least one of 3 values must exist
				if(  (dao.getSitll()!=null && !"".equals(dao.getSitll())) ||
					 (dao.getSitle()!=null && !"".equals(dao.getSitle())) ||
  					 (dao.getSidtg()!=null && !"".equals(dao.getSidtg()))		 ){
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
	
}
