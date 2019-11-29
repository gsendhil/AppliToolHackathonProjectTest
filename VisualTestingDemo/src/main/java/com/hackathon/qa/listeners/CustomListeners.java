package com.hackathon.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.hackathon.qa.base.TestBase;
import com.hackathon.qa.util.CustomSoftAssert;
import com.hackathon.qa.util.ExtentManager;
import com.hackathon.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		
		objExRepo = ExtentManager.getInstance(context.getSuite().getName());
		
	}

	public void onTestStart(ITestResult arg0) {

		test = objExRepo.startTest(arg0.getMethod().getDescription().toUpperCase());
		

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		objExRepo.endTest(test);
		objExRepo.flush();
	}

	@Override
	public void onTestFailure(ITestResult arg0) {

		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		objExRepo.flush();

	}

}
