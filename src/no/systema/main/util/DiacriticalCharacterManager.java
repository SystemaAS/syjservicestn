package no.systema.main.util;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Wash UTF-8 Diacritical letters that corrupt AS400 db and all json mappers
 * 
 * If no Nordic letters are to be kept then use the apache lib: --> org.apache.commons.lang3.StringUtils.stripAccents(str) in one-liner instead of using this class
 * This will cover all special characters letters ...
 * 
 * TEST Strings
 * String str = "ÅÄÖåäö-ØøÆæ-ViluišĚÁ";
 * String extrem = "r̀r̂r̃r̈rʼŕřt̀t̂ẗţỳỹẙyʼy̎ýÿŷp̂p̈s̀s̃s̈s̊sʼs̸śŝŞşšd̂d̃d̈ďdʼḑf̈f̸g̀g̃g̈gʼģq́ĝǧḧĥj̈jʼḱk̂k̈k̸ǩl̂l̃l̈Łłẅẍc̃c̈c̊cʼc̸Çççćĉčv̂v̈vʼv̸b́b̧ǹn̂n̈n̊nʼńņňñm̀m̂m̃m̈m̊m̌ǵß";
 *
 * @author oscardelatorre
 * @date Nov-2023
 * 
 */
public class DiacriticalCharacterManager {
	//public static final Pattern DIACRITICS_AND_FRIENDS = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}\\u0591-\\u05C7]+");
	public static final Pattern DIACRITICS_AND_FRIENDS = Pattern.compile("[\\p{InCombiningDiacriticalMarks}]+");
	
	/**
	 * Main-ALT since we care about Scandinavia
	 * 
	 * Test String = "ÅÄÖåäö-ØøÆæ-ViluišĚÁ";
	 * Test String extrem = "r̀r̂r̃r̈rʼŕřt̀t̂ẗţỳỹẙyʼy̎ýÿŷp̂p̈s̀s̃s̈s̊sʼs̸śŝŞşšd̂d̃d̈ďdʼḑf̈f̸g̀g̃g̈gʼģq́ĝǧḧĥj̈jʼḱk̂k̈k̸ǩl̂l̃l̈Łłẅẍc̃c̈c̊cʼc̸Çççćĉčv̂v̈vʼv̸b́b̧ǹn̂n̈n̊nʼńņňñm̀m̂m̃m̈m̊m̌ǵß";
	 * 
	 * @param source
	 * @return
	 */
	public static String stripDiacriticsKeepNordicLetters(String source) {
		String result = "";
		
		StringBuilder sb = new StringBuilder();
		Map<Character, Character> map = new HashMap<Character, Character>();
		//Estland
		map.put('á', 'a'); map.put('Á', 'A'); 
		map.put('ě', 'e'); map.put('Ě', 'E');
		map.put('č', 'c'); map.put('Č', 'C');
		map.put('š', 's'); map.put('Š', 'S'); 
		map.put('ž', 'z'); map.put('Ž', 'Z');
		map.put('ž', 'z'); map.put('Ž', 'Z');
		//Poland
		map.put('ć', 'c'); map.put('ń', 'n');
		map.put('ó', 'o'); map.put('ś', 's');
		map.put('ź', 'z'); map.put('ą', 'a'); 
		map.put('ę', 'e'); 
		//extend to cover all the diacritics ... (a lot)
		
		
		char[] ch = source.toCharArray();
        // Traverse the character array
        for (int i = 0; i < ch.length; i++) {
        	if(map.get(ch[i])!=null) {
        		//replace
        		sb.append(map.get(ch[i]));
        	}else {
        		//keep original
        		sb.append(ch[i]);
        	}
        }
        result = sb.toString();
        
        return result;
	}
	/**
	 * ALT(1)
	 * Unfortunately åäöÅÄÖ disappear also. Use the above method instead if you don't care aboutn Scandinavian letters
	 * 
	 * @param input
	 * @return
	 */
	public static String removeAccents(String input) {
		
	    return normalize(input).replaceAll("\\p{M}", "");
	}
	
	/**
	 * ALT(2)
	 * Unfortunately åäöÅÄÖ disappear also. Use the above method instead if you don't care aboutn Scandinavian letters
	 * 
	 * @param str
	 * @return
	 */
	public static String stripDiacritics(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
	    return str;
	}
	/**
	 * ALT(3)
	 * Unfortunately åäöÅÄÖ disappear also. Use the above method instead if you don't care aboutn Scandinavian letters
	 * 
	 * @param str
	 * @return
	 */
	public static String stripDiacriticsApache(String str) {
	    return StringUtils.stripAccents(str);
	}
	
	
	
	
	
	private static String normalize(String input) {
	    return input == null ? null : Normalizer.normalize(input, Normalizer.Form.NFKD);
	}
	
	

}
