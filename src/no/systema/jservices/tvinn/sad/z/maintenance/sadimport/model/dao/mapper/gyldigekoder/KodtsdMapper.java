package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsdDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 24, 2016
 * 
 */
public class KodtsdMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtsdMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtsdDao dao = new KodtsdDao();
    
    	dao.setKsdls(rs.getString("ksdls"));
    	dao.setKsdtxt(rs.getString("ksdtxt"));
    	
        return dao;
    }

}


