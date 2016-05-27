package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadvareDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Maj 26, 2016
 * 
 */
public class SadvareMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(SadvareMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SadvareDao dao = new SadvareDao();
    	
    	dao.setLevenr(rs.getString("levenr"));
    	dao.setVarenr(rs.getString("varenr"));
    	dao.setVarebe(rs.getString("varebe"));
    	
    	
        return dao;
    }

}


