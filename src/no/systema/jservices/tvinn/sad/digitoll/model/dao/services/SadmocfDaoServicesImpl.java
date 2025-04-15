package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmoafDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmocfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Dec 2023
 * 
 */
public class SadmocfDaoServicesImpl implements SadmocfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmocfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "sadmocf";
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadmocfDao> retval = new ArrayList<SadmocfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME+ " order by orgnr ");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadmocfDao.class));
			
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
	 * get a data set with where clause
	 */
	public List find(Object obj,StringBuffer errorStackTrace){
		SadmocfDao dao = (SadmocfDao)obj;
		List<SadmocfDao> retval = new ArrayList<SadmocfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME );
			//walk through the filter fields
			if(StringUtils.isNotEmpty(dao.getName()) ){ 
				sql.append(" where name LIKE ? " ); params.add(this.SQL_WILD_CARD + dao.getName() + this.SQL_WILD_CARD); 
			}else if(StringUtils.isNotEmpty(dao.getCommtype()) ){ 
				sql.append(" where commtype LIKE ? " ); params.add(this.SQL_WILD_CARD + dao.getCommtype() + this.SQL_WILD_CARD); 
			}else if(StringUtils.isNotEmpty(dao.getFormat()) ){ 
				sql.append(" where format LIKE ? " ); params.add(this.SQL_WILD_CARD + dao.getFormat() + this.SQL_WILD_CARD); 
			}
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmocfDao.class));
			
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
		List<SadmocfDao> retval = new ArrayList<SadmocfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + TABLE_NAME+ " where orgnr = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadmocfDao.class));
			
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
		logger.info("before INSERT");
	
		try{
			SadmocfDao dao = (SadmocfDao)daoObj;
			//we must check if this is not the record nr 1 otherwise there will fail in: getNext...
			List list = this.find(daoObj, errorStackTrace);
			logger.info(list.toString());
			if(list==null || list.size()<=0 ) {
				StringBuffer sql = new StringBuffer();
				//DEBUG --> logger.info("mydebug");
				sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( orgnr, name, commtype, format, xmlxsd,  ");
				sql.append(" ftpserver, ftpport, ftpuser, ftppwd, ftpdir, ftptmp, ftpbupdir,  ");
				sql.append(" sftpdir_ps, wsendpoint, avsorgnr, avsname  )");
				sql.append(" VALUES ( ?,?,?,?,?, ");
				sql.append(" ?,?,?,?,?,?,?, ");
				sql.append(" ?,?,?,? ) ");
				
				//params
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getOrgnr(), dao.getName(), dao.getCommtype(), dao.getFormat(), dao.getXmlxsd(),
						dao.getFtpserver(), dao.getFtpport(), dao.getFtpuser(), dao.getFtppwd(), dao.getFtpdir(), dao.getFtptmp(), dao.getFtpbupdir(),
						dao.getSftpdir_ps(), dao.getWsendpoint(), dao.getAvsorgnr(), dao.getAvsname()
						});	
			}else {
				logger.error("RECORD exists already ? (check orgnr, name, commtype ...)");
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
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmocfDao dao = (SadmocfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET  name = ?, commtype = ?, format = ?, xmlxsd = ?, ");
			sql.append(" ftpserver = ?, ftpport = ?, ftpuser = ?, ftppwd = ?, ftpdir = ?, ftptmp = ?, ftpbupdir = ?, ");
			sql.append(" sftpdir_ps = ?, wsendpoint = ?, avsorgnr = ?, avsname = ?  ");
			//id's
			sql.append(" WHERE orgnr = ?  ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getName(), dao.getCommtype(), dao.getFormat(), dao.getXmlxsd(),
			dao.getFtpserver(), dao.getFtpport(), dao.getFtpuser(), dao.getFtppwd(), dao.getFtpdir(), dao.getFtptmp(), dao.getFtpbupdir(),
			dao.getSftpdir_ps(), dao.getWsendpoint(), dao.getAvsorgnr(), dao.getAvsname(),
			//id's
			dao.getOrgnr()
			} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update :"+writer.toString());
			e.printStackTrace();
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
			SadmocfDao dao = (SadmocfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" DELETE from "  + this.TABLE_NAME );
			sql.append(" WHERE orgnr = ?  ");
			logger.info(sql.toString() + " orgnr:" + dao.getOrgnr());
				
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getOrgnr()
			} );
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadl:"+writer.toString());
			e.printStackTrace();
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
