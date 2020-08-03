package com.techmojo.web.automation.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.techmojo.web.automation.base.TestBase;
import com.techmojo.web.automation.pages.HomePage;
import com.techmojo.web.automation.pages.LoginPage;

public class LoginPageTest extends TestBase {
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void init() {
		loginPage = new LoginPage();
		loginPage.invokeBrowser("chrome");
	}
	
	@Test
	public void doLogin() {
		String userName = prop.getProperty("username");
		String password = prop.getProperty("password");
		homePage = loginPage.doLogin(userName, password);
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
}