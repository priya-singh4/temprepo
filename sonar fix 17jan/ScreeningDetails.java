package cares.cwds.salesforce.pom.screening;

import static java.lang.String.format;

import java.io.File;
import java.time.LocalDateTime;
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
import org.testng.Assert;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.excel.PoiWriteExcel;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.CustomException;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class ScreeningDetails {
	private static final Logger logger =LoggerFactory.getLogger(ScreeningDetails.class.getName());
	private static final String SCREENING_NAME = "Screening Name";
	private static final String SCREENING_NARRATIVE = "Screening Narrative";
	private static final String SAVE_AND_PROCEED= "Save and Proceed";
	private static final String REASON_FOR_CALL = "Reason for the Call";

	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime= LocalDateTime.now();

	String moduleName = ModuleConstants.SCREENING;
	String screenName = ScreenConstants.SCREENINGDETAILS;
	
	@FindBy(how = How.XPATH, using = "//lightning-icon[@icon-name='utility:error']//ancestor::div[@data-key='error']//span[contains(@class,'toastMessage')]")
    public WebElement recordLockedErrMsg;
	
	public ScreeningDetails(){ }
	
	public ScreeningDetails(WebDriver wDriver)
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
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Call Date and Time']/../../following-sibling::lightning-input//input)[1]")
	public WebElement callDateAndTime;
	
	@FindBy(how = How.XPATH, using = "//div[text() ='User Entered Address:']//following::lightning-input//label")
	public WebElement userEnteredAddress;
	
	@FindBy(xpath = "//*[text()='Screening ID']/..//lightning-formatted-text")
	WebElement screeningID;
	
	@FindBy(xpath = "//span[text()='SCAR Form Received']/parent::label")
	WebElement scarFormReceivedChkbox;
	
	@FindBy(xpath = "//*[contains(@data-name,'Screening_Name')]//child::input")
	WebElement screeningName;
	
	@FindBy(xpath = "//lightning-input-rich-text[@data-name='Call_Narrative__c']//div[@part='rich-text-editor-textarea']//div")
	WebElement  screeningNarrativeclick;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Resource Name']//parent::div//input")
    public WebElement resourceName;	
	
	String modifyText = "(//label[text()='%s']/../parent::div//following::lightning-combobox//descendant::button//span)[1]";
		
	public void navigateToScreeningDetails( String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Screening Details");
		action.setPageActionDescription("Navigate to Screening Details");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			String scrDetailsTabTD = testCaseDataSd.get("DETAILS_TAB").get(0);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Details",scrDetailsTabTD),scrDetailsTabTD,action);
	
	}
	
	public void clickNewScreening(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Click New Screening button");
		action.setPageActionDescription("Click New Screening button");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)),testCaseDataSd.get("NEW_BTN").get(0),action);
	}
	
	public void validateScreeningPage(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Validate New Screening Page");
		action.setPageActionDescription("Validate New Screening Page");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, SCREENING_NAME, testCaseDataSd.get("SCREENING_NAME_VERIFY").get(0)), testCaseDataSd.get("SCREENING_NAME_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textarea(driver, SCREENING_NARRATIVE,  testCaseDataSd.get("SCREENING_NARRATIVE_VERIFY").get(0)), testCaseDataSd.get("SCREENING_NARRATIVE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, REASON_FOR_CALL, testCaseDataSd.get("REASON_FOR_CALL_VERIFY").get(0)), testCaseDataSd.get("REASON_FOR_CALL_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, callDateAndTime, testCaseDataSd.get("CALL_DATE_TIME_VERIFY").get(0), action);
			
			//ReasonForCall WebElements

			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Does the youth identify as being in an unsafe environment?",testCaseDataSd.get("YOUTH_IDENTIFIED_UNSAFE_VERIFY").get(0)),testCaseDataSd.get("YOUTH_IDENTIFIED_UNSAFE_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Is the call pertaining to a Fatality/ Near Fatality of a child?",testCaseDataSd.get("PERTAINING_FATALITY_VERIFY").get(0)),testCaseDataSd.get("PERTAINING_FATALITY_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "CWS/CMS Referral ID",testCaseDataSd.get("CWS_CMS_REFERRALID_VERIFY").get(0)),testCaseDataSd.get("CWS_CMS_REFERRALID_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Resource Name",testCaseDataSd.get("RESOURCE_NAME_VERIFY").get(0)),testCaseDataSd.get("RESOURCE_NAME_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Phone Number",testCaseDataSd.get("PHONE_NUMBER_VERIFY").get(0)), testCaseDataSd.get("PHONE_NUMBER_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Address Line 2", testCaseDataSd.get("ADDRESS_LINE2_VERIFY").get(0)), testCaseDataSd.get("ADDRESS_LINE2_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "State", testCaseDataSd.get("STATE_VERIFY").get(0)), testCaseDataSd.get("STATE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver, "County", testCaseDataSd.get("COUNTY_VERIFY").get(0)), testCaseDataSd.get("COUNTY_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Monetary Assistance",testCaseDataSd.get("MONETARY_ASSIST_RB_VERIFY").get(0)),testCaseDataSd.get("MONETARY_ASSIST_RB_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Non-Contracted Services",testCaseDataSd.get("NONCONTRACTED_SERVICE_RB_VERIFY").get(0)),testCaseDataSd.get("NONCONTRACTED_SERVICE_RB_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Tangible Goods",testCaseDataSd.get("TANGIBLE_GOODS_RB_VERIFY").get(0)),testCaseDataSd.get("TANGIBLE_GOODS_RB_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textarea(driver, "Resource Description",testCaseDataSd.get("RESOURCE_DESC_VERIFY").get(0)),testCaseDataSd.get("RESOURCE_DESC_VERIFY").get(0), action);
			
			//Caller Type WebElements
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Anonymous",testCaseDataSd.get("ANONYMOUS_RB_VERIFY").get(0)),testCaseDataSd.get("ANONYMOUS_RB_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Non-Mandated Reporter",testCaseDataSd.get("NON_MANDATED_REPORTER_RB_VERIFY").get(0)),testCaseDataSd.get("NON_MANDATED_REPORTER_RB_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Mandated Reporter",testCaseDataSd.get("MANDATED_REPORTER_RB_VERIFY").get(0)),testCaseDataSd.get("MANDATED_REPORTER_RB_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.radiobutton(driver,"Self Report",testCaseDataSd.get("SELF_REPORT_RB_VERIFY").get(0)),testCaseDataSd.get("SELF_REPORT_RB_VERIFY").get(0),action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "SCAR Form Received Date",testCaseDataSd.get("SCAR_FORM_DATE_VERIFY").get(0)),  testCaseDataSd.get("SCAR_FORM_DATE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Reporter First Name",testCaseDataSd.get("REPORTER_FIRSTNAME_VERIFY").get(0)), testCaseDataSd.get("REPORTER_FIRSTNAME_VERIFY").get(0), action);	
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Reporter Last Name",testCaseDataSd.get("REPORTER_LASTNAME_VERIFY").get(0)), testCaseDataSd.get("REPORTER_LASTNAME_VERIFY").get(0), action);	
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Caller First Name",testCaseDataSd.get("CALLER_FIRSTNAME_VERIFY").get(0)), testCaseDataSd.get("CALLER_FIRSTNAME_VERIFY").get(0), action);	
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Caller Last Name",testCaseDataSd.get("CALLER_LASTNAME_VERIFY").get(0)), testCaseDataSd.get("CALLER_LASTNAME_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Caller’s Relationship to Victim(s)", testCaseDataSd.get("CALLER_RELATION_VICTIM_VERIFY").get(0)),testCaseDataSd.get("CALLER_RELATION_VICTIM_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Relationship Description",testCaseDataSd.get("RELATIONSHIP_DESC_VERIFY").get(0)), testCaseDataSd.get("RELATIONSHIP_DESC_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Preferred Method to Receive ERNRD", testCaseDataSd.get("PREFERRED_METHOD_ERNRD_VERIFY").get(0)),testCaseDataSd.get("PREFERRED_METHOD_ERNRD_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Email",testCaseDataSd.get("EMAIL_VERIFY").get(0)), testCaseDataSd.get("EMAIL_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Fax Number",testCaseDataSd.get("FAX_VERIFY").get(0)), testCaseDataSd.get("FAX_VERIFY").get(0), action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Method of Report", testCaseDataSd.get("METHOD_OF_REPORT_VERIFY").get(0)),testCaseDataSd.get("METHOD_OF_REPORT_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Mandated Reporter Type", testCaseDataSd.get("MANDATED_REPORTER_TYPE_VERIFY").get(0)),testCaseDataSd.get("MANDATED_REPORTER_TYPE_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Mandated Reporter - Other", testCaseDataSd.get("MANDATED_REPORTER_OTHER_VERIFY").get(0)),testCaseDataSd.get("MANDATED_REPORTER_OTHER_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Law Enforcement Badge ID", testCaseDataSd.get("LAW_ENFORCEMENT_BADGEID_VERIFY").get(0)),testCaseDataSd.get("LAW_ENFORCEMENT_BADGEID_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Report Number", testCaseDataSd.get("REPORT_NUMBER_VERIFY").get(0)),testCaseDataSd.get("REPORT_NUMBER_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Division/Station/Unit", testCaseDataSd.get("DIVISON_STATION_UNIT_VERIFY").get(0)),testCaseDataSd.get("DIVISON_STATION_UNIT_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Precinct", testCaseDataSd.get("PRECINCT_VERIFY").get(0)),testCaseDataSd.get("PRECINCT_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Preferred Language", testCaseDataSd.get("PREFERRED_LANGUAGE_VERIFY").get(0)),testCaseDataSd.get("PREFERRED_LANGUAGE_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver,"Interpreter Needed", testCaseDataSd.get("INTERPRETER_NEEDED_VERIFY").get(0)),  testCaseDataSd.get("INTERPRETER_NEEDED_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Interpreter Name", testCaseDataSd.get("INTERPRETER_NAME_VERIFY").get(0)),  testCaseDataSd.get("INTERPRETER_NAME_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver,"Employer/Organization Name", testCaseDataSd.get("EMP_ORGNAME_VERIFY").get(0)),  testCaseDataSd.get("EMP_ORGNAME_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver,"Family Informed of Report", testCaseDataSd.get("FAMILY_IMFORMED_VERIFY").get(0)),  testCaseDataSd.get("FAMILY_IMFORMED_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.checkbox(driver,"Family Confidentiality Waived", testCaseDataSd.get("FAMILY_CONFIDENTIALITY_VERIFY").get(0)),  testCaseDataSd.get("FAMILY_CONFIDENTIALITY_VERIFY").get(0), action);

			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Call back Required", testCaseDataSd.get("CALLBACK_VERIFY").get(0)),testCaseDataSd.get("CALLBACK_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Phone Type", testCaseDataSd.get("PHONE_TYPE_VERIFY").get(0)),testCaseDataSd.get("PHONE_TYPE_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.dropdown(driver,"Country Code", testCaseDataSd.get("COUNTY_CODE_VERIFY").get(0)),testCaseDataSd.get("COUNTY_CODE_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Reporter Phone Number",testCaseDataSd.get("REPORTER_PHONE_NUMBER_VERIFY").get(0)), testCaseDataSd.get("REPORTER_PHONE_NUMBER_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Caller Phone Number",testCaseDataSd.get("CALLER_PHONE_NUMBER_VERIFY").get(0)), testCaseDataSd.get("CALLER_PHONE_NUMBER_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Phone Number Ext",testCaseDataSd.get("PHONE_NUMBER_EXT_VERIFY").get(0)), testCaseDataSd.get("PHONE_NUMBER_EXT_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, "Add New",testCaseDataSd.get("ADD_NEW_BTN_VERIFY").get(0)),testCaseDataSd.get("ADD_NEW_BTN_VERIFY").get(0),action);

			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, " Address Line 1",testCaseDataSd.get("ADDRESS_LINE1_VERIFY").get(0)),util.getRandom(testCaseDataSd.get("ADDRESS_LINE1_VERIFY").get(0)), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "City",testCaseDataSd.get("CITY_VERIFY").get(0)), testCaseDataSd.get("CITY_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.textbox(driver, "Zip Code", testCaseDataSd.get("ZIP_CODE_VERIFY").get(0)), testCaseDataSd.get("ZIP_CODE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, "Validate Address",testCaseDataSd.get("VALIDATE_ADDRESS_BTN_VERIFY").get(0)),testCaseDataSd.get("VALIDATE_ADDRESS_BTN_VERIFY").get(0),action);
			Webkeywords.instance().verifyElementDisplayed(driver, userEnteredAddress,testCaseDataSd.get("USER_ENTERED_ADDRESS_VERIFY").get(0),action);		
			
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Created By",testCaseDataSd.get("CREATEDBY_VERIFY").get(0)), testCaseDataSd.get("CREATEDBY_VERIFY").get(0), action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Created Date",testCaseDataSd.get("CREATED_DATE_VERIFY").get(0)), testCaseDataSd.get("CREATED_DATE_VERIFY").get(0), action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Modified Date",testCaseDataSd.get("MODIFIED_DATE_VERIFY").get(0)), testCaseDataSd.get("MODIFIED_DATE_VERIFY").get(0), action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, "Last Modified By",testCaseDataSd.get("MODIFIEDBY_VERIFY").get(0)), testCaseDataSd.get("MODIFIEDBY_VERIFY").get(0), action);
			
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.button(driver, SAVE_AND_PROCEED, testCaseDataSd.get("SAVE_AND_PROCEED_VERIFY").get(0)), testCaseDataSd.get("SAVE_AND_PROCEED_VERIFY").get(0), action);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
	
	}
	
	public void enterScreeningDetails( String scriptIteration, String pomIteration) {
		
		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Screening Details");
		action.setPageActionDescription("Enter Screening Details");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
				
			Webkeywords.instance().click(driver, screeningNarrativeclick, "" , action);	
			WebElement screeningNarrative = driver.findElement(By.xpath("//div[@role='textbox' and @aria-label='Compose text']"));
			screeningNarrative.sendKeys( util.getRandom(testCaseDataSd.get("SCREENING_NARRATIVE").get(0)));
			
			Webkeywords.instance().setDateText(driver, callDateAndTime, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("CALL_DATE_AND_TIME").get(0)), action);		
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, SCREENING_NAME, testCaseDataSd.get("SCREENING_NAME").get(0)), util.getRandom(testCaseDataSd.get("SCREENING_NAME").get(0)), action);
			

			selectReasonForCall(testCaseDataSd,action);
			selectCallerType(testCaseDataSd,action);
			enterCommunityReferralInfo(testCaseDataSd,action);

      		}
	
