package com.coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.coverFox_POM.CoverFoxAddressDetailsPage;
import com.coverFox_POM.CoverFoxGenderPage;
import com.coverFox_POM.CoverFoxHomePage;
import com.coverFox_POM.CoverFoxResultPage;
import com.coverFox_POM.CoverFoxSelectAge;

public class CoverFoxTestUsingTestNG extends com.coverFox_Base.Base {

	public static Logger logger;
	CoverFoxHomePage coverFoxHomePage;
	CoverFoxAddressDetailsPage coverFoxAddressDetailsPage;
	CoverFoxGenderPage coverFoxGenderPage;
	CoverFoxSelectAge coverFoxSelectAge;
	CoverFoxResultPage coverFoxResultPage;

	@BeforeClass
	public void openBrowser() throws EncryptedDocumentException, IOException {
		logger = Logger.getLogger("CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Begin Testing for CoverFox");

		launchBrowser();
		logger.info("launching browser");
		// myFile = new FileInputStream("path of excel");
		// mySheet = WorkbookFactory.create(myFile).getSheet("Sheetname");

		coverFoxHomePage = new CoverFoxHomePage(getDriver());
		coverFoxAddressDetailsPage = new CoverFoxAddressDetailsPage(getDriver());
		coverFoxGenderPage = new CoverFoxGenderPage(getDriver());
		coverFoxSelectAge = new CoverFoxSelectAge(getDriver());
		coverFoxResultPage = new CoverFoxResultPage(getDriver());

	}

	@BeforeMethod
	public void coverFoxPreRequisites() throws InterruptedException {
		coverFoxHomePage.HomePage();
		logger.info("coverFoxHomePage");
		// coverFoxAddressDetailsPage.Enterpin(mySheet.getRow(0).getCell(0).getStringCellValue(),mySheet.getRow(0).getCell(0).getStringCellValue());
		coverFoxGenderPage.selectGender();
		logger.info("coverFoxGenderPage");
		coverFoxSelectAge.select("27");
		logger.info("coverFoxSelectAge");
		coverFoxAddressDetailsPage.Enterpin("411014", "8888888888");
		logger.info("coverFoxAddressDetailsPage");
		// coverFoxSelectAge.select(mySheet.getRow(0).getCell(0).getStringCellValue());

		Thread.sleep(4000);
	}

	@Test
	public void validateCoverFoxPlans() {
		// Assert.fail();
		int planNumberFromText = coverFoxResultPage.getPlanNumberFromText();
		int planNumberFromCards = coverFoxResultPage.getPlanNumberFromPlanCards();
		logger.info("Validating coverFoxPlans");
		Assert.assertEquals(planNumberFromText, planNumberFromCards, "TC failed, plan numbers does not match");
		Reporter.log("Plan number from Text = " + planNumberFromText, true);
		Reporter.log("Plan number from Cards = " + planNumberFromCards, true);
		Reporter.log("TC Passed", true);
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		logger.info("Closing Browser");
		quitBrowser();

	}
}
