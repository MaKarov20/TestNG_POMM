package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

import io.qameta.allure.Step;

import com.qa.hubspot.base.BasePage;

public class ContactsPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	WebElement webelemen;
	
	By createContactButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");
	By createContactFormButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");
	
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String getContactsPageTitle(){
		
		elementUtil.waitForTitlePresent("Contacts");
		return elementUtil.doGetPageTitle();
	}
	
	@Step("Create new contact with {0}, {1}, {2}, {3}")
	public void createNewContact(String mail, String FN, String LN, String jobT) throws InterruptedException{
		
		elementUtil.waitForElementPresent(createContactButton);
		Thread.sleep(3000);
		WebElement element = driver.findElement(createContactButton);
    	element.click();
		//		elementUtil.doClick(createContactButton);
		
		elementUtil.waitForElementPresent(email);
		Thread.sleep(3000);
		WebElement element1 = elementUtil.getElement(email);
		element1.sendKeys(mail);		
 //  elementUtil.doSendKeys(email, mail);
		
		elementUtil.waitForElementPresent(firstName);
		Thread.sleep(3000);
		elementUtil.doSendKeys(firstName, FN);
		
		elementUtil.waitForElementPresent(lastName);
		Thread.sleep(3000);
		elementUtil.doSendKeys(lastName, LN);
		
		elementUtil.waitForElementPresent(jobTitle);
		elementUtil.doSendKeys(jobTitle, jobT);
		
	//	elementUtil.doClick(createContactFormButton);   // bu calismiyor 
		jsUtil.clickElementByJS(elementUtil.getElement(createContactFormButton));
	}
}
