package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefdefDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
public class SadefdefDaoServicesImpl implements SadefdefDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadefdefDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadefdefDao> retval = new ArrayList<SadefdefDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//sql.append(" select * from sadeff order by rrn(sadeff) desc");
			sql.append(" select * from sadefdef order by efavd");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadefdefDao.class));
			
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
		SadefdefDao dao = (SadefdefDao)obj;
		List<SadefdefDao> retval = new ArrayList<SadefdefDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from sadefdef where efavd LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			if(dao.getEfpro()>0){ sql.append(" and efpro = ? "); params.add(dao.getEfpro()); }
			if(StringUtils.isNotEmpty(dao.getEfsg())){ sql.append(" and efsg = ? "); params.add(dao.getEfsg()); }
			if(dao.getEfdtr()>0){ sql.append(" and efdtr >= ? "); params.add(dao.getEfdtr()); }
			if(dao.get_efdtrt()>0){ sql.append(" and efdtr <= ? "); params.add(dao.get_efdtrt()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadefdefDao.class));
			
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
		List<SadefdefDao> retval = new ArrayList<SadefdefDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from sadefdef where efavd = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadefdefDao.class));
			
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
			SadefdefDao dao = (SadefdefDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadefdef (efuuid, efst, efavd, efpro, efdtr, efsg, efst2, eftsd, efst3, efdtin, ");
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
			logger.warn(writer.toString());
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
			SadefdefDao dao = (SadefdefDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TEST
			/*sql.append(" UPDATE sadeff SET efavd = ? " );
			sql.append(" WHERE efuuid = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEfavd(),  dao.getEfuuid() });
			*/
			sql.append(" UPDATE sadefdef SET efst = ?, efavd = ?, efpro = ?, efdtr = ?, efsg = ?, efst2 = ?, eftsd = ?, efst3 = ?, efdtin = ?,  ");
			sql.append(" efeta = ? , efetm = ? , efata = ? , efatm = ? , ef3039e = ?, efeid = ?, efknd = ?, efrgd = ?, eftm = ?, eftmt = ?, ");
			sql.append(" efktyp = ? , efktypt = ? , efklk = ? , efkmrk = ? , efplk = ?, efpmrk = ?, efsjaf = ?, efsjae = ?, efsjalk = ?, efsjadt = ?, ");
			sql.append(" efbekr = ?  ");
			//id's
			sql.append(" WHERE efavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getEfst(), dao.getEfavd(), dao.getEfpro(), dao.getEfdtr(), dao.getEfsg(), dao.getEfst2(), dao.getEftsd(), dao.getEfst3(), dao.getEfdtin(),
						dao.getEfeta(), dao.getEfetm(), dao.getEfata(), dao.getEfatm(), dao.getEf3039e(), dao.getEfeid(), dao.getEfknd(), dao.getEfrgd(), dao.getEftm(), dao.getEftmt(),
						dao.getEfktyp(), dao.getEfktypt(), dao.getEfklk(), dao.getEfkmrk(), dao.getEfplk(), dao.getEfpmrk(), dao.getEfsjaf(), dao.getEfsjae(), dao.getEfsjalk(), dao.getEfsjadt(),
						dao.getEfbekr(),
						//id's
						dao.getEfavd(),
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.warn("Exception in update Sadl:"+writer.toString());
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
			SadefdefDao dao = (SadefdefDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from sadefdef ");
			//id's
			sql.append(" WHERE efavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEfavd() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.warn(writer.toString());
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
