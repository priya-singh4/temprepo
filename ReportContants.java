package report_utilities.constants;

import java.util.ArrayList;
import java.util.List;

import report_utilities.Model.html.HTMLTCLiveModel;
import report_utilities.TestResultModel.BrowserResult;
import report_utilities.TestResultModel.ModuleResult;
import report_utilities.TestResultModel.TestCaseResult;


public class ReportContants
{
	public static boolean isScreenShotforAllPass = false; 
	public static boolean isFullPageScreenShot = false;
	public static String resultsFolder = "";
	public static List<TestCaseResult> testcaseResults= new ArrayList<>();
	public static List<ModuleResult> moduleResults= new ArrayList<>();
	public static List<BrowserResult> browserResults= new ArrayList<>();
	public static List<HTMLTCLiveModel> htmlTCLiveModels= new ArrayList<>();
	public static boolean replaceExistingSnapshotForAllPass=false;
	public static String statusInProgress="InProgress";
	public static String statusCompleted="Completed";
	public static String statusNotStarted="Not Started";
	public static String statusDone="Done";
	public static String statusPass="Pass";
	public static String statusFail="Failed";

	
	public ReportContants(String resultsFolder, boolean isScreenShotforPass, boolean isfullPageScreenShot)
	{
		isFullPageScreenShot = isfullPageScreenShot;
		resultsFolder = resultsFolder;
		isScreenShotforAllPass=isScreenShotforPass;
	} 
 


}