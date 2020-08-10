package com.techmojo.web.automation.base;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.pages.LoginPage;
import com.techmojo.web.automation.util.DateUtils;

public class PageBase {
	protected WebDriver driver;
	protected ExtentTest logger;

	public PageBase() {

	}

	public PageBase(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	/****************** Open URL ***********************/
	public LoginPage openURL(String websiteURLKey) {
		LoginPage loginPage = null;
		try {
			logger.log(Status.INFO, "Opening the WebSite");
			driver.get(websiteURLKey);
			reportPass(websiteURLKey + " Identified Successfully");
			loginPage = new LoginPage(driver, logger);
			PageFactory.initElements(driver, loginPage);
		} catch (Exception e) {
			System.out.println("openURL == " + e.getMessage());
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		return loginPage;
	}

	/****************** Get Page Title ***********************/
	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Select value From DropDown ***********************/
	public void selectDropDownValue(WebElement webElement, String value) {
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Verify Element is Present ***********************/
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Element is Displayed");
			} else {
				reportFail("Element is not appeared");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Get All Elements of DropDown ***********************/
	public List<WebElement> getAllElementsOfDropDown(WebElement webElement) {
		List<WebElement> allElements = null;
		try {
			Select select = new Select(webElement);
			allElements = select.getOptions();
			return allElements;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return allElements;
	}

	/****************** Enter Text ***********************/
	public void enterText(String xpathKey, String data) {
		try {
			getElement(xpathKey).sendKeys(data);
			reportPass(data + " - Entered successfully in locator Element : " + xpathKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Click Element ***********************/
	public void elementClick(String xpathKey) {
		try {
			getElement(xpathKey).click();
			reportPass(xpathKey + " : Element Clicked Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Identify Element ***********************/
	public WebElement getElement(String locatorKey) {
		WebElement element = null;
		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				element = driver.findElement(By.className("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name("prop.getProperty(locatorKey)"));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return element;
	}

	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");

		} catch (IOException e) {
			reportFail(e.getMessage());
		}
	}
}