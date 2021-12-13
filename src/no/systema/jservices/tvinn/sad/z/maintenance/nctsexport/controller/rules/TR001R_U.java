package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.controller.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;

import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrkodfDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.entities.TrughDao;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TransitKoder;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TrkodfDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services.TrughDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts2DaoServices;
import no.systema.main.util.MessageSourceHelper;
/**
 * 
 * @author Fredrik Möller
 * @date Okt 17, 2016
 */
public class TR001R_U {
	private static Logger logger = LogManager.getLogger(TR001R_U.class.getName());
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private MessageSourceHelper messageSourceHelper = new MessageSourceHelper();
	private TrkodfDaoServices trkodfDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;

	public TR001R_U(TrkodfDaoServices trkodfDaoServices, StringBuffer sb, StringBuffer dbErrorStackTrace){
		this.trkodfDaoServices = trkodfDaoServices;
		this.errors = sb;
		this.dbErrors = dbErrorStackTrace;
	}

	/**
	 * Validate null values and exist controls i db.
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(TrkodfDao dao, String user, String mode) {
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ((dao.getTkunik() != null && !"".equals(dao.getTkunik()))
					&& (dao.getTkkode() != null && !"".equals(dao.getTkkode()))
					&& (dao.getTktxtn() != null && !"".equals(dao.getTktxtn()))
					&& (dao.getTktxte() != null && !"".equals(dao.getTktxte())) ) {
				// Check duplicate
				if ("A".equals(mode) && existInTrkodf(user, TransitKoder.fromString(dao.getTkunik()), dao.getTkkode())) {
					return false;
				}
				
			} else {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}

	public boolean isValidInputForDelete(TrkodfDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTkunik() != null && !"".equals(dao.getTkunik())
				 &&dao.getTkkode()!=null && !"".equals(dao.getTkkode())) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		
		return retval;
	}
	
	private boolean existInTrkodf(String userName, TransitKoder tkUnik, String tkKode) {
		boolean exists = this.trkodfDaoServices.exists(tkUnik, tkKode);
		if (!exists) {
			return false;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					messageSourceHelper.getMessage("systema.tvinn.sad.ncts.export.error.tkkode", new Object[] { tkKode, tkUnik.getTransitKode() }), "error", dbErrors));
			return true;
			
		}
	}
	
	
}
