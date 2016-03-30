package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.KodtlikMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public class KodtlikDaoServicesImpl implements KodtlikDaoServices {
	private static Logger logger = Logger.getLogger(KodtlikDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List<KodtlikDao> getList(StringBuffer errorStackTrace){
		List<KodtlikDao> retval = new ArrayList<KodtlikDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select * from kodtlik");
			retval = this.jdbcTemplate.query( sql.toString(), new KodtlikMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	
	public List<KodtlikDao> findById(StringBuffer errorStackTrace, String id){
		List<KodtlikDao> retval = new ArrayList<KodtlikDao>();
		try{
			String sql= "select * from kodtlik where klikod = ?";
			retval = this.jdbcTemplate.query( sql, new Object[] { id }, new KodtlikMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
