package no.systema.jservices.model.dao.services;

import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date May 19, 2016
 * 
 */
public interface EdisssFtpLogDaoServices {
	public List getList(StringBuffer errorStackTrace);
	public List findById(String id, String date, String time, StringBuffer errorStackTrace);
	
}
