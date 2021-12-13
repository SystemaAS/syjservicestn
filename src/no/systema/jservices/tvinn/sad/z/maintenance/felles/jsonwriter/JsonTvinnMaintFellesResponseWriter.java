package no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;


import org.apache.logging.log4j.*;

import no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.entities.KodtlbDao;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants;

/**
 * 
 * @author oscardelatorre
 * JSON outputter
 * 
 */
public class JsonTvinnMaintFellesResponseWriter extends JsonResponseWriter{
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static Logger logger = LogManager.getLogger(JsonTvinnMaintFellesResponseWriter.class.getName());
	
	/**
	 * 
	 * SAD012R
	 * 
	 * @param user
	 * @param list
	 * @return
	 */
	public String setJsonResult_SAD012R_GetList(String user, List<KodtlbDao> list ){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JsonConstants.JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JsonConstants.JSON_OPEN_LIST);
		int counter = 1;
		for(KodtlbDao record : list){
			
			if(counter>1){ sb.append(JsonConstants.JSON_RECORD_SEPARATOR); }
			sb.append(JsonConstants.JSON_OPEN_LIST_RECORD); 
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbstaPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbsta()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbuniPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbuni()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbkodPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbkod()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbktPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbkt()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbnavPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbnav()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbfokPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbfok()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbprmPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbprm()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbfrkPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbfrk()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			//----------------------------------------------------------------------------
			//START klbxxx:
			//this field MUST NOT be trimmed! klbxxx can contain leading spaces for sure!
			//----------------------------------------------------------------------------
			if(record.getKlbxxx()!=null && record.getKlbxxx().startsWith(" ")){
				if(this.jsonFixMgr.isValidContentWithLeadingSpaces(record.getKlbxxx())){
					record.setKlbxxx(this.jsonFixMgr.trimEnd(record.getKlbxxx()));
				}else{
					record.setKlbxxx(this.jsonFixMgr.cleanRecord(record.getKlbxxx()).trim());
				}
			}else{
				record.setKlbxxx(this.jsonFixMgr.cleanRecord(record.getKlbxxx()).trim());
			}
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbxxxPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + record.getKlbxxx() + JsonConstants.JSON_QUOTES);
			//END klbxxx
			
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
