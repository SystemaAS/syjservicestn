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
import no.systema.jservices.jsonwriter.JsonTvinnMaintImportResponseWriter_EDIFTP;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.entities.EdissDao;
import no.systema.jservices.model.dao.entities.EdisssDao;
import no.systema.jservices.model.dao.services.EdissFtpLogDaoServices;
import no.systema.jservices.model.dao.services.EdisssFtpLogDaoServices;




/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */

@Controller
public class JsonTvinnImportResponseOutputterController_EDIFTP {
	private static Logger logger = Logger.getLogger(JsonTvinnImportResponseOutputterController_EDIFTP.class.getName());
	
	/**
	 * Source:
	 * 	 File: 		EDISS
	 * 	 PGM:		EDI42R
	 * 	 Member: 	EDI FTP Log - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsEDI42R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsEDI42R.do?user=OSCAR&sssn=123
	 * 
	 */
	@RequestMapping(value="syjsEDI42R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsEDI42R( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter_EDIFTP jsonWriter = new JsonTvinnMaintImportResponseWriter_EDIFTP();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsEDI42R");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				EdissDao dao = new EdissDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( dao.getSssn()!=null && !"".equals(dao.getSssn()) ){
					logger.info("findById");
					list = this.edissFtpLogDaoServices.findById(dao.getSssn(), dbErrorStackTrace);
				}else{
					logger.info("getList (all)");
					list = this.edissFtpLogDaoServices.getList(dbErrorStackTrace);
				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_EDIFTP_GetList(userName, list));
				}else{
					//phantom return
					logger.info("phantom return...");
					sb.append(jsonWriter.setJsonResult_EDIFTP_GetList(userName, new ArrayList()));
				}
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		return sb.toString();
	}
	
	/**
	 * * Source:
	 * 	 File: 		EDISSS
	 * 	 PGM:		EDI43R
	 * 	 Member: 	EDI FTP Log (deep level) - SELECT LIST or SELECT SPECIFIC
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsEDI43R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsEDI43R.do?user=OSCAR&ssssn=123&sssdt=20160515&ssstm=131027
	 * 
	 */
	@RequestMapping(value="syjsEDI43R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsEDI43R( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter_EDIFTP jsonWriter = new JsonTvinnMaintImportResponseWriter_EDIFTP();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsEDI43R");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				EdisssDao dao = new EdisssDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( dao.getSsssn()!=null && !"".equals(dao.getSsssn()) &&
            		dao.getSssdt()!=null && !"".equals(dao.getSssdt()) &&
            		dao.getSsstm()!=null && !"".equals(dao.getSsstm()) ){
					
	            	logger.info("findById");
					list = this.edisssFtpLogDaoServices.findById(dao.getSsssn(), dao.getSssdt(), dao.getSsstm(), dbErrorStackTrace);
				}else{
					//N/A
					//logger.info("getList (all)");
					//list = this.edissFtpLogDaoServices.getList(dbErrorStackTrace);
				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_EDIFTP_GetList(userName, list));
				}else{
					//phantom return
					logger.info("phantom return...");
					sb.append(jsonWriter.setJsonResult_EDIFTP_GetList(userName, new ArrayList()));
					
				}
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		return sb.toString();
	}
	
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("edissFtpLogDaoServices")
	private EdissFtpLogDaoServices edissFtpLogDaoServices;
	@Autowired
	@Required
	public void setEdissFtpLogDaoServices (EdissFtpLogDaoServices value){ this.edissFtpLogDaoServices = value; }
	public EdissFtpLogDaoServices getEdissFtpLogDaoServices(){ return this.edissFtpLogDaoServices; }
	
	
	@Qualifier ("edisssFtpLogDaoServices")
	private EdisssFtpLogDaoServices edisssFtpLogDaoServices;
	@Autowired
	@Required
	public void setEdisssFtpLogDaoServices (EdisssFtpLogDaoServices value){ this.edisssFtpLogDaoServices = value; }
	public EdisssFtpLogDaoServices getEdisssFtpLogDaoServices(){ return this.edisssFtpLogDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

