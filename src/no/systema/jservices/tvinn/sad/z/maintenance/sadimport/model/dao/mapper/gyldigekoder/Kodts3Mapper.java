package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts3Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 20, 2016
 * 
 */
public class Kodts3Mapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(Kodts3Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts3Dao dao = new Kodts3Dao();
    
    	dao.setKs3sta(rs.getString("ks3sta"));
    	dao.setKs3uni(rs.getString("ks3uni"));
    	dao.setKs3trt(rs.getString("ks3trt"));
    	dao.setKs3ftx(rs.getString("ks3ftx"));
    	
        return dao;
    }

}


