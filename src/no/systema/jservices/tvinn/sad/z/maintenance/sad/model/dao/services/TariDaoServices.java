package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date May 6, 2016
 * 
 */
public interface TariDaoServices extends IDaoServices { 
	public List findByIdExactMatch(String id, StringBuffer errorStackTrace);
	public List findByAlfa(String alfa, StringBuffer errorStackTrace);
	public List findForUpdate(String id, String alfa, String txt, StringBuffer errorStackTrace);
	public List findByText(String tatxt, StringBuffer dbErrorStackTrace);
	
}
