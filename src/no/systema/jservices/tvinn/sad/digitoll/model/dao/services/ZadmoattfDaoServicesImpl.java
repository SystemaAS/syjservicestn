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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.ZadmoattfDao;

import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Mar 2025
 * 
 */
public class ZadmoattfDaoServicesImpl implements ZadmoattfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(ZadmoattfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private String SQL_WILD_CARD = "%";
	private String DB_TABLE = "zadmoattf";
	
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
		ZadmoattfDao dao = (ZadmoattfDao)obj;
		List<ZadmoattfDao> retval = new ArrayList<ZadmoattfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where emdkm LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			
			if(StringUtils.isNotEmpty(dao.getId())){ sql.append(" and id = ? "); params.add(dao.getId()); } 
			if(StringUtils.isNotEmpty(dao.getAvsid())){ sql.append(" and avsid = ? "); params.add(dao.getAvsid()); } 
			if(StringUtils.isNotEmpty(dao.getMotid())){ sql.append(" and motid = ? "); params.add(dao.getMotid()); } 
			if(dao.getDate()>0){ sql.append(" and date >= ? "); params.add(dao.getDate()); }
			if(dao.getTime()>0){ sql.append(" and time >= ? "); params.add(dao.getTime()); }
			if(StringUtils.isNotEmpty(dao.getDocname())){ sql.append(" and docname = ? "); params.add(dao.getDocname()); } 
			if(StringUtils.isNotEmpty(dao.getTypref())){ sql.append(" and typref = ? "); params.add(dao.getTypref()); } 
			
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(ZadmoattfDao.class));
			
			
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
		//NA
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
		
		try{
			ZadmoattfDao dao = (ZadmoattfDao)daoObj;
			//date-time
			if(dao.getDate()<=0) {
				dao.setDate(Integer.valueOf(dateTimeMgr.getCurrentDate_ISO()));
			}
			if(dao.getTime()<=0) {
				dao.setTime(Integer.valueOf(dateTimeMgr.getCurrentTimeHHmmss()));
			}
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + DB_TABLE + "  ( id, date, time, avsid, motid, docname, typref, docref ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getId(), dao.getDate(), dao.getTime(), dao.getAvsid(), dao.getMotid(), dao.getDocname(), dao.getTypref(), dao.getDocref() 
					
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
		//NA
		return retval;
		
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			ZadmoattfDao dao = (ZadmoattfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE FROM " + DB_TABLE + "  WHERE id = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getId()
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
