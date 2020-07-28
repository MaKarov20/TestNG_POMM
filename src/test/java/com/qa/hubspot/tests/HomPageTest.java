package com.qa.hubspot.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;  //nie tanimladi bunu cunku burdan baslicak
	HomePage homePage;
	Credentials userCred;
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		basePage = new BasePage();     //driveri init yapmakmicin bu sayfa lazim
		prop = basePage.init_properites();   //browser i baslatmak icin bu sayfa lazim chrome orda yazio
		String browserName1 = prop.getProperty("browser");  //prop dan browser i aldi string in icin e godu
		driver = basePage.init_driver(browserName1);        //basepageden cagirio methodu
		driver.get(prop.getProperty("url"));                //proptan alio url i
		loginPage = new LoginPage(driver);           //dologin metodunu almak icin
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		 
		Thread.sleep(5000);   //dologini cagirdi sonra icine proptan username password yazdi
	}
	@Test(priority=1, description="homepage title",enabled = true)
	public void verifyHomePageTitleTest(){
	String title = homePage.getHomePageTitle();
	System.out.println(title);
	Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	@Test(priority = 2, description="Account Setup | HubSpot", enabled = true)
	public void verifyHomePageHeaderTest(){
		String header = homePage.getHomePageHeader();
		System.out.println(header);
		Assert.assertEquals(header, "Sales");
	}
	@Test(priority=3, description="Account name is ...", enabled = true)
	public void verifyLoggedInUserTest(){
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println(accountName);
		Assert.assertEquals(accountName, "");
		
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
