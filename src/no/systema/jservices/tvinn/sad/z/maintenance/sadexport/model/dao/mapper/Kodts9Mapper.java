package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.Kodts9Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Oct 6, 2016
 * 
 */
public class Kodts9Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Kodts9Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts9Dao dao = new Kodts9Dao();
    
    	dao.setKs9typ(rs.getString("ks9typ"));
    	dao.setKs9ftx(rs.getString("ks9ftx"));
    	dao.setKs9uni(rs.getString("ks9uni"));
    	dao.setKs9sta(rs.getString("ks9sta"));
    	
        return dao;
    }

}


