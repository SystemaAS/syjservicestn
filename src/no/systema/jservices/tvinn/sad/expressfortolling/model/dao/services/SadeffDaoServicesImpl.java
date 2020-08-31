package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadeffDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2020
 * 
 */
public class SadeffDaoServicesImpl implements SadeffDaoServices {
	private static Logger logger = Logger.getLogger(SadeffDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadeffDao> retval = new ArrayList<SadeffDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from sadeff ");
			//sql.append(" from sadeff ");
			//sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadeffDao.class));
			
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
	 * N/A
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*TODO
		try{
			SadlDao dao = (SadlDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadl (slknr, slalfa, sltxt, sloppl, sltanr, sltn, slvekt, slpva, slsats, SLKDAÆ, SLKDSÆ, slcref, slto )");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSlknr(), dao.getSlalfa(), dao.getSltxt(),
				dao.getSloppl(), dao.getSltanr(), dao.getSltn(), dao.getSlvekt(), dao.getSlpva(), dao.getSlsats(), dao.getSlkdae(), dao.getSlkdse(), dao.getSlcref(), dao.getSlto() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			SadlDao dao = (SadlDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE sadl SET sltxt = ? , sloppl = ? , sltanr = ? , sltn = ? , slvekt = ? , ");
			sql.append(" slpva = ? , slsats = ? , SLKDAÆ = ? , SLKDSÆ = ? , slcref = ?, slto = ? ");
			//id's
			sql.append(" WHERE slknr = ? ");
			sql.append(" AND slalfa = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getSltxt(), dao.getSloppl(), dao.getSltanr(), dao.getSltn(), dao.getSlvekt(),
						dao.getSlpva(), dao.getSlsats(), dao.getSlkdae(), dao.getSlkdse(), dao.getSlcref(),
						dao.getSlto(),
						//id's
						dao.getSlknr(),
						dao.getSlalfa(),
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadl:"+writer.toString());
			e.printStackTrace();
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
		
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			SadlDao dao = (SadlDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadl ");
			//id's
			sql.append(" WHERE slknr = ? ");
			sql.append(" AND slalfa = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSlknr(), dao.getSlalfa() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
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
		sql.append(" SELECT slstat, CHAR(slknr) slknr, slalfa, SUBSTR(sltxt, 1, 20) sltxt, sloppl, CHAR(slvekt) slvekt, CHAR(sltanr) sltanr, sltar, slpva, ");
		sql.append(" CHAR(slsats) slsats, sltn, SLKDAÆ slkdae, SLKDSÆ slkdse, slto, slcref, ");
		sql.append(" SUBSTR(sltxt, 21, 1) r31, SUBSTR(sltxt, 22, 1) pref, SUBSTR(sltxt, 23, 1) mf ");
		
		
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
