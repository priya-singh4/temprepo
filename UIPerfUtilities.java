package uiperformanceutilities.utilities;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UIPerfUtilities {
	
	private static final Logger logger =LoggerFactory.getLogger(UIPerfUtilities.class.getName());

	   public static String getCurrentTime(){
	        try 
	        { 
	        	 Date date = Calendar.getInstance().getTime();
			   DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.sss");
				   String result = dateFormat.format(date);
				   result = result.replace(":", "_");
	            result = result.replace(" ", "_");
	            result = result.replace(".", "_");
	            return result;

	        }
	        catch(Exception e)
	        {
	        	logger.error(e.getMessage(),e);  
	            
	            throw e;
	        }

	}
	   
	public String getDuration(long startTime, long endTime)
	{
		long milliSecs = endTime - startTime;
		
		
		long seconds = (long) (milliSecs / 1000) % 60 ;
		long minutes = (long) ((milliSecs / (1000*60)) % 60);
		long hours   = (long) ((milliSecs / (1000*60*60)) % 24);
		
		String strhours = String.valueOf(hours);
		String strminutes = String.valueOf(minutes);
		String strseconds = String.valueOf(seconds);
		String strMilliSeconds=String.valueOf(milliSecs);

		if(strhours.length()==1)
		{
			strhours="0"+strhours;
		}

		if(strminutes.length()==1)
		{
			strminutes="0"+strminutes;
		}

		if(strseconds.length()==1)
		{
			strseconds="0"+strseconds;
		}
		
		if(strMilliSeconds.length()==1)
		{
			strMilliSeconds="0"+strMilliSeconds;
		}

		return strhours +":" + strminutes+ ":" + strseconds+":"+strMilliSeconds;

	}

	
	public long getDurationinMillisecond(long startTime, long endTime)
	{

		long totalSecs = endTime - startTime;
		return totalSecs;
	}
	
	public String localDateTimeToString( long datetime)
	{ 
		Date date = new Date(datetime);
		Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS");
		return format.format(date);
    
	}
	
	public String addTimeinMilliseconds( long datetime,long timemilliseconds)
	{ 
		Date date = new Date(datetime);

		Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS");
		return format.format(date);
    
	}

}
