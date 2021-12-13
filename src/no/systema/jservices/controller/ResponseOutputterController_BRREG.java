package no.systema.jservices.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.IEnhet;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.brreg.services.BrregRegisterServices;

@Controller
public class ResponseOutputterController_BRREG {
	private static Logger logger = LogManager.getLogger(ResponseOutputterController_BRREG.class.getName());

	/**
	 * Returns a list of kunder that are validated against data.brreg.no and Enhetsregistret
	 * 
	 * @Exempel: http://localhost:8080/syjservicestn/brregKundeDataKontroll.do?user=FREDRIK
	 * 
	 * @param session
	 * @return lista med kunddata att kontrollera
	 */
	@RequestMapping(value="brregKundeDataKontroll.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String kundeDatakontroll(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter(); //TODO, enhance JsonResponseWriter2 with methid
		JsonResponseWriter2 jsonWriter2 = new JsonResponseWriter2();
		StringBuffer sb = new StringBuffer();

		logger.info("brregKundeDataKontroll.do");
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				List invalidaKunderList = null; 
				invalidaKunderList = brregRegisterServices.getInvalidaKunderEnhetsRegisteret();
				if (invalidaKunderList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, invalidaKunderList)); // invalidaKunderList can be empty
				} else {
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: http://data.brreg.no/enhetsregisteret/enhet/";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg);
					sb.append(jsonWriter2.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter2.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			// write std.output error output
			e.printStackTrace();
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}
	
	/**
	 * Return record from data.brreg.no. Adressing Hovedenhet and Underenehet
	 * 
	 * Exempel: http://localhost:8080/syjservicestn/brreg.do?user=OSCAR&orgnr=936809219
	 * 
	 * @param session
	 * @param request, user and orgnr
	 * @return record if exist, else null
	 */
	@RequestMapping(value="brreg.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String brreg(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<IEnhet> jsonWriter = new JsonResponseWriter2<IEnhet>();
		StringBuffer sb = new StringBuffer();
		String orgnr = request.getParameter("orgnr");
		
		try {
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				IEnhet record = brregRegisterServices.get(orgnr);
				if (record != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetComposite(userName, record));	
				} else {
					errMsg = "ERROR on SELECT: Can not find Enhet on orgnr="+orgnr;
					status = "error";
					logger.info("After get Enhet from brregRegisterServices :" + " " + status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}

	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	
	
	@Qualifier ("brregRegisterServices")
	private BrregRegisterServices brregRegisterServices;
	@Autowired
	@Required
	public void setBrregRegisterServices (BrregRegisterServices value){ this.brregRegisterServices = value; }
	public BrregRegisterServices getBrregRegisterServices(){ return this.brregRegisterServices; }	
	
	
}
