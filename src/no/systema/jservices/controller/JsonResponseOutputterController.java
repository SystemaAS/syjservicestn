package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Application
import no.systema.jservices.model.dao.entities.CusdfDao;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.model.dao.entities.DbConnectionTesterDao;
import no.systema.jservices.model.dao.services.DbConnectionTesterDaoServices;


import no.systema.main.util.JsonSpecialCharactersManager;

/**
 * Json Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 */

@Controller
public class JsonResponseOutputterController {
	private static Logger logger = Logger.getLogger(JsonResponseOutputterController.class.getName());
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static String JSON_START = "{";
	private static String JSON_END = "}";
	private static String JSON_QUOTES = "\"";
	private static String JSON_RECORD_SEPARATOR = ",";
	private static String JSON_FIELD_SEPARATOR = ",";
	
	private static String JSON_OPEN_LIST = "[";
	private static String JSON_CLOSE_LIST = "]";
	private static String JSON_OPEN_LIST_RECORD = "{";
	private static String JSON_CLOSE_LIST_RECORD = "}";
	
	
	
	/**
	 * 
	 * http://localhost:8080/syjservicestn/syjsJS001.do?user=OSCAR
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value="syjsJS001.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsJS001() {
		StringBuffer sb = new StringBuffer();
		try{
			logger.info("Inside syjsJS001");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			
			//get list
			logger.info("Before dao getList");
			List<CusdfDao> list = this.cundfDaoServices.getList();
			logger.info("After dao getList");
			//build the return JSON
			sb.append(JSON_START);
			sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes("OSCAR") + ",");
			sb.append(this.setFieldQuotes("list") + ":");
			sb.append(JSON_OPEN_LIST);
			int counter = 1;
			for(CusdfDao record : list){
				if(counter>1){ sb.append(JSON_RECORD_SEPARATOR); }
				sb.append(JSON_OPEN_LIST_RECORD); 
				sb.append(JSON_QUOTES + "knavn" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKnavn()) + JSON_QUOTES);
				sb.append(JSON_FIELD_SEPARATOR );
				sb.append(JSON_QUOTES + "adr1" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getAdr1()) + JSON_QUOTES);
				sb.append(JSON_CLOSE_LIST_RECORD);
				counter++;
			}
			sb.append(JSON_CLOSE_LIST);
			sb.append(JSON_END);
			
			
		}catch(Exception e){
			return "ERROR [JsonResponseOutputterController]";
		}
	    
		return sb.toString();
	}
	
	/**
	 * http://localhost:8080/syjservicestn/syjsdbconn.do?user=OSCAR
	 * @return
	 */
	@RequestMapping(value="syjsdbconn.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsJS001db() {
		StringBuffer sb = new StringBuffer();
		try{
			logger.info("Inside syjsdbconn");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			
			//get list
			logger.info("Before dao getList");
			List<DbConnectionTesterDao> list = this.dbConnectionTesterDaoServices.getList();
			logger.info("After dao getList");
			//build the return JSON
			sb.append(JSON_START);
			sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes("OSCAR") + ",");
			sb.append(this.setFieldQuotes("list") + ":");
			sb.append(JSON_OPEN_LIST);
			int counter = 1;
			for(DbConnectionTesterDao record : list){
				if(counter>1){ sb.append(JSON_RECORD_SEPARATOR); }
				sb.append(JSON_OPEN_LIST_RECORD); 
				sb.append(JSON_QUOTES + "text" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getText()) + JSON_QUOTES);
				sb.append(JSON_CLOSE_LIST_RECORD);
				counter++;
			}
			sb.append(JSON_CLOSE_LIST);
			sb.append(JSON_END);
			
			
		}catch(Exception e){
			return "ERROR [JsonResponseOutputterController]";
		}
	    
		return sb.toString();
	}
	
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String setFieldQuotes(String value){
		String retval = value;
		retval = this.JSON_QUOTES + value + this.JSON_QUOTES;
		
		return retval;
	}
	
	//DAO SERVICES
	@Qualifier ("cundfDaoServices")
	private CundfDaoServices cundfDaoServices;
	@Autowired
	@Required
	public void setCundfDaoServices (CundfDaoServices value){ this.cundfDaoServices = value; }
	public CundfDaoServices getCundfDaoServices(){ return this.cundfDaoServices; }
	
	
	@Qualifier ("dbConnectionTesterDaoServices")
	private DbConnectionTesterDaoServices dbConnectionTesterDaoServices;
	@Autowired
	@Required
	public void setDbConnectionTesterDaoServices (DbConnectionTesterDaoServices value){ this.dbConnectionTesterDaoServices = value; }
	public DbConnectionTesterDaoServices getDbConnectionTesterDaoServices(){ return this.dbConnectionTesterDaoServices; }
	
	
	
	
	
}

