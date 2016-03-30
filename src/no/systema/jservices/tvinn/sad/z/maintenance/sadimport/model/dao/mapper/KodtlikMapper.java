package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Mar 30, 2016
 * 
 */
public class KodtlikMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtlikMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtlikDao dao = new KodtlikDao();
    	dao.setKlista(rs.getString(1));
    	dao.setKliuni(rs.getString(2));
    	dao.setKlikod(rs.getString(3));
    	dao.setKlinav(rs.getString(4));
    	dao.setKlisto(rs.getString(5));
    	dao.setKlixxx(rs.getString(6));
    	//TODO
    	
        return dao;
    }

}


