package no.systema.jservices.tvinn.sad.sadexport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


 
import org.apache.logging.log4j.*;
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
import no.systema.jservices.tvinn.sad.sadexport.controller.rules.Tnoe042R;
import no.systema.jservices.tvinn.sad.sadexport.model.dao.entities.SadlDao;
import no.systema.jservices.tvinn.sad.sadexport.model.dao.services.Sadl_KundensVarRegDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;




/**
 * Json Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Feb 11, 2016
 * 
 */

@Controller
public class JsonTvinnResponseOutputterController {
	private static Logger logger = LogManager.getLogger(JsonTvinnResponseOutputterController.class.getName());
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
	 * 	 File: 		QRPGSRC ??
	 * 	 Library:	SYTTAX ??
	 * 	 Member: 	TNOE042R: TVINN Export - INSERT new record in KundensVarReg 
	 *  
	 *   Replaces: TNOE042R.pgm - RPG-module
	 * 
	 * @return
	 * @Example http://gw.systema.no:8080/syjservicestn/syjsTNOE042R.do?user=OSCAR&slknr=1&slalfa=ALFA_03&sltxt=INDICATOR+LAMP&sloppl=NO&sltanr=15041099
	 * 
	 */
	@RequestMapping(value="syjsTNOE042R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsTNOE042R( HttpSession session, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsTNOE042R.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			
			SadlDao sadlDao = new SadlDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(sadlDao);
            binder.bind(request);
            //DEBUG->> logger.info(sadlDao.getSlalfa());
            
			//Check user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG -> logger.info("USERNAME:" + userName);
            Tnoe042R noeObj = new Tnoe042R();
            
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(noeObj.validInput(userName, sadlDao)){
				//do INSERT
				logger.info("Before INSERT");
				int insertResult = this.sadl_KundensVarRegDaoServices.insertIntoSadl(sadlDao, dbErrorStackTrace);
				//process result
				if (insertResult<0){
					errMsg = "ERROR on INSERT";
					status = "error";
				}
				logger.info("After INSERT:" + " " + status + errMsg );
				sb.append(this.setJsonSimpleResult(userName, errMsg, status, dbErrorStackTrace));
				
			}else{
				errMsg = "ERROR on insert";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(this.setJsonSimpleResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
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
	private String setJsonSimpleResult(String user, String errMsg, String status, StringBuffer dbErrorStackTrace){
		StringBuffer sb = new StringBuffer();
		//build the return JSON
		sb.append(JSON_START);
		sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes(user) + ",");
		sb.append(this.setFieldQuotes("errMsg") + ":" + this.setFieldQuotes(errMsg) + ",");
		sb.append(this.setFieldQuotes("insertresult") + ":");
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
	
	
	
	
	
	
	@Qualifier ("sadl_KundensVarRegDaoServices")
	private Sadl_KundensVarRegDaoServices sadl_KundensVarRegDaoServices;
	@Autowired
	@Required
	public void setSadl_KundensVarRegDaoServices (Sadl_KundensVarRegDaoServices value){ this.sadl_KundensVarRegDaoServices = value; }
	public Sadl_KundensVarRegDaoServices getSadl_KundensVarRegDaoServices(){ return this.sadl_KundensVarRegDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

