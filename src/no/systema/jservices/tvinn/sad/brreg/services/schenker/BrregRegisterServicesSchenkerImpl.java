package no.systema.jservices.tvinn.sad.brreg.services.schenker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.csv.UnderEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.entites.EnhetRegisteretDataCheckDao;
import no.systema.jservices.tvinn.sad.brreg.proxy.OppslagEnhetRequest;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Enhet;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.HovedEnhet;
import no.systema.jservices.tvinn.sad.brreg.services.BrregRegisterServices;
import no.systema.main.util.ApplicationPropertiesUtil;


/**
 * This class is a replicate of BrregRegisterServicesImpl, with adjustment from DB Schenker demands.
 * 1. Alla kunder shall be be viewed.
 * 2. Organisasjonform is added. (This attribute is also included in standard excel).
 * 
 * This is also a test if this is valid tech. approch when customization is needed  per customer.
 * 
 * This class is referred to in  syjservicestn-service-schenker.xml
 * Configuration is done in web.xml.
 * 
 * 
 * @author Fredrik Möller
 * @date Dec 5, 2016
 *
 */
public class BrregRegisterServicesSchenkerImpl implements BrregRegisterServices {
	private static Logger logger = Logger.getLogger(BrregRegisterServicesSchenkerImpl.class.getName());
	private final static String ENHETS_REGISTERET_URL = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url");

	@Override
	public List getInvalidaKunderEnhetsRegisteret() {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		List<CundfDao> kunderForValideringList = cundfDaoServices.getListForQualityValidation();
		logger.info("KUNDERLIST, kunderForValideringList="+kunderForValideringList.size());
		checkedKunderList = getCheckedKunderList(kunderForValideringList);
		logger.info("CHECKED KUNDERLIST, checkedKunderList="+checkedKunderList.size());
		
		return checkedKunderList;
	}

	private List getCheckedKunderList(List kunderForValideringList) {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		EnhetRegisteretDataCheckDao checkedRecord = null;
		OppslagEnhetRequest oppslagHovedenhetRequest = new OppslagEnhetRequest(ENHETS_REGISTERET_URL, hovedEnhetCSVRepository, underEnhetCSVRepository);

		for (Iterator iterator = kunderForValideringList.iterator(); iterator.hasNext();) {
			CundfDao cundfDao = (CundfDao) iterator.next();
			Enhet enhet = null;
			if (passSanityCheck(cundfDao.getSyrg().trim())) {
				enhet = oppslagHovedenhetRequest.getEnhetRecord(new Integer(cundfDao.getSyrg().trim()), false);
			}
			checkedRecord = new EnhetRegisteretDataCheckDao();

			if (enhet == null) {
				//logger.info("ERROR: Enhet for " + cundfDao.getSyrg().trim() + " not found.");
				checkedRecord.setKundeNr(cundfDao.getKundnr());
				checkedRecord.setKundeNavn(cundfDao.getKnavn());
				checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
				checkedRecord.setExistsAsHovedEnhet("N");
				checkedRecord.setExistsAsUnderEnhet("N");

				checkedKunderList.add(checkedRecord);

			} else {
				if (isHovedEnhet(enhet)) {
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("J");
						checkedRecord.setKonkurs(enhet.getKonkurs());
						checkedRecord.setRegistrertIMvaregisteret(enhet.getRegistrertIMvaregisteret());
						checkedRecord.setUnderAvvikling(enhet.getUnderAvvikling());
						checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning(enhet.getUnderTvangsavviklingEllerTvangsopplosning());
						checkedRecord.setOrganisasjonsForm(enhet.getOrganisasjonsform());

						checkedKunderList.add(checkedRecord);

				} else { // is UnderEnhet
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("N");
						checkedRecord.setExistsAsUnderEnhet("J");
						if (enhet.getOverordnetEnhet() != null) { //Parent orgnr
							checkedRecord.setOverordnetEnhetOrgnr(enhet.getOverordnetEnhet().toString());
						}
						checkedRecord.setKonkurs(enhet.getKonkurs());
						checkedRecord.setRegistrertIMvaregisteret(enhet.getRegistrertIMvaregisteret());
						checkedRecord.setUnderAvvikling(enhet.getUnderAvvikling());
						checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning(enhet.getUnderTvangsavviklingEllerTvangsopplosning());
						checkedRecord.setOrganisasjonsForm(enhet.getOrganisasjonsform());

						checkedKunderList.add(checkedRecord);
				}
				
			}
		}

		return checkedKunderList;

	}


	private boolean isHovedEnhet(Enhet enhet) {
		boolean hovedenhet = false;
		if (enhet instanceof HovedEnhet) {
			hovedenhet = true;
		}
		return hovedenhet;
	}


	private boolean passSanityCheck(String orgNr) {
		if (!isNumber(orgNr)) {
			return false;
		}

		if(!hasCorrectLenght(orgNr)) {
			return false;
		}
		return true;
	}

	private boolean isNumber(String orgNr) {
		try {
			Integer.parseInt(orgNr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}		
	}

	private boolean hasCorrectLenght(String orgNr) {
		if (orgNr.length() > 9){  
			return false;
		} 
		return true;
	}	
	
	
	
	@Qualifier("cundfDaoServices")
	private CundfDaoServices cundfDaoServices;

	@Autowired
	@Required
	public void setCundfDaoServices(CundfDaoServices value) {
		this.cundfDaoServices = value;
	}

	public CundfDaoServices getCundfDaoServices() {
		return this.cundfDaoServices;
	}

	@Qualifier("hovedEnhetCSVRepository")
	private HovedEnhetCSVRepository hovedEnhetCSVRepository;

	@Autowired
	@Required
	public void setHovedEnhetCSVRepository(HovedEnhetCSVRepository value) {
		this.hovedEnhetCSVRepository = value;
	}

	public HovedEnhetCSVRepository getHovedEnhetCSVRepository() {
		return this.hovedEnhetCSVRepository;
	}	
	
	@Qualifier("underEnhetCSVRepository")
	private UnderEnhetCSVRepository underEnhetCSVRepository;

	@Autowired
	@Required
	public void setUnderEnhetCSVRepository(UnderEnhetCSVRepository value) {
		this.underEnhetCSVRepository = value;
	}

	public UnderEnhetCSVRepository getUnderEnhetCSVRepository() {
		return this.underEnhetCSVRepository;
	}	
	
}
