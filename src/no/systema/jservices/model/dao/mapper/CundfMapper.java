package no.systema.jservices.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.CusdfDao;
import no.systema.jservices.model.dao.services.CundfDaoServicesImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Nov 4, 2015
 * 
 */
public class CundfMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CusdfDao dao = new CusdfDao();
    	dao.setKnavn(rs.getString("knavn"));
    	dao.setAdr1(rs.getString("adr1"));
    	dao.setAdr2(rs.getString("adr2"));
    	dao.setAdr3(rs.getString("adr3"));
    	dao.setPostnr(rs.getString("postnr"));
    	
    	//DEBUG-->logger.info(cusdf.getKnavn());
    	
        return dao;
    }

}


