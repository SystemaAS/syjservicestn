package no.systema.jservices.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.CusdfDao;
import no.systema.jservices.model.dao.services.CundfDaoServicesImpl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date  Nov 4, 2015
 * 
 */
public class CundfMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(CundfMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CusdfDao dao = new CusdfDao();
/*
    	dao.setKnavn(rs.getString("knavn"));
    	dao.setAdr2(rs.getString("adr2"));
    	dao.setAdr3(rs.getString("adr3"));
    	dao.setPostnr(rs.getString("postnr"));
  */  	

       	try{
	    	Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			for(Field field : list){
				String name = (String)field.getName();
				if(name!=null && !"".equals(name)){
					//DEBUG --> logger.info(field.getName() + " Name:" + name + " value:" + rs.getString(name));
				}
				try{
					//here we put the value
					field.setAccessible(true);
					field.set(dao, rs.getString(name));
				}catch(Exception e){
					//Usually when no column matches the JavaBean property...
					//logger.info(e.getMessage() + e.toString());
					continue;
				}
			}
    	}catch(Exception e){
    		e.toString();
    		logger.info(e.getMessage() + e.toString());
    	}
        
        return dao;
    }

}


