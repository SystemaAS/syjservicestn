package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SaehDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.SaehMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 */
public class SaehDaoServicesImpl implements SaehDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SaehDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	public List getList(StringBuffer errorStackTrace){
		List<SaehDao> retval = new ArrayList<SaehDao>();
		//N/A
		return retval;
	}
	
	public List findById (String id, StringBuffer errorStackTrace ){
		List<SaehDao> retval = new ArrayList<SaehDao>();
		//N/A
		return retval;
	}

	public List getListByAvd(String avd, StringBuffer errorStackTrace) {
		List<SaehDao> retval = new ArrayList<SaehDao>();

		try {
			StringBuffer sql = new StringBuffer();
			// WE must specify all the columns since there are numeric formats.
			// All numeric formats are incompatible with JDBC template (at least
			// in DB2)
			// when issuing select * from ...
			// The numeric formats MUST ALWAYS be converted to CHARs (IBM string
			// equivalent to Oracle VARCHAR)
			sql.append(" SELECT seavd, setdn, setll, setle, sedtg, senas ");
			sql.append(" FROM saeh");
			sql.append(" WHERE seavd = ?");

			logger.info(sql.toString());
			retval = this.jdbcTemplate.query(sql.toString(), new Object[] { avd }, new SaehMapper());
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	

	public List getListByAvdOpd(String avd, String opd, StringBuffer errorStackTrace){
		List<SaehDao> retval = new ArrayList<SaehDao>();
		String SQL_WILD_CARD = "%";
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" SELECT seavd, setdn, setll, setle, sedtg, senas ");
			sql.append(" FROM saeh");
			sql.append(" WHERE seavd = ?");
			sql.append(" AND setdn LIKE ?");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd + SQL_WILD_CARD }, new SaehMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	public List findForUpdate(String avd, String opd, StringBuffer errorStackTrace){
		List<SaehDao> retval = new ArrayList<SaehDao>();
		try{
			StringBuffer sql= new StringBuffer();
			sql.append("SELECT seavd, setdn, setll, setle, sedtg, senas ");
			sql.append(" FROM saeh ");
			sql.append(" WHERE seavd = ? ");
			sql.append(" AND setdn = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, opd }, new SaehMapper());
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
		//N/A
		return retval;
	}
	/**
	 * 
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		
		int retval = 0;
		try{
			
			SaehDao dao = (SaehDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE saeh SET setll=?, setle = ?, sedtg = ? ");
			sql.append(" WHERE seavd = ? ");
			sql.append(" AND setdn = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
												dao.getSetll(), 
												dao.getSetle(), 
												dao.getSedtg(),
												dao.getSeavd(), 
												dao.getSetdn() } );
			
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
		//N/A
		
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
