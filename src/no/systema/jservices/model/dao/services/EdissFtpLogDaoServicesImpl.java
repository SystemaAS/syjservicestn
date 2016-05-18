package no.systema.jservices.model.dao.services;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.model.dao.entities.EdissDao;
import no.systema.jservices.model.dao.mapper.EdissMapper;
import no.systema.main.util.DbErrorMessageManager;
/**
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public class EdissFtpLogDaoServicesImpl implements EdissFtpLogDaoServices {
	private static Logger logger = Logger.getLogger(EdissFtpLogDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		
		List<EdissDao> retval = new ArrayList<EdissDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT sssn, ssdt, sstm, ssst, sstext ");
			sql.append(" from ediss ");
			sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new EdissMapper());
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
	 */
	public List findById(String id, StringBuffer errorStackTrace){
		List<EdissDao> retval = new ArrayList<EdissDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT sssn, ssdt, sstm, ssst, sstext ");
			sql.append(" from ediss ");
			sql.append(" where sssn = ? ");
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] {  id  },new EdissMapper());
			
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
