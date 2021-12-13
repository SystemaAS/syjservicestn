package no.systema.jservices.model.dao.mapper;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.BridfDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Feb 11, 2016
 * 
 */
public class BridfMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(BridfMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	BridfDao dao = new BridfDao();
    	//dao.setSlalfa(rs.getString("slalfa"));
    	//TODO
    	
        return dao;
    }

}


