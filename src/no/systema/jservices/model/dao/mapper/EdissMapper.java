package no.systema.jservices.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.EdissDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  May 18, 2016
 * 
 */
public class EdissMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(EdissMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	EdissDao dao = new EdissDao();
    
    	dao.setSssn(rs.getString("sssn"));
    	dao.setSsdt(rs.getString("ssdt"));
    	dao.setSstm(rs.getString("sstm"));
    	dao.setSsst(rs.getString("ssst"));
    	dao.setSstext(rs.getString("sstext"));
    	
        return dao;
    }

}


