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
    	dao.setW2vf(rs.getString("w2vf"));
    	dao.setW2lk(rs.getString("w2lk"));
    	dao.setW2vnti(rs.getString("w2vnti"));
    	dao.setW2tn(rs.getString("w2tn"));
    	dao.setW2pre(rs.getString("w2pre"));
    	dao.setW2pva(rs.getString("w2pva"));
    	dao.setW2as(rs.getString("w2as"));
    	dao.setW2mfr(rs.getString("w2mfr"));
    	
        return dao;
    }

}


