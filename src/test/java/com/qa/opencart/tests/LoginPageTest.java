package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design open cart login page")
@Story("US 101: Design login page features for open cart application")
@Feature("Feature 201: Adding login features")

public class LoginPageTest extends BaseTest {
	
	@Description("Checking login page title....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String accPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(accPageTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Description("Checking login page Url....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String accPageUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(accPageUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}
	
	@Description("Checking Forgot Password Link is displayed....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPasswordLinkExist() {
		Assert.assertTrue(loginPage.isForgottenPasswordLinkExist());
	}
	
	@DataProvider
	public Object[][] getLoginCredentials() {
		return new Object[][] {
			{"dec2023@opencart.con", "Selenium@12345"}
		};
	}
	
	@Description("Checking whether is able to login....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4, dataProvider = "getLoginCredentials")
	public void loginTest(String username, String pwd) {
		accPage = loginPage.doLogin(username, pwd);
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	

}
