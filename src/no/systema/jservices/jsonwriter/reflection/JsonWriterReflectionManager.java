package no.systema.jservices.jsonwriter.reflection;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants;
import no.systema.jservices.model.dao.entities.IDao; 

public class JsonWriterReflectionManager {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static Logger logger = Logger.getLogger(JsonWriterReflectionManager.class.getName());
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	public String getGettersFromRecord(IDao record){
		StringBuffer jsonReflectionOutput = new StringBuffer();
		try{
			Class<?> recordClazz = record.getClass();
			Method  theMethod = null;
			Class<?> returnType = null;
			int counter = 1;
			for(Method method : recordClazz.getDeclaredMethods()){
				//only getters
				String getter = method.getName();
				if(getter.startsWith("get") && !getter.endsWith("PropertyName")){
					theMethod= recordClazz.getDeclaredMethod(method.getName());
					returnType = theMethod.getReturnType();
					if(returnType.equals(String.class)){
						String field = theMethod.getName().replace("get", "").toLowerCase();
						String value = (String)theMethod.invoke(record);
						//logger.info(fieldName + "XX" + value);
						if(counter>1){ jsonReflectionOutput.append(JsonConstants.JSON_FIELD_SEPARATOR ); }
						jsonReflectionOutput.append(JsonConstants.JSON_QUOTES + field + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(value).trim() + JsonConstants.JSON_QUOTES);
						counter ++;
					}
				}
				
			}
			
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors);
		}
		
		return jsonReflectionOutput.toString();
	}
}
