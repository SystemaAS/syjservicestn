package no.systema.jservices.tvinn.sad.brreg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.HovedEnhet;
import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.csv.UnderEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.entites.EnhetRegisteretDataCheckDao;
import no.systema.jservices.tvinn.sad.brreg.proxy.OppslagEnhetRequest;
import no.systema.main.util.ApplicationPropertiesUtil;


public class BrregRegisterServicesImpl implements BrregRegisterServices {
	private static Logger logger = Logger.getLogger(BrregRegisterServicesImpl.class.getName());
	private final static String ENHETS_REGISTERET_URL = ApplicationPropertiesUtil.getProperty("no.brreg.data.enhetsregisteret.url");

	
	@Override
	public Enhet get(String orgnr) {
		Enhet enhet = null;
		OppslagEnhetRequest oppslagHovedenhetRequest = new OppslagEnhetRequest(ENHETS_REGISTERET_URL, hovedEnhetCSVRepository, underEnhetCSVRepository);
		enhet = oppslagHovedenhetRequest.getEnhetRecord(new Integer(orgnr.trim()), false);
		return enhet;
	}	
	
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
					if (isKonkurs(enhet) || !isMvareRegistret(enhet) || isUnderAvvikling(enhet)) {
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
					}

				} else { // is UnderEnhet
					if (isKonkurs(enhet) || !isMvareRegistret(enhet) || isUnderAvvikling(enhet)) {  //UnderEnhet could have been decorated
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

	private boolean isUnderAvvikling(Enhet enhet) {
		boolean underAvvikling = false;
		if ("J".equals(enhet.getUnderAvvikling())) {
			underAvvikling = true;
		}
		return underAvvikling;
	}

	private boolean isKonkurs(Enhet enhet) {
		boolean konkurs = false;
		if ("J".equals(enhet.getKonkurs())) {
			konkurs = true;
		}
		return konkurs;
	}

	private boolean isMvareRegistret(Enhet enhet) {
		boolean mVareRegistret = false;
		if ("J".equals(enhet.getRegistrertIMvaregisteret())) {
			mVareRegistret = true;
		}
		return mVareRegistret;
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
	public void setHovedEnhetCSVRepository(HovedEnhetCSVRepository value) {this.hovedEnhetCSVRepository = value;}
	public HovedEnhetCSVRepository getHovedEnhetCSVRepository() { return this.hovedEnhetCSVRepository; }	
	

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
