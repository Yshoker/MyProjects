package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {


	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"1\"]/a")
	WebElement buyingButton;
	
	@FindBy(xpath = "//*[@id=\"11\"]")
	WebElement basicsButton;

	@FindBy(xpath = "//*[@id=\"111\"]")
	WebElement howToBuyButton;
	
	@FindBy(css = "div#titleDiv > h3")
	WebElement resultHeader;
	
	public ContactUsPage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}

	public String getEmailHeader() {
		return driver.findElement(By.cssSelector("div.pgtl > h1")).getText();
	}
	public String getEmailSubject() {
		return driver.findElement(By.cssSelector("div#mainContent p:nth-child(3) > span.fld")).getText();
	}

	
	
	public String getHowTo() {
		return howToBuyButton.getText();
	}

	public void click() throws InterruptedException {
		buyingButton.click();
		basicsButton.click();
		howToBuyButton.click();
		Thread.sleep(6000);
	}

	public String getProbResult() {
		return resultHeader.getText();
	}

	public void clickToSendMail() {
		driver.findElement(By.cssSelector("button#w1-11")).click();
	}
	

}
