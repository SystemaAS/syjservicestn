package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.KodtlbMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlbDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 */
public class KodtlbDaoServicesImpl implements KodtlbDaoServices {
	private static Logger logger = Logger.getLogger(KodtlbDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<KodtlbDao> retval = new ArrayList<KodtlbDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" SELECT klbsta, klbuni, klbkod, klbkt, klbnav, klbfok, CHAR(klbprm) klbprm, klbfrk, klbxxx ");
			sql.append(" FROM kodtlb");
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new KodtlbMapper());
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
		List<KodtlbDao> retval = new ArrayList<KodtlbDao>();
		try{
			String sql= "select * from kodtlb where klbkod = ?";
			retval = this.jdbcTemplate.query( sql, new Object[] { id }, new KodtlbMapper());
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
			KodtlbDao dao = (KodtlbDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtlb (klbsta, klbuni, klbkod, klbkt, klbnav, klbfok, klbprm, klbfrk, klbxxx) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKlbsta(), dao.getKlbuni(), dao.getKlbkod(), dao.getKlbkt(), 
					dao.getKlbnav(), dao.getKlbfok(), dao.getKlbprm(), dao.getKlbfrk(), dao.getKlbxxx() } );
			
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
			
			KodtlbDao dao = (KodtlbDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE kodtlb SET klbkt=?, klbnav = ?, klbfok = ?, klbprm = ?, klbfrk = ?, klbxxx = ? ");
			sql.append(" WHERE klbkod = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKlbkt(), dao.getKlbnav(), dao.getKlbfok(), dao.getKlbprm(), dao.getKlbfrk(), dao.getKlbxxx() } );
			
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
			KodtlbDao dao = (KodtlbDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtlb ");
			sql.append(" WHERE klbkod = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKlbkod() } );
			
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
