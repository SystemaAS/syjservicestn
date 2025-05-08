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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmoafDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmoafDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Oct 2023
 * 
 */

@Controller
public class JsonResponseOutputterController_SADMOAF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOAF.class.getName());
	/**
	 * File: 	SADMOAF
	 * Member: 	SADMOAF Digitoll - SELECT SPECIFIC Error
	 * 
	 * @Example SELECT: http://gw.systema.no:8080/syjservicestn/syjsSADMOAF.do?user=OSCAR...
	 *
	 * 
	 *
	*/
	@RequestMapping(value="syjsSADMOAF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOAF");
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
				SadmoafDao dao = new SadmoafDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            
				if(dao.getEtavd()!=null && dao.getEtavd() > -1) {
					logger.warn("findById");
					list = this.sadmoafDaoServices.findById(String.valueOf(dao.getEtavd()), dbErrorStackTrace);
					
				}else {
					logger.warn("getList (all)");
					list = this.sadmoafDaoServices.getList(dbErrorStackTrace);
				}

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
		//logger.debug(sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADMOAF
	 * Member: 	SADMOAF Digitoll - INSERT SPECIFIC Error
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADMOAF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADMOAF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOAF_U.do");
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
			SadmoafDao dao = new SadmoafDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("etavd:" + dao.getEtavd());
            
            
            //rules
            //SADEXLOG_U rulerLord = new SADEXLOG_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				
				List<SadmoafDao> list = new ArrayList<SadmoafDao>();
				
				//do ADD
				if("A".equals(mode)){
					logger.warn("Before INSERT ...");
					String avdId = String.valueOf(dao.getEtavd());
					List tmpList= this.sadmoafDaoServices.findById(avdId, dbErrorStackTrace);
					if(tmpList!=null && tmpList.size()>0) {
						//nothing since this should have been and UPDATE on current avd. The caller is sending wrong mode=A
					}else {
						dmlRetval = this.sadmoafDaoServices.insert(dao, dbErrorStackTrace);
					}
					
				}else if("U".equals(mode)){
					logger.warn("Before UPDATE ...");
					dmlRetval = this.sadmoafDaoServices.update(dao, dbErrorStackTrace);
					
				}else if("D".equals(mode)){
					
					logger.warn("Before DELETE ...");
					dmlRetval = this.sadmoafDaoServices.delete(dao, dbErrorStackTrace);
					
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
	private SadmoafDaoServices sadmoafDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

