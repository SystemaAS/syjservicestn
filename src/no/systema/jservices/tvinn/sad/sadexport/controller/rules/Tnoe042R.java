package no.systema.jservices.tvinn.sad.sadexport.controller.rules;
import no.systema.jservices.tvinn.sad.sadexport.model.dao.entities.SadlDao;
/**
 * 
 * @author oscardelatorre
 * @date Feb 12, 2016
 */
public class Tnoe042R {

	/**
	 * 
	 * @param userName
	 * @param sadlDao
	 * @return
	 */
	public boolean validInput(String userName, SadlDao sadlDao){
		boolean retval = false;
		if(userName!=null && !"".equals(userName)){
			if(  (sadlDao.getSlknr()!=null && !"".equals(sadlDao.getSlknr())) &&
				 (sadlDao.getSlalfa()!=null && !"".equals(sadlDao.getSlalfa())) && 
				 (sadlDao.getSltanr()!=null && !"".equals(sadlDao.getSltanr())) ){
				retval = true;		
			}
			
		}
		
		return retval;
		
	}
}
