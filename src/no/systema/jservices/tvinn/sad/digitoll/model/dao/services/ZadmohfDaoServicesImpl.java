package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.ZadmohfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @date Aug 2023
 * 
 */
public class ZadmohfDaoServicesImpl implements ZadmohfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(ZadmohfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "zadmohf";
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<ZadmohfDao> retval = new ArrayList<ZadmohfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME+ " order by ehdts desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(ZadmohfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	private List getList(Integer ehlnrt, Integer ehlnrm, StringBuffer errorStackTrace){
		List<SadmomfDao> retval = new ArrayList<SadmomfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME);
			sql.append(" where ehlnrt = ? ");
			sql.append(" and ehlnrm = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { ehlnrt, ehlnrm }, new BeanPropertyRowMapper(ZadmohfDao.class));
			
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
		ZadmohfDao dao = (ZadmohfDao)obj;
		List<ZadmohfDao> retval = new ArrayList<ZadmohfDao>();
		LinkedList<Object> params = new LinkedList<Object>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from "  + TABLE_NAME+ " where ehlnrh LIKE ?" );
			params.add(SQL_WILD_CARD);
			//walk through the filter fields
			//if(dao.getEmavd()>0){ sql.append(" and emavd = ? " ); params.add(dao.getEmavd()); }
			//if(dao.getEmpro()>0){ sql.append(" and empro = ? "); params.add(dao.getEmpro()); }
			
			if(StringUtils.isNotEmpty(dao.getEhdkm_fh())){ sql.append(" and ehdkm_fh = ? " ); params.add(dao.getEhdkm_fh()); }
			/*if(dao.getEhlnrt()>0){ sql.append(" and ehlnrt = ? " ); params.add(dao.getEhlnrt()); }
			if(dao.getEhlnrm()>0){ sql.append(" and ehlnrm = ? "); params.add(dao.getEhlnrm()); }
			if(dao.getEhlnrh()>0){ sql.append(" and ehlnrh = ? "); params.add(dao.getEhlnrh()); }
			if(dao.getEhavd()>0){ sql.append(" and ehavd = ? " ); params.add(dao.getEhavd()); }
			if(dao.getEhpro()>0){ sql.append(" and ehpro = ? "); params.add(dao.getEhpro()); }
			if(dao.getEhtdn()>0){ sql.append(" and ehtdn = ? "); params.add(dao.getEhtdn()); }
			*/
			if(StringUtils.isNotEmpty(dao.getEhdkh())){ sql.append(" and ehdkh = ? " ); params.add(dao.getEhdkh()); }
			if(dao.getEhdts()>0){ sql.append(" and ehdts >= ? "); params.add(dao.getEhdts()); }
			//if(dao.getOwn_efdtr()>0){ sql.append(" and emdtr <= ? "); params.add(dao.getOwn_efdtr()); }
			//if(dao.getEfeta()>0){ sql.append(" and emetad >= ? "); params.add(dao.getEmetad()); }
			//if(dao.getOwn_efeta()>0){ sql.append(" and emetad <= ? "); params.add(dao.getOwn_efeta()); }
			logger.warn(sql.toString());
			logger.warn(params.toString());
			retval = this.jdbcTemplate.query( sql.toString(), params.toArray(new Object[0]), new BeanPropertyRowMapper(ZadmohfDao.class));
			
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
		List<ZadmohfDao> retval = new ArrayList<ZadmohfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + TABLE_NAME+ " where ehdkm_fh = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(ZadmohfDao.class));
			
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
	/*
	public List findByLrn (String id, StringBuffer errorStackTrace ){
		List<ZadmohfDao> retval = new ArrayList<ZadmohfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from "  + TABLE_NAME+ " where ehuuid = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(ZadmohfDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		
		return retval;
	}
	*/
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		logger.info("before INSERT");
		try{
			
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( ehavd, ehpro, ehtdn, ehlnrt, ehlnrm, ehlnrh, ehdkm_fh,  ");
			sql.append(" ehst, ehst2, ehst3, ehuuid, ehmid, ehdts, ehtms, ehrecid, ehcnin, ehvkb, ehntk, ehextref, ");
			sql.append(" ehvt, ehdkh, ehdkht, ehpr, ehprt, ehupr, ehuprt, ehrg, eh0068a, eh0068b, ehtrnr, ehtrty, ");
			//
			sql.append(" ehtrnr2, ehtrty2, ehtrnr3, ehtrty3, ehtrnr4, ehtrty4, ehtrnr5, ehtrty5,  ehtrnr6, ehtrty6,  ");
			sql.append(" ehtrnr7, ehtrty7, ehtrnr8, ehtrty8, ehtrnr9, ehtrty9, ehtrnr10, ehtrty10,   ");
			//
			sql.append(" ehetyp, ehetypt, eheid, ehkns, ehrgs, ehtpps, ehnas, ehna2s, ehad1s, ehnrs,  ");
			//
			sql.append(" ehetyp2, ehetypt2, eheid2, ehetyp3, ehetypt3, eheid3, ehetyp4, ehetypt4, eheid4, ehetyp5, ehetypt5, eheid5,   ");
			sql.append(" ehetyp6, ehetypt6, eheid6, ehetyp7, ehetypt7, eheid7, ehetyp8, ehetypt8, eheid8, ehetyp9, ehetypt9, eheid9, ehetyp10, ehetypt10, eheid10,   ");
			//
			sql.append(" ehpns, ehpss, ehlks, ehpbs, ehems, ehemst, ehknm, ehrgm, ehtppm, ehnam,  ehna2m, ehad1m, ehnrm, ehpnm,  ");
			sql.append(" ehpsm, ehlkm, ehpbm, ehemm, ehemmt, ehlka, ehsda, ehsdat, ehlkd, ehsdd, ehsddt ) ");
			
			sql.append(" VALUES ( ?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?, ");
			//flera transiteringar
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?, ");
			//
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			//flera exporter
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,  ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			//
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEhavd(), dao.getEhpro(), dao.getEhtdn(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh(), dao.getEhdkm_fh(),		
			dao.getEhst(), dao.getEhst2(), dao.getEhst3(), dao.getEhuuid(), dao.getEhmid(), dao.getEhdts(), dao.getEhtms(), dao.getEhrecid(), dao.getEhcnin(), dao.getEhvkb(), dao.getEhntk(), dao.getEhextref(),
			dao.getEhvt(), dao.getEhdkh(), dao.getEhdkht(), dao.getEhpr(), dao.getEhprt(), dao.getEhupr(), dao.getEhuprt(), dao.getEhrg(), dao.getEh0068a(), dao.getEh0068b(), dao.getEhtrnr(),dao.getEhtrty(), 
			//flera transiteringar
			dao.getEhtrnr2(),dao.getEhtrty2(), dao.getEhtrnr3(),dao.getEhtrty3(), dao.getEhtrnr4(),dao.getEhtrty4(), dao.getEhtrnr5(),dao.getEhtrty5(), dao.getEhtrnr6(),dao.getEhtrty6(), 
			dao.getEhtrnr7(),dao.getEhtrty7(), dao.getEhtrnr8(),dao.getEhtrty8(), dao.getEhtrnr9(),dao.getEhtrty9(), dao.getEhtrnr10(),dao.getEhtrty10(), 
			//
			dao.getEhetyp(), dao.getEhetypt(), dao.getEheid(), dao.getEhkns(), dao.getEhrgs(), dao.getEhtpps(), dao.getEhnas(), dao.getEhna2s(), dao.getEhad1s(), dao.getEhnrs(),
			//flera exporter
			dao.getEhetyp2(), dao.getEhetypt2(), dao.getEheid2(), dao.getEhetyp3(), dao.getEhetypt3(), dao.getEheid3(), dao.getEhetyp4(), dao.getEhetypt4(), dao.getEheid4(), dao.getEhetyp5(), dao.getEhetypt5(), dao.getEheid5(),
			dao.getEhetyp6(), dao.getEhetypt6(), dao.getEheid6(), dao.getEhetyp7(), dao.getEhetypt7(), dao.getEheid7(), dao.getEhetyp8(), dao.getEhetypt8(), dao.getEheid8(), dao.getEhetyp9(), dao.getEhetypt9(), dao.getEheid9(), dao.getEhetyp10(), dao.getEhetypt10(), dao.getEheid10(),
			//
			dao.getEhpns(), dao.getEhpss(), dao.getEhlks(), dao.getEhpbs(), dao.getEhems(), dao.getEhemst(), dao.getEhknm(), dao.getEhrgm(), dao.getEhtppm(), dao.getEhnam(), dao.getEhna2m(), dao.getEhad1m(), dao.getEhnrm(), dao.getEhpnm(),
			dao.getEhpsm(), dao.getEhlkm(), dao.getEhpbm(), dao.getEhemm(), dao.getEhemmt(), dao.getEhlka(), dao.getEhsda(), dao.getEhsdat(), dao.getEhlkd(), dao.getEhsdd(),  dao.getEhsddt(),
			
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
	
	public int insertTest(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		logger.info("before INSERT");
		try{
			
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			
			
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( ehavd, ehpro, ehtdn, ehlnrt, ehlnrm, ehlnrh, ehdkm_fh,  ");
			sql.append(" ehst, ehst2, ehst3, ehuuid, ehmid, ehdts, ehtms, ehrecid, ehcnin, ehvkb, ehntk, ehextref, ");
			sql.append(" ehvt, ehdkh, ehdkht, ehpr, ehprt, ehupr, ehuprt, ehrg, eh0068a, eh0068b, ehtrnr, ehtrty ) ");
			//
			/*sql.append(" ehtrnr2, ehtrty2, ehtrnr3, ehtrty3, ehtrnr4, ehtrty4, ehtrnr5, ehtrty5,  ehtrnr6, ehtrty6,  ");
			sql.append(" ehtrnr7, ehtrty7, ehtrnr8, ehtrty8, ehtrnr9, ehtrty9, ehtrnr10, ehtrty10,   ");
			//
			sql.append(" ehetyp, ehetypt, eheid, ehkns, ehrgs, ehtpps, ehnas, ehna2s, ehad1s, ehnrs,  ");
			//
			sql.append(" ehetyp2, ehetypt2, eheid2, ehetyp3, ehetypt3, eheid3, ehetyp4, ehetypt4, eheid4, ehetyp5, ehetypt5, eheid5,   ");
			sql.append(" ehetyp6, ehetypt6, eheid6, ehetyp7, ehetypt7, eheid7, ehetyp8, ehetypt8, eheid8, ehetyp9, ehetypt9, eheid9, ehetyp10, ehetypt10, eheid10,   ");
			//
			sql.append(" ehpns, ehpss, ehlks, ehpbs, ehems, ehemst, ehknm, ehrgm, ehtppm, ehnam,  ehna2m, ehad1m, ehnrm, ehpnm,  ");
			sql.append(" ehpsm, ehlkm, ehpbm, ehemm, ehemmt, ehlka, ehsda, ehsdat, ehlkd, ehsdd, ehsddt ) ");
			*/
			sql.append(" VALUES ( ?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?) ");
			/*
			//flera transiteringar
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?, ");
			//
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			//flera exporter
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,  ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			//
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,? ) ");
			*/
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEhavd(), dao.getEhpro(), dao.getEhtdn(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh(), dao.getEhdkm_fh(),		
			dao.getEhst(), dao.getEhst2(), dao.getEhst3(), dao.getEhuuid(), dao.getEhmid(), dao.getEhdts(), dao.getEhtms(), dao.getEhrecid(), dao.getEhcnin(), dao.getEhvkb(), dao.getEhntk(), dao.getEhextref(),
			dao.getEhvt(), dao.getEhdkh(), dao.getEhdkht(), dao.getEhpr(), dao.getEhprt(), dao.getEhupr(), dao.getEhuprt(), dao.getEhrg(), dao.getEh0068a(), dao.getEh0068b(), dao.getEhtrnr(),dao.getEhtrty(), 
			
			/*//flera transiteringar
			dao.getEhtrnr2(),dao.getEhtrty2(), dao.getEhtrnr3(),dao.getEhtrty3(), dao.getEhtrnr4(),dao.getEhtrty4(), dao.getEhtrnr5(),dao.getEhtrty5(), dao.getEhtrnr6(),dao.getEhtrty6(), 
			dao.getEhtrnr7(),dao.getEhtrty7(), dao.getEhtrnr8(),dao.getEhtrty8(), dao.getEhtrnr9(),dao.getEhtrty9(), dao.getEhtrnr10(),dao.getEhtrty10(), 
			//
			dao.getEhetyp(), dao.getEhetypt(), dao.getEheid(), dao.getEhkns(), dao.getEhrgs(), dao.getEhtpps(), dao.getEhnas(), dao.getEhna2s(), dao.getEhad1s(), dao.getEhnrs(),
			//flera exporter
			dao.getEhetyp2(), dao.getEhetypt2(), dao.getEheid2(), dao.getEhetyp3(), dao.getEhetypt3(), dao.getEheid3(), dao.getEhetyp4(), dao.getEhetypt4(), dao.getEheid4(), dao.getEhetyp5(), dao.getEhetypt5(), dao.getEheid5(),
			dao.getEhetyp6(), dao.getEhetypt6(), dao.getEheid6(), dao.getEhetyp7(), dao.getEhetypt7(), dao.getEheid7(), dao.getEhetyp8(), dao.getEhetypt8(), dao.getEheid8(), dao.getEhetyp9(), dao.getEhetypt9(), dao.getEheid9(), dao.getEhetyp10(), dao.getEhetypt10(), dao.getEheid10(),
			//
			dao.getEhpns(), dao.getEhpss(), dao.getEhlks(), dao.getEhpbs(), dao.getEhems(), dao.getEhemst(), dao.getEhknm(), dao.getEhrgm(), dao.getEhtppm(), dao.getEhnam(), dao.getEhna2m(), dao.getEhad1m(), dao.getEhnrm(), dao.getEhpnm(),
			dao.getEhpsm(), dao.getEhlkm(), dao.getEhpbm(), dao.getEhemm(), dao.getEhemmt(), dao.getEhlka(), dao.getEhsda(), dao.getEhsdat(), dao.getEhlkd(), dao.getEhsdd(),  dao.getEhsddt(),
			*/
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
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET  ehavd = ?, ehpro = ?, ehtdn = ?, ehdkm_fh = ?,   ");
			sql.append(" ehst = ?, ehst2 = ?, ehst3 = ?, ehuuid = ?, ehmid = ?, ehdts = ?, ehtms = ?, ehrecid = ?, ehcnin = ?, ehvkb = ?, ehntk = ?, ehextref = ?, ");
			sql.append(" ehvt = ?, ehdkh = ?, ehdkht = ?, ehpr = ?, ehprt = ?, ehupr = ?, ehuprt = ?, ehrg = ?, eh0068a = ?, eh0068b = ?, ehtrnr = ?, ehtrty = ?,  ");
			//
			sql.append(" ehtrnr2 = ?, ehtrty2 = ?, ehtrnr3 = ?, ehtrty3 = ?, ehtrnr4 = ?, ehtrty4 = ?, ehtrnr5 = ?, ehtrty5 = ?, ehtrnr6 = ?, ehtrty6 = ?, "); 
			sql.append(" ehtrnr7 = ?, ehtrty7 = ?, ehtrnr8 = ?, ehtrty8 = ?, ehtrnr9 = ?, ehtrty9 = ?, ehtrnr10 = ?, ehtrty10 = ?,  "); 
			//
			sql.append(" ehetyp = ?, ehetypt = ?, eheid = ?, ehkns = ?, ehrgs = ?, ehtpps = ?, ehnas = ?, ehna2s = ?, ehad1s = ?, ehnrs = ?,  ");
			//
			sql.append(" ehetyp2 = ?, ehetypt2 = ?, eheid2 = ?, ehetyp3 = ?, ehetypt3 = ?, eheid3 = ?, ehetyp4 = ?, ehetypt4 = ?, eheid4 = ?, ehetyp5 = ?, ehetypt5 = ?, eheid5 = ?,   ");
			sql.append(" ehetyp6 = ?, ehetypt6 = ?, eheid6 = ?, ehetyp7 = ?, ehetypt7 = ?, eheid7 = ?, ehetyp8 = ?, ehetypt8 = ?, eheid8 = ?, ehetyp9 = ?, ehetypt9 = ?, eheid9 = ?, ehetyp10 = ?, ehetypt10 = ?, eheid10 = ?,   ");
			//
			sql.append(" ehpns = ?, ehpss = ?, ehlks = ?, ehpbs = ?, ehems = ?, ehemst = ?, ehknm = ?, ehrgm = ?, ehtppm = ?, ehnam = ?,  ehna2m = ?, ehad1m = ?, ehnrm = ?, ehpnm = ?,  ");
			sql.append(" ehpsm = ?, ehlkm = ?, ehpbm = ?, ehemm = ?, ehemmt = ?, ehlka = ?, ehsda = ?, ehsdat = ?, ehlkd = ?, ehsdd = ?, ehsddt = ?  ");
			//id's
			sql.append(" WHERE ehdkm_fh = ? AND ehdkh = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			dao.getEhavd(), dao.getEhpro(), dao.getEhtdn(), dao.getEhdkm_fh(),	
			dao.getEhst(), dao.getEhst2(), dao.getEhst3(), dao.getEhuuid(), dao.getEhmid(), dao.getEhdts(), dao.getEhtms(), dao.getEhrecid(), dao.getEhcnin(), dao.getEhvkb(), dao.getEhntk(), dao.getEhextref(),
			dao.getEhvt(), dao.getEhdkh(), dao.getEhdkht(), dao.getEhpr(), dao.getEhprt(), dao.getEhupr(), dao.getEhuprt(), dao.getEhrg(), dao.getEh0068a(), dao.getEh0068b(), dao.getEhtrnr(),dao.getEhtrty(),
			//
			dao.getEhtrnr2(),dao.getEhtrty2(), dao.getEhtrnr3(),dao.getEhtrty3(), dao.getEhtrnr4(),dao.getEhtrty4(), dao.getEhtrnr5(),dao.getEhtrty5(), dao.getEhtrnr6(),dao.getEhtrty6(),
			dao.getEhtrnr7(),dao.getEhtrty7(), dao.getEhtrnr8(),dao.getEhtrty8(), dao.getEhtrnr9(),dao.getEhtrty9(), dao.getEhtrnr10(),dao.getEhtrty10(),
			//
			dao.getEhetyp(), dao.getEhetypt(), dao.getEheid(), dao.getEhkns(), dao.getEhrgs(), dao.getEhtpps(), dao.getEhnas(), dao.getEhna2s(), dao.getEhad1s(), dao.getEhnrs(),
			//
			dao.getEhetyp2(), dao.getEhetypt2(), dao.getEheid2(), dao.getEhetyp3(), dao.getEhetypt3(), dao.getEheid3(), dao.getEhetyp4(), dao.getEhetypt4(), dao.getEheid4(), dao.getEhetyp5(), dao.getEhetypt5(), dao.getEheid5(),
			dao.getEhetyp6(), dao.getEhetypt6(), dao.getEheid6(), dao.getEhetyp7(), dao.getEhetypt7(), dao.getEheid7(), dao.getEhetyp8(), dao.getEhetypt8(), dao.getEheid8(), dao.getEhetyp9(), dao.getEhetypt9(), dao.getEheid9(), dao.getEhetyp10(), dao.getEhetypt10(), dao.getEheid10(),
			//
			dao.getEhpns(), dao.getEhpss(), dao.getEhlks(), dao.getEhpbs(), dao.getEhems(), dao.getEhemst(), dao.getEhknm(), dao.getEhrgm(), dao.getEhtppm(), dao.getEhnam(), dao.getEhna2m(), dao.getEhad1m(), dao.getEhnrm(), dao.getEhpnm(),
			dao.getEhpsm(), dao.getEhlkm(), dao.getEhpbm(), dao.getEhemm(), dao.getEhemmt(), dao.getEhlka(), dao.getEhsda(), dao.getEhsdat(), dao.getEhlkd(), dao.getEhsdd(),  dao.getEhsddt(),
			//id's
			dao.getEhdkm_fh(), dao.getEhdkh(),
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
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			
			sql.append(" DELETE from "  + this.TABLE_NAME );
			sql.append(" WHERE ehdkm_fh = ? AND ehdkh = ? ");
			logger.info(sql.toString() + " ehdkm_fh:" + dao.getEhdkm_fh() + " ehdkh:" + dao.getEhdkh());
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhdkm_fh(), dao.getEhdkh()} );
		
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Delete:"+writer.toString());
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
	/*
	public int deleteLight(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			ZadmohfDao dao = (ZadmohfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set ehuuid = '', ehmid = '', ehuuid_own = '', ehmid_own = '',  ");
			sql.append(" ehst2 = ?, ehst3 = '', ehdts = ? ");
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
		
		return retval;
		
	}
	*/
	/**
	 * 
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int updateStatus(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			ZadmohfDao dao = (ZadmohfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE Zadmohf set ehst = ? ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			sql.append(" AND ehlnrm = ? ");
			sql.append(" AND ehlnrh = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhst(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh() } );
			
			
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
			ZadmohfDao dao = (ZadmohfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE Zadmohf set ehst2 = ? ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			sql.append(" AND ehlnrm = ? ");
			sql.append(" AND ehlnrh = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhst2(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh() } );
			
			
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
			ZadmohfDao dao = (ZadmohfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE Zadmohf set ehst3 = ? ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			sql.append(" AND ehlnrm = ? ");
			sql.append(" AND ehlnrh = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhst3(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh() } );
			
			
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
	public int updateLrnMrn(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			ZadmohfDao dao = (ZadmohfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set ehuuid = ?, ehmid = ?, ehdts = ?, ehst = ?, ehst2 = ? ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			sql.append(" AND ehlnrm = ?" );
			sql.append(" AND ehlnrh = ?" );
			//sql.append(" AND ehmid = '' " );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhuuid(), dao.getEhmid(), dao.getEhdts(), dao.getEhst(), dao.getEhst2(),   
															dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh() } );
			
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
	 * This method is used for the update of LRN after an API PUT (Update with mrn)
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	/*
	public int updateLrn(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set ehuuid = ?, ehdts = ?, ehst = ?, ehst2 = ?  ");
			//id's
			sql.append(" WHERE ehlnrt = ? ");
			sql.append(" AND ehlnrm = ?" );
			sql.append(" AND ehlnrh = ?" );
			sql.append(" AND ehmid = ?" );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhuuid(), dao.getEhdts(), dao.getEhst(), dao.getEhst2(),
																dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh(), dao.getEhmid() } );
			
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
	 * Back up to MRN in-case we loose the original MRN (ehmid)
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	/*
	public int setMrnBup(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set ehmid_own = ?  ");
			//id's
			sql.append(" WHERE ehmid = ?" );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhmid(), dao.getEhmid() } );
			
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
			ZadmohfDao dao = (ZadmohfDao)daoObj;
			//DEBUG logger.warn(daoObj.toString());	
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" UPDATE "  + TABLE_NAME+ " set ehuuid_own = ?  ");
			//id's
			sql.append(" WHERE ehlnrt = ?" );
			sql.append(" AND ehlnrm = ?" );
			sql.append(" AND ehlnrh = ?" );
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getEhuuid(), dao.getEhlnrt(), dao.getEhlnrm(), dao.getEhlnrh() } );
			
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
	 * @param ehlnrt
	 * @param ehlnrm
	 * @return
	 */
	/*
	private int getNextEhlnrh(Integer ehlnrt, Integer ehlnrm) {
		int retval = 0;
		logger.info("ehlnrt/ehlnrm for NextSeed:" + ehlnrt + "/" + ehlnrm);
		
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("mydebug");
		sql.append(" SELECT max(ehlnrh)+1 from " + this.TABLE_NAME  );
		sql.append(" WHERE ehlnrt = ? AND ehlnrm = ? " );
		
		retval = this.jdbcTemplate.queryForObject( sql.toString(), new Object[] { ehlnrt, ehlnrm },  Integer.class );
			
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
