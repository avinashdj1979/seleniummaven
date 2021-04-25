package edu.framework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import edu.framework.factory.DriverFactory;
import edu.framework.factory.PageProvider;
import edu.framework.listerners.TestListenerExtent;
import edu.framework.pageobject.HomePage;
import edu.framework.pageobject.LoginPage;

//@Slf4j
@Listeners(TestListenerExtent.class)
public class LogInTest extends BaseTest{
	
	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	PageProvider pageProvider;
	
	public static final Logger logger =  LogManager.getLogger(LogInTest.class);
	
	@BeforeClass
	public void setUp(ITestContext context) {
		driver = DriverFactory.getDriver();
		context.setAttribute("driver", driver);
	}
	
	@Test
	public void loginTest() {
		pageProvider = new PageProvider(driver);
		
		homePage = pageProvider.getHomePage();
		homePage.clickSignIn();
		
		loginPage = pageProvider.getLoginPage();
		loginPage.login();
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
