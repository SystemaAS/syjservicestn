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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.ZadmoattfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.ZadmoattfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Mar 2025
 * 
 */

@Controller
public class JsonResponseOutputterController_ZADMOATTF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_ZADMOATTF.class.getName());
	/**
	 * File: 	ZADMOATTF
	 * Member: 	ZADMOATTF Digitoll - SELECT SPECIFIC 
	 * 
	 * @Example SELECT: http://gw.systema.no:8080/syjservicestn/syjsSADMOATTF.do?user=OSCAR&...
	 *
	 * 
	 *
	*/
	@RequestMapping(value="syjsZADMOATTF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsZADMOATTF");
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
				ZadmoattfDao dao = new ZadmoattfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            
				logger.warn("find");
				list = this.zadmoattfDaoServices.find(dao, dbErrorStackTrace);
				
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.find";
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
		//logger.debug(sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * 
	 * Update Database DML operations
	 * File: 	ZADMOATTF
	 * Member: 	ZADMOATTF Digitoll - INSERT SPECIFIC 
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsZADMOATTF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsZADMOATTF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsZADMOATTF_U.do");
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
			ZadmoattfDao dao = new ZadmoattfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("id:" + dao.getId());
            logger.warn("avsid:" + dao.getAvsid());
            logger.warn("motid:" + dao.getMotid());
            logger.warn("docname:" + dao.getDocname());
            logger.warn("typref:" + dao.getTypref());
            logger.warn("docref:" + dao.getDocref());
            
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					
					if(StringUtils.isNotEmpty(dao.getId()) && StringUtils.isNotEmpty(dao.getAvsid()) ){
						logger.warn("Before DELETE ...");
						dmlRetval = this.zadmoattfDaoServices.delete(dao, dbErrorStackTrace);
						
					}else {
						//write JSON error output
						errMsg = "ERROR on DELETE: emdkm-id is empty ??? ";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else if("A".equals(mode)){
					
				    if(StringUtils.isNotEmpty(dao.getId()) && StringUtils.isNotEmpty(dao.getAvsid()) && StringUtils.isNotEmpty(dao.getMotid()) ) {
						
						logger.warn("Before INSERT ...");
						//do ADD
						dmlRetval = this.zadmoattfDaoServices.insert(dao, dbErrorStackTrace);
					
				    }else {
				    	//write JSON error output
						errMsg = "ERROR on INSERT/UPDATE";
						status = "error";
						dbErrorStackTrace.append("request input parameters are invalid: <mandatory fields>");
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				    	
				    }
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
					
			}else {
				//write JSON error output
				errMsg = "ERROR on INSERT/UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <mandatory fields>");
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
	private ZadmoattfDaoServices zadmoattfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

