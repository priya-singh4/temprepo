package cares.cwds.salesforce.pom.folio;

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

public class FolioCaseClosure {
	
	private static final Logger logger = LoggerFactory.getLogger(FolioCaseClosure.class.getName());
	private static final String CASE_CLOSURE_TAB="CASE_CLOSURE_TAB";
	private static final String CASECLOSURE="Case Closure";
	private static final String SAVE_AND_PROCEED= "Save and Proceed";
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIOCASECLOSURE;

	LocalDateTime startTime = LocalDateTime.now();

	public FolioCaseClosure() {
	}

	public FolioCaseClosure(WebDriver wDriver) {
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
	
	public void navigateToCaseClosure( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Case Closure");
		action.setPageActionDescription("Navigate to Case Closure");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

		String caseClosureTabTD = testCaseDataSd.get(CASE_CLOSURE_TAB).get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, CASECLOSURE,caseClosureTabTD),caseClosureTabTD, action);
	}


	public void addClosureReasons( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add Closure Reason");
		action.setPageActionDescription("Add Closure Reason");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

	
		
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("FOLIO_STATUS").get(0),"Folio Status",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CASE_TYPE").get(0),"Case Type",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SERVICE_COMPONENT").get(0),"Service Component",action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Focus Child Age",testCaseDataSd.get("FOCUS_CHILD_AGE").get(0)), util.getRandom(testCaseDataSd.get("FOCUS_CHILD_AGE").get(0)), action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Is case closing due to a Court Ordered Termination?",testCaseDataSd.get("CASE_CLOSE_RB").get(0)),testCaseDataSd.get("CASE_CLOSE_RB").get(0),action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CLOSURE_REASONS").get(0),"Closure Reasons",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CLOSURE_SUB_REASONS").get(0),"Closure Sub Reasons",action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Legal Authority for Placement",testCaseDataSd.get("LEGAL_AUTHORITY").get(0)), "AutoDescription_" + util.getRandom(testCaseDataSd.get("DESCRIPTION").get(0)), action);
		String saveAndProceedTD = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_AND_PROCEED,saveAndProceedTD),saveAndProceedTD,action);
		
	
	}
	
	public void identifySupportPerson( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Case Closure");
		action.setPageActionDescription("Navigate to Case Closure");

		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

		String caseClosureTabTD = testCaseDataSd.get(CASE_CLOSURE_TAB).get(0);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SUPPORT_PERSON").get(0),"Identify Support Person(s)",action);
		Webkeywords.instance().click(driver, genericLocators.link(driver, CASECLOSURE,caseClosureTabTD),caseClosureTabTD, action);
	}
	

}
