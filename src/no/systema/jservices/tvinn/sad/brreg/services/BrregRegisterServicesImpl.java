package no.systema.jservices.tvinn.sad.brreg.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.brreg.proxy.entities.IEnhet;
import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;
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
	public Enhet get(String orgnr) throws RestClientException, IOException {
		IEnhet enhet = null;
		OppslagEnhetRequest oppslagHovedenhetRequest = new OppslagEnhetRequest(ENHETS_REGISTERET_URL, hovedEnhetCSVRepository, underEnhetCSVRepository, restTemplate);
		enhet = oppslagHovedenhetRequest.getEnhetRecord(orgnr.trim(), true);

		return (Enhet) enhet;
	}	
	
	@Override
	public List getInvalidaKunderEnhetsRegisteret()  throws RestClientException,  IOException {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		List<CundfDao> kunderForValideringList = cundfDaoServices.getListForQualityValidation();
		logger.info("KUNDERLIST, kunderForValideringList="+kunderForValideringList.size());
		checkedKunderList = getCheckedKunderList(kunderForValideringList);
		logger.info("CHECKED KUNDERLIST, checkedKunderList="+checkedKunderList.size());
		
		return checkedKunderList;
	}

	private List getCheckedKunderList(List kunderForValideringList) throws RestClientException,  IOException {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		EnhetRegisteretDataCheckDao checkedRecord = null;
		OppslagEnhetRequest oppslagHovedenhetRequest = new OppslagEnhetRequest(ENHETS_REGISTERET_URL, hovedEnhetCSVRepository, underEnhetCSVRepository, restTemplate);

		for (Iterator iterator = kunderForValideringList.iterator(); iterator.hasNext();) {
			CundfDao cundfDao = (CundfDao) iterator.next();
			IEnhet i_enhet = null;
			if (passSanityCheck(cundfDao.getSyrg().trim())) {
				i_enhet = oppslagHovedenhetRequest.getEnhetRecord(cundfDao.getSyrg().trim(), false);
			}
			checkedRecord = new EnhetRegisteretDataCheckDao();

			if (i_enhet == null) {
				//logger.info("ERROR: Enhet for " + cundfDao.getSyrg().trim() + " not found.");
				checkedRecord.setKundeNr(cundfDao.getKundnr());
				checkedRecord.setKundeNavn(cundfDao.getKnavn());
				checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
				checkedRecord.setExistsAsHovedEnhet("N");
				checkedRecord.setExistsAsUnderEnhet("N");

				checkedKunderList.add(checkedRecord);

			} else {
				if (isHovedEnhet(i_enhet)) {
					i_enhet = (Enhet) i_enhet;
					if (isKonkurs(i_enhet) || !isMvareRegistret(i_enhet) || isUnderAvvikling(i_enhet)) {
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("J");
						logger.info("enhet.getKonkurs()="+i_enhet.getKonkurs());
						if (i_enhet.getKonkurs()) {
							checkedRecord.setKonkurs("J");
						} else {
							checkedRecord.setKonkurs("N");
						}
						if (i_enhet.getRegistrertIMvaregisteret()) {
							checkedRecord.setRegistrertIMvaregisteret("J");
						} else {
							checkedRecord.setRegistrertIMvaregisteret("N");
						}
						if (i_enhet.getUnderAvvikling()) {
							checkedRecord.setUnderAvvikling("J");
						} else {
							checkedRecord.setUnderAvvikling("N");
						}
						if (i_enhet.getUnderTvangsavviklingEllerTvangsopplosning()) {
							checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning("J");
						} else {
							checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning("N");
						}
//						checkedRecord.setOrganisasjonsForm(enhet.getOrganisasjonsform());

						checkedKunderList.add(checkedRecord);
					}

				} else { // is UnderEnhet
					i_enhet = (UnderEnhet) i_enhet;
					if (isKonkurs(i_enhet) || !isMvareRegistret(i_enhet) || isUnderAvvikling(i_enhet)) {  //UnderEnhet could have been decorated
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("N");
						checkedRecord.setExistsAsUnderEnhet("J");
						checkedRecord.setOverordnetEnhetOrgnr(i_enhet.getOverordnetEnhet());
						if (i_enhet.getKonkurs()) {
							checkedRecord.setKonkurs("J");
						} else {
							checkedRecord.setKonkurs("N");
						}
						if (i_enhet.getRegistrertIMvaregisteret()) {
							checkedRecord.setRegistrertIMvaregisteret("J");
						} else {
							checkedRecord.setRegistrertIMvaregisteret("N");
						}
						if (i_enhet.getUnderAvvikling()) {
							checkedRecord.setUnderAvvikling("J");
						} else {
							checkedRecord.setUnderAvvikling("N");
						}
						if (i_enhet.getUnderTvangsavviklingEllerTvangsopplosning()) {
							checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning("J");
						} else {
							checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning("N");
						}
//						checkedRecord.setOrganisasjonsForm(enhet.getOrganisasjonsform());

						checkedKunderList.add(checkedRecord);
					}
				}
				
			}
		}

		
		hovedEnhetCSVRepository.clearMap();
		underEnhetCSVRepository.clearMap();
		
		return checkedKunderList;

	}


	private boolean isHovedEnhet(IEnhet enhet) {
		boolean isEnhet = false;
		if (enhet instanceof Enhet) {
			isEnhet = true;
		}
		return isEnhet;
	}

	private boolean isUnderAvvikling(IEnhet enhet) {
		boolean underAvvikling = false;
		if (enhet.getUnderAvvikling()) {
			underAvvikling = true;
		}
		return underAvvikling;
	}

	private boolean isKonkurs(IEnhet enhet) {
		boolean konkurs = false;
		if (enhet.getKonkurs()) {
			konkurs = true;
		}
		return konkurs;
	}

	private boolean isMvareRegistret(IEnhet enhet) {
		boolean mVareRegistret = false;
		if (enhet.getRegistrertIMvaregisteret()) {
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
	
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	@Autowired
	@Required
	public void setRestTemplate(RestTemplate value) {
		this.restTemplate = value;
	}
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}	
    	
	

}
