package no.systema.jservices.tvinn.sad.sadimport.model.dao.services;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.sadimport.model.dao.entities.SadvDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Nov 2024
 * 
 */
public class SadvDaoServicesImpl implements SadvDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadvDaoServicesImpl.class.getName());
	private static ServletContext ctx;
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "sadv";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadvDao> retval = new ArrayList<SadvDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME+ " order by svli asc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadvDao.class));
			
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
		SadvDao dao = (SadvDao)obj;
		List<SadvDao> retval = new ArrayList<SadvDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from "  + TABLE_NAME+ " where svavd LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			if(StringUtils.isNotEmpty(dao.getSvavd())){ sql.append(" and svavd = ? " ); params.add(dao.getSvavd()); }
			if(StringUtils.isNotEmpty(dao.getSvtdn())){ sql.append(" and svtdn = ? "); params.add(dao.getSvtdn()); }
			if(StringUtils.isNotEmpty(dao.getSvli())){ sql.append(" and svli = ? "); params.add(dao.getSvli()); }
			
			/*if(dao.getEhlnrt()>0){ sql.append(" and ehlnrt = ? " ); params.add(dao.getEhlnrt()); }
			if(dao.getEhlnrm()>0){ sql.append(" and ehlnrm = ? "); params.add(dao.getEhlnrm()); }
			if(dao.getEhlnrh()>0){ sql.append(" and ehlnrh = ? "); params.add(dao.getEhlnrh()); }
			if(dao.getEhavd()>0){ sql.append(" and ehavd = ? " ); params.add(dao.getEhavd()); }
			if(dao.getEhpro()>0){ sql.append(" and ehpro = ? "); params.add(dao.getEhpro()); }
			if(dao.getEhtdn()>0){ sql.append(" and ehtdn = ? "); params.add(dao.getEhtdn()); }
			*/
			//if(dao.getEhdts()>0){ sql.append(" and ehdts >= ? "); params.add(dao.getEhdts()); }
			//if(dao.getOwn_efdtr()>0){ sql.append(" and emdtr <= ? "); params.add(dao.getOwn_efdtr()); }
			//if(dao.getEfeta()>0){ sql.append(" and emetad >= ? "); params.add(dao.getEmetad()); }
			//if(dao.getOwn_efeta()>0){ sql.append(" and emetad <= ? "); params.add(dao.getOwn_efeta()); }
			sql.append(" order by svli asc" );
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadvDao.class));
			
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
		
		return null;
	}
	

	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		return retval;
		
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		
		return retval;
		
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		
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
