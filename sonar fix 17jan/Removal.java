package cares.cwds.salesforce.pom.folio.placement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;

import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class Removal {
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.REMOVAL;
	LocalDateTime startTime= LocalDateTime.now();
	
	private static final String REMOVALTAB = "Removal";
	public Removal(){ }
	
	public Removal(WebDriver wDriver)
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
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Removal Date & Time']/../../following-sibling::lightning-input//input)[1]")
	public WebElement removalDateAndTime;
	
	
	public void navigateToRemovalTab(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Navigate To Removal Tab");
		action.setPageActionDescription("Navigate To Removal Tab");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String removalTD =  testCaseDataSd.get("RELEASES_OF_INFORMATION_AND_CONSENT_FORM_TAB").get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, REMOVALTAB, removalTD),removalTD,  action);
		
	}

	public void addRemovalDetails(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Add Removal Details");
		action.setPageActionDescription("Add Removal Details");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		Webkeywords.instance().pause();
		Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)), testCaseDataSd.get("NEW_BTN").get(0),action);
		Webkeywords.instance().pause();
		Webkeywords.instance().setDateText(driver, removalDateAndTime, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("REMOVAL_DATE_AND_TIME").get(0)),  action);		
		
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PRIMARY_REASON_FOR_REMOVAL").get(0),"Primary Reason for Removal",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ENVIRONMENT_AT_REMOVAL").get(0),"Environment at Removal",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PERSON_PRESENT_AT_THE_HOME").get(0),"Persons Present at the Home",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SECONDARY_REASON_FOR_REMOVAL").get(0),"Secondary Reason for Removal",action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("PERSON_WHO_LIVED_WITH_THE_CHILD_AT_ANY_POINT").get(0),"Person who lived with the child at any point in the last 6 months",action);	
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENTATION_STATUS").get(0),"Documentation Status",action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
		
		
	}
}
