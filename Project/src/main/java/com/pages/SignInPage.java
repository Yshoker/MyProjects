package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.*;

public class SignInPage {

	@FindBy(css = "input#userid")
	WebElement mail;

	@FindBy(css = "input#pass")
	WebElement pass;

	@FindBy(css = "input#sgnBt")
	WebElement button;

	@FindBy(css = "div#sgnTab > span.ml53.tx")
	WebElement header;

	WebDriver driver;

	public SignInPage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}
	
	public String getErrorMesage() {
		return driver.findElement(By.cssSelector("span#errf")).getText();
	}

	public void putMail(String x) {
		mail.clear();
		mail.sendKeys(x);
	}

	public void putPass(String x) {
		pass.clear();
		pass.sendKeys(x);
	}

	public void clickSend() {
		button.click();
	}

	public String getHeader() {
		return header.getText();
	}

}
