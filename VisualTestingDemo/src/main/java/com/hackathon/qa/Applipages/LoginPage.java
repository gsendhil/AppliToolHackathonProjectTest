package com.hackathon.qa.Applipages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.applitools.eyes.BatchInfo;
import com.hackathon.qa.base.TestBase;

public class LoginPage extends TestBase {

	WebDriver driver;

	// Page Factory

	@FindBy(xpath = "//input[@id='username']")
	WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//button[@id='log-in']")
	WebElement login;

	@FindBy(xpath = "//div[contains(text(),'must be present')]")
	WebElement loginError;

	@FindBy(xpath = "//label[contains(text(),'Username')]")
	WebElement usernameLabel;

	@FindBy(xpath = "//label[contains(text(),'Password')]")
	WebElement passwordLabel;

	@FindBy(xpath = "//label[@class='form-check-label']")
	WebElement remembermeLabel;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement remembermeCheckBox;

//	@FindBy(xpath = "//img[@src='img/social-icons/linkedin.png']")
//	WebElement linkedinImg;

	@FindBy(xpath = "//img[@src='img/social-icons/twitter.png']")
	WebElement twitterImg;

	@FindBy(xpath = "//img[@src='img/social-icons/facebook.png']")
	WebElement facebookImg;
	

	

	
	WebElement userName_UI ;
	WebElement passwordLabel1_UI ;
	WebElement remembermeLabel1_UI ;
	WebElement remembermeCheckBox1_UI ;
	WebElement linkedinImg1_UI ;
	WebElement twitterImg1_UI;
	WebElement facebookImg1_UI ;
	WebElement loginError_UI;
	

	/**
	 * Login page constructor
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		userName_UI = driver.findElement(By.xpath("//input[@id='username']"));
		passwordLabel1_UI = driver.findElement(By.xpath("//input[@id='password']"));
		remembermeLabel1_UI = driver.findElement(By.xpath("//label[@class='form-check-label']/.."));
		remembermeCheckBox1_UI = driver.findElement(By.xpath("//input[@type='checkbox']"));
		//linkedinImg1_UI = driver.findElement(By.xpath("//img[@src='img/social-icons/linkedin.png']/.."));
		twitterImg1_UI = driver.findElement(By.xpath("//img[@src='img/social-icons/twitter.png']/.."));
		facebookImg1_UI = driver.findElement(By.xpath("//img[@src='img/social-icons/facebook.png']/.."));
						
	}

	
	public void checkElementPresent(String testName, BatchInfo batch)
	{
		eyesValidateElement(userName_UI, "User Name Validate", batch,"UserName Field");
		eyesValidateElement(passwordLabel1_UI, "Password Validate", batch, "Password Field");
	}

	public void enterLoginDetails(String user, String pwd) {
		type_Appli(username, user, "User Name");
		type_Appli(password, pwd, "Password");

	}

	public HomePage clickLoginButton() {

		click_Appli(login, "Login");

		return new HomePage(driver);
	}


	
	public void loginfailed(String testName, BatchInfo batch)
	{
		click_Appli(login, "Login");
		loginError_UI = driver.findElement(By.xpath("//div[contains(text(),'must be present')]"));
		eyesValidateElement(loginError_UI, testName, batch,"Login Screen UI Attributes Check");
			 
	}
	

}
