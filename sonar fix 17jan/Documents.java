package cares.cwds.salesforce.pom.screening;

import static java.lang.String.format;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import cares.cwds.salesforce.utilities.web.GenericLocators;
import cares.cwds.salesforce.utilities.web.Webkeywords;


public class Documents {
	private static final Logger logger =LoggerFactory.getLogger(Documents.class.getName());
	private WebDriver driver;
	Util util = new Util();
	GenericLocators genericLocators = null;
	String moduleName = ModuleConstants.DOCUMENTS;
	String screenName = ScreenConstants.DOCUMENTS;


public Documents(){ }
	
	public Documents(WebDriver wDriver) {
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
	
	private String showaction = "//th[@data-cell-value='%s']/..//span[text()='Show actions']//parent::button";
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'file-selector')]//span[@part='button']")
	public WebElement uploadfiles;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Document Type']//parent::div//button")
	public WebElement documentType;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Document Type']//parent::div//lightning-base-combobox-item/span/span")
	public List<WebElement> documentTypeList;
	
	@FindBy(how = How.XPATH, using ="(//table//tbody//tr/th[@data-label='Title'])[1]/../td//button")
	public WebElement documentRecord;
	
	private String showActionBtn  = "//tbody//tr[%s]//span[text()='Show actions']";

	
	public void navigateToDocuments( String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
	
		action.setPageActionName("Navigate to Documents");
		action.setPageActionDescription("Navigate to Documents");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().pause();
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Documents",testCaseDataSd.get("DOCUMENTS_TAB").get(0)),testCaseDataSd.get("DOCUMENTS_TAB").get(0), action);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().pause();
	
	}

	public void validateDocumentList( String documentName, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Process Documents");
		action.setPageActionDescription("Process Documents");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.button(driver, "New", " "));
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, documentName,testCaseDataSd.get("ERR_DOCUMENT_NAME").get(0)), testCaseDataSd.get("ERR_DOCUMENT_NAME").get(0), action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
			String downloadTD = testCaseDataSd.get("DOWNLOAD").get(0);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Download",downloadTD), downloadTD, action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Print",testCaseDataSd.get("PRINT").get(0)), testCaseDataSd.get("PRINT").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "View",testCaseDataSd.get("VIEW").get(0)), testCaseDataSd.get("VIEW").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Update Status",testCaseDataSd.get("UPDATE_STATUS").get(0)), testCaseDataSd.get("UPDATE_STATUS").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Upload New Version",testCaseDataSd.get("UPLOAD_NEW_VERSION").get(0)), testCaseDataSd.get("UPLOAD_NEW_VERSION").get(0), action);
			Webkeywords.instance().verifyElementDisplayed(driver, genericLocators.link(driver, "Delete",testCaseDataSd.get("DELETE").get(0)), testCaseDataSd.get("DELETE").get(0), action);
			Actions actions = new Actions(driver);
	        actions.sendKeys(Keys.ESCAPE).perform();


	}
	
	public void documentSection( String scriptIteration, String pomIteration){
		PageDetails action = new PageDetails();
	
		action.setPageActionName("Navigate to Documents");
		action.setPageActionDescription("Navigate to Documents");
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			Webkeywords.instance().scrollIntoViewElement(driver, genericLocators.link(driver, "Documents",testCaseDataSd.get("DOCUMENTS_TAB").get(0)));
			Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.link(driver, "Documents",testCaseDataSd.get("DOCUMENTS_TAB").get(0)));	
			Webkeywords.instance().waitElementClickable(driver,genericLocators.link(driver, "Documents",testCaseDataSd.get("DOCUMENTS_TAB").get(0)));
			Webkeywords.instance().jsClick(driver, genericLocators.link(driver, "Documents",testCaseDataSd.get("DOCUMENTS_TAB").get(0)),testCaseDataSd.get("DOCUMENTS_TAB").get(0), action);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
	
	}

	
	public void uploadDocument( String scriptIteration, String pomIteration) throws AWTException {
		PageDetails action = new PageDetails();
		action.setPageActionName("Upload Document");
		action.setPageActionDescription("Upload New Document");
		
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().scrollUpPageToTheTop(driver);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "New", testCaseDataSd.get("NEW").get(0)), testCaseDataSd.get("NEW").get(0), action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENT_CATEGORY").get(0),"Document Category",action);
			Webkeywords.instance().selectDropdownValueByElement(driver,documentType,documentTypeList,testCaseDataSd.get("DOCUMENT_TYPE").get(0),"Document Type",action);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENT_STATUS").get(0),"Document Status",action);
			Webkeywords.instance().setText(driver, genericLocators.textarea(driver, "Description",testCaseDataSd.get("DESCRIPTION").get(0)), "AutoDescription_" + util.getRandom(testCaseDataSd.get("DESCRIPTION").get(0)), action);
			Webkeywords.instance().jsClick(driver,uploadfiles );
			
			String filePath = TestRunSettings.getUploadFilePath() + File.separator+ testCaseDataSd.get("UPLOAD_DOCUMENT").get(0);

		    uploadFile(filePath);
			Webkeywords.instance().waitElementClickable(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE").get(0)));
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE").get(0)),testCaseDataSd.get("SAVE").get(0), action);
		
	}
	
	public void deleteDocument(  String documentName,String scriptIteration,String pomIteration) {
		PageDetails action = new PageDetails();
	
		action.setPageActionName("Delete Documents");
		action.setPageActionDescription("Delete Documents");
		
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
			String deleteTD = testCaseDataSd.get("DELETE").get(0);
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Delete",deleteTD),deleteTD, action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Delete",deleteTD),deleteTD, action);
			
		 
	}
	

	public void printDocument(String documentName, String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Print Documents");
		action.setPageActionDescription("Print Documents");
		
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);			
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().waitElementToBeVisible(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().waitElementClickable(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
			String printTD = testCaseDataSd.get("PRINT").get(0);
			String closeTD = testCaseDataSd.get("CLOSE").get(0);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Print",printTD),printTD, action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Close",closeTD),closeTD, action);
		  
	
	}
	
	public void downloadDocument( String documentName,String scriptIteration,  String pomIteration) {
		PageDetails action = new PageDetails();
	
		action.setPageActionName("Download Document");
		action.setPageActionDescription("Download Document");
		
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().waitElementToBeVisible(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().waitElementClickable(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
			String downloadTD = testCaseDataSd.get("DOWNLOAD").get(0);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Download",downloadTD),downloadTD, action);
		  
	
	}
	
	public void viewDocument(String documentName, String scriptIteration, String pomIteration)  {
		PageDetails action = new PageDetails();
		action.setPageActionName("View Documents");
		action.setPageActionDescription("View Documents");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
			String viewTD = testCaseDataSd.get("VIEW").get(0);
			String closeTD = testCaseDataSd.get("CLOSE").get(0);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "View",viewTD),viewTD, action);
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Close",closeTD),closeTD, action);
			
		  
	
	}
	

	public void updateDocumentStatus(String documentName, String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Update Document Status");
		action.setPageActionDescription("Update Document Status");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().waitElementToBeVisible(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().waitElementClickable(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);

			String updateStatusTD = testCaseDataSd.get("UPDATE_STATUS").get(0);
			Webkeywords.instance().click(driver,  genericLocators.link(driver, "Update Status",updateStatusTD),updateStatusTD, action);
			Webkeywords.instance().selectInputDropdownValue(driver, "Final", "Document Status", action);
			Webkeywords.instance().waitElementClickable(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE").get(0)));
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE").get(0)),testCaseDataSd.get("SAVE").get(0), action);
			
	}
	
	public void uploadNewVersion(String documentName,String scriptIteration, String pomIteration) throws AWTException  {
		PageDetails action = new PageDetails();
		action.setPageActionName("Upload New Version");
		action.setPageActionDescription("Upload New Document");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);

			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().waitElementToBeVisible(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().waitElementClickable(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName));
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showaction,documentName),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
			String uploadNewVersionTD = testCaseDataSd.get("UPLOAD_NEW_VERSION").get(0);
		
			Webkeywords.instance().click(driver, genericLocators.link(driver, "Upload New Version",uploadNewVersionTD),uploadNewVersionTD, action);
			Webkeywords.instance().waitElementToBeVisible(driver, genericLocators.textarea(driver, "Description",testCaseDataSd.get("DESCRIPTION").get(0)));
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("DOCUMENT_STATUS").get(0),"Document Status",action);
			Webkeywords.instance().jsClick(driver,uploadfiles );
			
			String filePath = TestRunSettings.getUploadFilePath() + File.separator+ testCaseDataSd.get("UPLOAD_DOCUMENT").get(0);
			uploadFile(filePath);
			Webkeywords.instance().waitElementClickable(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE").get(0)));
			Webkeywords.instance().click(driver, genericLocators.button(driver, "Save",testCaseDataSd.get("SAVE").get(0)),testCaseDataSd.get("SAVE").get(0), action);
	}
	
	public void uploadFile(String path) throws AWTException{
		
		StringSelection strSel = new StringSelection(path);
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(strSel, null);
		
		Robot rb = new Robot();
		rb.delay(2000);
		rb.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
		rb.keyPress(java.awt.event.KeyEvent.VK_V);
		rb.keyRelease(java.awt.event.KeyEvent.VK_V);
		rb.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
		rb.keyPress(java.awt.event.KeyEvent.VK_ENTER);
		rb.delay(4000);
		rb.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
	
	}
	
	public void latestRecord( String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
	
		action.setPageActionName("Click Latest record");
		action.setPageActionDescription("Click Latest record");
		
	
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			
			Webkeywords.instance().click(driver,documentRecord, testCaseDataSd.get("LATEST_RECORD").get(0), action);
	
	}
	
	
	private WebElement getElementBasedOnFlag(String flag, String healthInfoTypeXpath, String name) {
		if(!(flag.equalsIgnoreCase("n/a"))) {
			return driver.findElement(By.xpath(format(healthInfoTypeXpath,name)));
		}
		else
			return null;
	}
	
	public void validateDocumentListNotDisplayed(String scriptIteration, String pomIteration) {
		PageDetails action = new PageDetails();
		action.setPageActionName("Process Documents");
		action.setPageActionDescription("Process Documents");
		
			Map<String, ArrayList<String>>	testCaseDataSd = util.getScreenTCData(screenName, TestRunSettings.getTestNGTestMethodName(),TestRunSettings.getTestDataPath(), TestRunSettings.getTestDataMappingFileName() ,TestRunSettings.getTestDataMappingSheetNameSd(),scriptIteration,pomIteration);
			Webkeywords.instance().selectInputDropdownValue(driver,testCaseDataSd.get("ITEMS_PER_PAGE").get(0),"Items per Page",action);
			Webkeywords.instance().verifyTextDisplayed(driver, genericLocators.textOnPage(driver, scriptIteration,testCaseDataSd.get("ERR_DOCUMENT_NAME").get(0)), testCaseDataSd.get("ERR_DOCUMENT_NAME").get(0), action);
			Webkeywords.instance().scrollIntoViewElement(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showActionBtn,scriptIteration));
			Webkeywords.instance().click(driver, getElementBasedOnFlag(testCaseDataSd.get("SHOW_ACTIONS").get(0),showActionBtn,scriptIteration),testCaseDataSd.get("SHOW_ACTIONS").get(0), action);
		
			String deleteXpath = "//span[text()='Delete']";
			Webkeywords.instance().verifyelementnotdisplayed(driver, deleteXpath, testCaseDataSd.get("DELETE").get(0), action);
	}
}
