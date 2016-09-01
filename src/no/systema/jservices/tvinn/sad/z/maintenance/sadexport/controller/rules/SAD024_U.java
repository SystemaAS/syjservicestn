package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SaehDao;
/**
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 */
public class SAD024_U {


	public static boolean isValidInput(SaehDao dao, String user, String mode){
		boolean retval = true;
		
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ((dao.getSeavd() != null && !"".equals(dao.getSeavd()))
					&& (dao.getSetdn() != null && !"".equals(dao.getSetdn()))) {
				// At least one of 3 values must exist
				if ((dao.getSetll() != null && !"".equals(dao.getSetll()))
						|| (dao.getSetle() != null && !"".equals(dao.getSetle()))
						|| (dao.getSedtg() != null && !"".equals(dao.getSedtg()))) {
					// OK = do nothing
				} else {
					retval = false;
				}
			} else {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}
	
}
