package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.mapper.TrughMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 16, 2016
 * 
 */
public class TrughDaoServicesImpl implements TrughDaoServices {
	private static Logger logger = Logger.getLogger(TrughDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();

	/**
	 * @param errorStackTrace
	 * @return
	 */
	public List<Object> getList(StringBuffer errorStackTrace) {
		List<Object> retval = new ArrayList<Object>();

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());

			retval = jdbcTemplate.query(sql.toString(), new TrughMapper());
			
			logger.info("retval="+retval.size());
		} catch (Exception e) {
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}

	/**
	 * Find row in TRUGH by tggnr (garantinr)
	 * Support wildcard %
	 * 
	 */
	public List<Object> findById(String id, StringBuffer errorStackTrace) {
		List<Object> retval = new ArrayList<Object>();
		String SQL_WILD_CARD = "%";

		try {

			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());
			sql.append(" where tggnr LIKE ? ");

			retval = jdbcTemplate.query(sql.toString(), new Object[] { id + SQL_WILD_CARD }, new TrughMapper());

		} catch (Exception e) {
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}

	/*	*//**
			 * 
			 * @param dao
			 * @param errorStackTrace
			 * @return
			 */

	/*
	 * public int insert(Object daoObj, StringBuffer errorStackTrace) {
	 * 
	 * int retval = 0; try { SadavgeDao dao = (SadavgeDao) daoObj; StringBuffer
	 * sql = new StringBuffer(); sql.append(
	 * " INSERT INTO sadavge (agtanr, agakd, agskv, agdtf, agdtt, agkd, agpp, agsats, agaktk) "
	 * ); sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ");
	 * 
	 * retval = this.jdbcTemplate.update(sql.toString(), new Object[] {
	 * dao.getAgtanr(), dao.getAgakd(), dao.getAgskv(), dao.getAgdtf(),
	 * dao.getAgdtt(), dao.getAgkd(), dao.getAgpp(), dao.getAgsats(),
	 * dao.getAgaktk() });
	 * 
	 * } catch (Exception e) { Writer writer =
	 * this.dbErrorMessageMgr.getPrintWriter(e); logger.info(writer.toString());
	 * // Chop the message to comply to JSON-validation
	 * errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(
	 * writer)); retval = -1; }
	 * 
	 * return retval; }
	 */
	/*	*//**
			 * UPDATE Til Dato = AGDTT, EU=AGKD, %/P=AGPP, Sats=AGSATS, status=
			 * AGAKTK
			 *//*
			 * public int update(Object daoObj, StringBuffer errorStackTrace){
			 * 
			 * int retval = 0; try{ SadavgeDao dao = (SadavgeDao)daoObj;
			 * StringBuffer sql = new StringBuffer();
			 * 
			 * sql.append(
			 * " UPDATE sadavge SET agdtf = ?, agdtt = ?, agkd = ?, agpp= ?, agsats = ?, agaktk = ?"
			 * ); sql.append(" WHERE agtanr = ? "); sql.append(" AND agakd = ?"
			 * ); sql.append(" AND agskv = ?");
			 * 
			 * logger.info("update, sql="+sql.toString()); logger.info(
			 * "update, dao="+dao.toString()); retval =
			 * this.jdbcTemplate.update( sql.toString(), new Object[] {
			 * dao.getAgdtf(), dao.getAgdtt(), dao.getAgkd(), dao.getAgpp(),
			 * dao.getAgsats(), dao.getAgaktk(), dao.getAgtanr(),
			 * dao.getAgakd(), dao.getAgskv() } );
			 * 
			 * 
			 * }catch(Exception e){ Writer writer =
			 * this.dbErrorMessageMgr.getPrintWriter(e);
			 * logger.info(writer.toString()); //Chop the message to comply to
			 * JSON-validation errorStackTrace.append(this.dbErrorMessageMgr.
			 * getJsonValidDbException(writer)); retval = -1; }
			 * 
			 * return retval; }
			 * 
			 */
	private StringBuffer getSELECT_FROM_WHERE_CLAUSE() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select tgst, tggnr, CHAR(tggty) tggty ,tggvk, CHAR(tggbl) tggbl, CHAR(tggblb) tggblb, CHAR(tgkna) tgkna, tgtina,tgnaa,tgada1, tgpna, tgpsa, tglka, tgtsd,");
		sql.append(" tgakny, tgakgm, tgusr, CHAR(tgdt) tgdt, CHAR(tgdtr) tgdtr, CHAR(tgprm) tgprm , CHAR(tgrn) tgrn ,tggfv ");
		sql.append(" from trugh ");
		return sql;
	}

	/*	*//**
			 * DELETE
			 *//*
			 * public int delete(Object daoObj, StringBuffer errorStackTrace){
			 * int retval = 0;
			 * 
			 * try{ SadavgeDao dao = (SadavgeDao)daoObj;
			 * 
			 * StringBuffer sql = new StringBuffer(); sql.append(
			 * " DELETE from sadavge "); sql.append(" WHERE agtanr = ? ");
			 * sql.append(" AND agakd = ?"); sql.append(" AND agskv = ?");
			 * 
			 * retval = this.jdbcTemplate.update( sql.toString(), new Object[] {
			 * dao.getAgtanr(), dao.getAgakd(), dao.getAgskv() } );
			 * 
			 * }catch(Exception e){ Writer writer =
			 * this.dbErrorMessageMgr.getPrintWriter(e);
			 * logger.info(writer.toString()); //Chop the message to comply to
			 * JSON-validation errorStackTrace.append(this.dbErrorMessageMgr.
			 * getJsonValidDbException(writer)); retval = -1; }
			 * 
			 * return retval; }
			 */

	/**
	 * Wires jdbcTemplate
	 * 
	 */
	private JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	public int insert(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object dao, StringBuffer errorStackTrace) {
		// TODO Auto-generated method stub
		return 0;
	}

}
