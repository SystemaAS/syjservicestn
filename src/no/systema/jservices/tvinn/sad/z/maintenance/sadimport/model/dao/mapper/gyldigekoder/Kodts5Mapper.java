package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts5Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 23, 2016
 * 
 */
public class Kodts5Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Kodts5Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts5Dao dao = new Kodts5Dao();
    
    	dao.setKs5sta(rs.getString("ks5sta"));
    	dao.setKs5uni(rs.getString("ks5uni"));
    	dao.setKs5tln(rs.getString("ks5tln"));
    	dao.setKs5ftx(rs.getString("ks5ftx"));
    	
        return dao;
    }

}


