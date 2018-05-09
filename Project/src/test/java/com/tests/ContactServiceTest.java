package com.tests;

import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import com.pages.CartPage;
import com.pages.ContactUsPage;
import com.pages.CustomerServicePage;
import com.pages.HomePage;
import com.pages.SearchPage;
import com.pages.SignInPage;

import resources.Base;
import resources.DataFile;
import resources.Excel;

import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTML;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ContactServiceTest {

	private static WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	SignInPage signInPage;
	CartPage cartPage;
	CustomerServicePage cSercvPage;
	ContactUsPage contactUsPage;


	String URL;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		Base.logger.info("Cservice test");
		
		driver = Base.getBrowser(driver);
		driver = LoginAndLogOutTest.signInEveryTime(driver);
		URL = Base.propfile.getProperty("CUSTOMERURL");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		cSercvPage = new CustomerServicePage(driver);
	}

	@Test(priority=0)
	public void checkGoodQuery() throws Exception {
		Base.logger.info("CS good query check");
		
		for (int i = 1; i < Excel.CSInputList.size(); i += 2) {
		cSercvPage.sendSearch(Excel.CSInputList.get(i));
		cSercvPage.clickSearch();
		Thread.sleep(10000);

		Assert.assertEquals(cSercvPage.getSearchHeader().substring(1, cSercvPage.getSearchHeader().length()-1),
				Excel.CSInputList.get(i));
		}
	}

	@Test(priority=1)
	public void checkWrongQuery() throws Exception {
		Base.logger.info("CS wrong query check");
		
		for (int i = 0; i < Excel.CSInputList.size(); i += 2) {
		cSercvPage.sendSearch(Excel.CSInputList.get(i));
		cSercvPage.clickSearch();
		Thread.sleep(10000);

		Assert.assertEquals(true, cSercvPage.getNotFound().contains("didn't"));
	  }
	}
	
	@Test(priority=2)
	public void checkSelectTopic() throws Exception {
		Base.logger.info("CS select topic check");
		
		cSercvPage.clickContact();
		contactUsPage = new ContactUsPage(driver);
		contactUsPage.click();
		Assert.assertEquals(true, contactUsPage.getProbResult().contains(contactUsPage.getHowTo()));
	}

	@Test(priority=3)
	public void checkMackingContant() throws Exception {
		Base.logger.info("CS macking contacct check");
		
		contactUsPage = new ContactUsPage(driver);
		contactUsPage.click();
		String how_to = contactUsPage.getHowTo();
		contactUsPage.clickToSendMail();
		
		ArrayList <String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1).toString());
		Assert.assertEquals(true, contactUsPage.getEmailHeader().contains("Email"));
		Assert.assertEquals(true, contactUsPage.getEmailSubject().contains(how_to));

	}

	@AfterClass
	public void afterClass() {
		Base.logger.info("CS test finish");
		
		ArrayList <String>tabs = new ArrayList<String> (driver.getWindowHandles());
		for(int i=0; i<tabs.size();i++){
			driver.switchTo().window(tabs.get(i)).close();
		}
	
	}
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver( WebDriver m_driver) {
		driver= m_driver;
	}
}
