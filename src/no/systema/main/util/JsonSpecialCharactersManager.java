package no.systema.main.util;

/**
 * 
 * @author oscardelatorre
 * @date Feb 1, 2016
 * 
 */
public class JsonSpecialCharactersManager {
	
	public String cleanRecord(String value){
		String retval = value;
		if(retval!=null){
			//CHAR: |
			if(retval.contains("|")){
				retval = retval.replaceAll("\\|", " ");
			}
			//CHAR: \
			if(retval.contains("\\")){
				retval = retval.trim().replaceAll("\\\\", " ");
			}
			//CHAR: "
			if(retval.contains("\"")){
				retval = retval.replaceAll("\"", "'");
			}
			//ASCII: 172
			int a172 = 172;
			String aHak172 = new Character((char) a172).toString();
			if(retval.contains(aHak172)){
				retval = retval.replaceAll(aHak172, " ");
			}
			
		}
		
		return retval;
	}
	/**
	 * Trims only trailing spaces and respects the leading spaces.
	 * @param s
	 * @return
	 */
   public String trimEnd(String s){
      int i = s.length()-1;
      while(s.charAt(i)==' '){
    	  i--;
      }
      return s.substring(0, i+1);
   }
   
   
   /**
	 * to secure valid content with leading spaces
	 * @param s
	 * @return
	 */
	public boolean isValidContentWithLeadingSpaces(String s){
		boolean retval = true;
		try{
			int i = s.length()-1;
			while(s.charAt(i)==' '){
	    	  i--;
			}
			String tmp = s.substring(0, i+1);
		}catch(Exception e){
			retval = false;
		}
		
	      
	      
	    return retval;
		
	}
	
   
	
	
}
