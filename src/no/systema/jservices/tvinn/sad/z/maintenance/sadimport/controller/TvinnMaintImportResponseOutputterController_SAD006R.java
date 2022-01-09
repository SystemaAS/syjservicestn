package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.HeadfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.HeadfDaoServices;
//rules
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.SAD006R_U;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter.JsonTvinnMaintImportResponseWriter;
//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SadhHeadfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.SadhDaoServices;





/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Jun 17, 2016
 * 
 */

@Controller
public class TvinnMaintImportResponseOutputterController_SAD006R {
	private static Logger logger = LoggerFactory.getLogger(TvinnMaintImportResponseOutputterController_SAD006R.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		SADH-HEADF
	 * 	 PGM:		SAD006
	 * 	 Member: 	SAD Import Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSAD006R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSAD006R.do?user=OSCAR&siavd=&sitdn=&sh=y(for search)
	 * 
	 */
	@RequestMapping(value="syjsSAD006R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter jsonWriter = new JsonTvinnMaintImportResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD006R");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String search = request.getParameter("sh");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				SadhHeadfDao dao = new SadhHeadfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if( (dao.getSiavd()!=null && !"".equals(dao.getSiavd())) && (dao.getSitdn()!=null && !"".equals(dao.getSitdn())) ){
					if(search!=null && !"".equals(search)){
						logger.info("getListByAvdOpd");
						list = this.sadhDaoServices.getListByAvdOpd(dao.getSiavd(), dao.getSitdn(), dbErrorStackTrace);
					}else{
						logger.info("findForUpdate");
						list = this.sadhDaoServices.findForUpdate(dao.getSiavd(), dao.getSitdn(), dbErrorStackTrace);
					}
				}else {
					if( dao.getSiavd()!=null && !"".equals(dao.getSiavd()) ){
						logger.info("getListByAvd");
						list = this.sadhDaoServices.getListByAvd(dao.getSiavd(), dbErrorStackTrace);
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
	 * 
	 * Update Database ONLY UPDATE operations
	 * File: 	SADH
	 * PGM:		SAD006
	 * Member: 	SAD Import Maintenance - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSAD006R_U.do?user=OSCAR&mode=U
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsSAD006R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter jsonWriter = new JsonTvinnMaintImportResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD006R_U");
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
			SadhHeadfDao dao = new SadhHeadfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SAD006R_U rulerLord = new SAD006R_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					//N/A - none existent
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						logger.info("Before UPDATE ...");
						List<SadhHeadfDao> list = new ArrayList<SadhHeadfDao>();
						
						//do ADD
						if("A".equals(mode)){
							//N/A - none existent
						}else if("U".equals(mode)){
							 dmlRetval = this.sadhDaoServices.update(dao, dbErrorStackTrace);
							 if(dmlRetval>=0){
								 List headfList = this.headfDaoServices.findForUpdate(dao.getSiavd(), dao.getSitdn(), dbErrorStackTrace);
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
	
	private HeadfDao createUpdatedHeadfDao(SadhHeadfDao dao) {
		HeadfDao headfDao = new HeadfDao();
		headfDao.setHetll(dao.getSitll()); //update
		headfDao.setHetle(dao.getSitle()); //update
		headfDao.setHeavd(dao.getSiavd()); //id
		headfDao.setHeopd(dao.getSitdn()); //id
		return headfDao;
	}

	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("sadhDaoServices")
	private SadhDaoServices sadhDaoServices;
	@Autowired
	@Required
	public void setSadhDaoServices (SadhDaoServices value){ this.sadhDaoServices = value; }
	public SadhDaoServices getSadhDaoServices(){ return this.sadhDaoServices; }
	
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

