package no.systema.jservices.tvinn.sad.z.maintenance.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.model.dao.entities.KodtvaDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Maj 31, 2016
 * 
 */
public class KodtvaMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(KodtvaMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	KodtvaDao dao = new KodtvaDao();
    	
    	dao.setKvasta(rs.getString("kvasta"));
    	dao.setKvauni(rs.getString("kvuni"));
    	dao.setKvakod(rs.getString("kvakod"));
    	dao.setKvakrs(rs.getString("kvakrs"));
    	dao.setKvaomr(rs.getString("kvaomr"));
    	dao.setKvadt(rs.getString("kvadt"));
    	dao.setKvagkr(rs.getString("kvagkr"));
    	dao.setKvaxxx(rs.getString("kvaxxx"));
    	dao.setKvagv(rs.getString("kvagv"));
    	
    	
        return dao;
    }

}


