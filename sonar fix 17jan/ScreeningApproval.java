package cares.cwds.salesforce.pom.screening;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cares.cwds.salesforce.constants.ModuleConstants;
import cares.cwds.salesforce.constants.SalesforceConstants;
import cares.cwds.salesforce.constants.ScreenConstants;
import cares.cwds.salesforce.utilities.common.TestRunSettings;
import cares.cwds.salesforce.utilities.common.Util;
import cares.cwds.salesforce.utilities.reports.common.ReportCommon;
import cares.cwds.salesforce.utilities.reports.extentmodel.PageDetails;
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;

public class ScreeningApproval {
	private static final Logger logger =LoggerFactory.getLogger(ScreeningApproval.class.getName());
	private static final String COMMENTS = "Comments";
	private static final String REASSIGN = "Reassign";


	private WebDriver driver;
	ReportCommon exceptionDetails = new ReportCommon();
	Util util = new Util();
	GenericLocators genericLocators = null;
	LocalDateTime startTime = LocalDateTime.now();

	String moduleName = ModuleConstants.SCREENING;
	String screenName = ScreenConstants.SCREENINGAPPROVAL;

	public ScreeningApproval() {
	}

	public ScreeningApproval(WebDriver wDriver) {
		initializePage(wDriver);
	}

	public void initializePage(WebDriver wDriver) {
		logger.info(this.getClass().getName());
		driver = wDriver;
		PageFactory.initElements(driver, this);
		ReportCommon testStepLogDetails = new ReportCommon();
		testStepLogDetails.logModuleAndScreenDetails( moduleName, screenName);
		genericLocators = new GenericLocators(wDriver);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Screening Approval']/..//span[contains(@class,'processOutputStatus')]")
	public WebElement screeningStatus;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Approval Details']//ancestor::fieldset//span[text()='Status']/..//following-sibling::dd//*/span")
	public WebElement approvalStatus;
	
    String approvalButton = "//span[text()='Cancel']/../../button/span[text()='%s']";

	public void approveScreening(String scriptIteration, String pomIteration)
		{
		PageDetails action = new PageDetails();
		action.setPageActionName("Approve Screening");
		action.setPageActionDescription("Approve Screening");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().pause();
			String approveBtnTD = testCaseDataSd.get("APPROVE_BTN").get(0);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Approve",approveBtnTD),
					approveBtnTD, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, COMMENTS,testCaseDataSd.get("COMMENTS").get(0)),
					util.getRandom(testCaseDataSd.get("COMMENTS").get(0)), action);
			Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(approvalButton,"Approve"))), approveBtnTD, action);
			Webkeywords.instance().pause();
			
		
	
	}

	public void rejectScreening(String scriptIteration, String pomIteration)
			 {
		PageDetails action = new PageDetails();
		action.setPageActionName("Reject Screening");
		action.setPageActionDescription("Reject Screening");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			String rejectBtnTD = testCaseDataSd.get("REJECT_BTN").get(0);

			Webkeywords.instance().click(driver, genericLocators.link(driver, "Reject",rejectBtnTD), rejectBtnTD, action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, COMMENTS,testCaseDataSd.get("COMMENTS").get(0)),

			util.getRandom(testCaseDataSd.get("COMMENTS").get(0)), action);
			Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(approvalButton,"Reject"))),rejectBtnTD, action);
			Webkeywords.instance().verifyTextDisplayed(driver, screeningStatus, "Rejected", action);
			Webkeywords.instance().verifyTextDisplayed(driver, approvalStatus, "Modifications Required", action);
	}

	public void reassignScreening(String scriptIteration, String pomIteration)
		 {
		PageDetails action = new PageDetails();
		action.setPageActionName("Reassign Screening");
		action.setPageActionDescription("Reassign Screening");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			String reassignBtnTD = testCaseDataSd.get("REASSIGN_BTN").get(0);
			String reassignSupervisorTD = testCaseDataSd.get("REASSIGN_SUPERVISOR").get(0);

			Webkeywords.instance().click(driver, genericLocators.link(driver, REASSIGN,reassignBtnTD),reassignBtnTD, action);
			Webkeywords.instance().setText(driver, genericLocators.textbox(driver, "Reassign To",SalesforceConstants.getUserName(reassignSupervisorTD)),
					SalesforceConstants.getUserName(reassignSupervisorTD),
					 action);
			Webkeywords.instance().click(driver,
					genericLocators.link(driver, testCaseDataSd.get("REASSIGN_SUPERVISOR").get(0),SalesforceConstants.getUserName(reassignSupervisorTD)),reassignSupervisorTD,
					 action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, REASSIGN,reassignBtnTD),
					util.getRandom(reassignBtnTD), action);
			Webkeywords.instance().click(driver,driver.findElement(By.xpath(format(approvalButton,REASSIGN))), reassignBtnTD, action);
			Webkeywords.instance().verifyTextDisplayed(driver, screeningStatus, "Pending", action);
	
	}
}