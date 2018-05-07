package com.tests;



import com.google.common.base.Predicate;
import com.pages.CartPage;
import com.pages.CustomerServicePage;
import com.pages.HomePage;
import com.pages.SearchPage;
import com.pages.SignInPage;

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

import javax.annotation.Nullable;
import javax.swing.text.html.HTML;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;
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

public class CheckNavberTest {

	WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	SignInPage signInPage;
	CartPage cartPage;
	CustomerServicePage cSercvPage;
	
	String URL;

	@BeforeClass
	public void beforeTest() throws InterruptedException {
		Base.logger.info("check nav test");
		
		driver = Base.getBrowser(driver);
		URL = Base.propfile.getProperty("HOMEURL").toString();
		driver = LoginAndLogOutTest.signInEveryTime(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		homePage = new HomePage(driver);
	}

	@Test
	public void checkNavbar() throws Exception {
		Base.logger.info("checing navbar");
		
		List<String> temp = homePage.getNavber();
		for (int i = 1; i < temp.size() - 1; i++) {
			driver.get(temp.get(i));
			Thread.sleep(1000);
			Assert.assertEquals(true, driver.getTitle() != URL);
		}

	}

	@AfterClass
	public void afterClass() {
		Base.logger.info("navbar test finish");
		
		driver.close();

	}

}
