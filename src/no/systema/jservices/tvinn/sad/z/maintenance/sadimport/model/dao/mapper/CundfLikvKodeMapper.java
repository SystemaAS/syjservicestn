package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.mapper;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.CundfLikvKodeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * 
 * @author oscardelatorre
 * @date  May 2, 2016
 * 
 */
public class CundfLikvKodeMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(CundfLikvKodeMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CundfLikvKodeDao dao = new CundfLikvKodeDao();
    	dao.setFirma(rs.getString(1));
    	dao.setKundnr(rs.getString(2));
    	dao.setKnavn(rs.getString(3));
    	dao.setAdr1(rs.getString(4));
    	dao.setSylikv(rs.getString(5));
    	
        return dao;
    }

}


