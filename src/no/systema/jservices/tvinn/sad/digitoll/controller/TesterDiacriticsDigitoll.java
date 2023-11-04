package no.systema.jservices.tvinn.sad.digitoll.controller;

import org.apache.commons.lang3.StringUtils;

import no.systema.main.util.DiacriticalCharacterManager;


public class TesterDiacriticsDigitoll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "ÅÄÖåäö-ØøÆæ-ViluišĚÁ";
		String extrem = "r̀r̂r̃r̈rʼŕřt̀t̂ẗţỳỹẙyʼy̎ýÿŷp̂p̈s̀s̃s̈s̊sʼs̸śŝŞşšd̂d̃d̈ďdʼḑf̈f̸g̀g̃g̈gʼģq́ĝǧḧĥj̈jʼḱk̂k̈k̸ǩl̂l̃l̈Łłẅẍc̃c̈c̊cʼc̸Çççćĉčv̂v̈vʼv̸b́b̧ǹn̂n̈n̊nʼńņňñm̀m̂m̃m̈m̊m̌ǵß";
		String newStr = DiacriticalCharacterManager.stripDiacriticsKeepNordicLetters(str);
		System.out.println(newStr);
		
		String other = DiacriticalCharacterManager.removeAccents(str);
		System.out.println(other);
		
		String otherX = DiacriticalCharacterManager.stripDiacriticsApache(str);
		System.out.println(otherX);
		
		
	}
}
