package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.mapper;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.SadlDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Maj 30, 2016
 * 
 */
public class SadlMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(SadlMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SadlDao dao = new SadlDao();
    	
    	dao.setSlstat(rs.getString("slstat"));
    	dao.setSlknr(rs.getString("slknr"));
    	dao.setSlalfa(rs.getString("slalfa"));
    	dao.setSltxt(rs.getString("sltxt"));
    	dao.setSloppl(rs.getString("sloppl"));
    	dao.setSlvekt(rs.getString("slvekt"));
    	dao.setSltanr(rs.getString("sltanr"));
    	dao.setSltar(rs.getString("sltar"));
    	dao.setSlpva(rs.getString("slpva"));
    	dao.setSlsats(rs.getString("slsats"));
    	dao.setSltn(rs.getString("sltn"));
    	dao.setSlkdae(rs.getString("slkdae"));
    	dao.setSlkdse(rs.getString("slkdse"));
    	dao.setSlto(rs.getString("slto"));
    	dao.setSlcref(rs.getString("slcref"));
    	//own
    	dao.setR31(rs.getString("r31"));
    	dao.setPref(rs.getString("pref"));
    	dao.setMf(rs.getString("mf"));
    	
    	
    	
        return dao;
    }

}


