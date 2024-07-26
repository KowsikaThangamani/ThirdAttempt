package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProducts = By.cssSelector("div.product-thumb");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting the number of products listed")
	public int getSearchProductCount() {
		return eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_MEDIUM_TIME).size();
	}
	
	@Step("Selecting the product : {0}")
	public ProductInfoPage selectProduct(String productName) {
		Log.info("Selecting the Product : " + productName);
		eleUtil.waitForElementVisible(By.linkText(productName), TimeUtil.DEFAULT_SHORT_TIME).click();
		return new ProductInfoPage(driver);
	}

}
