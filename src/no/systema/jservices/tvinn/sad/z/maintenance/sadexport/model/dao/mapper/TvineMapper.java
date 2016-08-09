package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.TvineDao;

/**
 * 
 * @author Fredrik Möller
 * @date  Aug 8, 2016
 * 
 */
public class TvineMapper implements RowMapper<Object> {
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	TvineDao dao = new TvineDao();
    
    	dao.setE9705(rs.getString(1));
    	dao.setE4440(rs.getString(2));
    	
        return dao;
    }

}


