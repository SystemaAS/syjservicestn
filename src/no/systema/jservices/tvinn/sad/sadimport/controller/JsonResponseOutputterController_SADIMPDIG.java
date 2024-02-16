package no.systema.jservices.tvinn.sad.sadimport.controller;

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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.sadimport.controller.rules.SADIMPDIG_U;
import no.systema.jservices.tvinn.sad.sadimport.model.dao.entities.SadImpDigDao;
import no.systema.jservices.tvinn.sad.sadimport.model.dao.services.SadImpDigDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;






/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * It is used as a basis for statistics (Excel) for SADH and DIGITOLL tables (SADMOTF.SADMOMF,SADMOHF)
 * 
 * @author oscardelatorre
 * @date Feb 2024
 * 
 */

@Controller
public class JsonResponseOutputterController_SADIMPDIG {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADIMPDIG.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADH & SADMOTF-SADMOMF - SADMOHF
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADIMPDIG.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADIMPDIG.do?user=OSCAR...
	 * 
	 */
	@RequestMapping(value="syjsSADIMPDIG.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADIMPDIG");
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
				SadImpDigDao dao = new SadImpDigDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				logger.info("...bind params:" + dao.toString());
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.warn("Before SELECT ...");
	            if( dao.getSitdn()>0){
					logger.warn("inside: findById");
					list = this.sadImpDigDaoServices.findById(String.valueOf(dao.getSitdn()), dbErrorStackTrace);
				}else if( this.isDoFind(dao) ){
					logger.warn("inside: doFind");
					list = this.sadImpDigDaoServices.find(dao, dbErrorStackTrace);
				}else{
					logger.warn("inside: getList (all)");
					logger.warn("getList (all)");
					list = this.sadImpDigDaoServices.getList(dbErrorStackTrace);
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
	
	
	/**
	 * To find if a deeper search of a dataset is needed
	 * @param dao
	 * @return
	 */
	private boolean isDoFind(SadImpDigDao dao){
		boolean retval = false;
		
		if(dao.getSiavd()>0 ){
			retval = true;
		}
		if(dao.getSidt()>0 || StringUtils.isNotEmpty(dao.getSisg()) || StringUtils.isNotEmpty(dao.getSitle()) || StringUtils.isNotEmpty(dao.getSitll())  ){
			retval = true;
		}
		
		return retval;
	}
	
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadImpDigDaoServices sadImpDigDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

