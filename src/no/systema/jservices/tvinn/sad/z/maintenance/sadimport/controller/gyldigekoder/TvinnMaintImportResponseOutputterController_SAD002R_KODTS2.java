package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.gyldigekoder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


 
import org.slf4j.*;
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
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts2Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts2DaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter.JsonTvinnMaintImportResponseWriterGyldigeKoder;
import no.systema.jservices.jsonwriter.reflection.JsonWriterReflectionManager;
//rules
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.gyldigekoder.SAD002_KODTS2R_U;





/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date May 20, 2016
 * 
 */

@Controller
public class TvinnMaintImportResponseOutputterController_SAD002R_KODTS2 {
	private static Logger logger = LoggerFactory.getLogger(TvinnMaintImportResponseOutputterController_SAD002R_KODTS2.class.getName());
	
	/**
	 * Source:
	 * 	 File: 		KODTS2
	 * 	 PGM:		SAD002
	 * 	 Member: 	SAD Import Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS2R.do?user=OSCAR
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS2R.do?user=OSCAR&ks2lk=NO
	 * 
	 */
	@RequestMapping(value="syjsSAD002_KODTS2R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsSAD002_KODTS2R( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriterGyldigeKoder jsonWriter = new JsonTvinnMaintImportResponseWriterGyldigeKoder();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD002_KODTS2R.do");
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
				Kodts2Dao dao = new Kodts2Dao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
	            logger.info("Before SELECT ...");
	            if( (dao.getKs2lk()!=null && !"".equals(dao.getKs2lk())) ){
	            	logger.info("findById");
	            	list = this.kodts2DaoServices.findById(dao.getKs2lk(), dbErrorStackTrace);
	            }else{
	            	logger.info("getList (all)");
					list = this.kodts2DaoServices.getList(dbErrorStackTrace);
					
					
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
	 * File: 	KODTS2
	 * PGM:		SAD002
	 * Member: 	SAD Import Maintenance - UPDATE SPECIFIC
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS2R_U.do?user=OSCAR&mode=U/A/D
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsSAD002_KODTS2R_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsSAD002R_U( HttpSession session, HttpServletRequest request) {
		JsonTvinnMaintImportResponseWriterGyldigeKoder jsonWriter = new JsonTvinnMaintImportResponseWriterGyldigeKoder();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsSAD002_KODTS2R_U.do");
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
			Kodts2Dao dao = new Kodts2Dao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            SAD002_KODTS2R_U rulerLord = new SAD002_KODTS2R_U();
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				int dmlRetval = 0;
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						dmlRetval = this.kodts2DaoServices.delete(dao, dbErrorStackTrace);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						logger.info(sb.toString());
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						logger.info("Before UPDATE ...");
						List<Kodts2Dao> list = new ArrayList<Kodts2Dao>();
						
						//do ADD
						if("A".equals(mode)){
							list = this.kodts2DaoServices.findById(dao.getKs2lk(), dbErrorStackTrace);
							//check if there is already such a code. If it does, stop the update
							if(list!=null && list.size()>0){
								//write JSON error output
								errMsg = "ERROR on UPDATE: Code exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
								logger.info(sb.toString());
							}else{
								dmlRetval = this.kodts2DaoServices.insert(dao, dbErrorStackTrace);
							}
						}else if("U".equals(mode)){
							 dmlRetval = this.kodts2DaoServices.update(dao, dbErrorStackTrace);
						}
						
				  }else{
						//write JSON error output
						errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						logger.info(sb.toString());
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
					logger.info(sb.toString());
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
				logger.info(sb.toString());
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
	@Qualifier ("kodts2DaoServices")
	private Kodts2DaoServices kodts2DaoServices;
	@Autowired
	@Required
	public void setKodts2DaoServices (Kodts2DaoServices value){ this.kodts2DaoServices = value; }
	public Kodts2DaoServices getKodts2DaoServices(){ return this.kodts2DaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

