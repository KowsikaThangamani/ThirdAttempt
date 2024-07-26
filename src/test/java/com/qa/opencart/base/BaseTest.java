package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected RegistrationPage registrationPage;
	protected MyAccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	
	@Step("Launching the browser : {0} and initializing properties")
	@Parameters({"browser", "browserVersion", "testName"})
	@BeforeTest
	public void setup(String browserName, String browserVersion, String testName) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserVersion", browserVersion);
			prop.setProperty("testName", testName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		accPage = new MyAccountsPage(driver);
		searchResultsPage = new SearchResultsPage(driver);
		productInfoPage = new ProductInfoPage(driver);
		registrationPage = new RegistrationPage(driver);
		softAssert = new SoftAssert();
	}
	
	@Step("Closing Browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
