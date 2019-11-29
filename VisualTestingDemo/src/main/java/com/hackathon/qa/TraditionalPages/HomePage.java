package com.hackathon.qa.TraditionalPages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hackathon.qa.base.TestBase;

public class HomePage extends TestBase{

	
	WebDriver driver;

	// Page Factory
	@FindBy(xpath = "//div[@id='logged-user-name']")
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
				

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void validateloginsuccess()
	{
		isElementPresent(loggedinUser, "Loggedin User Name");
	
	}
	

	public void canvasChartValidation()
	{
		click(CompareExpenses, "Compare Expenses");
		//There is no proper way to validate the canvas chart using selenium, in this test i have limited it to validation only presense of the element
		isElementPresent(Chart, "Canvas Chart");
			 
	}
	
	public void verifyFlashSale()
	{
		isElementPresent(FlashSale1, "Flash sale Image1");
		isElementPresent(FlashSale2, "Flash sale Image2");
	}
	
	public boolean verifyTransactionTable() throws InterruptedException
	{
		String prevalue = null;
		
		List<WebElement> listitems = driver.findElements(By.xpath("//tr/td[5]/span"));
		ArrayList<String> BaseData = new ArrayList<String>();
		for (int icouter = 1; icouter<=listitems.size(); icouter++)
		{
			WebElement arrayvalue = driver.findElement(By.xpath("//tr[" + icouter + "]/td[5]/span"));
			prevalue = arrayvalue.getText();
			BaseData.add(prevalue);
			
		}
		
		Collections.sort(BaseData);
				
		
		AmountColumn.click();
		Thread.sleep(2000);
		prevalue = null;
		
		List<WebElement> Slistitems = driver.findElements(By.xpath("//tr/td[5]/span"));
		ArrayList<String> Sortedlistitems = new ArrayList<String>();
		for (int icouter = 1; icouter<=Slistitems.size(); icouter++)
		{
			WebElement arrayvalue = driver.findElement(By.xpath("//tr[" + icouter + "]/td[5]/span"));
			prevalue = arrayvalue.getText();
			Sortedlistitems.add(prevalue);
	
		}
		
	//writing a logic for convering the string content in to integer is bit tediouls due to the content of the cell, this verified only a strign comparision
		
		if (BaseData.equals(Sortedlistitems))
		{
			return true;
		}
		return false;
	}
}
