package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.SadhMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadhHeadfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jun 17, 2016
 * 
 */
public class SadhDaoServicesImpl implements SadhDaoServices {
	private static Logger logger = LogManager.getLogger(SadhDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * 
	 * @param avd
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadhHeadfDao> retval = new ArrayList<SadhHeadfDao>();
		//N/A
		return retval;
	}
	
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List<SadhHeadfDao> retval = new ArrayList<SadhHeadfDao>();
		//N/A
		return retval;
	}
	/**
	 * 
	 */
	public List getListByAvd(String avd, StringBuffer errorStackTrace){
		List<SadhHeadfDao> retval = new ArrayList<SadhHeadfDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" SELECT siavd, sitdn, sitll, sitle, sidtg, sinak ");
			sql.append(" FROM sadh");
			sql.append(" WHERE siavd = ?");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd }, new SadhMapper());
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
	 * @param avd
	 * @param opd
	 * @param errorStackTrace
	 * @return
	 */
	public List getListByAvdOpd(String avd, String opd, StringBuffer errorStackTrace){
		List<SadhHeadfDao> retval = new ArrayList<SadhHeadfDao>();
		String SQL_WILD_CARD = "%";
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" SELECT siavd, sitdn, sitll, sitle, sidtg, sinak ");
			sql.append(" FROM sadh");
			sql.append(" WHERE siavd = ?");
			sql.append(" AND sitdn LIKE ?");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd + SQL_WILD_CARD }, new SadhMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	public List findForUpdate(String avd, String opd, StringBuffer errorStackTrace){
		List<SadhHeadfDao> retval = new ArrayList<SadhHeadfDao>();
		try{
			StringBuffer sql= new StringBuffer();
			sql.append("SELECT siavd, sitdn, sitll, sitle, sidtg, sinak ");
			sql.append(" FROM sadh ");
			sql.append(" WHERE siavd = ? ");
			sql.append(" AND sitdn = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd }, new SadhMapper());
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
		//N/A
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		
		int retval = 0;
		try{
			
			SadhHeadfDao dao = (SadhHeadfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadh SET sitll=?, sitle = ?, sidtg = ? ");
			sql.append(" WHERE siavd = ? ");
			sql.append(" AND sitdn = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getSitll(), dao.getSitle(), dao.getSidtg(),
				//id
				dao.getSiavd(), dao.getSitdn() } );
			
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
		//N/A
		
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
