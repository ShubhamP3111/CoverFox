package com.coverFox_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.coverFox_Base.Base;

public class Utility {

	public static String readDataFromExcel(String sheetName, int rowIndex, int cellIndex)
			throws EncryptedDocumentException, IOException {

		FileInputStream myFile = new FileInputStream("");
		Sheet sheet = WorkbookFactory.create(myFile).getSheet(sheetName);
		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(cellIndex);
		String value = cell.getStringCellValue();
		Reporter.log("Reading data from Excel", true);
		return value;
	}

	public static void takeScreenshot(WebDriver driver) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		File destination = new File("C:\\Users\\Shubham\\OneDrive\\Desktop\\screenshots\\CoverFox" + timeStamp + ".png");
		FileHandler.copy(src, destination);
		Reporter.log("Taking screenshot and saving at destination "+destination, true);
	}
	
	public static void scrollToWebElement(WebDriver driver, WebElement refElement) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", refElement);
		Reporter.log("Scrolling to WebElement", true);
	}
	
	public static String readDataFromProperties(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream propFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\testData\\coverFox.properties");
		prop.load(propFile);
		String value = prop.getProperty(key);
		return value;
		
	}
	
	public static void explicitWaitOnWebElements(WebDriver driver, WebElement element, int milliSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milliSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
