package no.systema.jservices.model.dao.services;
import java.util.*;

public interface CundfDaoServices {
	public List getList();

	/**
	 * Retrieve a list of firmas kunder for quality validation check
	 * against brreg.no
	 * 
	 * @return a list of kundedata for validation check against brreg.no
	 */
	public List getListForQualityValidation();
}
