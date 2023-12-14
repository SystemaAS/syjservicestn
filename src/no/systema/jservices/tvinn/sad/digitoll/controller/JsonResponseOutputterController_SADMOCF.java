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
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmocfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmocfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Dec 2023
 * 
 */

@Controller
public class JsonResponseOutputterController_SADMOCF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOCF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADMOHF
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADMOCF.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADMOCF.do?user=OSCAR&orgnr=...
	 * 
	 */
	@RequestMapping(value="syjsSADMOCF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOCF");
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
				SadmocfDao dao = new SadmocfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            logger.warn("orgnr:" + dao.getOrgnr());
	            logger.warn("name:" + dao.getName());
	            logger.warn("commtype:" + dao.getCommtype());
	            
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.warn("Before SELECT ...");
	            if(StringUtils.isNotEmpty(dao.getOrgnr()) ) {
					logger.warn("inside: findById (lineNr + lnr-keys...)");
					list = this.sadmocfDaoServices.findById(dao.getOrgnr(), dbErrorStackTrace);
				}else if( StringUtils.isNotEmpty(dao.getName()) || StringUtils.isNotEmpty(dao.getCommtype()) ){
					logger.warn("inside: find (dao)");
					list = this.sadmocfDaoServices.find(dao, dbErrorStackTrace);
				}else{
					logger.warn("inside: getList (all)");
					logger.warn("getList (all)");
					list = this.sadmocfDaoServices.getList(dbErrorStackTrace);
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
	
	/*
	@RequestMapping(value="syjsSADMOCF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOIF_U.do");
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
			SadmoifDao dao = new SadmoifDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("avd:" + dao.getEiavd().toString());
            logger.warn("pro:" + dao.getEipro().toString());
            logger.warn("eilnrt:" + dao.getEilnrt().toString());
            logger.warn("eilnrm:" + dao.getEilnrm().toString());
            logger.warn("eilnrh:" + dao.getEilnrh().toString());
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("eist:" + dao.getEist());
            
            
            
            
            //rules
            SADMOIF_U rulerLord = new SADMOIF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.sadmoifDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInputForUpdate(dao, userName, mode)){
						logger.warn("Before UPDATE ...");
						//do ADD
						if("A".equals(mode)){
							logger.info("MODE:" + mode + " " + " INSERT...");
							dmlRetval = this.sadmoifDaoServices.insert(dao, dbErrorStackTrace);
							
						}else if("U".equals(mode)){
							logger.info("MODE:" + mode + " " + " UPDATE...");
							dmlRetval = this.sadmoifDaoServices.update(dao, dbErrorStackTrace);
							 
						}
						
				  }else{
						//write JSON error output
						errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				  }
				}
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK INSERT/UPDATE
					if("A".equals(mode)){
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status, dao.getEilnrt(), dao.getEilnrm(), dao.getEilnrh(), dmlRetval ));
					}else {
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
					}
					
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
	*/
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadmocfDaoServices sadmocfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

