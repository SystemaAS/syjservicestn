package no.systema.jservices.tvinn.sad.z.maintenance.sad.controller.rules;

import java.util.ArrayList;
import java.util.List;

import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.SadlDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.entities.TariDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.SadlDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.TariDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.entities.gyldigekoder.KodtseDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadexport.model.dao.services.gyldigekoder.KodtseDaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts2Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.Kodts8Dao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.gyldigekoder.KodtsbDao;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts2DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.Kodts8DaoServices;
import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.services.gyldigekoder.KodtsbDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date May 30, 2016
 */
public class SAD004R_U {
	private JsonResponseWriter jsonWriter = new JsonResponseWriter();
	private TariDaoServices tariDaoServices = null;
	private SadlDaoServices  sadlDaoServices= null;
	private KodtseDaoServices kodtseDaoServices = null;
	private Kodts2DaoServices kodts2DaoServices = null;
	private Kodts8DaoServices kodts8DaoServices = null;
	private KodtsbDaoServices kodtsbDaoServices = null;
	
	private StringBuffer errors = null;
	private StringBuffer dbErrors = null;
	
	public SAD004R_U(TariDaoServices tariDaoServices, SadlDaoServices sadlDaoServices,
			KodtseDaoServices kodtseDaoServices, Kodts2DaoServices kodts2DaoServices, Kodts8DaoServices kodts8DaoServices, KodtsbDaoServices kodtsbDaoServices, StringBuffer sb,
			StringBuffer dbErrorStackTrace) {
		this.tariDaoServices = tariDaoServices;
		this.sadlDaoServices = sadlDaoServices;
		this.kodtseDaoServices = kodtseDaoServices;
		this.kodts2DaoServices = kodts2DaoServices;
		this.kodts8DaoServices = kodts8DaoServices;
		this.kodtsbDaoServices = kodtsbDaoServices;
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
	public boolean isValidInput(SadlDao dao, String user, String mode){
		//boolean retval = true;
		
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			// check dao
			if ((dao.getSlalfa() != null && !"".equals(dao.getSlalfa()))
					&& (dao.getSlknr() != null && !"".equals(dao.getSlknr()))) {
				// Check if exist in TARI
				if (existInTari(user, dao.getSltanr())) {
					// Ok
				} else {
					return false;
				}
				// Check duplicate
				if ("A".equals(mode) && existInSadl(user, dao.getSlalfa(), dao.getSlknr())) {
					return false;
				}
				// Check sloppl
				if (dao.getSloppl() != null && !"".equals(dao.getSloppl()) && isValidSloppl(user, dao.getSloppl())) {
					// Ok
				} else {
					return false;
				}
				// Check kode
				if (dao.getSlkdae() != null && !"".equals(dao.getSlkdae()) && dao.getSlkdse() != null
						&& !"".equals(dao.getSlkdse())) {
					if (existInKodts8(user, dao.getSlkdae(), dao.getSlkdse())) {
						// Ok
					} else {
						return false;
					}
				} else {
					// Ok
				}
				//Check ref
				if (dao.getSlcref() != null && !"".equals(dao.getSlcref()) && existInKodtsb(user, dao.getSlcref())) {
					// Ok
				} else {
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

	
	private boolean isValidSloppl(String user, String sloppl) {
		boolean valid = false;

		try {
			int slopplInt = Integer.parseInt(sloppl);
			if (existsInKodtse(user, sloppl)) {
				valid = true;
			}

			return valid;
		} catch (NumberFormatException e) {
			// sloopl is Land, continue
		}

		if (existsInKodts2(user, sloppl)) {
			valid = true;
		} 

		return valid;

	}

	
	private boolean existsInKodtse(String userName, String sloppl) {
		boolean exist = false;
		
		List<KodtseDao> listKodtse = new ArrayList<KodtseDao>();
		listKodtse = this.kodtseDaoServices.findById(sloppl, dbErrors);
		if (listKodtse != null && listKodtse.size() > 0) {
			exist = true;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					"ERROR on UPDATE/ADD: Fylke is not valid.", "error", dbErrors));
		}
		
		return exist;		
		
	}	
	
	private boolean existsInKodts2(String user, String sloppl) {
		boolean exist = false;
		
		List<Kodts2Dao> listKodts2 = new ArrayList<Kodts2Dao>();
		listKodts2 = this.kodts2DaoServices.findById(sloppl, dbErrors);
		if (listKodts2 != null && listKodts2.size() > 0) {
			exist = true;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(user,
					"ERROR on UPDATE/ADD: Landskode is not valid.", "error", dbErrors));
		}

		return exist;		
	}
	
	
	private boolean existInSadl(String userName, String slalfa, String slknr) {
		boolean exist = false;
		List<SadlDao> list = new ArrayList<SadlDao>();
		list = this.sadlDaoServices.findById(slalfa, slknr, dbErrors);
		// check if there is already such a code. If it does, stop the update
		if (list != null && list.size() == 0) {
			exist = false;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName, "ERROR on ADD: Code exists already", "error",
					dbErrors));
			exist = true;

		}
		return exist;
	}

	private boolean existInTari(String userName, String sltanr) {
		boolean exist = false;
		List<TariDao> listTari = new ArrayList<TariDao>();
		listTari = this.tariDaoServices.findByIdExactMatch(sltanr, dbErrors);
		if (listTari != null && listTari.size() > 0) {
			exist = true;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					"ERROR on UPDATE/ADD: Tariff nr. is not valid.", "error", dbErrors));
		}
		return exist;

	}

	private boolean existInKodts8(String userName, String avg, String sekvId) { 
		boolean exist = false;
		List<Kodts8Dao> listTari = new ArrayList<Kodts8Dao>();
		listTari = this.kodts8DaoServices.findById(avg,sekvId ,dbErrors);
		if (listTari != null && listTari.size() > 0) {
			exist = true;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					"ERROR on UPDATE/ADD: Kode/avgift is not valid.", "error", dbErrors));
		}
		return exist;

	}	

	private boolean existInKodtsb(String userName, String slcref) {
		boolean exist = false;
		List<KodtsbDao> listKodtsb = new ArrayList<KodtsbDao>();
		listKodtsb = this.kodtsbDaoServices.findById(slcref ,dbErrors);
		if (listKodtsb != null && listKodtsb.size() > 0) {
			exist = true;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					"ERROR on UPDATE/ADD: Ref. is not valid.", "error", dbErrors));
		}
		return exist;
	}
	
	
	
	public boolean isValidInputForDelete(SadlDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getSlalfa()!=null && !"".equals(dao.getSlalfa())) &&  (dao.getSlknr()!=null && !"".equals(dao.getSlknr())) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
	
		return retval;
	}
	
	
}
