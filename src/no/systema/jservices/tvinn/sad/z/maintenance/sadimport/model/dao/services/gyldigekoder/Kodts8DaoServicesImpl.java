package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder.Kodts8Mapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts8Dao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 13, 2016
 * 
 */
public class Kodts8DaoServicesImpl implements Kodts8DaoServices {
	private static Logger logger = Logger.getLogger(Kodts8DaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<Kodts8Dao> retval = new ArrayList<Kodts8Dao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT ks8avg, ks8skv, substr(ks8ftx, 1, 50) ks8ftx, substr(ks8ftx, 51, 1) ore, substr(ks8ftx, 52, 1) mil,  ks8sat, ks8sty ");
			sql.append(" from kodts8 a ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Kodts8Mapper());
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
	public List findById (String avgId, String sekvId, StringBuffer errorStackTrace ){
		List<Kodts8Dao> retval = new ArrayList<Kodts8Dao>();
		//logger.info(avgId + "XXX" + sekvId);
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT ks8avg, ks8skv, substr(ks8ftx, 1, 50) ks8ftx, substr(ks8ftx, 51, 1) ore, substr(ks8ftx, 52, 1) mil,  ks8sat, ks8sty ");
			sql.append(" from kodts8 ");
			sql.append(" WHERE ks8avg = ? ");
			sql.append(" AND ks8skv = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avgId, sekvId }, new Kodts8Mapper());
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
		//N/A
		return new ArrayList();
	}
	/**
	 * 
	 */
	public List findByAlfa (String alfa, StringBuffer errorStackTrace ){
		//To be determnined
		return new ArrayList();
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
			Kodts8Dao dao = (Kodts8Dao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodts8 (ks8avg, ks8skv, ks8ftx, ks8sat, ks8sty )");
			sql.append(" VALUES (?, ?, ?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getKs8avg(), dao.getKs8skv(), dao.getKs8ftx(), dao.getKs8sat(), dao.getKs8sty()
				
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
			Kodts8Dao dao = (Kodts8Dao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodts8 SET  ks8ftx = ?, ks8sat = ?, ks8sty = ? ");
			//id's
			sql.append(" WHERE ks8avg = ? ");
			sql.append(" AND ks8skv = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKs8ftx(), dao.getKs8sat(), dao.getKs8sty(),
						//id's
						dao.getKs8avg(),
						dao.getKs8skv(),
						
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
			Kodts8Dao dao = (Kodts8Dao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodts8 ");
			//id's
			sql.append(" WHERE ks8avg = ? ");
			sql.append(" AND ks8skv = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKs8avg(), dao.getKs8skv() } );
			
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
