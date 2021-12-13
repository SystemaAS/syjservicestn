package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.KodttsMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodttsDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jun 20, 2016
 * 
 */
public class KodttsDaoServicesImpl implements KodttsDaoServices {
	private static Logger logger = LogManager.getLogger(KodttsDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 * @param avd
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodttsDao> retval = new ArrayList<KodttsDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			
			sql.append(" SELECT a.ktsuni ktsuni, a.ktskod ktskod, a.ktspnr ktspnr , a.ktsnav ktsnav, b.ktxpnr ktxpnr, b.ktxkod ktxkod");
			sql.append(" FROM kodttst a, kodttsx b");
			sql.append(" WHERE a.ktspnr = b.ktxkod ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new KodttsMapper());
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
		List<KodttsDao> retval = new ArrayList<KodttsDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			
			sql.append(" SELECT a.ktsuni ktsuni, a.ktskod ktskod, a.ktspnr ktspnr , a.ktsnav ktsnav, b.ktxpnr ktxpnr, b.ktxkod ktxkod");
			sql.append(" FROM kodttst a, kodttsx b");
			sql.append(" WHERE a.ktspnr = ? ");
			sql.append(" AND a.ktspnr = b.ktxkod ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new KodttsMapper());
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
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	public List findByAlfa (String id, StringBuffer errorStackTrace ){
		List<KodttsDao> retval = new ArrayList<KodttsDao>();
		String SQL_WILD_CARD = "%";
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			
			sql.append(" SELECT a.ktsuni ktsuni, a.ktskod ktskod, a.ktspnr ktspnr , a.ktsnav ktsnav, b.ktxpnr ktxpnr, b.ktxkod ktxkod");
			sql.append(" FROM kodttst a, kodttsx b");
			sql.append(" WHERE a.ktspnr LIKE ? ");
			sql.append(" AND a.ktspnr = b.ktxkod ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD  }, new KodttsMapper());
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
			KodttsDao dao = (KodttsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO kodttst ( ktsuni, ktspnr, ktsnav ) ");
			sql.append(" VALUES ( ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKtsuni(), dao.getKtspnr(), dao.getKtsnav() } );
			if(retval>=0){
				this.insertChild(daoObj, errorStackTrace);
			}
			
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
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	private int insertChild(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			KodttsDao dao = (KodttsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO kodttsx ( ktxkod, ktxpnr ) ");
			sql.append(" VALUES ( ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] {  dao.getKtspnr(), dao.getKtxpnr() } );
			
			
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
			
			KodttsDao dao = (KodttsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodttst SET ktsnav=? ");
			sql.append(" WHERE ktspnr = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKtsnav(), dao.getKtspnr() } );

			if(retval>=0){
				this.updateChild(daoObj, errorStackTrace);
			}
			
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
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	private int updateChild(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			
			KodttsDao dao = (KodttsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodttsx SET ktxpnr=? ");
			sql.append(" WHERE ktxkod = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKtxpnr(), dao.getKtspnr() } );
			
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
			KodttsDao dao = (KodttsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodttst WHERE ktspnr = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKtspnr() } );
			if(retval>=0){
				this.deleteChild(daoObj, errorStackTrace);
			}
			
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
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	private int deleteChild(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			KodttsDao dao = (KodttsDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodttsx WHERE ktxkod = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKtspnr() } );
			
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
