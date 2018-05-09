package com.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CartPage{
	
	

	//Header
	@FindBy(css = "div#PageTitle > h1")
	WebElement header;
	
	//Proceed to Order
	@FindBy(css = "a#ptcBtnBottom")
	WebElement proceedToOrder;

	// Proceed to Order
	@FindBy(css = "a#contShoppingBtn")
	WebElement proceedToBuy;

	// items Number
	@FindBy(css = "	div#asynccartsummary tr:nth-child(1) > td:nth-child(1) > div")
	WebElement numberOfItems;
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}
	
	
	public void clickProccedOrder()
	{
		proceedToOrder.click();
	}
	
	public void clickProccedBuy()
	{
		proceedToBuy.click();
	}
	
	public int cartListItemsNumber()
	{
		List<WebElement> forms = driver.findElement(By.cssSelector("div#ShopCart > div"))
				.findElements(By.xpath("./div"));
		return forms.size();
		
	}

	public List<WebElement> getItemsList()
	{
		return driver.findElement(By.cssSelector("div#ShopCart > div"))
				.findElements(By.tagName("a"));
		
	}

	
	public String getItemsNumber() {
		String str = numberOfItems.getText();

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				builder.append(c);
			}
		}
		return builder.toString();

	}
	
	
	

}
