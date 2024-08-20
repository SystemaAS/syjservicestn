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
			
			SadmoafDao dao = (SadmoafDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TRANSPORT data
			sql.append(" INSERT INTO "  + this.TABLE_NAME +  "( etavd, etsg, etpro, etetad, etetat, etshed, etshet, ");
			sql.append(" etknr, etrgr, etnar, etna2r, etad1r, etnrr, etpnr, etpsr, etlkr, etpbr, etemr, etemrt, etkmrk, etktm, ");
			sql.append(" etktyp, etklk, etcref, etktkd, etsjaf, etems, etemst,  ");
			sql.append(" etknt, etrgt, etnat, etna2t, etad1t, etnrt, etpnt, etpst, etlkt, etpbt, etemt, etemtt, etdkm, etdkmt, ettsd, ");
			
			//MASTER data
			sql.append(" emavd, empro, emlnrt, emlnrm, emknt, emrgt, ");
			sql.append(" emdtr, emsg, emst, emst2, emuuid, emmid, emst3, emdtin, ematdd, emcn, emvkb, ");
			sql.append(" emrcem1, emrcem2, emrcem3, ");
			//
			sql.append(" emknm, emrgm, emtppm, emnam, emna2m, emad1m, emnrm, empnm, empsm, emlkm, empbm, ememm, ememmt,  ");
			sql.append(" emkns, emrgs, emtpps, emnas, emna2s, emad1s, emnrs, empns, empss, emlks, empbs, emems, ememst,  ");
			sql.append(" emdkm, emdkmt, emc1ty, emc1ps, emc1ss, emc1id, emc2ty, emc2ps, emc2ss, emc2id,  emc3ty, emc3ps, emc3ss, emc3id,  ");
			sql.append(" emlkl, emsdl, emsdlt, emlku, emsdu, emsdut, emlkd, emsdd, emsddt ) ");
			
			//VALUES
			//TRANSPORT data
			sql.append(" VALUES (?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,  ");
			//MASTER data
			sql.append(" ?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?, ");
			//
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					
			dao.getEtavd(), dao.getEtsg(), dao.getEtpro(), dao.getEtetad(), dao.getEtetat(), dao.getEtshed(), dao.getEtshet(),  
			dao.getEtknr(), dao.getEtrgr(), dao.getEtnar(), dao.getEtna2r(), dao.getEtad1r(), dao.getEtnrr(), dao.getEtpnr(), dao.getEtpsr(), dao.getEtlkr(), dao.getEtpbr(), dao.getEtemr(), dao.getEtemrt(), dao.getEtkmrk(), dao.getEtktm(),
			dao.getEtktyp(), dao.getEtklk(), dao.getEtcref(), dao.getEtktkd(), dao.getEtsjaf(), dao.getEtems(), dao.getEtemst(),
			dao.getEtknt(), dao.getEtrgt(), dao.getEtnat(), dao.getEtna2t(), dao.getEtad1t(), dao.getEtnrt(), dao.getEtpnt(), dao.getEtpst(), dao.getEtlkt(), dao.getEtpbt(), dao.getEtemt(), dao.getEtemtt(), dao.getEtdkm(), dao.getEtdkmt(), dao.getEttsd(),
			//MASTER data
			dao.getEmavd(), dao.getEmpro(), dao.getEmlnrt(), dao.getEmlnrm(), dao.getEmknt(), dao.getEmrgt(),	
			dao.getEmdtr(), dao.getEmsg(), dao.getEmst(), dao.getEmst2(), dao.getEmuuid(), dao.getEmmid(), dao.getEmst3(), dao.getEmdtin(), dao.getEmatdd(), dao.getEmcn(), dao.getEmvkb(),
			dao.getEmrcem1(), dao.getEmrcem2(), dao.getEmrcem3(),
			//
			dao.getEmknm(), dao.getEmrgm(), dao.getEmtppm(), dao.getEmnam(), dao.getEmna2m(), dao.getEmad1m(), dao.getEmnrm(), dao.getEmpnm(), dao.getEmpsm(), dao.getEmlkm(), dao.getEmpbm(), dao.getEmemm(), dao.getEmemmt(),
			dao.getEmkns(), dao.getEmrgs(), dao.getEmtpps(), dao.getEmnas(), dao.getEmna2s(), dao.getEmad1s(), dao.getEmnrs(), dao.getEmpns(), dao.getEmpss(), dao.getEmlks(), dao.getEmpbs(), dao.getEmems(), dao.getEmemst(),
			dao.getEmdkm(), dao.getEmdkmt(), dao.getEmc1ty(), dao.getEmc1ps(), dao.getEmc1ss(), dao.getEmc1id(), dao.getEmc2ty(), dao.getEmc2ps(), dao.getEmc2ss(), dao.getEmc2id(), dao.getEmc3ty(), dao.getEmc3ps(), dao.getEmc3ss(), dao.getEmc3id(),
			dao.getEmlkl(), dao.getEmsdl(), dao.getEmsdlt(), dao.getEmlku(), dao.getEmsdu(), dao.getEmsdut(), dao.getEmlkd(), dao.getEmsdd(), dao.getEmsddt(),
			
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
			SadmoafDao dao = (SadmoafDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TRANSPORT
			sql.append(" UPDATE "  + this.TABLE_NAME +  " SET etst = ?, etsg = ?, etpro = ?, etetad = ?, etetat = ?, ");
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
			dao.getEtst(), dao.getEtsg(), dao.getEtpro(), dao.getEtetad(), dao.getEtetat(),	
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
			logger.info("Exception in update Sadmoaf:"+writer.toString());
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
			SadmoafDao dao = (SadmoafDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TRANSPORT
			sql.append(" delete from "  + this.TABLE_NAME); 
			sql.append(" WHERE etavd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
			//id's
			dao.getEtavd(),
			} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in delete Sadmoaf:"+writer.toString());
			e.printStackTrace();
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
