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
import no.systema.jservices.tvinn.sad.expressfortolling.controller.rules.SADEFCFR_U;
import no.systema.jservices.tvinn.sad.expressfortolling.controller.rules.SADEFFR_U;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadefcfDao;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.entities.SadeffDao;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services.SadefcfDaoServices;
import no.systema.jservices.tvinn.sad.expressfortolling.model.dao.services.SadeffDaoServices;
//import no.systema.jservices.tvinn.sad.z.maintenance.felles.controller.rules.SAD010R_U;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;


//rules





/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Aug 2020
 * 
 */

@Controller
public class JsonResponseOutputterController_SADEFCF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADEFCF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADEFCF
	 * 	 Member: 	SAD Ekpressfortolling Cargo lines - SELECT LIST or SELECT SPECIFIC Cargo line
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADEFCFR.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADEFCFR.do?user=OSCAR&clpro=501921
	 * 
	 */
	@RequestMapping(value="syjsSADEFCFR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		String pickFlag = request.getParameter("pick");
		
		try{
			logger.info("Inside syjsSADEFCFR");
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
				SadefcfDao dao = new SadefcfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( dao.getClpro()>0) {
	            	if( Math.abs(dao.getCltdn())>0) {
	            		logger.warn("find");
	            		list = this.sadefcfDaoServices.find(dao, dbErrorStackTrace);
	            		
					}else{
						logger.warn("findById");
						list = this.sadefcfDaoServices.findById(String.valueOf(dao.getClpro()), dbErrorStackTrace);
					}
				
				}else{ 
					if(StringUtils.isNotEmpty(pickFlag)){
						//in order to get all orphan records, those without a tur (clpro). This case is used in order to pick a record
						//to a parent tur and eliminate the orphan state
						logger.warn("pick special case...");
	            		list = this.sadefcfDaoServices.pick(dao, dbErrorStackTrace);
					}else{
						logger.warn("getList (all)");
						list = this.sadefcfDaoServices.getList(dbErrorStackTrace);
					}
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
		return sb.toString();
	}
	/**
	 * To find if a deeper search of a dataset is needed
	 * @param dao
	 * @return
	 */
	private boolean isDoFind(SadeffDao dao){
		boolean retval = false;
		if(dao.getEfavd()>0 || dao.getEfpro()>0 || 
			StringUtils.isNotEmpty(dao.getEfsg())){
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADEFCF
	 * Member: 	SADEFCF Ekspressfortolling - UPDATE SPECIFIC cargo line
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADEFCFR_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADEFCFR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSADEFCFR_U.do");
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
			SadefcfDao dao = new SadefcfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SADEFCFR_U rulerLord = new SADEFCFR_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.sadefcfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
					logger.info("Before UPDATE ...");
					List<SadeffDao> list = new ArrayList<SadeffDao>();
					if(rulerLord.isValidInput(dao, userName, mode)){
						//do ADD
						if("A".equals(mode)){
							logger.warn("Before INSERT...");
							//cltdn will be created in the insert by a counter (TELLGE db-table)
							if(dao.getCltdn() == 0){
								logger.warn("doInsert now...");
								dmlRetval = this.sadefcfDaoServices.insert(dao, dbErrorStackTrace);
							}
						}else{
							//Update
							if("U".equals(mode)){
								logger.warn("Before UPDATE ...");
								dmlRetval = this.sadefcfDaoServices.update(dao, dbErrorStackTrace);
							//R=Release	
							}else if("R".equals(mode)){
								logger.warn("Before RELEASE ...");
								dmlRetval = this.sadefcfDaoServices.release(dao, dbErrorStackTrace);
							//B=Bind
							}else if("B".equals(mode)){
								logger.warn("Before BIND ...");
								dmlRetval = this.sadefcfDaoServices.bindTur(dao, dbErrorStackTrace);
							}
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
		return sb.toString();
	}
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private SadefcfDaoServices sadefcfDaoServices;
	public void setSadefcfDaoServices (SadefcfDaoServices value){ this.sadefcfDaoServices = value; }
	public SadefcfDaoServices getSadefcfDaoServices(){ return this.sadefcfDaoServices; }
	
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

