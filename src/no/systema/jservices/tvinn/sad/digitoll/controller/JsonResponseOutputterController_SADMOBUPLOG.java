package no.systema.jservices.tvinn.sad.digitoll.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;


import org.apache.commons.lang3.StringUtils;
 
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmobuplogDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmobuplogDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Sep 2024
 * 
 */

@Controller
public class JsonResponseOutputterController_SADMOBUPLOG {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOBUPLOG.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADMOBUPLOG
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADMOBUPLOG.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADMOBUPLOG.do?user=OSCAR&orgnr=...
	 * 
	 */
	@RequestMapping(value="syjsSADMOBUPLOG.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOBUPLOG");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			logger.warn("User:" + user);
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				SadmobuplogDao dao = new SadmobuplogDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            logger.warn("file:" + dao.getFile());
	            logger.warn("date:" + dao.getDate());
	            logger.warn("time:" + dao.getTime());
	            
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.warn("Before SELECT ...");
	            if(StringUtils.isNotEmpty(dao.getFile()) ) {
					logger.warn("inside: findById (file...)");
					list = this.sadmobuplogDaoServices.findById(dao.getFile(), dbErrorStackTrace);
				}else{
					logger.warn("inside: find (dao)");
					list = this.sadmobuplogDaoServices.find(dao, dbErrorStackTrace);
				}
	            logger.warn("After SELECT ..." );
	            
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.warn("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
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
		session.invalidate();
		return sb.toString();
	}
	
	
	@RequestMapping(value="syjsSADMOBUPLOG_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOBUPLOG_U.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SadmobuplogDao dao = new SadmobuplogDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("file:" + dao.getFile());
            logger.warn("date:" + dao.getDate());
            logger.warn("time:" + dao.getTime());
            
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            
            
            //Start here
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				
				logger.warn("Before INSERT ...");
				List<SadmobuplogDao> list = new ArrayList<SadmobuplogDao>();
				
				//do ADD
				if("A".equals(mode)){
					if(StringUtils.isNotEmpty(dao.getFile()) && StringUtils.isNotEmpty(dao.getDate()) && StringUtils.isNotEmpty(dao.getTime())) {
						dmlRetval = this.sadmobuplogDaoServices.insert(dao, dbErrorStackTrace);
					}else {
						//write JSON error output
						errMsg = "ERROR on INSERT: invalid values (file, date ,time) for INSERT?";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else if("D".equals(mode)){
					if(StringUtils.isNotEmpty(dao.getFile())) {
						dmlRetval = this.sadmobuplogDaoServices.delete(dao, dbErrorStackTrace);
					}else {
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid id (file) for DELETE?";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
					
				}else{
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid mode for INSERT?";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
				
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on INSERT/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}
				
			}else{
				//write JSON error output
				errMsg = "ERROR on UPDATE";
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
		session.invalidate();
		return sb.toString();
	}
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadmobuplogDaoServices sadmobuplogDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

