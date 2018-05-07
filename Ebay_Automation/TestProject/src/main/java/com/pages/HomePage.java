package com.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	// User name
	@FindBy(xpath = "//*[@id=\"gh-ug\"]/b[1]")
	WebElement userName;

	// SIGN IN Button
	@FindBy(css = "span#gh-ug > a")
	WebElement signInButton;

	// sign Out Button
	@FindBy(css = "li#gh-uo > a")
	WebElement signOutButton;

	// logo
	@FindBy(css = "img#gh-logo")
	WebElement logoButton;

	// button of hide div
	@FindBy(css = "button#gh-ug")
	WebElement hideenDiv;
	
	@FindBy(xpath = "//*[@id=\"navigationFragment\"]/div/table/tbody/tr")
	WebElement navbar;
	
	
	
		
	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}

	public void clickSignInBtn() {
		signInButton.click();
	}

	public String getOutHeader() {
		return driver.findElement(By.cssSelector("div#AreaTitle span")).getText();
	}
	
	
	public void clickSignOut() {
		signOutButton.click();
	}

	public String getUserName() {
		return userName.getText();
	}

	public void moveOnDiv() {
		new Actions(driver).moveToElement(hideenDiv).perform();
	}

	public List <String> getNavber() {
		List <WebElement> temp = navbar.findElements(By.xpath("./td/a"));
		List<String> str_list = new LinkedList<String>() ;  
		for (WebElement string : temp) {
			str_list.add(string.getAttribute("href"));
		}
		
		return str_list;
	}

	public String getNavabrResult() {
		return driver.findElement(By.cssSelector("h1#mainTitle")).getText();
	}

		
	
	
}
