package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtsiDao;
/**
 * 
 * @author oscardelatorre
 * @date Apr 9, 2016
 */
public class SYFT10R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtsiDao dao, String user, String mode){
		boolean retval = true;
		
		/*if( (user!=null && !"".equals(user)) &&
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
		}*/
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(KodtsiDao dao, String user, String mode){
		boolean retval = true;
		/*
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
		*/
		return retval;
	}
}
