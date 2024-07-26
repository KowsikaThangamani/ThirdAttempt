package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirm = By.id("input-confirm");
	private By subscribeYes = By.cssSelector("input[name='newsletter'][value='1']");
	private By subscribeNo = By.cssSelector("input[name='newsletter'][value='0']");
	private By privacyPolicy = By.cssSelector("input[name='agree'][type='checkbox']");
	private By submitBtn = By.cssSelector("input[value='Continue'][type='submit']");
	private By successMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean doRegistration(String fname, String lname, String email, String phone, String pwd, String subscribe) {
		eleUtil.waitForElementVisible(firstname, TimeUtil.DEFAULT_MEDIUM_TIME).sendKeys(fname);
		eleUtil.doSendKeys(this.lastname, lname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, phone);
		eleUtil.doSendKeys(this.password, pwd);
		eleUtil.doSendKeys(this.confirm, pwd);
		if (subscribe.equals("Yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(privacyPolicy);
		eleUtil.doClick(submitBtn);
		
		String actSuccessMsg = eleUtil.waitForElementVisible(successMsg, TimeUtil.DEFAULT_MEDIUM_TIME).getText();
		Log.info(actSuccessMsg);
		if (actSuccessMsg.equals(AppConstants.USER_REG_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
