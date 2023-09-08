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
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public class SadmomfDaoServicesImpl implements SadmomfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmomfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "sadmomf";
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadmomfDao> retval = new ArrayList<SadmomfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME+ " order by emdtr desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadmomfDao.class));
			
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
		SadmomfDao dao = (SadmomfDao)obj;
		List<SadmomfDao> retval = new ArrayList<SadmomfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from "  + TABLE_NAME+ " where emlnrt LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			//if(dao.getEmavd()>0){ sql.append(" and emavd = ? " ); params.add(dao.getEmavd()); }
			//if(dao.getEmpro()>0){ sql.append(" and empro = ? "); params.add(dao.getEmpro()); }
			
			if(dao.getEmlnrt()>0){ sql.append(" and emlnrt = ? " ); params.add(dao.getEmlnrt()); }
			if(dao.getEmlnrm()>0){ sql.append(" and emlnrm = ? "); params.add(dao.getEmlnrm()); }
			if(dao.getEmavd()>0){ sql.append(" and emavd = ? " ); params.add(dao.getEmavd()); }
			if(dao.getEmpro()>0){ sql.append(" and empro = ? "); params.add(dao.getEmpro()); }
			if(StringUtils.isNotEmpty(dao.getEmsg())){ sql.append(" and emsg = ? "); params.add(dao.getEmsg()); }
			//dates
			if(dao.getEmdtr()>0){ 
				sql.append(" and emdtr >= ? "); params.add(dao.getEmdtr()); 
			}
			//if(dao.getOwn_efdtr()>0){ sql.append(" and emdtr <= ? "); params.add(dao.getOwn_efdtr()); }
			//if(dao.getEfeta()>0){ sql.append(" and emetad >= ? "); params.add(dao.getEmetad()); }
			//if(dao.getOwn_efeta()>0){ sql.append(" and emetad <= ? "); params.add(dao.getOwn_efeta()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmomfDao.class));
			
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
		List<SadmomfDao> retval = new ArrayList<SadmomfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + TABLE_NAME+ " where emmid = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadmomfDao.class));
			
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
		List<SadmomfDao> retval = new ArrayList<SadmomfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from "  + TABLE_NAME+ " where emuuid = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadmomfDao.class));
			
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
		int nextEmlnrm = -1;
		
		logger.info("before INSERT");
		try{
			
			SadmomfDao dao = (SadmomfDao)daoObj;
			//we must check if this is not the record nr 1 otherwise there will fail in: getNext...
			List list = this.find(daoObj, errorStackTrace);
			logger.info(list.toString());
			if(list==null || list.size()<=0 ) {
				nextEmlnrm = 1;
			}else {
				nextEmlnrm =  getNextEmlnrm( dao.getEmlnrt());
			}
			dao.setEmlnrm(nextEmlnrm);
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( emavd, empro, emlnrt, emlnrm, emknt, emrgt, ");
			sql.append(" emdtr, emsg, emst, emst2, emuuid, emmid, emst3, emdtin, ematdd, emcn, emvkb, ");
			sql.append(" emrcem1, emrcem2, emrcem3, ");
			sql.append(" emknm, emrgm, emtppm, emnam, emna2m, emad1m, emnrm, empnm, empsm, emlkm, empbm, ememm, ememmt,  ");
			sql.append(" emkns, emrgs, emtpps, emnas, emna2s, emad1s, emnrs, empns, empss, emlks, empbs, emems, ememst,  ");
			sql.append(" emdkm, emdkmt, emc1ty, emc1ps, emc1ss, emc1id, emc2ty, emc2ps, emc2ss, emc2id,  emc3ty, emc3ps, emc3ss, emc3id,  ");
			sql.append(" emlkl, emsdl, emsdlt, emlku, emsdu, emsdut, emlkd, emsdd, emsddt ) ");
			
			sql.append(" VALUES ( ?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,? ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEmavd(), dao.getEmpro(), dao.getEmlnrt(), dao.getEmlnrm(), dao.getEmknt(), dao.getEmrgt(),		
			dao.getEmdtr(), dao.getEmsg(), dao.getEmst(), dao.getEmst2(), dao.getEmuuid(), dao.getEmmid(), dao.getEmst3(), dao.getEmdtin(), dao.getEmatdd(), dao.getEmcn(), dao.getEmvkb(),
			dao.getEmrcem1(), dao.getEmrcem2(), dao.getEmrcem3(),
			
			dao.getEmknm(), dao.getEmrgm(), dao.getEmtppm(), dao.getEmnam(), dao.getEmna2m(), dao.getEmad1m(), dao.getEmnrm(), dao.getEmpnm(), dao.getEmpsm(), dao.getEmlkm(), dao.getEmpbm(), dao.getEmemm(), dao.getEmemmt(),
			dao.getEmkns(), dao.getEmrgs(), dao.getEmtpps(), dao.getEmnas(), dao.getEmna2s(), dao.getEmad1s(), dao.getEmnrs(), dao.getEmpns(), dao.getEmpss(), dao.getEmlks(), dao.getEmpbs(), dao.getEmems(), dao.getEmemst(),
			dao.getEmdkm(), dao.getEmdkmt(), dao.getEmc1ty(), dao.getEmc1ps(), dao.getEmc1ss(), dao.getEmc1id(), dao.getEmc2ty(), dao.getEmc2ps(), dao.getEmc2ss(), dao.getEmc2id(), dao.getEmc3ty(), dao.getEmc3ps(), dao.getEmc3ss(), dao.getEmc3id(),
			dao.getEmlkl(), dao.getEmsdl(), dao.getEmsdlt(), dao.getEmlku(), dao.getEmsdu(), dao.getEmsdut(), dao.getEmlkd(), dao.getEmsdd(), dao.getEmsddt(),
			
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
			retval = nextEmlnrm;
		}
		logger.info("after INSERT --> retval:" + nextEmlnrm);
		return retval;
		
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmomfDao dao = (SadmomfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET emavd = ?, empro = ?, emknt = ?, emrgt = ?, ");
			sql.append(" emdtr = ?, emsg = ?, emst = ?, emst2 = ?, emuuid = ?, emmid = ?, emst3 = ?, emdtin = ?, ematdd = ?, emcn = ?, emvkb = ?, ");
			sql.append(" emrcem1 = ?, emrcem2 = ?, emrcem3 = ?,  ");
			sql.append(" emknm = ? , emrgm = ? , emtppm = ? ,emnam = ?, emna2m = ?, emad1m = ?, emnrm = ?, empnm = ?, empsm = ?, emlkm = ?, empbm = ?, ememm = ?, ememmt = ?, ");
			sql.append(" emkns = ? , emrgs = ? , emtpps = ? , emnas = ?, emna2s = ?, emad1s = ?, emnrs = ?, empns = ?, empss = ?, emlks = ?, empbs = ?, emems = ?, ememst = ?, ");
			sql.append(" emdkm = ? , emdkmt = ? , emc1ty = ? , emc1ps = ? , emc1ss = ?, emc1id = ?, emc2ty = ? , emc2ps = ? , emc2ss = ?, emc2id = ?,  emc3ty = ? , emc3ps = ? , emc3ss = ?, emc3id = ?, ");
			sql.append(" emlkl = ? , emsdl = ? , emsdlt = ?, emlku = ?, emsdu = ?, emsdut = ?, emlkd = ?, emsdd = ?, emsddt = ? ");
			
			//id's
			sql.append(" WHERE emlnrt = ? AND emlnrm = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEmavd(), dao.getEmpro(), dao.getEmknt(), dao.getEmrgt(),		
			dao.getEmdtr(), dao.getEmsg(), dao.getEmst(), dao.getEmst2(), dao.getEmuuid(), dao.getEmmid(), dao.getEmst3(), dao.getEmdtin(), dao.getEmatdd(), dao.getEmcn(), dao.getEmvkb(),
			dao.getEmrcem1(), dao.getEmrcem2(), dao.getEmrcem3(),
			dao.getEmknm(), dao.getEmrgm(), dao.getEmtppm(), dao.getEmnam(), dao.getEmna2m(), dao.getEmad1m(), dao.getEmnrm(), dao.getEmpnm(), dao.getEmpsm(), dao.getEmlkm(), dao.getEmpbm(), dao.getEmemm(), dao.getEmemmt(),
			dao.getEmkns(), dao.getEmrgs(), dao.getEmtpps(), dao.getEmnas(), dao.getEmna2s(), dao.getEmad1s(), dao.getEmnrs(), dao.getEmpns(), dao.getEmpss(), dao.getEmlks(), dao.getEmpbs(), dao.getEmems(), dao.getEmemst(),
			dao.getEmdkm(), dao.getEmdkmt(), dao.getEmc1ty(), dao.getEmc1ps(), dao.getEmc1ss(), dao.getEmc1id(), dao.getEmc2ty(), dao.getEmc2ps(), dao.getEmc2ss(), dao.getEmc2id(), dao.getEmc3ty(), dao.getEmc3ps(), dao.getEmc3ss(), dao.getEmc3id(),
			dao.getEmlkl(), dao.getEmsdl(), dao.getEmsdlt(), dao.getEmlku(), dao.getEmsdu(), dao.getEmsdut(), dao.getEmlkd(), dao.getEmsdd(), dao.getEmsddt(),
			//id's
			dao.getEmlnrt(), dao.getEmlnrm(),
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
	 * This method is not used at the moment.
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmomfDao dao = (SadmomfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" DELETE from "  + this.TABLE_NAME );
			if(StringUtils.isNotEmpty(dao.getEmmid())) {
				sql.append(" WHERE emmid = ? ");
				logger.info(sql.toString() + " emmid:" + dao.getEmmid());
				
				//params
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getEmmid(),
				} );
			}else {
				sql.append(" WHERE emlnrt = ? AND emlnrm = ? ");
				logger.info(sql.toString() + " emlnrt:" + dao.getEmlnrt() + " emlnrm:" + dao.getEmlnrm());
				
				//params
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
				dao.getEmlnrt(), dao.getEmlnrm(),
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
			SadmomfDao dao = (SadmomfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set emuuid = '', emmid = '', emst2 = ?, emst3 = '', emdtin = ? ");
			//id's
			sql.append(" WHERE emlnrt = ? ");
			sql.append(" AND emlnrm = ?" );
			sql.append(" AND emmid = ? " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEmst2(), dao.getEmdtin(), dao.getEmlnrt(), dao.getEmlnrm(), dao.getEmmid() } );
			
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
			SadmomfDao dao = (SadmomfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadmomf set emst = ? ");
			//id's
			sql.append(" WHERE emlnrt = ? ");
			sql.append(" AND emlnrm = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEmst(), dao.getEmlnrt(), dao.getEmlnrm() } );
			
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
			SadmomfDao dao = (SadmomfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set emuuid = ?, emmid = ?, emdtin = ?, emst = ?, emst2 = ?, emst3 = ? ");
			//id's
			sql.append(" WHERE emlnrt = ? ");
			sql.append(" AND emlnrm = ?" );
			sql.append(" AND emmid = '' " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEmuuid(), dao.getEmmid(), dao.getEmdtin(), dao.getEmst(), dao.getEmst2(), dao.getEmst3(),  
															dao.getEmlnrt(), dao.getEmlnrm() } );
			
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
			SadmomfDao dao = (SadmomfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set emuuid = ?, emdtin = ?, emst = ?, emst2 = ?, emst3 = ?  ");
			//id's
			sql.append(" WHERE emlnrt = ? ");
			sql.append(" AND emlnrm = ?" );
			sql.append(" AND emmid = ?" );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEmuuid(), dao.getEmdtin(), dao.getEmst(), dao.getEmst2(), dao.getEmst3(),
																dao.getEmlnrt(), dao.getEmlnrm(), dao.getEmmid() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	private int getNextEmlnrm(Integer emlnrt) {
		int retval = 0;
		logger.info("emlnrt for NextSeed:" + emlnrt);
		
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("mydebug");
		sql.append(" SELECT max(emlnrm)+1 from " + this.TABLE_NAME  );
		sql.append(" WHERE emlnrt = ? " );
		
		retval = this.jdbcTemplate.queryForObject( sql.toString(), Integer.class, emlnrt );
			
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
