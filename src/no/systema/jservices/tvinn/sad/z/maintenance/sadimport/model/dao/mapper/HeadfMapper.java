package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadhHeadfDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Jun 17, 2016
 * 
 */
public class HeadfMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(HeadfMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SadhHeadfDao dao = new SadhHeadfDao();
    
    	dao.setHeavd(rs.getString("heavd"));
    	dao.setHeopd(rs.getString("heopd"));
    	dao.setHetll(rs.getString("hetll"));
    	dao.setHetle(rs.getString("hetle"));
    	
        return dao;
    }

}


