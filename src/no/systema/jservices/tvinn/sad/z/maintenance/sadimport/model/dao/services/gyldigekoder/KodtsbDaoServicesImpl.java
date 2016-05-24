package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder.KodtsbMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsbDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 *  
 */
public class KodtsbDaoServicesImpl implements KodtsbDaoServices {
	private static Logger logger = Logger.getLogger(KodtsbDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtsbDao> retval = new ArrayList<KodtsbDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ksbkd, ksbft ");
			sql.append(" from kodtsb ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new KodtsbMapper());
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
		List<KodtsbDao> retval = new ArrayList<KodtsbDao>();
		//logger.info(avgId + "XXX" + sekvId);
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT ksbkd, ksbft ");
			sql.append(" from kodtsb ");
			sql.append(" WHERE ksbkd = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtsbMapper());
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
			KodtsbDao dao = (KodtsbDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtsb ( ksbkd, ksbft )");
			sql.append(" VALUES ( ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getKsbkd(), dao.getKsbft()
				
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
			KodtsbDao dao = (KodtsbDao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodtsb SET ksbft = ? ");
			//id's
			sql.append(" WHERE ksbkd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKsbft(), 
						//id's
						dao.getKsbkd(),
						
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
			KodtsbDao dao = (KodtsbDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtsb ");
			//id's
			sql.append(" WHERE ksbkd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKsbkd() } );
			
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
