package no.systema.jservices.model.dao.services;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.model.dao.entities.EdisssDao;
import no.systema.jservices.model.dao.mapper.EdisssMapper;
import no.systema.main.util.DbErrorMessageManager;
/**
 * 
 * @author oscardelatorre
 * @date May 19, 2016
 * 
 */
public class EdisssFtpLogDaoServicesImpl implements EdisssFtpLogDaoServices {
	private static Logger logger = Logger.getLogger(EdisssFtpLogDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		
		List<EdisssDao> retval = new ArrayList<EdisssDao>();
		//N/A
		return retval;
		
	}
	/**
	 * 
	 */
	public List findById(String id, String date, String time, StringBuffer errorStackTrace){
		List<EdisssDao> retval = new ArrayList<EdisssDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ssssn, sssdt, ssstm, sssdata ");
			sql.append(" from edisss ");
			sql.append(" where ssssn = ? ");
			sql.append(" and sssdt = ? ");
			sql.append(" and ssstm = ? ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] {  id, date, time  },new EdisssMapper());
			
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
