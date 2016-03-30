package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.KodtlikDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.KodtlikDaoServices;
import no.systema.jservices.model.dao.entities.CusdfDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.main.util.JsonSpecialCharactersManager;




/**
 * Json Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Mar 30, 2016
 * 
 */

@Controller
public class JsonTvinnMaintImportResponseOutputterController {
	private static Logger logger = Logger.getLogger(JsonTvinnMaintImportResponseOutputterController.class.getName());
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
	 * FreeForm Source:
	 * 	 File: 		KODTLIK
	 * 	 PGM:		SYFT19
	 * 	 Member: 	SAD Import Maintenance - SELECT LIST
	 *  
	 * 
	 * @return
	 * @Example http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsSYFT19R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsTNOE042R( HttpSession session, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSYFT19R.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			
			KodtlikDao dao = new KodtlikDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //DEBUG->> logger.info(sadlDao.getSlalfa());
            
			//Check user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//do INSERT
				logger.info("Before SELECT * ...");
				List<KodtlikDao> list = this.kodtlikDaoServices.getList(dbErrorStackTrace);
				//process result
				if (list!=null){
					sb.append(this.setJsonResult(userName, list));
				}else{
					errMsg = "ERROR on SELECT *: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.info("After SELECT * :" + " " + status + errMsg );
					sb.append(this.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
				
				
			}else{
				errMsg = "ERROR on SELECT * ";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(this.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param user
	 * @param list
	 * @return
	 */
	private String setJsonResult(String user, List<KodtlikDao> list ){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes("") + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JSON_OPEN_LIST);
		int counter = 1;
		for(KodtlikDao record : list){
			if(counter>1){ sb.append(JSON_RECORD_SEPARATOR); }
			sb.append(JSON_OPEN_LIST_RECORD); 
			sb.append(JSON_QUOTES + "klista" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlista()).trim() + JSON_QUOTES);
			sb.append(JSON_FIELD_SEPARATOR );
			sb.append(JSON_QUOTES + "kluni" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKliuni()).trim() + JSON_QUOTES);
			sb.append(JSON_FIELD_SEPARATOR );
			sb.append(JSON_QUOTES + "klikod" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlikod()).trim() + JSON_QUOTES);
			sb.append(JSON_FIELD_SEPARATOR );
			sb.append(JSON_QUOTES + "klinav" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlinav()).trim() + JSON_QUOTES);
			sb.append(JSON_FIELD_SEPARATOR );
			sb.append(JSON_QUOTES + "klisto" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlisto()).trim() + JSON_QUOTES);
			sb.append(JSON_FIELD_SEPARATOR );
			sb.append(JSON_QUOTES + "klixxx" + JSON_QUOTES + ":" + JSON_QUOTES + this.jsonFixMgr.cleanRecord(record.getKlixxx()).trim() + JSON_QUOTES);
			sb.append(JSON_CLOSE_LIST_RECORD);
			counter++;
		}
		sb.append(JSON_CLOSE_LIST);
		sb.append(JSON_END);
		
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
	
	/**
	 * 
	 * @param user
	 * @param errMsg
	 * @param status
	 * @param dbErrorStackTrace
	 * @return
	 */
	private String setJsonSimpleErrorResult(String user, String errMsg, String status, StringBuffer dbErrorStackTrace){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes(errMsg) + ",");
		sb.append(this.setFieldQuotes("list") + ":");
		sb.append(JSON_OPEN_LIST);
		//START RECORD
		sb.append(JSON_OPEN_LIST_RECORD);
		sb.append(JSON_QUOTES + "status" + JSON_QUOTES + ":" + JSON_QUOTES + status + JSON_QUOTES);
		sb.append(JSON_CLOSE_LIST_RECORD);
		//NEW RECORD
		sb.append(JSON_RECORD_SEPARATOR);
		sb.append(JSON_OPEN_LIST_RECORD);
		sb.append(JSON_QUOTES + "desc" + JSON_QUOTES + ":" + JSON_QUOTES + dbErrorStackTrace.toString() + JSON_QUOTES);
		sb.append(JSON_CLOSE_LIST_RECORD);
		
		//END LIST OF RECORDS
		sb.append(JSON_CLOSE_LIST);
		sb.append(JSON_END);
		
		return sb.toString();
	}
	
	
	@Qualifier ("kodtlikDaoServices")
	private KodtlikDaoServices kodtlikDaoServices;
	@Autowired
	@Required
	public void setKodtlikDaoServices (KodtlikDaoServices value){ this.kodtlikDaoServices = value; }
	public KodtlikDaoServices getKodtlikDaoServices(){ return this.kodtlikDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

