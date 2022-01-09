package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadsdDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Apr 8, 2016
 * 
 */
public class SadsdMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(SadsdMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SadsdDao dao = new SadsdDao();
    

    	dao.setSdtnrf(rs.getString("sdtnrf"));
    	dao.setSdkdae(rs.getString("sdkdae"));
    	dao.setSdkdse(rs.getString("sdkdse"));
    	dao.setSddtf(rs.getString("sddtf"));
    	dao.setSddtt(rs.getString("sddtt"));
    	dao.setSdblse(rs.getString("sdblse"));
    	dao.setSdaktk(rs.getString("sdaktk"));
    	dao.setTaalfa(rs.getString("taalfa"));
    	
    	
    	
        return dao;
    }

}


