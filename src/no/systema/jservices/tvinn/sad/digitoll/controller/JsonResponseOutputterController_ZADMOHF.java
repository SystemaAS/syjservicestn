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
import no.systema.jservices.tvinn.sad.digitoll.controller.rules.ZADMOHF_U;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.ZadmohfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.ZadmohfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Feb 2024
 * 
 */

@Controller
public class JsonResponseOutputterController_ZADMOHF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_ZADMOHF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		ZADMOHF
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC for External Houses (främmande houses)
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsZadmohf.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsZadmohf.do?user=OSCAR&ehlnrh=3...
	 * 
	 */
	@RequestMapping(value="syjsZADMOHF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsZADMOHF");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			logger.warn("User:" + user);
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				ZadmohfDao dao = new ZadmohfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.warn("Before SELECT ...");
	            /*if( StringUtils.isNotEmpty(dao.getEhdkm_fh()) && StringUtils.isNotEmpty(dao.getEhdkh()) ){
					logger.warn("inside: findById");
					list = this.zadmohfDaoServices.findById(dao.getEhmid(), dbErrorStackTrace);
				}else */ 
				
				if( this.isDoFind(dao) ){
					logger.warn("inside: doFind");
					list = this.zadmohfDaoServices.find(dao, dbErrorStackTrace);
				}else{
					logger.warn("inside: getList (all)");
					logger.warn("getList (all)");
					list = this.zadmohfDaoServices.getList(dbErrorStackTrace);
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
	private boolean isDoFind(ZadmohfDao dao){
		boolean retval = false;
		if( StringUtils.isNotEmpty(dao.getEhdkm_fh()) || StringUtils.isNotEmpty(dao.getEhdkh()) ){
			retval = true;
		}//else if(dao.getEmdtr()>0 || dao.getEmetad()>0) {
		else if(dao.getEhdts()>0 ) {
			retval = true;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * Update Database DML operations
	 * File: 	ZADMOHF
	 * Member: 	ZADMOHF Ekspressfortolling - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsZADMOHF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsZADMOHF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsZADMOHF_U.do");
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
			ZadmohfDao dao = new ZadmohfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("ehdkm_fh:" + dao.getEhdkm_fh());
            logger.warn("ehdkh:" + dao.getEhdkh());
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("ehst:" + dao.getEhst());
            logger.warn("ehst2:" + dao.getEhst2());
            logger.warn(dao.toString());
            
            //rules
            ZADMOHF_U rulerLord = new ZADMOHF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						//Delete light means updating the record with blanks emuuid and emmid. The record will exists but without any id.
						dmlRetval = this.zadmohfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInputForUpdate(dao, userName, mode)){
						logger.warn("Before UPDATE ...");
						List<ZadmohfDao> list = new ArrayList<ZadmohfDao>();
						
						//do ADD
						if("A".equals(mode)){
							logger.warn("Before INSERT ...");
							list = this.zadmohfDaoServices.find(dao, dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								logger.info("UPDATE instead of INSERT ..." );
								dmlRetval = this.zadmohfDaoServices.update(dao, dbErrorStackTrace);
								
							}else{
								logger.info("MODE:" + mode + " " + " INSERT...");
								dmlRetval = this.zadmohfDaoServices.insert(dao, dbErrorStackTrace);
							}
						}else if("U".equals(mode)){
							logger.debug("########_dao-update:" + dao.toString());
							logger.info("MODE:" + mode + " " + " UPDATE...");
							dmlRetval = this.zadmohfDaoServices.update(dao, dbErrorStackTrace);
							 
						}else if("US".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								dmlRetval = this.zadmohfDaoServices.updateStatus(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("US2".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								dmlRetval = this.zadmohfDaoServices.updateStatus2(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("US3".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								dmlRetval = this.zadmohfDaoServices.updateStatus3(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status3 invalid (rulerLord)?  Try to check: <DaoServices>.update";
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
					//OK INSERT/UPDATE
					if("A".equals(mode)){
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status, dao.getEhlnrt(), dao.getEhlnrm(), dmlRetval ));
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
	
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Autowired
	private ZadmohfDaoServices zadmohfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

