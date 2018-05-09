package com.tests;

import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import com.pages.CartPage;
import com.pages.CustomerServicePage;
import com.pages.HomePage;
import com.pages.SearchPage;
import com.pages.SignInPage;

import resources.Base;
import resources.DataFile;
import resources.MyExtentReport;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CartInfoTest {

	private static WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	SignInPage signInPage;
	CartPage cartPage;
	CustomerServicePage cSercvPage;

	String URL;

	@BeforeSuite
	public void beforeSuite() {
		Base.beforeClass();

	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {

		Base.logger.info("cart test");
		driver = Base.getBrowser(driver);
		URL = Base.propfile.getProperty("CARTURL");
		driver = LoginAndLogOutTest.signInEveryTime(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
	}

	@Test(priority = 0)
	public void addItemToCart() throws InterruptedException {
		Base.logger.info("add item to cart chcek ");

		cartPage = new CartPage(driver);
		String items_num = cartPage.getItemsNumber();
		searchPage = new SearchPage(driver);
		searchPage.enterSearchQuery("car accesoosris");
		searchPage.clickSearch();

		Thread.sleep(30000);

		List<WebElement> ResList = searchPage.getResultList();
		ResList.get(0).findElement(By.tagName("a")).click();
		driver.findElement(By.cssSelector("a#isCartBtn_btn")).click();

		Thread.sleep(10000);

		Assert.assertNotEquals(items_num, cartPage.getItemsNumber());

	}

	@Test
	public void removeItemFromCart() throws InterruptedException {

		cartPage = new CartPage(driver);
		String items_num = cartPage.getItemsNumber();
		try {
			Thread.sleep(10000);
			List<WebElement> temp = cartPage.getItemsList();// <a> list
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getText().equals("Remove")) {
					driver.get(temp.get(i).getAttribute("href"));// get first item remove link
					Thread.sleep(5000);
					break;

				}
			}

		} catch (StaleElementReferenceException x) {
			driver.navigate().refresh();
			Thread.sleep(10000);
			removeItemFromCart();
		}

		Thread.sleep(10000);

		System.out.println("dfdfdsfsd " + items_num);

		Assert.assertNotEquals(items_num, cartPage.getItemsNumber());

	}

	@Test(priority = 1)
	public void numberOfItems() throws InterruptedException {
		Base.logger.info("number of items cart chcek");

		cartPage = new CartPage(driver);
		Thread.sleep(5000);
		String items_num = cartPage.getItemsNumber();
		Assert.assertEquals(Integer.parseInt(items_num), cartPage.cartListItemsNumber());

	}

	@AfterClass
	public void afterClass() {
		Base.logger.info("cart test finfih");

		driver.close();

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver m_driver) {
		driver = m_driver;
	}

}
