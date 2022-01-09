package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper.gui;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodttsDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Jun 21, 2016
 * 
 */
public class PostnrKodttsxMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(PostnrKodttsxMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodttsDao dao = new KodttsDao();
    	//dao.setKtxkod(rs.getString("ktxkod"));
    	dao.setKtxpnr(rs.getString("ktxpnr"));
    	
        return dao;
    }

}


