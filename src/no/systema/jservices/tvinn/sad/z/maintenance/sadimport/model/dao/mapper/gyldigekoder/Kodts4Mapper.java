package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts4Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 20, 2016
 * 
 */
public class Kodts4Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Kodts4Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts4Dao dao = new Kodts4Dao();
    
    	dao.setKs4sta(rs.getString("ks4sta"));
    	dao.setKs4uni(rs.getString("ks4uni"));
    	dao.setKs4trm(rs.getString("ks4trm"));
    	dao.setKs4ftx(rs.getString("ks4ftx"));
    	
        return dao;
    }

}


