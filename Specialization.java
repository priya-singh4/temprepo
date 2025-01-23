package cares.cwds.salesforce.pom.folio;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.excel.PoiWriteExcel;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;


import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.SalesforceCommon;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class Specialization {

	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	GenericLocators genericLocators = null;
	Util util = new Util();

	String moduleName = ModuleConstants.FOLIO;
	String screenName = ScreenConstants.SPECIALIZATION;
	LocalDateTime startTime= LocalDateTime.now();
	
	private static final String SPECIALIZATION = "Specialization";
	private static final String SPECIALIZATION_TYPE = "Specialization Type";
	public Specialization(){ }
	
	public Specialization(WebDriver wDriver)
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
	
	@FindBy(xpath = "//*[text()='Specialization ID']/..//lightning-formatted-text")
	WebElement specializationID;
	
	public void navigateToSpecializationTab(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Navigate To Specialization Tab");
		action.setPageActionDescription("Navigate To Specialization Tab");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		String specializationTD =  testCaseDataSd.get("SPECIALIZATION_TAB").get(0);
		Webkeywords.instance().click(driver, genericLocators.link(driver, SPECIALIZATION, specializationTD),specializationTD,  action);
		
	}
	
	public void clickNewSpecialization(String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Click New Specialization button");
		action.setPageActionDescription("Click New Specialization button");
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW_BTN").get(0)),testCaseDataSd.get("NEW_BTN").get(0),action);
	}

	public void addSpecializationInfo(String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();

		action.setPageActionName("Add Specialization Details");
		action.setPageActionDescription("Add Specialization Details");

		Map<String, ArrayList<String>> testCaseDataSd = util.getScreenTCData(screenName,TestRunSettings.getTestNGTestMethodName(), TestRunSettings.getTestDataPath(),
				TestRunSettings.getTestDataMappingFileName(), TestRunSettings.getTestDataMappingSheetNameSd(),
				scriptIteration, pomIteration);
		
		Webkeywords.instance().scrollUpPageToTheTop(driver);
		Webkeywords.instance().pause();
		Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("SPECIALIZATION_TYPE").get(0),SPECIALIZATION_TYPE,action);
		Webkeywords.instance().setText(driver, genericLocators.textbox(driver, SPECIALIZATION_TYPE, testCaseDataSd.get("SCREENING_NAME").get(0)), util.getRandom(testCaseDataSd.get("SCREENING_NAME").get(0)), action);
		Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE_BTN").get(0)),testCaseDataSd.get("SAVE_BTN").get(0),action);
		
		Webkeywords.instance().pause();
		Webkeywords.instance().waitElementToBeVisible(driver, specializationID);
		SalesforceConstants.setConstantValue("SPE_ID"+pomIteration, specializationID.getText());
		if(!testCaseDataSd.get("CAPTURE_SPEID").get(0).toLowerCase().equalsIgnoreCase("n/a")) {
			String[] fields = {"SPECIALIZATION_ID","SPE_ID"};
			String fielPath = TestRunSettings.getScreeningIdsFilePath() + File.separator + TestRunSettings.getScreeningIdsFileName();
			PoiWriteExcel.writeExcelData(fielPath, "FolioData", fields, specializationID.getText());
		}
		SalesforceCommon.captureRecordURL(driver,"SCR");
		
	}
}
