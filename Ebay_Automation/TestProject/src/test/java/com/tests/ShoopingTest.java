package com.tests;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pages.AdvancedSearchPage;
import com.pages.CartPage;
import com.pages.CustomerServicePage;
import com.pages.HomePage;
import com.pages.SearchPage;
import com.pages.SignInPage;

import resources.Base;
import resources.DataFile;
import resources.Excel;

import org.testng.annotations.BeforeClass;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ShoopingTest {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	SignInPage signInPage;
	CartPage cartPage;
	CustomerServicePage cSercvPage;
	AdvancedSearchPage advcancePage;

	String URL;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		Base.logger.info("start shhoping test");
		
		driver = Base.getBrowser(driver);
		URL = Base.propfile.getProperty("HOMEURL");
		driver = LoginAndLogOutTest.signInEveryTime(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		searchPage = new SearchPage(driver);

	}

	@Test(priority=0)
	public void checkGoodQuery() throws Exception {
		Base.logger.info("shooping check good query");
		
		for (int i = 1; i < Excel.SHOPInputList.size(); i += 2) {
			
		searchPage.enterSearchQuery(Excel.SHOPInputList.get(i));
		searchPage.clickSearch();

		Thread.sleep(10000);

		Assert.assertEquals(searchPage.getResultName(), Excel.SHOPInputList.get(i));
	    }
	}
	@Test(priority=1)
	public void checkWrongQuery() throws Exception {
		Base.logger.info("shooping check wrong query");
		
		for (int i = 0; i < Excel.CSInputList.size(); i += 2) {
			
		searchPage.enterSearchQuery(Excel.SHOPInputList.get(i));
		searchPage.clickSearch();

		Thread.sleep(10000);

		Assert.assertEquals(searchPage.getResultNumber(), "0");
	   }
	}
	@Test(priority=2)
	public void checkShopByCategory() throws Exception {
		Base.logger.info("shooping check shop by ");
		
		searchPage.clickToByCategory();
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.elementToBeClickable(
						By.cssSelector("table#gh-sbc td:nth-child(2) > ul:nth-child(2) > li:nth-child(4) > a")))
				.click();

		Thread.sleep(10000);

		Assert.assertEquals(searchPage.getResultName(), "pet supplies");

	}

	@Test(priority=3)
	public void checkAdvanceShpping() throws Exception {
		Base.logger.info("shooping check advance");
		
		searchPage.clickAdvance();
		advcancePage = new AdvancedSearchPage(driver);
		advcancePage.enterSearchQuery("pet supplies");
		advcancePage.click();

		Thread.sleep(10000);

		Assert.assertEquals(searchPage.getFilterSize(), 5);

	}

	@AfterClass
	public void afterClass() {
		Base.logger.info("shooping check finish");
		
		driver.close();

	}

}
