package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrkodfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.mapper.TrkodfMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * This Dao service also exists i bcore
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 * 
 * 
 */
public class TrkodfDaoServicesImpl implements TrkodfDaoServices {
	private static Logger logger = Logger.getLogger(TrkodfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	@Override
	public List findById (TransitKoder unikKode, String kode, StringBuffer errorStackTrace ){
		List<TrkodfDao> retval = new ArrayList<TrkodfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where tkunik = ?  ");
			sql.append(" and tkkode = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { unikKode.getTransitKode(), kode  }, new TrkodfMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;
	}
	
	@Override
	public List findById (String id, StringBuffer errorStackTrace ){
		List<TrkodfDao> retval = new ArrayList<TrkodfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where tkunik = ?  ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new TrkodfMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;	}
	

	@Override
	public int insert(Object dao, StringBuffer errorStackTrace) {
		//Not implemented
		return 0;
	}
	@Override
	public int update(Object dao, StringBuffer errorStackTrace) {
		//Not implemented
		return 0;
	}
	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		//Not implemented
		return 0;
	}    	
	
	@Override
	public List getList(StringBuffer errorStackTrace) {
		//Not implemented
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * ");
		sql.append(" from trkodf  ");
	
		return sql.toString();
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}



}
