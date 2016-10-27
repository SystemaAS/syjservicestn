package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.gyldigekoder;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.gyldigekoder.KodtsoDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.mapper.gyldigekoder.KodtsoMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 *  
 */
public class KodtsoDaoServicesImpl implements KodtsoDaoServices {
	private static Logger logger = Logger.getLogger(KodtsoDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtsoDao> retval = new ArrayList<KodtsoDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ksokd, ksoft ");
			sql.append(" from kodtso ");
			sql.append(" order by ksokd ");
			
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new KodtsoMapper());
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
		List<KodtsoDao> retval = new ArrayList<KodtsoDao>();
		//logger.info(avgId + "XXX" + sekvId);
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT ksokd, ksoft ");
			sql.append(" from kodtso ");
			sql.append(" WHERE ksokd = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodtsoMapper());
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
			KodtsoDao dao = (KodtsoDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtso ( ksokd, ksoft )");
			sql.append(" VALUES ( ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getKsokd(), dao.getKsoft()
				
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
			KodtsoDao dao = (KodtsoDao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodtso SET ksoft = ? ");
			//id's
			sql.append(" WHERE ksokd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKsoft(), 
						//id's
						dao.getKsokd(),
						
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
			KodtsoDao dao = (KodtsoDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtso ");
			//id's
			sql.append(" WHERE ksokd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKsokd() } );
			
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
