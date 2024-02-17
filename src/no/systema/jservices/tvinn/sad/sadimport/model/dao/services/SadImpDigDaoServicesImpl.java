package no.systema.jservices.tvinn.sad.sadimport.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.sadimport.model.dao.entities.SadImpDigDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */
public class SadImpDigDaoServicesImpl implements SadImpDigDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadImpDigDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadImpDigDao> retval = new ArrayList<SadImpDigDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where a.sitdn > 0 "); //remove all omberegnings
			sql.append(" order by a.sidt desc, a.sitdn desc  ");
			sql.append(" FETCH FIRST 2000 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadImpDigDao.class));
			
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
		SadImpDigDao dao = (SadImpDigDao)obj;
		List<SadImpDigDao> retval = new ArrayList<SadImpDigDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_CLAUSE());
			//bara för att bli kvit WHERE
			sql.append(" where a.sitdn > 0 "); //remove all omberegnings
			//sql.append(" where a.sitdn LIKE ? "); params.add(SQL_WILD_CARD); 
			//let the show begin
			if(dao.getSiavd()>0){ sql.append(" and a.siavd = ? "); params.add(dao.getSiavd()); }
			if(StringUtils.isNotEmpty(dao.getSisg())){ sql.append(" and a.sisg = ? "); params.add(dao.getSisg()); }
			if(StringUtils.isNotEmpty(dao.getSist())){ sql.append(" and a.sist = ? "); params.add(dao.getSist()); }
			//Eksped.sted
			if(StringUtils.isNotEmpty(dao.getSitle())){ sql.append(" and a.sitle = ? "); params.add(dao.getSitle()); }
			//Løpenr
			if(StringUtils.isNotEmpty(dao.getSitll())){ sql.append(" and a.sitll = ? "); params.add(dao.getSitll()); }
			//Bilnr
			if(StringUtils.isNotEmpty(dao.getSitrid())){ sql.append(" and a.sitrid = ? "); params.add(dao.getSitrid()); }
			//Godsnr
			if(StringUtils.isNotEmpty(dao.getSign())){ sql.append(" and a.sign = ? "); params.add(dao.getSign()); }
			//Parties
			if(StringUtils.isNotEmpty(dao.getSinas())){ sql.append(" and a.sinas LIKE ? "); params.add(SQL_WILD_CARD + dao.getSinas() + SQL_WILD_CARD); }
			if(StringUtils.isNotEmpty(dao.getSinak())){ sql.append(" and a.sinak LIKE ? "); params.add(SQL_WILD_CARD + dao.getSinak() + SQL_WILD_CARD); }
			//Dates fom-tom
			if(dao.getSidt()>0){ sql.append(" and a.sidt >= ? "); params.add(dao.getSidt()); }
			if(dao.getSidt_to()>0){ sql.append(" and a.sidt <= ? "); params.add(dao.getSidt_to()); }
			//order by
			sql.append(" order by a.sidt desc, a.sitdn desc  ");
			
			//security issue in case dates are empty since the whole db at a customer site is full of many years back
			if(dao.getSidt()==0) {
				sql.append(" FETCH FIRST 2000 ROWS ONLY ");
			}
			
			logger.warn(sql.toString());
			logger.warn(params.toString());
			
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadImpDigDao.class));
			
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
		List<SadImpDigDao> retval = new ArrayList<SadImpDigDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(this.getSELECT_CLAUSE());
			//91670
			sql.append(" where a.sitdn = ? ");
			//and a.sidt > 20190101
			
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadImpDigDao.class));
			
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
	 * Always constant
	 * @return
	 */
	private String getSELECT_CLAUSE() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.siavd,a.sisg,a.sist,a.sitdn,a.sidt,a.sidtg,a.sitrid,a.sitle,a.sitll,a.sinas,a.sinak,a.sivkb,a.sign, ");
		sql.append(" varchar_format(to_date(char(a.sidt),'YYYYMMDD'),'DDMMYY') sidtno, ");
		sql.append(" b.etlnrt, b.etpro, b.etkmrk, ");
		sql.append(" c.emdkm, d.ehrgm, d.ehpro, d.ehtdn, d.ehdkh, d.ehvkb, d.ehvt " );
		//sql.append(" c.emdkm, d.ehrgm " );
		sql.append(" from sadh as a " );
		sql.append(" full outer join sadmotf AS b  " );
		sql.append(" on a.sitrid = b.etkmrk " ); 
		sql.append(" full outer join sadmomf AS c " );
		sql.append(" on b.etlnrt = c.emlnrt " );
		sql.append(" full outer join sadmohf AS d " );
		sql.append(" on a.sirg = d.ehrgm " );
		sql.append(" and b.etlnrt = d.ehlnrt " );
		
		return sql.toString();
		
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}