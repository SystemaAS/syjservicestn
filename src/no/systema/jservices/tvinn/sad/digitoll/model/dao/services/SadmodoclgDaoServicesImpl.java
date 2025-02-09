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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmodoclgDao;

import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Feb 2025
 * 
 */
public class SadmodoclgDaoServicesImpl implements SadmodoclgDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmodoclgDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private String SQL_WILD_CARD = "%";
	private String DB_TABLE = "sadmodoclg";
	
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
		SadmodoclgDao dao = (SadmodoclgDao)obj;
		List<SadmodoclgDao> retval = new ArrayList<SadmodoclgDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where docid LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			
			
			if(StringUtils.isNotEmpty(dao.getDocId())){ sql.append(" and msgid = ? "); params.add(dao.getDocId()); } 
			if(StringUtils.isNotEmpty(dao.getDeklid())){ sql.append(" and peppolid = ? "); params.add(dao.getDeklid()); } 
			if(StringUtils.isNotEmpty(dao.getDoctyp())){ sql.append(" and peppolid = ? "); params.add(dao.getDoctyp()); } 
			if(StringUtils.isNotEmpty(dao.getDoclnk())){ sql.append(" and peppolid = ? "); params.add(dao.getDoclnk()); } 
			if(StringUtils.isNotEmpty(dao.getDeklnr())){ sql.append(" and peppolid = ? "); params.add(dao.getDeklnr()); } 
			if(StringUtils.isNotEmpty(dao.getDekldate())){ sql.append(" and peppolid = ? "); params.add(dao.getDekldate()); } 
			if(StringUtils.isNotEmpty(dao.getDeklsekv())){ sql.append(" and peppolid = ? "); params.add(dao.getDeklsekv()); } 
			
			if(StringUtils.isNotEmpty(dao.getSenddate())){ sql.append(" and senddate = ? "); params.add(dao.getSenddate()); } 
			if(StringUtils.isNotEmpty(dao.getSendtime())){ sql.append(" and sendtime = ? "); params.add(dao.getSendtime()); } 
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmodoclgDao.class));
			
			
			
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
		List<SadmodoclgDao> retval = new ArrayList<SadmodoclgDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + DB_TABLE + " where deklid LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			if(StringUtils.isNotEmpty(id)){ sql.append(" and deklid = ? "); params.add(id); } 
			
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmodoclgDao.class));
			
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
			SadmodoclgDao dao = (SadmodoclgDao)daoObj;
			//date-time
			if(StringUtils.isEmpty(dao.getSenddate())) {
				dao.setSenddate(dateTimeMgr.getCurrentDate_ISO());
			}
			if(StringUtils.isEmpty(dao.getSendtime())) {
				dao.setSendtime(dateTimeMgr.getCurrentTimeHHmmss());
			}
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + DB_TABLE + "  ( docid, deklid, doctyp, doclnk, deklnr, dekldate, deklsekv, senddate, sendtime ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getDocId(), dao.getDeklid(), dao.getDoctyp(), dao.getDoclnk(), dao.getDeklnr(), dao.getDekldate(), dao.getDeklsekv(), dao.getSenddate(), dao.getSendtime() 
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
			SadmodoclgDao dao = (SadmodoclgDao)daoObj;
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE " + DB_TABLE + " set deklid = ?, doctyp = ?, doclnk = ?, deklnr = ?, dekldate = ?, deklsekv = ?, senddate = ?, sendtime = ? ");
			sql.append(" WHERE docid = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getDeklid(), dao.getDoctyp(), dao.getDoclnk(), dao.getDeklnr(), dao.getDekldate(), dao.getDeklsekv(), dao.getSenddate(), dao.getSendtime(),
					//id
					dao.getDocId()
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
			SadmodoclgDao dao = (SadmodoclgDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE FROM " + DB_TABLE + "  WHERE docid = ? AND deklid = ?");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getDocId(), dao.getDeklid()
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
