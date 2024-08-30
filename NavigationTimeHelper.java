package uiperformanceutilities.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import uiperformanceutilities.model.EntryModel;


import org.json.JSONException;
import org.json.JSONObject;




public class NavigationTimeHelper {
	
	private static final Logger logger =LoggerFactory.getLogger(NavigationTimeHelper.class.getName());

	  Map<String, Object> timings = null; 
	  private final String javaScriptForPerformance = "var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {};var timings = performance.timing || {};return timings;"; 
	   
	  public String getAllTiming(WebDriver driver) throws JSONException{
		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
	        var timings = jsrunner.executeScript(javaScriptForPerformance); 
	        
	        String strTimings = timings.toString();
	        String s1 = strTimings.replace('=', ':');
	        JSONObject json = new JSONObject(s1); 
 
	        return timings.toString();	  
	        
	    }
	  
	  public class RootGetAllTimings{
		  
		  public RootGetAllTimings() {
			  
			  throw new UnsupportedOperationException("Default constructor should not be used directly.");
			  
		  }
		  
		  	@JsonProperty("unloadEventEnd")
		    public int unloadEventEnd;
		  	@JsonProperty("responseEnd")
		    public long responseEnd;
		  	@JsonProperty("responseStart")
		    public long responseStart;
		  	@JsonProperty("domInteractive")
		    public long domInteractive;
		  	@JsonProperty("domainLookupEnd")
		    public long domainLookupEnd;
		  	@JsonProperty("unloadEventStart")
		    public int unloadEventStart;
		  	@JsonProperty("domComplete")
		    public long domComplete;
		  	@JsonProperty("domContentLoadedEventStart")
		    public long domContentLoadedEventStart;
		  	@JsonProperty("domainLookupStart")
		    public long domainLookupStart;
		  	@JsonProperty("redirectEnd")
		    public int redirectEnd;
		  	@JsonProperty("redirectStart")
		    public int redirectStart;
		  	@JsonProperty("connectEnd")
		    public long connectEnd;
		  	@JsonProperty("connectStart")
		    public long connectStart;
		  	@JsonProperty("loadEventStart")
		    public long loadEventStart;
		  	@JsonProperty("navigationStart")
		    public long navigationStart;
		  	@JsonProperty("requestStart")
		    public long requestStart;
		  	@JsonProperty("secureConnectionStart")
		    public long secureConnectionStart;
		  	@JsonProperty("fetchStart")
		    public long fetchStart;
		  	@JsonProperty("domContentLoadedEventEnd")
		    public long domContentLoadedEventEnd;
		  	@JsonProperty("domLoading")
		    public long domLoading;
		  	@JsonProperty("loadEventEnd")
		    public long loadEventEnd;
		}

	   public long getPageLoadTime(WebDriver driver) {
		   
		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
		   
		   long pageLoadTime = (Long)jsrunner.executeScript("return (window.performance.timing.loadEventEnd-window.performance.timing.responseStart)");
	
		   return pageLoadTime;
	   }
	   
	   public long getTTFB(WebDriver driver) {
		   
		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
		   
		   long ttfb= (Long)jsrunner.executeScript("return (window.performance.timing.responseStart-window.performance.timing.navigationStart)");
		   return ttfb;
	   }
	   
	   public long getEndtoendRespTime(WebDriver driver) {
		   
		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
		   
           long endtoendRespTime= (Long)jsrunner.executeScript("return (window.performance.timing.loadEventEnd-window.performance.timing.navigationStart)");
           return endtoendRespTime;
           
	   }
	   
	   public String getDomComplete(WebDriver driver) {
		   
		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;  
		   
           var domComplete = jsrunner.executeScript("return window.performance.getEntries()");
           
           String dom = domComplete.toString();
	       String s2 = dom.replace('=', ':');
	   
	       
           return domComplete.toString();	   
	   }
	   
