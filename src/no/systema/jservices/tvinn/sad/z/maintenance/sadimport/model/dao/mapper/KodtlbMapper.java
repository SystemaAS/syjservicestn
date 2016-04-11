package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlbDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Apr 8, 2016
 * 
 */
public class KodtlbMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtlbMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtlbDao dao = new KodtlbDao();
    	
    	dao.setKlbsta(rs.getString(1));
    	dao.setKlbuni(rs.getString(2));
    	dao.setKlbkod(rs.getString(3));
    	dao.setKlbkt(rs.getString(4));
    	dao.setKlbnav(rs.getString(5));
    	dao.setKlbfok(rs.getString(6));
    	dao.setKlbprm(rs.getDouble((7));
    	dao.setKlbfrk(rs.getString(8));
    	dao.setKlbxxx(rs.getString(9));
    	
        return dao;
    }

}


