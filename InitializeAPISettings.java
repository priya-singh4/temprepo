package api_utilities.TestSettings;

import java.io.IOException;
import java.util.Properties;

import api_utilities.api_common.APIUtil;
import api_utilities.models.APIConfig;


public class InitializeAPISettings {

	public APIConfig initializeInterfaceSettings(String prjPath,String artifactsPath,String configLocation,String env)
	{
	    Properties prop=new Properties();
	    APIUtil util = new APIUtil();

	    APIConfig apiConfigSettings = new APIConfig();
		
		 try {
			prop=util.loadProperties(configLocation);
		} catch (IOException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
			APITestSettings.homePath = prjPath;
		

			
	    apiConfigSettings.setEnvironment(env);

	    APITestSettings.artifactsPath = artifactsPath;
		apiConfigSettings.setTestDataPath(APITestSettings.artifactsPath + prop.getProperty("TestDataMappingLocation"));
		apiConfigSettings.setExcelFileName(APITestSettings.artifactsPath + prop.getProperty("ExcelFileName"));
		apiConfigSettings.setInterfaceTestCaseSheet(prop.getProperty("InterfaceTestCaseSheet"));
		apiConfigSettings.setUrlRepositorySheet(APITestSettings.artifactsPath + prop.getProperty("URLRepositorySheet"));
		apiConfigSettings.setMockRepositorySheet(APITestSettings.artifactsPath + prop.getProperty("MockRepositorySheet"));
		apiConfigSettings.setHeaderRepository(APITestSettings.artifactsPath + prop.getProperty("HeaderRepository"));
		apiConfigSettings.setResponseSheetPath(APITestSettings.artifactsPath + prop.getProperty("ResponseSheetPath"));

		apiConfigSettings.setCertificateLocation(APITestSettings.artifactsPath + prop.getProperty("CertificateLocation"));
		apiConfigSettings.setRequestLocation(APITestSettings.artifactsPath + prop.getProperty("RequestLocation"));
		apiConfigSettings.setResponseLocation(APITestSettings.artifactsPath + prop.getProperty("ResponseLocation"));
		apiConfigSettings.setInterfaceSheetDetails(prop.getProperty("InterfaceSheetDetails"));

		apiConfigSettings.setExcelSheetExtension(prop.getProperty("ExcelSheetExtension"));
		apiConfigSettings.setXmlExtension(prop.getProperty("XMLExtension"));
		apiConfigSettings.setJsonExtension(prop.getProperty("JSONExtension"));
		apiConfigSettings.setCommonMockSheetName(prop.getProperty("CommonMockSheetName"));
		apiConfigSettings.setUseCommonMockSheet(prop.getProperty("UseCommonMockSheet"));
		apiConfigSettings.setDomainName(prop.getProperty("DomainName"));
		apiConfigSettings.setMockTemplateLocation(APITestSettings.artifactsPath + prop.getProperty("MockTemplateLocation"));

		
		apiConfigSettings.setHeaderRepositorySheetName(prop.getProperty("HeaderRepositorySheetName"));
		apiConfigSettings.setMockSheetName(getMockSheetName(apiConfigSettings.getUseCommonMockSheet(), apiConfigSettings.getCommonMockSheetName(), apiConfigSettings.getEnvironment()));
		apiConfigSettings.setAddReportToDB(addReportToDB(prop.getProperty("AddReportToDataBase")));
		apiConfigSettings.setDefaultServiceTimeout(Integer.parseInt(prop.getProperty("DefaultServiceTimeout")));

		//Response Validation
		apiConfigSettings.setResponseValidationFilePath(APITestSettings.artifactsPath + prop.getProperty("ResponseValidationFileName"));
		apiConfigSettings.setResponseValidationSheetName(prop.getProperty("ResponseValidationSheetName"));
		
		//TrustStore Location and Password
		APITestSettings.trustStoreLocation= prop.getProperty("TrustStoreLocation");
		APITestSettings.trustStorePassword= prop.getProperty("TrustStorePassword");

		
		APITestSettings.apiTestSettings=apiConfigSettings;
		return apiConfigSettings;


	}

	boolean addReportToDB(String addReportToDB)
	{

		if (addReportToDB.toUpperCase().trim().equals("YES"))
		{
			return true;
		}
		else
			return false;
	}

	String getMockSheetName(String useCommonMockSheet, String commonMockSheetName, String environment)
	{

		if (useCommonMockSheet.toUpperCase().trim() == "YES")
		{
			return commonMockSheetName;
		}
		else
		{
			return environment;
		}
	}

}
