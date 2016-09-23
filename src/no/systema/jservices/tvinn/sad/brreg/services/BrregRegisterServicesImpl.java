package no.systema.jservices.tvinn.sad.brreg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import no.systema.jservices.model.dao.entities.CusdfDao;
import no.systema.jservices.model.dao.services.CundfDaoServices;
import no.systema.jservices.tvinn.sad.brreg.entites.EnhetRegisteretDataCheckDao;
import no.systema.jservices.tvinn.sad.brreg.proxy.OppslagHovedenhetRequest;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;

public class BrregRegisterServicesImpl implements BrregRegisterServices {
	private static Logger logger = Logger.getLogger(BrregRegisterServicesImpl.class.getName());

	@Override
	public List getInvalidaKunderEnhetsRegisteret(String firmaKode) {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		List<CusdfDao> kunderForValideringList = cundfDaoServices.getListForQualityValidation(firmaKode);
		checkedKunderList = getCheckedKunderList(kunderForValideringList, firmaKode);
		return checkedKunderList;
	}

	private List getCheckedKunderList(List kunderForValideringList, String firmaKode) {
		List<EnhetRegisteretDataCheckDao> checkedKunderList = new ArrayList<EnhetRegisteretDataCheckDao>();
		EnhetRegisteretDataCheckDao checkedRecord = null;
		OppslagHovedenhetRequest oppslagHovedenhetRequest = new OppslagHovedenhetRequest();

		for (Iterator iterator = kunderForValideringList.iterator(); iterator.hasNext();) {
			CusdfDao cundfDao = (CusdfDao) iterator.next();
			// Get hovedenhet in data.brreg.no
			//logger.info("cundfDao="+ ReflectionToStringBuilder.toString(cundfDao));
			Hovedenhet hovedenhet = oppslagHovedenhetRequest.getHovedenhetRecord(cundfDao.getSyrg().trim());
			checkedRecord = new EnhetRegisteretDataCheckDao();

			if (hovedenhet == null) {
				logger.info("ERROR: Hovedenhet for "+cundfDao.getSyrg().trim()+" not found ");
				checkedRecord.setFirmaKode(firmaKode);
				checkedRecord.setKundeNr(cundfDao.getKundnr());
				checkedRecord.setKundeNavn(cundfDao.getKnavn());
				checkedRecord.setOrgNr(cundfDao.getSyrg().trim() + " ikke funnet i Brønnøysundregistrene.");
			} else {
				if (isKonkurs(hovedenhet) || !isMvareRegistret(hovedenhet) || isUnderAvvikling(hovedenhet)) {
					// Add checkRecord into checkedKundeList
					checkedRecord.setFirmaKode(firmaKode);

					checkedRecord.setKundeNr(cundfDao.getKundnr());
					checkedRecord.setKundeNavn(cundfDao.getKnavn());
					checkedRecord.setOrgNr(cundfDao.getSyrg());

					checkedRecord.setKonkurs(hovedenhet.getKonkurs());
					checkedRecord.setRegistrertIMvaregisteret(hovedenhet.getRegistrertIMvaregisteret());
					checkedRecord.setUnderAvvikling(hovedenhet.getUnderAvvikling());
					checkedRecord.setUnderTvangsavviklingEllerTvangsopplosning(
							hovedenhet.getUnderTvangsavviklingEllerTvangsopplosning());

				}

			}

			checkedKunderList.add(checkedRecord);
		}

		return checkedKunderList;

	}

	private boolean isUnderAvvikling(Hovedenhet hovedenhet) {
		boolean underAvvikling = false;
		if ("J".equals(hovedenhet.getUnderAvvikling())) {
			underAvvikling = true;
		}
		return underAvvikling;
	}

	private boolean isKonkurs(Hovedenhet hovedenhet) {
		boolean konkurs = false;
		if ("J".equals(hovedenhet.getKonkurs())) {
			konkurs = true;
		}
		return konkurs;
	}

	private boolean isMvareRegistret(Hovedenhet hovedenhet) {
		boolean mVareRegistret = false;
		if ("J".equals(hovedenhet.getRegistrertIMvaregisteret())) {
			mVareRegistret = true;
		}
		return mVareRegistret;
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

}
