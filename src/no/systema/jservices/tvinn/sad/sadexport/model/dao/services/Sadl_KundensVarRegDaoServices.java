package no.systema.jservices.tvinn.sad.sadexport.model.dao.services;

import no.systema.jservices.tvinn.sad.sadexport.model.dao.entities.SadlDao;

/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public interface Sadl_KundensVarRegDaoServices {
	public int insertIntoSadl(SadlDao sadlDao, StringBuffer errorStackTrace);
	
}
