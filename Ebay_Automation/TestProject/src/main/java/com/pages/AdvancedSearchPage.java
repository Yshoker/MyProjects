package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class AdvancedSearchPage {

	@FindBy(css = "input#_nkw")
	WebElement searchTextBox;

	@FindBy(xpath = "//*[@id=\"LH_TitleDesc\"]")
	WebElement checkBox;

	@FindBy(xpath = "//*[@id=\"adv_search_from\"]/fieldset[3]/input[2]")
	WebElement fromPriceText;

	@FindBy(xpath = "//*[@id=\"LH_BIN\"]")
	WebElement buyItCheckBox;

	@FindBy(xpath = "//*[@id=\"LH_ItemConditionNew\"]")
	WebElement newConditionCheckBox;

	@FindBy(xpath = "//*[@id=\"LH_FS\"]")
	WebElement freeSheppingCheck;
	
	@FindBy(xpath = "//*[@id=\"searchBtnLowerLnk\"]")
	WebElement sendButton;
	
	WebDriver driver;

	public AdvancedSearchPage(WebDriver driver) {

		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 50);
		// This initElements method will create all WebElements
		PageFactory.initElements(factory, this);

	}

	public void enterSearchQuery(String q) {
		searchTextBox.clear();
		searchTextBox.sendKeys(q);
	}

	public void click() {
		checkBox.click();
		fromPriceText.sendKeys("50");
		buyItCheckBox.click();
		newConditionCheckBox.click();
		freeSheppingCheck.click();
		sendButton.click();
	}


}


