package no.systema.jservices.model.dao.services;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import no.systema.jservices.model.dao.mapper.BridfMapper;
import no.systema.jservices.model.dao.entities.BridfDao;

/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */
public class BridfDaoServicesImpl implements BridfDaoServices {
	private static Logger logger = Logger.getLogger(BridfDaoServicesImpl.class.getName());
	
	/**
	 * @param userName
	 * @return
	 */
	public List<BridfDao> getList(String userName){
		StringBuffer sql = new StringBuffer();
		sql.append("select LTRIM(RTRIM(bibrid)) bibrid, bipo, bibesk");
		sql.append("from bridf");
		sql.append("where bibrid = ?");
		final Object[] params = new Object[]{ userName }; 
        
		return this.jdbcTemplate.query( sql.toString(), params, new BridfMapper());
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public String findNameById(String userName){
		String sql = "SELECT bibrid FROM bridf WHERE bibrid = ?";
		String name = null;
		try{
			name = (String)jdbcTemplate.queryForObject( sql, new Object[] { userName }, String.class);
		}catch(Exception e){
			//nothing
		}
		return name;
	}
	
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
