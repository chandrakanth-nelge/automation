package com.techmojo.web.automation.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.techmojo.web.automation.base.PageBase;
import com.techmojo.web.automation.base.TestBase;
import com.techmojo.web.automation.pages.HomePage;
import com.techmojo.web.automation.pages.LandingPage;
import com.techmojo.web.automation.pages.LoginPage;

public class LoginPageTest extends TestBase {
	private HomePage homePage;

	@BeforeMethod
	public void init() {

	}

	@Test()
	public void doLogin() {
		logger = report.createTest("Login Rediff Portfolio : " + "Comment");

		invokeBrowser("chrome");

		PageBase pageBase = new PageBase(driver, logger);

		LoginPage loginPage = pageBase.openURL("https://www.facebook.com/");
		String userName = "chandrakanth.nelge@gmail.com"; //prop.getProperty("username");
		String password = "nelge"; //prop.getProperty("password");
		homePage = loginPage.doLogin(userName, password);

		homePage.getTitle("");
	}

	@AfterMethod
	public void close() {
		//driver.quit();
	}
}