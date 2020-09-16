package no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefcfDao;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadeffDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
public class SadefcfDaoServicesImpl implements SadefcfDaoServices {
	private static Logger logger = Logger.getLogger(SadefcfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadeffDao> retval = new ArrayList<SadeffDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//sql.append(" select * from sadeff order by rrn(sadeff) desc");
			sql.append(" select * from sadefcf order by clpro desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new BeanPropertyRowMapper(SadefcfDao.class));
			
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
		SadefcfDao dao = (SadefcfDao)obj;
		List<SadefcfDao> retval = new ArrayList<SadefcfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from sadefcf where clpro = ?" );
			params.add(dao.getClpro());
			//walk through the filter fields
			if(dao.getClavd()>0){ sql.append(" and clavd = ? " ); params.add(dao.getClavd()); }
			if(dao.getCltdn()>0){ sql.append(" and cltdn = ? "); params.add(dao.getCltdn()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(SadefcfDao.class));
			
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
		List<SadefcfDao> retval = new ArrayList<SadefcfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from sadefcf where clpro = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadefcfDao.class));
			
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
			SadefcfDao dao = (SadefcfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO sadefcf (clst, clavd, clpro, cltdn, clrg, cl0068a, cl0068b, clntk, clvkb, ");
			sql.append(" clvt, cltrid, cl3039e, cllkf, clsdf, clsdft, cllkt, clsdt, clsdtt, clpr, ");
			sql.append(" clprt, cletyp, cletypt, cleid, cleser ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getClst(),dao.getClavd(), dao.getClpro(), dao.getCltdn(), dao.getClrg(), dao.getCl0068a(), dao.getCl0068b(), dao.getClntk(), dao.getClvkb(),
					dao.getClvt(), dao.getCltrid(), dao.getCl3039e(), dao.getCllkf(), dao.getClsdf(), dao.getClsdft(), dao.getCllkt(), dao.getClsdt(), dao.getClsdtt(), dao.getClpr(),
					dao.getClprt(), dao.getCletyp(), dao.getCletypt(), dao.getCleid(), dao.getCleser(),

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
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadefcfDao dao = (SadefcfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TEST
			/*sql.append(" UPDATE sadeff SET efavd = ? " );
			sql.append(" WHERE efuuid = ? ");
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEfavd(),  dao.getEfuuid() });
			*/
			sql.append(" UPDATE sadefcf SET clst = ?, clavd = ?, cltdn = ?, clrg = ?, cl0068a = ?, cl0068b = ?, clntk = ?, clvkb = ?,  ");
			sql.append(" clvt = ? , cltrid = ? , cl3039e = ? , cllkf = ? , clsdf = ?, clsdft = ?, cllkt = ?, clsdt = ?, clsdtt = ?, clpr = ?, ");
			sql.append(" clprt = ? , cletyp = ? , cletypt = ? , cleid = ? , cleser = ?  ");
			//id's
			sql.append(" WHERE clpro = ? ");
			sql.append(" AND cltdn = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getClst(), dao.getClavd(), dao.getCltdn(), dao.getClrg(), dao.getCl0068a(), dao.getCl0068b(), dao.getClntk(), dao.getClvkb(), 
						dao.getClvt(), dao.getCltrid(), dao.getCl3039e(), dao.getCllkf(), dao.getClsdf(), dao.getClsdft(), dao.getCllkt(), dao.getClsdt(), dao.getClsdtt(), dao.getClpr(),
						dao.getClprt(), dao.getCletyp(), dao.getCletypt(), dao.getCleid(), dao.getCleser(),
						//id's
						dao.getClpro(),dao.getCltdn(),
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Sadefcf:"+writer.toString());
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
			SadefcfDao dao = (SadefcfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadefcf set clst = ? ");
			//id's
			sql.append(" WHERE clpro = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getClst(), dao.getClpro() } );
			
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
	 * When the release of a record is necessary. Usually when the record must return to an unbound state = without a tur(clpro)
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int release(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		int DELETE_VALUE_TUR = 0;
		
		try{
			SadefcfDao dao = (SadefcfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadefcf set clpro = ?, clst = ? ");
			//id's
			sql.append(" WHERE clpro = ? AND cltdn = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { DELETE_VALUE_TUR, dao.getClst(), dao.getClpro(), dao.getCltdn() } );
			
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
	 * When the release of a record is necessary. Usually when the record must return to an unbound state = without a tur(clpro)
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int bindTur(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			SadefcfDao dao = (SadefcfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE sadefcf set clpro = ? ");
			//id's
			sql.append(" WHERE clpro = ? AND cltdn = ? ");
			
			//params
			logger.warn(sql.toString());
			logger.warn(dao.toString());
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getClpro(), 0, dao.getCltdn() } );
			
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
