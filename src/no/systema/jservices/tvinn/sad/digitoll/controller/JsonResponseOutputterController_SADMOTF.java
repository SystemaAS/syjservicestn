package no.systema.jservices.tvinn.sad.digitoll.controller;

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
import no.systema.jservices.tvinn.sad.digitoll.controller.rules.SADMOTF_U;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmotfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmotfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;
import no.systema.main.util.DiacriticalCharacterManager;


//rules





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
public class JsonResponseOutputterController_SADMOTF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOTF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADMOTF
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADMOTF.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADMOTF.do?user=OSCAR&etmid=whatever...
	 * 
	 */
	@RequestMapping(value="syjsSADMOTF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOTF");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String house_opd = request.getParameter("opd");//special case for deep search
			String house_extref = request.getParameter("extref");//special case for deep search
			String master_id = request.getParameter("masterid");//special case for master id search
			
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
				SadmotfDao dao = new SadmotfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				logger.info("...bind params:" + dao.toString());
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            if(!isSpecialCaseDeepSearch(house_opd, house_extref, master_id)) {
		            logger.warn("Before SELECT ...");
		            if( StringUtils.isNotEmpty(dao.getEtmid()) ){
						logger.warn("inside: findById");
						list = this.sadmotfDaoServices.findById(dao.getEtmid(), dbErrorStackTrace);
					}else if( this.isDoFind(dao) ){
						logger.warn("inside: doFind");
						list = this.sadmotfDaoServices.find(dao, dbErrorStackTrace);
					}else if( this.isDoFindByLrn(dao) ){
						logger.warn("inside: doFindByLrn");
						list = this.sadmotfDaoServices.findByLrn(dao.getEtuuid(), dbErrorStackTrace);
					}else{
						logger.warn("inside: getList (all)");
						logger.warn("getList (all)");
						list = this.sadmotfDaoServices.getList(dbErrorStackTrace);
					}
	            }else {
	            	
	            	if(StringUtils.isNotEmpty(master_id)) {
	            		//special case for deep search towards opd (houses ehtdn) from a transport search
	            		logger.warn("inside: doFindMasterId");
	            		list = this.sadmotfDaoServices.findMasterId(master_id, dao, dbErrorStackTrace);
	            	
	            	}else if(StringUtils.isNotEmpty(house_opd)) {
	            		//special case for deep search towards opd (houses ehtdn) from a transport search
	            		logger.warn("inside: doFindHouseOpd");
	            		list = this.sadmotfDaoServices.findHouseOpd(house_opd, dao, dbErrorStackTrace);
	            	
	            	}else if(StringUtils.isNotEmpty(house_extref)) {
	            		//special case for deep search towards opd (houses ehtdn) from a transport search
	            		logger.warn("inside: doFindHouseExtRef");
	            		list = this.sadmotfDaoServices.findHouseExtRef(house_extref, dao, dbErrorStackTrace);
	            	}
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
	
	private boolean isSpecialCaseDeepSearch(String house_opd, String house_extref, String master_id) {
		boolean retval = false;
		if(StringUtils.isNotEmpty(house_opd)){
			retval = true;
		}
		if(StringUtils.isNotEmpty(house_extref)){
			retval = true;
		}
		if(StringUtils.isNotEmpty(master_id)){
			retval = true;
		}
		
		return retval;
	}
	/**
	 * To find if a deeper search of a dataset is needed
	 * @param dao
	 * @return
	 */
	private boolean isDoFind(SadmotfDao dao){
		boolean retval = false;
		
		if(dao.getEtlnrt()>0 || StringUtils.isNotEmpty(dao.getEtsg()) || StringUtils.isNotEmpty(dao.getEtst()) || StringUtils.isNotEmpty(dao.getEtst2())
				|| StringUtils.isNotEmpty(dao.getEtkmrk()) ){
			retval = true;
		}else if(dao.getEtdtr()>0 || dao.getEtetad()>0) {
			retval = true;
		//removed to permit -1 or 0 in etpro --->}else if(dao.getEtavd()>0 || dao.getEtpro()>0) { //usually from web-GUI
		}else if(dao.getEtavd()>0 || dao.getEtpro()>0 || dao.getEtpro()<0 ) { //usually from web-GUI (minus is for external houses
			retval = true;
		//for multiple status from GUI	
		}else if(StringUtils.isNotEmpty(dao.getCb_C()) || StringUtils.isNotEmpty(dao.getCb_N()) || StringUtils.isNotEmpty(dao.getCb_M()) 
				|| StringUtils.isNotEmpty(dao.getCb_D()) || StringUtils.isNotEmpty(dao.getCb_S()) || StringUtils.isNotEmpty(dao.getCb_EMPTY())
				|| StringUtils.isNotEmpty(dao.getCb_Z())) { //usually from web-GUI
			retval = true;
		}
		
		
		return retval;
	}
	private boolean isDoFindByLrn(SadmotfDao dao){
		boolean retval = false;
		if(StringUtils.isNotEmpty(dao.getEtuuid())){
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADMOTF
	 * Member: 	SADMOTF Ekspressfortolling - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADMOTF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADMOTF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOTF_U.do");
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
			SadmotfDao dao = new SadmotfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("etlnrt:" + dao.getEtlnrt().toString());
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("etst:" + dao.getEtst());
            logger.warn("etst2:" + dao.getEtst2());
            logger.warn("etst3:" + dao.getEtst3());
            logger.warn("etuuid:" + dao.getEtuuid());
            logger.warn("etmid:" + dao.getEtmid());
            logger.warn("etsjaf:" + dao.getEtsjaf());
            
            //clean name
            String washedDriversName = DiacriticalCharacterManager.stripDiacriticsKeepNordicLetters(dao.getEtsjaf());
            dao.setEtsjaf(washedDriversName);
            
            
            //rules
            SADMOTF_U rulerLord = new SADMOTF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.sadmotfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInputForUpdate(dao, userName, mode)){
						logger.warn("Before UPDATE ...");
						List<SadmotfDao> list = new ArrayList<SadmotfDao>();
						
						//do ADD
						if("A".equals(mode)){
							if(dao.getEtlnrt()>0) { //if this happens then there is something wrong since this will be an UPDATE ... (just in case)
								list = this.sadmotfDaoServices.find(dao, dbErrorStackTrace);
							}
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Code exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("MODE:" + mode + " " + " INSERT...");
								dmlRetval = this.sadmotfDaoServices.insert(dao, dbErrorStackTrace);
							}
						
						}else if("U".equals(mode)){
							logger.debug("########_AAA:" + dao.toString());
							logger.info("MODE:" + mode + " " + " UPDATE...");
							dmlRetval = this.sadmotfDaoServices.update(dao, dbErrorStackTrace);
							
						
						}else if("ULM".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrnMrn(dao, userName, mode)){
								logger.info(mode + " " + " UPDATE LRN/MRN...");
								dmlRetval = this.sadmotfDaoServices.updateLrnMrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN/MRN: invalid (rulerLord)?  Try to check: <DaoServices>.updateLrnMrn";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						
						}else if("UL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrn(dao, userName, mode)){
								logger.info(mode + " " + " UPDATE LRN...");
								dmlRetval = this.sadmotfDaoServices.updateLrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN: invalid (rulerLord)?  Try to check: <DaoServices>.updateLrn";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("DL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								logger.info(mode + " " + " DELETE LIGHT...");
								//Delete light means updating the record with blanks emuuid and emmid. The record will exists but without any id.
								dmlRetval = this.sadmotfDaoServices.deleteLight(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on DELETE-LIGHT invalid (rulerLord)?  Try to check: <DaoServices>.deleteLight";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						}else if("DC".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								logger.info(mode + " " + " DELETE CONSOLIDATED...");
								//Delete consolidated. Only when all digitoll-levels have been created automatically from the transpor-system integration
								dmlRetval = this.sadmotfDaoServices.deleteConsolidated(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on DELETE-LIGHT invalid (rulerLord)?  Try to check: <DaoServices>.deleteLight";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						}else if("US".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								//Delete light means updating the record with blanks emuuid and emmid. The record will exists but without any id.
								dmlRetval = this.sadmotfDaoServices.updateStatus(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("US2".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								dmlRetval = this.sadmotfDaoServices.updateStatus2(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status2 invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("US3".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								dmlRetval = this.sadmotfDaoServices.updateStatus3(dao, dbErrorStackTrace);
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
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status, dmlRetval));
					}else {
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
					}
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
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="syjsSADMOTF_U_BUP.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_BUP( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOTF_U_BUP.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SadmotfDao dao = new SadmotfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("etlnrt:" + dao.getEtlnrt().toString());
            logger.warn("etmid:" + dao.getEtmid());
            logger.warn("etuuid:" + dao.getEtuuid());
            
            //rules
            SADMOTF_U rulerLord = new SADMOTF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				  if(StringUtils.isNotEmpty(dao.getEtmid())) {
					  if(rulerLord.isValidInputForUpdateMrnBup(dao, userName)){
							logger.warn("Before UPDATE MRN-BUP ...");
							dmlRetval = this.sadmotfDaoServices.setMrnBup(dao, dbErrorStackTrace);
					  }else{
							//write JSON error output
							errMsg = "ERROR on UPDATE MRN-BUP: invalid (rulerLord)?  Try to check: <DaoServices>.update";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					  }
				  }else  {
					  if(rulerLord.isValidInputForUpdateRequestIdBup(dao, userName)){
							logger.warn("Before UPDATE RequestId-BUP ...");
							dmlRetval = this.sadmotfDaoServices.setRequestIdBup(dao, dbErrorStackTrace);
					  }else{
							//write JSON error output
							errMsg = "ERROR on UPDATE RequestId-BUP: invalid (rulerLord)?  Try to check: <DaoServices>.update";
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
					//OK
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
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="syjsSADMOTF_U_AUTOGEN_CHIL.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_AUTOGEN_CHIL( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOTF_U_AUTOGEN_CHIL.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SadmotfDao dao = new SadmotfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("etlnrt:" + dao.getEtlnrt().toString());
            logger.warn("etsg:" + dao.getEtsg());
            logger.warn("etst2:" + dao.getEtst2());
            
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				  if(dao.getEtlnrt()>0 && "Z".equals(dao.getEtst2())) {
						logger.warn("Before UPDATE AUTO-GEN Children ...");
						dmlRetval = this.sadmotfDaoServices.updateAutoGenChildren(dao, dbErrorStackTrace);
				 
				  }else  {
						//write JSON error output
						errMsg = "ERROR on UPDATE AUTO-GEN Children: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					  
				  }
				
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on UPDATE AUTO-GEN Children: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK
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
	
	/**
	 * Updates road entry when the lorry is beyond the boarder 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="syjsSADMOTF_U_RoadEntry.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_RoadEntry( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOTF_U_RoadEntry.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SadmotfDao dao = new SadmotfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("etmid:" + dao.getEtmid());
            logger.warn("etlnrt:" + dao.getEtlnrt());
            
            logger.warn("etentdval:" + dao.getEtentval());
            logger.warn("etentoff:" + dao.getEtentoff());
            logger.warn("etenttim:" + dao.getEtenttim());
            
            
            //rules
            SADMOTF_U rulerLord = new SADMOTF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if(rulerLord.isValidInputForEntryComplete(dao, userName)){
					logger.warn("Before UPDATE MRN-ENTRY ...");
					dmlRetval = this.sadmotfDaoServices.updateStatus2ForEntry(dao, dbErrorStackTrace);
				}else{
					//write JSON error output
					errMsg = "ERROR on UPDATE MRN-ENTRY: invalid (rulerLord)?  Try to check: <DaoServices>.update";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
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
					//OK
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
	private SadmotfDaoServices sadmotfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

