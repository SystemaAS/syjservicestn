package no.systema.jservices.model.dao.services;

import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public interface EdissFtpLogDaoServices {
	public List getList(StringBuffer errorStackTrace);
	public List findById(String id, StringBuffer errorStackTrace);
	
}
