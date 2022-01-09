package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.gyldigekoder;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.Kodts9Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Okt 26, 2016
 * 
 */
public class Kodts9Mapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(Kodts9Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts9Dao dao = new Kodts9Dao();
    
    	dao.setKs9typ(rs.getString("ks9typ"));
    	dao.setKs9ftx(rs.getString("ks9ftx"));
    	dao.setKs9uni(rs.getString("ks9uni"));
    	dao.setKs9sta(rs.getString("ks9sta"));
    	
        return dao;
    }

}


