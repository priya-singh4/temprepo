package cares.cwds.salesforce.pom.folio.placement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;

import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class OverstayReportSubmissions {
	
	private static final Logger logger = LoggerFactory.getLogger(OverstayReportSubmissions.class.getName());
	private static final String CORRESPONDENCES_TAB= "CORRESPONDENCES_TAB";
	private static final String CORRESPONDENCES= "Corrrespondences";
	
	private static final String SAVE_AND_PROCEED= "Save and Proceed";
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIOPLACEMENT;

	LocalDateTime startTime = LocalDateTime.now();

	public OverstayReportSubmissions() {
	}

	public OverstayReportSubmissions(WebDriver wDriver) {
		initializePage(wDriver);
	}

	public void initializePage(WebDriver wDriver) {
		logger.info(this.getClass().getName());
		driver = wDriver;
		PageFactory.initElements(driver, this);
		ReportCommon testStepLogDetails = new ReportCommon();
		testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
		genericLocators = new GenericLocators(wDriver);
	}
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Distribution Date and Time']/../../following-sibling::lightning-input//input)[1]")
	public WebElement callDateAndTime;
	
	public void navigateToCorrespondences( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Correspondences");
		action.setPageActionDescription("Navigate to Correspondences");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

		String correspondencesTabTD = testCaseDataSd.get(CORRESPONDENCES_TAB).get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, CORRESPONDENCES,correspondencesTabTD),correspondencesTabTD, action);
	}
	
	public void clickNew(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Create New Overstay Report Submission");
		action.setPageActionDescription("Create New Overstay Report Submission");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)),testCaseDataSd.get("NEW_BTN").get(0),action);
	}


	public void addInformation( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add Closure Reason");
		action.setPageActionDescription("Add Closure Reason");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
		String cdssTD = testCaseDataSd.get("STAFF_PERSON").get(0);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DISTRIBUTION_METHOD").get(0),"Method Of Distribution",action);
		Webkeywords.instance().selectValueInSearchbox(driver, "CDSS Placement Oversight Recipient", cdssTD, action);
		Webkeywords.instance().setDateText(driver, callDateAndTime, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("CALL_DATE_AND_TIME").get(0)), action);		
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("TITLEIV_AGENCY").get(0),"Title IV Agency",action);
		String saveAndProceedTD = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_AND_PROCEED,saveAndProceedTD),saveAndProceedTD,action);
		
	
	}
	

}
