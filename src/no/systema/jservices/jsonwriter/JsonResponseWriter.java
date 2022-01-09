package no.systema.jservices.jsonwriter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;


import org.slf4j.*;
import no.systema.jservices.jsonwriter.reflection.JsonWriterReflectionManager;
import no.systema.jservices.model.dao.entities.IDao;

import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants;

/**
 * JSON outputter
 * 
 * @author oscardelatorre
 * @date Jun 3, 2016
 * 
 */
public class JsonResponseWriter {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static Logger logger = LoggerFactory.getLogger(JsonResponseWriter.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param list
	 * @return
	 */
	public String setJsonResult_Common_GetList(String user, List<IDao> list ){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JsonConstants.JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JsonConstants.JSON_OPEN_LIST);
		int counter = 1;
		for(IDao record : list){
			if(counter>1){ sb.append(JsonConstants.JSON_RECORD_SEPARATOR); }
			sb.append(JsonConstants.JSON_OPEN_LIST_RECORD);
			//doIt
			sb.append(new JsonWriterReflectionManager().getGettersFromRecord(record));
			//close the list
			sb.append(JsonConstants.JSON_CLOSE_LIST_RECORD);
			counter++;
		}
		sb.append(JsonConstants.JSON_CLOSE_LIST);
		sb.append(JsonConstants.JSON_END);
		
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @param user
	 * @param errMsg
	 * @param status
	 * @param dbErrorStackTrace
	 * @return
	 */
	public String setJsonSimpleErrorResult(String user, String errMsg, String status, StringBuffer dbErrorStackTrace){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JsonConstants.JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes(errMsg) + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JsonConstants.JSON_OPEN_LIST);
		//START RECORD
		sb.append(JsonConstants.JSON_OPEN_LIST_RECORD);
		sb.append(JsonConstants.JSON_QUOTES + "status" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + status + JsonConstants.JSON_QUOTES);
		sb.append(JsonConstants.JSON_CLOSE_LIST_RECORD);
		//NEW RECORD
		sb.append(JsonConstants.JSON_RECORD_SEPARATOR);
		sb.append(JsonConstants.JSON_OPEN_LIST_RECORD);
		sb.append(JsonConstants.JSON_QUOTES + "desc" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + dbErrorStackTrace.toString() + JsonConstants.JSON_QUOTES);
		sb.append(JsonConstants.JSON_CLOSE_LIST_RECORD);
		
		//END LIST OF RECORDS
		sb.append(JsonConstants.JSON_CLOSE_LIST);
		sb.append(JsonConstants.JSON_END);
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param user
	 * @param status
	 * @return
	 */
	public String setJsonSimpleValidResult(String user, String status){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JsonConstants.JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JsonConstants.JSON_OPEN_LIST);
		//START RECORD
		sb.append(JsonConstants.JSON_OPEN_LIST_RECORD);
		sb.append(JsonConstants.JSON_QUOTES + "status" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + status + JsonConstants.JSON_QUOTES);
		sb.append(JsonConstants.JSON_CLOSE_LIST_RECORD);
		
		//END LIST OF RECORDS
		sb.append(JsonConstants.JSON_CLOSE_LIST);
		sb.append(JsonConstants.JSON_END);
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String setFieldQuotes(String value){
		String retval = value;
		retval = JsonConstants.JSON_QUOTES + value + JsonConstants.JSON_QUOTES;
		
		return retval;
	}
}
