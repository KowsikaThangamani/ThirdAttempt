package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class MyAccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By headers = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public MyAccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting Account Page Title")
	public String getAccPageTitle() {
		String apTitle = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		Log.info("Account Page Title is : " + apTitle);
		return apTitle;
	}
	
	@Step("Getting Account Page URL")
	public String getAccPageUrl() {
		String apUrl = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		Log.info("Account Page URL is : " + apUrl);
		return apUrl;
	}
	
	@Step("Verifying whether Logout Link is Displayed in the page.....")
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, TimeUtil.DEFAULT_MEDIUM_TIME).isDisplayed();
	}
	
	@Step("Verifying whether My Account Link is Displayed in the page.....")
	public boolean myAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink, TimeUtil.DEFAULT_MEDIUM_TIME).isDisplayed();
	}
	
	@Step("Getting the headers in the page")
	public List<String> getAccountPageHeaders() {
		List<WebElement> headerEle = eleUtil.waitForElementsVisible(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headers = new ArrayList<String>();
		for (WebElement e : headerEle) {
			String headerText = e.getText();
			headers.add(headerText);
		}
		return headers;
	}
	
	@Step("Searching for the product : {0}")
	public SearchResultsPage doSearch(String searchKey) {
		Log.info("Searching for the product : " + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}


}
