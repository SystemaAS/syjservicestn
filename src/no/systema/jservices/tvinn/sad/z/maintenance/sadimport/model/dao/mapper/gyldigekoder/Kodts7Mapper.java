package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts7Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 23, 2016
 * 
 */
public class Kodts7Mapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(Kodts7Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts7Dao dao = new Kodts7Dao();
    
    	dao.setKs7sta(rs.getString("ks7sta"));
    	dao.setKs7uni(rs.getString("ks7uni"));
    	dao.setKs7vf(rs.getString("ks7vf"));
    	dao.setKs7ftx(rs.getString("ks7ftx"));
    	
        return dao;
    }

}


