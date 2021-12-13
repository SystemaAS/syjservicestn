package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TransitKodeTypeDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrkodfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.mapper.TrkodfMapper;
import no.systema.main.util.DbErrorMessageManager;
import no.systema.main.util.MessageSourceHelper;

/**
 * This Dao service also exists i bcore
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 * 
 * 
 */
public class TrkodfDaoServicesImpl implements TrkodfDaoServices {
	private static Logger logger = LogManager.getLogger(TrkodfDaoServicesImpl.class.getName());
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
	public List findBySearch(TransitKoder unikKode, String kode, String text, StringBuffer errorStackTrace) {
		List<TrkodfDao> retval = new ArrayList<TrkodfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where tkunik = ? ");
			sql.append(" and UPPER(tkkode) like ? ");
			sql.append(" and UPPER(tktxtn) like ?"); 
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { unikKode.getTransitKode(), wildcard(kode), wildcard(text)  }, new TrkodfMapper());
			
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
	public List<TransitKodeTypeDao> getTransitKodeTyper() {
		MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
		String keyPrefix = "systema.tvinn.sad.ncts.export.transitkoder.";
		List<TransitKodeTypeDao> list = new ArrayList<TransitKodeTypeDao>();
		TransitKodeTypeDao dao =null;
		for (TransitKoder koder : TransitKoder.values()) {
			dao = new TransitKodeTypeDao();
			dao.setTkunik(koder.getTransitKode());	
			dao.setDescription(messageSourceHelper.getMessage(keyPrefix+koder.getTransitKode(), null));
			list.add(dao);
		}
		return list;
	}	
	

	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;

		try {
			TrkodfDao dao = (TrkodfDao) daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO trkodf (tkunik, tkkode, tktxtn, tktxte, tkavg, tkank, tktrs )");
			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ? )");

			logger.info("sql="+sql.toString());
			logger.info("dao="+ReflectionToStringBuilder.toString(dao));
			
			retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dao.getTkunik(), dao.getTkkode(),
					dao.getTktxtn(), dao.getTktxte(), dao.getTkavg(), dao.getTkank(), dao.getTktrs() });

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
		
	}
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			TrkodfDao dao = (TrkodfDao) daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE trkodf SET tktxtn = ?, tktxte = ?, tkavg = ?, tkank = ?, tktrs = ? ");
			sql.append(" where tkunik = ? ");
			sql.append(" and tkkode = ?");

			retval = this.jdbcTemplate.update(sql.toString(), new Object[] { dao.getTktxtn(), dao.getTktxte(),
					dao.getTkavg(), dao.getTkank(), dao.getTktrs(), dao.getTkunik(), dao.getTkkode() });

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString()); // Chop the message to comply to
											// JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	
	@Override
	public int delete(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try{
			TrkodfDao dao = (TrkodfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE from trkodf ");
			sql.append(" where tkunik = ? ");
			sql.append(" and tkkode = ?");

			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTkunik(), dao.getTkkode() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;	}    	
	
	@Override
	public List getList(StringBuffer errorStackTrace) {
		//Not implemented
		return null;
	}

	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * ");
		sql.append(" from trkodf  ");
	
		return sql.toString();
	}

	private String wildcard(String criteria) {
		if (criteria == null){
			return "%";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		sb.append(criteria.toUpperCase());
		sb.append("%");
		return sb.toString();
	}
	

	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}


	@Override
	public boolean exists(TransitKoder unikKode, String kode) {
		List<TrkodfDao> retval = new ArrayList<TrkodfDao>();

		StringBuffer sql = new StringBuffer();

		sql.append(this.getSELECT_FROM_CLAUSE());
		sql.append(" where tkunik = ? ");
		sql.append(" and tkkode = ? ");

		retval = this.jdbcTemplate.query(sql.toString(), new Object[] { unikKode.getTransitKode(), kode }, new TrkodfMapper());

		if (retval.size() == 0) {
			return false;
		} else {
			return true;
		}

	}


}
