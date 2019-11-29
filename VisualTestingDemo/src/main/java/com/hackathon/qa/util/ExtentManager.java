package com.hackathon.qa.util;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	public static ExtentReports getInstance(String suiteName) {
		
		if(extent==null)
		{
			TestUtil.getCurrentDate();
			extent = new ExtentReports(".\\ExtentReports\\"+suiteName +TestUtil.getCurrentDate()+".html",true,DisplayOrder.OLDEST_FIRST);
		    extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		}
		return extent;
	}
}
