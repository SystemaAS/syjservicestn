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
    	//countries
    	dao.setTaeosb(rs.getString("taeosb"));
    	dao.setTaeosk(rs.getString("taeosk"));
    	dao.setTatsjb(rs.getString("tatsjb"));
    	dao.setTatsjk(rs.getString("tatsjk"));
    	dao.setTatyrb(rs.getString("tatyrb"));
    	dao.setTatyrk(rs.getString("tatyrk"));
    	dao.setTaisrb(rs.getString("taisrb"));
    	dao.setTaisrk(rs.getString("taisrk"));
    	dao.setTaellb(rs.getString("taellb"));
    	dao.setTaellk(rs.getString("taellk"));
    	//
    	dao.setTabulb(rs.getString("tabulb"));
    	dao.setTabulk(rs.getString("tabulk"));
    	dao.setTapolb(rs.getString("tapolb"));
    	dao.setTapolk(rs.getString("tapolk"));
    	dao.setTaromb(rs.getString("taromb"));
    	dao.setTaromk(rs.getString("taromk"));
    	dao.setTan05b(rs.getString("tan05b"));
    	dao.setTan05k(rs.getString("tan05k"));
    	dao.setTan06b(rs.getString("tan06b"));
    	dao.setTan06k(rs.getString("tan06k"));
    	
    	
    	
        return dao;
    }

}


