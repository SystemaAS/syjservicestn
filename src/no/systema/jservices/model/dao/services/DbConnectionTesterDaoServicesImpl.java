package no.systema.jservices.model.dao.services;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.controller.JsonResponseOutputterController;
import no.systema.jservices.model.dao.mapper.DbConnectionTesterMapper;
import no.systema.jservices.model.dao.entities.DbConnectionTesterDao;


public class DbConnectionTesterDaoServicesImpl implements DbConnectionTesterDaoServices {
	private static Logger logger = Logger.getLogger(DbConnectionTesterDaoServicesImpl.class.getName());
	
	/**
	 * 
	 * @return
	 */
	public List<DbConnectionTesterDao> getList(){
		/*String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
		String paramKnavn = "B%";
		final Object[] params = new Object[]{ paramKnavn }; 
        return this.jdbcTemplate.query( sql, params, new CundfMapper());
        */
		String sql = "select 'Hello world' text, current server dbserver from sysibm.sysdummy1";
		return this.jdbcTemplate.query( sql, new DbConnectionTesterMapper());
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
