package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.gyldigekoder.Kodts9Mapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.Kodts9Dao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 *  
 */
public class Kodts9DaoServicesImpl implements Kodts9DaoServices {
	private static Logger logger = LoggerFactory.getLogger(Kodts9DaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<Kodts9Dao> retval = new ArrayList<Kodts9Dao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ks9sta, ks9uni, ks9typ, ks9ftx ");
			sql.append(" from kodts9 ");
			sql.append(" order by ks9typ ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Kodts9Mapper());
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
		List<Kodts9Dao> retval = new ArrayList<Kodts9Dao>();
		//logger.info(avgId + "XXX" + sekvId);
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT ks9sta, ks9uni, ks9typ, ks9ftx ");
			sql.append(" from kodts9 ");
			sql.append(" WHERE ks9typ = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new Kodts9Mapper());
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
			Kodts9Dao dao = (Kodts9Dao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodts9 ( ks9uni, ks9typ, ks9ftx )");
			sql.append(" VALUES ( ?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getKs9uni(), dao.getKs9typ(), dao.getKs9ftx()
				
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
			Kodts9Dao dao = (Kodts9Dao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodts9 SET ks9ftx = ? ");
			//id's
			sql.append(" WHERE ks9typ = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKs9ftx(), 
						//id's
						dao.getKs9typ(),
						
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
			Kodts9Dao dao = (Kodts9Dao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodts9 ");
			//id's
			sql.append(" WHERE ks9typ = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKs9typ() } );
			
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
