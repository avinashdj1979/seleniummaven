package edu.framework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import edu.framework.factory.DriverFactory;
import edu.framework.factory.PageProvider;
import edu.framework.listerners.TestListenerExtent;
import edu.framework.pageobject.HomePage;
import edu.framework.reports.ExtentTestManager;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Listeners(TestListenerExtent.class)
public class LogInTest extends BaseTest{
	
	WebDriver driver;
	HomePage homePage;
	public static final Logger logger =  LogManager.getLogger(LogInTest.class);
	
	@BeforeClass
	public void setUp() {
		driver = DriverFactory.getDriver();
	}
	
	@Test
	public void loginTest() {
		HomePage homePage = new PageProvider(driver).getHomePage();
		ExtentTestManager.getTest().log(Status.INFO, "Sign In Button Clicked");
		logger.info("Sign In Button Clicked");
		homePage.clickSignIn();
		waitForSeconds(5);
	}
	
	@Test
	public void skipTest() {
			throw new SkipException("Test skipped");
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
