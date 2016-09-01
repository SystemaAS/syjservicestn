package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SadavgeDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.SadavgeMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 12, 2016
 * 
 */
public class SadavgeDaoServicesImpl implements SadavgeDaoServices {
	private static Logger logger = Logger.getLogger(SadavgeDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List<Object> getList(StringBuffer errorStackTrace){
		List<Object> retval = new ArrayList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();			
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());
			
			retval = jdbcTemplate.query( sql.toString(), new SadavgeMapper());
		}catch(Exception e){
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	/**
	 * 
	 */
	public List<Object> findById(String id, StringBuffer errorStackTrace) {
		List<Object> retval = new ArrayList<Object>();
		String SQL_WILD_CARD = "%";

		try {

			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());
			sql.append(" and agtanr LIKE ? ");

			logger.info("sql=" + sql.toString());

			retval = jdbcTemplate.query(sql.toString(), new Object[] { id + SQL_WILD_CARD }, new SadavgeMapper());

		} catch (Exception e) {
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}	
	
	@Override
	public List<Object> findByIds(String id, String kode, String sekv, StringBuffer errorStackTrace) {
		List<Object> retval = new ArrayList<Object>();
		try {

			StringBuffer sql = new StringBuffer();			
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());
			sql.append(" and agtanr = ? ");
			sql.append(" and agakd = ?");
			sql.append(" and agskv = ?");
			logger.info("sql="+sql.toString());
			
			retval = jdbcTemplate.query(sql.toString(), new Object[] { id, kode, sekv }, new SadavgeMapper());
		} catch (Exception e) {
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
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
	public int insert(Object daoObj, StringBuffer errorStackTrace) {

		int retval = 0;
		try {
			SadavgeDao dao = (SadavgeDao) daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO sadavge (agtanr, agakd, agskv, agdtf, agdtt, agkd, agpp, agsats, agaktk) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			retval = this.jdbcTemplate.update(sql.toString(),
					new Object[] { dao.getAgtanr(), dao.getAgakd(), dao.getAgskv(), dao.getAgdtf(), dao.getAgdtt(),
							dao.getAgkd(), dao.getAgpp(), dao.getAgsats(), dao.getAgaktk() });

		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	/**
	 * UPDATE
	 * Til Dato = AGDTT, EU=AGKD, %/P=AGPP,  Sats=AGSATS, status= AGAKTK
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		
		int retval = 0;
		try{
			SadavgeDao dao = (SadavgeDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" UPDATE sadavge SET agdtf = ?, agdtt = ?, agkd = ?, agpp= ?, agsats = ?, agaktk = ?");
			sql.append(" WHERE agtanr = ? ");
			sql.append(" AND agakd = ?");
			sql.append(" AND agskv = ?");
			
			logger.info("update, sql="+sql.toString());
			logger.info("update, dao="+dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 	dao.getAgdtf(),
																				dao.getAgdtt(), 
																				dao.getAgkd(), 
																				dao.getAgpp(),
																				dao.getAgsats(), 
																				dao.getAgaktk(),
																				dao.getAgtanr(),																				
																				dao.getAgakd(), 
																				dao.getAgskv()
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
	 * 
	 * @return
	 */
	private StringBuffer getSELECT_FROM_WHERE_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select CHAR(s.agtanr) agtanr, t.taalfa, s.agakd, s.agskv, CHAR(s.agdtf) agdtf, CHAR(s.agdtt) agdtt, s.agkd, s.agpp, CHAR(s.agsats) agsats, s.agaktk ");
		sql.append(" from sadavge s, tari t");
	    sql.append(" where s.agtanr = t.tatanr ");
		return sql;
	}	
	
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadavgeDao dao = (SadavgeDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE from sadavge ");
			sql.append(" WHERE agtanr = ? ");
			sql.append(" AND agakd = ?");
			sql.append(" AND agskv = ?");
				
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getAgtanr(), dao.getAgakd(), dao.getAgskv() } );
			
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
