package com.hackathon.qa.base;

import static com.hackathon.qa.base.TestBase.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.apache.log4j.Logger;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;

import com.hackathon.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static Properties propConfig;
	public static String userDirector = System.getProperty("user.dir");
	public static Logger log = Logger.getLogger(TestBase.class);
	public ExtentReports objExRepo;
	public static ExtentTest test;
	public static String testClassName = null;
	public static SoftAssert softAssert;
	public static Eyes eyes;

	// Driver variable
	public static WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	// Driver getter and setter.
	public static WebDriver getDriver() {
		return dr.get();
	}

	public static void setWebDriver() {
		dr.set(driver);
	}

	public void setupConfig() {
		initProperties(); // Initialize properties file
		initLogConfig();

	}

	// Create new browser instances.
	public void launchBrowser(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Launching browser" + browser);
			// Log.debug(msg);
			System.setProperty(propConfig.getProperty("CHROME_DRIVERCLASS"),
					userDirector + propConfig.getProperty("CHROME_DRIVERPATH"));
			driver = new ChromeDriver();
			initiateeyes();

			setWebDriver();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(propConfig.getProperty("implicit.wait")),
					TimeUnit.SECONDS);

		} else if (browser.equalsIgnoreCase("IE11")) {
			System.out.println("Launching browser" + browser);
			System.setProperty(propConfig.getProperty("IE11_DRIVERCLASS"),
					userDirector + propConfig.getProperty("IE11_DRIVERPATH"));
			driver = new InternetExplorerDriver();
			setWebDriver();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(propConfig.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}

		getDriver().get(url);

	}

	// Quit the Driver
	public void quit() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

	public static void initiateeyes() {
		eyes = new Eyes();
		eyes.setApiKey(propConfig.getProperty("applitools.api.key"));
		eyes.setLogHandler(new StdoutLogHandler(true));
		eyes.setLogHandler(new FileLogger(".src/test/resources/logs/file.log", true, true));

	}

	public static void eyesValidateWindow(String testName, BatchInfo batch) {

		eyes.setBatch(batch);
		// eyes.setMatchLevel(MatchLevel.CONTENT);
		eyes.setForceFullPageScreenshot(true);
		eyes.open(getDriver(), "AppliTools Demo VisualTesting", testName);
		eyes.checkWindow();
		eyes.close();
	}

	public static void eyesValidateElement(WebElement element, String testName, BatchInfo batch, String validationmsg) {
		eyes.setBatch(batch);
		eyes.setForceFullPageScreenshot(true);
		eyes.open(getDriver(), "AppliTools Demo VisualTesting", testName);
		// eyes.checkElement(element);
		eyes.checkElement(element, 5, validationmsg);
		eyes.close();
	}

	// InitialiZe config file
	public static void initProperties() {
		propConfig = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(userDirector + "\\src\\test\\resources\\properties\\config.properties");
			propConfig.load(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// InitialiZe Log config file
	public static void initLogConfig() {
		TestUtil.getCurrentDate();
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
	}

	// Verify if the element is visible
	public static void isElementPresent(WebElement element, String msg) {

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 8);
			wait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed()) {
				test.log(LogStatus.PASS, msg + " is displayed in Login UI");

			}
		} catch (Exception e) {

			test.log(LogStatus.FAIL, " Verification failed with exception for : " + element + e.getMessage());

		}

	}

	public static void isElementPresent_Appli(WebElement element, String msg) {

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 8);
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {

			System.out.println("Verification failed with exception :" + e.getMessage());

		}

	}

	// Wrapper method for Web element click
	public void click(WebElement element, String locatorName) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 8);
			wait.until(ExpectedConditions.elementToBeClickable(element));

			if (element.isDisplayed()) {

				test.log(LogStatus.PASS, locatorName + " button is displayed ");
				element.click();
				log.debug("Clicking on an Element : " + element);
				test.log(LogStatus.INFO, "Clicking on button : " + locatorName);

			}
		} catch (NoSuchElementException e) {

			test.log(LogStatus.FAIL, "Verification failed with exception : " + locatorName + " does not exist");
			test.log(LogStatus.FAIL, locatorName + "button is not displayed ");
			log.debug("Verification failed with exception for element  : " + locatorName + ": " + e.getMessage());

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Verification failed with exception : " + locatorName + " does not exist");
		}

	}

	public void click_Appli(WebElement element, String locatorName) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 8);
			wait.until(ExpectedConditions.elementToBeClickable(element));

			if (element.isDisplayed()) {

				element.click();

			}
		} catch (NoSuchElementException e) {
			System.out.println("Verification failed with exception :" + e.getMessage());

		} catch (Exception e) {
			System.out.println("Verification failed with exception :" + e.getMessage());
		}

	}

	// Wrapper method for sendkeys ( enter values in the fields)
	public void type(WebElement element, String value, String locatorName) {
		try {

			WebDriverWait wait = new WebDriverWait(getDriver(), 8);
			wait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed()) {
				element.sendKeys(value);
				log.debug("Typing in an Element : " + element + " entered value as : " + value);
				if (!locatorName.equals("Password")) {
					test.log(LogStatus.INFO, "Enter : " + locatorName + " field value as : " + value);
				}

			}
		} catch (NoSuchElementException e) {

			test.log(LogStatus.FAIL, "Verification failed with exception : " + e.getMessage());
			test.log(LogStatus.FAIL, element + " is not displayed ");
			log.debug("Verification failed with exception for element  : " + element + ": " + element);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Verification failed with exception : " + locatorName + " does not exist");
		}

	}

	// Wrapper method for sendkeys ( enter values in the fields)
	public void type_Appli(WebElement element, String value, String locatorName) {
		try {

			WebDriverWait wait = new WebDriverWait(getDriver(), 8);
			wait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed()) {
				element.sendKeys(value);

			}
		} catch (NoSuchElementException e) {
			System.out.println("Verification failed with exception :" + e.getMessage());

		} catch (Exception e) {
			System.out.println("Verification failed with exception :" + e.getMessage());
		}

	}

	public void verfiyEquals(String expected, String actual, String succMsg, String failMsg) throws IOException {

		try {
			Assert.assertEquals(actual, expected);
			test.log(LogStatus.PASS, succMsg);
		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			// Extent Reports
			test.log(LogStatus.FAIL, failMsg + "Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			log.debug("Verification failed with exception : " + t.getMessage());
			softAssert.assertEquals(actual, expected); // Assertion Failure
		}

	}

}
