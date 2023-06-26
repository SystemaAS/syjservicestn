package no.systema.jservices.tvinn.sad.ncts5export.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import no.systema.jservices.tvinn.sad.ncts5export.model.dao.entities.NctsecDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jun 2023
 * 
 */
public class NctsecDaoServicesImpl implements NctsecDaoServices {
	private static Logger logger = LoggerFactory.getLogger(NctsecDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	private String SQL_WILD_CARD = "%";
	
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List<NctsecDao> retval = new ArrayList<NctsecDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from nctsec order by tcavd, tctdn desc");
			sql.append(" FETCH FIRST 500 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new BeanPropertyRowMapper(NctsecDao.class));
			
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
		List<NctsecDao> retval = new ArrayList<NctsecDao>();
		//N/A;
		return retval;
	}
	
	public List findById (Integer avd, Integer tdn, StringBuffer errorStackTrace ){
		List<NctsecDao> retval = new ArrayList<NctsecDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from nctsec where tcavd = ? and tctdn = ?  ");
			
			logger.info(sql.toString());
			logger.info("tcavd:" + avd + "/tctdn:" + tdn);
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, tdn }, new BeanPropertyRowMapper(NctsecDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	public List findById (Integer avd, Integer tdn, Integer lineNr, StringBuffer errorStackTrace ){
		List<NctsecDao> retval = new ArrayList<NctsecDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from nctsec where tcavd = ? and tctdn = ? and tcli = ?  ");
			
		
			logger.info("tcavd:" + avd + "/tctdn:" + tdn + "tcli:" + lineNr);
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, tdn, lineNr }, new BeanPropertyRowMapper(NctsecDao.class));
			
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
			NctsecDao dao = (NctsecDao)daoObj;
			//get the next lineNr in turn
			dao.setTcli(this.getLineNr(dao.getTcavd(), dao.getTctdn(), errorStackTrace));
			
			if(dao.getTcli()>0) {
				StringBuffer sql = new StringBuffer();
				//cltdn must be negativt according to YBC
				//DEBUG --> logger.info("mydebug");
				sql.append(" INSERT INTO nctsec (tcavd, tctdn, tcli, tcln, tcdk, tcalk, tcblk, tcvktb, ");
				sql.append(" tcucr, tcavd2, tctdn2, tcxext, tcrole, tcidr, tctaty, tctaid, tctalk, tcpdty , ");
				sql.append(" tcpdrf, tcpdin, tcsdty, tcsdrf, tcsdln, tcsdin, tctdty, tctdrf, tcadty, ");
				sql.append(" tcadrf, tcaicd, tcaitx, tctrch  ) ");
				sql.append(" VALUES(?,?,?,?,?,?,?,?, ");
				sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
				sql.append(" ?,?,?,?,?,?,?,?,?, ");
				sql.append(" ?,?,?,? ) ");
				
				logger.warn(sql.toString());
				logger.warn(dao.toString());
				//params
				
				retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getTcavd(),dao.getTctdn(), dao.getTcli(), dao.getTcln(), dao.getTcdk(), dao.getTcalk(), dao.getTcblk(), dao.getTcvktb(),
						dao.getTcucr(), dao.getTcavd2(), dao.getTctdn2(), dao.getTcxext(), dao.getTcrole(), dao.getTcidr(), dao.getTctaty(), dao.getTctaid(), dao.getTctalk(), dao.getTcpdty(),
						dao.getTcpdrf(), dao.getTcpdin(), dao.getTcsdty(), dao.getTcsdrf(), dao.getTcsdln(), dao.getTcsdin(), dao.getTctdty(), dao.getTctdrf(), dao.getTcadty(), 
						dao.getTcadrf(), dao.getTcaicd(), dao.getTcaitx(), dao.getTctrch()
	
						} );
				
				
				//deal with parties...
				if(retval > -1) {
					if(StringUtils.isNotEmpty(dao.getTcnas())){
						//no update. Just delete and insert record
						retval = this.deleteParties(dao, errorStackTrace);
						retval = this.insertParties(dao, errorStackTrace);
					}
				}
				
			}
			
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
			NctsecDao dao = (NctsecDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TEST
			
			sql.append(" UPDATE nctsec SET tcln = ?, tcdk = ?, tcalk = ?, tcblk = ?, tcvktb = ?, ");
			sql.append(" tcucr = ?, tcavd2 = ?, tctdn2 = ?, tcxext = ?, tcrole = ?, tcidr = ?, tctaty = ?, tctaid = ?, tctalk = ?, tcpdty = ?, ");
			sql.append(" tcpdrf = ?, tcpdin = ?, tcsdty = ?, tcsdrf = ?, tcsdln = ?, tcsdin = ?, tctdty = ?, tctdrf = ?, tcadty = ?, ");
			sql.append(" tcadrf = ?, tcaicd = ?, tcaitx = ?, tctrch = ?  ");
			//id's
			sql.append(" WHERE tcavd = ? ");
			sql.append(" AND tctdn = ? ");
			sql.append(" AND tcli = ? ");
			
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getTcln(), dao.getTcdk(), dao.getTcalk(), dao.getTcblk(), dao.getTcvktb(),
					dao.getTcucr(), dao.getTcavd2(), dao.getTctdn2(), dao.getTcxext(), dao.getTcrole(), dao.getTcidr(), dao.getTctaty(), dao.getTctaid(), dao.getTctalk(), dao.getTcpdty(),
					dao.getTcpdrf(), dao.getTcpdin(), dao.getTcsdty(), dao.getTcsdrf(), dao.getTcsdln(), dao.getTcsdin(), dao.getTctdty(), dao.getTctdrf(), dao.getTcadty(), 
					dao.getTcadrf(), dao.getTcaicd(), dao.getTcaitx(), dao.getTctrch(),
					//id's
					dao.getTcavd(),dao.getTctdn(), dao.getTcli()

					} );
			
			//deal with parties...
			if(retval > -1) {
				if(StringUtils.isNotEmpty(dao.getTcnas())){
					//no update. Just delete and insert record
					retval = this.deleteParties(dao, errorStackTrace);
					retval = this.insertParties(dao, errorStackTrace);
				}
				
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update Nctsec:"+writer.toString());
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
			NctsecDao dao = (NctsecDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from nctsec where tcavd = ? and tctdn = ? and tcli = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTcavd(), dao.getTctdn(), dao.getTcli() } );
			
			//deal with Parties 
			if(retval > -1) {
				retval = this.deleteParties(dao, errorStackTrace);
			}
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	
	private Integer getLineNr(Integer avd, Integer opd, StringBuffer errorStackTrace)  {
		Integer retval = 0;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" select max(tcli + 1) from nctsec where tcavd = ? and tctdn = ?");
			
			logger.info(sql.toString());
			String tmp = this.jdbcTemplate.queryForObject( sql.toString(), new Object[] { avd, opd} , String.class);
			if(StringUtils.isEmpty(tmp)){
				retval = 1;
			}else {
				retval = Integer.valueOf(tmp);
			}
			logger.info("New lineNr (for insert):" + retval);
			
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	private List findPartiesById (Integer avd, Integer tdn, Integer lineNr, StringBuffer errorStackTrace ){
		List<NctsecDao> retval = new ArrayList<NctsecDao>();
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(" select * from nctsecam where tcavd = ? and tctdn = ? and tcli = ? ");
			
			logger.info(sql.toString());
			logger.info("tcavd:" + avd + "/tctdn:" + tdn + "/tcli:" + lineNr);
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { avd, tdn, lineNr }, new BeanPropertyRowMapper(NctsecDao.class));
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	
	private int insertParties(NctsecDao dao, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			StringBuffer sql = new StringBuffer();
			//cltdn must be negativt according to YBC
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO nctsecam (tcavd, tctdn, tcli, tckns, tcnas, tcad1s, tcpns, tcpss, ");
			sql.append(" tclks, tctins, tccps, tctls, tcems, tcknk, tcnak, tcad1k, tcpnk, tcpsk , ");
			sql.append(" tclkk, tctink  ) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?, ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?, ");
			sql.append(" ?,? ) ");
			
			logger.warn(sql.toString());
			logger.warn(dao.toString());
			//params
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getTcavd(),dao.getTctdn(), dao.getTcli(), dao.getTckns(), dao.getTcnas(), dao.getTcad1s(), dao.getTcpns(), dao.getTcpss(),
					dao.getTclks(), dao.getTctins(), dao.getTccps(), dao.getTctls(), dao.getTcems(), dao.getTcknk(), dao.getTcnak(), dao.getTcad1k(), dao.getTcpnk(), dao.getTcpsk(),
					dao.getTclkk(), dao.getTctink()

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
	/*
	public int updateParties(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			NctsecDao dao = (NctsecDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//TEST
			
			sql.append(" UPDATE nctsecam SET tckns = ?, tcnas = ?, tcad1s = ?, tcpns = ?, tcpss = ?, tclks = ?, tctins = ?, tccps = ?, tctls = ?, tcems = ? ");
			sql.append(" tcknk = ?, tcnak = ?, tcad1k = ?, tcpnk = ?, tcpsk = ?, tclkk = ?, tctink = ?  ");
			//id's
			sql.append(" WHERE tcavd = ? ");
			sql.append(" AND tctdn = ? ");
			sql.append(" AND tcli = ? ");
			
			
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getTckns(), dao.getTcnas(), dao.getTcad1s(), dao.getTcpns(), dao.getTcpss(), dao.getTclks(), dao.getTctins(), dao.getTccps(), dao.getTctls(), dao.getTcems(), 
					dao.getTcknk(), dao.getTcnak(), dao.getTcad1k(), dao.getTcpnk(), dao.getTcpsk(), dao.getTclkk(), dao.getTctink(), 
					//id's
					dao.getTcavd(),dao.getTctdn(), dao.getTcli()

					} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info("Exception in update nctsecam:"+writer.toString());
			e.printStackTrace();
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
		
	}
	*/
	
	private int deleteParties(NctsecDao dao, StringBuffer errorStackTrace){
		int retval = 0;
		logger.info("Start deleteing parties ...");
		try{
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from nctsecam where tcavd = ? and tctdn = ? and tcli = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTcavd(), dao.getTctdn(), dao.getTcli() } );
			logger.info("End deleteing parties ...");
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
