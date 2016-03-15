
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonSpecialCharactersManager;
public class SyjsTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SyjsTester main = new SyjsTester();
		main.runIt();
		
	}
	//sql compact execution
	private void runIt() {
		//TC1
		/*
		DateTimeManager dateTimeMgr = new DateTimeManager();
		try{
			String maxDate = "9999-12-31";
			String minDate = "2008-07-31";
			//String maxDate = "2016-01-01";
			//String minDate = "2016-07-31";
			
			if(dateTimeMgr.isValidCurrentDayBeforeLimit(maxDate, "yyyy-MM-dd")){
				System.out.println("OK - Max date");
				if(dateTimeMgr.isValidCurrentDayAfterLimit(minDate, "yyyy-MM-dd")){
					System.out.println("OK -  date");
				}else{
					System.out.println("ERROR - Invalid MIN date!");
				}
			}else{
				System.out.println("ERROR - Invalid MAX date!");
			}
					
		}catch(Exception e){
				
		}
		*/
		
		//TC2
		JsonSpecialCharactersManager mgr = new JsonSpecialCharactersManager();
		String a124 = new Character((char) 124).toString();
		String a172 = new Character((char) 172).toString();
		
		String value = "PETER \\PETERSONs " +  a124 + " inklusive" + a172;
		System.out.println(value);
		value = mgr.cleanRecord(value);
		System.out.println(value);
		
	}

}
