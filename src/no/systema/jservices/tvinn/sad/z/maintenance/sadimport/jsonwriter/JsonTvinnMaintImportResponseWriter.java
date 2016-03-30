package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter;

import java.util.List;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;
import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants;


public class JsonTvinnMaintImportResponseWriter {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	
	/**
	 * 
	 * @param user
	 * @param list
	 * @return
	 */
	public String setJsonResult_SYFT19R_GetList(String user, List<KodtlikDao> list ){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JsonConstants.JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JsonConstants.JSON_OPEN_LIST);
		int counter = 1;
		for(KodtlikDao record : list){
			if(counter>1){ sb.append(JsonConstants.JSON_RECORD_SEPARATOR); }
			sb.append(JsonConstants.JSON_OPEN_LIST_RECORD); 
			sb.append(JsonConstants.JSON_QUOTES + "klista" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlista()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + "kluni" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKliuni()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + "klikod" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlikod()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + "klinav" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlinav()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + "klisto" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlisto()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + "klixxx" + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlixxx()).trim() + JsonConstants.JSON_QUOTES);
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
	 * @param value
	 * @return
	 */
	private String setFieldQuotes(String value){
		String retval = value;
		retval = JsonConstants.JSON_QUOTES + value + JsonConstants.JSON_QUOTES;
		
		return retval;
	}
}
