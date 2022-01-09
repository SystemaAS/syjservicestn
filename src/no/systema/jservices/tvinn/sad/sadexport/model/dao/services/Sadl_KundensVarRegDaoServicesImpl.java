package no.systema.jservices.tvinn.sad.sadexport.model.dao.services;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.tvinn.sad.sadexport.model.dao.entities.SadlDao;
import no.systema.main.util.DbErrorMessageManager;
/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public class Sadl_KundensVarRegDaoServicesImpl implements Sadl_KundensVarRegDaoServices {
	private static Logger logger = LoggerFactory.getLogger(Sadl_KundensVarRegDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	/**
	 * @param sadlDao
	 * @param errorStackTrace
	 * @return
	 */
	public int insertIntoSadl(SadlDao sadlDao, StringBuffer errorStackTrace){
		int retval = 0;
		try{
		
		StringBuffer sql = new StringBuffer();
		//DEBUG --> logger.info("SLVEKT:" + sadlDao.getSlvektDbl());
		sql.append(" INSERT INTO sadl (slstat, slknr, slalfa, sltxt, sloppl, slvekt, sltanr, sltar, ");
		sql.append(" slpva, sltn, slto, slcref, slsats) ");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		//params
		retval = this.jdbcTemplate.update( sql.toString(), new Object[] { sadlDao.getSlstat(), sadlDao.getSlknr(), sadlDao.getSlalfa(), sadlDao.getSltxt(),
				sadlDao.getSloppl(), sadlDao.getSlvektDbl(), sadlDao.getSltanr(), sadlDao.getSltar(), sadlDao.getSlpva(), sadlDao.getSltn(), 
				sadlDao.getSlto(), sadlDao.getSlcref(), sadlDao.getSlsatsDbl() } );
		
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