	   public ArrayList<String> getPerfEntries(WebDriver driver) {
		   
		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
		   var domComplete = jsrunner.executeScript("return window.performance.getEntries().length");
			 
			 int entriesDOM = Integer.parseInt(domComplete.toString());
			 
			 ArrayList<String> entries = new ArrayList<>();	 
			 ArrayList<String> entriesNameString = new ArrayList<>();
			 
			 for (int i =0;i<entriesDOM;i++)
			 {
				 try {
					 var domNameEntries = jsrunner.executeScript("return window.performance.getEntries()["+i+"].name");
					 entriesNameString.add(domNameEntries.toString());
				 }
				 catch(Exception e) {
					e.printStackTrace();
				 }
				
			 } 		 
			 entries.add("Name|EntryType|Duration(ms)|InitiatorType|Size");
			for(int j=0;j<entriesNameString.size();j++) {
				
				String result = "";
			
				
				
				boolean resultCheck = true;
				
	            var pageLoadTime = jsrunner.executeScript("return (window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].loadEventEnd-window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].responseStart)");
	            var ttfb= jsrunner.executeScript("return (window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].responseStart-window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].navigationStart)"); 
	            var endtoendRespTime= jsrunner.executeScript("return (window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].loadEventEnd-window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].navigationStart)");
	            var duration =  jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].duration");
	            var entryType =  jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].entryType");
	            var initiatorType =  jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].initiatorType");
	            var size =  jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].transferSize");
	            
	            String  strEntryType="";
	            String  strInitiatorType="";
	            String  strSize="";
	            String strDuration="";
	            
	            if(entryType==null)
	            {
	            	strEntryType="N/A";
	            }
	            else
	            {
	            	strEntryType=entryType.toString();
	            }
	            
	            if(initiatorType==null)
	            {
	            	 strInitiatorType="N/A";
	            }
	            else
	            {
	            	strInitiatorType=initiatorType.toString();
	            }

	            if(size==null)
	            {
	            	  strSize="N/A";
	            }
	            else
	            {
	            	strSize=size.toString();
	            }
	            if(duration==null)
	            {
	            	  strDuration="N/A";
	            }
	            else
	            {
	            	strDuration=duration.toString();
	            }
	            

	            if(resultCheck == true) {
	            	
	            	 
	   				result = entriesNameString.get(j) + " | " + strEntryType + " | " +strDuration + " | " +strInitiatorType + " | " +strSize;
	            }
	         
	            entries.add(result);
	            logger.info("{}",entries);
	            
			}
	            return entries;
	            
			}
	   
	
	   
	   public HashMap <String,ArrayList<EntryModel>> groupedEntryMap(ArrayList<EntryModel> entryModelList)
	   {
		   HashMap <String,ArrayList<EntryModel>> mapEntryModel= new HashMap <>();
		   for(EntryModel entryModel: entryModelList)
		   {
			   if(mapEntryModel.containsKey(entryModel.initiatorType))
			   {
				   ArrayList<EntryModel> lclentryModelList= mapEntryModel.get(entryModel.initiatorType);
				   lclentryModelList.add(entryModel);
				   mapEntryModel.put(entryModel.initiatorType, lclentryModelList);   
			   }
			   
			   else
			   {
				   ArrayList<EntryModel> lclentryModelList= new ArrayList<>();
				   lclentryModelList.add(entryModel);
				   mapEntryModel.put(entryModel.initiatorType, lclentryModelList);
			   }
		   }
		   
		   return mapEntryModel;
	   }
	   
	   
	   
	   
	   public ArrayList<EntryModel> getEntryModelDetails(WebDriver driver,long starttime)
	   {

		   ArrayList<EntryModel> entryModelList= new ArrayList<>();
		   UIPerfUtilities uiperfutil= new UIPerfUtilities();
		   

		   JavascriptExecutor jsrunner = (JavascriptExecutor) driver;
		   var domComplete = jsrunner.executeScript("return window.performance.getEntries().length");
			 
			 int entriesDOM = Integer.parseInt(domComplete.toString());
			 

			 ArrayList<String> entriesNameString = new ArrayList<>();
			 
			 for (int i =0;i<entriesDOM;i++)
			 {
				 try {
					 var domNameEntries = jsrunner.executeScript("return window.performance.getEntries()["+i+"].name");
					 entriesNameString.add(domNameEntries.toString());
				 }
				 catch(Exception e) {
					e.printStackTrace();
				 }
				
			 } 		 
			 
			for(int j=0;j<entriesNameString.size();j++) 
			{
				
			
				EntryModel entryModel= new EntryModel();
	            var duration = jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].duration");
	            var entryType = jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].entryType");
	            var initiatorType = jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].initiatorType");
	            var startTime=jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].startTime");
	            var transferSize=jsrunner.executeScript("return window.performance.getEntriesByName("+"'" +entriesNameString.get(j)+"'"+ ")[0].transferSize");	        
	            
	            long starTime=0;
          		long durationLong=0;


	            
	            String  strEntryType="";
	            String  strInitiatorType="";
	            String  strSize="";

          		if(entryType==null)
	            {
	            	strEntryType="N/A";
	            }
	            else
	            {
	            	strEntryType=entryType.toString();
	            }
	            
	            if(initiatorType==null)
	            {
	            	 strInitiatorType="N/A";
	            }
	            else
	            {
	            	strInitiatorType=initiatorType.toString();
	            }

	            if(transferSize==null)
	            {
	            	  strSize="N/A";
	            }
	            else
	            {
	            	strSize=transferSize.toString();
	            }
	            

          		if(startTime!=null && (startTime.toString().equals("0")==false))
	            { 
	            	starTime=Long.parseLong(startTime.toString().split("\\.")[0]);
	            	
	            }
	            
	            if(duration!=null && (duration.toString().equals("0")==false))
	            { 
	            	logger.info("Duration: {}", duration);
	            	durationLong=Long.parseLong(duration.toString().split("\\.")[0]);
	            }
	            
	            entryModel.entryName=entriesNameString.get(j);
	            entryModel.duration=String.valueOf(durationLong);
	            entryModel.entryType=strEntryType;
	            entryModel.initiatorType=strInitiatorType;
	            entryModel.transferSize=strSize;
	            entryModel.startTime=uiperfutil.addTimeinMilliseconds(starttime, starTime);
	            entryModel.endTime=uiperfutil.addTimeinMilliseconds(starttime,starTime+durationLong);
	            
	            
	            
	            
	            entryModelList.add(entryModel);
	            
			}
	            return entryModelList;

		   
	   }
}
