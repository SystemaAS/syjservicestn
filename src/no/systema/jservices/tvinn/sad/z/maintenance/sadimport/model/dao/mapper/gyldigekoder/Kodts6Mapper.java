package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts6Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 23, 2016
 * 
 */
public class Kodts6Mapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(Kodts6Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts6Dao dao = new Kodts6Dao();
    
    	dao.setKs6sta(rs.getString("ks6sta"));
    	dao.setKs6uni(rs.getString("ks6uni"));
    	dao.setKs6pre(rs.getString("ks6pre"));
    	dao.setKs6ftx(rs.getString("ks6ftx"));
    	
        return dao;
    }

}


