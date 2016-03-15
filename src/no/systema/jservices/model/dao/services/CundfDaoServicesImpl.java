package no.systema.jservices.model.dao.services;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import no.systema.jservices.controller.JsonResponseOutputterController;
import no.systema.jservices.model.dao.mapper.CundfMapper;
import no.systema.jservices.model.dao.entities.CusdfDao;


public class CundfDaoServicesImpl implements CundfDaoServices {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	
	/**
	 * 
	 * @return
	 */
	public List<CusdfDao> getList(){
		/*String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
		String paramKnavn = "B%";
		final Object[] params = new Object[]{ paramKnavn }; 
        return this.jdbcTemplate.query( sql, params, new CundfMapper());
        */
		String sql = "select knavn, adr1, adr2, postnr, adr3 from cundf";
		return this.jdbcTemplate.query( sql, new CundfMapper());
	}
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
