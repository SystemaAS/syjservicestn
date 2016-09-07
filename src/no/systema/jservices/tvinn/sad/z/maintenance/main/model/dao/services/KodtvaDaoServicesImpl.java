package no.systema.jservices.tvinn.sad.z.maintenance.main.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.main.model.dao.entities.KodtvaDao;
import no.systema.jservices.tvinn.sad.z.maintenance.main.model.dao.mapper.KodtvaMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jun 1, 2016
 * 
 */
public class KodtvaDaoServicesImpl implements KodtvaDaoServices {
	private static Logger logger = Logger.getLogger(KodtvaDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * 
	 */
	public List getListDistinct(StringBuffer errorStackTrace){
		List<KodtvaDao> retval = new ArrayList<KodtvaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" Select distinct kvakod, kvaxxx" );
			sql.append(" from kodtva ");
			//sql.append(" FETCH FIRST 00 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new KodtvaMapper());
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
	public List getList(StringBuffer errorStackTrace){
		List<KodtvaDao> retval = new ArrayList<KodtvaDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from kodtva ");
			//sql.append(" FETCH FIRST 00 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new KodtvaMapper());
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
	 * when searching for an update
	 * 
	 */
	public List findForUpdate (String id, String alfa, StringBuffer errorStackTrace ){
		List<KodtvaDao> retval = new ArrayList<KodtvaDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from kodtva ");
			sql.append(" where kvakod = ? ");
			sql.append(" and kvadt = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id , alfa }, new KodtvaMapper());
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
		List<KodtvaDao> retval = new ArrayList<KodtvaDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" from kodtva ");
			sql.append(" where kvakod LIKE ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD }, new KodtvaMapper());
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
			KodtvaDao dao = (KodtvaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO kodtva (kvauni, kvakod, kvakrs, kvaomr, kvadt, kvagkr, kvaxxx, kvagv)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKvauni(), dao.getKvakod(), dao.getKvakrs(),
				dao.getKvaomr(), dao.getKvadt(), dao.getKvagkr(), dao.getKvaxxx(), dao.getKvagv() } );
			
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
			KodtvaDao dao = (KodtvaDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE kodtva SET kvakrs = ?, kvaomr = ?, kvadt = ?, kvagkr = ?, kvaxxx = ?, kvagv = ? ");
			//id's
			sql.append(" WHERE kvakod = ? ");
			sql.append(" AND kvadt = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getKvakrs(), dao.getKvaomr(), dao.getKvadt(), dao.getKvagkr(), 
						dao.getKvaxxx(), dao.getKvagv(),
						//id's
						dao.getKvakod(),
						dao.getKvadt(),
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
			KodtvaDao dao = (KodtvaDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from kodtva ");
			//id's
			sql.append(" WHERE kvakod = ? ");
			sql.append(" AND kvadt = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getKvakod(), dao.getKvadt() } );
			
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
		sql.append(" SELECT kvasta, kvauni, kvakod, CHAR(kvakrs) kvakrs, CHAR(kvaomr) kvaomr, CHAR(kvadt) kvadt, CHAR(kvagkr) kvagkr, kvaxxx, kvagv ");
		//sql.append(" SELECT kvasta, kvauni, kvakod ");
		
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
