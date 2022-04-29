package no.systema.jservices.tvinn.sad.expressfortolling.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
 
import org.slf4j.*;
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

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.expressfortolling.controller.rules.SADEFCMF_U;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefcmfDao;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services.SadefcmfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;


//rules





/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Apr 2022
 * 
 */

@Controller
public class JsonResponseOutputterController_SADEFCMF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADEFCMF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADEFCMF
	 * 	 Member: 	SAD Ekpressfortolling Flera EksportIds from SE - SELECT LIST or SELECT SPECIFIC Ekspid line from a Cargo line
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADEFCMF.do?user=OSCAR&cmavd=1&cmtdn=501921
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADEFCMF.do?user=OSCAR&cmavd=1&cmtdn=501921&cmeid=SE9827934827ETCETC
	 * 
	 */
	@RequestMapping(value="syjsSADEFCMF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		String pickFlag = request.getParameter("pick");
		
		try{
			logger.info("Inside syjsSADEFCMF");
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
				SadefcmfDao dao = new SadefcmfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( dao.getCmavd()>0 && Math.abs(dao.getCmtdn())>0) {
	            	
            		logger.warn("find");
            		list = this.sadefcmfDaoServices.find(dao, dbErrorStackTrace);
            		//in case we are picking
            		if(StringUtils.isNotEmpty(pickFlag)){
            			list = this.sadefcmfDaoServices.pick(dao, dbErrorStackTrace);
            		}
	            	//process result
					if (list==null){ list = new ArrayList(); }
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
					
				}else {
					//write JSON error output
					errMsg = "ERROR on SELECT";
					status = "error";
					dbErrorStackTrace.append("request input parameters are invalid or missing: <cmavd, cmtdn> ?");
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
	
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADEFCMF
	 * Member: 	SADEFCMF Ekspressfortolling - UPDATE SPECIFIC exp.id in a specific cargo line
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADEFCMF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADEFCMF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADEFCMF_U.do");
			logger.warn("cmtdn:" + request.getParameter("cmtdn"));
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
			SadefcmfDao dao = new SadefcmfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn(dao.toString());
            //rules
            SADEFCMF_U rulerLord = new SADEFCMF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.sadefcmfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
					logger.warn("Before UPDATE ...");
					List<SadefcmfDao> list = new ArrayList<SadefcmfDao>();
					if(rulerLord.isValidInput(dao, userName, mode)){
						logger.warn("ok rulerLord");
						//do ADD
						if("A".equals(mode)){
							logger.warn("Before INSERT...");
							//cltdn will be created in the insert by a counter (TELLGE db-table)
							logger.warn("doInsert now...");
							dmlRetval = this.sadefcmfDaoServices.insert(dao, dbErrorStackTrace);
							
						}else if("D".equals(mode)){
							//Delete mode=D
							logger.warn("Before DELETE ...");
							dmlRetval = this.sadefcmfDaoServices.delete(dao, dbErrorStackTrace);							
						}else {
							//Update mode=U
							logger.warn("Before UPDATE ...");
							dmlRetval = this.sadefcmfDaoServices.update(dao, dbErrorStackTrace);
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
		logger.warn(sb.toString());
		return sb.toString();
	}
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadefcmfDaoServices sadefcmfDaoServices;
	public void setSadefcmfDaoServices (SadefcmfDaoServices value){ this.sadefcmfDaoServices = value; }
	public SadefcmfDaoServices getSadefcfDaoServices(){ return this.sadefcmfDaoServices; }
	
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

