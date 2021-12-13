package no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
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
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.TariDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.controller.rules.SAD015_U;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.SadavgeDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.SadavgeDaoServices;

/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer. All
 * communication to the outside world is done through this gateway.
 * 
 * @author Fredrik Möller
 * @date Aug 12, 2016
 * 
 */

@Controller
public class TvinnMaintExportResponseOutputterController_SAD015 {
	private static Logger logger = LogManager.getLogger(TvinnMaintExportResponseOutputterController_SAD015.class.getName());

	@Qualifier("sadavgeDaoServices")
	private SadavgeDaoServices sadavgeDaoServices;

	@Autowired
	@Required
	public void setSadavgeDaoServices(SadavgeDaoServices value) {
		this.sadavgeDaoServices = value;
	}

	public SadavgeDaoServices getSadavgeDaoServices() {
		return this.sadavgeDaoServices;
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

	@Qualifier("tariDaoServices")
	private TariDaoServices tariDaoServices;

	@Autowired
	@Required
	public void setTariDaoServices(TariDaoServices value) {
		this.tariDaoServices = value;
	}

	public TariDaoServices getTariDaoServices() {
		return this.tariDaoServices;
	}

	/**
	 * FreeForm Source: File: SAD015 PGM: SAD015 Member: SAD Export Maintenance
	 * - SELECT LIST or SELECT SPECIFIC
	 * 
	 * 
	 * @return
	 * @Example SELECT *:
	 *          http://gw.systema.no:8080/syjservicestn/syjsSAD015.do?user=OSCAR
	 * @Example SELECT specific:
	 *          http://gw.systema.no:8080/syjservicestn/syjsSAD015.do?user=OSCAR&agtanr=03011100&agakd=FF&agskv=100
	 * 
	 */
	@RequestMapping(value = "syjsSAD015.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsRList(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsSAD015");
			String user = request.getParameter("user");

			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			// Start processing now
			if (userName != null && !"".equals(userName)) {
				// bind attributes is any
				SadavgeDao dao = new SadavgeDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
				binder.bind(request);
				// At this point we now know if we are selecting a specific or
				// all the db-table content (select *)
				List list = null;
				// do SELECT
				logger.info("Before SELECT ...dao="+dao.toString());
				if (dao.getAgtanr() != null && !"".equals(dao.getAgtanr()) && dao.getAgakd() != null
						&& !"".equals(dao.getAgakd()) && dao.getAgskv() != null && !"".equals(dao.getAgskv())) {
					list = sadavgeDaoServices.findByIds(dao.getAgtanr(), dao.getAgakd(), dao.getAgskv(),
							dbErrorStackTrace);
				} else if (dao.getAgtanr() != null && !"".equals(dao.getAgtanr())) {
					list = sadavgeDaoServices.findById(dao.getAgtanr(), dbErrorStackTrace);
				} else {
					logger.info("getList (all)");
					list = sadavgeDaoServices.getList(dbErrorStackTrace);
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
	 * Update Database DML operations File: SAD015 PGM: SAD015 Member: SAD
	 * Export Maintenance - SELECT LIST or SELECT SPECIFIC
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicestn/syjsSAD015_U.do?user=OSCAR&mode=U&xxx=yyy
	 * 
	 * @Example ADD:
	 * 			http://gw.systema.no:8080/syjservicestn/syjsSAD015_U.do?user=OSCAR&mode=U&agtanr=yyy&agakd=yyy&agskv=yyy&agdtf=yyy&agdtt=yyy&agkd=yyy&agpp=yyy&agsats=yyy&agaktk=yyy
	 * 
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value = "syjsSAD015_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsR_U(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();

		try {
			logger.info("Inside syjsSAD015_U");
			// TEST-->logger.info("Servlet root:" +
			// AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user);
			logger.info("USERNAME:" + userName + ", mode:"+mode);
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			// bind attributes is any
			SadavgeDao dao = new SadavgeDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);
			
			if (userName != null && !"".equals(userName)) {
				int dmlRetval = 0;
				if ("D".equals(mode)) {
					if (SAD015_U.isValid(dao, userName, mode)) {
						dmlRetval = sadavgeDaoServices.delete(dao, dbErrorStackTrace);
					} else {
						// write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}

				} else {
					if (SAD015_U.isValidInput(dao, userName, mode)) {

						if ("A".equals(mode)) {
							if (isValidTariffnr(dao.getAgtanr(), dbErrorStackTrace)) {
								dao.setAgakd("FF"); // Hardwired
								if (!idExist(dao, dbErrorStackTrace)) {
									dmlRetval = sadavgeDaoServices.insert(dao, dbErrorStackTrace);
								} else {
									// write JSON error output
									errMsg = "ERROR on UPDATE: Record exists already";
									status = "error";
									sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,
											dbErrorStackTrace));
								}
							} else {
								// write JSON error output
								errMsg = "ERROR on UPDATE: Tariff nr. is not valid";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,
										dbErrorStackTrace));
							}

						} else if ("U".equals(mode)) {
							dmlRetval = sadavgeDaoServices.update(dao, dbErrorStackTrace);

						}
					} else {
						// write JSON error output
						errMsg = "ERROR on UPDATE: invalid?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}

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

	private boolean idExist(SadavgeDao dao, StringBuffer dbErrorStackTrace) {
		List<SadavgeDao> list = new ArrayList<SadavgeDao>();
		list = this.sadavgeDaoServices.findByIds(dao.getAgtanr(), dao.getAgakd(),dao.getAgskv(), dbErrorStackTrace);
		if (list != null && list.size() > 0) {
			logger.info("idExist ,list.size()="+list.size());
			return true;
		} else {
			logger.info("not idExist ");
			return false;
		}
		
	}

	private boolean isValidTariffnr(String agtanr, StringBuffer dbErrorStackTrace) {
		List<TariDao> listTari = new ArrayList<TariDao>();
		listTari = this.tariDaoServices.findByIdExactMatch(agtanr, dbErrorStackTrace);
		if (listTari != null && listTari.size() > 0) {
			return true; 
		} else {
			return false;
		}

	}

}
