package no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Maj , 2016
 * 
 */
public class TariMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(TariMapper.class.getName());
	
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
    	dao.setTan07b(rs.getString("tan07b"));
    	dao.setTan07k(rs.getString("tan07k"));
    	dao.setTaungb(rs.getString("taungb"));
    	dao.setTaungk(rs.getString("taungk"));
    	//
    	dao.setTaslob(rs.getString("taslob"));
    	dao.setTaslok(rs.getString("taslok"));
    	dao.setTamulb(rs.getString("tamulb"));
    	dao.setTamulk(rs.getString("tamulk"));
    	dao.setTaoulb(rs.getString("taoulb"));
    	dao.setTaoulk(rs.getString("taoulk"));
    	dao.setTagrlb(rs.getString("tagrlb"));
    	dao.setTagrlk(rs.getString("tagrlk"));
    	dao.setTaferb(rs.getString("taferb"));
    	dao.setTaferk(rs.getString("taferk"));
    	dao.setTaistb(rs.getString("taistb"));
    	dao.setTaistk(rs.getString("taistk"));
    	//
    	dao.setTamarb(rs.getString("tamarb"));
    	dao.setTamark(rs.getString("tamark"));
    	dao.setTan08b(rs.getString("tan08b"));
    	dao.setTan08k(rs.getString("tan08k"));
    	dao.setTan09b(rs.getString("tan09b"));
    	dao.setTan09k(rs.getString("tan09k"));
    	dao.setTan10b(rs.getString("tan10b"));
    	dao.setTan10k(rs.getString("tan10k"));
    	dao.setTamexb(rs.getString("tamexb"));
    	dao.setTamexk(rs.getString("tamexk"));
    	dao.setTavgab(rs.getString("tavgab"));
    	dao.setTavgak(rs.getString("tavgak"));
    	//
    	dao.setTan01b(rs.getString("tan01b"));
    	dao.setTan01k(rs.getString("tan01k"));
    	dao.setTan02b(rs.getString("tan02b"));
    	dao.setTan02k(rs.getString("tan02k"));
    	dao.setTan03b(rs.getString("tan03b"));
    	dao.setTan03k(rs.getString("tan03k"));
    	dao.setTan04b(rs.getString("tan04b"));
    	dao.setTan04k(rs.getString("tan04k"));
    	dao.setTan11b(rs.getString("tan11b"));
    	dao.setTan11k(rs.getString("tan11k"));
    	dao.setTan12b(rs.getString("tan12b"));
    	dao.setTan12k(rs.getString("tan12k"));
    	//
    	dao.setTan13b(rs.getString("tan13b"));
    	dao.setTan13k(rs.getString("tan13k"));
    	dao.setTan14b(rs.getString("tan14b"));
    	dao.setTan14k(rs.getString("tan14k"));
    	dao.setTan15b(rs.getString("tan15b"));
    	dao.setTan15k(rs.getString("tan15k"));
    	//
    	dao.setTarest(rs.getString("tarest"));
    	dao.setTakapa(rs.getString("takapa"));
    	
    	
        return dao;
    }

}


