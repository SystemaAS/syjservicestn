package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrughDao;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 8, 2016
 * 
 */
public class TrughMapper implements RowMapper<Object> {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		TrughDao dao = new TrughDao();

		// We use reflection since there are many fields. We could have written
		// all fields manually without reflection. Refer to other daos.
		try {
			Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			for (Field field : list) {
				String name = (String) field.getName();
				// here we put the value
				field.setAccessible(true);
				field.set(dao, rs.getString(name));
			}
		} catch (Exception e) {
			e.toString();
		}

		return dao;
	}

}
