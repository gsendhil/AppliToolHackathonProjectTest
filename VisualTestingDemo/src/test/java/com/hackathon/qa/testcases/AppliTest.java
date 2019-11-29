package com.hackathon.qa.testcases;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.hackathon.qa.Applipages.HomePage;
import com.hackathon.qa.Applipages.LoginPage;
import com.hackathon.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AppliTest extends TestBase {

	private static  String browserName;
	//private String siteVersion;
	private static String siteURL;
	LoginPage loginpage;
	HomePage homepage;
	public static BatchInfo batch;

	@BeforeSuite(alwaysRun = true)
	@Parameters("browsername")
	public void getBrowserName(String BrowserName) {
		this.browserName = BrowserName;
		// batch = new BatchInfo("Hackathon");
		setupConfig();
	}

	@BeforeTest
	@Parameters("siteVersion")
	public void getURL(String siteVersion) {

		if (siteVersion.equalsIgnoreCase("Version1")) {
			batch = new BatchInfo("Hackathon_Version1");
			this.siteURL = propConfig.getProperty("SiteURL1");
		} else {
			batch = new BatchInfo("Hackathon_Version2");
			this.siteURL = propConfig.getProperty("SiteURL2");
		}

	}

	@BeforeMethod

	// Launching the Browser
	public void launchBrowser() throws Exception {

		launchBrowser(browserName, siteURL);
		
	}

	// Verifying the Login UI Attributes
	@Test(description = "Verify Login page UI Attributes", priority = 1)
	public void loginuivalidation(Method m) throws Throwable

	{
		loginpage = new LoginPage(getDriver());
		// loginpage.checkElementPresent(m.getName(),batch);
		Thread.sleep(3000);
		eyesValidateWindow(m.getName(), batch);

	}

	// Verifying successfull login message
	@Test(description = "Verify Whether User Can Login Successfully with Valid credentials", priority = 2)
	public void validloginTest(Method m)

	{
		loginpage = new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage = loginpage.clickLoginButton();
		homepage.validateloginsuccess(m.getName(), batch);
	}

	// Password blank validation
	@Test(description = "Verify Whether login fails with blank password", priority = 3)
	public void invalidUsernameTest(Method m)

	{
		loginpage = new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "");
		loginpage.loginfailed(m.getName(), batch);

	}

	// Username blank, password provided, validating login failure
	@Test(description = "Verify Whether login fails with blank username", priority = 4)
	public void invalidPasswordTest(Method m)

	{
		loginpage = new LoginPage(getDriver());
		loginpage.enterLoginDetails("", "sendhil");
		loginpage.loginfailed(m.getName(), batch);
	}

	// Verifying the canvas image
	@Test(description = "Validate the Canvas Chart", priority = 5)
	public void canvasValidateTest(Method m) {
		loginpage = new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage = loginpage.clickLoginButton();

		homepage.canvasChartValidation(m.getName(), batch);

	}

	// Verifying the flast image
	@Test(description = "Validate the Flash Sale images", priority = 6)
	public void VerifyFlashSaleTest(Method m) {
		loginpage = new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage = loginpage.clickLoginButton();
		// homepage.validateloginsuccess(m.getName(),batch);
		homepage.verifyFlashSale(m.getName(), batch);
	}

	// Verifying the transaction table

	@Test(description = "Validate the Transaction Table", priority = 7)
	public void verifyTransactionTableTest(Method m) throws InterruptedException {
		loginpage = new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage = loginpage.clickLoginButton();
		// homepage.validateloginsuccess(m.getName(),batch);
		homepage.sortAmountColumn(m.getName(), batch);
		eyesValidateWindow(m.getName(), batch);

	}

	@AfterMethod
	public void tearDown() {

		eyes.abortIfNotClosed();
		quit();
	}

}
