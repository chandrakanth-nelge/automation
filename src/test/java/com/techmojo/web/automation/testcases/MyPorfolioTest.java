package com.techmojo.web.automation.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.techmojo.web.automation.base.MoneyPage;
import com.techmojo.web.automation.base.MyPorfolioPage;
import com.techmojo.web.automation.base.PageBase;
import com.techmojo.web.automation.base.PortFolioLoginPage;
import com.techmojo.web.automation.base.TestBase;
import com.techmojo.web.automation.pages.LandingPage;

public class MyPorfolioTest extends TestBase {
	private LandingPage landingPage;
	private MoneyPage moneyPage;
	private PortFolioLoginPage portfolioLoginPage;
	private MyPorfolioPage myporfolioPage;
	
	@Test(dataProvider="getOpenPortfolioTestData", groups={"Regression", "LoginTest" })
	public void openPorfolio(Hashtable<String, String> testData){
		logger = report.createTest("Login Rediff Portfolio : " + testData.get("Comment"));
		
		invokeBrowser("chrome");
		PageBase pageBase = new PageBase(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.openURL("");
		moneyPage = landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		myporfolioPage = portfolioLoginPage.doLogin(testData.get("UserName"), testData.get("Password"));
		myporfolioPage.verifyMoneyBiz();
		myporfolioPage.getTitle(testData.get("PageTitle"));
	}
}
