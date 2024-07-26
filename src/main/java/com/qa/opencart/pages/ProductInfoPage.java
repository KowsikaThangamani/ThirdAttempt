package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap = new HashMap<>();
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String productHeader() {
		String header = eleUtil.waitForElementVisible(productHeader, TimeUtil.DEFAULT_SHORT_TIME).getText();
		Log.info("Selected product name is : " + header);
		return header;
	}
	
	public int getProductImages() {
		return eleUtil.waitForElementsVisible(images, TimeUtil.DEFAULT_SHORT_TIME).size();
	}
	
	private void getProductMetaData() {
		List<WebElement> metaData = eleUtil.waitForElementsVisible(productMetaData, TimeUtil.DEFAULT_SHORT_TIME);
		for(WebElement e : metaData) {
			String text = e.getText();
			String metaKey = text.split(":")[0].trim();
			String metaValue = text.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPricingData() {
		List<WebElement> pricingData = eleUtil.waitForElementsVisible(productPriceData, TimeUtil.DEFAULT_SHORT_TIME);
		String price = pricingData.get(0).getText();
		String exTax = pricingData.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", price);
		productMap.put("exTaxPrice", exTax);
	}
	
	public Map<String, String> getProductDetailsMap() {
		productMap.put("header", productHeader());
		productMap.put("images", String.valueOf(getProductImages()));
		getProductMetaData();
		getProductPricingData();
		Log.info("Product Details : " + productMap);
		return productMap;
		
	}

}
