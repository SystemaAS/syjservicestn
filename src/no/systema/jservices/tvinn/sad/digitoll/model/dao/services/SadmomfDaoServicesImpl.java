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
			if(StringUtils.isNotEmpty(dao.getEmsg())){ sql.append(" and efsg = ? "); params.add(dao.getEmsg()); }
			if(dao.getEmdtr()>0){ sql.append(" and emdtr >= ? "); params.add(dao.getEmdtr()); }
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
		//TODO
		/*
		try{
			SadexmfDao dao = (SadexmfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadexmf (TODO-->>, efst, efavd, efpro, efdtr, efsg, efst2, eftsd, efst3, efdtin, ");
			sql.append(" efeta, efetm, efata, efatm, ef3039e, efeid, efknd, efrgd, eftm, eftmt, ");
			sql.append(" efktyp, efktypt, efklk, efkmrk, efplk, efpmrk, efsjaf, efsjae, efsjalk, efsjadt, efbekr ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getEfuuid(),
					dao.getEfst(), dao.getEfavd(), dao.getEfpro(), dao.getEfdtr(), dao.getEfsg(), dao.getEfst2(), dao.getEftsd(), dao.getEfst3(), dao.getEfdtin(),
					dao.getEfeta(), dao.getEfetm(), dao.getEfata(), dao.getEfatm(), dao.getEf3039e(), dao.getEfeid(), dao.getEfknd(), dao.getEfrgd(), dao.getEftm(), dao.getEftmt(),
					dao.getEfktyp(), dao.getEfktypt(), dao.getEfklk(), dao.getEfkmrk(), dao.getEfplk(), dao.getEfpmrk(), dao.getEfsjaf(), dao.getEfsjae(), dao.getEfsjalk(), dao.getEfsjadt(),
					dao.getEfbekr(),

					} );
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
		
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		//TODO
		/*
		try{
			SadeffDao dao = (SadeffDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" UPDATE sadeff SET efst = ?, efavd = ?, efpro = ?, efdtr = ?, efsg = ?, efst2 = ?, eftsd = ?, efst3 = ?, efdtin = ?,  ");
			sql.append(" efeta = ? , efetm = ? , efata = ? , efatm = ? , ef3039e = ?, efeid = ?, efknd = ?, efrgd = ?, eftm = ?, eftmt = ?, ");
			sql.append(" efktyp = ? , efktypt = ? , efklk = ? , efkmrk = ? , efplk = ?, efpmrk = ?, efsjaf = ?, efsjae = ?, efsjalk = ?, efsjadt = ?, ");
			sql.append(" efbekr = ?  ");
			//id's
			sql.append(" WHERE efuuid = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getEfst(), dao.getEfavd(), dao.getEfpro(), dao.getEfdtr(), dao.getEfsg(), dao.getEfst2(), dao.getEftsd(), dao.getEfst3(), dao.getEfdtin(),
						dao.getEfeta(), dao.getEfetm(), dao.getEfata(), dao.getEfatm(), dao.getEf3039e(), dao.getEfeid(), dao.getEfknd(), dao.getEfrgd(), dao.getEftm(), dao.getEftmt(),
						dao.getEfktyp(), dao.getEfktypt(), dao.getEfklk(), dao.getEfkmrk(), dao.getEfplk(), dao.getEfpmrk(), dao.getEfsjaf(), dao.getEfsjae(), dao.getEfsjalk(), dao.getEfsjadt(),
						dao.getEfbekr(),
						//id's
						dao.getEfuuid(),
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadl:"+writer.toString());
			e.printStackTrace();
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
		
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		//NA --> refer to update status. There is never a true DELETE
		return 0;
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
	/*
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadeffDao dao = (SadeffDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadeff set efst = ? ");
			//id's
			sql.append(" WHERE efuuid = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEfst(), dao.getEfuuid() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	*/
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
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
