package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmohfDao;
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
		int nextEili = -1;
		
		logger.info("before INSERT");
		try{
			
			SadmoifDao dao = (SadmoifDao)daoObj;
			//we must check if this is not the record nr 1 otherwise there will fail in: getNext...
			List list = this.find(daoObj, errorStackTrace);
			logger.info(list.toString());
			if(list==null || list.size()<=0 ) {
				nextEili= 1;
			}else {
				nextEili =  getNextEili( dao.getEilnrt(), dao.getEilnrm(), dao.getEilnrh() );
			}
			dao.setEili(nextEili);
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( eist, eipro, eiavd, eitdn, eili,  ");
			sql.append(" eilnrt, eilnrm, eilnrh, eibl, eistk, eivnt, eirge, eiroe) ");
			
			sql.append(" VALUES ( ?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,? ) ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEist(), dao.getEipro(), dao.getEiavd(), dao.getEitdn(), dao.getEili(), 		
			dao.getEilnrt(), dao.getEilnrm(), dao.getEilnrh(), dao.getEibl(), dao.getEistk(), dao.getEivnt(), dao.getEirge(), dao.getEiroe(), 
			
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
			retval = nextEili;
		}
		logger.info("after INSERT --> retval:" + nextEili);
		return retval;
		
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadmoifDao dao = (SadmoifDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET  eist = ?, eipro = ?, eiavd = ?, eitdn = ?,   ");
			sql.append(" eibl = ?, eistk = ?, eivnt = ?, eirge = ?, eiroe = ? ");
			//id's
			sql.append(" WHERE eilnrt = ? AND eilnrm = ? AND eilnrh = ? AND eili = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEist(), dao.getEipro(), dao.getEiavd(), dao.getEitdn(), 		
			dao.getEibl(), dao.getEistk(), dao.getEivnt(), dao.getEirge(), dao.getEiroe(), 
			//id's
			dao.getEilnrt(), dao.getEilnrm(), dao.getEilnrh(), dao.getEili(),
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
			SadmoifDao dao = (SadmoifDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" DELETE from "  + this.TABLE_NAME );
			sql.append(" WHERE eilnrt = ? AND eilnrm = ? AND eilnrh = ? AND eili = ? ");
			logger.info(sql.toString() + " eilnrt:" + dao.getEilnrt() + " eilnrm:" + dao.getEilnrm() + " eilnrh:" + dao.getEilnrh() + " eili:" + dao.getEili());
				
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEilnrt(), dao.getEilnrm(), dao.getEilnrh(), dao.getEili()
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
	 * 
	 * @param eilnrt
	 * @param eilnrm
	 * @param eilnrh
	 * @return
	 */
	private int getNextEili(Integer eilnrt, Integer eilnrm, Integer eilnrh ) {
		int retval = 0;
		logger.info("eilnrt/eilnrm/eilnrh for NextSeed:" + eilnrt + "/" + eilnrm + "/" + eilnrh);
		
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("mydebug");
		sql.append(" SELECT max(eili)+1 from " + this.TABLE_NAME  );
		sql.append(" WHERE eilnrt = ? AND eilnrm = ? AND eilnrh = ? " );
		
		retval = this.jdbcTemplate.queryForObject( sql.toString(), new Object[] { eilnrt, eilnrm, eilnrh },  Integer.class );
			
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
