package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsoDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 24, 2016
 * 
 */
public class KodtsoMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtsoMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtsoDao dao = new KodtsoDao();
    
    	dao.setKsokd(rs.getString("ksokd"));
    	dao.setKsoft(rs.getString("ksoft"));
    	
        return dao;
    }

}


