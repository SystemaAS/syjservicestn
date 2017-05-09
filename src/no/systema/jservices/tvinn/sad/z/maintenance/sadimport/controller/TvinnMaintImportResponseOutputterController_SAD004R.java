package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.controller.rules.SAD004R_U;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.SadlDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.SadlDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.TariDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.KodtseDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter.JsonTvinnMaintImportResponseWriter;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts2DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts8DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.KodtsbDaoServices;


/**
 * Service Response Controller - Gml. Kunders vareregister SAD004 / SADL 
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date May 30, 2016
 * 
 */

@Controller
public class TvinnMaintImportResponseOutputterController_SAD004R {
	private static Logger logger = Logger.getLogger(TvinnMaintImportResponseOutputterController_SAD004R.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		SADL
	 * 	 PGM:		SAD004
	 * 	 Member: 	SAD Import Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSAD004R.do?user=OSCAR&slknr=1
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSAD004R.do?user=OSCAR&slknr=1&slalfa=2041000
	 * 
	 */
	@RequestMapping(value="syjsSAD004R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter jsonWriter = new JsonTvinnMaintImportResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD004R");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String kundnr = request.getParameter("slknr");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if( (userName!=null && !"".equals(userName)) && (kundnr!=null && !"".equals(kundnr)) ){
				//bind attributes is any
				SadlDao dao = new SadlDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( (dao.getSlalfa()!=null && !"".equals(dao.getSlalfa())) && (dao.getSlknr()!=null && !"".equals(dao.getSlknr())) ){
					list = this.sadlDaoServices.findById(dao.getSlalfa(), dao.getSlknr(), dbErrorStackTrace);
	            }else{
					list = this.sadlDaoServices.getList(dao.getSlknr(), dbErrorStackTrace);
					
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
	 * File: 	SADL
	 * PGM:		SAD004
	 * Member: 	SAD Import Maintenance - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSAD004R_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSAD004R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriter jsonWriter = new JsonTvinnMaintImportResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD004R_U.do");
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
			SadlDao dao = new SadlDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SAD004R_U rulerLord = new SAD004R_U(tariDaoServices, sadlDaoServices, kodtseDaoServices, kodts2DaoServices, kodts8DaoServices, kodtsbDaoServices ,sb, dbErrorStackTrace);
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.sadlDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
					if (rulerLord.isValidInput(dao, userName, mode)) {
						if ("A".equals(mode)) {
							dmlRetval = this.sadlDaoServices.insert(dao, dbErrorStackTrace);
						} else if ("U".equals(mode)) {
							dmlRetval = this.sadlDaoServices.update(dao, dbErrorStackTrace);
						}
					} else {
						// write JSON error output
						logger.info("Error, dbErrorStackTrace= "+dbErrorStackTrace);
						logger.info("Error, sb="+sb.toString());
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
	@Qualifier ("kodtseDaoServices")
	private KodtseDaoServices kodtseDaoServices;
	@Autowired
	@Required
	public void setKodtseDaoServices (KodtseDaoServices value){ this.kodtseDaoServices = value; }
	public KodtseDaoServices getKodtseDaoServices(){ return this.kodtseDaoServices; }

	@Qualifier ("kodts2DaoServices")
	private Kodts2DaoServices kodts2DaoServices;
	@Autowired
	@Required
	public void setKodts2DaoServices (Kodts2DaoServices value){ this.kodts2DaoServices = value; }
	public Kodts2DaoServices getKodts2DaoServices(){ return this.kodts2DaoServices; }	

	@Qualifier ("kodts8DaoServices")
	private Kodts8DaoServices kodts8DaoServices;
	@Autowired
	@Required
	public void setKodts8DaoServices (Kodts8DaoServices value){ this.kodts8DaoServices = value; }
	public Kodts8DaoServices getKodts8DaoServices(){ return this.kodts8DaoServices; }		

	@Qualifier ("kodtsbDaoServices")
	private KodtsbDaoServices kodtsbDaoServices;
	@Autowired
	@Required
	public void setKodtsbDaoServices (KodtsbDaoServices value){ this.kodtsbDaoServices = value; }
	public KodtsbDaoServices getKodtsbDaoServices(){ return this.kodtsbDaoServices; }			
	
	@Qualifier ("tariDaoServices")
	private TariDaoServices tariDaoServices;
	@Autowired
	@Required
	public void setTariDaoServices (TariDaoServices value){ this.tariDaoServices = value; }
	public TariDaoServices getTariDaoServices(){ return this.tariDaoServices; }
	
	
	@Qualifier ("sadlDaoServices")
	private SadlDaoServices sadlDaoServices;
	@Autowired
	@Required
	public void setSadlDaoServices (SadlDaoServices value){ this.sadlDaoServices = value; }
	public SadlDaoServices getSadlDaoServices(){ return this.sadlDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

