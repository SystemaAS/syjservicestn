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
import no.systema.jservices.tvinn.sad.digitoll.controller.rules.SADMOMF_U;
import no.systema.jservices.tvinn.sad.digitoll.controller.rules.SADMOTF_U;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmomfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmotfDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.services.SadmomfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.jsonwriter.JsonTvinnMaintFellesResponseWriter;


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
public class JsonResponseOutputterController_SADMOMF {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController_SADMOMF.class.getName());
	/**
	 * FreeForm Source:
	 * 	 File: 		SADMOMF
	 * 	 Member: 	SAD DIGITOLL - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSADMOMF.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSADMOMF.do?user=OSCAR&etmid=whatever...
	 * 
	 */
	@RequestMapping(value="syjsSADMOMF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOMF");
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
				SadmomfDao dao = new SadmomfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.warn("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getEmmid()) ){
					logger.warn("inside: findById");
					list = this.sadmomfDaoServices.findById(dao.getEmmid(), dbErrorStackTrace);
				}else if( this.isDoFind(dao) ){
					logger.warn("inside: doFind");
					list = this.sadmomfDaoServices.find(dao, dbErrorStackTrace);
				}else if( this.isDoFindByLrn(dao) ){
					logger.warn("inside: doFindByLrn");
					list = this.sadmomfDaoServices.findByLrn(dao.getEmuuid(), dbErrorStackTrace);
				}else{
					logger.warn("inside: getList (all)");
					logger.warn("getList (all)");
					list = this.sadmomfDaoServices.getList(dbErrorStackTrace);
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
	private boolean isDoFind(SadmomfDao dao){
		boolean retval = false;
		if(dao.getEmlnrt()>0 || dao.getEmlnrm()>0 || StringUtils.isNotEmpty(dao.getEmsg())){
			retval = true;
		}//else if(dao.getEmdtr()>0 || dao.getEmetad()>0) {
		else if(dao.getEmdtr()>0 ) {
			retval = true;
		}
		
		return retval;
	}
	private boolean isDoFindByLrn(SadmomfDao dao){
		boolean retval = false;
		if(StringUtils.isNotEmpty(dao.getEmuuid())){
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * Update Database DML operations
	 * File: 	SADMOMF
	 * Member: 	SADMOMF Ekspressfortolling - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSADMOMF_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSADMOMF_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOMF_U.do");
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
			SadmomfDao dao = new SadmomfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("emlnrt:" + dao.getEmlnrt().toString());
            logger.warn("emlnrm:" + dao.getEmlnrm().toString());
            
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("emst:" + dao.getEmst());
            logger.warn("emst2:" + dao.getEmst2());
            logger.warn("etuuid:" + dao.getEmuuid());
            logger.warn("etmid:" + dao.getEmmid());
            
            
            
            //rules
            SADMOMF_U rulerLord = new SADMOMF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.sadmomfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInputForUpdate(dao, userName, mode)){
						logger.warn("Before UPDATE ...");
						List<SadmomfDao> list = new ArrayList<SadmomfDao>();
						
						//do ADD
						if("A".equals(mode)){
							if(dao.getEmlnrm()>0) { //if this happens then there is something wrong since this will be an UPDATE ... (just in case)
								list = this.sadmomfDaoServices.find(dao, dbErrorStackTrace);
							}
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Code exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("MODE:" + mode + " " + " INSERT...");
								dmlRetval = this.sadmomfDaoServices.insert(dao, dbErrorStackTrace);
							}
						}else if("U".equals(mode)){
							logger.debug("########_AAA:" + dao.toString());
							logger.info("MODE:" + mode + " " + " UPDATE...");
							dmlRetval = this.sadmomfDaoServices.update(dao, dbErrorStackTrace);
							
						
						}else if("ULM".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrnMrn(dao, userName, mode)){
								dmlRetval = this.sadmomfDaoServices.updateLrnMrn(dao, dbErrorStackTrace);
							}else {
								//write JSON error output
								errMsg = "ERROR on UPDATE LRN/MRN: invalid (rulerLord)?  Try to check: <DaoServices>.update";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}
						}else if("UL".equals(mode)){
							logger.warn("MODE:" + mode);
							if(rulerLord.isValidInputForUpdateLrn(dao, userName, mode)){
								dmlRetval = this.sadmomfDaoServices.updateLrn(dao, dbErrorStackTrace);
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
								dmlRetval = this.sadmomfDaoServices.deleteLight(dao, dbErrorStackTrace);
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
								dmlRetval = this.sadmomfDaoServices.updateStatus(dao, dbErrorStackTrace);
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
						sb.append(jsonWriter.setJsonSimpleValidResult(userName, status, dao.getEmlnrt(), dmlRetval ));
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
	 * Change transport 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="syjsSADMOMF_U_CT.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_CT( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOMF_U.do");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			String toEmlnrt = request.getParameter("toEmlnrt");
			
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			SadmomfDao dao = new SadmomfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("mode:" + mode);
            logger.warn("emlnrt:" + dao.getEmlnrt().toString());
            logger.warn("emlnrm:" + dao.getEmlnrm().toString());
            logger.warn("toemlnrt:" + toEmlnrt);
            
            
            //rules
            SADMOMF_U rulerLord = new SADMOMF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				
				if(rulerLord.isValidInputForChangeTransport(dao, userName, mode, toEmlnrt)){
					logger.warn("Before UPDATE ...");
					if("U".equals(mode)){
						logger.debug("Dao:" + dao.toString());
						logger.info("MODE:" + mode + " " + " UPDATE...");
						dmlRetval = this.sadmomfDaoServices.updateTransport(dao, toEmlnrt, dbErrorStackTrace);
					}
					
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
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="syjsSADMOMF_U_BUP.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U_BUP( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintFellesResponseWriter jsonWriter = new JsonTvinnMaintFellesResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsSADMOMF_U_BUP.do");
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
			SadmomfDao dao = new SadmomfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            logger.warn("user:" + user);
            logger.warn("emlnrt:" + dao.getEmlnrt().toString());
            logger.warn("emlnrm:" + dao.getEmlnrm().toString());
            logger.warn("emmid:" + dao.getEmmid());
            
            //rules
            SADMOMF_U rulerLord = new SADMOMF_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				
				  if(rulerLord.isValidInputForUpdateMrnBup(dao, userName)){
						logger.warn("Before UPDATE MRN-BUP ...");
						dmlRetval = this.sadmomfDaoServices.setMrnBup(dao, dbErrorStackTrace);
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
	private SadmomfDaoServices sadmomfDaoServices;
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
}

