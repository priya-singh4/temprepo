package api_utilities.TestSettings;

import java.util.ArrayList;
import java.util.HashMap;

import api_utilities.models.APIConfig;
import api_utilities.models.APIReportModel;
import api_utilities.models.APITestCaseModel;
import api_utilities.models.APITestStepModel;

public class APITestSettings {


	public static final String DBVALIDATION = "No";
	public static String artifactsPath = null;
	public static String environment="";
	public static APIConfig apiTestSettings= null;
	public static String homePath;
	public static String trustStoreLocation;
	public static String trustStorePassword;
	
	public static HashMap<String,ArrayList<APIReportModel>> reportModel= new HashMap<>();
	public static  HashMap<String, ArrayList<APITestStepModel>> apiTcExecData= new HashMap<>();
	public static  HashMap<String, APITestCaseModel> apiTcInfo= new HashMap<>();

	public static HashMap<String,HashMap<String, String>> dictTestData= new HashMap<>();
	public static String resultsPath;
	public static String resultsRequestLocation;
	public static String resultsResponseLocation;
	public static String appUrl;
	public static String userName;
	public static String password;
	public static String browser;
	public static String excelFilepath;
	public static boolean isKeyStoreConfigurationRequired=false;


	
}
