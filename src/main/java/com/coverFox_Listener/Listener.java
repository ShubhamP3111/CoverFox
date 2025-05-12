package com.coverFox_Listener;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.coverFox_Utility.Utility;



public class Listener extends com.coverFox_Base.Base implements ITestListener{
	
	@Override
	public void onTestSuccess(ITestResult result) {
		//result.getName();
		Reporter.log("TC is passed "+result.getName(), true);
		
		try {
			Utility.takeScreenshot(getDriver());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("TC is failed "+result.getName(), true);
		try {
			Utility.takeScreenshot(getDriver());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("TC is skipped "+result.getName(), true);
	}

}
