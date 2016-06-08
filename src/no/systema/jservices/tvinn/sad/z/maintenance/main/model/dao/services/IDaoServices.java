package no.systema.jservices.tvinn.sad.z.maintenance.main.model.dao.services;

import java.util.List;

/**
 * Grandfather for all normal Dao services
 * @author oscardelatorre
 * @date Jun 1, 2016
 */
public interface IDaoServices {
	public List getList(StringBuffer errorStackTrace);
	public List findById(String id, StringBuffer errorStackTrace);
	//DMLs
	public int insert(Object dao, StringBuffer errorStackTrace);
	public int update(Object dao, StringBuffer errorStackTrace);
	public int delete(Object dao, StringBuffer errorStackTrace);
}
