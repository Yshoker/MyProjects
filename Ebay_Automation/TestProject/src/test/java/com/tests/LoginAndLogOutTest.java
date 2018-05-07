package com.tests;

import org.testng.log4testng.Logger;

import com.pages.CartPage;
import com.pages.CustomerServicePage;
import com.pages.HomePage;
import com.pages.SearchPage;
import com.pages.SignInPage;

import resources.Excel;
import resources.Base;
import resources.DataFile;

import org.testng.annotations.*;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTML;

import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginAndLogOutTest {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	SignInPage signInPage;
	CartPage cartPage;
	CustomerServicePage cSercvPage;

	String URL;
	String MyUsername;
	String MyPassword;

	@BeforeClass
	public void beforeTest() throws InterruptedException {
		Base.logger.info("login and logut check");
		driver = Base.getBrowser(driver);
		URL = Base.propfile.getProperty("SIGNINURL").toString();
		MyUsername = Base.propfile.getProperty("username");
		MyPassword = Base.propfile.getProperty("password");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		signInPage = new SignInPage(driver);

	}

	@Test(priority=0)
	public void tryToLogin() throws Exception {
		Base.logger.info("try to login check ");
		signInPage.putMail(MyUsername);
		signInPage.putPass(MyPassword);
		signInPage.clickSend();
		Thread.sleep(5000);
		Assert.assertEquals(Base.propfile.getProperty("HOMEURL"), driver.getCurrentUrl());
	}

	@Test(priority=1)
	public void tryToSignOut() throws Exception {
		Base.logger.info("try to logout check ");
		homePage = new HomePage(driver);
		homePage.moveOnDiv();
		homePage.clickSignOut();
		Thread.sleep(10000);
		Assert.assertEquals(true, homePage.getOutHeader().contains("out"));
	}

	@Test(priority=2)
	public void tryToLoginError() throws Exception {
		Base.logger.info("try to wrong login check ");
		
		for (int i = 0; i < Excel.LOGOUTInputList.size(); i += 2) {
			driver.get(URL);
			signInPage.putMail(Excel.LOGOUTInputList.get(i));
			signInPage.putPass(Excel.LOGOUTInputList.get(++i));
			signInPage.clickSend();
			Thread.sleep(5000);
			Assert.assertNotEquals(Base.propfile.getProperty("HOMEURL"), driver.getCurrentUrl());
		}
	
	}

	@AfterClass
	public void afterClass() {
		Base.logger.info("login & logout finish");
		driver.close();

	}

	static WebDriver signInEveryTime(WebDriver driver) throws InterruptedException {

		Properties propfile = DataFile.propretiesfile();
		driver.get(propfile.getProperty("SIGNINURL"));

		SignInPage signInPage = new SignInPage(driver);
		signInPage.putMail(propfile.getProperty("username"));
		signInPage.putPass(propfile.getProperty("password"));
		signInPage.clickSend();
		Thread.sleep(5000);
		return driver;

	}

}
