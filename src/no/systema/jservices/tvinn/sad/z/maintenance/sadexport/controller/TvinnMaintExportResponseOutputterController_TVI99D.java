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
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.TvineDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.TvineDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.controller.rules.SYFT10R_U;

/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer. All
 * communication to the outside world is done through this gateway.
 * 
 * @author Fredrik Möller
 * @date June 28, 2016
 * 
 */

@Controller
public class TvinnMaintExportResponseOutputterController_TVI99D {
	private static Logger logger = Logger.getLogger(TvinnMaintExportResponseOutputterController_TVI99D.class.getName());

	@Qualifier("tvineDaoServices")
	private TvineDaoServices tvineDaoServices;

	@Autowired
	@Required
	public void setTvineDaoServices(TvineDaoServices value) {
		this.tvineDaoServices = value;
	}

	public TvineDaoServices getTvineDaoServices() {
		return this.tvineDaoServices;
	}

	@Qualifier("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;

	@Autowired
	@Required
	public void setBridfDaoServices(BridfDaoServices value) {
		this.bridfDaoServices = value;
	}

	public BridfDaoServices getBridfDaoServices() {
		return this.bridfDaoServices;
	}

	/**
	 * FreeForm Source: File: TVINE PGM: TVI99D Member: SAD Export Maintenance -
	 * SELECT LIST or SELECT SPECIFIC
	 * 
	 * 
	 * @return
	 * @Example SELECT *:
	 *          http://gw.systema.no:8080/syjservicestn/syjsTVI99D.do?user=OSCAR
	 * @Example SELECT specific:
	 *          http://gw.systema.no:8080/syjservicestn/syjsTVI99D.do?user=OSCAR
	 *          &E9705=611
	 * 
	 */
	@RequestMapping(value = "syjsTVI99D.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsRList(HttpSession session, HttpServletRequest request) {
		// JsonTvinnMaintImportResponseWriter jsonWriter = new
		// JsonTvinnMaintImportResponseWriter();
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsTVI99D");
			String user = request.getParameter("user");

			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			// Start processing now
			if (userName != null && !"".equals(userName)) {
				// bind attributes is any
				TvineDao dao = new TvineDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				// At this point we now know if we are selecting a specific or
				// all the db-table content (select *)
				List list = null;
				// do SELECT
				logger.info("Before SELECT ...");
				if (dao.getE9705() != null && !"".equals(dao.getE9705())) {
					logger.info("findById");
					list = tvineDaoServices.findById(dao.getE9705(), dbErrorStackTrace);
				} else {
					logger.info("getList (all)");
					list = tvineDaoServices.getList(dbErrorStackTrace);
				}
				// process result
				if (list != null) {
					// write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				} else {
					// write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				// write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			// write std.output error output
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
	 * Update Database DML operations File: TVINE PGM: TVI99D Member: SAD Export
	 * Maintenance - SELECT LIST or SELECT SPECIFIC
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsTVI99D_U.do?user=OSCAR&mode=U&e9705=999&e4440=Text
	 *          
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsTVI99D_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsR_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsTVI99D_U");
			// TEST-->logger.info("Servlet root:" +
			// AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			logger.info("USERNAME:" + userName + "XX");
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			// bind attributes is any
			TvineDao dao = new TvineDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			// rules
			// Start processing now
			if (userName != null && !"".equals(userName)) {
				int dmlRetval = 0;
				if ("D".equals(mode)) {
					dmlRetval = tvineDaoServices.delete(dao, dbErrorStackTrace);
				} else if ("A".equals(mode)) {
					List<TvineDao> list = new ArrayList<TvineDao>();
					list = this.tvineDaoServices.findById(dao.getE9705(), dbErrorStackTrace);
					if (list != null && list.size() > 0) {
						// write JSON error output
						errMsg = "ERROR on UPDATE: Code exists already";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,
								dbErrorStackTrace));
					} else {
						dmlRetval = tvineDaoServices.insert(dao, dbErrorStackTrace);
					}
					
				} else if ("U".equals(mode)) {
					dmlRetval = tvineDaoServices.update(dao, dbErrorStackTrace);
				}
						
				
				
				// ----------------------------------
				// check returns from dml operations
				// ----------------------------------
				if (dmlRetval < 0) {
					// write JSON error output
					errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.insert/update/delete";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				} else {
					// OK UPDATE
					sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
				}

			} else {
				// write JSON error output
				errMsg = "ERROR on UPDATE";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}

		} catch (Exception e) {
			// write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}

}
