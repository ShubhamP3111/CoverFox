package com.coverFox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxGenderPage {
	
	@FindBy(className = "next-btn") private WebElement nextBtn;
	
	
	public CoverFoxGenderPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void selectGender() {
		Reporter.log("Clicking on Gender", true);
		nextBtn.click();
	}
}
