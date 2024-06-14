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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.ZadmomlfDao;

import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2024
 * 
 */
public class ZadmomlfDaoServicesImpl implements ZadmomlfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(ZadmomlfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private String SQL_WILD_CARD = "%";
	private String DB_TABLE = "zadmomlf";
	
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
		ZadmomlfDao dao = (ZadmomlfDao)obj;
		List<ZadmomlfDao> retval = new ArrayList<ZadmomlfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where emdkm LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			
			if(StringUtils.isNotEmpty(dao.getEmdkm())){ sql.append(" and emdkm = ? "); params.add(dao.getEmdkm()); } 
			if(StringUtils.isNotEmpty(dao.getAvsid())){ sql.append(" and avsid = ? "); params.add(dao.getAvsid()); } 
			if(StringUtils.isNotEmpty(dao.getMotid())){ sql.append(" and motid = ? "); params.add(dao.getMotid()); } 
			if(dao.getDate()>0){ sql.append(" and date >= ? "); params.add(dao.getDate()); }
			if(dao.getTime()>0){ sql.append(" and time >= ? "); params.add(dao.getTime()); }
			if(StringUtils.isNotEmpty(dao.getTrreforg())){ sql.append(" and trreforg = ? "); params.add(dao.getTrreforg()); } 
			if(StringUtils.isNotEmpty(dao.getTrrefreg())){ sql.append(" and trrefreg = ? "); params.add(dao.getTrrefreg()); } 
			
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(ZadmomlfDao.class));
			
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
			ZadmomlfDao dao = (ZadmomlfDao)daoObj;
			//date-time
			if(dao.getDate()>0) {
				dao.setDate(Integer.valueOf(dateTimeMgr.getCurrentDate_ISO()));
			}
			if(dao.getTime()>0) {
				dao.setTime(Integer.valueOf(dateTimeMgr.getCurrentTimeHHmmss()));
			}
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + DB_TABLE + "  ( emdkm, emdkmt, empro, date, time, trreforg, trrefreg, avsna, avsid, motna, motid ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getEmdkm(), dao.getEmdkmt(), dao.getEmpro(), dao.getDate(), dao.getTime(), dao.getTrreforg(), dao.getTrrefreg(), 
					dao.getAvsna(), dao.getAvsid(), dao.getMotna(), dao.getMotid()
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
	public int insertLight(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			ZadmomlfDao dao = (ZadmomlfDao)daoObj;
			//date-time
			if(dao.getDate()>0) {
				dao.setDate(Integer.valueOf(dateTimeMgr.getCurrentDate_ISO()));
			}
			if(dao.getTime()>0) {
				dao.setTime(Integer.valueOf(dateTimeMgr.getCurrentTimeHHmmss()));
			}
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + DB_TABLE + "  ( emdkm, emdkmt ) ");
			sql.append(" VALUES(?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getEmdkm(), dao.getEmdkmt()
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
			ZadmomlfDao dao = (ZadmomlfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE FROM " + DB_TABLE + "  WHERE emdkm = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getEmdkm()
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
