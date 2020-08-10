package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.techmojo.web.automation.base.PageBase;

public class LandingPage extends PageBase {

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/header/div/nav/div[2]/div/div[2]/ul/a")
	private WebElement signIn;

	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public LoginPage clickSignIn() {
		this.signIn.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
}