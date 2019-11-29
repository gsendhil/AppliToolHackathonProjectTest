package com.hackathon.qa.Applipages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.applitools.eyes.BatchInfo;
import com.hackathon.qa.base.TestBase;

public class HomePage extends TestBase{

	
	WebDriver driver;

	// Page Factory Elements 
	
	@FindBy(xpath = "//div[@class='logged-user-name']")
	WebElement loggedinUser;
	
	@FindBy(xpath="//a[@id='showExpensesChart']")
	WebElement CompareExpenses;
	
	@FindBy(xpath="//canvas[@id='canvas']")
	WebElement Chart;
	
	@FindBy(xpath="//img[@src='img/flashSale.gif']")
	WebElement FlashSale1;
	
	@FindBy(xpath="//img[@src='img/flashSale2.gif']")
	WebElement FlashSale2;
	
	@FindBy(xpath="//th[@id='amount']")
	WebElement AmountColumn;
	
	@FindBy(xpath="//table[@id='transactionsTable']")
	WebElement TransactionTable;
	
	@FindBy(xpath = "//button[contains(text(),'Show data for next year')]")
	WebElement ShowDataForNextYear;
	
	
	WebElement loggedinUser_UI;
	WebElement CanvasChart_UI;
	WebElement FlashSale1_UI;
	WebElement FlashSale2UI;
	WebElement AmountColumn_UI;
	WebElement ShowDataForNextYear_UI;
	
			

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		loggedinUser_UI = driver.findElement(By.xpath("//div[@class='logged-user-name']"));
	}
	
	public void validateloginsuccess(String testName, BatchInfo batch)
	{
		isElementPresent_Appli(loggedinUser, "Loggedin User Name");
		//Verifying the Login Success Message
		eyesValidateElement(loggedinUser_UI, "Login Success", batch,"Validating Login Success");
			 
	}
	
	public void canvasChartValidation(String testName, BatchInfo batch)
	{
		click_Appli(CompareExpenses, "Compare Expenses");
		//Verifying the Canvas Chart
		eyesValidateWindow("Canvas Chart Validation",batch);
		click_Appli(ShowDataForNextYear, "Show Data for Next Year");
		isElementPresent_Appli(Chart, "Next Year Canvas Chart");
		//Verifying the Next year canvas chart
		eyesValidateWindow("Canvas Chart Show Next Year",batch);
		
			 
	}
	
	public void verifyFlashSale(String testName, BatchInfo batch)
	{
		isElementPresent_Appli(FlashSale1, "Flash sale Image1");
		isElementPresent_Appli(FlashSale2, "Flash sale Image2");
		//Verifying the Flash images
		eyesValidateWindow(testName,batch);
		
	
	}
	
	//Verifying the Amount column is sorted
	public void sortAmountColumn(String testName, BatchInfo batch) throws InterruptedException
	{
		AmountColumn.click();
		Thread.sleep(2000);
	}

}
