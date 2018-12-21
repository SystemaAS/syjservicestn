package no.systema.jservices.tvinn.sad.brreg.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import no.systema.jservices.common.util.CommonClientHttpRequestInterceptor;
import no.systema.jservices.common.util.CommonResponseErrorHandler;
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
	private final static String API = ApplicationPropertiesUtil.getProperty("no.brreg.data.from.api");
	
	static {
		logger.info("API="+API);
	}
	
	@Override
	public IEnhet get(String orgnr) throws RestClientException, IOException {
		IEnhet i_enhet = null;
		OppslagEnhetRequest oppslagHovedenhetRequest = new OppslagEnhetRequest(ENHETS_REGISTERET_URL, hovedEnhetCSVRepository, underEnhetCSVRepository, restTemplate);
		i_enhet = oppslagHovedenhetRequest.getEnhetRecord(orgnr.trim(), true);

		return i_enhet;
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

	private List getCheckedKunderList(List kunderForValideringList) throws RestClientException, IOException {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		EnhetRegisteretDataCheckDao checkedRecord = null;
		OppslagEnhetRequest oppslagHovedenhetRequest = new OppslagEnhetRequest(ENHETS_REGISTERET_URL, hovedEnhetCSVRepository, underEnhetCSVRepository, restTemplate);

		for (Iterator iterator = kunderForValideringList.iterator(); iterator.hasNext();) {
			CundfDao cundfDao = (CundfDao) iterator.next();
			IEnhet i_enhet = null;
			if (passSanityCheck(cundfDao.getSyrg().trim())) {
				if (Boolean.valueOf(API)) {
					i_enhet = oppslagHovedenhetRequest.getEnhetRecord(cundfDao.getSyrg().trim(), true);
//					logger.info("i_enhet=" + ReflectionToStringBuilder.toString(i_enhet, ToStringStyle.MULTI_LINE_STYLE));
				} else {
					i_enhet = oppslagHovedenhetRequest.getEnhetRecord(cundfDao.getSyrg().trim(), false);
				}
			}
			checkedRecord = new EnhetRegisteretDataCheckDao();

			if (i_enhet == null) {
//				logger.error("ERROR: Enhet for " + cundfDao.getSyrg().trim() + " not found.");
				checkedRecord.setKundeNr(cundfDao.getKundnr());
				checkedRecord.setKundeNavn(cundfDao.getKnavn());
				checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
				checkedRecord.setExistsAsHovedEnhet("N");
				checkedRecord.setExistsAsUnderEnhet("N");

				checkedKunderList.add(checkedRecord);

			} else {
				if (isHovedEnhet(i_enhet)) {
//					logger.info("Enhet for " + cundfDao.getSyrg().trim() + " found!");
					i_enhet = (Enhet) i_enhet;
					// Sanity check, orgnr can have been deleted, then all values set to <null>, here check on konkurs
					if (i_enhet.getKonkurs() == null) {
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("-");
						checkedRecord.setExistsAsUnderEnhet("-");

						checkedKunderList.add(checkedRecord);

					} else if (i_enhet.getKonkurs() || !i_enhet.getRegistrertIMvaregisteret() || i_enhet.getUnderAvvikling() || i_enhet.getUnderTvangsavviklingEllerTvangsopplosning()) { // UnderEnhet
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("J");
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

						checkedKunderList.add(checkedRecord);

					}

				} else { // is UnderEnhet
//					logger.info("Underenhet for " + cundfDao.getSyrg().trim() + " found!");
					i_enhet = (UnderEnhet) i_enhet;
					// Sanity check, orgnr can have been deleted, then all values set to <null>, here check on konkur
					if (i_enhet.getKonkurs() == null) {
						checkedRecord.setKundeNr(cundfDao.getKundnr());
						checkedRecord.setKundeNavn(cundfDao.getKnavn());
						checkedRecord.setOrgNr(cundfDao.getSyrg().trim());
						checkedRecord.setExistsAsHovedEnhet("-");
						checkedRecord.setExistsAsUnderEnhet("-");

						checkedKunderList.add(checkedRecord);

					} else if (i_enhet.getKonkurs() || !i_enhet.getRegistrertIMvaregisteret() || i_enhet.getUnderAvvikling() || i_enhet.getUnderTvangsavviklingEllerTvangsopplosning()) {
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

						checkedKunderList.add(checkedRecord);

					}

				}

			}
		}
		
		return checkedKunderList;

	}


	private boolean isHovedEnhet(IEnhet enhet) {
		boolean isEnhet = false;
		if (enhet instanceof Enhet) {
			isEnhet = true;
		}
		return isEnhet;
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
		restTemplate.setInterceptors(Arrays.asList(new CommonClientHttpRequestInterceptor()));
		restTemplate.setErrorHandler(new CommonResponseErrorHandler());
		return this.restTemplate;
	}	
    	
	

}
