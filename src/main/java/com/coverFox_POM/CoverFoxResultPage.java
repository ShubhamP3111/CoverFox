package com.coverFox_POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxResultPage {

	@FindBy(xpath = "//div[contains(text(), 'matching Health')]")
	private WebElement result;
	@FindBy(className = "plan-card-container")
	private List<WebElement> toCompare;

	public CoverFoxResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void validateResult() throws InterruptedException {
		Thread.sleep(2000);

		String s = result.getText();
		String[] s1 = s.split(" ");
		int a = Integer.parseInt(s1[0]);
		if (a == toCompare.size()) {
			System.out.println("TC is passed");
		} else {
			System.out.println("TC is failed");
		}

	}

	public int getPlanNumberFromText() {

		Reporter.log("Fetching plan number from Text", true);
		String s = result.getText();
		String[] s1 = s.split(" ");
		int planNumber = Integer.parseInt(s1[0]);
		return planNumber;
	}

	public int getPlanNumberFromPlanCards() {
		Reporter.log("Fetching plan number from plan cards", true);
		int planNumberfromCards = toCompare.size();
		return planNumberfromCards;
	}

}
