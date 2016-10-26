package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.gyldigekoder.KodtsaDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 24, 2016
 * 
 */
public class KodtsaMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtsaMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtsaDao dao = new KodtsaDao();
    
    	dao.setKsakd(rs.getString("ksakd"));
    	dao.setKsaft(rs.getString("ksaft"));
    	
        return dao;
    }

}


