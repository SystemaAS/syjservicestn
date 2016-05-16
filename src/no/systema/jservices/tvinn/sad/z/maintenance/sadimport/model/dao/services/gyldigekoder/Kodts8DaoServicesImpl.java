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
			
			sql.append(" SELECT ks8avg, ks8skv, ks8ftx, ks8sat, ks8sty ");
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
		
			sql.append(" SELECT ks8avg, ks8skv, ks8ftx, ks8sat, ks8sty ");
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
		/*TODO
		try{
			SadsdDao dao = (SadsdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadsd (sdtnrf, SDKDAÆ, SDKDSÆ, sddtf, sddtt, SDBLSÆ, sdaktk )");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSdtnrf(), dao.getSdkdae(), dao.getSdkdse(), dao.getSddtf(), dao.getSddtt(),
					dao.getSdblse(), dao.getSdaktk()
		
					} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*TODO
		try{
			SadsdDao dao = (SadsdDao)daoObj;
			//logger.info("TURKIET BBBB" + dao.getTatyrb());
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE sadsd SET sddtf = ?, sddtt = ?, SDKDAÆ = ?, SDKDSÆ = ?, ");
			sql.append(" SDBLSÆ = ?, sdaktk = ? ");
			//id's
			sql.append(" WHERE sdtnrf = ? ");
			sql.append(" AND sddtf = ? ");
			sql.append(" AND sddtt = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getSddtf(), dao.getSddtt(), dao.getSdkdae(), dao.getSdkdse(),
						dao.getSdblse(), dao.getSdaktk(),
						//id's
						dao.getSdtnrf(),
						dao.getSddtfOrig(),
						dao.getSddttOrig(),
						
						
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		/*TODO
		try{
			SadsdDao dao = (SadsdDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadsd ");
			//id's
			sql.append(" WHERE sdtnrf = ? ");
			sql.append(" AND sddtf = ? ");
			sql.append(" AND sddtt = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getSdtnrf(), dao.getSddtf(), dao.getSddtt() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
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
