package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.controller;

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

import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.controller.rules.TR030R_U;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrughDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TrkodfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TrughDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts2DaoServices;


/**
 * Service Response Controller -  Garantiref. TR030R / TRUGH,KODTVA,CUNDF,KODTS2,TRKODF
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author Fredrik Möller
 * @date Sep 16, 2016
 * 
 */

@Controller
public class TvinnMaintExportResponseOutputterController_TR030R {
	private static Logger logger = Logger.getLogger(TvinnMaintExportResponseOutputterController_TR030R.class.getName());
	

	/**
	 * 
	 * 	 File: 		TRUGH
	 * 	 PGM:		TR030R
	 * 	 Member: 	NCTS Export Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsTR030R?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsTR030R.do?user=OSCAR&tggnr=05NO01011A0000A10
	 * 
	 */
	@RequestMapping(value="syjsTR030R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
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
				//bind attributes is any
				TrughDao dao = new TrughDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( (dao.getTggnr()!=null && !"".equals(dao.getTggnr())) ){
					list = trughDaoServices.findByIdSearch(dao.getTggnr(), dbErrorStackTrace);
	            }
	            else{
					list = trughDaoServices.getList(dbErrorStackTrace);
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
	 * 	 File: 		TRUGH
	 * 	 PGM:		TR030R
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsTR030R_U.do?user=OSCAR&mode=U&xxx=yyy
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicestn/syjsTR030R_U.do?user=OSCAR&mode=U&agtanr=yyy&agakd=yyy&agskv=yyy&agdtf=yyy&agdtt=yyy&agkd=yyy&agpp=yyy&agsats=yyy&agaktk=yyy
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="syjsTR030R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsTR030R_U.do");
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			TrughDao dao = new TrughDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            TR030R_U rulerLord = new TR030R_U(trughDaoServices , trkodfDaoServices, kodts2DaoServices ,sb, dbErrorStackTrace);
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){ 
						dmlRetval = this.trughDaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
					logger.info("About to do isValidInput");
					if (rulerLord.isValidInput(dao, userName, mode)) {
						rulerLord.updateNumericFieldsIfNull(dao);
						if ("A".equals(mode)) {
							logger.info("A....dao="+dao.toString());
							dmlRetval = this.trughDaoServices.insert(dao, dbErrorStackTrace);
						} else if ("U".equals(mode)) {
							logger.info("U....dao="+dao.toString());
							dmlRetval = this.trughDaoServices.update(dao, dbErrorStackTrace);
						}
					} else {
						logger.info("else, dao="+dao.toString());
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
		
		logger.info("sb.toString()="+sb.toString());
		
		return sb.toString();
	}
	

	/**
	 * 
	 * 
	 * @return
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsCustomerRecord.do?user=OSCAR&kundnr=2
	 * 
	 */
	@RequestMapping(value="syjsCustomerRecord.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String customerRecord( HttpSession session, HttpServletRequest request) {
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
				//bind attributes is any
				CundfDao dao = new CundfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            List list = null;
	            if( (dao.getKundnr()!=null && !"".equals(dao.getKundnr())) ){
					list = cundfDaoServices.findById(dao.getKundnr(), dbErrorStackTrace);
	            }
	            
				if (list!=null){
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
	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("cundfDaoServices")
	private CundfDaoServices cundfDaoServices;
	@Autowired
	@Required
	public void setCundfDaoServices (CundfDaoServices value){ this.cundfDaoServices = value; }
	public CundfDaoServices getCundfDaoServices(){ return this.cundfDaoServices; }
	
	@Qualifier ("kodts2DaoServices")
	private Kodts2DaoServices kodts2DaoServices;
	@Autowired
	@Required
	public void setKodts2DaoServices (Kodts2DaoServices value){ this.kodts2DaoServices = value; }
	public Kodts2DaoServices getKodts2DaoServices(){ return this.kodts2DaoServices; }
	
	@Qualifier ("trkodfDaoServices")
	private TrkodfDaoServices trkodfDaoServices;
	@Autowired
	@Required
	public void setTrkodfDaoServices (TrkodfDaoServices value){ this.trkodfDaoServices = value; }
	public TrkodfDaoServices getTrkodfDaoServices(){ return this.trkodfDaoServices; }
	
	@Qualifier ("trughDaoServices")
	private TrughDaoServices trughDaoServices;
	@Autowired
	@Required
	public void setTrughDaoServices (TrughDaoServices value){ this.trughDaoServices = value; }
	public TrughDaoServices getTrughDaoServices(){ return this.trughDaoServices; }

	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

