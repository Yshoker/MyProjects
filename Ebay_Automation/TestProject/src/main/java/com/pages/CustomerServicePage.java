package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerServicePage {

	public static Class<Object> java;

	WebDriver driver;

	@FindBy(css = "td#gh-title")
	WebElement header;

	@FindBy(css = "input#query")
	WebElement searchBox;

	@FindBy(css = "button#srhbtn > b")
	WebElement searchButton;

	@FindBy(xpath = "//*[@id=\"mainContent\"]/div/div[1]/h2/i")
	WebElement searchHeader;
	
	@FindBy(css = "div#ocsinfo span")
	WebElement notFoundSpan;
	
	
	public CustomerServicePage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}

	public String getHeader() {
		return header.getText();
	}

	public void sendSearch(String x) {
		searchBox.clear();
		searchBox.sendKeys(x);
	}

	public void clickSearch() {
		searchButton.click();
	}

	public String getSearchHeader() {
		return searchHeader.getText();
	}

	public void clickContact() {
		driver.findElement(By.cssSelector("input#w1-5")).click();
	}

	public String getNotFound() {
		return driver.findElement(By.cssSelector("div#ocsinfo span")).getText();
	}
	
	public String getContactPageHeader() {
		return driver.findElement(By.cssSelector("div.pagewidth > h2")).getText();
	}
}
