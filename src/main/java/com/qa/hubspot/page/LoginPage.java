package com.qa.hubspot.page;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage{
	WebDriver driver;
	ElementUtil elementUtil;
	Properties prop;
	
	By emailId = By.className("login-email");
//	By emailId = By.id("username1");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By loginErrorText = By.cssSelector("div.private-alert__inner");
	
	//Constructors
	public LoginPage(WebDriver driver){
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//page actions
	//first action
	public String getPageTitle(){
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	//second action
	public boolean checkSignUpLink(){
	//	elementUtil.waitForElementVisible(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}
	//third action
	public HomePage doLogin(Credentials userCred){  //credentials classtan parametre yazarak cekio istedigi seyleri
		elementUtil.waitForElementPresent(emailId);  //element problem cikariyorsa belet bu methodla
        elementUtil.waitForElementPresent(password);
        elementUtil.waitForElementPresent(loginButton);
        
      
//		driver.findElement(emailId).sendKeys(userCred.getAppUsername());
//		driver.findElement(password).sendKeys(userCred.getAppPassword());
//		driver.findElement(loginButton).click();
	
        
		elementUtil.doSendKeys(emailId, userCred.getAppUsername() );
		elementUtil.doSendKeys(password,userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);   //bu login nden homepage e gecis yapio bu metodu yazdiginda bi onceki methodun
	}                                  //return type i da deisti kendimiz yazmadik onu
	
	public boolean checkLoginErrorMessage(){
		return elementUtil.doIsDisplayed(loginErrorText);
	}
}
