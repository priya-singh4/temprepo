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
    public static Integer parallelNodesCount = 1;

    //Environment Settings
    public static String url = "";
    public static String projectName = "";
    public static String release = "";
    public static String environment = "";
    public static String testRunName = "";
    public static String executedBy = "";
    public static String browser = "";
    public static String browserLocation = "";
    public static boolean maximizedMode = false;
    public static boolean isSnapshotForAllPass = false;
    public static boolean isFullPageScreenshot = false;
    public static Integer pageLoadTimeout = 60;
    public static Integer elementTimeout = 60;
    public static Integer elementTimeoutLongWait = 120;
    public static String appDate = "";
    public static boolean clearCache = false;
    public static String captureTimeTravelDate="";
    public static String ubtt="";
    public static String accountNumber="";
    public static String captureUIPerformance="";
    
    

    //Artifacts Location Setup
    public static String testDataMappingSheetNameCs = "";
    public static String testDataLocationCs = "/CS";

    //pdf
	
	public static String targetPDFfile = "TargetPDFfile";
	public static String pdfExtension = "PdfExtension";
	public static String editedPDFFile = "EditedPDFFile";
	public static String sourcePDFfile = "SourcePDFfile";
	public static String resultfile = "Resultfile";
	public static String textFile = "TextFile";
	public static String psFileoutpath = "PSfileoutpath";
	public static String sourceTextFile = "SourceTextFile";
	public static String targetTextFile = "TargetTextFile";
	public static String replacedTextFile = "ReplacedTextFile";
	public static String sourceTextFileMuscle = "SourceTextFile_Muscle";
	public static String sourceTextFileHnF = "SourceTextFile_HnF";
	public static String sourceTextFileNeuro = "SourceTextFile_Neuro";
	public static String sourcePdfFileMuscle = "SourcePDFfile_Muscle";
	public static String sourcePdfFileHnF = "SourcePDFfile_HnF";
	public static String SourcePdfFileNeuro = "SourcePDFfile_Neuro";
	public static boolean replaceExistingSnapshotForAllPass=false;
	
	//
    public static String uploadDocumentPath = "DocUpload";
    
    
  //DB Validation
  	public static String dbDetailedValidationFileName="";
  	public static String dbDetailedValidationLocation="";
  	public static String crDbDetailedValidationFileName="";

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
    public static String fireFoxLocation = "";
    public static String ieDriverLocation = "";
    public static String operaLocation = "";



    public static String interfaceSheetDetails;
    public static String excelSheetExtension;
    public static String xmlExtension;
    public static String jsonExtension;
    public static String commonMockSheetName;
    public static String useCommonMockSheet;
    public static String mockSheetName;
    public static String headerRepositorySheetName;
    public static String headerRepository;
    public static String responseSheetPath;
    public static Integer defaultServiceTimeout;
    public static String resultsPath;
    public static String requestFolderPath;
    public static String responseFolderPath;
    public static String excelFileName;
    public static boolean generateExcelReport;
    public static String mockTemplateLocation;
    public static String domainName;


    //Capabilities Config
    public static HashMap<String, HashMap<String, String>> dictCapabilities = new HashMap<String, HashMap<String, String>>();
    public static boolean isDriverOptionsEnabled = false;


    //WebDrivers
    public static WebDriver driver;
    public static HashMap<Integer, WebDriver> dictWebDriver = new HashMap<Integer, WebDriver>();

    //AndroidDriver
    public static AndroidDriver<AndroidElement> aDriver;
    public static HashMap<Integer, AndroidDriver<AndroidElement>> dictAdriver = new HashMap<Integer, AndroidDriver<AndroidElement>>();


    //IOSDriver
    public static IOSDriver<IOSElement> iDriver;
    public static HashMap<Integer, IOSDriver<IOSElement>> dictIDriver = new HashMap<Integer, IOSDriver<IOSElement>>();


    public static HashMap<String, HashMap<String,String>> masterInterfaceSheet = new HashMap<String, HashMap<String, String>>();


	public static String excelFilepath;
	public static String uiPerfFileName;

	//DB Validations
	public static String dbValidationFileName;
	public static String dbValidationSheetName;
	public static String dbConnectionStrings;
	public static  String StaticDataFileName = "";

	public static final String staticDataSheetName="";

	//ConfigLocation
	public static String configLocation;


	//API Config
	public static String apiConfigFileName;



//	ExcelDriven TestCases
	public static String testScenarioFilePath;
	public static String testScenarioFileName;
	public static String testScenarioSheetName;
	public static String testCasesFilePath;
	public static int parallelThreadCount;

	//Temp Results
	public static String tempExcelTestCaseResults;
	public static String tempXmlTestCaseResults;
	public static String tempJsonTestCaseResults;


    public static String testDataMappingSheetNameEn = "";
	public static String testDataMappingSheetNameSd = "";
	public static HashMap<String, String[]> adaResultsMap = new HashMap<>();
	
	
	public static HashMap<String, String> UIPerformanceResultsMap= new HashMap<String, String>();
	public static String adaToolPath="";
}
