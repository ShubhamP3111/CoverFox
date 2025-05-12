package com.coverFoxTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.coverFox_POM.CoverFoxAddressDetailsPage;
import com.coverFox_POM.CoverFoxGenderPage;
import com.coverFox_POM.CoverFoxHomePage;
import com.coverFox_POM.CoverFoxSelectAge;



public class CoverFoxValidateErrorMsgOnAddressPage extends com.coverFox_Base.Base{


	CoverFoxHomePage coverFoxHomePage;
	CoverFoxAddressDetailsPage coverFoxAddressDetailsPage;
	CoverFoxGenderPage coverFoxGenderPage;
	CoverFoxSelectAge coverFoxSelectAge;
	public static Logger logger;

	@BeforeClass
	public void openBrowser() throws EncryptedDocumentException, IOException {
		
		

	}

	@BeforeMethod
	public void coverFoxPreRequisites() throws InterruptedException {
		
		logger = Logger.getLogger("CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Begin Testing for CoverFoxErrorMsgonAddressPage");

		logger.info("launching browser");
		//launchBrowser();
		
		// myFile = new FileInputStream("path of excel");
		// mySheet = WorkbookFactory.create(myFile).getSheet("Sheetname");

		coverFoxHomePage = new CoverFoxHomePage(getDriver());
		coverFoxAddressDetailsPage = new CoverFoxAddressDetailsPage(getDriver());
		coverFoxGenderPage = new CoverFoxGenderPage(getDriver());
		coverFoxSelectAge = new CoverFoxSelectAge(getDriver());
		
		coverFoxHomePage.HomePage();
		logger.info("coverFoxHomePage");
		// coverFoxAddressDetailsPage.Enterpin(mySheet.getRow(0).getCell(0).getStringCellValue(),mySheet.getRow(0).getCell(0).getStringCellValue());
		coverFoxGenderPage.selectGender();
		logger.info("coverFoxGenderPage");
		coverFoxSelectAge.select("27");
		logger.info("coverFoxSelectAge");
		coverFoxAddressDetailsPage.clickContinueBtn();
		logger.info("Click on coninue button on Address Page");
		
		

		// coverFoxSelectAge.select(mySheet.getRow(0).getCell(0).getStringCellValue());

		Thread.sleep(4000);
	}

	@Test
	public void validateAddressPageErrorMessages() {
		String errorMsgPincode = coverFoxAddressDetailsPage.getpinCodeErrorMessage();
		String errorMsgMobile = coverFoxAddressDetailsPage.getmobileErrorMessage();
		SoftAssert soft = new SoftAssert();
		logger.info("Validating Pincode Error Message");
		soft.assertEquals(errorMsgPincode, "Please enter a valid pincode");
		logger.info("Validating Mobile Error Message");
		soft.assertEquals(errorMsgMobile, "Please enter a valid mobile no.");
		soft.assertAll();
		
	}
	
	

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		logger.info("Closing Browser");
		quitBrowser();

	}
}
