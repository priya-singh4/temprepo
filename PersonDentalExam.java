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

public class PersonDentalExam {
	private static final Logger logger =LoggerFactory.getLogger(PersonDentalExam.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	
	
	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONDENTALEXAM;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Exam/Treatment Date']/../../following-sibling::lightning-input//input")
	public WebElement treatmentDate;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Date Reported to Worker']//following-sibling::div//input")
	public WebElement dateReported;

	@FindBy(how = How.XPATH, using = "//label[text()='Next Scheduled Date']/../../following-sibling::lightning-input//input")
	public WebElement nextScheduledDate;
	
	@FindBy(xpath = "//*[@title='Health Information ID']/..//lightning-formatted-text")
	WebElement dentalExamID;
	
	
	public PersonDentalExam(){ }
	
	public PersonDentalExam(WebDriver wDriver)
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
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("DENTAL_EXAMS_RB").get(0),healthInfoTypeXpath,"Dental Exams"),testCaseDataSd.get("DENTAL_EXAMS_RB").get(0),action);
			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", testCaseDataSd.get("NEXT_BTN").get(0)), testCaseDataSd.get("NEXT_BTN").get(0),action);
	}
	
	public void personDentalExamDetails(  String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		action.setPageActionName("Person Dental Exam Details");
		action.setPageActionDescription("Person Dental Exam Details");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();						
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("DENTAL_EXAM_TYPE").get(0),"Dental Exam Type",action);
			Webkeywords.instance().setDateText(driver, treatmentDate, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("TREATMENT_DATE").get(0)), action);	
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Diagnosed Condition",testCaseDataSd.get("DIAGNOSED_CONDITION").get(0)), util.getRandom(testCaseDataSd.get("DIAGNOSED_CONDITION").get(0)), action);	
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Treatment Received",testCaseDataSd.get("TREATMENT_RECEIVED").get(0)), util.getRandom(testCaseDataSd.get("TREATMENT_RECEIVED").get(0)), action);	
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Name",testCaseDataSd.get("PROVIDER_NAME").get(0)), util.getRandom(testCaseDataSd.get("PROVIDER_NAME").get(0)), action);	
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Provider Phone",testCaseDataSd.get("PROVIDER_PHONE").get(0)), util.getRandom(testCaseDataSd.get("PROVIDER_PHONE").get(0)), action);
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("REFERRAL_NEEDED").get(0),"Referral Needed",action);
			Webkeywords.instance().setDateText(driver, dateReported, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("DATE_REPORTED").get(0)), action);	
			Webkeywords.instance().setDateText(driver, nextScheduledDate, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("NEXT_SCHEDULED_DATE").get(0)), action);	
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("HEALTH_NEED").get(0),"Health Need",action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Include as HEP Alert?",testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0)),testCaseDataSd.get("INCLUDE_AS_HEP_ALERT_CB").get(0),action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Confidential",testCaseDataSd.get("CONFIDENTIAL_CB").get(0)),testCaseDataSd.get("CONFIDENTIAL_CB").get(0),action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Summary of Current Health Condition",testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), util.getRandom(testCaseDataSd.get("SUMMARY_CURRENT_HEALTH_CONDITION").get(0)), action);	
			Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("RELATED_HEALTH_RECORD").get(0),"Related Health Record",action);							
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Additional Information",testCaseDataSd.get("ADDITIONAL_INFO").get(0)), util.getRandom(testCaseDataSd.get("ADDITIONAL_INFO").get(0)), action);			
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
			Webkeywords.instance().pause();
			Webkeywords.instance().waitElementToBeVisible(driver, dentalExamID);
			SalesforceConstants.setConstantValue("Person LegalIssuesID", dentalExamID.getText());
	}
	
	public WebElement getElementBasedOnFlag(String flag, String checkboxXpath, String name) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(checkboxXpath,name)));
		}
		else
			return null;
	}

}
