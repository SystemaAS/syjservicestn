package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadhHeadfDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Apr 8, 2016
 * 
 */
public class SadhMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(SadhMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SadhHeadfDao dao = new SadhHeadfDao();
    
    	dao.setSiavd(rs.getString("siavd"));
    	dao.setSitdn(rs.getString("sitdn"));
    	dao.setSitll(rs.getString("sitll"));
    	dao.setSitle(rs.getString("sitle"));
    	dao.setSidtg(rs.getString("sidtg"));
    	
        return dao;
    }

}


