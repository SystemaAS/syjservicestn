package no.systema.jservices.tvinn.sad.brreg.proxy.entities;

/**
 * This class represent a entity found as UnderEnhet
 * 
 * @author Fredrik Möller
 * @date Nov 29, 2016
 *
 */
public class UnderEnhet extends Enhet {

	private boolean hasOverordnetEnhet;

	public boolean isHasOverordnetEnhet() {
		return hasOverordnetEnhet;
	}

	public void setHasOverordnetEnhet(boolean hasOverordnetEnhet) {
		this.hasOverordnetEnhet = hasOverordnetEnhet;
	}
	


}
