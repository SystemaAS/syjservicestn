package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmotfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public class SadmotfDaoServicesImpl implements SadmotfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmotfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "sadmotf";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadmotfDao> retval = new ArrayList<SadmotfDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + this.TABLE_NAME + " order by etdtr desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadmotfDao.class));
			
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
	 * get a data set with where clause
	 */
	public List find(Object obj,StringBuffer errorStackTrace){
		SadmotfDao dao = (SadmotfDao)obj;
		List<SadmotfDao> retval = new ArrayList<SadmotfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + this.TABLE_NAME + " where etlnrt LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			if(dao.getEtlnrt()>0){ sql.append(" and etlnrt = ? " ); params.add(dao.getEtlnrt()); }
			if(dao.getEtavd()>0){ sql.append(" and etavd = ? " ); params.add(dao.getEtavd()); }
			if(dao.getEtpro()>0){ sql.append(" and etpro = ? "); params.add(dao.getEtpro()); }
			if(StringUtils.isNotEmpty(dao.getEtsg())){ sql.append(" and etsg = ? "); params.add(dao.getEtsg()); }
			if(StringUtils.isNotEmpty(dao.getEtst())){ sql.append(" and etst = ? "); params.add(dao.getEtst()); }
			if(StringUtils.isNotEmpty(dao.getEtst2())){ sql.append(" and etst2 = ? "); params.add(dao.getEtst2()); }
			//dates
			if(dao.getEtdtr()>0){ 
				sql.append(" and etdtr >= ? "); params.add(dao.getEtdtr()); 
			}
			if(dao.getEtdtr_to()>0){ 
				sql.append(" and etdtr <= ? "); params.add(dao.getEtdtr_to()); 
			}
			if(dao.getEtetad()>0){ 
				sql.append(" and etetad >= ? "); params.add(dao.getEtetad()); 
			}
			if(dao.getEtetad_to()>0){ 
				sql.append(" and etetad <= ? "); params.add(dao.getEtetad_to()); 
			}
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmotfDao.class));
			
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
		List<SadmotfDao> retval = new ArrayList<SadmotfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + this.TABLE_NAME + " where etmid = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadmotfDao.class));
			
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
	public List findByLrn (String id, StringBuffer errorStackTrace ){
		List<SadmotfDao> retval = new ArrayList<SadmotfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + this.TABLE_NAME + " where etuuid = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadmotfDao.class));
			
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
	public List findHouseOpd (String opd, Object obj, StringBuffer errorStackTrace ){
		SadmotfDao dao = (SadmotfDao)obj;
		List<SadmotfDao> retval = new ArrayList<SadmotfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select DISTINCT a.* from sadmotf a, sadmohf b " );
			sql.append(" where a.etlnrt = b.ehlnrt " );
			sql.append(" and b.ehtdn LIKE ? " );
			sql.append(" order by etlnrt " );
			 
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { "%" + opd + "%" }, new BeanPropertyRowMapper(SadmotfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	public List findHouseExtRef (String extref, Object obj, StringBuffer errorStackTrace ){
		SadmotfDao dao = (SadmotfDao)obj;
		List<SadmotfDao> retval = new ArrayList<SadmotfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select DISTINCT a.* from sadmotf a, sadmohf b " );
			sql.append(" where a.etlnrt = b.ehlnrt " );
			sql.append(" and b.ehextref LIKE ? " );
			sql.append(" order by etlnrt " );
			 
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { "%" + extref + "%" }, new BeanPropertyRowMapper(SadmotfDao.class));
			
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
		int nextEtlnrt =  -1;
		
		logger.info("before INSERT");
		try{
			
			SadmotfDao dao = (SadmotfDao)daoObj;
			//we must check if this is not the record nr 1 otherwise there will fail in: getNext...
			List list = this.getList(errorStackTrace);
			logger.info(list.toString());
			if(list==null || list.size()<=0 ) {
				nextEtlnrt = 1;
			}else {
				nextEtlnrt =  getNextEtlnrt();
			}
			dao.setEtlnrt(nextEtlnrt);
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( etst, etavd, etpro, etlnrt, etdtr, etsg, etst2, etst3, etdtin, ");
			sql.append(" etetad, etetat, etshed, etshet, ");
			sql.append(" etknr, etrgr, etnar, etna2r, etad1r, etnrr, etpnr, etpsr, etlkr, etpbr, etemr, etemrt, etkmrk, etktm, ");
			sql.append(" etktyp, etklk, etcref, etktkd, etsjaf, etems, etemst,  ");
			sql.append(" etknt, etrgt, etnat, etna2t, etad1t, etnrt, etpnt, etpst, etlkt, etpbt, etemt, etemtt, etdkm, etdkmt, ettsd ) ");
			
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					
			dao.getEtst(), dao.getEtavd(), dao.getEtpro(), dao.getEtlnrt(), dao.getEtdtr(), dao.getEtsg(), dao.getEtst2(), dao.getEtst3(), dao.getEtdtin(),
			dao.getEtetad(), dao.getEtetat(), dao.getEtshed(), dao.getEtshet(),  
			dao.getEtknr(), dao.getEtrgr(), dao.getEtnar(), dao.getEtna2r(), dao.getEtad1r(), dao.getEtnrr(), dao.getEtpnr(), dao.getEtpsr(), dao.getEtlkr(), dao.getEtpbr(), dao.getEtemr(), dao.getEtemrt(), dao.getEtkmrk(), dao.getEtktm(),
			dao.getEtktyp(), dao.getEtklk(), dao.getEtcref(), dao.getEtktkd(), dao.getEtsjaf(), dao.getEtems(), dao.getEtemst(),
			dao.getEtknt(), dao.getEtrgt(), dao.getEtnat(), dao.getEtna2t(), dao.getEtad1t(), dao.getEtnrt(), dao.getEtpnt(), dao.getEtpst(), dao.getEtlkt(), dao.getEtpbt(), dao.getEtemt(), dao.getEtemtt(), dao.getEtdkm(), dao.getEtdkmt(), dao.getEttsd(),

			} );
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		//in order to get the id for a "find"
		if(retval >= 0) {
			retval = nextEtlnrt;
		}
		logger.info("after INSERT --> retval:" + nextEtlnrt);
		
		return retval;
		
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			
			
			SadmotfDao dao = (SadmotfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET etdtr = ?, etsg = ?, etst = ?, etst2 = ?, etuuid = ?, etmid = ?, etst3 = ?, etdtin = ?, etetad = ?, etetat = ?,  ");
			sql.append(" etshed = ? , etshet = ? , etknr = ? , etrgr = ? , etnar = ?, etna2r = ?, etad1r = ?, etnrr = ?, etpnr = ?, etpsr = ?, etlkr = ?, etpbr = ?, ");
			sql.append(" etemr = ? , etemrt = ? , etkmrk = ? , etktm = ? , etktyp = ?, etklk = ?, etcref = ?, etktkd = ?, etsjaf = ?, etems = ?, etemst = ?, ");
			sql.append(" etknt = ? , etrgt = ? , etnat = ?, etna2t = ?, etad1t = ?, etnrt = ?, etpnt = ?, etpst = ?, etlkt = ?, etpbt = ?, ");
			sql.append(" etemt = ? , etemtt = ? , etdkm = ? , etdkmt = ? , ettsd = ? ");
			
			//id's
			sql.append(" WHERE etlnrt = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					
			dao.getEtdtr(), dao.getEtsg(), dao.getEtst(), dao.getEtst2(), dao.getEtuuid(), dao.getEtmid(), dao.getEtst3(), dao.getEtdtin(), dao.getEtetad(), dao.getEtetat(),
			dao.getEtshed(), dao.getEtshet(), dao.getEtknr(), dao.getEtrgr(), dao.getEtnar(), dao.getEtna2r(), dao.getEtad1r(), dao.getEtnrr(), dao.getEtpnr(), dao.getEtpsr(), dao.getEtlkr(), dao.getEtpbr(),
			dao.getEtemr(), dao.getEtemrt(), dao.getEtkmrk(), dao.getEtktm(), dao.getEtktyp(), dao.getEtklk(), dao.getEtcref(), dao.getEtktkd(), dao.getEtsjaf(), dao.getEtems(), dao.getEtemst(),
			dao.getEtknt(), dao.getEtrgt(), dao.getEtnat(), dao.getEtna2t(), dao.getEtad1t(), dao.getEtnrt(), dao.getEtpnt(), dao.getEtpst(), dao.getEtlkt(), dao.getEtpbt(),
			dao.getEtemt(), dao.getEtemtt(), dao.getEtdkm(), dao.getEtdkmt(), dao.getEttsd(), 
			//id's
			dao.getEtlnrt(),
			} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadl:"+writer.toString());
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
			SadmotfDao dao = (SadmotfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" DELETE from "  + this.TABLE_NAME );
			if(StringUtils.isNotEmpty(dao.getEtmid())) {
				sql.append(" WHERE etmid = ? ");
				logger.info(sql.toString() + " etmid:" + dao.getEtmid());
				
				//params
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getEtmid(),
				} );
			}else {
				sql.append(" WHERE etlnrt = ? ");
				logger.info(sql.toString() + " etlnrt:" + dao.getEtlnrt());
				
				//params
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getEtlnrt(),
				} );
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadl:"+writer.toString());
			e.printStackTrace();
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		return retval;
	}
	
	/**
	 * DELETE Light
	 * Happens when the record must be retained and not removed. 
	 * 
	 */
	public int deleteLight(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE " + this.TABLE_NAME + " set etuuid = '', etmid = '', etuuid_own = '', etmid_own = '',   ");
			sql.append(" etst2 = ?, etst3 = '', etdtin = ? ");
			//id's
			sql.append(" WHERE etlnrt = ? ");
			sql.append(" AND etmid = ? " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtst2(), dao.getEtdtin(), dao.getEtlnrt(), dao.getEtmid() } );
			
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
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			logger.info("mydebug..." + "etst=" + dao.getEtst() + " etlnrt=" + dao.getEtlnrt());
			sql.append(" UPDATE " + this.TABLE_NAME + " set etst = ? ");
			//id's
			sql.append(" WHERE etlnrt = ? ");
			//params
			logger.info(sql.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtst(), dao.getEtlnrt() } );
			
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
	 */
	public int updateStatus2(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			logger.info("mydebug..." + "etst2=" + dao.getEtst2() + " etlnrt=" + dao.getEtlnrt());
			sql.append(" UPDATE " + this.TABLE_NAME + " set etst2 = ? ");
			//id's
			sql.append(" WHERE etlnrt = ? ");
			//params
			logger.info(sql.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtst2(), dao.getEtlnrt() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	public int updateStatus3(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			logger.info("mydebug..." + "etst3=" + dao.getEtst3() + " etlnrt=" + dao.getEtlnrt());
			sql.append(" UPDATE " + this.TABLE_NAME + " set etst3 = ? ");
			//id's
			sql.append(" WHERE etlnrt = ? ");
			//params
			logger.info(sql.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtst3(), dao.getEtlnrt() } );
			
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
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	
	public int updateLrnMrn(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			//sql.append(" UPDATE " + this.TABLE_NAME + " set etuuid = ?, emmid = ?, emdtin = ?, emst = ?, emst2 = ?, emst3 = ? ");
			//id's
			sql.append(" UPDATE " + this.TABLE_NAME + " set etuuid = ?, etmid = ?, etdtin = ?, etst = ?, etst2 = ?  ");
			sql.append(" WHERE etlnrt = ? ");
			//sql.append(" AND etmid = '' " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtuuid(), dao.getEtmid(), dao.getEtdtin(), dao.getEtst(), dao.getEtst2(),  
															dao.getEtlnrt() } );
			
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
	 * This method is used for the update of LRN after an API PUT (Update with mrn)
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int updateLrn(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE " + this.TABLE_NAME + " set etuuid = ?, etdtin = ?, etst = ?, etst2 = ?  ");
			//id's
			sql.append(" WHERE etlnrt = ? ");
			sql.append(" AND etmid = ?" );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtuuid(), dao.getEtdtin(), dao.getEtst(), dao.getEtst2(),
																	dao.getEtlnrt(), dao.getEtmid() } );
			
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
	 * Back up to MRN in-case we loose the original MRN (etmid)
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int setMrnBup(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE " + this.TABLE_NAME + " set etmid_own = ? ");
			//id's
			sql.append(" WHERE etmid = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtmid(), dao.getEtmid()} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	public int setRequestIdBup(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE " + this.TABLE_NAME + " set etuuid_own = ? ");
			//id's
			sql.append(" WHERE etlnrt = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtuuid(), dao.getEtlnrt()} );
			
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
	 */
	public int updateStatus2ForEntry(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmotfDao dao = (SadmotfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			logger.info("mydebug..." + "etst2=" + dao.getEtst2());
			logger.info("mydebug..." + "etentval=" + dao.getEtentval() + " etentoff=" + dao.getEtentoff() + " etenttim=" + dao.getEtenttim() );
			logger.info("mydebug..." + "etmid=" + dao.getEtmid() );
			
			sql.append(" UPDATE " + this.TABLE_NAME + " set etst2 = ?, etentval = ?, etentoff = ?, etenttim = ? ");
			//id's
			sql.append(" WHERE etmid = ? ");
			//params
			logger.info(sql.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEtst2(), dao.getEtentval(), dao.getEtentoff(), dao.getEtenttim(), 
					//id
					dao.getEtmid() } );
			if(retval>=0) {
				retval = updateStatus2ForEntryMasters(dao.getEtlnrt(), dao.getEtst2(), errorStackTrace);
				retval = updateStatus2ForEntryHouses(dao.getEtlnrt(), dao.getEtst2(), errorStackTrace);
				
			}
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	private int updateStatus2ForEntryMasters(Integer lnrt, String status, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
				
			StringBuffer sql = new StringBuffer();
			logger.info("mydebug...masters " + "st2= " + status +  "lnrt=" + lnrt);
			sql.append(" UPDATE sadmomf set emst2 = ? ");
			//id's
			sql.append(" WHERE emlnrt = ? ");
			//params
			logger.info(sql.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { status, lnrt } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	private int updateStatus2ForEntryHouses(Integer lnrt, String status, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
				
			StringBuffer sql = new StringBuffer();
			logger.info("mydebug...houses " + "st2= " + status +  "lnrt=" + lnrt);
			sql.append(" UPDATE sadmohf set ehst2 = ? ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			//params
			logger.info(sql.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { status, lnrt } );
			
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
	private int getNextEtlnrt() {
		int retval = 0;
	
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("mydebug");
		sql.append(" SELECT max(etlnrt)+1 from " + this.TABLE_NAME  );
		
		
		retval = this.jdbcTemplate.queryForObject( sql.toString(), Integer.class);
			
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
