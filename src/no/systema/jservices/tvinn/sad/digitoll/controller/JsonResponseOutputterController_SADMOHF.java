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
import no.systema.jservices.tvinn.sad.digitoll.controller.rules.SADMOHF_U;
import no.systema.jservices.tvinn.sad.digitoll.controller.rules.SADMOMF_U;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmohfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmohfDaoServices;
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
public class JsonResponseOutputterController_SADMOHF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOHF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADMOHF
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADMOHF.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADMOHF.do?user=OSCAR&ehlnrh=3...
	 * 
	 */
	@RequestMapping(value="syjsSADMOHF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOHF");
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
				SadmohfDao dao = new SadmohfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.warn("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getEhmid()) ){
					logger.warn("inside: findById");
					list = this.sadmohfDaoServices.findById(dao.getEhmid(), dbErrorStackTrace);
				}else if( this.isDoFind(dao) ){
					logger.warn("inside: doFind");
					list = this.sadmohfDaoServices.find(dao, dbErrorStackTrace);
				}else if( this.isDoFindByLrn(dao) ){
					logger.warn("inside: doFindByLrn");
					list = this.sadmohfDaoServices.findByLrn(dao.getEhuuid(), dbErrorStackTrace);
				}else{
					logger.warn("inside: getList (all)");
					logger.warn("getList (all)");
					list = this.sadmohfDaoServices.getList(dbErrorStackTrace);
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
	private boolean isDoFind(SadmohfDao dao){
		boolean retval = false;
		if(dao.getEhlnrt()>0 || dao.getEhlnrm()>0 || dao.getEhlnrh()>0 ){
			retval = true;
		}//else if(dao.getEmdtr()>0 || dao.getEmetad()>0) {
		else if(dao.getEhdts()>0 ) {
			retval = true;
		}
		
		return retval;
	}
	private boolean isDoFindByLrn(SadmohfDao dao){
		boolean retval = false;
		if(StringUtils.isNotEmpty(dao.getEhuuid())){
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADMOHF
	 * Member: 	SADMOHF Ekspressfortolling - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADMOHF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADMOHF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOHF_U.do");
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
			SadmohfDao dao = new SadmohfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("avd:" + dao.getEhavd().toString());
            logger.warn("pro:" + dao.getEhpro().toString());
            logger.warn("ehlnrt:" + dao.getEhlnrt().toString());
            logger.warn("ehlnrm:" + dao.getEhlnrm().toString());
            logger.warn("ehlnrh:" + dao.getEhlnrh().toString());
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("ehst:" + dao.getEhst());
            logger.warn("ehst2:" + dao.getEhst2());
            logger.warn("ehuuid:" + dao.getEhuuid());
            logger.warn("ehmid:" + dao.getEhmid());
            
            
            
            //rules
            SADMOHF_U rulerLord = new SADMOHF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						//dmlRetval = this.sadexmfDaoServices.updateManifestStatus(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInputForUpdate(dao, userName, mode)){
						logger.warn("Before UPDATE ...");
						List<SadmohfDao> list = new ArrayList<SadmohfDao>();
						
						//do ADD
						if("A".equals(mode)){
							logger.warn("Before INSERT ...");
							if(dao.getEhlnrh()>0) { //if this happens then there is something wrong since this will be an UPDATE ... (just in case)
								list = this.sadmohfDaoServices.find(dao, dbErrorStackTrace);
							}
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Code exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("MODE:" + mode + " " + " INSERT...");
								dmlRetval = this.sadmohfDaoServices.insert(dao, dbErrorStackTrace);
							}
						}else if("U".equals(mode)){
							logger.debug("########_AAA:" + dao.toString());
							logger.info("MODE:" + mode + " " + " UPDATE...");
							dmlRetval = this.sadmohfDaoServices.update(dao, dbErrorStackTrace);
							
						
						}else if("ULM".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrnMrn(dao, userName, mode)){
								dmlRetval = this.sadmohfDaoServices.updateLrnMrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN/MRN: invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						}else if("UL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrn(dao, userName, mode)){
								dmlRetval = this.sadmohfDaoServices.updateLrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN: invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("DL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								//Delete light means updating the record with blanks emuuid and emmid. The record will exists but without any id.
								dmlRetval = this.sadmohfDaoServices.deleteLight(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on DELETE-LIGHT invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("US".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								//Delete light means updating the record with blanks emuuid and emmid. The record will exists but without any id.
								dmlRetval = this.sadmohfDaoServices.updateStatus(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
							 
						}else if("US2".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForDelete(dao, userName, mode)){
								//Delete light means updating the record with blanks emuuid and emmid. The record will exists but without any id.
								dmlRetval = this.sadmohfDaoServices.updateStatus2(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on Update Status invalid (rulerLord)?  Try to check: <DaoServices>.update";
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
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="syjsSADMOHF_U_BUP.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_BUP( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOHF_U_BUP.do");
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
			SadmohfDao dao = new SadmohfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("ehlnrt:" + dao.getEhlnrt().toString());
            logger.warn("ehlnrm:" + dao.getEhlnrm().toString());
            logger.warn("ehlnrh:" + dao.getEhlnrh().toString());
            logger.warn("ehmid:" + dao.getEhmid());
            
            //rules
            SADMOHF_U rulerLord = new SADMOHF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				
				  if(rulerLord.isValidInputForUpdateMrnBup(dao, userName)){
						logger.warn("Before UPDATE MRN-BUP ...");
						dmlRetval = this.sadmohfDaoServices.setMrnBup(dao, dbErrorStackTrace);
				  }else{
						//write JSON error output
						errMsg = "ERROR on UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
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
	private SadmohfDaoServices sadmohfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

