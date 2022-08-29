package no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexhfDao;
import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexmfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2022
 * 
 */
public class SadexhfDaoServicesImpl implements SadexhfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadexhfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadexhfDao> retval = new ArrayList<SadexhfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from sadexhf order by ehtdn desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadexhfDao.class));
			
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
		SadexhfDao dao = (SadexhfDao)obj;
		List<SadexhfDao> retval = new ArrayList<SadexhfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from sadexhf where ehavd LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			if(dao.getEhavd()>0){ sql.append(" and ehavd = ? " ); params.add(dao.getEhavd()); }
			if(dao.getEhpro()>0){ sql.append(" and ehpro = ? "); params.add(dao.getEhpro()); }
			if(dao.getEhtdn()>0){ sql.append(" and ehtdn = ? "); params.add(dao.getEhtdn()); }
			
			if(StringUtils.isNotEmpty(dao.getEhmid())){ sql.append(" and ehmid = ? "); params.add(dao.getEhmid()); }
			if(StringUtils.isNotEmpty(dao.getEhst())){ sql.append(" and ehst = ? "); params.add(dao.getEhst()); }
			if(StringUtils.isNotEmpty(dao.getEhst2())){ sql.append(" and ehst2 = ? "); params.add(dao.getEhst2()); }
			if(StringUtils.isNotEmpty(dao.getEhst3())){ sql.append(" and ehst3 = ? "); params.add(dao.getEhst3()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadexhfDao.class));
			
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
		List<SadexhfDao> retval = new ArrayList<SadexhfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from sadexhf where ehmid = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadexhfDao.class));
			
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
			SadexhfDao dao = (SadexhfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadexhf (TODO-->>, efst, efavd, efpro, efdtr, efsg, efst2, eftsd, efst3, efdtin, ");
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
	 * DELETE Light
	 * Happens when the record must be retained and not removed. 
	 * 
	 */
	public int deleteLight(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadexhfDao dao = (SadexhfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			//TODO with sendDate --> sql.append(" UPDATE sadexhf set ehuuid = '', ehmid = '', emdtin = ? ");
			sql.append(" UPDATE sadexhf set ehuuid = '', ehmid = '' ");
			//id's
			sql.append(" WHERE ehavd = ? ");
			sql.append(" AND ehpro = ?" );
			sql.append(" AND ehtdn = ?" );
			sql.append(" AND ehmid = ? " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhavd(), dao.getEhpro(), dao.getEhpro(), dao.getEhmid() } );
			
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
			SadexhfDao dao = (SadexhfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadexhf set ehuuid = ?, ehmid = ? ");
			//id's
			sql.append(" WHERE ehavd = ? ");
			sql.append(" AND ehpro = ?" );
			sql.append(" AND ehtdn = ?" );
			
			sql.append(" AND ehmid = '' " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhuuid(), dao.getEhmid(), dao.getEhavd(), dao.getEhpro(), dao.getEhtdn() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	public int updateLrn(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadexhfDao dao = (SadexhfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			//sql.append(" UPDATE sadexhf set ehuuid = ?, ehdtin = ?  ");
			sql.append(" UPDATE sadexhf set ehuuid = ?  ");
			//id's
			sql.append(" WHERE ehavd = ? ");
			sql.append(" AND ehpro = ?" );
			sql.append(" AND ehtdn = ?" );
			sql.append(" AND ehmid = ?" );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhuuid(), dao.getEhavd(), dao.getEhpro(), dao.getEhtdn(), dao.getEhmid() } );
			
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
