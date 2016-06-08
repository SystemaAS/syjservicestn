package no.systema.jservices.tvinn.sad.z.maintenance.main.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.main.model.dao.entities.KodtvaDao;
/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 */
public class SYFT02R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(KodtvaDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKvakod()!=null && !"".equals(dao.getKvakod())) &&
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
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDelete(KodtvaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getKvakod()!=null && !"".equals(dao.getKvakod())) && (dao.getKvadt()!=null && !"".equals(dao.getKvadt()))  ){
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
