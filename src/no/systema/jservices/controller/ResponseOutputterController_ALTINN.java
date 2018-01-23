package no.systema.jservices.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.tvinn.sad.altinn.proxy.Authorization;

@Controller
public class ResponseOutputterController_ALTINN {
	private static Logger logger = Logger.getLogger(ResponseOutputterController_ALTINN.class.getName());

	/**
	 * Entrance for accessing info in secure www.altinn.no, using .P12 certificate
	 * 
	 * Exempel: http://gw.systema.no:8080/syjservicestn/altinnProxy.do?user=OSCAR&orgnr=936809219&servicecode=4814&serviceedition=3
	 * 
	 * @param session
	 * @param request, user , orgnr, servicecode and serviceedition
	 * @return ?
	 */	
	@RequestMapping(value="altinnProxy.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String altinnInit(HttpSession session, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();

		logger.info("altinnProxy.do");
		try {
			String user = request.getParameter("user");
			Assert.notNull(user, "user must be delivered."); 

			String userName = this.bridfDaoServices.findNameById(user); 
			Assert.notNull(userName, "userName not found in Bridf."); 
			
			String orgnr = request.getParameter("orgnr");
			Assert.notNull(orgnr, "orgnr must be delivered."); 

			String servicecode = request.getParameter("servicecode");
			Assert.notNull(servicecode, "servicecode must be delivered."); 	

			String serviceedition = request.getParameter("serviceedition");
			Assert.notNull(serviceedition, "serviceedition must be delivered."); 			
			
			sb.append(authorization.getAnyThing());
			
			logger.info("appended AnyThing on params:");
			
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
	
	@Autowired
	private BridfDaoServices bridfDaoServices;
	
	@Autowired
	private Authorization authorization;
	
}
