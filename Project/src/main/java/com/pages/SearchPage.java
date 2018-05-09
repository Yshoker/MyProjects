package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class SearchPage {

	// Search Text Box
	@FindBy(xpath = "//*[@id=\"gh-ac\"]")
	WebElement searchTextBox;

	// Search button
	@FindBy(xpath = "//*[@id='gh-btn']")
	WebElement searchButton;

	// shop by button
	@FindBy(xpath = "//*[@id=\"gh-shop-a\"]")
	WebElement shopByButton;
	
	// 
	@FindBy(css = "#smuys")
	WebElement filterSpan;
	
	// advane button
	@FindBy(css = "a#gh-as-a")
	WebElement advance;
		
	
	WebDriver driver;

	public SearchPage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}

	public void enterSearchQuery(String q) {
		searchTextBox.clear();
		searchTextBox.sendKeys(q);
	}

	public void clickAdvance() {
		advance.click();
	}
	
	public void clickSearch() {
		searchButton.click();
	}

	public String getResultName() {
		return driver.findElement(By.cssSelector("	div#cbelm span.kwcat > b")).getText();
	}
	
	public String getResultNumber() {
		return driver.findElement(By.cssSelector("div#cbelm span.rcnt")).getText();
	}

	public void clickToByCategory() {
		shopByButton.click();
	}
	
	public int getFilterSize() {
		return filterSpan.findElements((By.xpath("./span"))).size();
			
	}

	public List<WebElement> getResultList() {
		return driver
			    .findElement(By.cssSelector("#ListViewInner"))
			    .findElements(By.tagName("li"));
	}
	

}


