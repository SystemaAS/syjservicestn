package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.HeadfDao;

/**
 * 
 * @author oscardelatorre
 * @date  Jun 17, 2016
 * 
 */
public class HeadfMapper implements RowMapper {
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	HeadfDao dao = new HeadfDao();
    
    	dao.setHeavd(rs.getString("heavd"));
    	dao.setHeopd(rs.getString("heopd"));
    	dao.setHetll(rs.getString("hetll"));
    	dao.setHetle(rs.getString("hetle"));
    	
        return dao;
    }

}


