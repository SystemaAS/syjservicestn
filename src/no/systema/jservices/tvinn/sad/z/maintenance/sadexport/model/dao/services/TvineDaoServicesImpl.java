package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.TvineDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.TvineMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 8, 2016
 * 
 */
public class TvineDaoServicesImpl implements TvineDaoServices {
	private static Logger logger = LoggerFactory.getLogger(TvineDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List<Object> getList(StringBuffer errorStackTrace){
		List<Object> retval = new ArrayList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select * from tvine");
			logger.info(sql.toString());
			retval = jdbcTemplate.query( sql.toString(), new TvineMapper());
		}catch(Exception e){
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	/**
	 * 
	 */
	public List<Object> findById (String id, StringBuffer errorStackTrace ){
		List<Object> retval = new ArrayList<Object>();
		try{
			String sql= "select * from tvine where e9705 = ?";
			retval = jdbcTemplate.query( sql, new Object[] { id }, new TvineMapper());
		}catch(Exception e){
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
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
			TvineDao dao = (TvineDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO tvine (e9705, e4440) ");
			sql.append(" VALUES(?, ?) ");

			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getE9705(), dao.getE4440() } );
			
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
		    
			TvineDao dao = (TvineDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" UPDATE tvine SET e4440 = ? ");
			sql.append(" WHERE e9705 = ? ");
			//params
			//DEBUG --> 
			logger.info("mydebug, sql="+sql.toString());
			logger.info("dao="+dao);
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getE4440() , dao.getE9705()} );
			
			
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
			TvineDao dao = (TvineDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE from tvine ");
			sql.append(" WHERE e9705 = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getE9705() } );
			
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
