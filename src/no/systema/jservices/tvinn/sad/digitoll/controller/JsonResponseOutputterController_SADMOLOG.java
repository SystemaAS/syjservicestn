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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmologDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmologDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Aug 2023
 * 
 */

@Controller
public class JsonResponseOutputterController_SADMOLOG {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOLOG.class.getName());
	/**
	 * File: 	SADMOLOG
	 * Member: 	SADMOLOG Digitoll - SELECT SPECIFIC Error
	 * 
	 * @Example SELECT: http://gw.systema.no:8080/syjservicestn/syjsSADMOLOG.do?user=OSCAR&ellnrt=...
	 *
	 * 
	 *
	*/
	@RequestMapping(value="syjsSADMOLOG.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOLOG");
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
				SadmologDao dao = new SadmologDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            
				if( this.isDoFind(dao) ){
					logger.warn("find");
					list = this.sadmologDaoServices.find(dao, dbErrorStackTrace);
					
				}else{
					logger.warn("getList (all)");
					list = this.sadmologDaoServices.getList(dbErrorStackTrace);
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
	 * File: 	SADMOLOG
	 * Member: 	SADMOLOG Digitoll - INSERT SPECIFIC Error
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADMOLOG_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADMOLOG_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOLOG_U.do");
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
			SadmologDao dao = new SadmologDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("avd:" + dao.getElavd().toString());
            logger.warn("pro:" + dao.getElpro().toString());
            logger.warn("tdn:" + dao.getEltdn().toString());
            
            logger.warn("ellnrt:" + dao.getEllnrt().toString());
            logger.warn("ellnrm:" + dao.getEllnrm().toString());
            logger.warn("ellnrh:" + dao.getEllnrh().toString());
            
            logger.warn("elltxt:" + dao.getElltxt());
            
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            
            
            //rules
            //SADEXLOG_U rulerLord = new SADEXLOG_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				
				logger.warn("Before INSERT ...");
				List<SadmologDao> list = new ArrayList<SadmologDao>();
				
				//do ADD
				if("A".equals(mode)){
					dmlRetval = this.sadmologDaoServices.insert(dao, dbErrorStackTrace);
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
	
	private boolean isDoFind(SadmologDao dao){
		boolean retval = false;
		if(dao.getEllnrt()>0 || dao.getEllnrm()>0 || dao.getEllnrh()>0){
			retval = true;
		}//else if(dao.getEmdtr()>0 || dao.getEmetad()>0) {
		else if(dao.getEldate()>0 ) {
			retval = true;
		}
		
		return retval;
	}
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadmologDaoServices sadmologDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

