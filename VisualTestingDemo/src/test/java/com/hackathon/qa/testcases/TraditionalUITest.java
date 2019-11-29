package com.hackathon.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.hackathon.qa.TraditionalPages.HomePage;
import com.hackathon.qa.TraditionalPages.LoginPage;
import com.hackathon.qa.base.TestBase;


import com.relevantcodes.extentreports.LogStatus;

public class TraditionalUITest extends TestBase {


	LoginPage loginpage ;
	HomePage homepage;
	private static  String browserName;
	//private String siteVersion;
	private static String siteURL;
	
	@BeforeSuite(alwaysRun=true)
	@Parameters("browsername")
	public void getBrowserName(String BrowserName) {
		this.browserName = BrowserName;
		setupConfig();
	//All the test cases are full automated 
	//Note: the Amount column sort validation is performed only as strign validation
		
		
	}

	@BeforeTest
	@Parameters("siteVersion")
	public void getURL(String siteVersion) {

		if (siteVersion.equalsIgnoreCase("Version1")) {
			
			this.siteURL = propConfig.getProperty("SiteURL1");
		} else {
		
			this.siteURL = propConfig.getProperty("SiteURL2");
		}

	}

	
	@BeforeMethod
	
	public void launchBrowser() throws Exception {
		
		
		launchBrowser(browserName, siteURL);
		//test.log(LogStatus.PASS, "Launching the browser in "+browserName);
		Thread.sleep(1000);
	}
	
	
	
	@Test(description = "Verify Login page UI Attributes" ,priority = 1)
	public void loginuivalidation()
	
	{
		loginpage= new LoginPage(getDriver());
		loginpage.loginUiValidation();
		
	}
	
	
	@Test(description = "Verify Whether User Can Login Successfully with Valid credentials" ,priority = 2)
	public void validloginTest()
	
	{
		loginpage= new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage =  loginpage.clickLoginButton();
		homepage.validateloginsuccess();
	}
	

	@Test(description = "Verify Whether login fails with blank password", priority = 3)
	public void invalidUsernameTest()
	
	{
		loginpage= new LoginPage(getDriver());
		 loginpage.enterLoginDetails("sendhil", "");
			
		 if(loginpage.clickLoginButtonInvalid())
		 {
			 Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Login failed as expected- password left empty");
		 }
		 else
		 {
			 test.log(LogStatus.FAIL, "Unexpected : login success  password left empty");
		 }
		 initiateeyes();
	}
	
	@Test(description = "Verify Whether login fails with blank username" ,priority = 4)
	public void invalidPasswordTest()
	
	{
		loginpage= new LoginPage(getDriver());
		
		 loginpage.enterLoginDetails("", "sendhil");
			
		 if(loginpage.clickLoginButtonInvalid())
		 {
			 Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Login failed as expected- username left empty");
		 }
		 else
		 {
			test.log(LogStatus.FAIL, "Unexpected : login success  username left empty");
		 }
		 
	}
	
	@Test(description = "Validate the Canvas Chart" ,priority = 5)
	public void canvasValidateTest()
	{
		loginpage= new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage =  loginpage.clickLoginButton();
		homepage.validateloginsuccess();
		homepage.canvasChartValidation();
	
	}
	
	@Test(description = "Validate the Canvas Chart" ,priority = 6)
	public void VerifyFlashSaleTest()
	{
		loginpage= new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage =  loginpage.clickLoginButton();
		homepage.verifyFlashSale();
		
	}
	
	//Only string level validation performed for Amount column
	@Test(description = "Validate the Transaction Table" ,priority = 7)
	public void verifyTransactionTableTest() throws InterruptedException
	{
		loginpage= new LoginPage(getDriver());
		loginpage.enterLoginDetails("sendhil", "capgemini");
		homepage =  loginpage.clickLoginButton();
		if(homepage.verifyTransactionTable())
			{
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Verified the Amount Column in the Table is sorted Correctly");
			}
		else
			{
			test.log(LogStatus.FAIL, "Verified the Amount Column in the Table is not sorted Correctly");
			}
	}
	
			
	@AfterMethod
	public void tearDown() {
		quit();
	}
	
}
