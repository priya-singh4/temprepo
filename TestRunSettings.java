package testsettings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

import org.slf4j.*;



import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestRunSettings
{

    public static String runInHeadlessMode;
    public static String artifactsPath = "";
    public static String testDataPath = "";
    public static String testDataMappingFileName = "";
    public static String applicationCredentialsFileName = "";
    public static String applicationCredentialsSheetName = "";
    private static final Logger logger =LoggerFactory.getLogger(TestRunSettings.class.getName());

  
    public static String homePath = "";
    public static LinkedHashMap<HashMap<String,String>,ArrayList<String>> uiperfdata=new LinkedHashMap<HashMap<String,String>,ArrayList<String>>();
    public static LinkedList<Integer> UIPerf_ScreenCounter=new LinkedList<Integer>();


    public static LinkedList<String> pageLoadtime=new LinkedList<String>();
    public static LinkedList<String> domLoadtime=new LinkedList<String>();
    //TestInitialization
    public static boolean isTestInitialized = false;
    public static boolean isParallelExecution = false;
    public static Integer ParallelNodesCount = 1;

    //Environment Settings
    public static String URL = "";
    public static String ProjectName = "";
    public static String Release = "";
    public static String Environment = "";
    public static String TestRunName = "";
    public static String ExecutedBy = "";
    public static String Browser = "";
    public static String BrowserLocation = "";
    public static boolean MaximizedMode = false;
    public static boolean isSnapshotForAllPass = false;
    public static boolean isFullPageScreenshot = false;
    public static Integer PageLoadTimeout = 60;
    public static Integer ElementTimeout = 60;
    public static Integer ElementTimeoutLongWait = 120;
    public static String appDate = "";
    public static boolean ClearCache = false;
    public static String CaptureTimeTravelDate="";
    public static String UBTT="";
    public static String AccountNumber="";
    public static String CaptureUIPerformance="";
    
    

    //Artifacts Location Setup
    public static String TestDataMappingSheetName_CS = "";
    public static String TestDataLocation_CS = "/CS";

    //pdf
	
	public static String TargetPDFfile = "TargetPDFfile";
	public static String PdfExtension = "PdfExtension";
	public static String EditedPDFFile = "EditedPDFFile";
	public static String SourcePDFfile = "SourcePDFfile";
	public static String Resultfile = "Resultfile";
	public static String TextFile = "TextFile";
	public static String PSfileoutpath = "PSfileoutpath";
	public static String SourceTextFile = "SourceTextFile";
	public static String TargetTextFile = "TargetTextFile";
	public static String ReplacedTextFile = "ReplacedTextFile";
	public static String SourceTextFile_Muscle = "SourceTextFile_Muscle";
	public static String SourceTextFile_HnF = "SourceTextFile_HnF";
	public static String SourceTextFile_Neuro = "SourceTextFile_Neuro";
	public static String SourcePDFfile_Muscle = "SourcePDFfile_Muscle";
	public static String SourcePDFfile_HnF = "SourcePDFfile_HnF";
	public static String SourcePDFfile_Neuro = "SourcePDFfile_Neuro";
	public static boolean replaceExistingSnapshotForAllPass=false;
	
	//
    public static String UploadDocumentPath = "DocUpload";
    
    
  //DB Validation
  	public static String DBDetailedValidationFileName="";
  	public static String DBDetailedValidationLocation="";
  	public static String CR_DBDetailedValidationFileName="";

    //Result  Location Setup
    public static String ResultsFolderPath = "";
    public static String SetCustomResultLocation="";
    public static String CustomResultLocation="";

    //ParallelConfig
    public static String ParallelConfigPath = "";
    public static String ParallelExecutionConfig = "";
    public static String ParallelNodeSheetAssociation = "";

   
    //Driver Config
    public static boolean LoadDriverOptions = false;
    public static String ChromeConfig = "";
    public static String FireFoxConfig = "";
    public static String IEConfig = "";
    public static String EdgeConfig = "";
    public static String OperaConfig = "";
    public static String CloudConfig = "";
    public static String AndroidConfig = "";
    public static String IOSConfig = "";


    //Location for Browser in OS
    public static String FireFoxLocation = "";
    public static String IEDriverLocation = "";
    public static String OperaLocation = "";



    public static String interfaceSheetDetails;
    public static String ExcelSheetExtension;
    public static String XMLExtension;
    public static String JSONExtension;
    public static String CommonMockSheetName;
    public static String UseCommonMockSheet;
    public static String MockSheetName;
    public static String HeaderRepositorySheetName;
    public static String HeaderRepository;
    public static String ResponseSheetPath;
    public static Integer DefaultServiceTimeout;
    public static String ResultsPath;
    public static String RequestFolderPath;
    public static String ResponseFolderPath;
    public static String ExcelFileName;
    public static boolean GenerateExcelReport;
    public static String MockTemplateLocation;
    public static String DomainName;


    //Capabilities Config
    public static HashMap<String, HashMap<String, String>> DictCapabilities = new HashMap<String, HashMap<String, String>>();
    public static boolean isDriverOptionsEnabled = false;


    //WebDrivers
    public static WebDriver driver;
    public static HashMap<Integer, WebDriver> DictWebDriver = new HashMap<Integer, WebDriver>();

    //AndroidDriver
    public static AndroidDriver<AndroidElement> ADriver;
    public static HashMap<Integer, AndroidDriver<AndroidElement>> DictADriver = new HashMap<Integer, AndroidDriver<AndroidElement>>();


    //IOSDriver
    public static IOSDriver<IOSElement> IDriver;
    public static HashMap<Integer, IOSDriver<IOSElement>> DictIDriver = new HashMap<Integer, IOSDriver<IOSElement>>();


    public static HashMap<String, HashMap<String,String>> MasterInterfaceSheet = new HashMap<String, HashMap<String, String>>();


	public static String ExcelFilepath;
	public static String UIPerfFileName;

	//DB Validations
	public static String DBValidationFileName;
	public static String DBValidationSheetName;
	public static String DBConnectionStrings;
	public static  String StaticDataFileName = "";

	public static String StaticDataSheetName="";

	//ConfigLocation
	public static String ConfigLocation;


	//API Config
	public static String APIConfigFileName;



//	ExcelDriven TestCases
	public static String TestScenarioFilePath;
	public static String TestScenarioFileName;
	public static String TestScenarioSheetName;
	public static String TestCasesFilePath;
	public static int ParallelThreadCount;

	//Temp Results
	public static String TempExcelTestCaseResults;
	public static String TempXMLTestCaseResults;
	public static String TempJSONTestCaseResults;


    public static String TestDataMappingSheetName_En = "";
	public static String TestDataMappingSheetName_SD = "";
	public static HashMap<String, String[]> adaResultsMap = new HashMap<>();
	
	
	public static HashMap<String, String> UIPerformanceResultsMap= new HashMap<String, String>();
	public static String AdatoolPath="";
}
