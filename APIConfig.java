package api_utilities.models;

public class APIConfig {

	//InterfaceTestRunSettings

	public  String environment="";
    public  String defaultTestDataFormat = ".xlsx";
    public  String interfaceTestCaseSheet = "";
    public  String urlRepositorySheet = "";
    public  String requestLocation = "";
    public  String responseLocation = "";
    public  String certificateLocation = "";
    public  String certificatePassword="";
    public  String testDataPath = "";
    
    public  boolean addReportToDb = false;

    public  long testRunId = 1;
    public  String mockRepositorySheet;
    public  String interfaceSheetDetails;
    public  String excelSheetExtension;
    public  String xmlExtension;
    public  String jsonExtension;
    public  String commonMockSheetName;
    public  String useCommonMockSheet="";
    public  String mockSheetName;
    public  String headerRepositorySheetName;
    public  String headerRepository;
    public  String responseSheetPath;
    public  Integer defaultServiceTimeout;
    public  String mockTemplateLocation;
    public  String domainName;
	public String excelFileName;
	public String responseValidationFilePath;
	public String responseValidationSheetName;
	public String testDataLocationSd;
	public String testDataLocationXmlValidation;
	public String configLocationXmlValidation;
	public boolean useCommonTestDataSheet;
	public String commonTestDataSheetName;
	public String apiTestSuiteDirectory;
	public String apiTestCaseDirectory;
	public String apiDirectory;
	public String apiTestSuiteFileName;
	public String apiTestSuiteSheetName;
	public String apiTestCaseSheetName;

	public String storeResponseDataFilePath;
	public String storeResponseDataSheetName;

	
}
