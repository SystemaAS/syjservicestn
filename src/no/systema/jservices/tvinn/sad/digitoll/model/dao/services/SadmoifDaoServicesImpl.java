package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmoifDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public class SadmoifDaoServicesImpl implements SadmoifDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmoifDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "sadmoif";
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadmoifDao> retval = new ArrayList<SadmoifDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME+ " order by eilnrt, eilnrm, eilnrh, eili desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadmoifDao.class));
			
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
		SadmoifDao dao = (SadmoifDao)obj;
		List<SadmoifDao> retval = new ArrayList<SadmoifDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME+ " where eilnrh LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			//if(dao.getEmavd()>0){ sql.append(" and emavd = ? " ); params.add(dao.getEmavd()); }
			//if(dao.getEmpro()>0){ sql.append(" and empro = ? "); params.add(dao.getEmpro()); }
			
			if(dao.getEilnrt()>0){ sql.append(" and eilnrt = ? " ); params.add(dao.getEilnrt()); }
			if(dao.getEilnrm()>0){ sql.append(" and eilnrm = ? "); params.add(dao.getEilnrm()); }
			if(dao.getEilnrh()>0){ sql.append(" and eilnrh = ? "); params.add(dao.getEilnrh()); }
			
			//if(dao.getEhdts()>0){ sql.append(" and ehdts >= ? "); params.add(dao.getEhdts()); }
			//if(dao.getOwn_efdtr()>0){ sql.append(" and emdtr <= ? "); params.add(dao.getOwn_efdtr()); }
			//if(dao.getEfeta()>0){ sql.append(" and emetad >= ? "); params.add(dao.getEmetad()); }
			//if(dao.getOwn_efeta()>0){ sql.append(" and emetad <= ? "); params.add(dao.getOwn_efeta()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadmoifDao.class));
			
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
	 * N/A
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List<SadmoifDao> retval = new ArrayList<SadmoifDao>();
		return retval;
	}
	
	/**
	 * 
	 */
	public List findById (Integer id, Integer eilnrt, Integer eilnrm, Integer eilnrh, StringBuffer errorStackTrace ){
		List<SadmoifDao> retval = new ArrayList<SadmoifDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + TABLE_NAME+ " where eili = ? ");
			sql.append(" and eilnrt = ? " ); 
			sql.append(" and eilnrm = ? "); 
			sql.append(" and eilnrh = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id, eilnrt, eilnrm, eilnrh }, new BeanPropertyRowMapper(SadmoifDao.class));
			
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
		//TODO ..
		/*
		try{
			SadmoifDao dao = (SadmoifDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set ehuuid = '', ehmid = '', ehst2 = ?, ehst3 = '', ehdts = ? ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			sql.append(" AND ehlnrm = ?" );
			sql.append(" AND ehlnrh = ?" );
			sql.append(" AND ehmid = ? " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhst2(), dao.getEhdts(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh(), dao.getEhmid() } );
			
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
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