public void submitScreeningDetails( String scriptIteration, String pomIteration)  {
		
		PageDetails action = new PageDetails();
		action.setPageActionName("Submit Screening Details");
		action.setPageActionDescription("Submit Screening Details");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			String saveAndProceedTD = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);
			Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_AND_PROCEED,saveAndProceedTD),saveAndProceedTD,action);
			Webkeywords.instance().pause();
			Webkeywords.instance().waitElementToBeVisible(driver, screeningID);
			SalesforceConstants.setConstantValue("SCR_ID"+pomIteration, screeningID.getText());
			if(!testCaseDataSd.get("CAPTURE_SCRID").get(0).toLowerCase().equalsIgnoreCase("n/a")) {
				String[] fields = {"SCREENING_ID","SC_TSP"};
				String fielPath = TestRunSettings.getScreeningIdsFilePath() + File.separator + TestRunSettings.getScreeningIdsFileName();
				PoiWriteExcel.writeExcelData(fielPath, "ScreeningData", fields, screeningID.getText());
			}
			SalesforceCommon.captureRecordURL(driver,"SCR");
			
			
	}

	public void validateScarFormReceivedCheckbox(String scriptIteration, String pomIteration) throws CustomException {
		PageDetails action = new PageDetails();
		action.setPageActionName("Validate Scar Form Received Checkbox");
		action.setPageActionDescription("Validate Scar Form Received Checkbox");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().selectValueInputDropdown(driver,"Abuse/Neglect Referral",REASON_FOR_CALL,action);
			Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Anonymous",""),"",action);
			String scarFormXpath = "//span[text()='SCAR Form Received']/parent::label";
			String scarFormChkBoxTD =  testCaseDataSd.get("SCAR_FORM_CHKBOX_VERIFY").get(0);
			Webkeywords.instance().verifyelementnotdisplayed(driver, scarFormXpath,scarFormChkBoxTD, action);
			Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Non-Mandated Reporter",""),"",action);
			Webkeywords.instance().verifyelementnotdisplayed(driver, scarFormXpath, scarFormChkBoxTD, action);
			Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Mandated Reporter",""),"",action);
			Webkeywords.instance().verifyElementDisplayed(driver, scarFormReceivedChkbox,  scarFormChkBoxTD, action);
			Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Self Report",""),"",action);
			Webkeywords.instance().verifyelementnotdisplayed(driver, scarFormXpath, scarFormChkBoxTD, action);
		
	}
	
	public void verifyScreeningDetailsNonEditable(String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Validate Screening Details Not Editable");
		action.setPageActionDescription("Validate Screening Details Not Editable");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String scrDetailsTabTD = testCaseDataSd.get("DETAILS_TAB").get(0);
			String saveAndProceedTD = testCaseDataSd.get("SAVE_AND_PROCEED_BTN").get(0);

			Webkeywords.instance().click(driver, genericLocators.button(driver, "Details",scrDetailsTabTD),scrDetailsTabTD,action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE_AND_PROCEED,saveAndProceedTD),saveAndProceedTD,action);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			verifyRecordLockedMsg(testCaseDataSd.get("SCRLOCK_TOASTMSG_VERIFY").get(0));
			}
	
	public void editReasonForTheCall(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Edit reason for the call");
		action.setPageActionDescription("Edit reason for the call");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			selectReasonForCall(testCaseDataSd,action);	
		}
	
	public void editCallerType(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Edit Caller Type");
		action.setPageActionDescription("Edit  Caller Type");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			selectCallerType(testCaseDataSd,action);			
		
		
	}
	
	public void editCommunityReferralInformation(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Edit Community Referral Info");
		action.setPageActionDescription("Edit  Community Referral Info");
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		enterCommunityReferralInfo(testCaseDataSd,action);			
	}
	
	///////////////////////////////////////////////Private Methods////////////////////////////////////////
	
	private void selectReasonForCall(Map<String,ArrayList<String>> testCaseDataSd, PageDetails action) {
		String addressLine1TD = testCaseDataSd.get("ADDRESS_LINE1").get(0);
		String resourceNameTD = testCaseDataSd.get("RESOURCE_NAME").get(0);
		String zipCodeTD = testCaseDataSd.get("ZIP_CODE").get(0);
		
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("REASON_FOR_CALL").get(0),REASON_FOR_CALL,action);
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("YOUTH_IDENTIFIED_UNSAFE").get(0),"Does the youth identify as being in an unsafe environment?",action);
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("PERTAINING_FATALITY_NEAR_FATALITY_OF_CHILD").get(0),"Is the call pertaining to a possible Fatality/Possible near fatality of a child?",action);
		Webkeywords.instance().click(driver, resourceName,resourceNameTD,action);
		Webkeywords.instance().click(driver,genericLocators.link(driver, "Child Care Assistance",resourceNameTD),resourceNameTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Phone Number",testCaseDataSd.get("PHONE_NUMBER").get(0)), util.getRandom(testCaseDataSd.get("PHONE_NUMBER").get(0)), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Address Line 1",addressLine1TD), util.getRandom(addressLine1TD), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "City",testCaseDataSd.get("CITY").get(0)),testCaseDataSd.get("CITY").get(0), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver,"Zip Code",zipCodeTD),zipCodeTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Resource Description",testCaseDataSd.get("RESOURCE_DESC").get(0)),testCaseDataSd.get("RESOURCE_DESC").get(0), action);
	}
	private void selectCallerType (Map<String, ArrayList<String>> testCaseDataSd, PageDetails action){
		String addressLine1TD = testCaseDataSd.get("ADDRESS_LINE1").get(0);
		String zipCodeTD = testCaseDataSd.get("ZIP_CODE").get(0);

		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Anonymous",testCaseDataSd.get("ANONYMOUS_RB").get(0)),testCaseDataSd.get("ANONYMOUS_RB").get(0),action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Non-Mandated Reporter",testCaseDataSd.get("NON_MANDATED_REPORTER_RB").get(0)),testCaseDataSd.get("NON_MANDATED_REPORTER_RB").get(0),action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Mandated Reporter",testCaseDataSd.get("MANDATED_REPORTER_RB").get(0)),testCaseDataSd.get("MANDATED_REPORTER_RB").get(0),action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Self Report",testCaseDataSd.get("SELF_REPORT_RB").get(0)),testCaseDataSd.get("SELF_REPORT_RB").get(0),action);
		
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Reporter First Name",testCaseDataSd.get("REPORTER_FIRSTNAME").get(0)), util.getRandom(testCaseDataSd.get("REPORTER_FIRSTNAME").get(0)), action);	
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Reporter Last Name",testCaseDataSd.get("REPORTER_LASTNAME").get(0)), util.getRandom(testCaseDataSd.get("REPORTER_LASTNAME").get(0)), action);	
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("PREFERRED_METHOD_ERNRD").get(0),"Preferred Method to Receive ERNRD",action);
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("METHOD_OF_REPORT").get(0),"Method of Report",action);
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("MANDATED_REPORTER_TYPE").get(0),"Mandated Reporter Type",action);
		Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("CALLBACK").get(0),"Call back Required",action);
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("PHONE_TYPE").get(0),"Phone Type",action);
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("COUNTY_CODE").get(0),"Country Code",action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Reporter Phone Number",testCaseDataSd.get("REPORTER_PHONE_NUMBER").get(0)), util.getRandom(testCaseDataSd.get("REPORTER_PHONE_NUMBER").get(0)), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Caller Phone Number",testCaseDataSd.get("CALLER_PHONE_NUMBER").get(0)), util.getRandom(testCaseDataSd.get("CALLER_PHONE_NUMBER").get(0)), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Address Line 1",addressLine1TD),util.getRandom(addressLine1TD), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "City",testCaseDataSd.get("CITY").get(0)), testCaseDataSd.get("CITY").get(0), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Zip Code", zipCodeTD), zipCodeTD, action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Validate Address",testCaseDataSd.get("VALIDATE_ADDRESS_BTN").get(0)),testCaseDataSd.get("VALIDATE_ADDRESS_BTN").get(0),action);
		Webkeywords.instance().click(driver, userEnteredAddress,testCaseDataSd.get("USER_ENTERED_ADDRESS").get(0),action);		
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Email",testCaseDataSd.get("EMAIL").get(0)), testCaseDataSd.get("EMAIL").get(0), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Fax Number",testCaseDataSd.get("FAX").get(0)), testCaseDataSd.get("FAX").get(0), action);
	}
	

	private void enterCommunityReferralInfo(Map<String,ArrayList<String>> testCaseDataSd, PageDetails action) {
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("CRI_RESOURCE_TYPE").get(0), "Resource Type",action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Address Description",testCaseDataSd.get("CRI_ADDRESS_DESC").get(0)), util.getRandom(testCaseDataSd.get("CRI_ADDRESS_DESC").get(0)), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Resource Name",testCaseDataSd.get("CRI_RESOURCE_NAME").get(0)), util.getRandom(testCaseDataSd.get("CRI_RESOURCE_NAME").get(0)), action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Monetary Assistance",testCaseDataSd.get("MONETARY_ASSISTANCE_RB").get(0)),testCaseDataSd.get("MONETARY_ASSISTANCE_RB").get(0),action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Non-Contracted Services",testCaseDataSd.get("NON_CONTRACTED_SERVICES_RB").get(0)),testCaseDataSd.get("NON_CONTRACTED_SERVICES_RB").get(0),action);
		Webkeywords.instance().click(driver, genericLocators.radiobutton(driver,"Tangible Goods",testCaseDataSd.get("TANGIBLE_GOODS_RB").get(0)),testCaseDataSd.get("TANGIBLE_GOODS_RB").get(0),action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Resource Description",testCaseDataSd.get("CRI_RESOURCE_DESC").get(0)),testCaseDataSd.get("CRI_RESOURCE_DESC").get(0), action);
	}
	
	public void addScreeningDetailsTest(String scriptIteration, String pomIteration){

		PageDetails action = new PageDetails();
		action.setPageActionName("Add Screening Details");
		action.setPageActionDescription("Add Screening Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		
			String screeningNarrativeTD = testCaseDataSd.get("SCREENING_NARRATIVE").get(0);
			String screeningNameTD = testCaseDataSd.get("SCREENING_NAME").get(0);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, SCREENING_NARRATIVE,""), util.getRandom(screeningNarrativeTD), action);	
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, SCREENING_NAME,""), util.getRandom(screeningNameTD), action);
			
	}
	
	public void verifyPreviousValues(String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Verify Previous Value Details");
		action.setPageActionDescription("Verify Previous Value Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		
		Webkeywords.instance().verifyElementDisplayed(driver,screeningName ,testCaseDataSd.get("SCREENING_NAME").get(0) , action);
		
		Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("METHOD_OF_REPORT").get(0),"Method of Report",action);
		Webkeywords.instance().selectValueInputDropdown(driver, testCaseDataSd.get("CALLBACK").get(0),"Call back Required",action);
	}
	
	public void verifyUpdatedCallerDetails(String scriptIteration, String pomIteration) throws CustomException {
		PageDetails action = new PageDetails();
		action.setPageActionName("Verify Screening Details");
		action.setPageActionDescription("Verify Screening Details");

			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String actualMethodReportValue = testCaseDataSd.get("METHOD_OF_REPORT").get(0);
		WebElement expectedMethodReportValue = driver.findElement(By.xpath(format(modifyText,testCaseDataSd.get("METHOD_REPORT_TEXT").get(0))));
		
		String actualCallBackValue = testCaseDataSd.get("CALLBACK").get(0);
		WebElement expectedCallBackValue = driver.findElement(By.xpath(format(modifyText,testCaseDataSd.get("CALL_BACK_REQUIRED").get(0))));
		
		Webkeywords.instance().verifyDropdownText(driver,expectedMethodReportValue,actualMethodReportValue, action);
		Webkeywords.instance().verifyDropdownText(driver,expectedCallBackValue,actualCallBackValue, action);
	}

	private void verifyRecordLockedMsg(String flag) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			Assert.assertEquals("This record is locked. If you need to edit it, contact your admin.",recordLockedErrMsg.getText(), "Screening Details Record Locked Error Message is not displayed");
		}
	}
}