package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void searchResultsPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}
		};
	}
	
	@Test (dataProvider = "getProductCountData", priority = 1)
	public void searchResultsCountTest(String searchKey, int productCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), productCount);
	}
	
	@Test (priority = 2)
	public void selectProductTest() {
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), 2);
	}

}
