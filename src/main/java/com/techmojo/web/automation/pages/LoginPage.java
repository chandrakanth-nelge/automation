package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.techmojo.web.automation.base.PageBase;

public class LoginPage extends PageBase {
	@FindBy(id="email")
	private WebElement userName;

	@FindBy(id="pass")
	private WebElement password;

	@FindBy(xpath="//*[@id=\"u_0_b\"]")
	private WebElement loginButton;

	@FindBy(name = "signUp")
	private WebElement signUpBtn;

	public LoginPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, LoginPage.class);
	}

	public HomePage doLogin(String userName, String password) {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		
		System.out.println("here1");
		this.loginButton.click();
		System.out.println("here1");

		return PageFactory.initElements(driver, HomePage.class);
	}
}