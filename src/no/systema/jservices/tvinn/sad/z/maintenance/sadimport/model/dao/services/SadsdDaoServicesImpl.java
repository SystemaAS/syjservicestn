package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.SadsdMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadsdDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */
public class SadsdDaoServicesImpl implements SadsdDaoServices {
	private static Logger logger = Logger.getLogger(SadsdDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadsdDao> retval = new ArrayList<SadsdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			/*sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, b.taalfa taalfa ");
			sql.append(" from sadsd a, tari b ");
			sql.append(" WHERE a.sdtnrf = b.tatanr ");*/
			
			sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, '' taalfa ");
			sql.append(" from sadsd a ");
			sql.append(" FETCH FIRST 200 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new SadsdMapper());
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
		List<SadsdDao> retval = new ArrayList<SadsdDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			/*
			sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, b.taalfa taalfa ");
			sql.append(" from sadsd a, tari b ");
			sql.append(" WHERE a.sdtnrf LIKE ? ");
			sql.append(" AND a.sdtnrf = b.tatanr ");*/
			
			sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, '' taalfa ");
			sql.append(" from sadsd a ");
			sql.append(" WHERE a.sdtnrf LIKE ? ");
			sql.append(" FETCH FIRST 2000 ROWS ONLY ");
			
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD }, new SadsdMapper());
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
	/**
	 * 
	 */
	public List findByAvgift (String avgift, StringBuffer errorStackTrace ){
		List<SadsdDao> retval = new ArrayList<SadsdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, '' taalfa ");
			sql.append(" from sadsd a ");
			sql.append(" WHERE a.SDKDAÆ =  ? ");
			sql.append(" FETCH FIRST 200 ROWS ONLY ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avgift }, new SadsdMapper());
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
	 * @param id
	 * @param fdate
	 * @param tdate
	 * @param errorStackTrace
	 * @return
	 */
	public List findByDates(String id, String fdate, String tdate, StringBuffer errorStackTrace){
		List<SadsdDao> retval = new ArrayList<SadsdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT CHAR(a.sdtnrf) sdtnrf, a.SDKDAÆ sdkdae, a.SDKDSÆ sdkdse, a.sddtf sddtf, a.sddtt sddtt, ");
			sql.append(" a.SDBLSÆ sdblse, a.sdaktk sdaktk, '' taalfa ");
			sql.append(" from sadsd a ");
			sql.append(" WHERE a.sdtnrf =  ? ");
			sql.append(" AND a.sddtf =  ? ");
			sql.append(" AND a.sddtt =  ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, fdate, tdate }, new SadsdMapper());
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
		
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
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
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
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
