package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.TariMapper;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.TariDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 */
public class TariDaoServicesImpl implements TariDaoServices {
	private static Logger logger = Logger.getLogger(TariDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(StringBuffer errorStackTrace){
		List<TariDao> retval = new ArrayList<TariDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql = this.getSELECT_CLAUSE();
			sql.append(" FROM tari ");
			//TEST --> sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new TariMapper());
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
		List<TariDao> retval = new ArrayList<TariDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			sql = this.getSELECT_CLAUSE();
			sql.append(" FROM tari ");
			sql.append(" WHERE tatanr LIKE ?");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD }, new TariMapper());
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
	 * Alfa text search for toldtariff
	 * 
	 */
	public List findByAlfa (String alfa, StringBuffer errorStackTrace ){
		List<TariDao> retval = new ArrayList<TariDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			sql = this.getSELECT_CLAUSE();
			sql.append(" FROM tari ");
			sql.append(" WHERE taalfa LIKE ?");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { SQL_WILD_CARD + alfa + SQL_WILD_CARD }, new TariMapper());
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
	 * @param alfa
	 * @param errorStackTrace
	 * @return
	 */
	public List findForUpdate (String id, String alfa, StringBuffer errorStackTrace ){
		List<TariDao> retval = new ArrayList<TariDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql = this.getSELECT_CLAUSE();
			sql.append(" FROM tari ");
			sql.append(" WHERE tatanr = ?");
			sql.append(" AND taalfa = ?");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, alfa }, new TariMapper());
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
			TariDao dao = (TariDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO tari (tatanr, taalfa, tadtr, tadato, taordb, taordk, taeftb, taeftk, taefb, taefk, ");
			sql.append(" tastk, tatxt, taenhe, tadts, TAEØSB, TAEØSK, tatsjb, tatsjk, tatyrb, tatyrk, taisrb, taisrk, ");
			sql.append(" taellb, taellk ");
			
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ");
			sql.append(" ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTatanr(), dao.getTaalfa(), 
					dao.getTadtr(), dao.getTadato(), dao.getTaordb(),dao.getTaordk(),dao.getTaeftb(),dao.getTaeftk(), dao.getTaefb(),dao.getTaefk(),
					dao.getTastk(), dao.getTatxt(), dao.getTaenhe(), dao.getTadts(), dao.getTaeosb(), dao.getTaeosk(), dao.getTatsjb(), dao.getTatsjk(),
					dao.getTatyrb(), dao.getTatyrk(), dao.getTaisrb(), dao.getTaisrk(), dao.getTaellb(), dao.getTaellk()
							
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
			
			TariDao dao = (TariDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE tari SET tadato = ?, taalfa = ?, taordb = ?, taordk = ?, ");
			sql.append(" taeftb = ?, taeftk = ?, taefb = ?, taefk = ?, ");
			sql.append(" tastk = ?, tatxt = ?, taenhe = ?, tadts = ?, TAEØSB = ?, TAEØSK = ? ");
			sql.append(" tatsjb = ?, tatsjk = ?, tatyrb = ?, tatyrk = ?, taisrb = ?, taisrk = ?, ");
			sql.append(" taellb = ?, taellk = ? ");
			//id's
			sql.append(" WHERE tatanr = ? ");
			sql.append(" AND taalfa = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getTadato(), dao.getTaalfa() , dao.getTaordb(), dao.getTaordk(),
						dao.getTaeftb(), dao.getTaeftk(), dao.getTaefb(), dao.getTaefk(),
						dao.getTastk(), dao.getTatxt(), dao.getTaenhe(), dao.getTadts(),
						dao.getTaeosb(), dao.getTaeosk(), 
						dao.getTatsjb(), dao.getTatsjk(),
						dao.getTatyrb(), dao.getTatyrk(), dao.getTaisrb(), dao.getTaisrk(),
						dao.getTaellb(), dao.getTaellk(),
						//id's
						dao.getTatanr(), dao.getTaalfaOrig() } );
			
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
			TariDao dao = (TariDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from tari ");
			sql.append(" WHERE tatanr = ? ");
			sql.append(" AND taalfa = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTatanr(), dao.getTaalfa() } );
			
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
		sql.append(" SELECT CHAR(tatanr) tatanr, CHAR(taordb) taordb, taordk, CHAR(taeftb) taeftb, taeftk, CHAR(taefb) taefb, ");
		sql.append(" taefk, tastk, takap, taalfa, tatxt, taenhe, tadtr, tadato, tadts, TAEØSB taeosb, TAEØSK taeosk, ");
		sql.append(" tatsjb, tatsjk, tatyrb, tatyrk, taisrb, taisrk, taellb, taellk ");
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
