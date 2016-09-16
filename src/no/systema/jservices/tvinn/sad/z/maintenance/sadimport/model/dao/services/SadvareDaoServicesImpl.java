package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.SadvareMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadvareDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 27, 2016
 * 
 */
public class SadvareDaoServicesImpl implements SadvareDaoServices {
	private static Logger logger = Logger.getLogger(SadvareDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		return null;
	}
	/**
	 * N/A
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		return null;
	}
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(String leveid, StringBuffer errorStackTrace){
		List<SadvareDao> retval = new ArrayList<SadvareDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from sadvare ");
			sql.append(" WHERE levenr = ? ");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new Object[] { leveid  }, new SadvareMapper());
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
	public List findById (String id, String leveid, StringBuffer errorStackTrace ){
		List<SadvareDao> retval = new ArrayList<SadvareDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from sadvare ");
			sql.append(" WHERE varenr LIKE ? ");
			sql.append(" AND levenr = ?");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD, leveid  }, new SadvareMapper());
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
			SadvareDao dao = (SadvareDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadvare (varenr, varebe, levenr )");
			sql.append(" VALUES(?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getVarenr(), dao.getVarebe(), dao.getLevenr() } );
			
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
			SadvareDao dao = (SadvareDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE sadvare SET varebe = ? ");
			//id's
			sql.append(" WHERE varenr = ? ");
			sql.append(" AND levenr = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getVarebe(),
						//id's
						dao.getVarenr(),
						dao.getLevenr(),
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
			SadvareDao dao = (SadvareDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadvare ");
			//id's
			sql.append(" WHERE varenr = ? ");
			sql.append(" AND levenr = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getVarenr(), dao.getLevenr() } );
			
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
	 * @return
	 */
	private StringBuffer getSELECT_CLAUSE(){
		//Compatibility issue on special characters (ø,æ, etc)
		//All columns with special characters (NO,SE,DK) such as ö,ä,ø, etc MUST be defined with CAPITAL LETTERS, otherwise the selection in SQL will be invalid
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT varenr, varebe, levenr, w2vf, w2lk,	w2vnti, w2tn, w2pre, w2pva, w2as, w2mfr ");
		return sql;
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
