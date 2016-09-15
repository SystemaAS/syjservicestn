package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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

//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.SoktariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.SoktariDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter.JsonTvinnMaintImportResponseWriter;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.TariDaoServices;
//rules
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.SAD062R_U;





/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date May 6, 2016
 * 
 */

@Controller
public class TvinnMaintImportResponseOutputterController_SAD062R {
	private static Logger logger = Logger.getLogger(TvinnMaintImportResponseOutputterController_SAD062R.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		SOKTARI
	 * 	 PGM:		SAD062
	 * 	 Member: 	SAD Import Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSAD062R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSAD062R.do?user=OSCAR&tariff=2041000
	 * 
	 */
	@RequestMapping(value="syjsSAD062R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter jsonWriter = new JsonTvinnMaintImportResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD062R");
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
				SoktariDao dao = new SoktariDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( (dao.getTariff()!=null && !"".equals(dao.getTariff()))  && (dao.getBeskr1()!=null && !"".equals(dao.getBeskr1()))){
	            	logger.info("findForUpdate");
					list = this.soktariDaoServices.findForUpdate(dao.getTariff(), dao.getBeskr1(), dbErrorStackTrace);
	            }else{
					if( dao.getTariff()!=null && !"".equals(dao.getTariff()) ){
						logger.info("findById");
						list = this.soktariDaoServices.findById(dao.getTariff(), dbErrorStackTrace);
					}else{
						logger.info("getList (all)");
						list = this.soktariDaoServices.getList(dbErrorStackTrace);
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
	 * Update Database DML operations
	 * File: 	SOKTARI
	 * PGM:		SAD062
	 * Member: 	SAD Import Maintenance - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSAD062R_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSAD062R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter jsonWriter = new JsonTvinnMaintImportResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD062R_U.do");
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
			SoktariDao dao = new SoktariDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SAD062R_U rulerLord = new SAD062R_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.soktariDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						logger.info("Before UPDATE ...");
						List<SoktariDao> list = new ArrayList<SoktariDao>();
						//check if the tariff nr exists
						List<TariDao> listTari = new ArrayList<TariDao>();
						listTari = this.tariDaoServices.findByIdExactMatch(dao.getTariff(), dbErrorStackTrace);
						if(listTari!=null && listTari.size()>0){
							
							//do ADD
							if("A".equals(mode)){
								logger.info("ADD ...");
						
								list = this.soktariDaoServices.findForUpdate(dao.getTariff(), dao.getBeskr1(), dbErrorStackTrace);
								//check if there is already such a code. If it does, stop the update
								if(list!=null && list.size()>0){
									//write JSON error output
									errMsg = "ERROR on UPDATE: Code exists already";
									status = "error";
									sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
								}else{
									dmlRetval = this.soktariDaoServices.insert(dao, dbErrorStackTrace);
								}
							//do Update	
							}else if("U".equals(mode)){
								logger.info("UPDATE ...");
								dmlRetval = this.soktariDaoServices.update(dao, dbErrorStackTrace);
							}
							
						}else{
							//write JSON error output
							errMsg = "ERROR on UPDATE: Tariff nr. is not valid";
							status = "error";
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
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
	@Qualifier ("tariDaoServices")
	private TariDaoServices tariDaoServices;
	@Autowired
	@Required
	public void setTariDaoServices (TariDaoServices value){ this.tariDaoServices = value; }
	public TariDaoServices getTariDaoServices(){ return this.tariDaoServices; }
	
	
	@Qualifier ("soktariDaoServices")
	private SoktariDaoServices soktariDaoServices;
	@Autowired
	@Required
	public void setSoktariDaoServices (SoktariDaoServices value){ this.soktariDaoServices = value; }
	public SoktariDaoServices getSoktariDaoServices(){ return this.soktariDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

