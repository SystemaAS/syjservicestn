package no.systema.jservices.tvinn.sad.expressfortolling.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import no.systema.jservices.common.dao.Sted2Dao;
import no.systema.jservices.common.dao.services.BridfDaoService;
import no.systema.jservices.common.dao.services.Sted2DaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;

@Controller
public class JsonResponseOutputterController_STED2 {
	private static final Logger logger = LogManager.getLogger(JsonResponseOutputterController_STED2.class.getName());

	
	/**
	 * File: 	STED2
	 * 
	 * @Example SELECT semi specific: http://gw.systema.no:8080/syjservicestn/syjsSTED2.do?user=OSCAR&st2lk=NO
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestn/syjsSTED2.do?user=OSCAR&st2kod=8000&st2lk=NO
	 * @Example SELECT semi specific: http://gw.systema.no:8080/syjservicestn/syjsSTED2.do?user=OSCAR&st2kod=8000
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicestn/syjsSTED2.do?user=OSCAR
	 * 
	 */
	@RequestMapping(value="syjsSTED2.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsSTED2(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<Sted2Dao> jsonWriter = new JsonResponseWriter2<Sted2Dao>();
		StringBuffer sb = new StringBuffer();
		List<Sted2Dao> sted2DaoList = new ArrayList<Sted2Dao>();

		try {
			logger.info("Inside syjsSTED2");
			String user = request.getParameter("user");
			// Check ALWAYS user in BRIDF
			String userName = bridfDaoService.getUserName(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			Map<String, Object> params = new HashMap<String, Object>();

			if (StringUtils.hasValue(userName)) {
				Sted2Dao resultDao = new Sted2Dao();
				Sted2Dao dao = new Sted2Dao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);

				if (StringUtils.hasValue(dao.getSt2kod())) {
					if (StringUtils.hasValue(dao.getSt2lk())) {
						resultDao = sted2DaoService.find(dao);
						if (resultDao != null) {
							sted2DaoList.add(resultDao);
						}
					} else {
						sted2DaoList = sted2DaoService.findByLike(dao.getSt2kod());
					}
				} else if (StringUtils.hasValue(dao.getSt2lk())){
					params.put("st2lk", dao.getSt2lk());
					sted2DaoList = sted2DaoService.findAll(params);
				} else {
					sted2DaoList = sted2DaoService.findAll(null);
				}
				if (sted2DaoList != null) {
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, sted2DaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find Sted2Dao list";
					status = "error";
					logger.info(status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}

	@Autowired
	private BridfDaoService bridfDaoService;
	
	@Autowired
	private Sted2DaoService sted2DaoService;
	
	
}
