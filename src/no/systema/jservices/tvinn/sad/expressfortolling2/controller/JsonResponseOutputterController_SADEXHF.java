package no.systema.jservices.tvinn.sad.expressfortolling2.controller;

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
import no.systema.jservices.tvinn.sad.expressfortolling2.controller.rules.SADEXHF_U;
//import no.systema.jservices.tvinn.sad.expressfortolling2.controller.rules.SADEFFR_U;
import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.entities.SadexhfDao;
import no.systema.jservices.tvinn.sad.expressfortolling2.model.dao.services.SadexhfDaoServices;
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
 * @date Aug 2022
 * 
 */

@Controller
public class JsonResponseOutputterController_SADEXHF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADEXHF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADEXHF
	 * 	 Member: 	SAD Ekpressfortolling - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADEXHF.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADEXHF.do?user=OSCAR&emmid=whatever...
	 * 
	 */
	@RequestMapping(value="syjsSADEXHF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADEXHF");
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
				SadexhfDao dao = new SadexhfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getEhmid()) ){
					logger.warn("findById");
					list = this.sadexhfDaoServices.findById(dao.getEhmid(), dbErrorStackTrace);
					
				}else if( this.isDoFind(dao) ){
					logger.warn("find");
					list = this.sadexhfDaoServices.find(dao, dbErrorStackTrace);
					
				}else{
					logger.warn("getList (all)");
					list = this.sadexhfDaoServices.getList(dbErrorStackTrace);
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
	private boolean isDoFind(SadexhfDao dao){
		boolean retval = false;
		if(dao.getEhavd()>0 || dao.getEhpro()>0 || dao.getEhtdn()>0 || StringUtils.isNotEmpty(dao.getEhst())){
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADEXHF
	 * Member: 	SADEXHF Ekspressfortolling - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADEXHF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADEXHF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSADEXHF_U.do");
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
			SadexhfDao dao = new SadexhfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("avd:" + dao.getEhavd().toString());
            logger.warn("pro:" + dao.getEhpro().toString());
            logger.warn("tdn:" + dao.getEhtdn().toString());
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            
            //rules
            SADEXHF_U rulerLord = new SADEXHF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						//dmlRetval = this.sadexhfDaoServices.updateManifestStatus(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInputForUpdate(dao, userName, mode)){
						logger.info("Before UPDATE ...");
						List<SadexhfDao> list = new ArrayList<SadexhfDao>();
						
						//do ADD
						if("A".equals(mode)){
							/*list = this.sadeffDaoServices.findById(dao.getEfuuid(), dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Code exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								dmlRetval = this.sadeffDaoServices.insert(dao, dbErrorStackTrace);
							}*/
						}else if("ULM".equals(mode)){
							if(rulerLord.isValidInputForUpdateLrnMrn(dao, userName, mode)){
								dmlRetval = this.sadexhfDaoServices.updateLrnMrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN: invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						}else if("UL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrn(dao, userName, mode)){
								dmlRetval = this.sadexhfDaoServices.updateLrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN: invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						}else if("DL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								//Delete light means updating the record with blanks ehuuid and ehmid. The record will exists but without any id.
								dmlRetval = this.sadexhfDaoServices.deleteLight(dao, dbErrorStackTrace);
								
							}else {
								//write JSON error output
								errMsg = "ERROR on DELETE-LIGHT invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
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
	private SadexhfDaoServices sadexhfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

