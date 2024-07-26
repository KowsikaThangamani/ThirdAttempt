package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"},

		};
	}
	
	@Test (dataProvider = "getProductSearchData", priority = 1)
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.productHeader(), productName);
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
		};
	}
	
	@DataProvider
	public Object[][] getProductImagesDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	@Test(dataProvider = "getProductImagesDataFromExcel", priority = 2)
	public void productImagesCountTest(String searchKey, String productName, String imagesCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImages(), Integer.parseInt(imagesCount));
	}
	
	@Test (priority = 3)
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productActDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productActDetailsMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productActDetailsMap.get("exTaxPrice"), "$2,000.00");
		softAssert.assertAll();
	}

}
