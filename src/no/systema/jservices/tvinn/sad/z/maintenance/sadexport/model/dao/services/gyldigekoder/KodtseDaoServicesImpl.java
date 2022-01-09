package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.KodtseDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.gyldigekoder.KodtseMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 *  
 */
public class KodtseDaoServicesImpl implements KodtseDaoServices {
	private static Logger logger = LoggerFactory.getLogger(KodtseDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtseDao> retval = new ArrayList<KodtseDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ksefyl, ksetxt ");
			sql.append(" from kodtse ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new KodtseMapper());
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
	public List findById (String id, StringBuffer errorStackTrace ){
		List<KodtseDao> retval = new ArrayList<KodtseDao>();
		//logger.info(avgId + "XXX" + sekvId);
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT ksefyl, ksetxt ");
			sql.append(" from kodtse ");
			sql.append(" WHERE ksefyl = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtseMapper());
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
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtseDao dao = (KodtseDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtse ( ksefyl, ksetxt )");
			sql.append(" VALUES ( ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getKsefyl(), dao.getKsetxt()
				
				} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtseDao dao = (KodtseDao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodtse SET ksetxt = ? ");
			//id's
			sql.append(" WHERE ksefyl = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKsetxt(), 
						//id's
						dao.getKsefyl(),
						
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			KodtseDao dao = (KodtseDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtse ");
			//id's
			sql.append(" WHERE ksefyl = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKsefyl() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
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
