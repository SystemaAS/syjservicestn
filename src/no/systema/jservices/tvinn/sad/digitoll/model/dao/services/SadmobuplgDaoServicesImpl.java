package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.common.util.DateTimeManager;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmobuplgDao;

import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2024
 * 
 */
public class SadmobuplgDaoServicesImpl implements SadmobuplgDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmobuplgDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private String SQL_WILD_CARD = "%";
	private String DB_TABLE = "sadmobuplg";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		//NA
		return null;
	}
	/**
	 * get a data set with where clause
	 */
	public List find(Object obj,StringBuffer errorStackTrace){
		SadmobuplgDao dao = (SadmobuplgDao)obj;
		List<SadmobuplgDao> retval = new ArrayList<SadmobuplgDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where file LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			
			if(StringUtils.isNotEmpty(dao.getDate())){ sql.append(" and date = ? "); params.add(dao.getDate()); } 
			if(StringUtils.isNotEmpty(dao.getTime())){ sql.append(" and time = ? "); params.add(dao.getTime()); } 
			if(StringUtils.isNotEmpty(dao.getMsgid())){ sql.append(" and msgid = ? "); params.add(dao.getMsgid()); } 
			if(StringUtils.isNotEmpty(dao.getPeppolid())){ sql.append(" and peppolid = ? "); params.add(dao.getPeppolid()); } 
			
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmobuplgDao.class));
			
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
		List<SadmobuplgDao> retval = new ArrayList<SadmobuplgDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where file LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			if(StringUtils.isNotEmpty(id)){ sql.append(" and file = ? "); params.add(id); } 
			
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmobuplgDao.class));
			
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
			SadmobuplgDao dao = (SadmobuplgDao)daoObj;
			//date-time
			if(StringUtils.isEmpty(dao.getDate())) {
				dao.setDate(dateTimeMgr.getCurrentDate_ISO());
			}
			if(StringUtils.isEmpty(dao.getTime())) {
				dao.setTime(dateTimeMgr.getCurrentTimeHHmmss());
			}
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + DB_TABLE + "  ( file, date, time, msgid, peppolid ) ");
			sql.append(" VALUES(?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getFile(), dao.getDate(), dao.getTime(), dao.getMsgid(), dao.getPeppolid()
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
			SadmobuplgDao dao = (SadmobuplgDao)daoObj;
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE " + DB_TABLE + " set msgid = ?, peppolid = ? ");
			sql.append(" WHERE file = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getMsgid(), dao.getPeppolid(),
					//id
					dao.getFile()
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
			SadmobuplgDao dao = (SadmobuplgDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE FROM " + DB_TABLE + "  WHERE file = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getFile()
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
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
