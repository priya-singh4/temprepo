package cares.cwds.salesforce.pom.folio.placement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;

import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class OverstayReportDetails {
	
	private static final Logger logger = LoggerFactory.getLogger(OverstayReportDetails.class.getName());
	private static final String OVERSTAY_REPORT_TAB="OVERSTAY_REPORT_TAB";
	private static final String OVERSTAYREPORT="Overstay Report";
	private static final String SAVE_AND_PROCEED= "Save and Proceed";
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIOPLACEMENT;

	LocalDateTime startTime = LocalDateTime.now();

	public OverstayReportDetails() {
	}

	public OverstayReportDetails(WebDriver wDriver) {
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
	
	public void navigateToOverstayReport( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Overstay Report Details");
		action.setPageActionDescription("Navigate to Overstay Report Details");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

		String overStayReportTabTD = testCaseDataSd.get(OVERSTAY_REPORT_TAB).get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, OVERSTAYREPORT,overStayReportTabTD),overStayReportTabTD, action);
	}
	
	public void clickNew(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Create New Overstay Report");
		action.setPageActionDescription("Create New Overstay Report ");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)),testCaseDataSd.get("NEW_BTN").get(0),action);
	}



	public void addInformation( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add Information");
		action.setPageActionDescription("Add Information");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CARE_SETTINGS").get(0),"Alternative Care Settings",action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Overstay Reason and Circumstances",testCaseDataSd.get("OVERSTAY_REASON").get(0)), "AutoDescription_" + util.getRandom(testCaseDataSd.get("OVERSTAY_REASON").get(0)), action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Steps Taken to Identify Placement",testCaseDataSd.get("STEPS_TAKEN").get(0)), "AutoDescription_" + util.getRandom(testCaseDataSd.get("STEPS_TAKEN").get(0)), action);
		String saveAndProceedTD = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_AND_PROCEED,saveAndProceedTD),saveAndProceedTD,action);
		
	
	}
	

}
