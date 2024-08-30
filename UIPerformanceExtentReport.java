package uiperformanceutilities.reports.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import uiperformanceutilities.constants.UIPerformanceConstants;
import uiperformanceutilities.model.EntryModel;
import uiperformanceutilities.model.UIPerformanceModel;
import testsettings.TestRunSettings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UIPerformanceExtentReport
{
	private static final Logger logger =LoggerFactory.getLogger(UIPerformanceExtentReport.class.getName());

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;

	public static HashMap<String, String> Sheet_ScreenHashMap = new HashMap<String,String>();
	public static HashMap<String, String> ScreenNameSheetNameMap = new HashMap<String, String>();
	public static HashMap<String, Integer> screenNameTotalTestCases = new HashMap<String, Integer>();
	public static HashMap<String, ArrayList<String>> screenNameToTestCasesMap = new HashMap<String,ArrayList<String>>();
	public static  HashMap<String,String> sheets=new HashMap<String,String>();


	// Create a workbook
	static XSSFWorkbook workbook = new XSSFWorkbook();




	public void createExtentReportCategory(String reportPath,String browserName, String executedBy) throws Exception
	{

		try
		{
		try
			{
				File dir = new File(reportPath);
				if (!dir.exists()) dir.mkdirs();
				logger.info("Directory Created ===> {} ",reportPath);

			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;

			}
			String reportFilePath = reportPath + "/UIPerformance.html"; 
			int count=1;
			XSSFSheet dashboardSheet = workbook.createSheet("Dashboard");
			ExtentHtmlReporter htmlReporter_lcl = new ExtentHtmlReporter(reportFilePath);
			ExtentReports extentIcl = new ExtentReports();
			try
			{
				extentIcl.attachReporter(htmlReporter_lcl);

			}
			catch(Exception e)
			{
				logger.error(e.getMessage(), e);
				logger.error("An exception occurred", e);

				throw e;	
			}


			if (UIPerformanceConstants.uiPerfData != null)
			{


				for (String  ScreenName : UIPerformanceConstants.uiPerfData.keySet())
				{
					String result = "";
					ExtentTest test = extentIcl.createTest(ScreenName);

					test.assignCategory(browserName);

					ArrayList<UIPerformanceModel> uiPerfModels=UIPerformanceConstants.uiPerfData.get(ScreenName);


					for(UIPerformanceModel uiPerfModel:uiPerfModels)
					{
						ExtentTest multiTcNode=test.createNode("Test Case : "+ uiPerfModel.testCaseName + "  :: ModuleName : " + uiPerfModel.moduleName + " :: BrowserName : " + uiPerfModel.browserName);	

						if(uiPerfModel.responseTimeMillisecond<=UIPerformanceConstants.EXPECTEDRESPONSETIMEINMILLISECOND)
						{
							for(String s:uiPerfModel.entries) {
								result=result+"\n "+ s;
							}
							multiTcNode.pass(" SourceScreen : " + uiPerfModel.sourceScreen+ "<br>  "
									+ " DestinationScreen : " + uiPerfModel.destinationScreen+ "<br>  "
									+ " Source_DestinationScreen : " + uiPerfModel.sourceDestinationScreen+ "<br>  "
									+ " ResponseTime : " + uiPerfModel.responseTime+ "<br>  "
									+ " ResponseTimeMillisecond : " + uiPerfModel.responseTimeMillisecond+ "<br>  "
									+ " TestCaseName : " + uiPerfModel.testCaseName+ "<br>  "
									+ " ModuleName : " + uiPerfModel.moduleName+ "<br>  "
									+ " BrowserName : " + uiPerfModel.browserName+ "<br>  "
									+ " StartTime : " + uiPerfModel.startTime+ "<br>  "
									+ " EndTime : " + uiPerfModel.endtime+ "<br>  "
									+ " PageLoadTime : " + uiPerfModel.pageLoadTime+ "<br> "
									+ " TTFB : " +uiPerfModel.ttfb+ "<br> "
									+ " EndToEndResponseTime : "+uiPerfModel.endtoendRespTime+ "<br> "
									);



						}
						else
						{

							for(String s:uiPerfModel.entries) {
								result=result+"\n "+ s;
							}
							multiTcNode.fail(" SourceScreen : " + uiPerfModel.sourceScreen+ "<br>  "
									+ " DestinationScreen : " + uiPerfModel.destinationScreen+ "<br>  "
									+ " Source_DestinationScreen : " + uiPerfModel.sourceDestinationScreen+ "<br>  "
									+ " ResponseTime : " + uiPerfModel.responseTime+ "<br>  "
									+ " ResponseTimeMillisecond : " + uiPerfModel.responseTimeMillisecond+ "<br>  "
									+ " TestCaseName : " + uiPerfModel.testCaseName+ "<br>  "
									+ " ModuleName : " + uiPerfModel.moduleName+ "<br>  "
									+ " BrowserName : " + uiPerfModel.browserName+ "<br>  "
									+ " StartTime : " + uiPerfModel.startTime+ "<br>  "
									+ " EndTime : " + uiPerfModel.endtime+ "<br>  "
									+ " PageLoadTime : " + uiPerfModel.pageLoadTime+ "<br> "
									+ " TTFB : " +uiPerfModel.ttfb+ "<br> "
									+ " EndToEndResponseTime : "+uiPerfModel.endtoendRespTime+ "<br> "
									);

							for(String resourceType: uiPerfModel.mapEntryModel.keySet())
							{
								ExtentTest resourceTypeNode=multiTcNode.createNode("Resource  Type : "+ resourceType);	
								ArrayList<EntryModel> lstEntryModel=uiPerfModel.mapEntryModel.get(resourceType);
								for (EntryModel entryModel :lstEntryModel)
								{

									resourceTypeNode.log(Status.FAIL,
											"EntryName : "+ entryModel.entryName
											+"<br>EntryType : "+ entryModel.entryType
											+"<br>InitiatorType : "+ entryModel.initiatorType
											+"<br>StartTime : "+ entryModel.startTime
											+"<br>EndTime : "+ entryModel.endTime
											+"<br>Duration : "+ entryModel.duration
											+"<br>TransferSize : "+ entryModel.transferSize);
								}
							}
						}
						String sheetName=null;
						if(sheets.containsKey(ScreenName)) {
							for(String s:TestRunSettings.UIPerformanceResultsMap.keySet()) {
								if(s.contains(sheets.get(ScreenName))) {
									count++;
								}
							}
							if(ScreenName.length()>25) {
								String newScreenName=ScreenName.substring(0,Math.min(ScreenName.length(), 25));
								sheetName=newScreenName+"_"+count;

							}else {
								sheetName=ScreenName+"_"+count;
							}
						}else {
							count=1;
							if(ScreenName.length()>25) {
								String newScreenName=ScreenName.substring(0,Math.min(ScreenName.length(), 25));
								sheetName=newScreenName+"_"+count;

							}else {
								sheetName=ScreenName+"_"+count;
							}
						}
						sheets.put(ScreenName,sheetName);
						TestRunSettings.UIPerformanceResultsMap.put(sheetName, result);
						ScreenNameSheetNameMap.put(sheetName, uiPerfModel.testCaseName);
						ArrayList<String> s=new ArrayList<String>();

						s.add(uiPerfModel.testCaseName);
						Sheet_ScreenHashMap.put(sheetName,ScreenName);
						if(screenNameToTestCasesMap.get(ScreenName)==null) {
							screenNameToTestCasesMap.put(ScreenName, s);
						}else {
						screenNameToTestCasesMap.get(ScreenName).add(uiPerfModel.testCaseName);
						}
						writetoExcel(workbook,result,sheetName, reportPath);
						writeworkbook(workbook, reportPath);

					}



				}

				createDashboard(workbook,reportPath);
				writeworkbook(workbook, reportPath);

			}

			extentIcl.flush();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			logger.error("An exception occurred", e);
			throw e;	
		}
	}
	private static XSSFCellStyle getDataCellStyle(XSSFWorkbook workbook){
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);		
		BorderStyle borderStyle = BorderStyle.THIN;
		cellStyle.setBorderLeft(borderStyle);
		cellStyle.setBorderRight(borderStyle);
		cellStyle.setBorderBottom(borderStyle);
		cellStyle.setBorderTop(borderStyle);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);		
		return cellStyle;
	}	

	private static XSSFCellStyle headerCellStyle(XSSFWorkbook workbook){
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);		
		BorderStyle borderStyle = BorderStyle.THIN;
		cellStyle.setBorderLeft(borderStyle);
		cellStyle.setBorderRight(borderStyle);
		cellStyle.setBorderBottom(borderStyle);
		cellStyle.setBorderTop(borderStyle);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);	
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		cellStyle.setFont(headerFont);
		cellStyle.setFont(headerFont);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		return cellStyle;
	}	

	public static void writetoExcel(XSSFWorkbook workbook,String data,String sheetName, String reportPath) throws Exception {

		Sheet sheet = workbook.createSheet(sheetName);
		XSSFCellStyle cellstyle = getDataCellStyle(workbook);
		XSSFCellStyle headerStyle = headerCellStyle(workbook);
		// Split data into lines
		String[] lines = data.split("\n");

		// Create headers
		String headerLine=lines[1].trim(); //taking iteration as 1 because 0th iteration is empty
		String[] headers = headerLine.split("\\|"); // Split first line by pipe (|)
		int colIndex = 0;
		Row headerRow = sheet.createRow(0);

		for (String header : headers) {
			Cell headerCell = headerRow.createCell(colIndex++);
			headerCell.setCellValue(header);
			headerCell.setCellStyle(headerStyle);

		}



		// Write data rows
		sheet.setColumnWidth(0, 30 * 270); 
		for (int rowIndex = 1; rowIndex < lines.length-2; rowIndex++) {
			String[] values = lines[rowIndex+2].split("\\|");
			colIndex = 0;
			Row dataRow = sheet.createRow(rowIndex);
			for (String value : values) {
				Cell dataCell = dataRow.createCell(colIndex++);
				dataCell.setCellValue(value);
				dataCell.setCellStyle(cellstyle);
				sheet.setColumnWidth(rowIndex, 30 * 270); 
			}
		}
	}
	public static void writeworkbook(XSSFWorkbook workbook, String reportPath) throws Exception {
		// Save the workbook
		String filepath = reportPath+"/"+"UIPerformanceReport"+".xlsx";
		FileOutputStream outputStream = new FileOutputStream(filepath);
		workbook.write(outputStream);
		outputStream.close();
		logger.info("Data written to Excel file: "+"UIPerformanceReport"+".xlsx");
	}

	public String writeDataToTextFile(String filePath, String fileName,String fileContent,String fileFormat)
	{
		filePath = filePath + "/" + fileName + fileFormat;
		try
		{

			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(fileContent);

			writer.close();

			filePath= filePath.replace("\\","/");

			filePath = "<a href = 'file:///"+ filePath + "'>"+ fileName + "</a>";
			return filePath;
		}

		catch (Exception e)
		{
			return filePath;
		}
	}

	public static void createDashboard(XSSFWorkbook workbook,String reportPath) {
		try {
			HashMap<String, Double> screenNameToMedian = new HashMap<>();
			HashMap<String, Double> screenNameToTotalMean = new HashMap<>();
			HashMap<String, Double> screenNameToMinimum = new HashMap<>();
			HashMap<String, Double> screenNameToMaximun = new HashMap<>();
			HashMap<String, Double> screenNameToMedian_New = new HashMap<>();
			HashMap<String, Double> screenNameToTotalMean_New  = new HashMap<>();
			HashMap<String, Double> screenNameToMinimum_New  = new HashMap<>();
			HashMap<String, Double> screenNameToMaximun_New  = new HashMap<>();
			XSSFSheet dashboardSheet = workbook.getSheet("Dashboard");

			CreationHelper createHelper = workbook.getCreationHelper();
			int rowNum = 1;




			for (String screenName : TestRunSettings.UIPerformanceResultsMap.keySet()) {
				String results = TestRunSettings.UIPerformanceResultsMap.get(screenName);
				String arrayResult[]=results.split("\n");
				int totalRows = arrayResult.length - 1; // Exclude header


				double mean=calculateMean(screenName);
				screenNameToTotalMean.put(screenName, mean);
				double median=calculateMedian(screenName);
				screenNameToMedian.put(screenName, median);
				double min=calculateMinimunDuration(screenName);
				screenNameToMinimum.put(screenName, min);
				double max=calculateMaximunDuration(screenName);
				screenNameToMaximun.put(screenName, max);

			}
			screenNameToTotalMean_New=MaximunValueForDashboard(screenNameToTotalMean);
			screenNameToMedian_New=MaximunValueForDashboard(screenNameToMedian);
			screenNameToMinimum_New=MaximunValueForDashboard(screenNameToMinimum);
			screenNameToMaximun_New=MaximunValueForDashboard(screenNameToMaximun);

			// Create a cell style with borders and color for HEADER

			CellStyle headerStyle = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);
			headerStyle.setFont(headerFont);
			headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			
			headerStyle.setBorderBottom(BorderStyle.THICK);
			headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			headerStyle.setBorderLeft(BorderStyle.THICK);
			headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			headerStyle.setBorderRight(BorderStyle.THICK);
			headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			headerStyle.setBorderTop(BorderStyle.THICK);
			headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			// Create a cell style with borders and color
			CellStyle style = workbook.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());

			Row headerRow = dashboardSheet.createRow(0);
			Cell headerCell1 = headerRow.createCell(0);
			headerCell1.setCellValue("S.No.");
			headerCell1.setCellStyle(headerStyle);
			Cell headerCell2 = headerRow.createCell(1);
			headerCell2.setCellValue("Screename");
			headerCell2.setCellStyle(headerStyle);
			Cell headerCell3 = headerRow.createCell(2);
			headerCell3.setCellValue("Average Duration (ms)");
			headerCell3.setCellStyle(headerStyle);
			Cell headerCell4 = headerRow.createCell(3);
			headerCell4.setCellValue("Median Duration (ms)");
			headerCell4.setCellStyle(headerStyle);
			Cell headerCell5 = headerRow.createCell(4);
			headerCell5.setCellValue("Min Duration (ms)");
			headerCell5.setCellStyle(headerStyle);
			Cell headerCell6 = headerRow.createCell(5);
			headerCell6.setCellValue("Max Duration (ms)");
			headerCell6.setCellStyle(headerStyle);
			Cell headerCell7 = headerRow.createCell(6);
			headerCell7.setCellValue("Count of TestCase");
			headerCell7.setCellStyle(headerStyle);
			Cell headerCell8 = headerRow.createCell(7);
			headerCell8.setCellValue("All TestCases");
			headerCell8.setCellStyle(headerStyle);
			Cell headerCell9 = headerRow.createCell(8);
			headerCell9.setCellValue("Max Avrg Duration TestCase");
			headerCell9.setCellStyle(headerStyle);

			
			CellStyle hyperlinkStyle = workbook.createCellStyle();
			Font hyperlinkFont = workbook.createFont();
			hyperlinkFont.setUnderline(Font.U_SINGLE);
			hyperlinkFont.setColor(IndexedColors.BLUE.getIndex());
			hyperlinkStyle.setFont(hyperlinkFont);
			hyperlinkStyle.setBorderBottom(BorderStyle.THIN);
			hyperlinkStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			hyperlinkStyle.setBorderLeft(BorderStyle.THIN);
			hyperlinkStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			hyperlinkStyle.setBorderRight(BorderStyle.THIN);
			hyperlinkStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			hyperlinkStyle.setBorderTop(BorderStyle.THIN);
			hyperlinkStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());


			for (String entry : screenNameToTotalMean_New.keySet()) {
				Row row = dashboardSheet.createRow(rowNum++);
				Cell cell1=row.createCell(0);
				cell1.setCellValue(rowNum - 1);
				cell1.setCellStyle(style);
				String screenName = entry;
				row.createCell(1).setCellValue(screenName);
				// Add hyperlink to the screen name
				Cell cell = row.getCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(screenName);

				// End of hyperlink addition
				Cell cell2=row.createCell(2);
				cell2.setCellValue(screenNameToTotalMean_New.get(entry));
				cell2.setCellStyle(style);
				Cell cell3=row.createCell(3);
				cell3.setCellValue(screenNameToMedian.get(entry));
				cell3.setCellStyle(style);
				Cell cell4=row.createCell(4);
				cell4.setCellValue(screenNameToMinimum.get(entry));
				cell4.setCellStyle(style);
				Cell cell5=row.createCell(5);
				cell5.setCellValue(screenNameToMaximun.get(entry));
				cell5.setCellStyle(style);
				String arrayData="";
				boolean f=true;
				int count=0;
				String keyDataForSheetName=Sheet_ScreenHashMap.get(entry);
				for(String s:screenNameToTestCasesMap.get(keyDataForSheetName)) {
					if(f) {
						arrayData+=s;
						f=false;
					}else {
					arrayData+=":"+s;
					}
					count++;
				}
				
				Cell cell6=row.createCell(6);
				cell6.setCellValue(count);
				cell6.setCellStyle(style);
			
				Cell cell7=row.createCell(7);
				cell7.setCellValue(arrayData);
				cell7.setCellStyle(style);
			
				Cell cell8=row.createCell(8);
				cell8.setCellValue(ScreenNameSheetNameMap.get(entry));
				cell8.setCellStyle(hyperlinkStyle);
				Hyperlink link = createHelper.createHyperlink(HyperlinkType.DOCUMENT);
				link.setAddress("'" + screenName + "'!A1"); // Sheet name followed by cell reference
				cell8.setHyperlink(link);

			}

			// Set column widths
			dashboardSheet.setColumnWidth(0, 10 * 250); // 20 characters width
			dashboardSheet.setColumnWidth(1, 20 * 250); // 200 units width
			dashboardSheet.setColumnWidth(2, 20 * 250); // 20 characters width
			dashboardSheet.setColumnWidth(3, 20 * 250); // 20 characters width
			dashboardSheet.setColumnWidth(4, 20 * 250); // 200 units width
			dashboardSheet.setColumnWidth(5, 20 * 250); // 20 characters width
			dashboardSheet.setColumnWidth(6, 20 * 250); // 20 characters width
			dashboardSheet.setColumnWidth(7, 20 * 250); // 20 characters width
			dashboardSheet.setColumnWidth(8, 20 * 250); // 20 characters width


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static double calculateMean(String screenName) {

		int columnIndex = 2; // For the third column, index is 2
		List<Double> numbers = new ArrayList<>();
		XSSFSheet sheet = workbook.getSheet(screenName);

		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null ) {
				String cellValue = cell.getStringCellValue();
				try {
					double number = Double.parseDouble(cellValue);
					numbers.add(number);
				} catch (NumberFormatException e) {
				}
			}
		}

		double sum = 0;
		for (double number : numbers) {
			sum += number;
		}
		double mean = numbers.isEmpty() ? 0 : sum / numbers.size();
		logger.info("Mean: for screen {} :: {} ",screenName, mean);
		return mean;

	}

	private static double calculateMedian(String screenName) {

		XSSFSheet sheet = workbook.getSheet(screenName);

		int columnIndex = 2; // For the third column, index is 2
		List<Double> numbers = new ArrayList<>();

		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				String cellValue = cell.getStringCellValue();
				try {
					double number = Double.parseDouble(cellValue);
					numbers.add(number);
				} catch (NumberFormatException e) {
					
				}
			}
		}

		Collections.sort(numbers); // Sort the numbers

		double median;
		int size = numbers.size();
		if (size % 2 == 0) {
			median = (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
		} else {
			median = numbers.get(size / 2);
		}

		logger.info("Median: {} ", median);

		return median;

	}

	private static double calculateMinimunDuration(String screenName) {

		XSSFSheet sheet = workbook.getSheet(screenName);

		int columnIndex = 2; // For the third column, index is 2
		List<Double> numbers = new ArrayList<>();

		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				String cellValue = cell.getStringCellValue();
				try {
					double number = Double.parseDouble(cellValue);
					numbers.add(number);
				} catch (NumberFormatException e) {
					
				}
			}
		}

		double minValue = Collections.min(numbers); // Sort the numbers


		logger.info("Minimum: {} ",minValue);

		return minValue;

	}
	private static double calculateMaximunDuration(String screenName) {

		XSSFSheet sheet = workbook.getSheet(screenName);

		int columnIndex = 2; // For the third column, index is 2
		List<Double> numbers = new ArrayList<>();

		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				String cellValue = cell.getStringCellValue();
				try {
					double number = Double.parseDouble(cellValue);
					numbers.add(number);
				} catch (NumberFormatException e) {
				}
			}
		}

		double maxValue = Collections.max(numbers); // Sort the numbers


		logger.info("Maximun: {}", maxValue);

		return maxValue;

	}


	public static HashMap<String,Double> MaximunValueForDashboard(HashMap<String,Double> screenNameToTotalMean) {
		HashMap<String, Double> screenNameResult = new HashMap<>();

		for(String k:TestRunSettings.UIPerformanceResultsMap.keySet()) {

			double maxAccountValue = Double.MIN_VALUE;
			String latestKey=null;

			String keyTrimmed=k.split("_")[0];

			for (Map.Entry<String, Double> entry : screenNameToTotalMean.entrySet()) {
				String key = entry.getKey();

				Double value = entry.getValue();
				if (key.startsWith(keyTrimmed) && value>maxAccountValue) {
						latestKey=key;
						maxAccountValue=value;
					}
					
				 

			}
			screenNameResult.put(latestKey, maxAccountValue);
		}
		return screenNameResult;
	}
}
