package edu.framework.tests;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import edu.framework.factory.DriverFactory;
import edu.framework.factory.PageProvider;
import edu.framework.pageobject.HomePage;


@Listeners(edu.framework.listerners.TestListener.class)
public class ExtentReportsLoginTest extends BaseTest{
	
	WebDriver driver;
	HomePage homePage;
	
	@BeforeClass
	public void setUp() {
		driver = DriverFactory.getDriver();
	}
	
	@Test
	public void loginTest() {
		HomePage homePage = new PageProvider(driver).getHomePage();
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
