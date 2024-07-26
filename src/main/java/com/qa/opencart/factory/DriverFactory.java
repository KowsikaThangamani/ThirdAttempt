package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver (Properties prop) {
		
		String browserName = prop.getProperty("browser");
		Log.info("Launching the application in : " + browserName.toUpperCase() + " browser");
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome" :
			if (Boolean.parseBoolean(prop.getProperty("remote").trim())) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox" :
			if (Boolean.parseBoolean(prop.getProperty("remote").trim())) {
				init_remoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge" :
			if (Boolean.parseBoolean(prop.getProperty("remote").trim())) {
				init_remoteDriver("edge");
			} else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		case "safari" :
			tlDriver.set(new SafariDriver());
			break;
		default :
			Log.warn("Please pass the right browser name");
			throw new BrowserException ("BROWSER NOT FOUND");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}

	public void init_remoteDriver (String browserName) {
		Log.info("Launching the browser in remote grid");
		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome" :
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl").trim()), optionsManager.getChromeOptions()));
				break;
			case "firefox" :
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl").trim()), optionsManager.getFirefoxOptions()));
				break;
			case "edge" :
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl").trim()), optionsManager.getEdgeOptions()));
				break;
			default :
				Log.warn("Please pass the browser which is supported by selenium grid");
				throw new BrowserException("BROWSER NOT FOUND");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		
		prop = new Properties();
		FileInputStream fip = null;
		
		String envName = System.getProperty("env");
		Log.info("Environment name is : " + envName);
		
		try {
			if (envName == null) {
				Log.info("Environment is null, Hence running in QA Environment");
				fip = new FileInputStream("./src/test/resources/config/config_qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa" :
					fip = new FileInputStream("./src/test/resources/config/config_qa.properties");
					break;
				case "dev" :
					fip = new FileInputStream("./src/test/resources/config/config_dev.properties");
					break;
				case "uat" :
					fip = new FileInputStream("./src/test/resources/config/config_uat.properties");
					break;
				case "prod" :
					fip = new FileInputStream("./src/test/resources/config/config_prod.properties");
					break;
				case "stage" :
					fip = new FileInputStream("./src/test/resources/config/config_stage.properties");
					break;
				default :
					Log.warn("Please pass the right environment name");
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(fip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
		
		File destFile = new File(path);
		
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
		
	}
}
