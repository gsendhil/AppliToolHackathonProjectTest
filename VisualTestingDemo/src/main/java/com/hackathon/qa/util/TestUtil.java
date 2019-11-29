package com.hackathon.qa.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import com.hackathon.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase
{

	public static long PAGE_LOAD_TIMEOUT = 20;

	public static long IMPLICIT_WAIT = 10;
	
	public static String screenshotPath;
	public static String screenshotName;

	

	public static String  getCurrentDate() {
		Date d = new Date();
		return System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));

	}

	
	
	public static void captureScreenshot() {
	 
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "\\ExtentReports\\" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static String checkMandatoryFields(List<WebElement> MandatoryFields) {
		String[] mandateList = null;
		try
		{
			if (MandatoryFields.size() > 0) {
				mandateList = new String[MandatoryFields.size()];

				for (int i = 0; i < MandatoryFields.size(); i++) {
					mandateList[i] = MandatoryFields.get(i).getText();
				}

			}
			return Arrays.toString(mandateList);
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{
			test.log(LogStatus.FAIL, " Verification failed with exception : " + e.getMessage());
		}
		return null;
	}

}
	
