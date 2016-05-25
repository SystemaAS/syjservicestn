package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts2Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 20, 2016
 * 
 */
public class Kodts2Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Kodts2Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts2Dao dao = new Kodts2Dao();
    
    	dao.setKs2lk(rs.getString("ks2lk"));
    	dao.setKs2ftx(rs.getString("ks2ftx"));
    	dao.setKs2uni(rs.getString("ks2uni"));
    	dao.setKs2sta(rs.getString("ks2sta"));
    	dao.setKs2pre(rs.getString("ks2pre"));
    	dao.setKs2nas(rs.getString("ks2nas"));
    	dao.setKs2mo(rs.getString("ks2mo"));
    	
        return dao;
    }

}


