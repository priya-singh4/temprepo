package uiperformanceutilities.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import uiperformanceutilities.utilities.NavigationTimeHelper;
import uiperformanceutilities.utilities.UIPerfUtilities;

public class UIPerformanceModel {

	public String sourceScreen="";
	public String destinationScreen="";
	public String sourceDestinationScreen="";
	public int screenCount=0;
	public String responseTime="";
	public long responseTimeMillisecond=0;
	public String testCaseName="";
	public String moduleName="";
	public String browserName="";
	public String startTime="";
	public String endtime="";
	public long pageLoadTime=0;
	public long ttfb=0;
	public long endtoendRespTime=0;
	
	public String navigationTimeDetails="";
	public long connectEnd=0;
	public String connectStart="";
	public String domComplete="";
	public String domContentLoadedEventEnd="";
	public String domContentLoadedEventStart="";
	public String domInteractive="";
	public String domLoading="";
	public String domainLookupEnd="";
	public String domainLookupStart="";
	public String fetchStart="";
	public String loadEventEnd="";
	public String loadEventStart="";
	public String navigationStart="";
	public String redirectEnd="";
	public String redirectStart="";
	public String requestStart="";
	public String responseEnd="";
	public String responseStart="";
	public String secureConnectionStart="";
	public String unloadEventEnd="";
	public String unloadEventStart="";
	public ArrayList<String> entries = new ArrayList<>();
	public ArrayList<EntryModel> entryModel = new ArrayList<>();
	public ArrayList<EntryModel> lstEntryModel = new ArrayList<>();
	public HashMap<String,ArrayList<EntryModel>> mapEntryModel = new HashMap<>();
	
	NavigationTimeHelper navigationTimeHelper = new NavigationTimeHelper(); 
	
	public UIPerformanceModel()
	{
		throw new UnsupportedOperationException("Default constructor should not be used directly.");
	}
	
	
	public UIPerformanceModel addUiPerfModel(String sourceScreen, String testCaseName, String moduleName, String browserName,
			long startTime,WebDriver driver)
	{
		
		long endTime= System.currentTimeMillis();
		UIPerfUtilities uiPerfUtilities = new UIPerfUtilities();
		
		UIPerformanceModel uiPerfModel= new UIPerformanceModel();
		
		uiPerfModel.sourceScreen=sourceScreen;
		uiPerfModel.destinationScreen=destinationScreen;
		uiPerfModel.sourceDestinationScreen=sourceScreen;
		uiPerfModel.screenCount=screenCount;
		uiPerfModel.testCaseName=testCaseName;
		uiPerfModel.moduleName=moduleName;
		uiPerfModel.browserName=browserName;
		uiPerfModel.startTime=uiPerfUtilities.localDateTimeToString(startTime);
		uiPerfModel.endtime=uiPerfUtilities.localDateTimeToString(endTime);
		uiPerfModel.pageLoadTime=navigationTimeHelper.getPageLoadTime(driver);
		uiPerfModel.ttfb=navigationTimeHelper.getTTFB(driver);
		uiPerfModel.endtoendRespTime=navigationTimeHelper.getEndtoendRespTime(driver);
		uiPerfModel.entries=navigationTimeHelper.getPerfEntries(driver);
		uiPerfModel.lstEntryModel=navigationTimeHelper.getEntryModelDetails(driver,startTime);
		uiPerfModel.mapEntryModel=navigationTimeHelper.groupedEntryMap(uiPerfModel.lstEntryModel);
		
		uiPerfModel.responseTimeMillisecond=uiPerfUtilities.getDurationinMillisecond(startTime, endTime);
		uiPerfModel.responseTime=uiPerfUtilities.getDuration(startTime, endTime);
		return uiPerfModel;
	}
	
}
