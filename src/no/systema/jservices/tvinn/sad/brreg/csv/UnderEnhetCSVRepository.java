package no.systema.jservices.tvinn.sad.brreg.csv;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.UnderEnhet;

public interface UnderEnhetCSVRepository {

	/**
	 * Retrieve a UnderEnhet by orgnr
	 * 
	 * @param orgNr, Integer
	 * @return HovedEnhet
	 */
	public UnderEnhet get(Integer orgNr); 
	
	
	/**
	 * Set full qualifier for cvs-file.
	 * 
	 * @param String pathAndFileName, path and full filename
	 */
	public void setFile(String pathAndFileName);
}
