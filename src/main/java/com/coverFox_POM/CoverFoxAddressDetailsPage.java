package com.coverFox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxAddressDetailsPage {
	
	@FindBy(xpath="(//input[@type='number'])[1]") private WebElement pin;
	@FindBy(xpath="(//input[@type='number'])[2]") private WebElement mobileNo;
	@FindBy(xpath="//div[text()='Continue']") private WebElement continueButton;
	@FindBy(xpath = "//div[@class='error-ui']") private WebElement pinCodeMsg;
	@FindBy(xpath = "//div[@class='want-expert__error error-ui']") private WebElement mobileMsg;
	
	public CoverFoxAddressDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void Enterpin(String pincode, String mobile) 
	{
		
		Reporter.log("Address details Page", true);
		
		Reporter.log("Entering Pin", true);
		pin.sendKeys(pincode);
		
		Reporter.log("Entering Mobile number", true);
		mobileNo.sendKeys(mobile);
		
		Reporter.log("Clicking continue button", true);
		continueButton.click();
	}
	
	public void clickContinueBtn() {
		Reporter.log("Address details Page", true);
		Reporter.log("Clicking continue button", true);
		continueButton.click();
	}

	public String getpinCodeErrorMessage() {
		Reporter.log("Checking if pincode error msg is displayed " + pinCodeMsg.isDisplayed(), true);
		String pinCodeErrorMsg = pinCodeMsg.getText();
		return pinCodeErrorMsg;

	}

	public String getmobileErrorMessage() {

		Reporter.log("Checking if mobile error msg is displayed " + pinCodeMsg.isDisplayed(), true);
		String mobileErrorMsg = mobileMsg.getText();
		return mobileErrorMsg;

	}

		
	}
	
	
