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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmolffDao;

import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2024
 * 
 */
public class SadmolffDaoServicesImpl implements SadmolffDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmolffDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private String SQL_WILD_CARD = "%";
	private String DB_TABLE = "sadmolff";
	
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
		SadmolffDao dao = (SadmolffDao)obj;
		List<SadmolffDao> retval = new ArrayList<SadmolffDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where uuid LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			
			if(StringUtils.isNotEmpty(dao.getStatus())){ sql.append(" and status = ? " ); params.add(dao.getStatus()); }
			
			if(StringUtils.isNotEmpty(dao.getUuid())){ sql.append(" and uuid = ? "); params.add(dao.getUuid()); }
			if(StringUtils.isNotEmpty(dao.getEmdkm())){ sql.append(" and emdkm = ? "); params.add(dao.getEmdkm()); } 
			if(StringUtils.isNotEmpty(dao.getAvsid())){ sql.append(" and avsid = ? "); params.add(dao.getAvsid()); } 
			if(StringUtils.isNotEmpty(dao.getMotid())){ sql.append(" and motid = ? "); params.add(dao.getMotid()); } 
			if(StringUtils.isNotEmpty(dao.getEmlnrt())){ sql.append(" and emlnrt = ? " ); params.add(dao.getEmlnrt()); }
			if(StringUtils.isNotEmpty(dao.getDate())){ sql.append(" and date = ? "); params.add(dao.getDate()); }
			if(StringUtils.isNotEmpty(dao.getTime())){ sql.append(" and time = ? "); params.add(dao.getTime()); }
			
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmolffDao.class));
			
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
			SadmolffDao dao = (SadmolffDao)daoObj;
			//date-time
			if(dao.getDate().isEmpty()) {
				dao.setDate(dateTimeMgr.getCurrentDate_ISO());
			}
			if(dao.getTime().isEmpty()) {
				dao.setTime(dateTimeMgr.getCurrentTimeHHmmss());
			}
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + DB_TABLE + "  ( status, statustxt, uuid, emdkm, emlnrt, date, time, avsid, motid ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getStatus(), dao.getStatustxt(), dao.getUuid(), dao.getEmdkm(), dao.getEmlnrt(), dao.getDate(), dao.getTime(), 
					dao.getAvsid(), dao.getMotid()
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
		//NA --> refer to update status. There is never a true DELETE
		return 0;
	}
	
	
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
