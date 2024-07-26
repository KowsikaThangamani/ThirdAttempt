package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private EdgeOptions eo;
	private FirefoxOptions fo;
	
	public OptionsManager (Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote").trim())) {
			co.setCapability("browserName", "chrome");
			//co.setBrowserVersion(prop.getProperty("browserVersion").trim());
			//Map<String, Object> selenoidOptions = new HashMap<>();
			//selenoidOptions.put("screenResolution", "1280x1024x24");
			//selenoidOptions.put("enableVNC", true);
			//selenoidOptions.put("testName", prop.getProperty("testName"));
			//co.setCapability("selenoid:options", selenoidOptions);
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running chrome in HEADLESS mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running chrome in INCOGNITO mode");
			co.addArguments("--incognito");
		}	
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote").trim())) {
			fo.setCapability("browserName", "firefox");
			//fo.setBrowserVersion(prop.getProperty("browserVersion"));
			//Map<String, Object> selenoidOptions = new HashMap<>();
			//selenoidOptions.put("screenResolution", "1280x1024x24");
			//selenoidOptions.put("enableVNC", true);
			//selenoidOptions.put("testName", prop.getProperty("testName"));
			//fo.setCapability("selenoid:options", selenoidOptions);
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running firefox in HEADLESS mode");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running firefox in INCOGNITO mode");
			fo.addArguments("--incognito");
		}	
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote").trim())) {
			eo.setCapability("browserName", "edge");
			eo.setCapability("platform", Platform.LINUX);
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running edge in HEADLESS mode");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running edge in INCOGNITO mode");
			eo.addArguments("--inprivate");
		}	
		return eo;
	}
}
