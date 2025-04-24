package com.coverFox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxHomePage {
	
	//@FindBy(xpath = "//input[@type='tel']") private WebElement mobileNo;
	@FindBy(xpath = "//button[@title='Get Started']") private WebElement getStartedBtn;
	
	
	public CoverFoxHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void HomePage()
	{
	//	mobileNo.sendKeys(number);
		getStartedBtn.click();
		
	}	
	
	
	
	

}
