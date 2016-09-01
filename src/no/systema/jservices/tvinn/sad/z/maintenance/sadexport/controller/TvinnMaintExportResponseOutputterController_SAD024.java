package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.HeadfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.HeadfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules.SAD024_U;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SaehDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.SaehDaoServices;



/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author Fredrik Möller
 * @date Aug 30, 2016
 * 
 */

@Controller
public class TvinnMaintExportResponseOutputterController_SAD024 {
	private static Logger logger = Logger.getLogger(TvinnMaintExportResponseOutputterController_SAD024.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		SAEH-HEADF
	 * 	 PGM:		SAD024
	 * 	 Member: 	SAD Export Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSAD024.do?user=OSCAR
	 * @Example SELECT specific http://gw.systema.no:8080/syjservicestn/syjsSAD024.do?user=OSCAR&seavd=1&setdn=1&sh=y(for search)
	 * 
	 */
	@RequestMapping(value="syjsSAD024.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD024");
			String user = request.getParameter("user");
			String search = request.getParameter("sh");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				SaehDao dao = new SaehDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				logger.info("dao="+dao.toString());
				if( (dao.getSeavd()!=null && !"".equals(dao.getSeavd())) && (dao.getSetdn()!=null && !"".equals(dao.getSetdn())) ){
					if(search!=null && !"".equals(search)){
						logger.info("getListByAvdOpd");
						list = this.saehDaoServices.getListByAvdOpd(dao.getSeavd(), dao.getSetdn(), dbErrorStackTrace);
					}else{
						logger.info("findForUpdate");
						list = this.saehDaoServices.findForUpdate(dao.getSeavd(), dao.getSetdn(), dbErrorStackTrace);
					}
				}else {
					if( dao.getSeavd()!=null && !"".equals(dao.getSeavd()) ){
						logger.info("getListByAvd");
						list = this.saehDaoServices.getListByAvd(dao.getSeavd(), dbErrorStackTrace);
					}else{
						logger.info("getList (Empty)");
						list = new ArrayList();
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
					logger.info("After SELECT:" + " " + status + errMsg );
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
	 * TODO
	 * 
	 * Update Database ONLY UPDATE operations
	 * File: 	SAEH and HEADF
	 * PGM:		SAD024
	 * Member: 	SAD Export Maintenance - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSAD024_U.do?user=OSCAR&mode=U
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSAD024_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD024_U");
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
			SaehDao dao = new SaehDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					//N/A - none existent
				}else{
				  if(SAD024_U.isValidInput(dao, userName, mode)){
						logger.info("Before UPDATE ...");
						List<SaehDao> list = new ArrayList<SaehDao>();
						//do ADD
						if("A".equals(mode)){
							//N/A - none existent
						}else if("U".equals(mode)){
							 dmlRetval = this.saehDaoServices.update(dao, dbErrorStackTrace);
							 if(dmlRetval>=0){
								 List headfList = this.headfDaoServices.findForUpdate(dao.getSeavd(), dao.getSetdn(), dbErrorStackTrace);
								 //search for child record - exact match
								 if(headfList!=null && headfList.size()==1){
									 HeadfDao headfDao = createUpdatedHeadfDao(dao);
									 logger.info(headfDao.toString());
									 dmlRetval = this.headfDaoServices.update(headfDao, dbErrorStackTrace);
								 }else{
									 logger.info("[INFO]: no child record on HEADF");
								 }
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
	
	private HeadfDao createUpdatedHeadfDao(SaehDao dao) {
		HeadfDao headfDao = new HeadfDao();
		headfDao.setHetll(dao.getSetll()); //update
		headfDao.setHetle(dao.getSetle()); //update
		headfDao.setHeavd(dao.getSeavd()); //id
		headfDao.setHeopd(dao.getSetdn()); //id
		return headfDao;
	}

	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("saehDaoServices")
	private SaehDaoServices saehDaoServices;
	@Autowired
	@Required
	public void setSaehDaoServices (SaehDaoServices value){ this.saehDaoServices = value; }
	public SaehDaoServices getSaehDaoServices(){ return this.saehDaoServices; }
	
	@Qualifier ("headfDaoServices")
	private HeadfDaoServices headfDaoServices;
	@Autowired
	@Required
	public void setHeadfDaoServices (HeadfDaoServices value){ this.headfDaoServices = value; }
	public HeadfDaoServices getHeadfDaoServices(){ return this.headfDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

