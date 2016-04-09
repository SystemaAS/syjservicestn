package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter;

import java.util.List;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlbDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtsiDao;

import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants;


public class JsonTvinnMaintImportResponseWriter {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	
	/**
	 * 
	 * SYFT19R
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
			sb.append(JsonConstants.JSON_QUOTES + record.getKlistaPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlista()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKliuniPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKliuni()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlikodPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlikod()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlinavPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlinav()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlistoPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlisto()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKlixxxPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlixxx()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_CLOSE_LIST_RECORD);
			counter++;
		}
		sb.append(JsonConstants.JSON_CLOSE_LIST);
		sb.append(JsonConstants.JSON_END);
		
		return sb.toString();
	}
	
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
			sb.append(JsonConstants.JSON_QUOTES + record.getKlbxxxPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlbxxx()).trim() + JsonConstants.JSON_QUOTES);
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
	 * @param list
	 * @return
	 */
	public String setJsonResult_SYFT10R_GetList(String user, List<KodtsiDao> list ){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JsonConstants.JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JsonConstants.JSON_OPEN_LIST);
		int counter = 1;
		for(KodtsiDao record : list){
			    
			
			if(counter>1){ sb.append(JsonConstants.JSON_RECORD_SEPARATOR); }
			sb.append(JsonConstants.JSON_OPEN_LIST_RECORD); 
			sb.append(JsonConstants.JSON_QUOTES + record.getKsistaPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsista()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKsiuniPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsiuni()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKsisigPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsisig()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKsinavPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsinav()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKsixxxPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsixxx()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKsovlPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsovl()).trim() + JsonConstants.JSON_QUOTES);
			sb.append(JsonConstants.JSON_FIELD_SEPARATOR );
			sb.append(JsonConstants.JSON_QUOTES + record.getKsuserPropertyName() + JsonConstants.JSON_QUOTES + ":" + JsonConstants.JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKsuser()).trim() + JsonConstants.JSON_QUOTES);
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
