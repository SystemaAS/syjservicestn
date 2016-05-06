package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.TariDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Maj , 2016
 * 
 */
public class TariMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(TariMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	TariDao dao = new TariDao();
    
    	dao.setTatanr(rs.getString("tatanr"));
    	dao.setTaordb(rs.getString("taordb"));
    	dao.setTaordk(rs.getString("taordk"));
    	dao.setTaeftb(rs.getString("taeftb"));
    	dao.setTaeftk(rs.getString("taeftk"));
    	dao.setTaefb(rs.getString("taefb"));
    	dao.setTaefk(rs.getString("taefk"));
    	dao.setTastk(rs.getString("tastk"));
    	dao.setTakap(rs.getString("takap"));
    	dao.setTaalfa(rs.getString("taalfa"));
    	dao.setTatxt(rs.getString("tatxt"));
    	dao.setTaenhe(rs.getString("taenhe"));
    	//dates
    	dao.setTadtr(rs.getString("tadtr"));
    	dao.setTadato(rs.getString("tadato"));
    	dao.setTadts(rs.getString("tadts"));
    	
    	
        return dao;
    }

}


