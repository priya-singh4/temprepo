package testsettings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import ReportUtilities.Model.TestCaseParam;


public class SessionData {
	
	private SessionData() {
		
	}


	public static String timeTravelDate= "";
	public static String fIRSTDAYOFMONTH="";
	public static String LASTDAYOFMONTH="";
	public static String FIRSTDAYOFPREVIOUSMONTH="";
	public static String LASTDAYOFPREVIOUSMONTH="";
	public static String FIRSTDAYOFNEXTMONTH="";
	public static String LASTDAYOFNEXTMONTH="";
	public static String FIRSTDAYOFCUSTOMMONTH = "";
	public static String LASTDAYOFFUTURECUSTOMMONTH="";
	public static String FIRSTDAYOFPOSTCUSTOMMONTH="";
	public static String LASTDAYOFPASTCUSTOMMONTH="";												

	
	public static String caseNumber="";
	public static String randomData="";
	public static String pNpReferralCode="";
	public static String cOPayAmount="";
	public static String amount="";	
	public static String calculationId="";

	public static String taskId="";
	public static HashMap<String,String> dbPlaceHolder= new HashMap<>();
	public static HashMap<String,String> sessionKeys= new HashMap<>();
	public static HashMap<String, ArrayList<String>> loginData = new HashMap<>();

	public static String loggingConnectionString="";
	public static String loggingTableName="";
	public static String applicationNumber="";
	
	public static final String DATEPATTERN="YYYY-MM-DD";
	
	
	public static void setDBPlaceHolderData(String dBKey, String dBValue, TestCaseParam testCaseParam)
	{
		dbPlaceHolder.put(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_"+dBKey,dBValue);

	}
	

	public static String getDBPlaceHolderData(String dBKey, TestCaseParam testCaseParam)
	{
		return dbPlaceHolder.get(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_"+dBKey);

	}

	
	public static void setSessionData(TestCaseParam testCaseParam,String key, String value)
	{
		sessionKeys.put(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_"+key,value);
	}
	

	public static String getSessionData(TestCaseParam testCaseParam,String key)
	{
		return sessionKeys.get(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_"+key);		
	}
	
	public static String replaceSessionData(TestCaseParam testCaseParam,String key)
	{
		
		for(String s : sessionKeys.keySet())
		{
			s= s.replace(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_", "");
			if(key.contains(s))
			{
				key=key.replace(s, sessionKeys.get(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_"+s));
			}
		}
		return key;		
	}
	
	
	
	
	public static boolean sessionDataContainsKey(TestCaseParam testCaseParam,String key)
	{
		if( sessionKeys.containsKey(testCaseParam.TestCaseName +"_"+ testCaseParam.ModuleName+"_"+ testCaseParam.Browser + "_"+ testCaseParam.Iteration+"_"+key))
		{
			return true;
		}
		else
		{
			return false;			
		}
	}
	
	public static String getLoginData(String key)
	{
		return loginData.get(key).get(0);
	}
	
	
	public static String getTimeTravelDate() 
	{
		return timeTravelDate;
	}
	
	public static void setTimeTravelDate(String ttdate) 
	{
		timeTravelDate=ttdate;
	}
	
	public static String getCalculationID() 
	{
		return calculationId;
	}
	
	public static void setCalculationID(String calcID) 
	{
		calculationId=calcID;
	}
	
	public static String getFirstDayofCurrentMonth() 
	{
		return fIRSTDAYOFMONTH;
	}
	
	public static String getTaskId() 
	{
		return taskId;
	}

	
	public static void setTaskID(String taskID) 
	{
		taskId=taskID;
	}
	
	
	

	public static String getLastDayofCurrentMonth() 
	{
		return LASTDAYOFMONTH;
	}

	
	public static void setFirstDayofCurrentMonth(String incomeDate) 
	{
		fIRSTDAYOFMONTH=incomeDate;
	}
	
	public static String getFirstDayofCustomMonth() 
	{
		return FIRSTDAYOFCUSTOMMONTH;
	}
	public static void setFirstDayofCustomMonth(String incomeDate) 
	{
		FIRSTDAYOFCUSTOMMONTH=incomeDate;
	}
	
	public static String getFirstDayofPreviousMonth() 
	{
		return FIRSTDAYOFPREVIOUSMONTH;
	}

	
	public static String getLastDayofPreviousMonth() 
	{
		return LASTDAYOFPREVIOUSMONTH;
	}

	public static void setFirstDayofPreviousMonth(String incomeDate) 
	{
		FIRSTDAYOFPREVIOUSMONTH=incomeDate;
	}
	
	
	public static String getFirstDayofNextMonth() 
	{
		return FIRSTDAYOFNEXTMONTH;
	}

	
	public static String getLastDayofNextMonth() 
	{
		return LASTDAYOFNEXTMONTH;
	}

	public static String getCaseNumber() 
	{
		return caseNumber;
	}

	
	public static String getFirstMonth() 
	{
		return timeTravelDate;
	}

	
	public static String increamentDateByMonths(int noOfMonths) 
	{
		LocalDate lDate=LocalDate.parse(timeTravelDate);
		lDate=lDate.plusMonths(noOfMonths);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATEPATTERN);
        return dtf.format(lDate);
	}

	
	public static String increamentDateByDays(int noOfDays) 
	{
		LocalDate lDate=LocalDate.parse(timeTravelDate);
		lDate=lDate.plusDays(noOfDays);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATEPATTERN);
        return dtf.format(lDate);
		
	}
	
	public static String increamentDateByYears(int noOfYears) 
	{
		LocalDate lDate=LocalDate.parse(timeTravelDate);
		lDate=lDate.plusMonths(noOfYears);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATEPATTERN);
        return dtf.format(lDate);
		
	}
	
	public static String getRandomData() 
	{
		return randomData;
	}

	
	
	public static void setRandomData(String data) 
	{
		randomData=data;
	}
	
	
	public static void setPnPReferralCode(TestCaseParam testCaseParam,String pnpReferralCode) 
	{
		pNpReferralCode=pnpReferralCode;
	}
	public static String getPnPReferralCode() 
	{
		return pNpReferralCode;
	}
	public static void setCoPayAmount(TestCaseParam testCaseParam,String coPayAmount) 
	{
		cOPayAmount=coPayAmount;
	}
	public static String getCoPayAmount() 
	{
		return cOPayAmount;
	}
	
}
