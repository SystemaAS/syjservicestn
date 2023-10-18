package no.systema.jservices.tvinn.sad.digitoll.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmoafDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmohfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmologDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Oct 2023
 * 
 */
public class SadmoafDaoServicesImpl implements SadmoafDaoServices {
	private static Logger logger = LoggerFactory.getLogger(SadmoafDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	private String TABLE_NAME = "sadmoaf";
	
	/**
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<SadmoafDao> retval = new ArrayList<SadmoafDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from " + TABLE_NAME );
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new BeanPropertyRowMapper(SadmoafDao.class));
			
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
		List<SadmoafDao> retval = new ArrayList<SadmoafDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from " + TABLE_NAME+ " where etavd = ? ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new BeanPropertyRowMapper(SadmoafDao.class));
			
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
			//TODO
			/*
			SadmologDao dao = (SadmologDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO " + this.TABLE_NAME + " ( elpro, elavd, eltdn, ellnrt, ellnrm, ellnrh, eldate, eltime, eltyp, elltxt, elifsf ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getElpro(), dao.getElavd(), dao.getEltdn(), dao.getEllnrt(), dao.getEllnrm(), dao.getEllnrh(), 
					dao.getEldate(), dao.getEltime(), dao.getEltyp(), dao.getElltxt(), dao.getElifsf(),
					} );
			*/
			
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
			SadmoafDao dao = (SadmoafDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TRANSPORT
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET etst = ?, etpro = ?, etetad = ?, etetat = ?, ");
			sql.append(" etknr = ?, etrgr = ?, etnar = ?, etad1r = ?, etpnr = ?, etpsr = ?, etlkr = ?, etemr = ?, etemrt = ?, etkmrk = ?, etktyp = ?, ");
			sql.append(" etktm = ?, etklk = ?, etcref = ?, etktkd = ?, etsjaf = ?, etems = ?, etemst = ?, ");
			sql.append(" etknt = ?, etrgt = ?, etnat = ?, etad1t = ?, etpnt = ?, etpst = ?, etlkt = ?, etemt = ?, etemtt = ?, ");
			sql.append(" etdkm = ? , etdkmt = ? , ettsd = ?, ");
			//MASTER
			sql.append(" emavd = ? , empro = ? , emrcem1 = ? , emrcem2 = ?, emrcem3 = ?, emcn = ?, emvkb = ?, emknt = ?, emrgt = ?, ");
			sql.append(" emknm = ?, emrgm = ?, emtppm = ?, emnam = ?, emad1m = ?, empnm = ?, empsm = ?, emlkm = ?, ememm = ?, ememmt = ?, ");
			sql.append(" emkns = ?, emrgs = ?, emtpps = ?, emnas = ?, emad1s = ?, empns = ?, empss = ?, emlks = ?, emems = ?, ememst = ?, ");
			sql.append(" emdkm = ? , emdkmt = ? , emc1ty = ? , emc1ps = ? , emc1ss = ?, emc1id = ?, emc2ty = ? , emc2ps = ? , emc2ss = ?, emc2id = ?,  emc3ty = ? , emc3ps = ? , emc3ss = ?, emc3id = ?, ");
			sql.append(" emlkl = ? , emsdl = ? , emsdlt = ?, emlku = ?, emsdu = ?, emsdut = ?, emlkd = ?, emsdd = ?, emsddt = ? ");
			//id's
			sql.append(" WHERE etavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			//TRANSPORT
			dao.getEtst(), dao.getEtpro(), dao.getEtetad(), dao.getEtetat(),	
			dao.getEtknr(), dao.getEtrgr(), dao.getEtnar(), dao.getEtad1r(), dao.getEtpnr(), dao.getEtpsr(), dao.getEtlkr(), dao.getEtemr(), dao.getEtemrt(), dao.getEtkmrk(), dao.getEtktyp(),	
			dao.getEtktm(), dao.getEtklk(), dao.getEtcref(), dao.getEtktkd(), dao.getEtsjaf(), dao.getEtems(), dao.getEtemst(),
			dao.getEtknt(), dao.getEtrgt(), dao.getEtnat(), dao.getEtad1t(), dao.getEtpnt(), dao.getEtpst(), dao.getEtlkt(), dao.getEtemt(), dao.getEtemtt(),
			dao.getEtdkm(), dao.getEtdkmt(), dao.getEttsd(),
			//MASTER
			dao.getEmavd(), dao.getEmpro(), dao.getEmrcem1(), dao.getEmrcem2(), dao.getEmrcem3(), dao.getEmcn(), dao.getEmvkb(), dao.getEmknt(), dao.getEmrgt(),
			dao.getEmknm(), dao.getEmrgm(), dao.getEmtppm(), dao.getEmnam(), dao.getEmad1m(), dao.getEmpnm(), dao.getEmpsm(), dao.getEmlkm(), dao.getEmemm(), dao.getEmemmt(),
			dao.getEmkns(), dao.getEmrgs(), dao.getEmtpps(), dao.getEmnas(), dao.getEmad1s(), dao.getEmpns(), dao.getEmpss(), dao.getEmlks(), dao.getEmems(), dao.getEmemst(),
			dao.getEmdkm(), dao.getEmdkmt(), dao.getEmc1ty(), dao.getEmc1ps(), dao.getEmc1ss(), dao.getEmc1id(), dao.getEmc2ty(), dao.getEmc2ps(), dao.getEmc2ss(), dao.getEmc2id(), dao.getEmc3ty(), dao.getEmc3ps(), dao.getEmc3ss(), dao.getEmc3id(),
			dao.getEmlkl(), dao.getEmsdl(), dao.getEmsdlt(), dao.getEmlku(), dao.getEmsdu(), dao.getEmsdut(), dao.getEmlkd(), dao.getEmsdd(), dao.getEmsddt(),
			//id's
			dao.getEtavd(),
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
		//NA --> refer to update status. There is never a true DELETE
		return 0;
	}
	
	
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
