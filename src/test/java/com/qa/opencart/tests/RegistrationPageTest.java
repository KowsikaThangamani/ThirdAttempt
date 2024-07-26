package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginPage.navigateToRegistrationPage();
	}
	
	@DataProvider
	public Object[][] getRegistrationDataFromExcel() {
		return ExcelUtil.getTestData("register");
	}
	
	@Test (dataProvider = "getRegistrationDataFromExcel")
	public void registrationTest(String fname, String lname, String phone, String pwd, String subscribe) {
		Assert.assertTrue(registrationPage.doRegistration(fname, lname, StringUtil.generateRandomEMail(), phone, pwd, subscribe));
	}

}
