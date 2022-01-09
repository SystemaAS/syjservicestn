package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsbDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 24, 2016
 * 
 */
public class KodtsbMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(KodtsbMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtsbDao dao = new KodtsbDao();
    
    	dao.setKsbkd(rs.getString("ksbkd"));
    	dao.setKsbft(rs.getString("ksbft"));
    	
        return dao;
    }

}


