package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.techmojo.web.automation.base.PageBase;

public class LoginPage extends PageBase {
	@FindBy(name = "username")
	private WebElement userName;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "submit")
	private WebElement loginBtn;
	
	@FindBy(name = "signUp")
	private WebElement signUpBtn;

	public LoginPage() {
		PageFactory.initElements(driver, LoginPage.class);
	}
	
	public HomePage doLogin(String userName, String password) {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.loginBtn.click();
		
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	
	
}