package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts1Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 20, 2016
 * 
 */
public class Kodts1Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Kodts1Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts1Dao dao = new Kodts1Dao();
    
    	dao.setKs1typ(rs.getString("ks1typ"));
    	dao.setKs1ftx(rs.getString("ks1ftx"));
    	dao.setKs1uni(rs.getString("ks1uni"));
    	dao.setKs1sta(rs.getString("ks1sta"));
    	
        return dao;
    }

}


