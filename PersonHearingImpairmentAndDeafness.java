package cares.cwds.salesforce.pom.person;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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

public class PersonHearingImpairmentAndDeafness {
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime= LocalDateTime.now();

	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONHEARINGIMPAIRMENTANDDEAFNESS;
	
	public PersonHearingImpairmentAndDeafness(){ }
	
	public PersonHearingImpairmentAndDeafness(WebDriver wDriver)
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
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date of Diagnosis']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateOfDiagnosis;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Date Reported to Worker']/../../following-sibling::lightning-input//input)[1]")
	public WebElement dateReportedToWorker;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Health Need']/..//input")
	public WebElement healthNeed;
		
	@FindBy(xpath = "//*[@title='Health Information ID']/..//lightning-formatted-text")
	WebElement personHearingImpairmentAndDeafnessID;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Is Day-to-Day Functioning Affected?']/../../following::lightning-radio-group//span[text()='No'])[1]")
	public WebElement functioningAffected ;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='s Educational Performance Affected?']/../../following::lightning-radio-group(//label[text()='s Educational Performance Affected?']/../../following::lightning-radio-group//span[text()='No'])[1]//span[text()='No'])[1]")
	public WebElement performanceAffected ;
	
	private String checkboxXpath = "//span[normalize-space()='%s']//label";
	private String healthInfoTypeXpath = "//legend[text()='New Health Information']//parent::fieldset[@role='radiogroup']//span[normalize-space()='%s']";
	private String newButtonXpath = "//*[@apiname='%s'][@title='New']//button[text()='New']";
		
	public void clickNewPhyscialHealthRecordType(String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		action.setPageActionName("Click New PhysicalHealth Record Type");
		action.setPageActionDescription("Click New PhysicalHealth Record Type");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("PH_NEW_BTN").get(0),newButtonXpath,"New_PhysicalHealth"), testCaseDataSd.get("PH_NEW_BTN").get(0),action);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("HEARING_IMPAIRMENT_DEAFNESS_RB").get(0),healthInfoTypeXpath,"Hearing Impairment and Deafness"),testCaseDataSd.get("HEARING_IMPAIRMENT_DEAFNESS_RB").get(0),action);
				
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", testCaseDataSd.get("NEXT_BTN").get(0)), testCaseDataSd.get("NEXT_BTN").get(0),action);
	}
	
	public void enterPersonHearingImpairmentAndDeafnessDetails(  String scriptIteration, String pomIteration)   {
		PageDetails action = new PageDetails();
		action.setPageActionName("Enter PersonHearingImpairmentAndDeafness Details");
		action.setPageActionDescription("Enter PersonHearingImpairmentAndDeafness Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		
			String healthNeedTD = testCaseDataSd.get("HEALTH_NEED").get(0);
			
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("HEARING_IMPAIRMENT_TYPE").get(0),"Hearing Impairment Type",action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Medical Office Information",testCaseDataSd.get("MEDICAL_OFFICE_INFORMATION").get(0)),util.getRandom(testCaseDataSd.get("MEDICAL_OFFICE_INFORMATION").get(0)), action);
			
			Webkeywords.instance().setDateText(driver, dateOfDiagnosis, CommonOperations.getDate("M/d/yyyy",testCaseDataSd.get("DATE_OF_DIAGNOSIS").get(0)), action);
			
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Name",testCaseDataSd.get("PROVIDER_NAME").get(0)),util.getRandom(testCaseDataSd.get("PROVIDER_NAME").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Phone",testCaseDataSd.get("PROVIDER_PHONE").get(0)),util.getRandom(testCaseDataSd.get("PROVIDER_PHONE").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Source Name",testCaseDataSd.get("SOURCE_NAME").get(0)),util.getRandom(testCaseDataSd.get("SOURCE_NAME").get(0)), action);
			Webkeywords.instance().setDateText(driver, dateReportedToWorker, CommonOperations.getDate("M/d/yyyy",testCaseDataSd.get("DATE_REPORTED_TO_WORKER").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Source Relationship to Focus Child",testCaseDataSd.get("SOURCE_RELATIONSHIP_FOCUSCHILD").get(0)),util.getRandom(testCaseDataSd.get("SOURCE_RELATIONSHIP_FOCUSCHILD").get(0)), action);
						
			Webkeywords.instance().click(driver, healthNeed,healthNeedTD,action);
			Webkeywords.instance().click(driver, genericLocators.link(driver, healthNeedTD,healthNeedTD),healthNeedTD,action);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),checkboxXpath, "Include as HEP Alert?"),testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),action);
			Webkeywords.instance().click(driver, functioningAffected, testCaseDataSd.get("FUNCTIONAL_AFFECTED").get(0), action);
			Webkeywords.instance().click(driver, performanceAffected, testCaseDataSd.get("PERFORMANCE_AFFECTED").get(0), action);
			
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("CONFIDENTIAL_CB").get(0),checkboxXpath, "Confidential"),testCaseDataSd.get("CONFIDENTIAL_CB").get(0),action);
						
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), util.getRandom(testCaseDataSd.get("ADDITIONAL_INFORMATION").get(0)), action);
			
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Summary of Current Health Condition",testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), util.getRandom(testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Related Health Record",testCaseDataSd.get("RELATED_HEALTH_RECORD").get(0)), util.getRandom(testCaseDataSd.get("SERVICES_PROVIDED_TREATMENT_GOALS").get(0)), action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
			Webkeywords.instance().pause();
			Webkeywords.instance().waitElementToBeVisible(driver,personHearingImpairmentAndDeafnessID);
			SalesforceConstants.setConstantValue("Person_PersonHearingImpairmentAndDeafnessID", personHearingImpairmentAndDeafnessID.getText());	
	}

	public WebElement getElementBasedOnFlag(String flag, String checkboxXpath, String name) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(checkboxXpath,name)));
		}
		else
			return null;
	}

}
