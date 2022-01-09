package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder.Kodts2Mapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts2Dao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 25, 2016
 *  
 */
public class Kodts2DaoServicesImpl implements Kodts2DaoServices {
	private static Logger logger = LoggerFactory.getLogger(Kodts2DaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<Kodts2Dao> retval = new ArrayList<Kodts2Dao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ks2sta, ks2uni, ks2lk, ks2ftx, ks2nas, ks2pre, ks2mo  ");
			sql.append(" from kodts2 ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Kodts2Mapper());
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
		List<Kodts2Dao> retval = new ArrayList<Kodts2Dao>();
		//logger.info(avgId + "XXX" + sekvId);
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT ks2sta, ks2uni, ks2lk, ks2ftx, ks2nas, ks2pre, ks2mo  ");
			sql.append(" from kodts2 ");
			sql.append(" WHERE ks2lk = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new Kodts2Mapper());
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
			Kodts2Dao dao = (Kodts2Dao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodts2 ( ks2uni, ks2lk, ks2ftx, ks2mo, ks2pre, ks2nas )");
			sql.append(" VALUES ( ?, ?, ?, ?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getKs2uni(), dao.getKs2lk(), dao.getKs2ftx(), dao.getKs2mo(), dao.getKs2pre(), dao.getKs2nas()
				
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
			Kodts2Dao dao = (Kodts2Dao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodts2 SET ks2ftx = ?, ks2mo = ?, ks2pre = ?, ks2nas = ?  ");
			//id's
			sql.append(" WHERE ks2lk = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKs2ftx(), dao.getKs2mo(), dao.getKs2pre(), dao.getKs2nas(), 
						//id's
						dao.getKs2lk(),
						
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
			Kodts2Dao dao = (Kodts2Dao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodts2 ");
			//id's
			sql.append(" WHERE ks2lk = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKs2lk() } );
			
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
