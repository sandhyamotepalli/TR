/**
 * 
 */
package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jnaga
 *
 */
public class DateUtility {
	public static java.util.Date parseStringToDate(String date) {
	     try {
	         SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy");
	         return ds.parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	
	public static java.util.Date parseStringTohyphenDate(String date) {
	     try {
	         SimpleDateFormat ds = new SimpleDateFormat("yyyy-mm-dd");
	         return ds.parse(date);
	     }catch (ParseException e) {
	         return null;
	     }
	  }
	
	public static String parseDateToString(java.util.Date date) {

	         SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy");
	         return ds.format(date);
	     
	  }
	
	public static String parseDateTohyphenString(java.util.Date date) {

        SimpleDateFormat ds = new SimpleDateFormat("yyyy-mm-dd");
        return ds.format(date);
    
 }
	
	public static Date getNextRunTime(int hour,int minute,int second)
	{
	   java.util.Date date = new java.util.Date();
	   Calendar startTime = Calendar.getInstance();
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   startTime.set(Calendar.HOUR_OF_DAY, hour);
	   startTime.set(Calendar.MINUTE, minute);
	   startTime.set(Calendar.SECOND, second);
	   startTime.set(Calendar.MILLISECOND, 0);

	   if(startTime.before(now) || startTime.equals(now))
	   {
	      startTime.add(Calendar.DATE, 1);
	   }

	   return startTime.getTime();
	}
	
	public static Date getNextRunTimeForRemindVisits(int day,int hour,int minute,int second)
	{
	   Calendar startTime = Calendar.getInstance();
	   Calendar now = Calendar.getInstance();
	   startTime.set(Calendar.DAY_OF_MONTH,day);
	   startTime.set(Calendar.HOUR_OF_DAY, hour);
	   startTime.set(Calendar.MINUTE, minute);
	   startTime.set(Calendar.SECOND, second);
	   startTime.set(Calendar.MILLISECOND, 0);

	   if(startTime.before(now) || startTime.equals(now))
	   {
	      startTime.add(Calendar.MONTH, 1);
	   }

	   return startTime.getTime();
	}
	
	public static String parseDateToDateAfter90Days(String date) {

		//System.out.println("Date before Addition: "+date);
		//Specifying date format that matches the given date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try{
		   //Setting the date to the given date
		   c.setTime(sdf.parse(date));
		}catch(ParseException e){
			e.printStackTrace();
		 }
		   
		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, 90);  
		//Date after adding the days to the given date
		String newDate = sdf.format(c.getTime());  
		//Displaying the new Date after addition of Days
		//System.out.println("Date after Addition: "+newDate);
		return newDate;
	   }
    

}
