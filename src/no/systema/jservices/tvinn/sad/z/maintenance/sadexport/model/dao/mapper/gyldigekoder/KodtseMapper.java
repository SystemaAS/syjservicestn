package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.gyldigekoder;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.KodtseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 24, 2016
 * 
 */
public class KodtseMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(KodtseMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtseDao dao = new KodtseDao();
    
    	dao.setKsefyl(rs.getString("ksefyl"));
    	dao.setKsetxt(rs.getString("ksetxt"));
    	
        return dao;
    }

}


