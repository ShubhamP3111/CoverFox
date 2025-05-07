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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.coverFox_POM.CoverFoxAddressDetailsPage;
import com.coverFox_POM.CoverFoxGenderPage;
import com.coverFox_POM.CoverFoxHomePage;
import com.coverFox_POM.CoverFoxResultPage;
import com.coverFox_POM.CoverFoxSelectAge;

public class CoverFoxValidatePincodeAndMobileErrorMsg extends com.coverFox_Base.Base {

	public static Logger logger;
	CoverFoxHomePage coverFoxHomePage;
	CoverFoxAddressDetailsPage coverFoxAddressDetailsPage;
	CoverFoxGenderPage coverFoxGenderPage;
	CoverFoxSelectAge coverFoxSelectAge;
	CoverFoxResultPage coverFoxResultPage;

	@BeforeMethod
	public void coverFoxPreRequisites() throws InterruptedException, IOException {
		logger = Logger.getLogger("CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Begin Testing for CoverFox");
		
	//	launchBrowser();
		logger.info("launching browser");
		// myFile = new FileInputStream("path of excel");
		// mySheet = WorkbookFactory.create(myFile).getSheet("Sheetname");

		coverFoxHomePage = new CoverFoxHomePage(getDriver());
		coverFoxAddressDetailsPage = new CoverFoxAddressDetailsPage(getDriver());
		coverFoxGenderPage = new CoverFoxGenderPage(getDriver());
		coverFoxSelectAge = new CoverFoxSelectAge(getDriver());
		coverFoxResultPage = new CoverFoxResultPage(getDriver());
		
		coverFoxHomePage.HomePage();
		logger.info("coverFoxHomePage");
		coverFoxGenderPage.selectGender();
		logger.info("coverFoxGenderPage");
		coverFoxSelectAge.select("27");
		logger.info("coverFoxSelectAge");
		coverFoxAddressDetailsPage.clickContinueBtn();
		logger.info("Click on continue button on Address Page");

		Thread.sleep(4000);
	}

	@Test
	public void validateAddressPagePinErrorMessages() {
		String errorMsgPincode = coverFoxAddressDetailsPage.getpinCodeErrorMessage();
		logger.info("Validating Pincode Error Message");
		Assert.assertEquals(errorMsgPincode, "Please enter a valid pincode");

	}

	@Test
	public void validateAddressPageMobileErrorMessages() {

		String errorMsgMobile = coverFoxAddressDetailsPage.getmobileErrorMessage();
		logger.info("Validating Mobile Error Message");
		Assert.assertEquals(errorMsgMobile, "Please enter a valid mobile no.");

	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		logger.info("Closing Browser");
		quitBrowser();

	}
}
