package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtsiDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Apr 8, 2016
 * 
 */
public class KodtsiMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtsiMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtsiDao dao = new KodtsiDao();
    
    	dao.setKsista(rs.getString(1));
    	dao.setKsiuni(rs.getString(2));
    	dao.setKsisig(rs.getString(3));
    	dao.setKsinav(rs.getString(4));
    	dao.setKsixxx(rs.getString(5));
    	dao.setKsovl(rs.getString(6));
    	dao.setKsuser(rs.getString(7));
    	
        return dao;
    }

}


