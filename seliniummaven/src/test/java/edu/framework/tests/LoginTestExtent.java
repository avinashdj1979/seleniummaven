package edu.framework.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import edu.framework.factory.DriverFactory;
import edu.framework.listerners.TestListenerExtent;
import edu.framework.reports.ExtentTestManager;
import lombok.extern.slf4j.Slf4j;

@Listeners(TestListenerExtent.class)
@Slf4j
public class LoginTestExtent {
	
	WebDriver driver;
	//public static final Logger logger = LogManager.getLogger(LoginTest2.class);
	
	@BeforeClass
	public void setUp(ITestContext context) {
		driver = DriverFactory.getDriver();
		context.setAttribute("driver", driver);
	}

	@Test
	public void successTest() throws InterruptedException {
		ExtentTestManager.getTest().log(Status.INFO, "Success test");
		log.trace("successTest");
	}
	
	//@Test
	public void failureTest() throws InterruptedException {
		ExtentTestManager.getTest().log(Status.WARNING, "Failure test");
		log.debug("failure test");
		AssertJUnit.fail();
	}
	
	//@Test
	public void SkippedTest() throws InterruptedException {
		ExtentTestManager.getTest().log(Status.SKIP, "Skipped test");
		log.debug("Skip test");
		throw new SkipException("Skipped");
	}


	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();  
	}
}
