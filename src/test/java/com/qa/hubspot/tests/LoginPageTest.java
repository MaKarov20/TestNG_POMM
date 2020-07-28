package com.qa.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic - 101 : create login features")
@Feature("US - 501 : Create test for login on HubSpot")

public class LoginPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	@BeforeTest(alwaysRun= true)
	@Parameters(value= {"browser"})
	
	public void setUp(String browser1) {
		String browsername = null;
		basePage = new BasePage();
		prop = basePage.init_properites();
		
		if(browser1.equals(null)) {
			browsername = prop.getProperty("browser");
		}else {
			browsername = browser1;
		}
		
		browsername = prop.getProperty("browser");
		driver = basePage.init_driver(browsername);
		driver.get(prop.getProperty("url"));
		log.info("url is launched "+ prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1, groups="sanity",description = "get page title from hubspot login", enabled=true)
	@Description ("Verify signup link")
	@Severity (SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() throws InterruptedException{
		log.info("starting ------------>>>>>>> verifyLoginPageTest");
		//Thread.sleep(6000);
		String title =loginPage.getPageTitle();
		System.out.println("Login page title is "+title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		
		log.info("ending ------------>>>>>>> verifyLoginPageTest");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	@Test(priority = 2, enabled=true)
	@Description ("Verify signup link")
	@Severity (SeverityLevel.NORMAL)
	public void verifySignUpLinkTest(){
	Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3, description = "invalid username and password for the login page",enabled=true)
	@Description ("Verify Login Page credentials")
	@Severity (SeverityLevel.CRITICAL)
	public void loginTest(){
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("logged in account name: "+ accountName);
		//Assert.assertEquals(acc, prop.getProperty("null"));
		
	}
	
	@DataProvider
	public Object [][] getLoginInvalidData(){
		
		Object data [][] = {{"arap@yandex.com","test12345"},
				{"jimmy@gmail.com", " "},
				{" ", "test12345"},
				{"yummy", "yummy"},
				{" ", " "}};
	return data;	
	}
	
	@Test(priority=4, dataProvider="getLoginInvalidData", enabled= false)
	public void login_invalidTestCase(String username, String pwd){
		
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertFalse(loginPage.checkLoginErrorMessage());
		
	}
				
	
	
	
	@AfterTest (alwaysRun = true)
	public void tearDown(){
		driver.quit();
	}

}
