package no.systema.jservices.tvinn.sad.z.maintenance.nctsexport.model.dao.services;

/**
 * Holding constant for select section from {@link TrkodfDaoServices}
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 7, 2016
 *
 */
public enum TransitKoder {

	TOLLSTED (106);
	
	private final int transitKode;

	TransitKoder(int transitKode) {
		this.transitKode = transitKode;
	}
	
	public int getTransitKode() {
		return transitKode;
	}
	
}
