package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.KodtscDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Okt 26, 2016
 * 
 */
public class KodtscMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtscMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtscDao dao = new KodtscDao();
    
    	dao.setKsckd(rs.getString("ksckd"));
    	dao.setKscft(rs.getString("kscft"));
    	
        return dao;
    }

}


