package initialize_scripts;

import common_utilities.Utilities.Util;
import testsettings.TestRunSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api_utilities.Models.APIConfig;
import api_utilities.TestSettings.APITestSettings;
import constants.PrjConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class InitializeTestSettings
{
	
    private static final Logger logger =LoggerFactory.getLogger(InitializeTestSettings.class.getName());


    Properties prop=new Properties();

	Util util = new Util();

	// Get the current working directory
	static String projectRoot = System.getProperty("user.dir");

	// Function to get the absolute path based on the project root and the relative path
	public static String getAbsolutePath(String relativePath) {
		return Paths.get(projectRoot, relativePath).toAbsolutePath().toString();
	}

	public void loadConfigData(String prjPath) throws Exception {
		try {
			// Construct the file path in a cross-platform manner
			String configFilePath = Paths.get(prjPath, "config.properties").toString();
			File configFile = new File(configFilePath);

			if (!configFile.exists()) {
				throw new IOException("Config file not found: " + configFile.getAbsolutePath());
			}

			prop = new Properties();
			try (FileInputStream fis = new FileInputStream(configFile)) {
				prop.load(fis);
			}

			TestRunSettings.homePath = prjPath;
			TestRunSettings.URL = prop.getProperty("URL");
			TestRunSettings.ProjectName = prop.getProperty("ProjectName");
			TestRunSettings.Release = prop.getProperty("Release");

			TestRunSettings.Environment = prop.getProperty("Environment").toUpperCase().trim();
			TestRunSettings.TestRunName = prop.getProperty("TestRunName");
			TestRunSettings.ExecutedBy = prop.getProperty("ExecutedBy");
			TestRunSettings.Browser = prop.getProperty("Browser");

			if (prop.getProperty("CustomBrowserLocation").equalsIgnoreCase("Yes")) {
				TestRunSettings.BrowserLocation = prop.getProperty("BrowserLocation");
			} else {
				TestRunSettings.BrowserLocation = TestRunSettings.homePath + "/Drivers";
			}

			TestRunSettings.MaximizedMode = prop.getProperty("MaximizedMode").equalsIgnoreCase("Yes");
			TestRunSettings.isSnapshotForAllPass = prop.getProperty("SnapshotForAllPass").equalsIgnoreCase("Yes");
			TestRunSettings.isFullPageScreenshot = prop.getProperty("FullPageScreenshot").equalsIgnoreCase("Yes");

			TestRunSettings.PageLoadTimeout = Integer.parseInt(prop.getProperty("PageLoadTimeout"));
			TestRunSettings.ElementTimeout = Integer.parseInt(prop.getProperty("ElementTimeout"));
			TestRunSettings.ElementTimeoutLongWait = Integer.parseInt(prop.getProperty("ElementTimeoutLongWait"));

			//run in headless mode
			TestRunSettings.runInHeadlessMode = prop.getProperty("runInHeadlessMode");
			TestRunSettings.CaptureUIPerformance = prop.getProperty("CaptureUIPerformance");
			TestRunSettings.UIPerfFileName=prop.getProperty("UIPerfFileName");
			// Set the Artifacts Path and other paths dynamically
			TestRunSettings.artifactsPath = getAbsolutePath(prop.getProperty("ArtifactsPath"));
			TestRunSettings.testDataPath = getAbsolutePath(prop.getProperty("TestDataPath"));
			TestRunSettings.testDataMappingFileName = getAbsolutePath(prop.getProperty("TestDataMappingFileName"));
			TestRunSettings.TestDataMappingSheetName_SD = prop.getProperty("TestDataMappingSheetName_SD");
			TestRunSettings.TestDataLocation_CS = getAbsolutePath(prop.getProperty("TestDataLocation_CS"));
			TestRunSettings.UploadDocumentPath = getAbsolutePath(prop.getProperty("UploadDocumentPath"));


			TestRunSettings.ConfigLocation = getAbsolutePath(prop.getProperty("ConfigLocation"));
			TestRunSettings.applicationCredentialsFileName = getAbsolutePath(prop.getProperty("ApplicationCredentialsFileName"));
			TestRunSettings.applicationCredentialsSheetName = prop.getProperty("ApplicationCredentialsSheetName");

			TestRunSettings.ResultsPath = getResultsPath();

			TestRunSettings.isParallelExecution = prop.getProperty("ParallelExecution").equalsIgnoreCase("Yes");
			if (TestRunSettings.isParallelExecution) {
				TestRunSettings.ParallelNodesCount = Integer.parseInt(prop.getProperty("ParallelNodes"));
				TestRunSettings.ParallelExecutionConfig = prop.getProperty("ParallelExecutionConfig");
				TestRunSettings.ParallelNodeSheetAssociation = prop.getProperty("ParallelNodeSheetAssociation");
			}

			TestRunSettings.LoadDriverOptions = loadDriverDetails();
			TestRunSettings.ChromeConfig = prop.getProperty("ChromeConfig");
			TestRunSettings.FireFoxConfig = prop.getProperty("FireFoxConfig");
			TestRunSettings.IEConfig = prop.getProperty("IEConfig");
			TestRunSettings.EdgeConfig = prop.getProperty("EdgeConfig");
			TestRunSettings.OperaConfig = prop.getProperty("OperaConfig");
			TestRunSettings.CloudConfig = prop.getProperty("CloudConfig");
			TestRunSettings.AndroidConfig = prop.getProperty("AndroidConfig");
			TestRunSettings.IOSConfig = prop.getProperty("IOSConfig");

			TestRunSettings.FireFoxLocation = prop.getProperty("FireFoxLocation");
			TestRunSettings.IEDriverLocation = TestRunSettings.homePath + prop.getProperty("IEDriverLocation");
			TestRunSettings.OperaLocation = TestRunSettings.homePath + prop.getProperty("IEDriverLocation");

			TestRunSettings.UploadDocumentPath = TestRunSettings.artifactsPath + prop.getProperty("UploadDocumentLocation");

			TestRunSettings.interfaceSheetDetails = prop.getProperty("InterfaceSheetDetails");
			TestRunSettings.ExcelSheetExtension = prop.getProperty("ExcelSheetExtension");
			TestRunSettings.XMLExtension = prop.getProperty("XMLExtension");
			TestRunSettings.JSONExtension = prop.getProperty("JSONExtension");
			TestRunSettings.CommonMockSheetName = prop.getProperty("CommonMockSheetName");
			TestRunSettings.UseCommonMockSheet = prop.getProperty("UseCommonMockSheet");
			TestRunSettings.MockTemplateLocation = TestRunSettings.artifactsPath + prop.getProperty("MockTemplateLocation");

			TestRunSettings.HeaderRepositorySheetName = prop.getProperty("HeaderRepositorySheetName");
			TestRunSettings.DefaultServiceTimeout = Integer.parseInt(prop.getProperty("DefaultServiceTimeout"));

			TestRunSettings.CaptureTimeTravelDate = prop.getProperty("CaptureTimeTravelDate");
			TestRunSettings.UBTT = prop.getProperty("UBTT");
			TestRunSettings.AccountNumber = prop.getProperty("AccountNumber");
			TestRunSettings.AdatoolPath=TestRunSettings.artifactsPath + prop.getProperty("AdatoolPath");

		} catch (Exception e) {
			logger.info("Failed to Initialize the Test Run Settings");

			logger.info(e.getMessage());
			logger.info("StackTrace: {}",e.getStackTrace());
			throw new Exception("Failed to Initialize the Test Run Settings");
		}
	}

		public void loadAPIConfig(String prjPath) throws Exception
	{
		Util newutil = new Util();
		// Initialize API Settings
		Properties newProp = newutil.loadProperties(prjPath + PrjConstants.APICONFIGFILE);
		 
		 APITestSettings.apiTestSettings = new APIConfig();
		
		APITestSettings.apiTestSettings.APITestSuiteDirectory= TestRunSettings.artifactsPath +newProp.getProperty("APITestSuiteDirectory");
		APITestSettings.apiTestSettings.APITestSuiteSheetName= newProp.getProperty("APITestSuiteSheetName");
		APITestSettings.apiTestSettings.APITestCaseDirectory= TestRunSettings.artifactsPath +newProp.getProperty("APITestCaseDirectory");
		APITestSettings.apiTestSettings.APIDirectory= TestRunSettings.artifactsPath +newProp.getProperty("APIDirectory");
		APITestSettings.apiTestSettings.APITestSuiteFileName= APITestSettings.apiTestSettings.APITestSuiteDirectory+ newProp.getProperty("APITestSuiteFileName");
		APITestSettings.apiTestSettings.APITestCaseSheetName= newProp.getProperty("APITestCaseSheetName");
		
		APITestSettings.apiTestSettings.Environment=TestRunSettings.Environment;
		APITestSettings.apiTestSettings.TestDataPath=TestRunSettings.testDataPath;
		APITestSettings.apiTestSettings.ExcelFileName = TestRunSettings.artifactsPath + newProp.getProperty("APIFileName");
		APITestSettings.apiTestSettings.InterfaceTestCaseSheet =  newProp.getProperty("InterfaceTestCaseSheet");
		APITestSettings.apiTestSettings.UrlRepositorySheet = TestRunSettings.artifactsPath + newProp.getProperty("URLRepositorySheet");
		APITestSettings.apiTestSettings.MockRepositorySheet = TestRunSettings.artifactsPath + newProp.getProperty("MockRepositorySheet");
		APITestSettings.apiTestSettings.HeaderRepository = TestRunSettings.artifactsPath + newProp.getProperty("HeaderRepository");
		APITestSettings.apiTestSettings.ResponseSheetPath = TestRunSettings.artifactsPath + newProp.getProperty("ResponseSheetPath");

		APITestSettings.apiTestSettings.CertificateLocation = TestRunSettings.artifactsPath + newProp.getProperty("CertificateLocation");
		APITestSettings.apiTestSettings.RequestLocation = TestRunSettings.artifactsPath + newProp.getProperty("RequestLocation");
		APITestSettings.apiTestSettings.ResponseLocation = TestRunSettings.artifactsPath + newProp.getProperty("ResponseLocation");
		APITestSettings.apiTestSettings.TestDataLocation_SD = TestRunSettings.artifactsPath + newProp.getProperty("TestDataLocation_SD");
		APITestSettings.apiTestSettings.TestDataLocation_XMLValidation = TestRunSettings.artifactsPath + newProp.getProperty("TestDataLocation_XMLValidation");
		APITestSettings.apiTestSettings.ConfigLocation_XMLValidation = TestRunSettings.artifactsPath + newProp.getProperty("ConfigLocation_XMLValidation");
		
		APITestSettings.apiTestSettings.InterfaceSheetDetails = newProp.getProperty("InterfaceSheetDetails");

		APITestSettings.apiTestSettings.ExcelSheetExtension = newProp.getProperty("ExcelSheetExtension");
		APITestSettings.apiTestSettings.XMLExtension = newProp.getProperty("XMLExtension");
		APITestSettings.apiTestSettings.JSONExtension = newProp.getProperty("JSONExtension");
		APITestSettings.apiTestSettings.CommonMockSheetName = newProp.getProperty("CommonMockSheetName");
		APITestSettings.apiTestSettings.UseCommonMockSheet = newProp.getProperty("UseCommonMockSheet");
		APITestSettings.apiTestSettings.MockTemplateLocation = TestRunSettings.artifactsPath + newProp.getProperty("MockTemplateLocation");

		APITestSettings.apiTestSettings.HeaderRepositorySheetName = newProp.getProperty("HeaderRepositorySheetName");
		APITestSettings.apiTestSettings.MockSheetName = getMockSheetName(APITestSettings.apiTestSettings.UseCommonMockSheet, TestRunSettings.CommonMockSheetName, TestRunSettings.Environment);

		APITestSettings.apiTestSettings.DefaultServiceTimeout = Integer.parseInt(newProp.getProperty("DefaultServiceTimeout"));

		APITestSettings.apiTestSettings.ResponseValidationFilePath=TestRunSettings.artifactsPath + newProp.getProperty("ResponseValidationFileName");
		APITestSettings.apiTestSettings.ResponseValidationSheetName=newProp.getProperty("ResponseSheetName");
		
		TestRunSettings.APIConfigFileName =newProp.getProperty("APIConfig");


		APITestSettings.apiTestSettings.StoreResponseDataFilePath=TestRunSettings.artifactsPath + newProp.getProperty("StoreResponseDataFilePath");
		APITestSettings.apiTestSettings.StoreResponseDataSheetName=newProp.getProperty("StoreResponseDataSheetName");

		
		if(newProp.getProperty("UseCommonTestDataSheetName").equalsIgnoreCase("Yes"))
		{
		APITestSettings.apiTestSettings.UseCommonTestDataSheet=true;
		}

		APITestSettings.apiTestSettings.CommonTestDataSheetName =newProp.getProperty("CommonTestDataSheetName");
		TestRunSettings.RequestFolderPath = newProp.getProperty("RequestFolderName");
		TestRunSettings.ResponseFolderPath = newProp.getProperty("ResponseFolderName");

	}
	

	String getMockSheetName(String useCommonMockSheet, String commonMockSheetName, String environment)
	{

		if ("YES".equals(useCommonMockSheet.toUpperCase().trim()))
		{
			return commonMockSheetName;
		}
		else
		{
			return environment;
		}
	}

	
	public boolean loadDriverDetails() throws Exception
	{
		try
		{
			return "YES".equalsIgnoreCase(prop.getProperty("LoadDriverOptions").trim());

		}
		catch (Exception e)
		{
			logger.info("Failed to Initialize the Driver Load Details");

			logger.info(e.getMessage());
			logger.info("StackTrace:{}",e.getStackTrace());
			throw new Exception("Failed to Initialize the  Driver Load Details");

		}
	}

	/// <summary>
	/// This mmethod will return the Directory Path where the results files will be stored
	/// Author: Jigesh Shah
	/// </summary>
	/// <returns>Results Directory Path</returns>
	public String getResultsPath() throws Exception
	{
		try
		{
			if (prop.getProperty("SetCustomResultLocation").toUpperCase().trim().equals("YES"))
			{
				File dir = new File(prop.getProperty("CustomResultLocation") + File.separator + Util.getCurrentDate() + File.separator + "Run_" + Util.getCurrentTime());

				if (!dir.exists()) dir.mkdirs();
				return prop.getProperty("CustomResultLocation")+ "/"+  Util.getCurrentDate() + "/Run_" +  Util.getCurrentTime();
			}
			else
			{
				String resultFolder= TestRunSettings.homePath + "/" + prop.getProperty("DefaultResultLocation") + "/"+  Util.getCurrentDate() + "/Run_" +  Util.getCurrentTime();
				File dir = new File(resultFolder);
	            if (!dir.exists()) dir.mkdirs();

	            
	            
				return resultFolder;
			}

		}
		catch (Exception e)
		{
			logger.info("Failed to Initialize the Results Path");
			logger.info(e.getMessage());
			logger.info("StackTrace: {}",e.getStackTrace());
			throw new Exception("Failed to Initialize the Results Path", e);

		}



	}





}
