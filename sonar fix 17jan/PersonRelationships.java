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
import org.testng.Assert;

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

public class PersonRelationships {
	

private static final String RELATIONPERSON = "Related Person";
	
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	

	String moduleName = ModuleConstants.PERSON;
	String screenName = ScreenConstants.PERSONRELATIONSHIPS;
	
	public PersonRelationships(){ }
	
	public PersonRelationships(WebDriver wDriver)
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
	
	@FindBy(how = How.XPATH, using = "//*[text()='Information']")
	public WebElement informationSection;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Start Date']/../../following-sibling::lightning-input//input)[1]")
	public WebElement startDate;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='End Date']/../../following-sibling::lightning-input//input)[1]")
	public WebElement endDate;
	
	@FindBy(how = How.XPATH, using = "//label[text()='End Date']")
	public WebElement endDateLabel;
	
	@FindBy(how = How.XPATH, using = "//article[@aria-label='Tribal Information']//span[text()='View All']")
	public WebElement viewAllbtn;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Person Relationship']/ancestor::h1//lightning-formatted-text")
	public WebElement personRelationshipId;	
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'windowViewMode-maximized')]//*[@title='Person']/..//a//span)[3]")
	public WebElement person;	
			
	String columnHeader = "//table[@aria-label='%s']/thead/tr/th[@*='%s']//span";
	
	public void navigateToRelationshipTab(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Navigate to Relationship Tab");
		action.setPageActionDescription("Navigate to Relationship Tab");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		
		String relationshipTab = testCaseDataSd.get("RELATIONSHIP_TAB").get(0);
		String familyInformationTab = testCaseDataSd.get("FAMILY_INFORMATION").get(0);
		
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Family Information" ,familyInformationTab),familyInformationTab,action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Relationships" ,relationshipTab),relationshipTab,action);
	}
	
	public void addPersonRelationship( String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Add to Person Relationship");
		action.setPageActionDescription("ADd to Person Relationship");
		
		Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
		
		String newBtn = testCaseDataSd.get("NEW_BTN").get(0);
		String saveBtn = testCaseDataSd.get("SAVE_BTN").get(0);
		String relationperson = testCaseDataSd.get("RELATION_PERSON").get(0);
		String relationpersonLink = testCaseDataSd.get("RELATION_PERSON_LINK").get(0);
		
		Webkeywords.instance().click(driver,genericLocators.button(driver, "New" ,newBtn),newBtn, action);
		Webkeywords.instance().refresh(driver, action);
		Webkeywords.instance().waitElementToBeVisible(driver, informationSection);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, RELATIONPERSON,relationperson),relationperson, action);
		Webkeywords.instance().click(driver, genericLocators.link(driver, relationperson,relationpersonLink),relationpersonLink,action);
		Webkeywords.instance().selectInputDropdownValue(driver,  testCaseDataSd.get("TYPE_OF_RELATIONSHIP").get(0), "Type of Relationship", action);
		Webkeywords.instance().selectInputDropdownValue(driver,  testCaseDataSd.get("RELATIONSHIP_TYPE").get(0), "Relationship Type", action);
		Webkeywords.instance().selectInputDropdownValue(driver,  testCaseDataSd.get("RELATIONSHIP_MODIFIER").get(0), "Relationship modifier", action);
		Webkeywords.instance().selectInputDropdownValue(driver,  testCaseDataSd.get("RECIPROCAL_RELATIONSHIP_TYPE").get(0), "Reciprocal Relationship Type", action);
		Webkeywords.instance().setDateText(driver, startDate, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("START_DATE").get(0)), action);			
		Webkeywords.instance().setDateText(driver, endDate, CommonOperations.getDate("M/d/yyyy", testCaseDataSd.get("END_DATE").get(0)), action);
		Webkeywords.instance().click(driver, endDateLabel,testCaseDataSd.get("END_DATE").get(0),action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "End Reason",testCaseDataSd.get("END_REASON").get(0)),util.getRandom(testCaseDataSd.get("END_REASON").get(0)), action);
		Webkeywords.instance().click(driver,genericLocators.button(driver, "Save",saveBtn),saveBtn, action);
		SalesforceCommon.verifyToastMessage(driver,"Data saved successfully.", action);
		Webkeywords.instance().waitElementToBeVisible(driver, personRelationshipId);
		SalesforceConstants.setConstantValue("PersonRelationshipId", personRelationshipId.getText());
		Assert.assertEquals(person.getText(), SalesforceConstants.getConstantValue("PersonName"),"Person Field is not auto populated with Person Name on Person Relationships");	
	}
	
	public void verifyPersonRelationshipColumnHeaders(String tableName, String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
		action.setPageActionName("Verify Person Relationship Column Headers in Table");
		action.setPageActionDescription("Verify Person Relationship Column Headers in Table");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("RELATIONSHIP_ID_VERIFY").get(0),columnHeader,tableName,"Relationship ID"), testCaseDataSd.get("RELATIONSHIP_ID_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("RELATED_PERSON_VERIFY").get(0),columnHeader,tableName,RELATIONPERSON), testCaseDataSd.get("RELATED_PERSON_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("RELATIONSHIP_TYPE_VERIFY").get(0),columnHeader,tableName,"Relationship Type"), testCaseDataSd.get("RELATIONSHIP_TYPE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("RECIPROCAL_RELATIONSHIP_TYPE_VERIFY").get(0),columnHeader,tableName,"Reciprocal Relationship Type"), testCaseDataSd.get("RECIPROCAL_RELATIONSHIP_TYPE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("START_DATE_VERIFY").get(0),columnHeader,tableName,"Start Date"), testCaseDataSd.get("START_DATE_VERIFY").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, getElementBasedOnFlag(testCaseDataSd.get("END_DATE_VERIFY").get(0),columnHeader,tableName,"End Date"), testCaseDataSd.get("END_DATE_VERIFY").get(0), action);
	}
	
	
	public WebElement getElementBasedOnFlag(String flag, String columnHeaderXpath, String tableName, String columnName) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(columnHeaderXpath,tableName,columnName)));
		}
		else
			return null;
	}
}
