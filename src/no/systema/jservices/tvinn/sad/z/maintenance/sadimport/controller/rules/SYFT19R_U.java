package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;
/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2016
 */
public class SYFT19R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtlikDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKlikod()!=null && !"".equals(dao.getKlikod())) &&
				(dao.getKlinav()!=null && !"".equals(dao.getKlinav())) && 
				(dao.getKlisto()!=null && !"".equals(dao.getKlisto())) ){
				//OK and go on 
				if("J".equals(dao.getKlisto()) || "N".equals(dao.getKlisto()) ){
					//OK
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
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(KodtlikDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( dao.getKlikod()!=null && !"".equals(dao.getKlikod()) ){
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
