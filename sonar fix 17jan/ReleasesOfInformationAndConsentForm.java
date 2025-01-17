package cares.cwds.salesforce.pom.folio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;

import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class ReleasesOfInformationAndConsentForm {
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.RELEASESOFINFORMATIONANDCONSENTFORM;
	LocalDateTime startTime= LocalDateTime.now();
	
	private static final String RELEASESOFINFORMATIONANDCONSENTFORMTAB = "Releases of Information and Consent Form";
	public ReleasesOfInformationAndConsentForm(){ }
	
	public ReleasesOfInformationAndConsentForm(WebDriver wDriver)
	{
		initializePage(wDriver);
	}

	public void initializePage(WebDriver wDriver) 
    {
    	 driver = wDriver;
         PageFactory.initElements(driver, this);
         ReportCommon testStepLogDetails = new ReportCommon(); 
         testStepLogDetails.logModuleAndScreenDetails(moduleName, screenName);
         genericLocators = new GenericLocators(wDriver);
    }
	
	public void navigateToReleaseOfInformationAndConsentForm(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Navigate To Release Of Information And Consent Form");
		action.setPageActionDescription("Navigate To Release Of Information And Consent Form");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String releaseOfInformationAndConsentFormTD =  testCaseDataSd.get("RELEASES_OF_INFORMATION_AND_CONSENT_FORM_TAB").get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, RELEASESOFINFORMATIONANDCONSENTFORMTAB, releaseOfInformationAndConsentFormTD),releaseOfInformationAndConsentFormTD,  action);
		
	}
	
	public void addReleaseOfInformationAndConsentFormDetails(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Add Release Of Information And Consent Form Details");
		action.setPageActionDescription("Add Release Of Information And Consent Form Details");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String releaseOfInformationAndConsentFormNewBtn  = testCaseDataSd.get("RELEASES_OF_INFORMATION_AND_CONSENT_FORM_NEW_BTN").get(0);
		String saveBtn = testCaseDataSd.get("SAVE_BTN").get(0);
		
		Webkeywords.instance().pause();
		Webkeywords.instance().click(driver, genericLocators.button(driver, "New",releaseOfInformationAndConsentFormNewBtn),releaseOfInformationAndConsentFormNewBtn, action);
		Webkeywords.instance().pause();
		Webkeywords.instance().selectValueInSearchbox(driver, testCaseDataSd.get("FOLIO_PERSON_ID").get(0),"Folio Person ID",action);
		Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("AUTHORIZATION_TYPE").get(0),"Authorization Type",action);
		Webkeywords.instance().selectValueInSearchbox(driver, testCaseDataSd.get("ON_BEHALF_OF").get(0),"On Behalf of",action);
		Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("TYPE_OF_CONSENT_ROI").get(0),"Type of Consent/ROI",action);
		Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("ACTIVE").get(0),"Active",action);
		Webkeywords.instance().click(driver, genericLocators.checkbox(driver, "Sensitive", testCaseDataSd.get("SENSITIVE").get(0)), testCaseDataSd.get("SENSITIVE").get(0), action);	
		Webkeywords.instance().click(driver, genericLocators.checkbox(driver, "Sealed", testCaseDataSd.get("SEALED").get(0)), testCaseDataSd.get("SEALED").get(0), action);	
		
		Webkeywords.instance().click(driver,genericLocators.button(driver, "Save",saveBtn),saveBtn,  action);
		
	}

}
