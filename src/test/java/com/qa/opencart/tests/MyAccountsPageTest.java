package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.logger.Log;

public class MyAccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void accPageTitleTest() {
		String accPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(accPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test (priority = 2)
	public void accPageTitleUrl() {
		String accPageUrl = accPage.getAccPageUrl();
		Assert.assertTrue(accPageUrl.contains(AppConstants.ACC_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}
	
	@Test(priority = 3)
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test(priority = 4)
	public void isMyAccountLinkExist() {
		Assert.assertTrue(accPage.myAccountLinkExist());
	}
	
	@Test(priority = 5)
	public void accPageHeadersTest() {
		List<String> headers = accPage.getAccountPageHeaders();
		Log.info("Headers list is : " + headers);
	}
	
	@DataProvider
	public Object[][] getSearchProducts() {
		return new Object[][] {
			{"mac"},
			{"samsung"},
			{"nokia"}
		};
	}
	
	@Test(dataProvider = "getSearchProducts", priority = 6)
	public void searchTest(String searchKey) {
		accPage.doSearch(searchKey);
	}

}
