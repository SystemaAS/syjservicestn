package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;
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
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.controller.rules.TR001R_U;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrkodfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TransitKoder;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TrkodfDaoServices;


/**
 * Service Response Controller -  Koderegister TR001R / TRKODF
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author Fredrik Möller
 * @date Okt 14, 2016
 * 
 */

@Controller
public class TvinnMaintExportResponseOutputterController_TR001R {
	private static Logger logger = LogManager.getLogger(TvinnMaintExportResponseOutputterController_TR001R.class.getName());
	

	/**
	 * 
	 * 	 File: 		TRKODF
	 * 	 PGM:		TR01R
	 * 	 Member: 	NCTS Export Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsTR001R.do?user=OSCAR&tkunik=106
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsTR001R.do?user=OSCAR&tkunik=106&tkkode=AD000001&tktxtn=SANT
	 * 
	 */
	@RequestMapping(value="syjsTR001R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsTR001R.do");
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if( (userName!=null && !"".equals(userName)) ){
				TrkodfDao dao = new TrkodfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            List list = trkodfDaoServices.findBySearch(TransitKoder.fromString(dao.getTkunik()), dao.getTkkode(), dao.getTktxtn(), dbErrorStackTrace);
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
	 * 	 File: 		TRKODF
	 * 	 PGM:		TR001R
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsTR001R_U.do?user=OSCAR&mode=U&tkunik=106&tkkode=AT001000&tktxtn=Norway
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicestn/syjsTR001R_U.do?user=OSCAR&mode=A&tkunik=106&tkkode=AT001999&tktxtn=Norway
	 *
	 */
	@RequestMapping(value="syjsTR001R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsTR001R_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			TrkodfDao dao = new TrkodfDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            TR001R_U rulerLord = new TR001R_U(trkodfDaoServices, sb, dbErrorStackTrace);
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){ 
						dmlRetval = this.trkodfDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
					if (rulerLord.isValidInput(dao, userName, mode)) {
						if ("A".equals(mode)) {
							dmlRetval = this.trkodfDaoServices.insert(dao, dbErrorStackTrace);
						} else if ("U".equals(mode)) {
							dmlRetval = this.trkodfDaoServices.update(dao, dbErrorStackTrace);
						}
					} else {
						// write JSON error output
						errMsg = "ERROR on ADD/UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}
				//----------------------------------
				//check returns from dml operations
				//----------------------------------
				if(dmlRetval<0){
					//write JSON error output
					errMsg = "ERROR on ADD/UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}else{
					//OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}
				
			}else{
				//write JSON error output
				errMsg = "ERROR on ADD/UPDATE";
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
	 * Retrieving TransitKoder for dropdown in GUI.
	 * 
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsTR001R_Kodetyper.do?user=OSCAR
	 */
	@RequestMapping(value="syjsTR001R_Kodetyper.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsKodeTyperList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
	
		try{
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if( (userName!=null && !"".equals(userName)) ){
	            List list = trkodfDaoServices.getTransitKodeTyper();
	            if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
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
	
	
	//----------------
	//WIRED SERVICES
	//----------------

	@Qualifier ("trkodfDaoServices")
	private TrkodfDaoServices trkodfDaoServices;
	@Autowired
	@Required
	public void setTrkodfDaoServices (TrkodfDaoServices value){ this.trkodfDaoServices = value; }
	public TrkodfDaoServices getTrkodfDaoServices(){ return this.trkodfDaoServices; }
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

