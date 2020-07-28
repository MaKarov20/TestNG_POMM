package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByClassName;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//Locators
	By header = By.cssSelector("h1.dashboard-selector__title");
	By accountName = By.cssSelector("span.account-name ");
	
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	
	//constructor
	public HomePage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle(){
		elementUtil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
//	public String getHomePageHeader(){
//		return elementUtil.doGetText(header);
//	}
	public String getHomePageHeader(){
		String element = driver.findElement(header).getText();
		return element;
	}
	
//	public String getLoggedInUserAccountName(){
//		return elementUtil.doGetText(accountName);
//	}
	public String getLoggedInUserAccountName(){
		elementUtil.waitForElementPresent(accountName);
		String element = driver.findElement(accountName).getText();
		return element;
	}
	
	public void ClickOnConctacts(){
		elementUtil.waitForElementPresent(mainContactsLink);
	//	elementUtil.doClick(mainContactsLink);
		WebElement element = driver.findElement(mainContactsLink);
		element.click();
		
		elementUtil.waitForElementPresent(childContactsLink);
		//elementUtil.doClick(childContactsLink);
		WebElement element1 = driver.findElement(childContactsLink);
		element1.click();
	}
	
	public ContactsPage goToContactsPage(){
		ClickOnConctacts();
		return new ContactsPage(driver);
	}

}
