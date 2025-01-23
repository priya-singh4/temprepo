package cares.cwds.salesforce.pom.person;

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
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class PersonSearch {
	
	private WebDriver driver;
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONSEARCH;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized')]//span[@slot='header']//lightning-formatted-text[text()='Person Information']")
	public WebElement personInfoHeader;
	
	public PersonSearch(){ }
	
	public PersonSearch(WebDriver wDriver)
	{
		initializePage(wDriver);
	}

	public void initializePage(WebDriver wDriver) 
    {
    	 driver = wDriver;
         PageFactory.initElements(driver, this);
         ReportCommon testStepLogDetails = new ReportCommon(); 
         testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
         genericLocators = new GenericLocators(wDriver);
    }
	
	public void searchAndAddPerson( String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Search and Add New Person");
		action.setPageActionDescription("Search and Add New Person");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
					TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "First Name",testCaseDataSd.get("FIRST_NAME").get(0)),util.getRandom(testCaseDataSd.get("FIRST_NAME").get(0)), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Last Name",testCaseDataSd.get("LAST_NAME").get(0)),util.getRandom(testCaseDataSd.get("LAST_NAME").get(0)), action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Search" ,testCaseDataSd.get("SEARCH_BTN").get(0)),testCaseDataSd.get("SEARCH_BTN").get(0),action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "New Person" ,testCaseDataSd.get("NEW_PERSON_BTN").get(0)),testCaseDataSd.get("NEW_PERSON_BTN").get(0),action);
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SEX_AT_BIRTH").get(0),"Sex at Birth",action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save" ,testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
		Webkeywords.instance().waitElementToBeVisible(driver, personInfoHeader);
		Webkeywords.instance().pause();
		SalesforceCommon.captureRecordURL(driver,"PERSON");
		
	}
}
