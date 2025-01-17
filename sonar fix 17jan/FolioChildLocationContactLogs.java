package cares.cwds.salesforce.pom.folio.placement;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
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
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class FolioChildLocationContactLogs {
	private static final Logger logger =LoggerFactory.getLogger(FolioChildLocationContactLogs.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIOCHILDLOCATIONCONTACTLOGS;
	
	private static final String CONTACT_PURPOSE= "Contact Purpose";
	private static final String CONTACT_METHOD= "Contact Method";
	private static final String CONTACT_STATUS= "Contact Status";
	private static final String LOCATION= "Location";
	private static final String NARRATIVE= "Narrative";
	private static final String SAVE= "Save";
	
	
	
	public FolioChildLocationContactLogs(){ }
	
	public FolioChildLocationContactLogs(WebDriver wDriver)
	{
		initializePage(wDriver);
	}
	
	public void initializePage(WebDriver wDriver) 
	    {
		logger.info(this.getClass().getName());
	    	 driver = wDriver;
	         PageFactory.initElements(driver, this);
	         ReportCommon testStepLogDetails = new ReportCommon(); 
	         testStepLogDetails.logModuleAndScreenDetails(moduleName, screenName);
	         genericLocators = new GenericLocators(wDriver);
	    }
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Contact Start Date/Time']/../../following-sibling::lightning-input//input)[1]")
	public WebElement contactStartDateTime;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Contact End Date/Time']/../../following-sibling::lightning-input//input)[2]")
	public WebElement contactEndDateTime;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Status']/../../following-sibling::lightning-combobox//button")
	public WebElement contactStatus;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Status']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> contactStatusList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Purpose']/../../following-sibling::lightning-combobox//button")
	public WebElement contactPurpose;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Contact Purpose']/../../following::lightning-combobox//span//span")
	public List<WebElement> contactPurposeList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Participant']/..//input")
	public WebElement participant;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../../following-sibling::lightning-combobox//button")
	public WebElement contactMethod;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> contactMethodList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Location']/../../following-sibling::lightning-combobox//button")
	public WebElement locationdd;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Location']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> locationList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Participant Type']/../../following-sibling::lightning-combobox//button")
	public WebElement participantType;
	
	@FindBy(how = How.XPATH, using = " //label[text()='Participant Type']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> participantTypeList;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Method']/../..//following-sibling::lightning-combobox//span/span")
	public List<WebElement> methodList;
	
	@FindBy(xpath = "//*[@title='Contact ID']/..//lightning-formatted-text")
	WebElement contactLogId;
	
	@FindBy(how = How.XPATH , using = "(//button[@title='Save'])[2]")
	public WebElement saveBtn;
  
	@FindBy(how = How.XPATH, using = "(//span[text()='View All']//parent::a)[1]")
	public WebElement viewAllButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Child Location Collateral']//preceding-sibling::span")
	public WebElement childLocationCollateralRB;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Placement Stability']//preceding-sibling::span")
	public WebElement placementStabilityRB;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Date of Reported Instability']/../../following-sibling::lightning-input//input")
	public WebElement dateOfReportedInstability;

	String tribalLogLink = "(//span[text()='%s']/../../../..//parent::a)[2]";
	String participantSelect= "(//span[normalize-space()='%s'])[3]";
	
	public void createNewContactLog( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Create New Contact Log");
		action.setPageActionDescription("Create New Contact Log");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New Contact", testCaseDataSd.get("NEW_CONTACT_LOG").get(0)), testCaseDataSd.get("NEW_CONTACT_LOG").get(0),action);
	}
	
	public void selectNewContactLogType( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Select New Contact Log Type");
		action.setPageActionDescription("Select New Contact Log Type");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);Webkeywords.instance().scrollUpPageToTheTop(driver);
			
		String clCollateralRBTD =  testCaseDataSd.get("CL_COLLATERAL_RB").get(0);
		String clPlacementStabilityRBTD =  testCaseDataSd.get("CL_COLLATERAL_RB").get(0);
		String nextBTNTD = testCaseDataSd.get("NEXT_BTN").get(0);
			
		Webkeywords.instance().click(driver, childLocationCollateralRB, clCollateralRBTD, action);
		Webkeywords.instance().click(driver, placementStabilityRB, clPlacementStabilityRBTD, action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Next", nextBTNTD), nextBTNTD,action);
	}
	
	public void enterChildLocationCollateralDetails( String scriptIteration, String pomIteration){

		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Child Location Collateral Details");
		action.setPageActionDescription("Enter Child Location Collateral Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
		String contactStatusTD = testCaseDataSd.get("CONTACT_STATUS").get(0);
		String contactPurposeTD = testCaseDataSd.get("CONTACT_PURPOSE").get(0);	
		String contactStartDateTimeTD = testCaseDataSd.get("CONTACT_START_DATE_TIME").get(0);
		String contactEndDateTimeTD = testCaseDataSd.get("CONTACT_END_DATE_TIME").get(0);
		String participantFirstNameTD = util.getRandom(testCaseDataSd.get("PARTICIPANT_FIRSTNAME").get(0));
		String participantLastNameTD = util.getRandom(testCaseDataSd.get("PARTICIPANT_LASTNAME").get(0));
		String staffPersonTD = testCaseDataSd.get("STAFF_PERSON").get(0);
		String otherStaffPresentTD = testCaseDataSd.get("OTHER_STAFF_PRESENT").get(0);	
		String participantTD = testCaseDataSd.get("PARTICIPANT").get(0);
		String participantValue = SalesforceConstants.getConstantValue( testCaseDataSd.get("PARTICIPANT").get(0));
		String methodTD = testCaseDataSd.get("METHOD").get(0);
		String locationTD = testCaseDataSd.get("LOCATION").get(0);
		String narrativeTD = util.getRandom(testCaseDataSd.get("NARRATIVE").get(0));
		
		Webkeywords.instance().pause();
		Webkeywords.instance().selectDropdownValueByElement(driver,contactStatus,contactStatusList,contactStatusTD,CONTACT_STATUS,action);
		Webkeywords.instance().selectDropdownValueByElement(driver,contactPurpose,contactPurposeList,contactPurposeTD,CONTACT_PURPOSE,action);
		Webkeywords.instance().setDateText(driver, contactStartDateTime, CommonOperations.getDate("M/d/yyyy", contactStartDateTimeTD), action);
		Webkeywords.instance().setDateText(driver, contactEndDateTime, CommonOperations.getDate("M/d/yyyy", contactEndDateTimeTD), action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Participant First Name",participantFirstNameTD),participantFirstNameTD, action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Participant Last Name",participantLastNameTD),participantLastNameTD, action);
		Webkeywords.instance().selectValueInSearchbox(driver, "Staff Person(s)", staffPersonTD, action);
		Webkeywords.instance().selectValueInSearchbox(driver, "Staff Person(s)", otherStaffPresentTD, action);
		Webkeywords.instance().click(driver, participant,participantTD,action);			
		Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(participantSelect,participantValue))),participantTD,action);
		Webkeywords.instance().selectDropdownValueByElement(driver, contactMethod, contactMethodList,methodTD,CONTACT_METHOD,action);
		Webkeywords.instance().selectDropdownValueByElement(driver, locationdd, locationList, locationTD,LOCATION,action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, NARRATIVE ,narrativeTD), narrativeTD,action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE,testCaseDataSd.get("SAVE_BTN").get(0)),  testCaseDataSd.get("SAVE_BTN").get(0), action);
		SalesforceCommon.verifyToastMessage(driver,"Data saved successfully.", action);
		Webkeywords.instance().waitElementToBeVisible(driver, contactLogId);
	    SalesforceConstants.setConstantValue("COLLATERAL_CONTACTID", contactLogId.getText());			
	}
	
	public void enterChildLocationPlacementStabilityDetails( String scriptIteration, String pomIteration){

		PageDetails action = new PageDetails();
		action.setPageActionName("Enter Child Location Placement Stability Details");
		action.setPageActionDescription("Enter Child Location Placement Stability Details");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
		String contactPurposeTD = testCaseDataSd.get("CONTACT_PURPOSE").get(0);	
		String contactStartDateTimeTD = testCaseDataSd.get("CONTACT_START_DATE_TIME").get(0);
		String contactEndDateTimeTD = testCaseDataSd.get("CONTACT_END_DATE_TIME").get(0);
		String participantTD = testCaseDataSd.get("PARTICIPANT").get(0);
		String participantValue = SalesforceConstants.getConstantValue( testCaseDataSd.get("PARTICIPANT").get(0));
		String methodTD = testCaseDataSd.get("METHOD").get(0);
		String locationTD = testCaseDataSd.get("LOCATION").get(0);
		String narrativeTD = util.getRandom(testCaseDataSd.get("NARRATIVE").get(0));
		String dateOfReportedInstabilityTD = testCaseDataSd.get("DATE_OF_REPORTED_INSTABILITY").get(0);
		String describePlacementConcernsTD = util.getRandom(testCaseDataSd.get("DESCRIBE_PLACEMENT_CONCERNS").get(0));
		
		Webkeywords.instance().pause();
		Webkeywords.instance().setDateText(driver, contactStartDateTime, CommonOperations.getDate("M/d/yyyy", contactStartDateTimeTD), action);
		Webkeywords.instance().setDateText(driver, contactEndDateTime, CommonOperations.getDate("M/d/yyyy", contactEndDateTimeTD), action);
		Webkeywords.instance().selectDropdownValueByElement(driver,contactPurpose,contactPurposeList,contactPurposeTD,CONTACT_PURPOSE,action);
		Webkeywords.instance().click(driver, participant,participantTD,action);			
		Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(participantSelect,participantValue))),participantTD,action);
		Webkeywords.instance().selectDropdownValueByElement(driver, contactMethod, contactMethodList,methodTD,CONTACT_METHOD,action);
		Webkeywords.instance().selectDropdownValueByElement(driver, locationdd, locationList, locationTD,LOCATION,action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, NARRATIVE ,narrativeTD), narrativeTD,action);
		Webkeywords.instance().setDateText(driver, dateOfReportedInstability, CommonOperations.getDate("M/d/yyyy", dateOfReportedInstabilityTD), action);
		Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Describe Placement Concerns" ,describePlacementConcernsTD), describePlacementConcernsTD,action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, SAVE,testCaseDataSd.get("SAVE_BTN").get(0)),  testCaseDataSd.get("SAVE_BTN").get(0), action);
		SalesforceCommon.verifyToastMessage(driver,"Data saved successfully.", action);
		Webkeywords.instance().waitElementToBeVisible(driver, contactLogId);
	    SalesforceConstants.setConstantValue("PLACEMENT_STABILITY_CONTACTID", contactLogId.getText());			
	}
}