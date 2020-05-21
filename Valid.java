package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Valid {
	 public static void main(String args[]){
			//Given Date in String format
		    Date today = new Date();
		   System.out.println("Date today: "+today);
			String oldDate = "04-05-2020";  
			System.out.println("Date before Addition: "+oldDate);
			//Specifying date format that matches the given date
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			try{
			   //Setting the date to the given date
			   c.setTime(sdf.parse(oldDate));
			}catch(ParseException e){
				e.printStackTrace();
			 }
			   
			//Number of Days to add
			c.add(Calendar.DAY_OF_MONTH, 90);  
			//Date after adding the days to the given date
			String newDate = sdf.format(c.getTime());  
			//Displaying the new Date after addition of Days
			System.out.println("Date after Addition: "+newDate);
		   }
}
