package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailAddress = By.id("input-email");
	private By password = By.id("input-password");
	private By forgottenPasswordLink = By.linkText("Forgotten Password");
	private By submitBtn = By.xpath("//input[@type='submit' and @value='Login']");
	private By registerLink = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting Login Page Title...")
	public String getLoginPageTitle() {
		String lpTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		Log.info("Login Page Title is : " + lpTitle);
		return lpTitle;
	}
	
	@Step("Getting Login Page Url...")
	public String getLoginPageUrl() {
		String lpUrl = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		Log.info("Login Page URL is : " + lpUrl);
		return lpUrl;
	}
	
	@Step("Verifying whether Forgotten Password Link exist or not...")
	public boolean isForgottenPasswordLinkExist() {
		return eleUtil.waitForElementVisible(forgottenPasswordLink, TimeUtil.DEFAULT_SHORT_TIME).isDisplayed();
	}
	
	@Step("Logging in with the username : {0} and password : {1}")
	public MyAccountsPage doLogin(String username, String pwd) {
		Log.info("Logging in with username : " + username + " and password : " + pwd);
		eleUtil.waitForElementVisible(emailAddress, TimeUtil.DEFAULT_SHORT_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(submitBtn);
		return new MyAccountsPage(driver);
	}
	
	@Step("Navigating to Registration Page....")
	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_SHORT_TIME).click();
		return new RegistrationPage(driver);
	}

}
