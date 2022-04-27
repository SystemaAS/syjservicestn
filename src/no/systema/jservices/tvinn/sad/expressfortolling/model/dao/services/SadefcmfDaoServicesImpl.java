package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.common.dao.services.TellgeDaoService;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefcmfDao;
//import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadeffDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 2022
 * 
 */
public class SadefcmfDaoServicesImpl implements SadefcmfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadefcmfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadefcmfDao> retval = new ArrayList<SadefcmfDao>();
		/*
		try{
			StringBuffer sql = new StringBuffer();
			//sql.append(" select * from sadeff order by rrn(sadeff) desc");
			sql.append(" select * from sadefcmf order by cmli ");
			sql.append(" FETCH FIRST 100 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new BeanPropertyRowMapper(SadefcfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		*/
		return retval;
	}
	/**
	 * get a data set with where clause
	 */
	public List find(Object obj,StringBuffer errorStackTrace){
		SadefcmfDao dao = (SadefcmfDao)obj;
		List<SadefcmfDao> retval = new ArrayList<SadefcmfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from sadefcmf where cmavd = ?" );
			params.add(dao.getCmavd());
			sql.append(" and cmtdn = ? " ); 
			params.add(dao.getCmtdn());
			//filter
			//if(Math.abs(dao.getCltdn())>0){ sql.append(" and cltdn = ? "); params.add(dao.getCltdn()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadefcmfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		
		return retval;
	}
	
	public List pick(Object obj,StringBuffer errorStackTrace){
		SadefcmfDao dao = (SadefcmfDao)obj;
		List<SadefcmfDao> retval = new ArrayList<SadefcmfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		/*
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from sadefcf where clpro = ?" );
			params.add(dao.getClpro());
			//walk through the filter fields
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadefcfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		*/
		return retval;
	}
	
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List<SadefcmfDao> retval = new ArrayList<SadefcmfDao>();
		/*
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from sadefcmf where cmli = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadefcmfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		*/
		return retval;
	}
	/* Do we need this really ?
	public List findById (String avd, String tdn, StringBuffer errorStackTrace ){
		List<SadefcmfDao> retval = new ArrayList<SadefcmfDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from sadefcmf where cmavd = ? and cmtdn = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, tdn }, new BeanPropertyRowMapper(SadefcmfDao.class));
			
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
		logger.warn("Insert...");
		
		try{
			SadefcmfDao dao = (SadefcmfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadefcmf (cmavd, cmtdn, cmli, cmavde, cmtdne, cmetyp, cmetypt, cmeid, cmeser ) ");
			sql.append(" VALUES( ?,?,?,?,?,?,?,?,? ) ");
			
			logger.warn(sql.toString());
			logger.warn(dao.toString());
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getCmavd(),dao.getCmtdn(), dao.getCmli(), dao.getCmavde(), dao.getCmtdne(), dao.getCmetyp(), dao.getCmetypt(), dao.getCmeid(), dao.getCmeser()
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
			SadefcmfDao dao = (SadefcmfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE sadefcmf SET cmavde = ?, cmtdne = ?, cmetyp = ?, cmeid = ?, cmeser = ?  ");
			//id's
			sql.append(" WHERE cmavd = ? ");
			sql.append(" AND cmtdn = ? ");
			sql.append(" AND cmli = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getCmavde(), dao.getCmtdne(), dao.getCmetyp(), dao.getCmetypt(), dao.getCmeid(), dao.getCmeser(),
				//id's
				dao.getCmavd(),dao.getCmtdn(), dao.getCmli(),
				} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadefcf:"+writer.toString());
			e.printStackTrace();
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
			SadefcmfDao dao = (SadefcmfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE FROM sadefcmf " );
			//ids
			sql.append(" WHERE cmavd = ? ");
			sql.append(" AND cmtdn = ? ");
			sql.append(" AND cmli = ? ");		
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getCmavd(),dao.getCmtdn(), dao.getCmli() } );
			
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

	@Autowired
	private TellgeDaoService tellgeDaoService = null;                                                            
	public void setTellgeDaoService( TellgeDaoService tellgeDaoService) {this.tellgeDaoService = tellgeDaoService;}          
	public TellgeDaoService getTellgeDaoService() {return this.tellgeDaoService;}

	
}
