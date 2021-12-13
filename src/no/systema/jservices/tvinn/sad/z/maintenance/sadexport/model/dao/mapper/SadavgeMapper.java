package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SadavgeDao;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 8, 2016
 * 
 */
public class SadavgeMapper implements RowMapper<Object> {
	private static Logger logger = LogManager.getLogger(SadavgeMapper.class.getName());
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SadavgeDao dao = new SadavgeDao();

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
				}catch (Exception e){
					//Usually when no column matches the JavaBean property...
					//logger.info(e.getMessage() + e.toString());
					continue;
				}
			}
    	}catch(Exception e){
    		e.toString();
    		//logger.info(e.getMessage() + e.toString());
    	}
		return dao;
	}

}
