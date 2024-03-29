package no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.entities.SoktariDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Okt 21, 2016
 * 
 */
public class SoktariMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(SoktariMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SoktariDao dao = new SoktariDao();
    	
    	dao.setTariff(rs.getString("tariff"));
    	dao.setFill1(rs.getString("fill1"));
    	dao.setBeskr1(rs.getString("beskr1"));
    	
    	
        return dao;
    }

}


