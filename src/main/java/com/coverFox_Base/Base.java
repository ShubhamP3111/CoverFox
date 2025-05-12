package com.coverFox_Base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.coverFox_Utility.Utility;

public class Base {

	//protected static WebDriver driver;
	
	//Make webdriver nonstatic for parallel testing
	//If WebDriver is static we may encounter issues during parallel execution 
	//protected WebDriver driver;
	
	
	//Used threadlocal because even if we make use of non static WebDriver will face issues if driver is passed as parameter
	//in Utility methods
	//ThreadLocal is Java class which allows you to create variable local to a thread therefore eliminating risks while parallel executions
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@BeforeMethod
	@Parameters("browser")
	public void launchBrowser(String browser) throws IOException {
		crossBrowserTesting(browser);
		getDriver().get(Utility.readDataFromProperties("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(6000));
	}

	public void quitBrowser() {
		Reporter.log("Closing Browser", true);
		getDriver().quit();
		driver.remove();
	}
	
	public WebDriver getDriver() {
	    return driver.get();
	}
	
	
	public void crossBrowserTesting(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("start-maximized", "disable-notifications");
			driver.set(new ChromeDriver(opt)); 
			Reporter.log("Launching Chrome Browser", true);
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
			Reporter.log("Launching firefox browser");
		} else if (browser.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			Reporter.log("Launching Edge browser");
		}
	}
}
