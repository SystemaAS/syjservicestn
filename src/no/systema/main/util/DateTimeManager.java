/**
 * 
 */
package no.systema.main.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class to manage date issues
 * 
 * @author oscardelatorre
 * @date Mar 28, 2014
 * 
 */
public class DateTimeManager {
	public static final String ISO_FORMAT = "yyyyMMdd";
	public static final String NO_FORMAT = "ddMMyy";

	public static final String DB_FORMAT = "yyyy-MM-dd";
	/**
	 * Gets the current ISO date
	 * @return
	 */
	public String getCurrentDate_ISO(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * 
	 * @return
	 */
	public String getCurrentDate(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DB_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	
	public String getCurrentYear(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	public String getCurrentMonth(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * The method gets a specific month backwards from the current day (today)
	 * @param numberOfMonths (+)=months forward, (-)=months backwards
	 * @return
	 */
	public String getSpecificMonthFrom_CurrentDate_ISO(int numberOfMonths){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			cal.add(Calendar.MONTH, numberOfMonths);
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * Gets the current NO date
	 * @return
	 */
	public String getCurrentDate_NO(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(NO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	
	/**
	 * 
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean isValidCurrentDayBeforeLimit( String userValue, String dateMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateMask);
		try{
			if(userValue!=null && dateMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate());
					if(today.equals(userDate) || today.before(userDate)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean isValidCurrentDayAfterLimit( String userValue, String dateMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateMask);
		try{
			if(userValue!=null && dateMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate());
					if(today.equals(userDate) || today.after(userDate)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * The method compares with current date and compares it with the user value.
	 * A valid forward time should always return a positive value.
	 * @param userValue
	 * @return
	 * 
	 */
	public boolean isValidForwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidBackwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.before(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	
	/**
	 * The method compares with current time-stamp (now) in milliseconds and compare it with the use value.
	 * A valid forward time should always return a positive value.
	 * @param userValue
	 * @return
	 */
	public boolean isValidForwardTime( String userValue, String dateMask){
		boolean retval = false;
		Calendar userCalendar = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		Long diff = 0L;
		try{
			if(userValue!=null && dateMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateMask.length()>=2){
					Date userDate = new SimpleDateFormat(dateMask).parse(userValue);
					userCalendar.setTime(userDate);
					diff = userCalendar.getTimeInMillis() - calendar.getTimeInMillis();
					//System.out.println("Milliseconds diff: " + diff);
					if (diff>0){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean currentDayBeforeUserDate( String userValue, String dateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date userDate = formatter.parse(userValue);
			Date today = formatter.parse(this.getCurrentDate_ISO());
			if(today.before(userDate)){
				retval = true;
			}
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @param dateMask
	 * @return
	 */
	public String getDateFormatted_ISO(String value, String sourceDateMask){
		String newDateString = null;
		final String OLD_FORMAT = sourceDateMask;
		try{
			String oldDateString = value;
			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			Date d = sdf.parse(oldDateString);
			
			sdf.applyPattern(ISO_FORMAT);
			newDateString = sdf.format(d);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return newDateString;
	}
	
	/**
	 * 
	 * @param value
	 * @param dateMask
	 * @return
	 */
	public String getDateFormatted_NO(String value, String sourceDateMask){
		String newDateString = null;
		final String OLD_FORMAT = sourceDateMask;
		
		try{
			String oldDateString = value;

			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			Date d = sdf.parse(oldDateString);
			sdf.applyPattern(NO_FORMAT);
			newDateString = sdf.format(d);
			//System.out.println(newDateString);
						
		}catch(Exception e){
			e.printStackTrace();
		}

		return newDateString;
	}
	
	/**
	 * User date: 29 = yyyyMM29 (current year and current month) ISO
	 * User date: 2910 = yyy1029 (current year + user month + user day) ISO
	 * 
	 * @param userDate
	 * @return
	 */
	public String adjustUserDateToISODate(String userDate){
		String retval = userDate;
		if(userDate!=null && !"".equals(userDate) && userDate.length()<8){
			if(userDate.length()==2){
				retval = this.getCurrentYear() + this.getCurrentMonth() + userDate;
			}else if (userDate.length()==4){
				String day = userDate.substring(0,2);String month = userDate.substring(2);
				retval = this.getCurrentYear() + month + day;
			}else{
				//nothing
			}
		}
		return retval;
	}

	/**
	 * User time: 23 = 2300
	 * 
	 * @param userTime
	 * @return
	 */
	public String adjustUserTimeToHHmm(String userTime){
		String MINUTES_SUFFIX = "00";
		String HOUR_PREFIX = "0";
		String retval = userTime;
		if(userTime!=null && !"".equals(userTime) && userTime.length()<4){
			if(userTime.length()==2){
				retval = userTime + MINUTES_SUFFIX;
			}else if(userTime.length()==1){
				retval = HOUR_PREFIX + userTime + MINUTES_SUFFIX;
			}else{
				//nothing
			}
		}
		return retval;
	}
}
