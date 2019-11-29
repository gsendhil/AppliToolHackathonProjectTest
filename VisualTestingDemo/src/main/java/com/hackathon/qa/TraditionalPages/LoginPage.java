package com.hackathon.qa.TraditionalPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	@FindBy(xpath = "//img[@src='img/social-icons/linkedin.png']")
	WebElement linkedinImg;

	@FindBy(xpath = "//img[@src='img/social-icons/twitter.png']")
	WebElement twitterImg;

	@FindBy(xpath = "//img[@src='img/social-icons/facebook.png']")
	WebElement facebookImg;

	/**
	 * Login page constructor
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginUiValidation() {

		isElementPresent(usernameLabel, "User Name Label");
		isElementPresent(passwordLabel, "password Label");
		isElementPresent(remembermeLabel, "Remember me Label");
		isElementPresent(remembermeCheckBox, "Remember Me Checkbox");
		isElementPresent(linkedinImg, "Linkedin Image");
		isElementPresent(twitterImg, "Twitter Image");
		isElementPresent(facebookImg, "facebook image");

	}

	public void enterLoginDetails(String user, String pwd) {
		type(username, user, "User Name");
		type(password, pwd, "Password");

	}

	public HomePage clickLoginButton() {

		click(login, "Login");

		return new HomePage(driver);
	}

	public boolean clickLoginButtonInvalid() {

		boolean flag = false;
		click(login, "Login");

		if (loginError.isDisplayed()) {
			return true;
		} else {
			return flag;
		}
	}

}
