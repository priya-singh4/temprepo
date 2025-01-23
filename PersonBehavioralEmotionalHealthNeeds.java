package cares.cwds.salesforce.pom.person;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;

import cares.cwds.salesforce.utilities.web.Webkeywords;

public class PersonBehavioralEmotionalHealthNeeds {
	private static final Logger logger =LoggerFactory.getLogger(PersonBehavioralEmotionalHealthNeeds.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	

	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONBEHAVIORALEMOTIONALHEALTHNEEDS;
	
	public PersonBehavioralEmotionalHealthNeeds(){ }
	
	public PersonBehavioralEmotionalHealthNeeds(WebDriver wDriver)
	{
		initializePage(wDriver);
	}
	
	public void initializePage(WebDriver wDriver) 
	    {
		logger.info(this.getClass().getName());
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	    }
	
	@FindBy(xpath = "//*[@title='Health Information ID']/..//lightning-formatted-text")
	WebElement behavioralEmotionalHealthNeedsID;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Source Observed Concern']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateSourceObservedConcern;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Reported to Worker']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateReportedToWorker;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Related Health Record']/..//input")
	public WebElement relatedHealthRecord;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Health Need']/..//input")
	public WebElement healthNeed;
	
	private String checkboxXpath = "//span[normalize-space()='%s']//label";
	private String healthInfoTypeXpath = "//legend[text()='New Health Information']//parent::fieldset[@role='radiogroup']//span[normalize-space()='%s']";
	private String newButtonXpath = "//*[@apiname='%s'][@title='New']//button[text()='New']";
	
	
	public void clickNewBehavioralEmotionalRecordType(String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Click New Behavioral Emotional Record Type");
		action.setPageActionDescription("Click New Behavioral Emotional Record Type");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BE_NEW_BTN").get(0),newButtonXpath,"New_Behavior_Emotional_Health"), testCaseDataSd.get("BE_NEW_BTN").get(0),action);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_HEALTHNEEDS_RB").get(0),healthInfoTypeXpath,"Behavioral and Emotional Health Needs"),testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_HEALTHNEEDS_RB").get(0),action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", testCaseDataSd.get("NEXT_BTN").get(0)), testCaseDataSd.get("NEXT_BTN").get(0),action);
	}
	
	public void enterBehavioralEmotionalHealthNeedsDetails(  String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		action.setPageActionName("Enter BehavioralEmotionalHealthNeeds Details");
		action.setPageActionDescription("Enter BehavioralEmotionalHealthNeeds Details");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
			String healthNeedTD = testCaseDataSd.get("HEALTH_NEED").get(0);	

			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("BEHAVIORAL_EMOTIONAL_HEALTH_NEED").get(0),"Behavioral and Emotional Health Need",action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Name",testCaseDataSd.get("PROVIDER_NAME").get(0)),util.getRandom(testCaseDataSd.get("PROVIDER_NAME").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Phone",testCaseDataSd.get("PROVIDER_PHONE").get(0)),util.getRandom(testCaseDataSd.get("PROVIDER_PHONE").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Source Name",testCaseDataSd.get("SOURCE_NAME").get(0)),util.getRandom(testCaseDataSd.get("SOURCE_NAME").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Source Relationship to Focus Child",testCaseDataSd.get("SOURCE_RELATIONSHIP_FOCUSCHILD").get(0)),util.getRandom(testCaseDataSd.get("SOURCE_RELATIONSHIP_FOCUSCHILD").get(0)), action);

			Webkeywords.instance().setDateText(driver, dateSourceObservedConcern, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("DATE_SOURCE_OBSERVED_CONCERN").get(0)), action);		
			Webkeywords.instance().setDateText(driver, dateReportedToWorker, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("DATE_REPORTED_TO_WORKER").get(0)), action);		
			
			Webkeywords.instance().click(driver, healthNeed,healthNeedTD,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, healthNeedTD,healthNeedTD),healthNeedTD,action);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("CONFIDENTIAL_CB").get(0),checkboxXpath, "Confidential"),testCaseDataSd.get("CONFIDENTIAL_CB").get(0),action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),checkboxXpath, "Include as HEP alert?"),testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),action);
			
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Summary of Current Health Condition",testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), util.getRandom(testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), util.getRandom(testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "HEP Alert Description",testCaseDataSd.get("HEP_ALERT_DESCRIPTION").get(0)), util.getRandom(testCaseDataSd.get("HEP_ALERT_DESCRIPTION").get(0)), action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
			
			Webkeywords.instance().waitElementToBeVisible(driver, behavioralEmotionalHealthNeedsID);
			SalesforceConstants.setConstantValue("Person BehavioralEmotionalHealthNeedsID", behavioralEmotionalHealthNeedsID.getText());	
	}
	
	public WebElement getElementBasedOnFlag(String flag, String checkboxXpath, String name) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(checkboxXpath,name)));
		}
		else
			return null;
	}
}
