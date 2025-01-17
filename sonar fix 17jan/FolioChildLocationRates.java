package cares.cwds.salesforce.pom.folio.placement;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;

import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.web.CommonOperations;
import cares.cwds.salesforce.utilities.web.GenericLocators;

import cares.cwds.salesforce.utilities.web.Webkeywords;

public class FolioChildLocationRates {
	private static final Logger logger =LoggerFactory.getLogger(FolioChildLocationRates.class.getName());
	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.FOLIOCHILDLOCATIONRATES;
	
	public FolioChildLocationRates(){ }
	
	public FolioChildLocationRates(WebDriver wDriver)
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
	@FindBy(how = How.XPATH, using = "(//label[text()='Are the Funeral Cost Payee and Placement Payee the same?'])[1]")
	public WebElement funeralCostPayeeAndPlacementPayeeRB;
	
	
	
	public void navigateToRatesTab( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Navigate to Rates Tab");
		action.setPageActionDescription("Navigate to Rates Tab");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(),TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Rates", testCaseDataSd.get("RATES_TAB").get(0)), testCaseDataSd.get("RATES_TAB").get(0),action);
	}
	
	public void enterRatesDetails( String scriptIteration, String pomIteration)   {

		PageDetails action = new PageDetails();
		
		action.setPageActionName("Enter Rates Details");
		action.setPageActionDescription("Enter Rates Details");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
		
			String newBtnTD = testCaseDataSd.get("NEW_BTN").get(0);	
			String placementStartDateTD = testCaseDataSd.get("PLACEMENT_START_DATE").get(0);
			String placementEndDateTD = testCaseDataSd.get("PLACEMENT_END_DATE").get(0);
			
			
			String rateStartDateTD = testCaseDataSd.get("RATE_START_DATE").get(0);
			String rateEndDateTD = testCaseDataSd.get("RATE_END_DATE").get(0);
			
			
			
			
			Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.button(driver, "New", newBtnTD));
			Webkeywords.instance().waitElementClickable(driver, genericLocators.button(driver, "New", newBtnTD));
			Webkeywords.instance().click(driver,genericLocators.button(driver, "New", newBtnTD), newBtnTD,action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Placement Name",testCaseDataSd.get("PLACEMENT_NAME").get(0)),util.getRandom(testCaseDataSd.get("PLACEMENT_NAME").get(0)), action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("PLACEMENT_TYPE").get(0),"Placement Type",action);
			Webkeywords.instance().setDateText(driver, genericLocators.textbox(driver, "Placement Start Date",placementStartDateTD), CommonOperations.getDate("M/d/yyyy", placementStartDateTD), action);
			Webkeywords.instance().setDateText(driver, genericLocators.textbox(driver, "Placement End Date", placementEndDateTD), CommonOperations.getDate("M/d/yyyy", placementEndDateTD), action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("RATE_CATEGORY").get(0),"Rate Category",action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("RATE_TYPE").get(0),"Rate Type",action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("RATE_SUBTYPE").get(0),"Rate Subtype",action);
			Webkeywords.instance().setDateText(driver, genericLocators.textbox(driver, "Rate Start Date",placementStartDateTD), CommonOperations.getDate("M/d/yyyy", rateStartDateTD), action);
			Webkeywords.instance().setDateText(driver, genericLocators.textbox(driver, "Rate End Date", placementEndDateTD), CommonOperations.getDate("M/d/yyyy", rateEndDateTD), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Rate Amount",testCaseDataSd.get("RATE_AMOUNT").get(0)),util.getRandom(testCaseDataSd.get("RATE_AMOUNT").get(0)), action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("FREQUENCY").get(0),"Frequency",action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Non Standard Rate Description",testCaseDataSd.get("NON_STANDARD_RATE_DESCRIPTION").get(0)),util.getRandom(testCaseDataSd.get("NON_STANDARD_RATE_DESCRIPTION").get(0)), action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("MILEAGE").get(0),"Mileage",action);
			Webkeywords.instance().selectValueInputDropdown(driver,testCaseDataSd.get("LOC_TYPE").get(0),"LOC_Type",action);
			Webkeywords.instance().click(driver, funeralCostPayeeAndPlacementPayeeRB, testCaseDataSd.get("ARE_THE_FUNERAL_COST_PAYEE_AND_PLACEMENT_PAYEE_THE_SAME_RB").get(0), action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Funeral Cost Payee",testCaseDataSd.get("FUNERAL_COST_PAYEE").get(0)),util.getRandom(testCaseDataSd.get("FUNERAL_COST_PAYEE").get(0)), action);
			
			
			
			

			
			

	}

}



