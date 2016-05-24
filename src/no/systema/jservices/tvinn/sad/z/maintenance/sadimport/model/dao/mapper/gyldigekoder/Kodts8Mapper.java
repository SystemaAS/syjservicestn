package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts8Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 13, 2016
 * 
 */
public class Kodts8Mapper implements RowMapper {
	private static Logger logger = Logger.getLogger(Kodts8Mapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Kodts8Dao dao = new Kodts8Dao();
    
    	dao.setKs8avg(rs.getString("ks8avg"));
    	dao.setKs8skv(rs.getString("ks8skv"));
    	dao.setKs8ftx(rs.getString("ks8ftx"));
    	dao.setKs8sat(rs.getString("ks8sat"));
    	dao.setKs8sty(rs.getString("ks8sty"));
    	dao.setOre(rs.getString("ore"));
    	dao.setMil(rs.getString("mil"));
    	
    	
        return dao;
    }

}


