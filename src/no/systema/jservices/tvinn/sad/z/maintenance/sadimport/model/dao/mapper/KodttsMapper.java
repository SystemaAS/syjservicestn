package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodttsDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Jun 20, 2016
 * 
 */
public class KodttsMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(KodttsMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodttsDao dao = new KodttsDao();
    
    	dao.setKtsuni(rs.getString("ktsuni"));
    	dao.setKtskod(rs.getString("ktskod"));
    	dao.setKtsnav(rs.getString("ktsnav"));
    	dao.setKtspnr(rs.getString("ktspnr"));
    	//dao.setKtstrt(rs.getString("ktstrt"));
    	//dao.setKtssat(rs.getString("ktssat"));
    	//dao.setKtsxxx(rs.getString("ktsxxx"));
    	//
    	dao.setKtxkod(rs.getString("ktxkod"));
    	dao.setKtxpnr(rs.getString("ktxpnr"));
    	
        return dao;
    }

}


