package com.coverFox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.coverFox_Base.Base;
import com.coverFox_Utility.Utility;

public class CoverFoxSelectAge {
	
	@FindBy(id = "Age-You") private WebElement selectAge;
	@FindBy(className = "next-btn") private WebElement nextBtn;
	
	public CoverFoxSelectAge(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void select(String age) {
		
		Reporter.log("Handeling Age Dropdown", true);
		Select s = new Select(selectAge);
		s.selectByValue(age+"y");
		nextBtn.click();
	}

}
