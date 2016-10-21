package no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.entities.SoktariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.mapper.SoktariMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.mapper.TariMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 */
public class SoktariDaoServicesImpl implements SoktariDaoServices {
	private static Logger logger = Logger.getLogger(SoktariDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SoktariDao> retval = new ArrayList<SoktariDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from soktari ");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new SoktariMapper());
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
		List<SoktariDao> retval = new ArrayList<SoktariDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from soktari ");
			sql.append(" WHERE tariff LIKE ? ");
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD }, new SoktariMapper());
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
	public List findForUpdate (String id, String alfa, StringBuffer errorStackTrace ){
		List<SoktariDao> retval = new ArrayList<SoktariDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from soktari ");
			sql.append(" WHERE tariff = ? ");
			sql.append(" AND beskr1 = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id , alfa }, new SoktariMapper());
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
	/*
	public List findByAlfa (String alfa, StringBuffer errorStackTrace ){
		List<SadsdDao> retval = new ArrayList<SadsdDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, b.taalfa taalfa ");
			sql.append(" from sadsd a, tari b ");
			sql.append(" WHERE b.taalfa LIKE ? ");
			sql.append(" AND a.sdtnrf = b.tatanr ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { SQL_WILD_CARD + alfa + SQL_WILD_CARD }, new SadsdMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	*/
	
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			SoktariDao dao = (SoktariDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO soktari (tariff, fill1, beskr1 )");
			sql.append(" VALUES(?, ?, ? )");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTariff(), dao.getFill1(), dao.getBeskr1() } );
			
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
			SoktariDao dao = (SoktariDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE soktari SET beskr1 = ? ");
			//id's
			sql.append(" WHERE tariff = ? ");
			sql.append(" AND beskr1 = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getBeskr1(), 
						//id's
						dao.getTariff(),
						dao.getBeskr1Orig(),
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
			SoktariDao dao = (SoktariDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from soktari ");
			//id's
			sql.append(" WHERE tariff = ? ");
			sql.append(" AND beskr1 = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTariff(), dao.getBeskr1() } );
			
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
	 * 
	 * @return
	 */
	private StringBuffer getSELECT_CLAUSE(){
		//Compatibility issue on special characters (ø,æ, etc)
		//All columns with special characters (NO,SE,DK) such as ö,ä,ø, etc MUST be defined with CAPITAL LETTERS, otherwise the selection in SQL will be invalid
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CHAR(tariff) tariff, fill1, beskr1 ");
		return sql;
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
