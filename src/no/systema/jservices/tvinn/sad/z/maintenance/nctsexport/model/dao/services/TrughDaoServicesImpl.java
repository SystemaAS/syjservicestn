package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrughDao;
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

	@Override
	public List<Object> getList(StringBuffer errorStackTrace) {
		List<Object> retval = new ArrayList<Object>();

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());

			retval = jdbcTemplate.query(sql.toString(), new TrughMapper());
			
		} catch (Exception e) {
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}

	public List<Object> findByIdSearch(String id, StringBuffer errorStackTrace) {
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

	@Override
	public List<Object> findById(String id, StringBuffer errorStackTrace) {
		List<Object> retval = new ArrayList<Object>();

		try {

			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_WHERE_CLAUSE());
			sql.append(" where tggnr = ? ");

			retval = jdbcTemplate.query(sql.toString(), new Object[] { id }, new TrughMapper());

		} catch (Exception e) {
			Writer writer = dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			// Chop the message to comply to JSON-validation
			errorStackTrace.append(dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}	
	
	
	@Override
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			TrughDao dao = (TrughDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO trugh (tggnr, tgkna, tgtina, tgnaa, tgada1, tgpna, tgpsa, tglka, tgtsd, tggty, ");
			sql.append(" tggfv, tgakny, tgakgm, tggbl, tggvk, tggblb, tgprm ) ");
			
			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTggnr(), dao.getTgkna(), dao.getTgtina(), dao.getTgnaa(), dao.getTgada1(),
				dao.getTgpna(), dao.getTgpsa(), dao.getTglka(), dao.getTgtsd(), dao.getTggty(), dao.getTggfv(), dao.getTgakny(), dao.getTgakgm(), dao.getTggbl(),  
				dao.getTggvk(), dao.getTggblb(), dao.getTgprm()  } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	@Override
	public int update(Object daoObj, StringBuffer errorStackTrace) {
		int retval = 0;
		try {
			TrughDao dao = (TrughDao) daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE trugh SET tgst = ?, tgkna = ?, tgtina = ?, tgnaa = ?, tgada1 = ?, tgpna = ?, tgpsa = ?, tglka = ?, ");
			sql.append(" tggty = ?, tggvk = ?, tggbl = ?, tggblb = ?, tgtsd = ?, tggfv = ?, tgakny = ?, tgakgm = ?, tgprm = ?");
			sql.append(" WHERE tggnr = ? ");

			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getTgst(), dao.getTgkna(), dao.getTgtina(), dao.getTgnaa(), dao.getTgada1(), dao.getTgpna(), dao.getTgpsa(), dao.getTglka(),
						dao.getTggty(), dao.getTggvk(), dao.getTggbl(), dao.getTggblb(), dao.getTgtsd(), dao.getTggfv(), dao.getTgakny(), dao.getTgakgm(),
						dao.getTgprm(),
						//id's
						dao.getTggnr(),
						} );
			
		} catch (Exception e) {
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString()); // Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
			 
	@Override
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			TrughDao dao = (TrughDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE from trugh ");
			sql.append(" WHERE tggnr = ? ");		
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTggnr() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}

	private StringBuffer getSELECT_FROM_WHERE_CLAUSE() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select tgst, tggnr, CHAR(tggty) tggty ,tggvk, CHAR(tggbl) tggbl, CHAR(tggblb) tggblb, CHAR(tgkna) tgkna, tgtina,tgnaa,tgada1, tgpna, tgpsa, tglka, tgtsd,");
		sql.append(" tgakny, tgakgm, tgusr, CHAR(tgdt) tgdt, CHAR(tgdtr) tgdtr, CHAR(tgprm) tgprm , CHAR(tgrn) tgrn ,tggfv ");
		sql.append(" from trugh ");
		return sql;
	}
	
	
	
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

}
