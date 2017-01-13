package no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.services;
import no.systema.jservices.tvinn.sad.z.maintenance.sad.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 * 
 */
public interface FirmDaoServices extends IDaoServices {
	
	/**
	 * Check if FIRMA has landkode NO
	 * 
	 * @return truu if, landkode = NU, else return false.
	 */
	public boolean isNorwegianFirm();
	
}
